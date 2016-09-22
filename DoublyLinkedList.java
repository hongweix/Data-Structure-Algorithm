/**
 * Doubly linked list
 * @author Hongwei Xie
 * @version 1.1 Jan 27, 2016
 */
public class DoublyLinkedList extends DoubleNode{
   private DoubleNode head;
   /**
    * No-arg Constructor, constructing an empty doubly linked list
    */
   public DoublyLinkedList(){
	   head = null;
   }
   /**
    * Add a character at the end of list.
    * Best & Worst condition: Theta(n), n is number of character in the list
    * @param c Character to be attached at the end of list
    * Pre-condition: DoublyLinkedList instance exits
	* Post-condition: Character is added at end
    */
   public void addCharAtEnd(char c){
	   if(head == null){
		   head = new DoubleNode(null, c, null);
	   }
	   else{
		   DoubleNode current = head;
		   while(current.getNext() != null){
			   current = current.getNext();
		   }
		   DoubleNode newNode = new DoubleNode(current, c, null);
		   current.setNext(newNode);
	   }
   }
   /**
    * Add a character at the front of list.
    * Best & Worst condition: Theta(1)
    * @param c Character to be attached at the front of list
    * Pre-condition: DoublyLinkedList instance exits
	* Post-condition: Character is added at front
    */
   public void addCharAtFront(char c){
	   if(head == null){
		   head = new DoubleNode(null, c, null);
	   }
	   else{
		   DoubleNode newNode = new DoubleNode(null, c, head);
		   head.setPrev(newNode);
		   head = newNode;
	   }
   }
   /**
    * Count the DoubleNode in the list.
    * Best & Worst condition: Theta(n), n is the number of character in the list
    * @return The number of DoubleNode
    * Pre-condition: DoublyLinkedList instance exits, no cycle
	* Post-condition: Return count number
    */
   public int countNodes(){
	int count = 0;
	if(head == null){
		return count;
	}
	else{
		DoubleNode current = head;
		while(current != null){
			count++;
			current = current.getNext();
		}
		return count;
	}
   }
   /**
    * Delete the first occurence of a character c in the linked list.
    * Best & Worst condition: Theta(n), n is the number of character in the list
    * @param c Character to be deleted
    * @return True if the character is deleted, false if there is no such a node in the list
    * Pre-condition: DoublyLinkedList instance exits
	* Post-condition: Character is deleted
    */
   public boolean deleteChar(char c){
	 if(head == null){
		 return false;
	 }
	 else{
		DoubleNode current = head;
		while(current.getC()!=c){
			current = current.getNext();
			if(current == null){
				return false;
			}
		}
		if(current.getPrev() == null && current.getNext() == null){
			head = null;
		}
		else if(current.getPrev() == null){
			current.getNext().setPrev(null);
			head = current.getNext();
		}
		else if(current.getNext() == null){
			current.getPrev().setNext(null);
		}
		else{
			current.getNext().setPrev(current.getPrev());
			current.getPrev().setNext(current.getNext());
		}
		return true;
     }	   
   }
   /**
    * Check if the list is empty.
    * Best & worst condition: Theta(1)
	* @return Return true if the list is empty, otherwise false
	* Pre-condition: DoublyLinkedList instance exits
	* Post-condition: Return true or false
    */
   public boolean isEmpty(){
	return head == null;	   
   }
   /**
    * Main method
    * @param a arguments users may enter
    */
   public static void main(java.lang.String[] a){
	   
   }
   /**
    * Remove the last element from the list.
    * Best & worst condition: Theta(n), n is the number of char in the list
	* @return The character deleted. 
	* Pre-condition: The list is not empty
	* Post-condition: Last element removed
    */
   public char removeCharAtEnd(){
	   if(head == null){
		   return 0;
	   }
	   else if(head.getNext() == null){
		   char returnchar = head.getC();
		   head = null;
		   return returnchar;
	   }
	   else{
		   DoubleNode current = head;
		   while(current.getNext() != null){
			   current = current.getNext();
		   }
		   current.getPrev().setNext(null);
		   return current.getC();
	   }
   }
   /**
    * Remove the first element from the list.
    * Best and worst condition: Theta(1)
	* @return The character deleted. 
	* Pre-condition: The list is not empty
	* Post-condition: Last element removed
    */
   public char removeCharFromFront(){
	   if(head == null){
		   return 0;
	   }
	   else if(head.getNext() == null){
		   char returnchar = head.getC();
		   head = null;
		   return returnchar;
	   }
	   else{
		   char returnchar = head.getC();
		   head = head.getNext();
		   head.setPrev(null);
		   return returnchar;
	   }
	   
   }
   /**
    * Reverse the linked list.
    * Best and worst condition: Theta(n), n is the number of char in the list
    * Pre-condition: The list is not cycle
	* Post-condition: List reversed
    */
   public void reverse(){
	   if(head != null){
		   DoubleNode current = head;
		   while(current != null){
			   DoubleNode next = current.getNext();
			   current.setNext(current.getPrev());
			   current.setPrev(next);
			   head = current;
			   current = next;
		   }
	   }
   }
   /**
    * Get the information about the linked list. 
    * Best and worst condition: Theta(n), n is the number of char in the list
	* @return A string consisted of characters contained in the list
    */
   public java.lang.String toString(){
	   StringBuilder s = new StringBuilder();
	   DoubleNode current = head;
	   while(current != null){
		   s.append(current.toString());
		   current = current.getNext();
	   };
	return s.toString();   
   }
}
