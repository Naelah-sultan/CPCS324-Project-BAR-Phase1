package applicationalg2;

import java.util.*;

public class Graph {

    private int maxVertices;//number of vertices 
    private int maxEdges;//number of edges 
    private boolean isDigraph;// if the graph is directed graph or not
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private LinkedList<Edge>[] adjList;

    public Graph() {
    }

    public Graph(int maxVertices, int maxEdges) {

        this.maxVertices = maxVertices;
        this.maxEdges = maxEdges;
        adjList = new LinkedList[maxVertices];

        for (int i = 0; i < maxVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
    //make graph method , will generate and create a random graph of random verts and edges
    //INPUT: number of vertices and number of edges
    //OUTPUT:connected graph
    //steps for this method:
    //----------------------------------------------------------
    //1-randomly initializes the vertices
    //2-create edges that connects the vertices randomly 
    //3-assigning random weights
    //-----------------------------------------------------------
    public void makeGraph() {

        int randomNum;
        int weight;
        int remainingEdge = 0;
        Vertex sourceVert;
        Vertex targetVert;
        boolean isConnected;

        //loop to generete vetices
        for (int i = 0; i < maxVertices; i++) {

            Vertex tempVert = new Vertex(i);
            vertices.add(tempVert);
        }

        //loop to randomly connect two random verices with random edges
        for (int i = 0; i < maxVertices - 1; i++) {

            //random vertices
            randomNum = (int) (Math.random() * maxVertices);
            sourceVert = new Vertex(randomNum);
            randomNum = (int) (Math.random() * maxVertices);
            targetVert = new Vertex(randomNum);

            //  connectivity check
            isConnected = isConnected(sourceVert.getLabel(), targetVert.getLabel(), adjList);

            //if isConnected is true, choose different vertices 
            if (isConnected) {
                i--;
            } //if not connectend, connect it by addEdge function shown below
            else {
                //random waight 
                weight = (int) (Math.random() * 20 + 1);
                addEdge(sourceVert, targetVert, weight);

            }
        }

        remainingEdge = maxEdges - (maxVertices - 1);//decrease number of edge  

        //loop again on rem edges
        for (int i = 0; i < remainingEdge; i++) {

            //random vertices
            randomNum = (int) (Math.random() * maxVertices);
            sourceVert = new Vertex(randomNum);
            randomNum = (int) (Math.random() * maxVertices);
            targetVert = new Vertex(randomNum);

            // connectivity check
            isConnected = isConnected(sourceVert.getLabel(), targetVert.getLabel(), adjList);

            //if isConnected is true, choose different vertices 
            if (isConnected == true) {
                i--;
            } //if isConnected is false, choose different vertices  it by addEdge
            else {
                //random waight 
                weight = (int) (Math.random() * 20 + 1);
                addEdge(sourceVert, targetVert, weight);

            }
        }
    }

    public void addEdge(Vertex source, Vertex target, int weight) {// add new edge to the adjList

        Edge edge = new Edge(source, target, weight);
        adjList[source.getLabel()].addFirst(edge);

        if (isDigraph == false) {// if the graph is undirected

            edge = new Edge(target, source, weight);
            adjList[target.getLabel()].addFirst(edge);
        }
    }

    public boolean isConnected(int source, int target, LinkedList<Edge>[] adjList) {

        // output: true if there is an edge between source and target vertex
        LinkedList<Edge> tempList;
        Edge tempEdge;
        int sourceid, targetid;

        //check each vertex
        for (int i = 0; i < adjList.length; i++) {

            //temp of edges connected to vertex i
            tempList = adjList[i];

            //loop to chek edges of vertex i
            for (int j = 0; j < tempList.size(); j++) {

                //temp of edge
                tempEdge = tempList.get(j);

                //get lable of source , target
                sourceid = tempEdge.getSourceVert().getLabel();
                targetid = tempEdge.getTargetVert().getLabel();

                //if lable and sent parameter are same then itsConnected
                if (sourceid == source && targetid == target) {
                    return true;
                }
            }
        }
        // not connected
        return false;
    }

    public void printGraph() {

        for (int i = 0; i < maxVertices; i++) {

            LinkedList<Edge> list = adjList[i];

            for (int j = 0; j < list.size(); j++) {

                System.out.println("vertex - " + list.get(j).getSourceVert().getLabel() + " is connected to "
                        + list.get(j).getTargetVert().getLabel() + " with weight " + list.get(j).getWeight());
            }
        }
    }
    
    public void decreaseKey(MinHeap minHeap, double newKey, int vertex) {

        //get the index which key's needs a decrease;
        int index = minHeap.indexes[vertex];

        //get the HeapNode and update its value
        HeapNode node = minHeap.getHeap()[index];
        node.key = newKey;
        minHeap.minHeapify(index);
    }

    public int getMaxVert() {
        return maxVertices;
    }

    public void setMaxVert(int maxVertices) {
        this.maxVertices = maxVertices;
    }

    public int getMaxEdges() {
        return maxEdges;
    }

    public void setMaxEdges(int maxEdges) {
        this.maxEdges = maxEdges;
    }
    
    public int getWeight(Vertex src, Vertex targ) {
        
        for (Edge v : this.getAdjList()[src.getLabel()]) {
            if (v.getTargetVert().getLabel() == targ.getLabel()) {
                return v.getWeight();
            }
        }
        return 0;
    }
    
    public LinkedList<Edge>[] getAdjList() {
        return adjList;
    }

    public void setAdjList(LinkedList<Edge>[] adjList) {
        this.adjList = adjList;
    }
}
