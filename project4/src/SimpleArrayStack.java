/**
 * SimpleArrayStack implementation for lab3
 *
 * @author Brannden Moss
 * @version 1/18/2016 Developed for CPE 103 Lab 3
 */

import java.util.NoSuchElementException;
public class SimpleArrayStack<Element> implements SimpleStack<Element> 
{
   private Object[] myArray;
   private int size = 0; 
   public SimpleArrayStack()
   {
      size = 0;
      myArray = new Object[10];
   }
   public int size()
   {
      return size;
   }
   private void double_list() 
   {
      if (size % myArray.length == 0 && size != 0)
      {
         Object[] temp = new Object[size*2];
         int i = 0;
         while(i < size)
         {
            temp[i] = myArray[i];
            i++;
         }
         myArray = temp;
      }
   }
   public int capacity()
   {
      return myArray.length;
   }
   public void push(Element element)
   {
      double_list();
      myArray[size] = element; 
      size ++;
   }
   @SuppressWarnings("unchecked")
   public Element peek()
   {
      if (size == 0)
      {
         throw new NoSuchElementException();    
      }
      else
      {
         return (Element) myArray[size - 1];
      }
   }
   @SuppressWarnings("unchecked")
   public Element pop()
   {
      if (size == 0)
      {
         throw new NoSuchElementException();    
      }
      else
      {
         size --;
         return (Element) myArray[size];
      }
   }
   
}