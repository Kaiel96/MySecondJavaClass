import static org.junit.Assert.*;

import org.junit.Test;
/**
* An RPN JUnit Tester in Java constructed for Project2
*
* @author Brannden Moss
* @version 1/19/2016 Developed for Project2  
*/
public class RPNTests 
{
   @Test
   public void RPNeval01()
   {
      String s = "5 2 +";
      assertTrue(RPN.evaluateRPN(s) == 7.0);
      s = "5.1 2 +";
      assertTrue(RPN.evaluateRPN(s) == 7.1);
      s = "5 2.1 +";
      assertTrue(RPN.evaluateRPN(s) == 7.1);
      s = "5.1 2.2 +";
      assertTrue(RPN.evaluateRPN(s) == 7.3);
      
      s = "5 2 -";
      assertTrue(RPN.evaluateRPN(s) == 3.0);
      s = "5.1 2 -";
      assertEquals(RPN.evaluateRPN(s), 3.1, .0000001);
      s = "5 2.1 -";
      assertTrue(RPN.evaluateRPN(s) == 2.9);
      s = "5.1 2.2 -";
      assertTrue(RPN.evaluateRPN(s) == 2.8999999999999995);
   }
   public void RPNeval02()
   {
      assertEquals(RPN.evaluateRPN("15 7.1 + 11 +"), 33.1, .000001);
      assertEquals(RPN.evaluateRPN("15 7.1 11 + +"), 33.1, .000001);
      
      assertEquals(RPN.evaluateRPN("15 7.1 + 11 + -3 +"), 30.1, .000001);
      assertEquals(RPN.evaluateRPN("15 7.1 11 -3 + + +"), 30.1, .000001);
      
      assertEquals(RPN.evaluateRPN("15 7.1 + -11 + 3 +"), 14.1, .000001);
      assertEquals(RPN.evaluateRPN("15 7.1 -11 3 + + +"), 14.1, .000001);
      
      assertEquals(RPN.evaluateRPN("15 -7.1 + 11 + 3 +"), 21.9, .000001);
      assertEquals(RPN.evaluateRPN("15 -7.1 11 3 + + +"), 21.9, .000001);
      
      assertEquals(RPN.evaluateRPN("-15 7.1 + 11 + 3 +"), 26.1, .000001);
      assertEquals(RPN.evaluateRPN("-15 7.1 11 3 + + +"), 26.1, .000001);
   }
   
   
}
