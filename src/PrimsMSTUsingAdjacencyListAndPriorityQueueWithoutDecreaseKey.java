import javafx.util.Pair;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Prim's Minimum Spanning Tree using Adjacency List and Priority Queue without decrease key
 * O(ElogV)
 *
 * resources/PrimsAlgorithmMST.png
 *
 * First we saw implementation using adjacency matrix which was easy to implement but complexity was O(V2)
 * Then we implemented using adjacency list with priority queue using decrease key but complexity was O(EVlogV)
 * After that we implemented using adjacency list with min Heap and got best time complexity O(ElogV)
 * But we implemented own min heap  which was complex.
 * In this implementation we will achieve the same complexity O(ElogV) using priority queue.
 *
 * Earlier implementation of prim's algorithm using priority queue with decrease key function, has higher complexity
 * because of decrease key function.
 *
 * In this approach we will implement without using decrease key.
 *
 * Decrease key algorithm for priority queue
 *
 * 1) Iterate through all the nodes in priority queue
 * 2) Find the node for which we want to decrease key.
 * 3) Once find the node, remove it from the priority queue, update it and add it again.
 *
 * Improvements
 *
 * 1) Do not insert all the vertices at the beginning.
 * 2) Insert only vertices which are not in MST.
 * 3) Use pair class. Use Pair object as priority queue node. Two pairs are equal if their key and value are same.
 *
 * Algorithm:
 *
 * 1) Create priority queue of size = no of vertices
 * 2) Create Pair object for each vertex with two information - vertex and key
 * 3) Override the comparator of priority queue to sort them based on the key
 * 4) Use mst[] to keep track of the vertices which are currently in MST
 * 5) Create key[] to keep track of key/weight value for each vertex. Initialize all the keys to Integer.MAX_VALUE except for the first
 *    vertex for which it will be 0.
 * 6) Create a pair object for vertex 0 with key 0 and insert into priority queue.
 * 7) while priority queue is not empty
 *    1) Extract the min node from the priority queue, say it vertex u and add it to the MST, mst[u] = true
 *    2) Iterate through all the adjacent vertices of vertex u and if adjacent vertex v is not in MST and key of v > u-v weight then create
 *    pair object with vertex v and key = u-v weight and insert into priority queue.
 *    3) Update the key[v] = u-v weight
 * 8) We will use the result object to store the result of each vertex. Result object will store 2 information
 *    1) First parent vertex means from which vertex you can visit this vertex. Example if for vertex v, you have edge u-v
 *      then u will be the parent vertex.
 *    2) Second weight of edge u-v. If you add all these weights for all the vertices in mst[] then you will bet minimum spanning tree.
 *
 * Time Complexity:
 *
 * Total vertices = V and Total Edges = E
 *
 * O(logV) - to extract each vertex from queue. So for V vertices - O(VlogV)
 * O(logV) - Each time new pair object with new key value is created and will be done for at most once for each edge.
 * So for total E edge - O(ElogV)
 * O(VlogV) + O(ElogV) = O((E+V)logV) = O(ElogV)
 *
 */
public class PrimsMSTUsingAdjacencyListAndPriorityQueueWithoutDecreaseKey {

    private static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class ResultSet {

        int parent;
        int weight;
    }

    private static int vertices;
    private static LinkedList<Edge>[] graphList;

    private static void initializeGraph(int nodes) {

        vertices = nodes;
        graphList = new LinkedList[vertices];

        //Initialize graphLit for all the vertices
        for (int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination, int weight) {

        Edge edge = new Edge(source, destination, weight);
        graphList[source].addFirst(edge);

        edge = new Edge(destination, source, weight);
        graphList[destination].addFirst(edge);
    }

    private static void primMST() {

        boolean[] mst = new boolean[vertices];
        ResultSet[] resultSets = new ResultSet[vertices];
        int[] key = new int[vertices]; //Keys used to store keys to know whether priority queue update is required.

        //Initialize all the keys to infinity and initialize resultSet for all the vertices
        for (int i = 0; i < vertices; i++) {
            key[i] = Integer.MAX_VALUE;
            resultSets[i] = new ResultSet();
        }

        //Initialize priority queue
        //override the comparator to do sorting based on keys
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {

                //Sort using key values
                int key1 = p1.getKey();
                int key2 = p2.getKey();

                return key1 - key2;
            }
        });

        //Create pair for the first index, 0 key and 0 vertex
        key[0] = 0;
        Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);

        //Add to pq
        pq.offer(p0);

        resultSets[0] = new ResultSet();
        resultSets[0].parent = -1;

        //While priority queue is not empty
        while(!pq.isEmpty()) {

            //Extract the min
            Pair<Integer, Integer> extractedPair = pq.poll();

            //Extracted vertex
            int extractedVertex = extractedPair.getValue();

            mst[extractedVertex] = true;

            //Iterate through all the adjacent vertices and update the keys
            LinkedList<Edge> list = graphList[extractedVertex];
            for (int i = 0; i < list.size(); i++) {

                Edge edge = list.get(i);

                //Only if edge.destination is not present in mst
                if (mst[edge.destination] == false) {

                    int destination = edge.destination;
                    int newKey = edge.weight;

                    //check if newKey is less than existing key
                    if (newKey < key[destination]) {

                        //Make pair and add to pq
                        Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                        pq.offer(p);

                        //Update the resultSet for destination vertex
                        resultSets[destination].parent = extractedVertex;
                        resultSets[destination].weight = newKey;

                        //Update key
                        key[destination] = newKey;
                    }
                }
            }
        }
        //Print MST
        printMST(resultSets);
    }

    private static void printMST(ResultSet[] resultSets) {

        int totalMinWeight = 0;

        System.out.println("Minimum Spanning Tree: ");
        for (int i = 1; i < vertices; i++) {
            System.out.println("Edge: " + i + " - " + resultSets[i].parent + " Weight: " + resultSets[i].weight);
        }
        System.out.println("Total minimum weight: " + totalMinWeight);
    }

    public static void main(String[] args) {

        int vertices = 6;
        initializeGraph(vertices);
        addEdge(0, 1, 4);
        addEdge(0, 2, 3);
        addEdge(1, 2, 1);
        addEdge(1, 3, 2);
        addEdge(2, 3, 4);
        addEdge(3, 4, 2);
        addEdge(4, 5, 6);

        primMST();
    }
}
