/**
 * Generic Queue
 * @author Hongwei Xie
 * @version 1.1 
 * @since Feb 2, 2016
 * @param <T> Type parameter
 */
public class Queue<T> {
	// front pointer
	private ListNode<T> front;
	// rear pointer
	private ListNode<T> rear;
	// size of the queue
	private int size;
       /**
        * no-arg constructor set everything to null or 0
        */
       public Queue(){
    	   front = null;
    	   rear = null;
    	   size = 0;
       }
       /**
        * check if queue is empty
        * @return boolean value
        * pre-condition: queue is instantiated
        * post-condition: return the size of the queue
        */
	   public boolean isEmpty() {
		   return (size == 0);
		}
	   /**
	    * enqueue method that add item to the end
	    * @param item
	    * pre-condition: queue already exits
	    * post-condition: item is added to the end of the queue
	    */
	   public void enqueue(T item){
		   //if is empty, add at end, set front and rear point to same item
		   if(isEmpty()){
			   rear = new ListNode<T>(item);
			   front = rear;
		   }
		   //if not empty, add at rear and move rear backward
		   else{
			   rear.link = new ListNode<T>(item);
			   rear = rear.link;
		   }
		   //size add 1
		   size++;
	   }
	   /**
	    * dequeue method that remove item from the front
	    * @return front item in the queue
	    * pre-condition: queue already exists
	    * post-condition: the front item is removed from the queue
	    */
	   public ListNode<T> dequeue(){
		   //if is empty return
		   if(isEmpty()){
			   return null;
		   }
		   //else if front equals rear, return Node and set front and rear to null size minus 1
		   else if(front == rear){
			   ListNode<T> returnNode = front;
			   front = null;
			   rear = null;
			   size--;
			   return returnNode;
		   }
		   //else return front and move backward front pointer size minus 1
		   else{
			   ListNode<T> returnNode = front;
			   front = front.link;
			   size--;
			   return returnNode;
		   }   
	   }
	  
	   /**
	    * Add element at the front of queue.
        * Best & Worst condition: Theta(1)
	    * @param item Element to be added
	    * Pre-condition: List instance exits
	    * Post-condition: item is added at front, size plus 1
	    */
	   public void addAtFront(T item){
		   //if front is null, set front as this new node and set rear equals front
		   if(front == null){
			   front = new ListNode<T>(item, null);
			   rear = front;
		   }
		   //else set new node's link equals current front and move forward front pointer
		   else{
			   ListNode<T> newNode = new ListNode<T>(item, front);
			   front = newNode;
		   }
		   //size plus 1
		   size++;
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
			   ListNode<T> current = front;
			   for(int i = 0;i < index;i++){
				   current = current.link;
			   }
			   return current.element;
		   }
	   }
	   /**
	    * Remove the last element from the queue.
        * Best & worst condition: Theta(n), n is the number of element in the list
	    * Pre-condition: The list is not empty
	    * Post-condition: Last element removed, size minus 1
	    */
	   public void removeFromEnd(){
		   //if rear is null, return
		   if(rear != null){
			   return;
			   }
		   //else if front equals rear, set front, rear to be null, size minus 1
		   else if(front == rear){
			   front = null;
			   rear = null;
			   size--;
		   }
		   //else traverse the list to the element just before the rear pointer and remove the rear
		   //then set current as new rear, size minus 1
		   else{
			   ListNode<T> current = front;
			   while(current.link != rear){
				   current = current.link;
			   }
			   current.link = null;
			   rear = current;
			   }
		     size--;
		   }	
    /**
     * Get the information about the linked list. 
     * Best and worst condition: Theta(n), n is the number of element in the list
	 * @return A string consisted of elements contained in the list
	 * pre-condition: queue already instantiated
	 * post-condition: the String contains information of the queue returned
     */
	   public java.lang.String toString(){
		   StringBuilder s = new StringBuilder();
		   ListNode<T> current = front;
		   while(current != null){
			   s.append(current.toString());
			   current = current.link;
		   };
		return s.toString();   
	   }
}
/**
 * Generic ListNode<T>, T could be any type, used to storing element and link
 * @author Hongwei Xie
 * @version 1.1 
 * @since Feb 2, 2016
 * @param <T> Type parameter
 */
class ListNode<T> {
	  //Data fields
	  // element stored in the node
	  T element;
	  // link to next node
	  ListNode<T> link;         
	  // Methods
	  /**
	   * no-arg constructor set everything to null
	   */
	  public ListNode() {
		  element = null; 
		  link = null;
		  }
      /**
       * one-arg constructor set element
       * @param element
       */
	  public ListNode(T element) {this.element = element; link = null;}
	  /**
	   * 2-arg constructor set element and link
	   * @param element
	   * @param link
	   */
	  public ListNode(T element, ListNode<T> link) {
		  this.element = element;
		  this.link = link;
	  }
	  /**
	   * toString method
	   * pre-condition: ListNode already instantiated
	   * post-condition: the String contains information of the ListNode returned
	   */
	  public java.lang.String toString(){
		  return element.toString();
	  }
} 