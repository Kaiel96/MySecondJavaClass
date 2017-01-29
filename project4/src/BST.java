/**
 * Provided BST class skeleton for students to complete. This class makes
 * use of Object Oriented structural recursion to solve the problem.
 *
 * Original Revision:
 * @author Paul Hatalsky
 * @version Lab 7
 *
 * Completed By: Brannden Moss   
 * @author Brannden Moss
 */
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BST <E extends Comparable<? super E>> implements Iterable<E>
{
   // Instance variables for BST class.
   // These are the only ones allowed!
   private BSTNode<E> root = new EmptyNode();
   private int size;

   // Polymorphic BST node type!
   private interface BSTNode<E>
   {
      public BSTNode<E> insert(E element);
      public boolean contains(E element);
      public E minimum(E minimum);
      public E maximum(E maximum);
      public void toSortedList(List<E> list);
      public BSTNode<E> remove(E element);
      public int treeHeight(int count);
      public long internalPathLength(int depth);
      public boolean isEmpty();
      public E get(E e);
   }

   ////////////////////////////////////////////////////////////////////////////
   // BST class methods...
   //
   
   /**
    * Inserts an element into the BST.  If the element is already in the BST,
    * the element being inserted is discarded.
    *
    * @param element The element to insert into the BST.
    *
    * @throws IllegalArgumentException If the element is null.
    */
   public void insert(E element)
   {
      if (element == null)
      {
         throw new IllegalArgumentException();
      }

      root = root.insert(element);
   }
   public void remove (E element)
   {  
      root = root.remove(element);
   }
   public int treeHeight()
   {
      return root.treeHeight(-1);
   }
   public long internalPathLength()
   {
      if (size() == 0)
      {
         return -1;
      }
      return root.internalPathLength(0);
      
   }
   /**
    * Determines whether or not an element is in the BST.
    *
    * @param element The element to search for in the BST.
    *
    * @return True if the element is in the BST, false if not.
    */
   public boolean contains(E element)
   {
      if (element == null)
      {
         return false;
      }
      //System.out.println("THIS IS THE ELEMENT --------------------" + element);
      return root.contains(element);
   }
   /**
    * Finds the minimum element in the BST.
    *
    * @return The minimum element in the BST.
    *     
    * @throws NoSuchElementException If the BST is empty.
    */
   public E minimum()
   {
      if (size == 0)
      {
         throw new NoSuchElementException();
      }

      return root.minimum(((Node)root).element);
   }
   /**
    * Finds the maximum element in the BST.
    *
    * @return The maximum element in the BST.
    *
    * @throws NoSuchElementException If the BST is empty.
    */
   public E maximum()
   {
      if (size == 0)
      {
         throw new NoSuchElementException();
      }

      return root.maximum(((Node)root).element);
   }
   /**
    * Takes the elements in the BST and places them in ascending 
    * order into the BST.
    *
    * @param element The element to search for in the BST.
    */
   public void toSortedList(List<E> list)
   {
      root.toSortedList(list);
   }

   public int size()
   {
      return size;
   }
   public Iterator<E> iterator() 
   {
      return new BSTIterator();
   }
   
   public E get(E e)
   {
      return root.get(e);
   }

   ////////////////////////////////////////////////////////////////////////////
   // private EmptyNode class...
   //
   private class EmptyNode implements BSTNode<E>
   {
      // No instance variables needed or allowed!

      public BSTNode<E> insert(E element)
      {
         Node n = new Node();
         n.element = element;
         n.left = new EmptyNode();
         n.right = new EmptyNode();
         size ++;
         return n; 
      }
      public boolean isEmpty()
      {
         return true;
      }
      public boolean contains(E element)
      {
         return false;
      }
      
      public E minimum(E element)
      {
         return element;
      }

      public E maximum(E element)
      {
         return element;
      }
      public BSTNode<E> remove (E element)
      {
         throw new NoSuchElementException(); 
      }
      public int treeHeight(int count)
      {
         return count; 
      }
      public long internalPathLength(int depth)
      {
         return 0; 
      }
      public void toSortedList(List<E> list)
      {
         return;
      }
      public E get(E e)
      {
         throw new NoSuchElementException();
      }
   }

   ////////////////////////////////////////////////////////////////////////////
   // private Node class...
   //
   private class Node implements BSTNode<E>
   {
      // These are the only instance variables needed
      // and the only ones allowed!
      E element;
      BSTNode<E> left, right;

      public BSTNode<E> insert(E element)
      {
         if (element.compareTo(this.element) == 0)
            return this;
         
         else if (element.compareTo(this.element) > 0)
         {
           this.right = this.right.insert(element);
           return this;
         }
         else if (element.compareTo(this.element) < 0)
         {
            this.left = this.left.insert(element);
            return this; 
         }
         else 
         {
            return this;
         }
         
      }

      public boolean contains(E element)
      {
         if (element.compareTo(this.element) > 0)
         {
            return right.contains(element);
         }
         else if (element.compareTo(this.element) < 0)
         {
            return left.contains(element);
         }
         else if (element.compareTo(this.element) == 0)
         {
            return true;
         }
         return false;
      }
      public BSTNode<E> remove (E element)
      {
         if (element.compareTo(this.element) > 0)
         {
            right = right.remove(element);
         }
         else if (element.compareTo(this.element) < 0)
         {
            left = left.remove(element);
         }
         else 
         {
            if (left.isEmpty() && right.isEmpty()) 
            {
               
               size --;
               return new EmptyNode();
               
            }
            else if (left.isEmpty())
            {
               size --;
               return this.right;
            }
            else if (right.isEmpty())
            {
               size --;
               return this.left;
            }
            else if (! right.isEmpty() && ! left.isEmpty())
            {
               
               this.element = right.minimum(element);
               right = right.remove(this.element);
               return this; 
            }
         }
         return this; 
      }
      public int treeHeight(int count)
      {
         int l = left.treeHeight(count+1);
         int r = right.treeHeight(count+1); 
         return Math.max(r, l);       
      }
      public boolean isEmpty()
      {
         return false;
      }
      public long internalPathLength(int depth)
      {
         if (size() == 1)
         {
            return 0;
         }
         return depth + this.right.internalPathLength(depth+1) + this.left.internalPathLength(depth+1);
      }
      public E minimum(E element)
      {
         return left.minimum(this.element);
      }
      public E maximum(E element)
      {
         return right.maximum(this.element);
      } 
      public void toSortedList(List<E> list)
      {
         left.toSortedList(list);
         list.add(this.element);
         right.toSortedList(list);
      }
      
      public E get(E e) 
      {
         if (e.compareTo(this.element) > 0)
            return this.right.get(e);
         else if (e.compareTo(this.element) < 0)
            return this.left.get(e);
         else
            return this.element;
      }
   }
   
   private class BSTIterator implements Iterator<E>
   {
      SimpleArrayStack <Node> sas = new  SimpleArrayStack<Node> ();
      public BSTIterator()
      {
         BSTNode<E> temp = root;
         while (!(temp instanceof BST.EmptyNode ) )
         {
            sas.push(((Node)temp));
            temp = ((Node) temp).left;
         } 
      } 
      
      public E next()
      {
         if (sas.size() == 0)
         {
            throw new NoSuchElementException();
         }
         
         Node value = sas.pop();
         
         if ( ! (value.right instanceof BST.EmptyNode) )
         {
            BSTNode<E> temp = value.right;
            while (!(temp instanceof BST.EmptyNode ) )
            {
               sas.push((Node) temp);
               temp = ((Node) temp).left;
            }
         }
         
         return value.element;
      }
      public boolean hasNext()
      {
         return (sas.size() != 0);
      }
      
      public void remove()
      {
         throw new UnsupportedOperationException();
      }
      
   }

   
}

