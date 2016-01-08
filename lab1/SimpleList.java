/**
* A Simple list Java Interface constructed for lab 1
*
* @author Brannden Moss
* @version 1/06/2016 Developed for Lab 1  
*/
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface SimpleList{
	public void add(Element element);
	//Appends the specified element to the end of this list.
	
	public void add(int index, Element element);
	//Inserts the specified index at the specified position 
	//in the list by shifting any existing and subsequent elements
	//to the right by one (numerically greater).
	
	public Element get(int index);
	//Returns the element at the specified position in this list.
	
	public Element remove(int index);
	//Removes the specified element from this list and shifts any 
	//subsequent elements to the left by one (numerically lower).
	
	public int size();
	//Returns the number of elements in this list
	//(elements that have been added by the creator/user of the list).
}
