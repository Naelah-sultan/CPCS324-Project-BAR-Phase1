package applicationalg2;
/*
---------------CPCS324-Group Project- Phase 1------------------
-Perform an experimental comparison of two of the minimum-spanning-tree algorithms
-Kruskal VS priority-queue (pq) based Prim
-Prim min-heap VS priority-queue (pq) based Prim*/

//--------Project Members------------
// Naelah Sultan Miyajan 1905313 BAR
//Rahaf Alwajeeh 1909711 BAR
//Wed abu Alhamayl 1813720 BAR
//---------------------------------------------

import java.util.Scanner;

// --------------------------------Main method-------------------------
// here user will be asked to input the number of vertices and edges for their graph
public class ApplicationALG2 {

    public static void main(String[] args) {

        //Scanner to read obj from user
        Scanner input = new Scanner(System.in);
        int n = 0, m = 0;
        System.out.println("\t ------- compare Different Minimum Spanning Tree Algorithms-------\n");
        System.out.println("\tKruskal's Algorithm& Prim's Algorithm (based on Priority Queue)\n"
                + "\t Prim's Algorithm (based on Min Heap)& Prim's Algorithm(based on Priority Queue)");
        System.out.println(">>cases : (where n is the number of vertices "
                + "and m is the number of edges: ");

        System.out.println(" 1:  n=1,000 ,  m=10,000");
        System.out.println(" 2:  n=1,000 ,  m=15,000");
        System.out.println(" 3:  n=1,000 ,  m=25,000");
        System.out.println(" 4:  n=5,000 ,  m=15,000");
        System.out.println(" 5:  n=5,000 ,  m=25,000");
        System.out.println(" 6:  n=10,000 , m=15,000");
        System.out.println(" 7:  n=10,000 , m=25,000");
        System.out.println(" 8:  n=20,000 , m=200,000");
        System.out.println(" 9:  n=20,000 , m=300,000");
        System.out.println("10:  n=50,000 , m=1,000,000");

        System.out.println("--------------------------------------------------------------");
        System.out.print("> Enter a case you want to test: ");
        int yourTestChoice = input.nextInt();

        if (yourTestChoice < 1 || yourTestChoice > 10) {
            System.out.println("Invalid input!");
            
        } else {

            switch (yourTestChoice) {
                case 1: {
                    n = 1000;
                    m = 10000;

                    break;
                }
                case 2: {
                    n = 1000;
                    m = 15000;

                    break;
                }
                case 3: {
                    n = 1000;
                    m = 25000;

                    break;
                }
                case 4: {
                    n = 5000;
                    m = 15000;

                    break;
                }
                case 5: {
                    n = 5000;
                    m = 25000;

                    break;
                }
                case 6: {
                    n = 10000;
                    m = 15000;

                    break;
                }
                case 7: {
                    n = 10000;
                    m = 25000;

                    break;
                }
                case 8: {
                    n = 20000;
                    m = 200000;

                    break;
                }
                case 9: {
                    n = 20000;
                    m = 300000;

                    break;
                }
                case 10: {
                    n = 50000;
                    m = 1000000;
                    break;
                }
                default:
                    System.out.println("Invalid input!");

                    System.out.println("Thanks!");
                    break;
            }
        }

        //----------------------------Print for Graph class-----------------------------------
        Graph graph = new Graph(n, m);
        graph.makeGraph();
       // graph.printGraph();  //Uncomment this statement to print the graph

        //-------------------------------------------------------------------------------------
        // =========================================================================================
        System.out.println("\n################## Running time for algorithms #######################");

        // ===========================  Kurskal's Algorithm =======================================
        long startTime = System.nanoTime();

        KurskalAlg kurskalAlg = new KurskalAlg();
        kurskalAlg.MST_Kruskal(graph);
        kurskalAlg.displayResultingMST();

        long endTime = System.nanoTime();
        long runningTime = endTime - startTime;
        System.out.println("\nKurskal Algorithm running time is: " + runningTime);
        // =========================================================================================
        System.out.println("===============================================================");
        // ===========================  Prim's Algorithm Using Min-Heap =============================
        startTime = System.nanoTime();

        MHPrimAlg MHPrim = new MHPrimAlg();
        MHPrim.primMH(graph);
        MHPrim.displayResultingMST();

        endTime = System.nanoTime();
        runningTime = endTime - startTime;
        System.out.println("\nMH-Prim Algorithm running time is: " + runningTime);
        // =========================================================================================
        System.out.println("===============================================================");
        // ===================  Prim's Algorithm Using min-PriorityQueue ===========================
        startTime = System.nanoTime();

        PQPrimAlg PQPrim = new PQPrimAlg();
        PQPrim.primsPQ(graph);
        PQPrim.displayResultingMST();

        endTime = System.nanoTime();
        runningTime = endTime - startTime;
        System.out.println("PQ-Prim Algorithm running time is: " + runningTime);
        // =========================================================================================

    }
}
