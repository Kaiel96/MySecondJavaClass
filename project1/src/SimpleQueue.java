/**
* A Simple Queue Interface  in Java constructed for Project 1
*
* @author Brannden Moss
* @version 1/11/2016 Developed for Project 1  
*/
import org.w3c.dom.Element;
public interface SimpleQueue<Element>
{
   Element dequeue();
   //Removes and returns the element at the front of the queue.
   void enqueue(Element element);
   //Adds the specified element to the back of the queue.
   Element peek();
  // Returns the element at the front of the queue but does not remove it.
   int  size();
   //Returns number of elements currently in the queue.
}