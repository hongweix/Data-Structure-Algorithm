/**
 * author: Hongwei Xie
 */
public class MinHeap {
// instance fields of the class
// use array to implement heap
private Vertex[] heap;
// size
private int size;
// predefined max size
private int maxSize;
// final front pointer
private static final int FRONT = 1;
// constructor
public MinHeap(int maxSize){
	this.maxSize = maxSize;
    this.heap = new Vertex[maxSize+1];
    this.size = 0;
}
// get parent
private int getParent(int position){
    return position/2;
}
// get left child
private int getLeftChild(int position){
    return 2*position;
}
// get right chile
private int getRightChild(int position){
    return 2*position+1;
}
// swap two element
private void swap(int position1, int position2){
    Vertex temp = heap[position1];
    heap[position1] = heap[position2];
    heap[position2] = temp;
}
// decide if an element is in leaf position
private boolean isLeaf(int position){
    if(position > size/2){
        return true;
    }
    return false;
}
// insert method
// insert the element in array's current end at first
// then compare with parent to decide moving it or not
// complexity: Worst Theta(log n), best Theta(1)
public void insert(Vertex data){
    heap[++size] = data;
    if(size==1){
    	return;
    }
    int currentItem = size;
    while( getParent(currentItem) != 0 && heap[getParent(currentItem)].disToMST > heap[currentItem].disToMST ){
        swap(getParent(currentItem),currentItem);
        currentItem = getParent(currentItem);
    }
}
// extractMin() method
// change front and current end, size minus 1
// fix up the heap
public Vertex extractMin(){
    Vertex itemPopped = heap[FRONT];
    heap[FRONT] = heap[size--];
    heapify(FRONT);
    return itemPopped;
}
// contains method
// time complexity: Theta(n)
public boolean contains(Vertex v){
	for(int i = 1;i<=size;i++){
		if(heap[i].index == v.index){
			return true;
		}
	}
	return false;
}
// delete method
// time complexity: Theta(n)
public void delete(Vertex v){
	int which = 1;
	for(int i = 1;i<=size;i++){
		which +=1;
		if(heap[i].text.equals(v.text)){
			heap[i] = null;
			size--;
			break;
		}
	}
	for(int j = which;j<size;j++){
		heap[j] = heap[j+1];
	}
	size--;
}
// check if the heap is empty or not
public boolean isEmpty(){
 return size == 0;	
}
// fix up method
// time complexity: Theta(log n)
public void heapify(int position){
    if(isLeaf(position)){
        return;
    }
    // compare with two child, if is larger than one of its child need to swap
    if ( heap[position].disToMST > heap[getLeftChild(position)].disToMST || heap[position].disToMST > heap[getRightChild(position)].disToMST){
        // swap with left child
        if(heap[getLeftChild(position)].disToMST < heap[getRightChild(position)].disToMST){
            swap(position , getLeftChild(position));
            heapify(getLeftChild(position));
        }
        // swap with right child
        else{
            swap(position , getRightChild(position));
            heapify(getRightChild(position));
        }
    }
}
// override to string method
@Override
public String toString(){
    StringBuilder output = new StringBuilder();
    for(int i=1; i<= size/2; i++){
        output.append("Parent :"+ heap[i]);
        output.append("LeftChild : "+heap[getLeftChild(i)] +" RightChild :"+ heap[getRightChild(i)]).append("\n");
    }
    return output.toString();
}
}
