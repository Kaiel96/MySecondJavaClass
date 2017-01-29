import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
* A Simple linked stack tester using JUnit in Java constructed for Project2
*
* @author Brannden Moss
* @version 1/19/2016 Developed for Project2  
*/
public class SimpleLinkedStackTests {
   @Test
   public void test01_testSizeInitial()
   {
      SimpleLinkedStack<Integer> ls = new SimpleLinkedStack<Integer>();
      assertTrue(ls.size() == 0);
   }
   @Test(expected = NoSuchElementException.class)
   public void test02_testPeekException()
   {
      SimpleLinkedStack<Integer> ls = new SimpleLinkedStack<Integer>();
      ls.peek();
   }
   @Test(expected = NoSuchElementException.class)
   public void test03_pop_exception()
   {
      SimpleLinkedStack<Integer> ls = new SimpleLinkedStack<Integer>();
      ls.pop();
   }
   @Test
   public void test04_push()
   {
      SimpleLinkedStack<Integer> ls = new SimpleLinkedStack<Integer>();
      ls.push(5);
      assertTrue(ls.size() == 1);
   }
   
   @Test
   public void test05_pop()
   {
      SimpleLinkedStack<Integer> ls = new SimpleLinkedStack<Integer>();
      ls.push(5);
      ls.push(6);
      assertTrue(ls.size() == 2);
      assertTrue(ls.pop() == 6);
   }
   @Test
   public void test06_peek()
   {
      SimpleLinkedStack<Integer> ls = new SimpleLinkedStack<Integer>();
      ls.push(5);
      ls.push(6);
      assertTrue(ls.size() == 2);
      assertTrue(ls.peek() == 6);
   }
   
   
   
   
   
   

}
