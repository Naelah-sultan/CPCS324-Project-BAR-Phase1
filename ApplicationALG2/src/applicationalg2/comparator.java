
package applicationalg2;

import java.util.Comparator;

public class comparator implements Comparator<node> {    // Comparator class created for PriorityQueue
        // returns 1 if node0.key > node1.key
        // returns 0 if node0.key < node1.key and
        // returns -1 otherwise
 
        @Override
        public int compare(node node0, node node1)
        {
            return node0.key - node1.key;
        }
    }
