/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Theresa
 */
public class Connection {
     public java.sql.Connection getconnection()
   {
        java.sql.Connection cn = null;
             try
              {
            Class.forName("com.mysql.jdbc.Driver") ;
              cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fleet?user=root&password=");
              }
            catch(Exception ex)
             {
                System.out.println("Error in db connect-->"+ex.toString());
             }
         return cn;
   }

  
   public boolean executeCommand(String task,String s)
    { boolean flag = false;


              try{
                  Connection conn = new Connection();
                  java.sql.Connection  c = conn.getconnection();
                  stmt = c.createStatement();
                  if(task.equals("select"))
                  {
                  rs=stmt.executeQuery(s);
                   if (rs.next())
                   flag = true;
                  }
                  else
                  {
                     stmt.executeUpdate(s);
                     flag = true;
                  }

                   stmt.close();
                  c.close();
                 }
               catch(Exception excp)
                {  JOptionPane.showMessageDialog(null,"Not Stored Successfully! -->"+excp,"Error",JOptionPane.ERROR_MESSAGE);  }
      return flag;
    }

   public String getData(String query,String column_name)
    {
       String result="";

       try{
                  Connection conn = new Connection();
                  java.sql.Connection  c = conn.getconnection();
                  stmt = c.createStatement();
                  rs=stmt.executeQuery(query);
  
                   if (rs.next())
                   result = rs.getString(column_name);

                   stmt.close();
                  c.close();
        }
       catch(Exception ex){ System.out.println("error in procedure getData --> "+ex.toString()); }


       return result;
    }

    public boolean exists(String query,String column_name)
    {
        boolean res=false;
        String data = getData(query,column_name);
        if(!data.equals(""))
            res = true;

        return res;
    }


   //Method for selecting from a single column in a table
   public ArrayList<String> getAllSingleColumnData(String instr,String column_name)
    {
       ArrayList<String> arl = new ArrayList<String>();

       try{
                  Connection conn = new Connection();
                  java.sql.Connection  c = conn.getconnection();
                  stmt = c.createStatement();
                   rs = stmt.executeQuery(instr);

     while(rs.next())
     {
                  arl.add(rs.getString(column_name));
     }

                 stmt.close();
                 c.close();
                 }
               catch(Exception excp)
                {    JOptionPane.showMessageDialog(null, ""+excp.toString()); }

       return arl;
    }

   //Method for selecting from multiple columns in a table
   public ArrayList<ArrayList<String>> getAllMultipleColumnData(String instr,String[] column_names)
    {
       ArrayList<ArrayList<String>> aarl = new ArrayList<ArrayList<String>>();


       try{
                  Connection conn = new Connection();
                  java.sql.Connection  c = conn.getconnection();
                  stmt = c.createStatement();

                   for(int i=0; i<column_names.length;i++)
                   {ArrayList<String> arl  = new ArrayList<String>();
                    rs = stmt.executeQuery(instr);
                       while(rs.next())
                           {
                           arl.add(rs.getString(column_names[i]).trim());
                           }
                 aarl.add(arl);
                   }


                 stmt.close();
                 c.close();
                 }
               catch(Exception excp)
                {    }

       return aarl;
    }

  private Statement stmt;
  private ResultSet rs;
    
}
