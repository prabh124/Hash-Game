//E is a generic Element, initializing the linked list with <E> allows us to
//add elements to the list without the need to cast them 
public class Node<E> {

	private Node<E> next;
	private E currentElement;
	
	//creates the list
	public Node(){
		
		next = null;
		currentElement = null;
	}
	
	public Node(E elem) {
		next = null;
		currentElement = elem;
	}
	//get the next element
	public Node<E> getNextElement(){
		return next;
	}
	
	//add a new node to the list
	public void setNextElement(Node<E> newNode){
		next = newNode;
	}
	
	//method which returns the currentElement element inside the list
	public E getCurrentElement(){
		return currentElement;
	}
	
	
	
}