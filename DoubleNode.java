/**
 * DoubleNode used to storing a character and two pointers
 * @author Hongwei Xie
 * @version 1.1 Jan 27, 2016
 *
 */
public class DoubleNode {
	/**
	 * Each DoubleNode stores a character and two pointers: previous and next
	 */
	private DoubleNode previous;
	private DoubleNode next;
	private char character;
    /**
     * No-arg constructor: set everything to null
     */
	public DoubleNode(){
	   previous = null;
	   next = null;
	   character = 0;
    }
	/**
	 * 3-arg constructor, set with value 
	 * @param p Previous DoubleNode
	 * @param ch Character in this DoubleNode
	 * @param n Next DoubleNode
	 */
	public DoubleNode(DoubleNode p, char ch, DoubleNode n){
		previous = p;
		character = ch;
		next = n;
	}
	/**
	 * Get character stored in this DoubleNode.
	 * Best & Worst condition: Theta(1)
	 * @return Character stored in this DoubleNode
	 * Pre-condition: DoubleNode exists
	 * Post-condition: Return character
	 */
	public char getC(){
		return character;	
	}
	/**
	 * Get next DoubleNode.
	 * Best & Worst condition: Theta(1)
	 * @return Next DoubleNode
	 * Pre-condition: DoubleNode exits
	 * Post-condition: Return Next DoubleNode
	 */
	public DoubleNode getNext(){
		return next;	
	}
	/**
	 * Get previous DoubleNode.
	 * Best & Worst condition: Theta(1)
	 * @return Previous DoubleNode
	 * Pre-condition: DoubleNode exits
	 * Post-condition: Return Previous DoubleNode
	 */
	public DoubleNode getPrev(){
		return previous;	
	}
	/**
	 * Test Driver
	 * @param args Arguments users may enter
	 */
	public static void main(java.lang.String[] args){
		DoublyLinkedList list = new DoublyLinkedList();
		list.addCharAtEnd('H');
		list.addCharAtEnd('e');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('o');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		System.out.println(list);
		System.out.println("Deleting H");
		list.deleteChar('H');
		System.out.println(list);
		System.out.println("Deleting o");
		list.deleteChar('o');
		System.out.println(list);
		System.out.println("Deleting e");
		list.deleteChar('e');
		System.out.println(list);
		System.out.println("Deleting l");
		list.deleteChar('l');
		System.out.println(list);
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		System.out.println(list);
		System.out.println(list.countNodes());
		System.out.println("Popping everything");
		while(!list.isEmpty()){
			System.out.println(list.removeCharFromFront());
		}
		list.addCharAtFront('o');
		list.addCharAtFront('l');
		list.addCharAtFront('l');
		list.addCharAtFront('e');
		list.addCharAtFront('H');
		System.out.println("Popping everything from the end");
		while(!list.isEmpty()){
			System.out.println(list.removeCharAtEnd());
		}
		System.out.println(list.countNodes());
		list.addCharAtEnd('o');
		list.addCharAtEnd('l');
		list.addCharAtEnd('l');
		list.addCharAtEnd('e');
		list.addCharAtEnd('H');
		list.reverse();
		System.out.println(list);
		list.reverse();
		System.out.println(list);

	}
	/**
	 * Set character of this DoubleNode.
	 * Best & worst condition: Theta(1)
	 * @param c Character of this DoubleNode
	 * Pre-condition: DoubleNode exits
	 * Post-condition: Character is set
	 */
	public void setC(char c){
		character = c;
	}
	/**
	 * Set next DoubleNode.
	 * Best & worst condition: Theta(1)
	 * @param next Next DoubleNode
	 * Pre-condition: DoubleNode exits
	 * Post-condition: Next DoubleNode is set
	 */
	public void setNext(DoubleNode next){
		this.next = next;
	}
	/**
	 * Set previous DoubleNode.
	 * Best & worst condition: Theta(1)
	 * @param prev Previous DoubleNode
	 * Pre-condition: DoubleNode exits
	 * Post-condition: Previous DoubleNode is set
	 */
	public void setPrev(DoubleNode prev){
		this.previous = prev;
	}
	/**
	 * Return information of this DoubleNode.
	 * Best & worst condition: Theta(1)
	 * @return Character in this DoubleNode
	 */
	public java.lang.String toString(){
		return String.valueOf(character);	
	}
}
