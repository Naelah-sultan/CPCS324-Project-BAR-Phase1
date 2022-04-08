
package applicationalg2;

import java.util.ArrayList;
import java.util.Stack;

public class KurskalAlg extends MSTAlgorithm  {
    
    private ArrayList<Edge> mstSet = new ArrayList<>();

    public KurskalAlg() {
    }
    
    public void MST_Kruskal(Graph graph) {

        Stack<Edge> edgeList = new Stack();// set of all edges in the graph

        for (int i = 0; i < graph.getMaxVert(); i++) {// Stroing Edges in the Stack
            Vertex temp = new Vertex(i);
            for (int j = 0; j < graph.getAdjList()[temp.getLabel()].size(); j++) {
                edgeList.add(graph.getAdjList()[temp.getLabel()].get(j));
            }
        }

        int[] parent = new int[graph.getMaxVert()];// Store the parent for each vertex

        makeSet(parent);// for each vertex in graph, make a set

        //sort the edges of 'allEdges' in non-decreasing order by weight
        bubbleSort(edgeList);


        while (!edgeList.isEmpty()) {// it will store the number of edges created ( it should be verticesNo - 1 )

            Edge tempE = edgeList.firstElement();// peak the first element (which is the smallest weight element in the Stack)

            int set1 = findParent(parent, tempE.getSourceVert().getLabel());
            int set2 = findParent(parent, tempE.getTargetVert().getLabel());

            if (set1 != set2) { // making sure it's not making a cycle

                mstSet.add(tempE); // add the edges to the MST set
                union(parent, set1, set2);
            }

            edgeList.remove(tempE);//Remove the edge from the Stack (whether it have been added to the list of MST OR not )
        }
    }

    public void bubbleSort(Stack<Edge> edges) {//Sort the edges in non-decreasing order by weight
        
        int n = edges.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (edges.get(j).getWeight() > edges.get(j + 1).getWeight()) {
                    // swap arr[j+1] and arr[j]
                    Edge temp = edges.get(j);
                    edges.set(j, edges.get(j + 1));
                    edges.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public void displayResultingMST() {// To print MST's edges and its weight for Kruskal's Algorithm

        double totalWeight = 0;
        for (int i = 0; i < mstSet.size(); i++) { // Loop to calculate the weight
            totalWeight += mstSet.get(i).getWeight(); 
        }

        System.out.println("\nTotal weight of MST by Kruskal's algorithm: " + totalWeight); 
        
        
        //**************Uncomment the following part to print the edges*******************************
        
//        System.out.println("The edges that are making MST are: ");
//
//        for (int i = 0; i < mstSet.size(); i++) { // Loop to print edge information
//            Edge edge = mstSet.get(i); 
//            System.out.println("Edge from " + edge.getSourceVert().getLabel() + " to " + edge.getTargetVert().getLabel() + " has weight " + edge.getWeight());
//        }
    }

    public void union(int[] parent, int x, int y) {

        int setXparent = findParent(parent, x);
        int setYparent = findParent(parent, y);

        parent[setYparent] = setXparent;//make x as parent of y
    }

    public int findParent(int[] parent, int vertex) {

        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself (ROOT)
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return findParent(parent, parent[vertex]);
    }

    public void makeSet(int[] parent) { // creating a new element with a parent pointer to itself.

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }
}
