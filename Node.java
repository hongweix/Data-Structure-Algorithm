/**
 * Generic Node<T>, T could be any type, used to storing element and link
 * @author Hongwei Xie
 * @version 1.1 Jan 27, 2016
 * @param <T> Type parameter
 */
public class Node<T>{
	private T element;
	private Node<T> link;
	/**
	 * 2-arg constructor, pass initial element and link to Node
	 * @param initialElement Element need to be passed
	 * @param initialLink Link need to be passed
	 */
	public Node(T initialElement, Node<T> initialLink){
		element = initialElement;
		link = initialLink;
	}
	/**
	 * Get element in this Node.
	 * Best & worst condition: Theta(1)
	 * @return Return element
	 * Pre-condtion: Node exists
	 * Post-condition: Return element
	 */
	public T getElement(){
		return element;
	}
	/**
	 * Get link in this Node.
	 * Best & worst condition: Theta(1)
	 * @return Return link
	 * Pre-condtion: Node exists
	 * Post-condition: Return link
	 */
	public Node<T> getLink(){
		return link;
	}
	/**
	 * Set element in this Node.
	 * Best & worst condition: Theta(1)
	 * @param newElement Element needs to be set
	 * Pre-condtion: Node exists
	 * Post-condition: Element is set
	 */
	public void setData(T newElement){
		element = newElement;
	}
	/**
	 * Set link in this Node.
	 * Best & worst condition: Theta(1)
	 * @param newLink Link needs to be set
	 * Pre-condtion: Node exists
	 * Post-condition: Link is set
	 */
	public void setLink(Node<T> newLink){
		link = newLink;
	}
	/**
	 * Get some information of this node.
	 * Best and worst condition: Theta(1)
	 * @return the information in this node
	 */
	public java.lang.String toString(){
		return element.toString();
	}
}