import java.util.ArrayList;
import java.util.NoSuchElementException;
public class PriorityQueue <E extends Comparable<? super E>> implements SimpleQueue<E>
{
   private ArrayList <E> al = new ArrayList<E>();
   private boolean minMax; // if true then max queue 
   public PriorityQueue()
   {
      al.add(null);
      minMax = false; //creates a min queue
      
   }
   public PriorityQueue(boolean b)
   {
      al.add(null);
      if (b)
         minMax = true; //creates a max queue
      else
         minMax = false; //creates a min queue
   }
   public PriorityQueue(E[] array, int size)
   {
      al.add(null);
      minMax = false; //creates a minimum queue
      for(int i = 0; i < size; i++)
      {
         enqueue(array[i]);
      }
   }
   public PriorityQueue(E[] array, int size, boolean b)
   {
      al.add(null);
      if (b)
      {
         minMax = true;
      }
      else
      {
         minMax = false;
      }
      for(int i = 0; i < size; i++)
      {
         enqueue(array[i]);
      }
   }
   public static <E extends Comparable<? super E>>  void  sort(E [] array,  int size)
   {
      PriorityQueue <E> pq = new PriorityQueue <E>(array,size,false);
      for (int i = 0; i < size; i++)
      {
         array[i] = pq.dequeue();
      }  
   }
   public static <E extends Comparable<? super E>> E kth(E [] array, int size, int k)
   {
      
      if (k < (size-k + 1))
      {
         PriorityQueue <E> pq = new PriorityQueue <E>(array,k, false);
         for (int i = k; i < size; i++)
         {  
            if (array[i].compareTo(pq.peek()) > 0 )
            {
               pq.dequeue();
               pq.enqueue(array[i]);
            }
         }
         return pq.peek();
      }
      
      else
      {
         PriorityQueue <E> pq = new PriorityQueue <E>(array,size-k+1, true);
         for (int i = (size-k +1); i < size; i++)
         {
            if (array[i].compareTo(pq.peek()) < 0 )
            {
               pq.dequeue();
               pq.enqueue(array[i]);
            }
         }
         return pq.peek();
      }
      
   }
   public E dequeue() 
   {
      if (al.size() <= 1)
      {
         throw new NoSuchElementException();
      }
     
      E top = al.get(1);
      E bottom = al.get(al.size() - 1);
      al.set(1, bottom);
      al.remove(al.size()-1);
      if (al.size() == 1)
      {
         return top; 
      }
      int index = 1; 
      if (minMax)
      {
         while ( (index*2 < al.size() && bottom.compareTo(al.get(index*2)) < 0) ||
               (index*2 + 1 < al.size() && bottom.compareTo(al.get(index*2 +1)) < 0)  ) 
         {
            if (index*2 + 1 >= al.size())
            {
               al.set(index, al.get(index * 2));
               index *= 2;
            }
            
            else if (al.get(index*2).compareTo(al.get(index*2 + 1)) > 0)
            {
               al.set(index, al.get(index*2));
               index *= 2;
            }
            else 
            {
               al.set(index, al.get(index*2 + 1));
               index = index * 2 + 1;
            }
         }
      }
      else
      {
         while ((index*2 < al.size() && bottom.compareTo(al.get(index*2)) > 0) ||
               (index*2 + 1 < al.size() && bottom.compareTo(al.get(index*2 +1)) > 0) ) 
         {
            if (index*2 + 1 >= al.size())
            {
               al.set(index, al.get(index * 2));
               index *= 2;
            }
            
            else if (al.get(index*2).compareTo(al.get(index*2 + 1)) < 0 )
            {
               al.set(index, al.get(index*2));
               index *= 2;
            }
            else
            {
               al.set(index, al.get(index*2 + 1));
               index = index * 2 + 1;
            }
         }
      }
      al.set(index, bottom);
      return top;
   }
   public void enqueue(E element)
   {
     
      int index = al.size();
      al.add(element);
      E temp = al.get(al.size()-1);
      if (minMax)
      {
         while (al.get(index/2) != null && 
               temp.compareTo(al.get(index/2)) > 0 )
         {
            al.set(index, al.get(index/2));
            index /= 2;
         }
      }
      else
      {
         while (al.get(index/2) != null  &&
               temp.compareTo(al.get(index/2)) < 0)
         {
            al.set(index, al.get(index/2));
            index /= 2;
         } 
      }
      al.set(index, temp);
   }
   public E peek()
   {
      if (al.size() <= 1)
      {
         throw new NoSuchElementException();
      }
      return al.get(1);
   }
   public int size()
   {
      if (al.size() == 0)
      {
         return 0; 
      }
      return al.size() - 1;
   }
}
