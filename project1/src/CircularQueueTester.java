/**

* A Simple circular queue tester in Java constructed for Project 1
*
* @author Brannden Moss
* @version 1/11/2016 Developed for Project 1   
*/
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;

/*
 * The peek() and size() methods are used to verify the behavior of
 * enqueue and dequeue and are not tested separately.
 */
public class CircularQueueTester
{ 
   @Test
   public void test01_verifySuperClass()
   {
      assertTrue(CircularQueue.class.getSuperclass() == Object.class);
   }

   @Test
   public void test02_verifyInterfaces()
   {
      Class[] faces = CircularQueue.class.getInterfaces();

      assertTrue(faces.length == 1);
      assertTrue(faces[0] == SimpleQueue.class);
   }

   @Test
   public void test03_verifyFields()
   {
      int arrayCount = 0;
      int intCount = 0;
      int constCount = 0;

      Field[] fields = CircularQueue.class.getDeclaredFields();

      // 1 constant, 1 array, and 2 or 3 ints (front, back, and optional size. 
      assertTrue(fields.length == 4 || fields.length == 5);

      for (int i = 0; i < fields.length; i++)
      {

         if (fields[i].getType() == Object[].class)
         {
            assertTrue(Modifier.isPrivate(fields[i].getModifiers()));
            arrayCount++;
         }
         else if (fields[i].getType() == int.class)
         {
            if (Modifier.isPublic(fields[i].getModifiers()))
            {
               constCount++;
            }
            else if (Modifier.isPrivate(fields[i].getModifiers()))
            {
               intCount++;
            }
         }
      }

      assertTrue(arrayCount == 1);
      assertTrue(intCount == 2 || intCount == 3);
      assertTrue(constCount == 1);
   }

   @Test
   public void test04_verifyConstructors()
   {
      int count = 0;

      Constructor[] cons = CircularQueue.class.getDeclaredConstructors();
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

      Method[] meths = CircularQueue.class.getDeclaredMethods();

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
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      assertTrue(q.size() == 0);
   }

   @Test(expected=NoSuchElementException.class)
   public void test07_peekAtConstruction()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      q.peek();
   }

   @Test(expected=NoSuchElementException.class)
   public void test08_dequeueAtConstruction()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      q.dequeue();
   }

   @Test
   public void test09_enqueuePeekSize()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      q.enqueue(39);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 39);

      q.enqueue(-55);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == 39);

      q.enqueue(7);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == 39);
   }

   @Test
   public void test10_dequeuePeekSize()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      q.enqueue(1);
      q.enqueue(2);
      q.enqueue(3);

      assertTrue(q.size() == 3);
      assertTrue(q.peek() == 1);

      assertTrue(q.dequeue() == 1);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == 2);

      assertTrue(q.dequeue() == 2);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 3);

      assertTrue(q.dequeue() == 3);
      assertTrue(q.size() == 0);
   }

   @Test(expected=NoSuchElementException.class)
   public void test11_peekExceptionAfterDequeue()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      q.enqueue(1);
      q.enqueue(2);
      q.enqueue(3);
      q.dequeue();
      q.dequeue();
      q.dequeue();

      q.peek();
   }

   @Test(expected=NoSuchElementException.class)
   public void test12_dequeueExceptionAfterDequeue()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      q.enqueue(1);
      q.enqueue(2);
      q.enqueue(3);
      q.dequeue();
      q.dequeue();
      q.dequeue();

      q.dequeue();
   }
      
   @Test
   public void test13_mixedEnqueueDequeue()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();

      q.enqueue(1);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 1);

      assertTrue(q.dequeue() == 1);
      assertTrue(q.size() == 0);

      q.enqueue(1);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 1);

      q.enqueue(2);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == 1);

      assertTrue(q.dequeue() == 1);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 2);

      q.enqueue(3);
      q.enqueue(4);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == 2);

      assertTrue(q.dequeue() == 2);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == 3);
      
      assertTrue(q.dequeue() == 3);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 4);
      assertTrue(q.dequeue() == 4);
      assertTrue(q.size() == 0);
   }

   @Test
   public void test14_verifyConstant()
   {
      assertTrue(CircularQueue.INITIAL_LENGTH == 10);
   }
   
   @Test
   public void test15_capacityAtConstruction()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();

      Object[] a = q.unusualMethodForTestingPurposesOnly();
      assertTrue(a.length == CircularQueue.INITIAL_LENGTH);
   }
   
   @Test
   public void test16_wrapNoGrow()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      q.enqueue(37);
      q.enqueue(38);
      q.enqueue(39);
      q.enqueue(40);
      q.enqueue(41);
      q.enqueue(42);
      q.enqueue(43);
      q.enqueue(44);
      q.enqueue(45);
      assertTrue(q.size() == 9);
      assertTrue(q.dequeue() == 37);
      assertTrue(q.dequeue() == 38);
      assertTrue(q.dequeue() == 39);
      assertTrue(q.dequeue() == 40);
      assertTrue(q.dequeue() == 41);
      assertTrue(q.dequeue() == 42);
      assertTrue(q.dequeue() == 43);
      assertTrue(q.dequeue() == 44);
      assertTrue(q.size() == 1);
      q.enqueue(46);
      q.enqueue(47);
      q.enqueue(48);
      q.enqueue(49);
      q.enqueue(50);
      assertTrue(q.size() == 6);
      q.enqueue(51);
      q.enqueue(52);
      q.enqueue(53);
      q.enqueue(54);
      assertTrue(q.size() == 10);
      assertTrue(q.dequeue() == 45);
      assertTrue(q.dequeue() == 46);
      assertTrue(q.dequeue() == 47);
      assertTrue(q.dequeue() == 48);
      assertTrue(q.dequeue() == 49);
      assertTrue(q.dequeue() == 50);
      assertTrue(q.dequeue() == 51);
      assertTrue(q.dequeue() == 52);
      assertTrue(q.dequeue() == 53);
      assertTrue(q.dequeue() == 54);
      assertTrue(q.size() == 0);
   }
      
   @Test
   public void test17_growthNoWrap()
   {
      int i = 0;
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      Field[] fields = CircularQueue.class.getDeclaredFields();

      // If they are using 5 fields they are keeping track of size and should
      // be able to use all of the array, otherwise they have to calculate
      // size and can only use length-1 array elements.
      boolean usingSize = CircularQueue.class.getDeclaredFields().length == 5;

      Object[] a = q.unusualMethodForTestingPurposesOnly();
      Object[] aa = null;

      // Note: I verify the correct length at construction elsewhere!
      int len = a.length;

      // Adjust len for use of size or not...
      int maxSize = usingSize ? len : len - 1;

      // Fill it up...
      for (i = 0; i < maxSize; i++)
      {
         q.enqueue(i);
         assertTrue(q.size() == i + 1);
         assertTrue(q.peek() == 0);
         
         // Make sure underlying array does not change
         // This also implies its capacity has not changed either.
         aa = q.unusualMethodForTestingPurposesOnly();
         assertTrue(aa == a);
      }

      // Add one, this should cause capacity to double
      q.enqueue(i);
      assertTrue(q.size() == i + 1);
      assertTrue(q.peek() == 0);
      a = q.unusualMethodForTestingPurposesOnly();
      len *= 2;
      assertTrue(a.length == len);
      
      // Adjust len for use of size or not...
      maxSize = usingSize ? len : len - 1;
      
      // Fill it up...
      for (i++; i < maxSize; i++)
      {
         q.enqueue(i);
         assertTrue(q.size() == i + 1);
         assertTrue(q.peek() == 0);
         
         // Make sure underlying array does not change
         // This also implies its capacity has not changed either.
         aa = q.unusualMethodForTestingPurposesOnly();
         assertTrue(aa == a);
      }
      
      // Add one, this should cause capacity to double
      q.enqueue(i); 
      assertTrue(q.size() == i + 1);
      assertTrue(q.peek() == 0);
      a = q.unusualMethodForTestingPurposesOnly();
      len *= 2;
      assertTrue(a.length == len);
      
      // Adjust len for use of size or not...
      maxSize = usingSize ? len : len - 1;

      // Fill it up...
      for (i++; i < maxSize; i++)
      {
         q.enqueue(i);
         assertTrue(q.size() == i + 1);
         assertTrue(q.peek() == 0);
         
         // Make sure underlying array does not change
         // This also implies its capacity has not changed either.
         aa = q.unusualMethodForTestingPurposesOnly();
         assertTrue(aa == a);
      }
      
      // Add one, this should cause capacity to double
      q.enqueue(i);
      assertTrue(q.size() == i + 1);
      assertTrue(q.peek() == 0);
      a = q.unusualMethodForTestingPurposesOnly();
      len *= 2;
      assertTrue(a.length == len);
   }

   @Test
   public void test18_growthWithWrapping()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();
      
      // If they are using 5 fields they are keeping track of size and should
      // be able to use all of the array, otherwise they have to calculate
      // size and can only use length-1 array elements.
      boolean usingSize = CircularQueue.class.getDeclaredFields().length == 5;
      
      int len = CircularQueue.INITIAL_LENGTH;

      // Fill it to half capacity
      for(int i = 0; i < len / 2; i++)
      {
         q.enqueue(i);
      }

      // dequeue until empty
      for (int i = 0; i < len / 2; i++)
      {
         q.dequeue();
      }
      
      // Adjust len for use of size or not...
      int maxSize = usingSize ? len : len - 1;

      // Fill it - should cause a wrap but no growth
      for (int i = 0; i < maxSize; i++)
      {
         q.enqueue(i);
      }

      Object[] a = q.unusualMethodForTestingPurposesOnly();
      assertTrue(q.size() == maxSize);
      assertTrue(a.length == len);

      // Add one to grow on...
      q.enqueue(maxSize);
      a = q.unusualMethodForTestingPurposesOnly();
      assertTrue(q.size() == maxSize + 1);
      assertTrue(a.length == 2 * len);

      //Check contents...
      for (int i = 0; i < maxSize + 1; i++)
      {
         assertTrue(q.dequeue() == i);
         assertTrue(q.size() == maxSize - i);
      }

      // Make sure array is the same - means they didn't do something they
      // shouldn't have and implies the length hasn't changed.
      Object[] aa = q.unusualMethodForTestingPurposesOnly();
      assertTrue(aa == a);
   }

   // Would time out occationally at 20ms on a 2.4ghz machine so 50ms
   // should be ample.
   @Test(timeout=50)
   public void test19_bigOhTest()
   {
      CircularQueue<Integer> q = new CircularQueue<Integer>();

      for (int i = 0; i < 80000; i++)
      {
         q.enqueue(i);
         q.peek();
      }

      for (int i = 0; i < 80000; i++)
      {
         q.dequeue();
      }
   }
}