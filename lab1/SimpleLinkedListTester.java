/**
 * JUnit tests for SimpleLinkedList.
 *
 * @author Brannden Moss
 * @version 1/6/2016 Developed for CPE 103 Lab 1
 */
import org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;


public class SimpleLinkedListTester
{
   @Test
   public void test01_sizeAtConstruction() 
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      assertTrue(list.size() == 0);
   }

   @Test(expected=IndexOutOfBoundsException.class)
   public void test02_addLargeIndexAtConstruction() 
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(25, null);
   }
   

   @Test(expected=IndexOutOfBoundsException.class)
   public void test03_removeZeroAtConstruction() 
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.remove(0);
   }
      
   @Test
   public void test04_add_size() 
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(5);
      list.add(10);
      list.add(15);
      list.add(20);
      assertTrue(listEquals(list,new int[] {5, 10, 15, 20}));
      assertTrue(list.size() == 4);
   }
   @Test
   public void test05_getNode()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(20);
      assertEqual(20,getNode(1));
   }
   @Test 
   public void test06_add_at_end_empty()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(20);
      assertEqual(1,size);
   }

   @Test
   public void test07_add_at_end_multiple()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(20);
      list.add(30);
      list.add(40);
      assertEqual(40,list.get(3));
   }

   @Test 
   public void test08_add_at_index_empty()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(1,20);
      assertEqual(1,size);
   }

   @Test
   public void test09_add_at_index_multiple()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(1, 20);
      list.add(2, 30);
      list.add(3, 40);
      assertEqual(40,list.get(3));
   }

   @Test 
   public void test10_add_at_index_large_index()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(6,20);
   }
   @Test(expected=IndexOutOfBoundsException.class)

   @Test 

   public void test11_add_at_index_negative_index()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(6,20);
   }
   @Test(expected=IndexOutOfBoundsException.class)

   @Test 
   public void test12_remove_at_end()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(20);
      list.add(30);
      list.remove(2); 
      assertTrue(listEquals(list,new int[] {20}));

   }
   @Test 
   
   public void test13_remove_at_middle()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(20);
      list.add(30);
      list.add(40);
      list.remove(2);
      assertTrue(listEquals(list,new int[] {20,40}));
   }

   @Test
   public void  test14_remove_negative_index()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(20);
      list.remove(-2);
   }
   @Test(expected=IndexOutOfBoundsException.class)

   public void  test15_remove_large_index()
   {
      SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
      list.add(20);
      list.remove(2);
   }
   @Test(expected=IndexOutOfBoundsException.class)

   


   private boolean listEquals(SimpleLinkedList<Integer> list, int[] a)
   {
      if (list.size() != a.length)
      {
         return false;
      }

      for (int i = 0; i < a.length; i++)
      {
         if (list.get(i) != a[i])
         {
            return false;
         }
      }

      return true;
   }
   
   
   
}
