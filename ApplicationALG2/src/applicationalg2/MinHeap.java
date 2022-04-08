
package applicationalg2;

/**
 *
 * @author User
 */
public class MinHeap {

    private HeapNode[] Heap;
    private int size;
    private int max;
    int[] indexes; //will be used to decrease the key

    // FRONT is the index of the minimum value in the Min-Heap
    private static final int FRONT = 1;

    public MinHeap() {
    }

    // Constructor of this class
    public MinHeap(int max) {
        
        this.max = max;
        Heap = new HeapNode[max + 1];
        indexes = new int[max];
        Heap[0] = new HeapNode();
        Heap[0].key = Integer.MIN_VALUE;
        Heap[0].vertex = -1;
        max = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HeapNode[] getHeap() {// Return the minHeap array
        return this.Heap;
    }

    // To swap two nodes of the heap
    private void swap(int fpos, int spos) {
        HeapNode temp;
        temp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = temp;
    }

    public void insert(HeapNode x) {
        size++;
        int index = size;
        Heap[index] = x;
        indexes[x.vertex] = index;
        minHeapify(index);
    }
    
    public void minHeapify(int pos) {
        
            int parentIdx = pos / 2;
            int currentIdx = pos;
            while (currentIdx > 0 && Heap[parentIdx].key > Heap[currentIdx].key) {
                HeapNode currentNode = Heap[currentIdx];
                HeapNode parentNode = Heap[parentIdx];

                //swap the positions
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
            }
        }
    
    public void sinkDown(int k) {
        
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k + 1;
            if (leftChildIdx < getSize() && Heap[smallest].key > Heap[leftChildIdx].key) {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < getSize() && Heap[smallest].key > Heap[rightChildIdx].key) {
                smallest = rightChildIdx;
            }
            if (smallest != k) {

                HeapNode smallestNode = Heap[smallest];
                HeapNode kNode = Heap[k];

                //swap the positions
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

    public void print() {// to print the contents of the heap
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(
                    " PARENT : " + Heap[i]
                    + " LEFT CHILD : " + Heap[2 * i]
                    + " RIGHT CHILD :" + Heap[2 * i + 1]);

            System.out.println();// new Line
        }
    }

    public HeapNode extractMin() { // it will extract the min value
        
        HeapNode popped = Heap[FRONT];
            HeapNode lastNode = Heap[size];
            
            // update the indexes[] and move the last HeapNode to the top
            indexes[lastNode.vertex] = 1;
            Heap[1] = lastNode;
            Heap[size] = null;
            sinkDown(1);
            size--;
            
        return popped;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

