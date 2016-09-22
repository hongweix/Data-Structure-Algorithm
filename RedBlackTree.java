import java.math.BigInteger;
/**
 * RedBlackTree class
 * @author Hongwei Xie
 * @version 1.3
 */
public class RedBlackTree extends java.lang.Object{
	/**
	 * Instance fields
	 * Invariant: 1. nil
	 *            2. A node is either red or black.
     *            3. The root is black. 
     *            4. All leaves (NIL) are black.
     *            5. If a node is red, then both its children are black.
     *            6. Every path from a given node to any of its descendant NIL nodes contains the same number of black nodes.
	 */
	public RedBlackNode root;
	// The class invariant nil
	public RedBlackNode nil;
	public static int BLACK = 0;
	public static int RED = 1;
	private int numberOfInserts;
	private int recentCompares;
	/**
	 * Constructor method, set nil and root equals to nil at the begining
	 */
    public RedBlackTree(){
    	nil = new RedBlackNode(null, null, BLACK, null, null, null);
    	root = nil;
    }
    /**
     * contains method
     * @param v key
     * @return boolean value
     * Best & worst case: Theta(Log(N)), N is the number of element in the tree
     * Invariant: nil
     * Pre-condition: stack exists
     * Post-condition: boolean value returned
     */
    public boolean contains(java.lang.String v){
    	// record the nearest compare times 
    	recentCompares = 0;
    	// set current to root
    	RedBlackNode current = root;
    	// if current == nil, the tree is empty return false
    	if(current == nil){
    		return false;
    	}
    	// the tree is not empty
    	else{
    		// set return value to be false at begining
    		boolean returnvalue = false;
    		// while current not equals to nil
    		while(current != nil){
    			// compare times plus 1
    			recentCompares++;
    			// if fint the key, then return value is true
        		if(current.getKey().compareTo(v) == 0){
        			returnvalue = true;
        			break;
        		}
        		// else if key is larger than current key, set current to be its right child
        		else if(current.getKey().compareTo(v) < 0){
        			current = current.getRc();
        		}
        		// else key is smaller than current key, set current to be its left child
        		else{
        			current = current.getLc();
        		}
        	}
    		return returnvalue;
    	}
    }
    /**
     * get recent compare times method
     * @return compare times
     */
    public int getRecentCompares(){
        return recentCompares;	
    }
    /**
     * get size method
     * @return number of inserts occurred
     */
    public int getSize(){
    	return numberOfInserts;
    }
    /**
     * get tree height method 
     * @return height of the tree
     */
    public int height(){
    	return height(root);
    }
    /**
     * get height of tree or subtree from node t
     * @param t current node
     * @return height of tree or subtree
     * Best & worst case: Theta(Log(N)), N is the number of elements
     * Invariant: nil
     * Pre-condition: stack exists
     * Post-condition: height returned
     */
    public int height(RedBlackNode t){
      if(t == nil){
    	  return 0;
      }
      // we need to get the max height, so recursively go to the max height between two child
      else{
    	  return 1 + (height(t.getLc()) >= height(t.getRc()) ? height(t.getLc()) : height(t.getRc()));
      }
    }
    /**
     * in order traversal method start from root
     */
    public void inOrderTraversal(){
    	inOrderTraversal(root);
    }
    /**
     * in order traversal method start from node t
     * @param t current node
     * Best & worst case: Theta(N), N is the number of elements
     * Invariant: nil
     * Pre-condition: stack exists
     * Post-condition: traversal finished
     */
    public void inOrderTraversal(RedBlackNode t){
    	if(t == nil){
    		return;
    	}
    	else{
    		reverseOrderTraversal(t.getLc());
			System.out.println(t);
			reverseOrderTraversal(t.getRc());
    	}
    }
    /**
     * insert method
     * @param key key
     * @param value value
     * Implemented as the javadoc analyzed
     */
    public void insert(java.lang.String key, BigInteger value){
    	RedBlackNode y = nil;
		RedBlackNode x = root;
		while(x != nil){
			y = x;
			if(key.compareTo(x.getKey()) < 0){
				x = x.getLc();
			}
			else{
				x = x.getRc();
			}
		}
		RedBlackNode z = new RedBlackNode(key, value, RED, y, null, null);
		if(y==nil){
			root = z;
		}
		else{
			if(z.getKey().compareTo(y.getKey()) < 0){
				y.setLc(z);
			}
			else{
				y.setRc(z);
			}
		}
		z.setLc(nil);
		z.setRc(nil);
		z.setColor(RED);
		RBInsertFixup(z);		
    }
    /**
     * left rotate method
     * @param x node x
     * Implemented as the javadoc analyzed
     */
    public void leftRotate(RedBlackNode x){
    	RedBlackNode y = x.getRc();
		x.setRc(y.getLc());
		y.getLc().setP(x);
		y.setP(x.getP());
		
		if (x.getP() == nil)
			root = y;
		else {
			if (x == x.getP().getLc())
				x.getP().setLc(y);
			else
				x.getP().setRc(y);
		}
		y.setLc(x);
		x.setP(y);
    }
    /**
     * look up method
     * @param key key
     * @return the node that has been found
     * Best & worst case: Theta(N), N is the number of elements
     * Invariant: nil
     * Pre-condition: stack exists
     * Post-condition: node found returned or return null if not found
     */
    public RedBlackNode lookup(String key){
    	// set current equals to root
    	RedBlackNode current = root;
    	// while current not equals to nil
    	while(current != nil){
    		// recent compare times plus 1
    		recentCompares++;
    		// if found, return current node
    		if(current.getKey().compareTo(key) == 0){
    			return current;
    		}
    		// else if key is larger than current key, set current to be its right child
    		else if(current.getKey().compareTo(key) < 0){
    			current = current.getRc();
    		}
    		// else key is smaller than current key, set current to be its left child
    		else{
    			current = current.getLc();
    		}
    	}
    	// after while loop not return anything, means not found, return null
    	return null;
    }
    /**
     * RB insert fixup method
     * @param z node z
     * Implemented as the javadoc analyzed
     */
    public void RBInsertFixup(RedBlackNode z){
    	while (z.getP().getColor() == RED) {
			if (z.getP() == z.getP().getP().getLc()) {
				RedBlackNode y = z.getP().getP().getRc();
				if (y.getColor() == RED) {
					z.getP().setColor(BLACK);
					y.setColor(BLACK);
					z.getP().getP().setColor(RED);
					z = z.getP().getP();

				} else {
					if (z == z.getP().getRc()) {
						z = z.getP();
						leftRotate(z);
					}
					z.getP().setColor(BLACK);
					z.getP().getP().setColor(RED);
					rightRotate(z.getP().getP());
				}
			} else {
				RedBlackNode y = z.getP().getP().getLc();
				if (y.getColor() == RED) {
					z.getP().setColor(BLACK);
					y.setColor(BLACK);
					z.getP().getP().setColor(RED);
					z = z.getP().getP();

				} else {
					if (z == z.getP().getLc()) {
						z = z.getP();
						rightRotate(z);
					}
					z.getP().setColor(BLACK);
					z.getP().getP().setColor(RED);
					leftRotate(z.getP().getP());
				}
			}
    	}
    	root.setColor(BLACK);
    }
    /**
     * reverse order traversal method start from root
     */
    public void reverseOrderTraversal(){
    	reverseOrderTraversal(root);
    }
    /**
     * reverse order traversal method start from node t
     * @param t current node
     * Best & worst case: Theta(N), N is the number of elements
     * Invariant: nil
     * Pre-condition: stack exists
     * Post-condition: traversal finished
     */
    public void reverseOrderTraversal(RedBlackNode t){
    	if(t == nil){
    		return;
    	}
    	else{
    		reverseOrderTraversal(t.getRc());
			System.out.println(t);
			reverseOrderTraversal(t.getLc());
    	}
    }
    /**
     * right rotate method
     * @param x node x
     * Implemented as the javadoc analyzed
     */
    public void rightRotate(RedBlackNode x){
    	RedBlackNode y = x.getLc();
		x.setLc(y.getRc());
		y.getRc().setP(x);
		y.setP(x.getP());
		if (x.getP() == nil)
			root = y;
		else {
			if (x == x.getP().getLc())
				x.getP().setLc(y);
			else
				x.getP().setRc(y);
		}
		y.setRc(x);
		x.setP(y);
    }
    /**
     * Main method to test the tree
     * @param args
     */
    public static void main(String[] args){
    	RedBlackTree test = new RedBlackTree();
    	System.out.println("Insert 3 (String,BigInteger) pairs in the tree: (a,1),(b,2),(c,3).");
    	test.insert("a", BigInteger.ONE);
    	test.insert("b", new BigInteger("2"));
    	test.insert("c", new BigInteger("3"));
    	System.out.println("According to RedBlackTree, (b,2) now should locate at the root.");
    	System.out.println("Look up (b,2): " + test.lookup("b"));
    	System.out.println("If we look up (key = \"b\"), it should only compare once.");
    	System.out.println("Recent compare times is: " + test.getRecentCompares());
    	System.out.println("And the current height should be two.");
    	System.out.println("Current height is: " + test.height());
    	System.out.println("Let's do a in order traverse, the out put sequence should be (a,1),(b,2),(c,3)");
    	test.inOrderTraversal();
    	System.out.println("Let's do a reverse order traverse, the out put sequence should be (c,3),(b,2),(a,1)");
    	test.reverseOrderTraversal();
    }
}

/**
 * RedBlackNode class
 * @author Hongwei Xie
 * @version 1.3
 */
class RedBlackNode extends java.lang.Object{
	// int to represent Black color
	static int BLACK = 0;
	// int to represent red color
	static int RED = 1;
	// key
	private String key;
	// value
	private BigInteger value;
	// color variable
	private int color;
	// parent node
	private RedBlackNode p;
	// left child node
	private RedBlackNode lc;
	// right child node
	private RedBlackNode rc;
	/**
	 * Constructor method
	 * @param key key
	 * @param value value
	 * @param color color
	 * @param p parent
	 * @param lc leftchild
	 * @param rc rightchild 
	 */
	public RedBlackNode(java.lang.String key, BigInteger value, int color, RedBlackNode p,
			RedBlackNode lc, RedBlackNode rc){
		this.key = key;
		this.value = value;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}
	/**
	 * get color method
	 * @return color
	 * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: color returned
	 */
	public int getColor(){
		return color;
	}
	/**
	 * get key method
	 * @return key string stored in node
	 * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: key returned
	 */
	public java.lang.String getKey(){
		return key;
	}
	/**
	 * get data method
	 * @return value stored in node
	 * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: value returned
	 */
	public BigInteger getData(){
		return value;
	}
	/**
	 * get left child method
	 * @return left child node
	 * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: left returned
	 */
	public RedBlackNode getLc(){
		return lc;
	}
	/**
	 * get parent method
	 * @return parent node
	 * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: parent returned
	 */
	public RedBlackNode getP(){
		return p;
	}
	/**
	 * get right child method
	 * @return right child node
	 * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: right returned
	 */
	public RedBlackNode getRc(){
		return rc;
	}
	/**
	 * set color method
	 * @param color color
	 * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: color be set
	 */
	public void setColor(int color){
		this.color = color;
	}
	/**
	 * set key method
	 * @param key key
	 * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: key be set
	 */
    public void setKey(java.lang.String key){
		this.key = key;
	}
    /**
     * set data method
     * @param value value
     * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: data be set
     */
    public void setData(BigInteger value){
    	this.value = value;
    }
    /**
     * set left child method
     * @param lc lc
     * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: left be set
     */
    public void setLc(RedBlackNode lc){
		this.lc = lc;
	}
    /**
     * set parent method
     * @param p p
     * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: parent be set
     */
    public void setP(RedBlackNode p){
		this.p = p;
	}
    /**
     * set right child method
     * @param rc rc
     * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: right be set
     */
    public void setRc(RedBlackNode rc){
    	this.rc = rc;
    }
    /**
     * to string method
     * @return string representation
     * Best case: Theta(1)
	 * Worst case: Theta(1)
	 * pre-condtion: node exists
	 * post-condition: string returned
     */
    public java.lang.String toString(){
    	String returncolor = color == BLACK ? "Black" : "Red";
    	String returnlckey = lc.getKey() == null ? null : lc.getKey();
    	String returnrckey = rc.getKey() == null ? null : rc.getKey();
    	String returnpkey = p.getKey() == null ? null : p.getKey();
    	return "[key=" + key + ", data=" + value + ", color=" + returncolor + ", p-key=" + returnpkey + ", lc-key=" + returnlckey + ", rc-key=" + returnrckey  + "]";
	}
}