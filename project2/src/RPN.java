import java.util.Scanner;
import java.util.Stack;

import javax.lang.model.element.Element;

/**
* An RPN in Java constructed for Project2
*
* @author Brannden Moss
* @version 1/19/2016 Developed for Project2  
*/

public class RPN 
{  
   private static boolean lowerPrecInPeek(String simplePeek, String str)
   {
      if ( (simplePeek.equals("+")|| simplePeek.equals("-")) && (str.equals("*")||str.equals("/")) )
      {
         return true;
      }
      else
      {
         return false; 
      }
   }
   public static double evaluateRPN(String s)
   {
      Scanner sc = new Scanner(s);
      double d1 = 0.0;
      double d2 = 0.0;
      double r = 0.0;
      SimpleLinkedStack<Double> sls = new SimpleLinkedStack<Double>();
      while (sc.hasNext())
      {
         String str = sc.next(); 
         try
         {
            sls.push(Double.parseDouble(str));
         }
         
         catch(Exception NumberFormatException)
         {
               d2 = sls.pop();
               d1 = sls.pop();
               if (str.equals("*") )
               {
                  r =  d1* d2;
               }
               else if (str.equals("/"))
               {
                  r =  d1/d2;
               }
               else if (str.equals("-"))
               {
                  r =  d1 - d2;
               }
               else
               {
                  r =  d1 + d2;
               }
               sls.push(r);
         }
      }
      sc.close();
      return sls.pop();
   }
   public static String toRPN(String s)
   {
      SimpleLinkedStack<String> sls = new SimpleLinkedStack<String>();
      Scanner sc = new Scanner(s);
      String result = "";
      
      while (sc.hasNext())
      {
         String str = sc.next();
         
         try
         {
            Double.parseDouble(str);
            result = result + str + " ";
         }
         
         catch(Exception NumberFormatException)
         {
            if (str.equals("("))
            {
               sls.push(str);
               
            }
            else if ( str.equals(")") )
            {
               while (! sls.peek().equals("("))
               {
                  result += sls.pop() + " ";
               }
               sls.pop();
            }
            else if ( (sls.size() == 0) || (sls.peek().equals("(")) || (lowerPrecInPeek(sls.peek(), str)) )
            {
               sls.push(str);
            }
            else
            {
               while ( (sls.size() != 0) && (!sls.peek().equals("(")) && (!lowerPrecInPeek(sls.peek(), str)) )
               {
                  result += sls.pop() + " ";
               }
               sls.push(str);
            }
         }
      }
      
      sc.close();
      while (sls.size() != 0)
      {
         result += sls.pop() + " ";
      }
      return result.trim();
   }
   public static double evaluateInfix(String s)
   {
      String st = toRPN(s);
      return evaluateRPN(st);
   }
   
}
