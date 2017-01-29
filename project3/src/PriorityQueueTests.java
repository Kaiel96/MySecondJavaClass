/**

 * JUnit tests for PriorityQueue
 *
 * @author Brannden Moss
 * @version 01/29/2016 Developed for CPE 103 Program 3 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriorityQueueTests
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
      assertTrue(PriorityQueue.class.getSuperclass() == Object.class);
   }

   @Test
   public void test02_verifyInterfaces()
   {
      Class[] faces = PriorityQueue.class.getInterfaces();
      assertTrue(faces.length == 0);
   }

   @Test
   public void test03_verifyFields()
   {
      Field[] fields = PriorityQueue.class.getDeclaredFields();
      assertTrue(fields.length == 0);
   }

   @Test
   public void test04_verifyConstructors()
   {
      Constructor[] cons = PriorityQueue.class.getDeclaredConstructors();
      assertTrue(cons.length == 1);
   }

   @Test
   public void test05_verifyMethods()
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = PriorityQueue.class.getDeclaredMethods();

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
   public void test06_basicEnqueueMin()
   {
      Integer [] a = new Integer[] {1, 2, 3, 4, 5};
      PriorityQueue<Integer> pq = new PriorityQueue <Integer>(a,5,true);
      pq.enqueue(6);   
   }
   @Test
   public void test07_basicEnqueueMax()
   {
      Integer [] a = new Integer[] {1, 2, 3, 4, 5};
      PriorityQueue<Integer> pq = new PriorityQueue <Integer>(a,5);
      pq.enqueue(1);   
   }
   @Test
   public void test08_singleSwap_minEnqueue()
   {
      Integer [] a = new Integer[] {1, 2, 3, 4, 5};
      PriorityQueue<Integer> pq = new PriorityQueue <Integer>(a,5);
      pq.enqueue(3);
   }
   @Test
   public void test09_singleSwap_maxEnqueue()
   {
      Integer [] a = new Integer[] {1, 2, 3, 4, 5};
      PriorityQueue<Integer> pq = new PriorityQueue <Integer>(a,5);
      pq.enqueue(3);
   }
   @Test
   public void test10_doubleSwap_minEnqueue()
   {
      Integer [] a = new Integer[] {1, 2, 3, 4, 5};
      PriorityQueue<Integer> pq = new PriorityQueue <Integer>(a,5);
      pq.enqueue(1);  
   }
   @Test
   public void test11_doubleSwap_maxEnqueue()
   {
      Integer [] a = new Integer[] {1, 2, 3, 4, 5};
      PriorityQueue<Integer> pq = new PriorityQueue <Integer>(a,5);
      pq.enqueue(7); 
   }
   @Test
   public void test12_minDequeue()
   {
      Integer [] a = new Integer[] {1, 2, 3, 4, 5};
      PriorityQueue<Integer> pq = new PriorityQueue <Integer>(a,5);
      pq.dequeue(); 
   }
   @Test
   public void test13_maxDequeue()
   {
      Integer [] a = new Integer[] {1, 2, 3, 4, 5};
      PriorityQueue<Integer> pq = new PriorityQueue <Integer>(a,5,false);
      pq.dequeue();
      assertTrue(a[0] == 1);
   }
   @Test
   public void test14_kth()
   {
      Integer [] a = new Integer[] {99, 2, 3, 4, 1};
      assertTrue(PriorityQueue.kth(a, 5, 3) == 1);
   }
   public void test15_kthMax()
   {
      Integer [] a = new Integer[] {99, 2, 3, 4, 1};
      assertTrue(PriorityQueue.kth(a, 5, 3) == 1);
   }
   
   
}