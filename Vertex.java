import java.util.LinkedList;
/**
 * @author: Hongwei Xie
*/
public class Vertex implements Comparable<Vertex> {
   /**
    * instance fields of the vertex class
   */
   public double X;
   public double Y;
   // store the distance to minimum spanning tree
   public double disToMST;
   // index to specify vertex
   public int index;
   // reference to parent vertex
   public Vertex pi;
   // list to store ajacent vertex in minimum spanning tree
   public LinkedList<Vertex> list;
   // store each record's all information
   public String text;
   // constructor
   public Vertex(double x, double y, int index, String text){
	   X = x;
	   Y = y;
	   this.index = index;
	   this.text = text;
	   this.pi = null;
	   this.list = new LinkedList<Vertex>() ;
   }
   // override the compareTo method
   @Override
   public int compareTo(Vertex v){
	   return Double.compare(disToMST, v.disToMST);
   }
   // override the toString method
   @Override
   public String toString(){
	   return text;
   } 
}
