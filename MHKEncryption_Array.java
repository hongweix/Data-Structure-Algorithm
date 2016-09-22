/**
 * MHKEncryption_Array
 * @author Hongwei Xie
 * @version 1.2 Jan 26, 2016
 */
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class MHKEncryption_Array {
	private BigInteger q;
	private BigInteger r;
	private BigInteger cypherText;
	private BigInteger wsum;
	private BigInteger[] w = new BigInteger[640];
	private BigInteger[] b = new BigInteger[640];
	/**
	 * No-arg constructor: Instantiate MHKEncryption object and generate w used in private key
	 * Best & worst condition: Theta(1), because list size is constant
	 * Pre-condition: SinglyLinkedList w is instantiated
	 * Post-condition: w is filled with 640 superincreasing BigInteger
	 *                 Get wsum, the sum of this superincreasing sequence
	 */
	public MHKEncryption_Array(){
			BigInteger wgenerator = new BigInteger("2");
			wsum = BigInteger.ZERO;
			for(int i = 0;i < 640;i++){
				wgenerator = wgenerator.multiply(new BigInteger("2")).subtract(BigInteger.ONE); 
				w[i] = wgenerator;
				wsum = wsum.add(wgenerator);
			}
	}
	/**
	 * Generate q which is larger than sum of w
	 * Best & worst condition: Theta(1)
	 * Pre-condition: wsum exists as BigInteger
	 * Post-condition: q is generated
	 */
	public void generateQ(){
			q = wsum.add(BigInteger.ONE);
	}
	/**
	 * Generate r which is coprime with q and locate in [1, q)
	 * Best condition: Theta(1), worst condition: Theta(2expo(n))
	 * Pre-condition: q exists as BigInteger
	 * Post-condition: r is generated
	 */
	public void generateR(){
			do{
				r = new BigInteger(80, new Random());
			}while(!(q.gcd(r).equals(BigInteger.ONE)&&r.compareTo(q)<0&&r.compareTo(BigInteger.ONE)>=0));
	}
	/**
	 * Generate b used as public key
	 * Best & worst condition: Theta(1), because list size is constant
	 * Pre-condition: w, r, q exist
	 * Post-condition: b is generated
	 */
	public void generateB(){
			for(int i = 0;i < w.length;i++){
				b[i] = w[i].multiply(r).mod(q);
			}
	}
	/**
	 * Encrypt the clear text
	 * Best & worst condition: Theta(n), n is the lenght of clear text
	 * @param text String passed to the method
	 * @return cypher text in BigInteger format
	 * Pre-condition: b exists as BigInteger
	 * Post-condition: cypher text is generated and returned
	 */
    public BigInteger Encryption(String text){
    	cypherText = BigInteger.valueOf(0);
    	int bposition = 0;
    	/*
    	 * Cursor move from 128 to 1 each time. Do & operation between chvalue
    	 * and cursor, if not 0, means in bit format this position is 1, add
    	 * the corresponding BigInteger in the b list to cypherText.
    	 */
    	for(byte ch: text.getBytes()){
    		int cursor = 128;
    		int chvalue = ch;
    		for(int i = 0;i < 8;i++){
    			if((chvalue & cursor) != 0){
    				cypherText = cypherText.add(b[bposition]);
    			}
    			cursor >>= 1;
    		    bposition ++;
    		}
    	}
    	return cypherText;
    }
    /**
     * Decrypt the cypher text
	 * Best & worst condition: Theta(1), we need to traverse w list, size is constant
     * @param text Cypher text passed to the method
     * @return clear text in string format
     * Pre-condition: w exists
	 * Post-condition: clear text is generated and returned
     */
    public String Decryption(BigInteger text){
    	StringBuilder str = new StringBuilder();
        BigInteger decryptor = text.multiply(r.modInverse(q)).mod(q);
        int ch = 0;
        int cursor2 = 1;
        /*
         * Traverse w list, if element is small or equal to decryptor, then subtract
         * decryptor with the element. If cursor2 arrive to 128, it means we already
         * traverse 8 bits, convert the 8 bits to char and append to str, finally
         * reverse the str
         */
        for(int i = w.length;i > 0;i--){
    		if(w[i-1].compareTo(decryptor) < 1){
    			decryptor = decryptor.subtract(w[i-1]);
    			ch += cursor2;
    		}
    		if(cursor2 == 128){
    			str.append((char) ch);
    			cursor2 = 1;
    			ch = 0;
    			continue;
    		}
    		cursor2 <<= 1;
    	}
        return str.reverse().toString();
    }
    /**
	 * Main method to test the program
	 * @param args arguments users input
	 */
    public static void main(String[] args){
      String text;
   	  do{
   	  System.out.println("Enter a string and I will encrypt it as single large integer.");
   	  Scanner input = new Scanner(System.in);
   	  text = input.nextLine();
   	  if(text.length()>80){
   		  System.out.println("String entered is too long, please try again!");
   	  }
   	  }while(text.length()>80);
      MHKEncryption_Array test2 = new MHKEncryption_Array();
      test2.generateQ();
      test2.generateR();
      test2.generateB();
   	  System.out.println("Clear text:");
   	  System.out.println(text);
   	  System.out.println("Number of clear text bytes = " + text.length());
   	  System.out.println(text + " is encryted as");
   	  BigInteger encryptedText = test2.Encryption(text);
   	  System.out.println(encryptedText);
   	  System.out.println("Original text is: " + test2.Decryption(encryptedText));
     }
}

