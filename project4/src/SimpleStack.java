/**
 * SimpleStack Interface implementation for lab3
 *
 * @author Brannden Moss
 * @version 1/18/2016 Developed for CPE 103 Lab 3
 */

public interface SimpleStack<E> {
   E peek();
   //Returns the element at the top of the stack but does not remove it.
   E  pop();
  //Removes and returns the element at the top of the stack.
   void push(E element);
   //Adds the specified element to the top of the stack.
   int size();
   //Returns number of elements currently in the stack.

}
