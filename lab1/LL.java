public class LinkedList<Element>
{
   private int size;
   private Node first;
   
   /**
    * Constructor for objects of class LinkedList
    */
   public LinkedList()
   {
      size = 0;
      first = null;
   }
   
   public Element get(int index)
   {
       Node p = getNode(index);
       return p.element;
   }
   
   public void remove(int index)
   {
       if (index == 0)
          first = first.next;
       else {
           Node p = getNode(index-1);
           p.next = p.next.next;
       }
       size--;
   }
   
   public void add(int index, Element element)
   {
       Node newNode = new Node(element);
       if (index == 0) {
           newNode.next = first;
           first = newNode;
       }
       else {
           Node p = getNode(index-1);
           newNode.next = p.next;
           p.next = newNode;
       }
       size++;
   }
      
   // Auxillary method (to help with other methods)
   private Node getNode(int i)
   {
       Node p = first;
       int pos = 0;
       while (pos != i) 
       {
          p = p.next;
          pos++;
       }
       return p;
   }

   /**
    * Node class - local to LinkedList
    */
   private class Node 
   {
      public Element element;
      public Node next;
      
      public Node(Element element)
      {
         this.element = element;
         this.next = null;
      }
   }
}
