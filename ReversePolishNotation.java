import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
/**
 * ReversePolishNotation class
 * @author Hongwei Xie
 * @version 1.3
 */
public class ReversePolishNotation {
   public static void main(String[] args) {
	   // buffered reader to read from input
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   // new line to store current input line
       String newline;
       // instantiate a red black tree
       RedBlackTree tree = new RedBlackTree();
       try {
    	// while newline is not empty
		while((newline = br.readLine()).length() != 0){
			   // split the input line
			   String[] record = newline.split(" ");
			   // instantiate a new stack
			   Stack input = new Stack();
			   // BigInteger to store result
			   BigInteger result = null;
			       // analyze the input line's each part
				   for(int i = 0;i < record.length;i++){
					   // if read "=" sigh
					   if(record[i].equals("=")){
						   // pop element as value
						   BigInteger value = new BigInteger(input.pop().toString());  
						   // pop element as key
						   Object key = input.pop();
						   try{
							   // if key is string
							   if(key instanceof String){
								   // if tree does not contain this key
								   if(!tree.contains(key.toString())){
									   // then insert this key, value in the tree
									   tree.insert(key.toString(), value);
									   // push this key in the stack
									   input.push(key.toString());
								   }
								   // else tree already contains this key
								   else{
									   // find this key, value pair and reset its value
									   tree.lookup(key.toString()).setData(value);
									   // push this key in the stack
									   input.push(key.toString());
								   }
								   // result equals to value
								   result = value;
							   }
							   // if key is not string, this is an exception
							   else{
								   // throw the exception
								   Exception e = new Exception(); 
								   throw e;
							   }
						   } catch(Exception e){ 
							// catch the exception and print out information
							System.out.println("Exception in thread \"main\" java.lang.Exception: error: " + key  + " not an lvalue");
							// halt the program
							System.exit(1);
						   }
					   }
					   // if read "+" sign
					   else if(record[i].equals("+")){
						   BigInteger num1 = null, num2 = null;
						   // pop element
						   Object temperary = input.pop();
						   // if element is BigInteger just assign to num1
						   if(temperary instanceof BigInteger){
							   num1 = (BigInteger)temperary;
						   }
						   // else the element is string
						   else{
							   // try look up this string in tree
							   try{
								   num1 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								   // if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								   // halt the program
								   System.exit(1);
							   }
						   }
						   // pop element
						   temperary = input.pop();
						   // if element is BigInteger just assign to num2
						   if(temperary instanceof BigInteger){
							   num2 = (BigInteger)temperary;
						   }
						   // else the element is string
						   else{
							   // try look up this string in tree
							   try{
							   num2 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								   // if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								   // halt the program
								   System.exit(1); 
							   }
						   }
						   // do "+" calculation
						   result = num2.add(num1);
						   // push result in stack
						   input.push(result);
					   }
					   // if read "-" sign
					   else if(record[i].equals("-")){
						   BigInteger num1 = null, num2 = null;
						   // pop element
						   Object temperary = input.pop();
						   // if element is BigInteger just assign to num1
						   if(temperary instanceof BigInteger){
							   num1 = (BigInteger)temperary;
						   }
						   // else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num1 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								   // if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								   // halt the program
								   System.exit(1);
							   }
						   }
						   // pop element
						   temperary = input.pop();
						   // if element is BigInteger just assign to num2
						   if(temperary instanceof BigInteger){
							   num2 = (BigInteger)temperary;
						   }
						   // else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num2 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								// if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								// halt the program
								   System.exit(1); 
							   }
						   }
						// do "-" calculation
						   result = num2.subtract(num1);
						// push result in stack
						   input.push(result);
					   }
					   // if read "*"
					   else if(record[i].equals("*")){
						   // pop element
						   Object temperary = input.pop();
						   BigInteger num1 = null, num2 = null;
						// if element is BigInteger just assign to num1
						   if(temperary instanceof BigInteger){
							   num1 = (BigInteger)temperary;
						   }
						// else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num1 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								// if not found, print error message
								  System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								  // halt the program
								  System.exit(1);  
							   }
						   }
						// pop element
						   temperary = input.pop();
						// if element is BigInteger just assign to num2
						   if(temperary instanceof BigInteger){
							   num2 = (BigInteger)temperary;
						   }
						// else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num2 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								// if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								// halt the program
								   System.exit(1); 
							   }
						   }
						// do "*" calculation
						   result = num2.multiply(num1);
						   // putsh result in stack
						   input.push(result);
					   }
					   // if read "/" sigh
					   else if(record[i].equals("/")){
						   // pop the element
						   Object temperary = input.pop();
						   BigInteger num1 = null, num2 = null;
						// if element is BigInteger just assign to num1
						   if(temperary instanceof BigInteger){
							   num1 = (BigInteger)temperary;
						   }
						// else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num1 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								// if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								// halt the program 
								   System.exit(1); 
							   }
						   }
						// pop the element
						   temperary = input.pop();
						// if element is BigInteger just assign to num2
						   if(temperary instanceof BigInteger){
							   num2 = (BigInteger)temperary;
						   }
						// else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num2 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								// if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								// halt the program
								   System.exit(1);  
							   }
						   }
						// do "/" calculation
						   result = num2.divide(num1);
						   // push result in stack
						   input.push(result);
					   }
					   // if read "%" sign
					   else if(record[i].equals("%")){
						   // pop the element
						   Object temperary = input.pop();
						   BigInteger num1 = null, num2 = null;
						// if element is BigInteger just assign to num1
						   if(temperary instanceof BigInteger){
							   num1 = (BigInteger)temperary;
						   }
						// else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num1 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								// if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								// halt the program
								   System.exit(1); 
							   }
						   }
						// pop the element
						   temperary = input.pop();
						// if element is BigInteger just assign to num2
						   if(temperary instanceof BigInteger){
							   num2 = (BigInteger)temperary;
						   }
						// else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num2 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								// if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								// halt the program
								   System.exit(1); 
							   }
						   }
						// do "%" calculation
						   result = num2.mod(num1);
						// push result in stack
						   input.push(result);
					   }
					   // if read "~" sign
					   else if(record[i].equals("~")){
						// pop the element
						   Object temperary = input.pop();
						   BigInteger num1 = null;
						// if element is BigInteger just assign to num1
						   if(temperary instanceof BigInteger){
							   num1 = (BigInteger)temperary;
							   // do "~" calculation
							   result = num1.negate();
							   // push result in stack
							   input.push(result);
						   }
						// else the element is string
						   else{
							// try look up this string in tree
							   try{
							   num1 = tree.lookup(temperary.toString()).getData();
							   } catch(Exception e){
								// if not found, print error message
								   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + temperary);
								// halt the program 
								   System.exit(1);
							   }
							// do "~" calculation
							   result = num1.negate();
							// push result in stack
							   input.push(temperary.toString());
							   // look up this key again and reset the value
							   tree.lookup(temperary.toString()).setData(result);
						   }  
					   }
					   // if read "#" sign
					   else if(record[i].equals("#")){
						   try{
							// pop the element
							   Object temperary = input.pop();
							   BigInteger num1 = null, num2 = null, num3 = null;
							// if element is BigInteger just assign to num1
							   if(temperary instanceof BigInteger){
								   num1 = (BigInteger)temperary;
							   }
							// else the element is string
							   else{
								   // look up this string in tree
								   num1 = tree.lookup(temperary.toString()).getData();
							   }
							// pop the element
							   temperary = input.pop();
							// if element is BigInteger just assign to num2
							   if(temperary instanceof BigInteger){
								   num2 = (BigInteger)temperary;
							   }
							// else the element is string
							   else{
								// look up this string in tree
								   num2 = tree.lookup(temperary.toString()).getData();
							   }
							// pop the element
							   temperary = input.pop();
							// if element is BigInteger just assign to num3
							   if(temperary instanceof BigInteger){
								   num3 = (BigInteger)temperary;
							   }
							// else the element is string
							   else{
								// look up this string in tree
								   num3 = tree.lookup(temperary.toString()).getData();
							   }
							   // do "#" calculation
							   result = num3.modPow(num2, num1);
							   // put result in stack
							   input.push(result);
						   } catch(Exception e){
							   // if stack do not has 3 element, out print error message
							   System.out.println("Exception in thread \"main\" java.lang.Exception: error: stack underflow exception");
							   // halt the program
							   System.exit(1);
						   }
					   }
					   // if read is number or variable
					   else{
						   // check if it is number or variabe
						   boolean isvariable = false;
						   for(int j = 0;j < record[i].length();j++){
							   if(Character.isDigit(record[i].charAt(j))){
								   continue;
							   }
							   else{
								   isvariable = true;
								   break;
							   }
						   }
						   // if is variable, put it in stack as string
						   if(isvariable){
							   input.push(record[i]);
						   }
						   // else is number, put it in stack as BigInteger
						   else{
							   input.push(new BigInteger(record[i]));
						   }
					   } 
				   }
				   // if record length equals 1, means want to search the variable value
			   	   if(record.length == 1){
			   		   try{
			   			// look up this string in tree
			   		   result = tree.lookup(record[0]).getData();
			   		   } catch(Exception e){
			   			// if not found, print error message
			   			   System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + record[0]);
						   // halt the program
			   			   System.exit(1);
			   		   }
			   	   }
			   System.out.println(result); 
			   }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	};
	   // customer only hit enter without any other things, then out print "terminating"
	   System.out.println("terminating");
   }
}

