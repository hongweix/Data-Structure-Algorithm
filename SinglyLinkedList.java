/**
 * Generic Singly linked list
 * @author Hongwei Xie
 * @version 1.1 Jan 27, 2016
 * @param <T> Type parameter
 */
public class SinglyLinkedList<T> {
	   private Node<T> head;
	   private int size;
	   /**
	    * No-arg constructor, set everything to null
	    */
	   public SinglyLinkedList (){
		   head = null;
		   size = 0;
	   }
	   /**
	    * Add element at the end of list.
        * Best & Worst condition: Theta(n), n is the number of element in the list
	    * @param element Element to be added
	    * Pre-condition: List instance exits
	    * Post-condition: Element is added at end, size plus 1
	    */
	   public void addAtEnd(T element){
		   if(head == null){
			   head = new Node<T>(element, null);
		   }
		   else{
			   Node<T> current = head;
			   while(current.getLink() != null){
				   current = current.getLink();
			   }
			   Node<T> newNode = new Node<T>(element, null);
			   current.setLink(newNode);
		   }
		   size++;
	   }
	   /**
	    * Add element at the front of list.
        * Best & Worst condition: Theta(1)
	    * @param element Element to be added
	    * Pre-condition: List instance exits
	    * Post-condition: Element is added at front, size plus 1
	    */
	   public void addAtFront(T element){
		   if(head == null){
			   head = new Node<T>(element, null);
		   }
		   else{
			   Node<T> newNode = new Node<T>(element, head);
			   head = newNode;
		   }
		   size++;
	   }
	   /**
	    * Check if the list is empty.
	    * Best & worst condition: Theta(1)
		* @return Return true if the list is empty, otherwise false
		* Pre-condition: List instance exits
		* Post-condition: Return true or false
	    */
	   public boolean isEmpty(){
		return head == null;	   
	   }
	   /**
	    * Get the list length.
	    * Best & worst condition: Theta(1)
		* @return Return size
		* Pre-condition: List instance exits
		* Post-condition: Return integer of list size
	    */
	   public int getLength(){
		   return size;
	   }
	   /**
	    * Get element in specific index
	    * Best & worst condition: Theta(n), n is the number of index
	    * @param index Index of the list
	    * @return Element in the list in the specific index
	    * Pre-condition: List exits, no cycle
	    * Post-condition: Element returned
	    */
	   public T get(int index){
		   if(index < 0 || index > size){
			   return null;
		   }
		   else{
			   Node<T> current = head;
			   for(int i = 0;i < index;i++){
				   current = current.getLink();
			   }
			   return current.getElement();
		   }
	   }
	   /**
	    * Remove the last element from the list.
        * Best & worst condition: Theta(n), n is the number of element in the list
	    * Pre-condition: The list is not empty
	    * Post-condition: Last element removed, size minus 1
	    */
	   public void removeAtEnd(){
		   if(head != null){
			   if(head.getLink() == null){
				   head = null;
			   }
			   else{
				  Node<T> current = head;
				  while(current.getLink().getLink() != null){
					  current = current.getLink();
				  }
				  current.setLink(null);
			   }
		     size--;
		   }
	   }
	   /**
	    * Remove the first element from the list.
        * Best & worst condition: Theta(1). 
	    * Pre-condition: The list is not empty
	    * Post-condition: First element removed, size minus 1
	    */
	   public void removeFromFront(){
		   if(head != null){
			   if(head.getLink() == null){
				   head = null;
			   }
			   else{
				   head = head.getLink();
			   }
			   size--;
		   }
	   }
       /**
        * Get the information about the linked list. 
        * Best and worst condition: Theta(n), n is the number of element in the list
	    * @return A string consisted of elements contained in the list
        */
	   public java.lang.String toString(){
		   StringBuilder s = new StringBuilder();
		   Node<T> current = head;
		   while(current != null){
			   s.append(current.toString());
			   current = current.getLink();
		   };
		return s.toString();   
	   }
}

