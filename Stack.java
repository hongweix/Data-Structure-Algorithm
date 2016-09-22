import java.math.BigInteger;
import java.util.Arrays;;
/**
 * Stack class implemented by array
 * @author Hongwei Xie
 * @version 1.3
 */
public class Stack {
	// top pointer
	private int top;
	// size of the stack
    private int size;
    // An object array which can store String or BigInteger
    Object[] stack;
    /**
     * Constructor method
     * set 2 as original size
     * instantiate the object array
     * set -1 as original top pointer
     * Invariant: top should be at least 1 less than size, top always point to latest element, LIFO
     */
    public Stack(){
    	size = 2;
        stack= new Object[size];
        top=-1;
    }
    /**
     * getSize method
     * @return integer of the size of stack
     * Best case condition: Theta(1)
     * Worst case condition: Theta(1)
     * Invariant: top should be at least 1 less than size, top always point to latest element, LIFO
     * Pre-condition: stack exists
     * Post-condition: size returned
     */
    public int getSize(){
    	return size;
    }
    /**
     * push method
     * @param value the element
     * Best case condition: Theta(1)
     * Worst case condition: Theta(N), N is the current number of elements in the stack
     * Invariant: top should be at least 1 less than size, top always point to latest element, LIFO
     * Pre-condition: stack exists
     * Post-condition: element pushed into the stack
     */
    public void push(Object value){
    	// if top == size-1, means current stack is full, we need to double its size
        if(top==size-1){
        	// double size
            size *= 2;
            // copy old elements in old array to new double sized array
            Object[] temp = new Object[size];
            for(int i = 0;i<size/2;i++){
            	temp[i] = stack[i];
            }
            stack = temp;
            // top plus 1
            top=top+1;
            // element locate at top position
            stack[top] = value; 
        }
        // else 
        else{
        	// top plus 1
            top=top+1;
            // element locate at top position
            stack[top]=value;
        }
    }
    /**
     * pop method
     * @return the top position object
     * Best case condition: Theta(1)
     * Worst case condition: Theta(1)
     * Invariant: top should be at least 1 less than size, top always point to latest element, LIFO
     * Pre-condition: stack exists
     * Post-condition: top element removed from the stack and returned
     */
    public Object pop(){
    	// the return object
        Object returnvalue;
        // if stack is not empty
		if(!isEmpty()){
			// assign top position object to return object
        	returnvalue = stack[top];
        	// top minus 1
            top=top-1;
            return returnvalue;
        }
		// else return null
		else{
            return null;
        }
    }
    /**
     * peek method
     * @return the top position object
     * Best case condition: Theta(1)
     * Worst case condition: Theta(1)
     * Invariant: top should be at least 1 less than size, top always point to latest element, LIFO
     * Pre-condition: stack exists
     * Post-condition: top element returned
     */
    public Object peek(){
    	// if not empty return the top position object
		if(!isEmpty()){
            return stack[top];
        }
		// else return null
		else{
            return null;
        }
    }
    /**
     * method to check if stack is empty
     * @return boolean value
     * Best case condition: Theta(1)
     * Worst case condition: Theta(1)
     * Invariant: top should be at least 1 less than size, top always point to latest element, LIFO
     * Pre-condition: stack exists
     * Post-condition: boolean value returned
     */
    public boolean isEmpty(){
    	// if top == -1 return true, else return false
        return top==-1;
    }
    /**
     * method to display the stack
     * Best case condition: Theta(n), n is number of element
     * Worst case condition: Theta(n), n is the number of element
     * Invariant: top should be at least 1 less than size, top always point to latest element, LIFO
     * Pre-condition: stack exists
     * Post-condition: stack elements displayed
     */
    public void display(){
    	// if not empty
    	if(!isEmpty()){
    		// using for loop traverse all elements and print out
    		for(int i=0;i<=top;i++){
                System.out.print(stack[i]+ " ");
            }
    	}
    	// else print out notice
    	else{
    		System.out.println("Can't display...stack is empty");
    	} 
    }
    /**
     * Main method to test the stack
     * @param args
     */
    public static void main(String[] args){
    	System.out.println("Push String 1, BigInteger 0, BigInteger 10 into the stack and test it.");
    	Stack test = new Stack();
    	System.out.println("Push 1.");
    	test.push("1");
    	System.out.println("Push 0.");
    	test.push(BigInteger.ZERO);
    	System.out.println("Originally the stack size is: " + test.getSize());
    	System.out.println("Push 10.");
    	test.push(BigInteger.TEN);
    	System.out.println("Now the stack size is doubled to: " + test.getSize());
    	System.out.println("Peek the top element: " + test.peek());
    	System.out.println("Pop the top element.");
    	test.pop();
    	System.out.println("Pop the top element again.");
    	test.pop();
    	System.out.println("After 2 pop, the top element should be 1, let's peek it to check.");
    	System.out.println(test.peek());
    	System.out.println("Success!");
    }
}
