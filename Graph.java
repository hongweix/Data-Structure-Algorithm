/**
 * @author: Hongwei Xie 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ListIterator;

public class Graph {
   // instance fields for the class
   private int start;
   private int end;
   private int size;
   // array to store all vertexes
   private Vertex[] vertexes;
   // distance matrix
   private double[][] disMatrix;
   // index for each vertex
   private int index;
   // length
   private double length;
   // path
   private Vertex[] path;
   // constructor
   public Graph(int start, int end){
	   this.start = start;
	   this.end = end;
	   size = end-start+1;
	   disMatrix = new double[size][size];
	   vertexes = new Vertex[size];
	   path = new Vertex[size]; 
	   index = 0;
	   // import data
	   importData();
	   // construct matrix
	   constructMatrix();
   }
   // method to import data
   public void importData(){
	   try{
	   	// read data
		   BufferedReader br = new BufferedReader(new FileReader("CrimeLatLonXY1990.csv"));
		   int i = 0;
		   br.readLine();
		   while(i < start){
			   br.readLine();
			   i++;
		   }
		   // instantiate vertex according to each record
		   String record;
		   while(i <= end && (record=br.readLine())!=null){
			   String temp[] = record.split(",");
			   vertexes[i-start] = new Vertex(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]),i-start,record);
			   i++;
		   }
	   } catch(IOException e){
		   System.out.println("Import data exception.");
	   }
   }
   // construct distance matrix
   public void constructMatrix(){
	   for(int i = 0;i<size;i++){
		   for(int j = i+1;j<size;j++){
			   disMatrix[i][j]=0.00018939*Math.sqrt(Math.pow(vertexes[i].X-vertexes[j].X, 2)+Math.pow(vertexes[i].Y-vertexes[j].Y, 2));
			   disMatrix[j][i]=disMatrix[i][j];
		   }
	   }
   }
   // prim algorithm
   public void Prim(){
	   MinHeap heap  = new MinHeap(size);
	   for(int i = 1;i<size;i++){
		   vertexes[i].disToMST = Double.MAX_VALUE;
		   heap.insert(vertexes[i]);
	   }
	   vertexes[0].disToMST = 0;
	   heap.insert(vertexes[0]);
	   while(!heap.isEmpty()){
		   Vertex u = heap.extractMin();
		   if(u.pi!=null){
			   u.pi.list.add(u);
		   }
		   for(int i = 0;i<size;i++){
			   Vertex v = vertexes[i];
			   double dis = disMatrix[i][u.index];
			   if(heap.contains(v) && dis<=v.disToMST){
				   v.pi = u;
				   v.disToMST = dis;
			   }
		   }  
	   }
   }
   // preorder traverse
   public void preOrder(Vertex v){
	   path[index++] = v;
	   ListIterator<Vertex> iterator=v.list.listIterator();
		while(iterator.hasNext())
		{
			preOrder(iterator.next());
		}
   }
   // calculate length
   public void calculateLen(){
	   for(int i=0;i<size-1;i++)
		{
			length+=disMatrix[path[i].index][path[i+1].index];
		}
		length+=disMatrix[path[0].index][path[size-1].index];
   }
   // TSP: first do prim, then preorder traverse, last calculate length
   public void TSP(){
	   Prim();
	   preOrder(vertexes[0]);
	   calculateLen();
   }
   // setter and getter
    public int getSize() {
		return size;
	}

	public Vertex[] getVertices() {
		return vertexes;
	}
	

	public Vertex[] getpath() {
		return path;
	}
	
	public double getLen() {
		return length;
	}
}
