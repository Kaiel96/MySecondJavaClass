/**
 * JUnit tests for SimpleArrayStack implementation for lab3
 *
 * @author Brannden Moss
 * @version 1/18/2016 Developed for CPE 103 Lab 3
 */

import static org.junit.Assert.*;
import org.junit.*;
import java.util.NoSuchElementException;
import java.lang.reflect.*;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleArrayStackTester
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
      assertTrue(SimpleArrayStack.class.getSuperclass() == Object.class);
   }

   @Test
   public void test02_verifyInterfaces() 
   {
      Class[] faces = SimpleArrayStack.class.getInterfaces();

      assertTrue(faces.length == 1);
      assertTrue(faces[0] == SimpleStack.class);
   }

   @Test
   public void test03_verifyFields() 
   {
      int arrayCount = 0;
      int intCount = 0;
      Field[] fields = SimpleArrayStack.class.getDeclaredFields();

      assertTrue(fields.length <= 3);

      for (int i = 0; i < fields.length; i++)
      {
         assertTrue(Modifier.isPrivate(fields[i].getModifiers()));

         if (fields[i].getType() == Object[].class)
         {
            arrayCount++;
         }
         else if (fields[i].getType() == int.class)
         {
            intCount++;
         }
      }

      assertTrue(arrayCount == 1);
      assertTrue(intCount <= 2);
   }

   @Test
   public void test04_verifyConstructors() 
   {
      int count = 0;
      Constructor[] cons = SimpleArrayStack.class.getDeclaredConstructors();

      assertTrue(cons.length == 1);
      assertTrue(Modifier.isPublic(cons[0].getModifiers()));

      Class[] params = cons[0].getParameterTypes();
     
      assertTrue(params.length == 0);
   }

   @Test
   public void test05_verifyMethods() 
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = SimpleArrayStack.class.getDeclaredMethods();

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

      assertTrue(countPublic == 5);
      assertTrue(countProtected == 0);
      assertTrue(countPackage == 0);
   }

   @Test
   public void test06_sizeAtConstruction() 
   {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();
      assertTrue(s.size() == 0);
   }
   
   @Test
   public void test07_capacityAtConstruction() 
   {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();
      assertTrue(s.capacity() == 10);
   }

   @Test(expected=NoSuchElementException.class)
   public void test08_peekAtConstruction() 
   {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();
      s.peek();
   }

   @Test(expected=NoSuchElementException.class)
   public void test09_popAtConstruction()  
   {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();
      s.pop();
   }

   @Test
   public void test10_simplePush() 
   {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();
    
      // Test up to, but not requiring, capacity change
      int[] a = new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

      for (int i = 0; i < a.length; i++)
      {
         s.push(a[i]);
         assertTrue(s.size() == i + 1);
         assertTrue(s.capacity() == 10);
         assertTrue(s.peek() == a[i]);
      }
   }

   @Test
   public void test11_simplePop() 
   {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();

      for (int i = 0; i < 10; i++)
      {
         s.push(i * 13);
      }

      for (int i = 9; i > -1; i--)
      {
         assertTrue(s.peek() == i * 13);
         assertTrue(s.pop() == i * 13);
         assertTrue(s.size() == i);
      }
   }

   @Test
   public void test12_pushPopCombo() 
   {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();

      s.push(55);
      s.push(66);
      s.pop();
      s.push(77);
      s.push(33);
      s.push(11);
      s.pop();
      s.push(-4);
      s.push(89);
      s.push(-521);
      s.push(333);
      s.push(-9);
      s.push(45);
      s.push(61);
      s.push(93);

      assertTrue(s.pop() == 93);
      assertTrue(s.pop() == 61);
      assertTrue(s.pop() == 45);
      assertTrue(s.pop() == -9);
      assertTrue(s.pop() == 333);
      assertTrue(s.pop() == -521);
      assertTrue(s.pop() == 89);
      assertTrue(s.pop() == -4);
      assertTrue(s.pop() == 33);
      assertTrue(s.pop() == 77);
      assertTrue(s.pop() == 55);
   }

   @Test
   public void test13_pushPopCapacityGrowth()
   {
      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();

      for (int i = 0; i < 10; i++)
      {
         s.push(i);
      }

      assertTrue(s.size() == 10);
      assertTrue(s.capacity() == 10);
      assertTrue(s.peek() == 9);

      s.push(10);
      assertTrue(s.size() == 11);
      assertTrue(s.capacity() == 20);
      assertTrue(s.peek() == 10);
      
      for (int i = 11; i < 20; i++)
      {
         s.push(i);
      }
      
      assertTrue(s.size() == 20);
      assertTrue(s.capacity() == 20);
      assertTrue(s.peek() == 19);

      s.push(20);
      assertTrue(s.size() == 21);
      assertTrue(s.capacity() == 40);
      assertTrue(s.peek() == 20);
      
      for (int i = 21; i < 40; i++)
      {
         s.push(i);
      }
      
      assertTrue(s.size() == 40);
      assertTrue(s.capacity() == 40);
      assertTrue(s.peek() == 39);

      s.push(40);
      assertTrue(s.size() == 41);
      assertTrue(s.capacity() == 80);
      assertTrue(s.peek() == 40);

      for (int i = 41; i < 80; i++)
      {
         s.push(i);
      }
      
      assertTrue(s.size() == 80);
      assertTrue(s.capacity() == 80);
      assertTrue(s.peek() == 79);

      for (int i = 79; i > -1; i--)
      {
         assertTrue(s.peek() == i);
         assertTrue(s.pop() == i);
         assertTrue(s.size() == i);
         assertTrue(s.capacity() == 80);
      }
   }

   @Test(timeout=10)
   public void test14_testOrderPush() 
   {
      // This method test that the run time of the push method
      // is reasonable. The 10ms is about 3 times longer than
      // the code should take on a 2.4ghz machine.
      final int TEST_SIZE = 10000;

      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();

      //long start = System.currentTimeMillis();

      for (int i = 0; i < TEST_SIZE; i++)
      {
         s.push(i);
      }

      //long stop = System.currentTimeMillis();
      //System.out.println("\nRUNTIME: " + (stop - start) + "ms");
   }

   @Test(timeout=10)
   public void test15_testOrderPop() 
   {
      // This method test that the run time of the push method
      // is reasonable. The 10ms is about 3 times longer than
      // the code should take on a 2.4ghz machine - note that the
      // pop() calls are virtually free (see push test above)!
      final int TEST_SIZE = 10000;

      SimpleArrayStack<Integer> s = new SimpleArrayStack<Integer>();

      //long start = System.currentTimeMillis();

      for (int i = 0; i < TEST_SIZE; i++)
      {
         s.push(i);
      }

      for (int i = 0; i < TEST_SIZE; i++)
      {
         s.pop();
      }

      //long stop = System.currentTimeMillis();
      //System.out.println("\nRUNTIME: " + (stop - start) + "ms");
   }
}