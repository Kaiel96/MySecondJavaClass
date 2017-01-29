/**
* A Simple circular queue in Java constructed for Project
*
* @author Brannden Moss
* @version 1/11/2016 Developed for Project1   
*/
import java.util.NoSuchElementException;

public class CircularQueue <Element> implements SimpleQueue<Element> 
{
   public static final int INITIAL_LENGTH = 10;
   private Object[] myArray; 
   private int front; 
   private int rear; 
   private int size; 
   public CircularQueue()
   {
      front = 0; 
      rear = 0;
      size = 0;
      myArray = new Object[INITIAL_LENGTH];
   }
   public Object[] unusualMethodForTestingPurposesOnly()
   {
      return myArray; 
   }
   public int  size()
   {
      return size;
   }
   private void double_list()
   {
      if (size % myArray.length == 0 && size != 0)
      {
         Object[] temp = new Object[2*myArray.length];
         for (int i = 0; i < temp.length/2 ; i++)
         {
            temp[i] = myArray[front];
            front = (front + 1) % myArray.length;
         }
         front = 0;
         rear = myArray.length;
         myArray = temp;
      }
      
   }
   public void enqueue(Element e)
   {
      double_list();
      myArray[rear] = e;
      size++;
      rear = (rear + 1) % myArray.length;
   }
   @SuppressWarnings("unchecked")
   public Element dequeue()
   {
      if (size == 0)
      {
         throw new NoSuchElementException(); 
      }
      else
      {
         Object temp = myArray[front];
         myArray[front] = null;
         front = (front + 1) % myArray.length;
         size --; 
         return (Element) temp; 
      }
      
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
         return (Element) myArray[front];   
      }
      
   }
}
   
   

