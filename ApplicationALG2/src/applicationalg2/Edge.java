
package applicationalg2;

public class Edge {

    private Vertex source;
    private Vertex target;
    private Vertex parent;
    private int weight = 1;

    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Vertex getSourceVert() {
        return source;
    }

    public Vertex getTargetVert() {
        return target;
    }

    public Vertex getParent() {
        return parent;
    }

    public int getWeight() {
        return weight;
    }

    public void setSourceVert(Vertex sourceVert) {
        this.source = sourceVert;
    }

    public void setTargetVert(Vertex targetVert) {
        this.target = targetVert;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
