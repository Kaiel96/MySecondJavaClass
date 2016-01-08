import org.w3c.dom.Element;
import java.util.NoSuchElementException;
public class SimpleLinkedQueue implements SimpleQueue
{
   private class Queue
   {
     	public Queue next; 
    	public Queue prev;
		public Element element;
		public Queue(Element element)
		{
		   this.element = element;
		   this.next = null; 
		   this.prev = null;
		}
	}
   private Queue front;
   private Queue rear; 
   private int size;
	public SimpleLinkedQueue()
	{
      front = null;
      front.prev = rear;
      rear= null; 
      rear.next = front;
      size = 0;
	}
   private Queue getQueue(int i)
   {
   	Queue n = front;
   	int position = 0;
   	while (position != i) 
      {
         n = n.next;
         position++;
      }
      return n;
   }
   public void enqueue(Element element)
	{
      Queue q = new Queue(element);
      int index = 0; 
  
      if(size == 0)
      {
         q.element = element;
	      q.prev = front;
	      front.next = q;
	      size ++;
	      index ++;  	
      }
      else
      {
      	Queue q2 = getQueue(index - 1); 
      	q.next = rear;
      	rear.prev = q;
      	q.prev = q2;
      	q.element = element; 
      	size ++;
      	index ++;
      }
	}
	public Element peek()
	{
		Queue n = getQueue(1); 
		return n.element; 
	}
	public Element dequeue()
	{
      if (getQueue(1) == null)
      {
         throw new NoSuchElementException();
      }
      else
      {
         Queue n = getQueue(1);
         Queue n2 = getQueue(2);
         front.next = n2;
         n2.prev = front;
         size --;
         return n.element;
      } 
    
	}
	public int size()
	{ 
		return size;  
	}
}