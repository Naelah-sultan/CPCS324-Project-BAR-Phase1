
package applicationalg2;

import java.util.LinkedList;

public abstract class MSTAlgorithm {
 
    private LinkedList<Edge>[] MSTresultList;

    MSTAlgorithm() {
    }

    public abstract void displayResultingMST();
}
