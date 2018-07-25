/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleet;

import java.util.ArrayList;

/**
 *
 * @author Theresa
 */
public class Utility {
      public static String charToStr(char a[])
    {
        String t="";
        for(int i=0;i<a.length;i++)
            t = t+a[i];
        return t;
    }

    public int emptyFields(ArrayList<String> arl)
    { int count = 0;

        for(int i=0;i<arl.size();i++)
        {
            System.out.println("Here "+i);
            if(arl.get(i).isEmpty()||arl.get(i).equals("")||arl.get(i)==null||arl.get(i).toLowerCase().equals("null"))
                count++;
        }
        return count;
    }
    
}
