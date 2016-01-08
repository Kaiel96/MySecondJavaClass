import org.w3c.dom.Element;
public interface SimpleQueue
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