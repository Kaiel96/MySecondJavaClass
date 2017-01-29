/**

 * JUnit tests for RPN
 *
 * @author Kurt Mammen
 * @version 10/07/2012 Developed for CPE 103 Program 2 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RPNAcceptanceTests
{   
   @Rule
   public TestRule watcher = new TestWatcher() {
      protected void starting(Description description) {
         System.out.print("Starting: " + description.getMethodName() + "...");
      }
   };
   @Rule
   public Stopwatch sw = new Stopwatch() {
      protected void finished(long nanos, Description description) {
         System.out.println(" (" + runtime(TimeUnit.MILLISECONDS) + " ms)");
      }
      protected void succeeded(long nanos, Description description) {
         System.out.print("Passed");
      }
      protected void failed(long nanos, Throwable e, Description description) {
         System.out.print("Failed");
      }
   };

   @Test
   public void test01_verifySuperClass()
   {
      assertTrue(RPN.class.getSuperclass() == Object.class);
   }

   @Test
   public void test02_verifyInterfaces()
   {
      Class[] faces = RPN.class.getInterfaces();
      assertTrue(faces.length == 0);
   }

   @Test
   public void test03_verifyFields()
   {
      Field[] fields = RPN.class.getDeclaredFields();
      assertTrue(fields.length == 0);
   }

   @Test
   public void test04_verifyConstructors()
   {
      Constructor[] cons = RPN.class.getDeclaredConstructors();
      assertTrue(cons.length == 1);
   }

   @Test
   public void test05_verifyMethods()
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = RPN.class.getDeclaredMethods();

      for (Method m : meths)
      {
         if (Modifier.isPublic(m.getModifiers()))
         {
            countPublic++;
         }
         else if (Modifier.isProtected(m.getModifiers()))
         {
            countProtected++;
         }
         else if (Modifier.isPrivate(m.getModifiers()))
         {
            countPrivate++;
         }
         else
         {
            countPackage++;
         }
      }

      assertTrue(countPublic == 3);
      assertTrue(countProtected == 0);
      assertTrue(countPackage == 0);
   }

   @Test
   public void test06_evaluateRPNBasicAdd()
   {
      
      assertEquals(RPN.evaluateRPN("5 7 +"), 12.0, .000001);
      assertEquals(RPN.evaluateRPN("5.1 7 +"), 12.1, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 +"), 12.1, .000001);
      assertEquals(RPN.evaluateRPN("5.1 7.1 +"), 12.2, .000001);
      
      
      assertEquals(RPN.evaluateRPN("5.1 -7.2 +"), -2.1, .000001);
      assertEquals(RPN.evaluateRPN("-5.1 7.2 +"), 2.1, .000001);
      assertEquals(RPN.evaluateRPN("-5.1 -7.2 +"), -12.3, .000001);
   }

   @Test
   public void test07_evaluateRPNComplexAdd()
   {
      assertEquals(RPN.evaluateRPN("5 7.1 + 11 +"), 23.1, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 11 + +"), 23.1, .000001);
      
      assertEquals(RPN.evaluateRPN("5 7.1 + 11 + -3 +"), 20.1, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 11 -3 + + +"), 20.1, .000001);
      
      assertEquals(RPN.evaluateRPN("5 7.1 + -11 + 3 +"), 4.1, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 -11 3 + + +"), 4.1, .000001);
      
      assertEquals(RPN.evaluateRPN("5 -7.1 + 11 + 3 +"), 11.9, .000001);
      assertEquals(RPN.evaluateRPN("5 -7.1 11 3 + + +"), 11.9, .000001);
      
      assertEquals(RPN.evaluateRPN("-5 7.1 + 11 + 3 +"), 16.1, .000001);
      assertEquals(RPN.evaluateRPN("-5 7.1 11 3 + + +"), 16.1, .000001);
   }

   @Test
   public void test08_evaluateRPNBasicSub()
   {
      assertEquals(RPN.evaluateRPN("5 7 -"), -2.0, .000001);
      assertEquals(RPN.evaluateRPN("5.1 7 -"), -1.9, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 -"), -2.1, .000001);
      assertEquals(RPN.evaluateRPN("5.1 7.1 -"), -2.0, .000001);
      
      assertEquals(RPN.evaluateRPN("5.1 -7.2 -"), 12.3, .000001);
      assertEquals(RPN.evaluateRPN("-5.1 7.2 -"), -12.3, .000001);
      assertEquals(RPN.evaluateRPN("-5.1 -7.2 -"), 2.1, .000001);
   }

   @Test
   public void test09_evaluateRPNComplexSub()
   {
      assertEquals(RPN.evaluateRPN("5 7.1 - 11 -"), -13.1, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 11 - -"), 8.9, .000001);
      
      assertEquals(RPN.evaluateRPN("5 7.1 - 11 - -3 -"), -10.1, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 11 -3 - - -"), 11.9, .000001);
      
      assertEquals(RPN.evaluateRPN("5 7.1 - -11 - 3 -"), 5.9, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 -11 3 - - -"), -16.1, .000001);
      
      assertEquals(RPN.evaluateRPN("5 -7.1 - 11 - 3 -"), -1.9, .000001);
      assertEquals(RPN.evaluateRPN("5 -7.1 11 3 - - -"), 20.1, .000001);
      
      assertEquals(RPN.evaluateRPN("-5 7.1 - 11 - 3 -"), -26.1, .000001);
      assertEquals(RPN.evaluateRPN("-5 7.1 11 3 - - -"), -4.1, .000001);
   }

   @Test
   public void test10_evaluateRPNBasicMul()
   {
      assertEquals(RPN.evaluateRPN("5 7 *"), 35.0, .000001);
      assertEquals(RPN.evaluateRPN("5.1 7 *"), 35.7, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 *"), 35.5, .000001);
      assertEquals(RPN.evaluateRPN("5.1 7.1 *"), 36.21, .000001);
      
      assertEquals(RPN.evaluateRPN("5.1 -7.2 *"), -36.72, .000001);
      assertEquals(RPN.evaluateRPN("-5.1 7.2 *"), -36.72, .000001);
      assertEquals(RPN.evaluateRPN("-5.1 -7.2 *"), 36.72, .000001);
   }

   @Test
   public void test11_evaluateRPNComplexMul()
   {
      assertEquals(RPN.evaluateRPN("5 7.1 * 11 *"), 390.5, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 11 * *"), 390.5, .000001);
      
      assertEquals(RPN.evaluateRPN("5 7.1 * 11 * -3 *"), -1171.5, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 11 -3 * * *"), -1171.5, .000001);
      
      assertEquals(RPN.evaluateRPN("5 7.1 * -11 * 3 *"), -1171.5, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 -11 3 * * *"), -1171.5, .000001);
      
      assertEquals(RPN.evaluateRPN("5 -7.1 * 11 * 3 *"), -1171.5, .000001);
      assertEquals(RPN.evaluateRPN("5 -7.1 11 3 * * *"), -1171.5, .000001);
      
      assertEquals(RPN.evaluateRPN("-5 7.1 * 11 * 3 *"), -1171.5, .000001);
      assertEquals(RPN.evaluateRPN("-5 7.1 11 3 * * *"), -1171.5, .000001);
   }

   @Test
   public void test12_evaluateRPNBasicDiv()
   {
      assertEquals(RPN.evaluateRPN("5 7 /"), 0.714285714, .000001);
      assertEquals(RPN.evaluateRPN("5.1 7 /"), 0.728571429, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 /"), 0.704225352, .000001);
      assertEquals(RPN.evaluateRPN("5.1 7.1 /"), 0.718309859, .000001);
      
      assertEquals(RPN.evaluateRPN("5.1 -7.2 /"), -0.708333333, .000001);
      assertEquals(RPN.evaluateRPN("-5.1 7.2 /"), -0.708333333, .000001);
      assertEquals(RPN.evaluateRPN("-5.1 -7.2 /"), 0.708333333, .000001);
   }

   @Test
   public void test13_evaluateRPNComplexDiv()
   {
      
      assertEquals(RPN.evaluateRPN("5 7.1 / 11 /"), 0.064020487, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 11 / /"), 7.746478873, .000001);
      
      assertEquals(RPN.evaluateRPN("5 7.1 / 11 / -3 /"), -0.021340162, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 11 -3 / / /"), -2.582159624, .000001);
      
      assertEquals(RPN.evaluateRPN("5 7.1 / -11 / 3 /"), -0.021340162, .000001);
      assertEquals(RPN.evaluateRPN("5 7.1 -11 3 / / /"), -2.582159624, .000001);
      
      assertEquals(RPN.evaluateRPN("5 -7.1 / 11 / 3 /"), -0.021340162, .000001);
      assertEquals(RPN.evaluateRPN("5 -7.1 11 3 / / /"), -2.582159624, .000001);
     
      assertEquals(RPN.evaluateRPN("-5 7.1 / 11 / 3 /"), -0.021340162, .000001);
      assertEquals(RPN.evaluateRPN("-5 7.1 11 3 / / /"), -2.582159624, .000001);
   }

   @Test
   public void test14_evaluateRPNExtraWhiteSpace()
   {
      assertEquals(RPN.evaluateRPN("2  3 4 5  6  + - *  /"), -0.09523809, .000001);
   }

   @Test
   public void test15_evaluateRPNMixed()
   {
      // Rotate + - * /
      assertEquals(RPN.evaluateRPN("2 3 4 5 6 + - * /"), -0.09523809, .000001);
      assertEquals(RPN.evaluateRPN("2 3 + 4 - 5 * 6 /"),  0.83333333, .000001);

      assertEquals(RPN.evaluateRPN("2 3 4 5 6 - * / +"),  1.25, .000001);
      assertEquals(RPN.evaluateRPN("2 3 - 4 * 5 / 6 +"),  5.2, .000001);

      assertEquals(RPN.evaluateRPN("2 3 4 5 6 * / + -"), -1.13333333, .000001);
      assertEquals(RPN.evaluateRPN("2 3 * 4 / 5 + 6 -"),  0.5, .000001);

      assertEquals(RPN.evaluateRPN("2 3 4 5 6 / + - *"), -3.66666666, .000001);
      assertEquals(RPN.evaluateRPN("2 3 / 4 + 5 - 6 *"), -2.0, .000001);

      // Rotate / * - +
      assertEquals(RPN.evaluateRPN("2 3 4 5 6 / * - +"),  1.66666666, .000001);
      assertEquals(RPN.evaluateRPN("2 3 / 4 * 5 - 6 +"),  3.66666666, .000001);

      assertEquals(RPN.evaluateRPN("2 3 4 5 6 * - + /"), -0.0869565217, .000001);
      assertEquals(RPN.evaluateRPN("2 3 * 4 - 5 + 6 /"),  1.16666666, .000001);

      assertEquals(RPN.evaluateRPN("2 3 4 5 6 - + / *"),  2.0, .000001);
      assertEquals(RPN.evaluateRPN("2 3 - 4 + 5 / 6 *"),  3.6, .000001);

      assertEquals(RPN.evaluateRPN("2 3 4 5 6 + / * -"),  0.90909090, .000001);
      assertEquals(RPN.evaluateRPN("2 3 + 4 / 5 * 6 -"),  0.25, .000001);
   }

   @Test
   public void test16_evaluateRPN_SingleValue()
   {
      assertEquals(RPN.evaluateRPN("1.234"),  1.234, .000001);
      assertEquals(RPN.evaluateRPN("-5"),  -5, .000001);
   }
   
   @Test
   public void test17_toRPNPrecedenceDiv()
   {
      assertTrue(RPN.toRPN("2 / 3 / 4 / 5").equals("2 3 / 4 / 5 /"));
      assertTrue(RPN.toRPN("2 / ( 3 / ( 4 / 5 ) )").equals("2 3 4 5 / / /"));
      assertTrue(RPN.toRPN("( 2 / ( 3 / ( 4 / 5 ) ) )").equals("2 3 4 5 / / /"));
      assertTrue(RPN.toRPN("2 / ( 3 / 4 ) / 5").equals("2 3 4 / / 5 /"));
   }
   
   @Test
   public void test18_toRPNPrecedenceSub()
   {
      assertTrue(RPN.toRPN("2 - 3 - 4 - 5").equals("2 3 - 4 - 5 -"));
      
      assertTrue(RPN.toRPN("2 - ( 3 - ( 4 - 5 ) )").equals("2 3 4 5 - - -"));
      assertTrue(RPN.toRPN("( 2 - ( 3 - ( 4 - 5 ) ) )").equals("2 3 4 5 - - -"));
      assertTrue(RPN.toRPN("2 - ( 3 - 4 ) - 5").equals("2 3 4 - - 5 -"));
   }
 
   @Test
   public void test19_toRPNBasic()
   {
      assertTrue(RPN.toRPN("5 + 9").equals("5 9 +"));
      assertTrue(RPN.toRPN("5 - 9").equals("5 9 -"));
      assertTrue(RPN.toRPN("5 * 9").equals("5 9 *"));
      assertTrue(RPN.toRPN("5 / 9").equals("5 9 /"));
   }

   @Test
   public void test20_toRPNPrecedenceMixed()
   {
      // + +, + -, + *, + /
      assertTrue(RPN.toRPN("3 + 4 + 5").equals("3 4 + 5 +"));
      assertTrue(RPN.toRPN("3 + 4 - 5").equals("3 4 + 5 -"));
      assertTrue(RPN.toRPN("3 + 4 * 5").equals("3 4 5 * +"));
      assertTrue(RPN.toRPN("3 + 4 / 5").equals("3 4 5 / +"));

      // - +, - -, - *, - / 
      assertTrue(RPN.toRPN("3 - 4 + 5").equals("3 4 - 5 +"));
      assertTrue(RPN.toRPN("3 - 4 - 5").equals("3 4 - 5 -"));
      assertTrue(RPN.toRPN("3 - 4 * 5").equals("3 4 5 * -"));
      assertTrue(RPN.toRPN("3 - 4 / 5").equals("3 4 5 / -"));

      // * +, * -, * *,  * / 
      assertTrue(RPN.toRPN("3 * 4 + 5").equals("3 4 * 5 +"));
      assertTrue(RPN.toRPN("3 * 4 - 5").equals("3 4 * 5 -"));
      assertTrue(RPN.toRPN("3 * 4 * 5").equals("3 4 * 5 *"));
      assertTrue(RPN.toRPN("3 * 4 / 5").equals("3 4 * 5 /"));

      // / +, / -, / *, / /
      assertTrue(RPN.toRPN("3 / 4 + 5").equals("3 4 / 5 +"));
      assertTrue(RPN.toRPN("3 / 4 - 5").equals("3 4 / 5 -"));
      assertTrue(RPN.toRPN("3 / 4 * 5").equals("3 4 / 5 *"));
      assertTrue(RPN.toRPN("3 / 4 / 5").equals("3 4 / 5 /"));
   }

   @Test
   public void test21_toRPNComplex()
   {
      System.out.println("Result: " + RPN.toRPN("( 5 * ( 4 + 3 - 7 ) / 6 )"));
      assertTrue(RPN.toRPN("( ( ( 5 + 4 ) - 3 ) * 6 )").equals("5 4 + 3 - 6 *"));
      assertTrue(RPN.toRPN("( ( 5 + 4 ) - 3 ) * 6").equals("5 4 + 3 - 6 *"));
      assertTrue(RPN.toRPN("( 5 * ( 4 + 3 - 7 ) / 6 )").equals("5 4 3 + 7 - * 6 /"));
      assertTrue(RPN.toRPN("5 * ( 4 + 3 - 7 ) / 6").equals("5 4 3 + 7 - * 6 /"));
   }

   @Test
   public void test22_mixed()
   {
      assertTrue(RPN.toRPN("5 * ( 6 + 3 - 7 * 3 + 2 ) / 6").equals("5 6 3 + 7 3 * - 2 + * 6 /"));
      assertEquals(RPN.evaluateRPN("6 4 3 + 2 - * 6 /"), 5.0, .000001);
      assertEquals(RPN.evaluateRPN("5 2 4 * + 7 2 - 4 6 2 / 2 - * + 4 - +"), 18.0, .000001);
      assertTrue(RPN.toRPN("8 + 3 * 4 + ( 6 - 2 + 2 * ( 6 / 3 - 1 ) - 3 )").equals("8 3 4 * + 6 2 - 2 6 3 / 1 - * + 3 - +"));

   }
   
   @Test
   public void test23_evaluateInfixComplex()
   {
      assertEquals(RPN.evaluateInfix("( 3 + 2 ) + 8 / 3 * 17 - ( 12 / 4.2 / 1.2 - 8 * 6 ) * ( ( 6.9 * 17 + 23 / 6 - 2.2 ) - 3.2 - ( 56 / 21 * 1.4 ) + 2.3 * 4.1 )"), 5589.854285714286, .000001);
      assertEquals(RPN.evaluateInfix("( ( ( ( 38 * 1.2 + 3.6 / 2.8 - 6 ) + 3.7 / 2 / 5 - 3 + 23 ) / 1.1 + 2.2 ) - 2.4 / 5 - 1 ) + ( 1.6 / 3 / 9 * 2.8 - 3 - ( 6.2 / 4 + ( 12.8 * 2 / 1.1 - 4.4 / ( 3.2 - 1.1 / 5.2 * 9.9 ) ) ) )"), 32.72934207499424, .000001);
      assertEquals(RPN.evaluateInfix("2 + ( ( ( ( 38 * 1.2 + 3.6 / 2.8 - 6 ) + 3.7 / 2 / 5 - 3 + 23 ) / 1.1 + 2.2 ) - 2.4 / 5 - 1 ) + ( 1.6 / 3 / 9 * 2.8 - 3 - ( 6.2 / 4 + ( 12.8 * 2 / 1.1 - 4.4 / ( 3.2 - 1.1 / 5.2 * 9.9 ) ) ) )"), 34.72934207499424, .000001);
      assertEquals(RPN.evaluateInfix("( ( ( ( 38 * 1.2 + 3.6 / 2.8 - 6 ) + 3.7 / 2 / 5 - 3 + 23 ) / 1.1 + 2.2 ) - 2.4 / 5 - 1 ) + ( 1.6 / 3 / 9 * 2.8 - 3 - ( 6.2 / 4 + ( 12.8 * 2 / 1.1 - 4.4 / ( 3.2 - 1.1 / 5.2 * 9.9 ) ) ) ) + -2"), 30.72934207499424, .000001);
   }

   @Test
   public void test24_evaluateInfixLarge()
   {
      assertEquals(RPN.evaluateInfix("1000000.1 + 2000000.2"), 3000000.3, .000001);
      assertEquals(RPN.evaluateInfix("1111111111.111111 + 2222222222.222222"), 3333333333.333333, .000001);
   }

   @Test
   public void test25_evaluateENotation()
   {
      assertEquals(RPN.evaluateInfix("1.234E10 + 2.345E10"), 3.579E10, .000001);
   }
}

