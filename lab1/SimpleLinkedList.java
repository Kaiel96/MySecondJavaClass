/**
* A Simple linked list in Java constructed for lab 1
*
* @author Brannden Moss
* @version 1/06/2016 Developed for Lab 1   
*/
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.util.*; 
import java.lang.IndexOutOfBoundsException;

public class SimpleLinkedList implements SimpleList
{
   private class Node
   {
      public Node next; 
      public Node prev;
		public Element element;
		public Node(Element element)
		{
		   this.element = element;
		   this.next = null; 
		   this.prev = null;
		}
	}
   private Node head;
   private Node tail; 
   private int size;
   public SimpleLinkedList()
	{
      head = null;
      tail = null; 
      size = 0;
	}
   private Node getNode(int i)
   {
   	Node n = head;
   	int position = 0;
      while (position != i) 
       {
          n = n.next;
          position++;
       }
       return n;
   }
   public void add(Element element)
   {
      Node n = new Node(element);
      int index = 0; 
      if(size == index)
      {
         n.element = element;
	      n.prev = head;
	      head.next = n;
	      size ++;
	      index ++;  	
      }
      else
      {
      	Node n2 = getNode(index - 1); 
      	n.next = tail;
      	tail.prev = n;
      	n.prev = n2;
      	n.element = element; 
      	size ++;
      	index ++;
      }
	}
	public void add(int index, Element element)
	{
		Node n = new Node(element);
		Node temp = head.next;
		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else if (index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
      else
      {
      	int startIndex = 0;
      	while(startIndex < index)
      	{
      		temp = temp.next;
      		startIndex++;
      	}
      	temp.prev.next = n;
      	n.prev = temp.prev;
      	temp.prev = n;
      	n.next = temp;
      }
	}
	public Element get(int index)
	{
		Node n = getNode(index); 
		return n.element; 
	}
	public Element remove(int index)
	{
		Node n = getNode(index-1);
		Node n2 = getNode(index + 1);
		if (index >= size)
		{
			throw new IndexOutOfBoundsException();	
		}
		else if (index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		else if (index == size)
		{
			n.next = n.next.next;
			tail.prev = tail.prev.prev;
			size --;
			return n.element;
		}
		else
		{
			n.next = n.next.next; 
			n2.prev = n2.prev;
			size --;
			return n2.element; 
		}
	}
	public int size()
	{ 
		return size;  
	}
}
