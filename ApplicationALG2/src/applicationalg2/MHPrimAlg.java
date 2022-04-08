
package applicationalg2;

import java.util.ArrayList;
import java.util.LinkedList;

public class MHPrimAlg extends MSTAlgorithm{

    private ArrayList<Edge> mstSet = new ArrayList<>();

    public MHPrimAlg() {
    }
    
    public void primMH(Graph graph) {

        boolean[] mstTemp = new boolean[graph.getMaxVert()];
        ResultSet[] resultSet = new ResultSet[graph.getMaxVert()];
        //keys[] used to store the key to know whether min hea update is required
        double[] key = new double[graph.getMaxVert()];

        HeapNode[] heapNodes = new HeapNode[graph.getMaxVert()];
        for (int i = 0; i < graph.getMaxVert(); i++) {

            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].key = Integer.MAX_VALUE;

            resultSet[i] = new ResultSet();
            resultSet[i].parent = -1;
            mstTemp[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        //decrease the key for the first index
        heapNodes[0].key = 0;

        //add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(graph.getMaxVert());
        //add all the vertices to priority queue
        for (int i = 0; i < graph.getMaxVert(); i++) {
            minHeap.insert(heapNodes[i]);
        }

        //while minHeap is not empty
        while (!minHeap.isEmpty()) {
            //extract the min
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.vertex;
            mstTemp[extractedVertex] = false;

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = graph.getAdjList()[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is present in heap
                if (mstTemp[edge.getTargetVert().getLabel()]) {
                    int destination = edge.getTargetVert().getLabel();
                    double newKey = edge.getWeight();
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        graph.decreaseKey(minHeap, newKey, destination);
                        //update the parent HeapNode for destination
                        
                        resultSet[destination].parent = extractedVertex;
                        resultSet[destination].weight = newKey;
                        key[destination] = newKey;
                    }
                }
            }
        }
        
        for (int i = 1; i < resultSet.length; i++) { // loop to add all edges that are making MST
            
            Vertex tempV1 = new Vertex(i);
            Vertex tempV2 = new Vertex(resultSet[i].parent);
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

        System.out.println("\nTotal weight of MST by Prim's algorithm [MinHeap]: " + total_min_weight);
        
        //**************Uncomment the following part to print the edges*******************************
        
//        System.out.println("The edges that are making MST are: ");
//
//        for (int i = 0; i < mstSet.size(); i++) {
//            System.out.println("Edge from " + mstSet.get(i).getSourceVert().getLabel() + " to " + mstSet.get(i).getTargetVert().getLabel() + " has weight " + mstSet.get(i).getWeight());
//        }
    }
}
    

