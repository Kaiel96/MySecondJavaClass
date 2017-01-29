
/**
* A Simple linked stack in Java constructed for Project2
*
* @author Brannden Moss
* @version 1/19/2016 Developed for Project2  
*/

import java.util.NoSuchElementException;
public class SimpleLinkedStack<Element> implements SimpleStack<Element> 
{  
   private class Node
   {
      public Node next;
      public Element element;
      
      public Node()
      {
         this.element = null;
         this.next = null;
      }
   }
   private Node top;
   private int size; 
   public SimpleLinkedStack()
   {
      size = 0;
      top  = new Node();
   }
   public Element peek() {
      if(size == 0)
      {
         throw new NoSuchElementException();
      }
      else
      {
         return top.next.element;
      }
   }
   @SuppressWarnings("unchecked")
   @Override
   public Element pop() {
      if (size == 0)
      {
         throw new NoSuchElementException();
      }
      else
      {
         Node temp = top.next;
         top.next = top.next.next;
         size --; 
         return temp.element;
      }
   }
   public void push(Element element)
   { 
      Node n = new Node();
      n.element = element;
      Node temp = top.next; 
      top.next = n;
      n.next = temp;
      size ++;
   }
   public int size() 
   {
      return size;
   }
}
