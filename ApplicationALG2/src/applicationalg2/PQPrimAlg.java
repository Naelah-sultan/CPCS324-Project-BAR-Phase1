
package applicationalg2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PQPrimAlg extends MSTAlgorithm {
    
    private ArrayList<Edge> mstSet = new ArrayList<>();

    public PQPrimAlg() {
    }
    
    public void primsPQ(Graph graph) {

        // Whether a vertex is in PriorityQueue or not
        Boolean[] mstset = new Boolean[graph.getMaxVert()];
        node[] List = new node[graph.getMaxVert()];

        // Stores the parents of a vertex
        int[] parent = new int[graph.getMaxVert()];

        for (int i = 0; i < graph.getMaxVert(); i++) {
            List[i] = new node();
        }

        for (int i = 0; i < graph.getMaxVert(); i++) {

            mstset[i] = false;
            List[i].key = Integer.MAX_VALUE;// Initialize key values to infinity
            List[i].vertex = i;
            parent[i] = -1; // -1 is NIL
        }

        // Include the source vertex in mstset
        mstset[0] = true;

        // Set key of the first element in the queue as 0 
        List[0].key = 0;

        // You should use minHeap as Priority Queue
        PriorityQueue<node> queue = new PriorityQueue<>(new comparator());

        for (int i = 0; i < graph.getMaxVert(); i++) {
            queue.add(List[i]);
        }

        // Loops until the queue is not empty
        while (!queue.isEmpty()) {

            // Extracts a node with min key value
            node node0 = queue.poll();

            // Include that node into mstset
            mstset[node0.vertex] = true;

            // For all adjacent vertex of the extracted vertex V
            for (Edge iterator : graph.getAdjList()[node0.vertex]) {

                // If V is in queue
                if (mstset[iterator.getTargetVert().getLabel()] == false) {
                    
                    // If the key value of the adjacent vertex is
                    // more than the extracted key
                    // update the key value of adjacent vertex
                    // to update first remove and add the updated vertex
                    if (List[iterator.getTargetVert().getLabel()].key > iterator.getWeight()) {

                        queue.remove(List[iterator.getTargetVert().getLabel()]);// remove parent from queue
                        List[iterator.getTargetVert().getLabel()].key = iterator.getWeight(); // change its key with its weight (because its weight is smaller)

                        queue.add(List[iterator.getTargetVert().getLabel()]); //add the parent again in the queue
                        parent[iterator.getTargetVert().getLabel()] = node0.vertex; // update the value of parent
                    }
                }
            }
        }
        
        for (int i = 0; i < graph.getMaxVert(); i++) { // loop to add all edges that are making MST
            
            Vertex tempV1 = new Vertex(i);
            Vertex tempV2 = new Vertex(parent[i]);
            int weightTemp = graph.getWeight(tempV1, tempV2);
            Edge temp = new Edge(tempV1, tempV2, weightTemp);
            
            mstSet.add(temp);
        }
    }
    
    @Override
    public void displayResultingMST() {
        
        double total_min_weight = 0;
        for (int i = 0; i < mstSet.size(); i++) {
            total_min_weight += mstSet.get(i).getWeight();
        }

        System.out.println("\nTotal weight of MST by Prim's algorithm [PriorityQueue]: " + total_min_weight);
        
        //**************Uncomment the following part to print the edges*******************************
        
//        System.out.println("The edges that are making MST are: ");
//
//        for (int i = 0; i < mstSet.size(); i++) {
//            System.out.println("Edge from " + mstSet.get(i).getSourceVert().getLabel() + " to " + mstSet.get(i).getTargetVert().getLabel() + " has weight " + mstSet.get(i).getWeight());
//        }
    }
}
