
package applicationalg2;

import java.util.LinkedList;

public class Vertex {

    private int label;
    private boolean isVisited = false;
    private LinkedList<Edge>[] adjList; //to represents the adjacency list of a vertex

    public Vertex(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public void setLable(int label) {
        this.label = label;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public LinkedList<Edge>[] getAdjList() {
        return adjList;
    }

    public void setAdjList(LinkedList<Edge>[] adjList) {
        this.adjList = adjList;
    }
}
