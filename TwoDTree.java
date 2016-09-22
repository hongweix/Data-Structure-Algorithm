import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * TwoDTree class
 * @author Hongwei Xie
 * @version 1.1
 * @since Feb 3, 2016
 */
public class TwoDTree {
	//construct the root node
    TwoDNode root = null;
    //line counter
    int linecount = 0;
    /**
     * 1-arg constructor that fill the TwoDTree with document content
     * @param crimeDataLocation
     * pre-condition: The String crimeDataLocation contains the path 
     * to a file formatted in the exact same way as CrimeLatLonXY.csv
     * post-condition: The 2d tree is constructed and may be printed or queried.
     * @throws IOException 
     * @throws NumberFormatException 
     */
	public TwoDTree(String crimeDataLocation) throws NumberFormatException, IOException{
		//Instantiate file reader object
		FileReader file = null;
		try {
			file = new FileReader(crimeDataLocation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Instantiate BufferredReader and FileReader
		BufferedReader breader = new BufferedReader(file);
		//Instantiate a Monitor class that can monitor the file
		String newline = null;
		try {
			newline = breader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			do{
				//if this is the title line, continue
				if(newline.indexOf("Time") != -1) continue;
				//split the content in the new line
				String[] record = newline.split(",");
				//get X
				Double X = Double.parseDouble(record[0]);
				//get Y
				Double Y = Double.parseDouble(record[1]);
				//other information stored together as data
				String data = record[2] + "," + record[3] + "," + record[4] + "," + record[5] + "," + record[6] + "," + record[7] + "," + record[8];
				//construct a new TwoDNode
				TwoDNode newNode = new TwoDNode(X, Y, null, null, true, data);
				//if root is null, set root to be this new node
				  if(root == null){
					  root = newNode;
				  }
				  //else
				  else{
					  //set focusNode as root
					  TwoDNode focusNode = root;
					  //set parent
					  TwoDNode parent;
					  //in a while loop
					  while(true){
						  //let parent be the focusNode
						  parent = focusNode;
						  //if focusNode is vertical, compare x value, if x of new node is smaller
						  //focusNode becomes left node of focusNode
						  if(focusNode.isVertical&&newNode.x < focusNode.x){
							  focusNode = focusNode.leftNode;
							  //new node's isVertical value changed
							  newNode.isVertical = !newNode.isVertical;
							  //if focusNode is null, set new node to be parent's left node
							  if(focusNode == null){
								  parent.leftNode = newNode;
								  break;
							  }
						  }
						  //if focusNode is vertical, compare x value, if x of new node is larger
						  //focusNode becomes right node of focusNode
						  else if(focusNode.isVertical&&newNode.x >= focusNode.x){
							  focusNode = focusNode.rightNode;
							  //new node's isVertical value changed
							  newNode.isVertical = !newNode.isVertical;
							  //if focusNode is null, set new node to be parent's right node
							  if(focusNode == null){
								  parent.rightNode = newNode;
								  break;
							  }
						  }
						  //if focusNode is not vertical, compare y value, if y of new node is smaller
						  //focusNode becomes left node of focusNode
						  else if(!focusNode.isVertical&&newNode.y < focusNode.y){
							  focusNode = focusNode.leftNode;
							  //new node's isVertical value changed
							  newNode.isVertical = !newNode.isVertical;
							  //if focusNode is null, set new node to be parent's left node
							  if(focusNode == null){
								  parent.leftNode = newNode;
								  break;
							  }
						  }
						  //if focusNode is not vertical, compare y value, if y of new node is larger
						  //focusNode becomes left node of focusNode
						  else{
							  focusNode = focusNode.rightNode;
							  //new node's isVertical value changed
							  newNode.isVertical = !newNode.isVertical;
							  //if focusNode is null, set new node to be parent's right node
							  if(focusNode == null){
								  parent.rightNode = newNode;
								  break;
							  }
						  }
					  }
				  }
				  //line count plus 1
				  linecount += 1;
			}while((newline = breader.readLine()) != null);
	}
	/**
	 * Method to get linecounter
	 * @return int value of linecounter;
	 * pre-condition: The 2d tree has been constructed.
	 * post-condtion: int value of line counter is returned
	 */
	public int getLineCount(){
		return linecount;
	}
	/**
	 * The preOrderPrint method use preOrderTraverse method
	 * pre-condition: The 2d tree has been constructed.
	 * post-condition: The 2d tree is displayed with a pre-order traversal. 
	 */
	public void preOrderPrint(){
	    preOrderTraverse(root);
	}
	/**
	 * The following method together finish preOrder print
	 * @param focusNode
	 * pre-condition: The 2d tree has been constructed.
	 * post-condition: The 2d tree is displayed with a pre-order traversal.
	 */
	public void preOrderTraverse(TwoDNode focusNode){
		  if(focusNode != null){
			  System.out.println(focusNode);
			  preOrderTraverse(focusNode.leftNode);
			  preOrderTraverse(focusNode.rightNode);
		  }
    }
	/**
	 * The inOrderPrint method use inOrderTraverse method
	 * pre-condition: The 2d tree has been constructed.
	 * post-condition: The 2d tree is displayed with a in-order traversal. 
	 */
	public void inOrderPrint(){
        inOrderTraverse(root);
	}
	/**
	 * The following method together finish inOrder print
	 * @param focusNode
	 * pre-condition: The 2d tree has been constructed.
	 * post-condition: The 2d tree is displayed with a in-order traversal.
	 */
	public void inOrderTraverse(TwoDNode focusNode){
		if(focusNode != null){
			  inOrderTraverse(focusNode.leftNode);
			  System.out.println(focusNode);
			  inOrderTraverse(focusNode.rightNode);
		  }
	}
    /**
     * The postOrderPrint method use postOrderTraverse method
	 * pre-condition: The 2d tree has been constructed.
	 * post-condition: The 2d tree is displayed with a post-order traversal.
     */
	public void postOrderPrint(){
		postOrderTraverse(root);
	}
	/**
	 * The following method together finish postOrder print
	 * @param focusNode
	 * pre-condition: The 2d tree has been constructed.
	 * post-condition: The 2d tree is displayed with a post-order traversal.
	 */
	public void postOrderTraverse(TwoDNode focusNode){
		  if(focusNode != null){
			  postOrderTraverse(focusNode.leftNode);
			  postOrderTraverse(focusNode.rightNode);
			  System.out.println(focusNode);
		  }
    }
	/**
	 * pre-condition: The 2d tree has been constructed.
	 * post-condition: The 2d tree is displayed with a level-order traversal. 
	 * Note: the level order traversal is not recursive. 
	 * It uses a queue that I write. 
	 * This queue is defined in a separate file named Queue.java. 
	 * Queue.java is built with a linked queue – using front and rear pointers. 
	 * Including methods to add to the front and remove from the rear of the queue. 
	 */
	public void levelOrderPrint(){
		Queue<TwoDNode> myqueue = new Queue<TwoDNode>();
		myqueue.enqueue(root);
		while(!myqueue.isEmpty()){
			TwoDNode tempNode = myqueue.dequeue().element;
			System.out.println(tempNode);
			if(tempNode.leftNode != null){
				myqueue.enqueue(tempNode.leftNode);
			}
			if(tempNode.rightNode != null){
				myqueue.enqueue(tempNode.rightNode);
			}
		}
	}
	/**
	 * Method to find points in rectangular range
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 * pre-condition: The 2d tree has been constructed
	 * post-condition: A list of 0 or more crimes is returned. 
	 * These crimes occurred within the rectangular range specified by the four parameters. 
	 */
	//count how many nodes have been examined
	int examined = 0;
	//count how many nodes have been found
	int found = 0;
	//Initiate a ListOfCrimes object
	ListOfCrimes listofcrimes = new ListOfCrimes();
	//begining search from root and return listofcrimes
	public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2){
	    findNodes(x1, y1, x2, y2, root);
		return listofcrimes;
	}
	/**
	 * Recursive method to points in the rectangular
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param focusNode
	 * pre-condition: The 2d tree has been constructed.
	 * Post-condition: find the points in the rectangular
	 */
	public void findNodes(double x1, double y1, double x2, double y2, TwoDNode focusNode){
		//if focus node is null, return
		if(focusNode == null){
			return;
		}
		//else
		else{
			//examined plus 1
			examined += 1;
			//if focusNode locate in the rectangular
			if(focusNode.x >= x1 && focusNode.x <= x2 && focusNode.y >= y1 && focusNode.y <= y2){
				//found plus 1
				found += 1;
				//add this node to listofcrimes
				listofcrimes.addingCrime(focusNode.toString());	
				//find nodes for the left child
				findNodes(x1,y1,x2,y2,focusNode.leftNode);
				//find nodes for the right child
				findNodes(x1,y1,x2,y2,focusNode.rightNode);
			}
			//else
			else{
				//if is vertical and x locate between x1 and x2 find nodes in both left and right child
				if(focusNode.isVertical && focusNode.x <= x2 && focusNode.x >= x1){
					findNodes(x1,y1,x2,y2,focusNode.leftNode);
					findNodes(x1,y1,x2,y2,focusNode.rightNode);
				}
				//if is vertical and x larger than x2 find nodes in left child
				else if(focusNode.isVertical && focusNode.x > x2){
					findNodes(x1,y1,x2,y2,focusNode.leftNode);
				}
				//if is vertical and x smaller than x1 find nodes in right child
				else if(focusNode.isVertical && focusNode.x < x1){
					findNodes(x1,y1,x2,y2,focusNode.rightNode);
				}
				//if is not vertical and y locate between y1 and y2 find nodes in both left and right child
				else if(!focusNode.isVertical && focusNode.y <= y2 && focusNode.y >= y1){
					findNodes(x1,y1,x2,y2,focusNode.leftNode);
					findNodes(x1,y1,x2,y2,focusNode.rightNode);
				}
				//if is not vertical and y larger than y2 find nodes in left child
				else if(!focusNode.isVertical && focusNode.y > y2){
					findNodes(x1,y1,x2,y2,focusNode.leftNode);
				}
				//if is not vertical and y smaller than y1 find nodes in right child
				else{
					findNodes(x1,y1,x2,y2,focusNode.rightNode);
				}
			}
		}		
	}
	/**
	 * get number of examined nodes
	 * @return number of examined nodes
	 * pre-condition: TwoDTree is already constructed
	 * post-condition: return the number of examined nodes
	 */
	public int getExamined(){
		return examined;
	}
	//get found
	public int getFound(){
		return found;
	}
	/**
	 * Method to find the nearest neighbor
	 * @param x1
	 * @param y1
	 * @return
	 * pre-condition: The 2d tree has been constructed. 
	 * The (x1,y1) pair represents a point in space near Pittsburgh and in the state plane coordinate system.
	 * Post-condition: the distance in feet to the nearest crime is returned. 
	 * Note: The details involved in writing this method are found on the Sedgewick video.
	 */
	//count how many node has been looked at
	int lookat = 0;
	//current winner node
	TwoDNode winner;
	//current winner distance
	double distance;
	public double nearestNeighbor(double x1, double y1){
		//set root as winner
		winner = root;
		//set distance
		distance = Math.pow((winner.x)-x1, 2) + Math.pow((winner.y)-y1, 2);
		//find neighbor from root
		findNeighbor(x1,y1,root);
		return Math.sqrt(Math.pow((winner.x)-x1, 2) + Math.pow((winner.y)-y1, 2));
	}
	/**
	 * Recursive method to find neighbor
	 * @param x1
	 * @param y1
	 * @param focusNode
	 * pre-condition: The 2d tree has been constructed.
	 * Post-condition: find the nearest neighbor
	 */
	public void findNeighbor(double x1, double y1, TwoDNode focusNode){
		//if focusNode is null, return
		if(focusNode == null){
			return;
		}
		//else
		else{
			//lookat plus 1
			lookat += 1;
			//if focusNode has smaller distance, set this to be new winner, and get new distance
			if(Math.pow((focusNode.x)-x1, 2) + Math.pow((focusNode.y)-y1, 2) < distance){
				winner = focusNode;
				distance = Math.pow((winner.x)-x1, 2) + Math.pow((winner.y)-y1, 2);
				//find neighbor in both left and right node
				findNeighbor(x1,y1,focusNode.leftNode);
				findNeighbor(x1,y1,focusNode.rightNode);
			}
			//else
			else{
				//if focusNode isVertical and x smaller than winner.x, search to the right
				if(focusNode.isVertical && focusNode.x <= winner.x){
					findNeighbor(x1,y1,focusNode.rightNode);
				}
				//if focusNode isVertical and x larger than winner.x, search to the left
				else if(focusNode.isVertical && focusNode.x > winner.x){
					findNeighbor(x1,y1,focusNode.leftNode);
				}
				//if focusNode is not Vertical and y smaller than winner.y, search to the right
				else if(!focusNode.isVertical && focusNode.y <= winner.y){
					findNeighbor(x1,y1,focusNode.rightNode);
				}
				//if focusNode isVertical and y larger than winner.y, search to the left
				else{
					findNeighbor(x1,y1,focusNode.leftNode);
				}
			}
		}
	}
	/**
	 * Method to get lookat
	 * @return
	 * pre-condition: tree already exits
	 * post-condition: return lookat
	 */
	public int getLookAt(){
		return lookat;
	}
	/**
	 * Method to get winner information
	 * @return
	 * pre-condition: tree already exits
	 * post-condition: winner returned
	 */
	public String getWinner(){
		return winner.toString();
	}
}
/**
 * TwoDNode class
 * @author Hongwei Xie
 * @version 1.2
 * @since Feb 3, 2016
 */
class TwoDNode{
	//left node pointer
	public TwoDNode leftNode;
	//right node pointer
    public TwoDNode rightNode; 
    //boolean value if is vertical then true, else false
    public boolean isVertical;
    //key x
    public double x;
    //key y
    public double y;
    //data value
    public String data;
    //construct method
    public TwoDNode(double x, double y, TwoDNode leftNode,  TwoDNode rightNode, boolean isVertical, String data) {    
        this.x = x;    
        this.y = y;    
        this.leftNode = leftNode;    
        this.rightNode = rightNode;    
        this.isVertical = isVertical;
        this.data = data;
        }
    //toString method
    public java.lang.String toString(){
    	return Double.toString(x) + "," + Double.toString(y) + "," + data;
    }
}
