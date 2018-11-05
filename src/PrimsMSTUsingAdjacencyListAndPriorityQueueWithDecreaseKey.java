import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Prim's Minimum Spanning Tree (MST) using Adjacency List and Priority Queue with decrease key.
 *
 * resources/PrimsAlgorithmMST.png
 *
 * 1) In place of minHeap we will use priority queue with decrease-key function.
 * This approach will have more time complexity.
 *
 * 2) Create priority queue of size = no of vertices
 * 3) Create heapNode for each vertex which will store two information
 *    Vertex
 *    Key
 * 4) Use inPriorityQueue[] to keep track of the vertices which are currently in priority queue.
 * 5) Create key[] to keep track of key value for each vertex (Keep updating it as you update headNode for each vertex)
 * 6) Override the comparator of priority queue to sort them based on the key. Insert all heapNodes into priority queue.
 *    inPriorityQueue[v] = true for all vertices
 * 7) While priority queue is not empty
 *    1) Extract the min node from the priority queue
 *    2) Decrease key: Iterate through all the adjacent vertices of above vertex u and if adjacent vertex (say it's v) is still
 *    in the inPriorityQueue[] and key of vertex v > u-v edge weight then update the node in priority queue. Find the node for vertex v in
 *    priority queue, remove it, update the key = u-v edge weight and insert it again. This step increases the complexity of this implementation.
 *8) We will use Result object to store the result of each vertex. Result object will store 2 informations:
 *   1) First the parent vertex means from which vertex you can visit this vertex. For example if for vertex v, you have edge u-v then vertex u
 *   will be the parent vertex
 *   2) Second the weight of edge u-v. If you add all these weights for all the vertices then you will get Minimum Spanning Tree Weight.
 *
 *Time Complexity:
 *
 * Total vertices: V and Total Edges: E
 *
 * O(logV) - to extract each vertex from priority queue. So for V vertices = O(VlogV)
 * O(V) - each time to decrease the key of a vertex. Decrease key will be called for atmost once for each edge.
 * So for total E edge = O(EV)
 * Total complexity = O(VlogV) + O(EV) = O(EVlogV)
 */
public class PrimsMSTUsingAdjacencyListAndPriorityQueueWithDecreaseKey {

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

    private static class HeapNode {
        int vertex;
        int key;
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

        //Initialize graph lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);

        graphList[source].addFirst(edge);

        edge = new Edge(destination, source, weight);
        graphList[destination].addFirst(edge); //For undirected graph
    }

    private static void primMST() {

        boolean[] inPriorityQueue = new boolean[vertices];
        ResultSet[] resultSets = new ResultSet[vertices];
        int[] key = new int[vertices]; //Keys are used to store the keys/weight to know whether priority queue update is required.

        //Create heapNode for all the vertices
        HeapNode[] heapNodes = new HeapNode[vertices];

        for (int i = 0; i < vertices; i++) {

            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].key = Integer.MAX_VALUE;

            //Initialize resultSet
            resultSets[i] = new ResultSet();
            resultSets[i].parent = -1;

            //Initialize inPriorityQueue
            inPriorityQueue[i] = true;

            key[i] = Integer.MAX_VALUE;
        }

        //Decrease key for the first index
        heapNodes[0].key = 0;

        //Add all vertices to the priority queue
        PriorityQueue<HeapNode> pq = new PriorityQueue<>(vertices, new Comparator<HeapNode>() {

            @Override
            public int compare(HeapNode o1, HeapNode o2) {
                return o1.key - o2.key;
            }
        });

        //Add all the vertices to priority queue
        for (int i = 0; i < vertices; i++) {
            pq.offer(heapNodes[i]);
        }

        //while priority queue is not empty
        while(!pq.isEmpty()) {

            //Extract the min
            HeapNode extractedMinNode = pq.poll();

            //Extracted vertex
            int extractedVertex = extractedMinNode.vertex;
            inPriorityQueue[extractedVertex] = false;

            //Iterate through all the adjacent vertices
            LinkedList<Edge> list = graphList[extractedVertex];

            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);

                //Only if the edge.destination is present in heap
                if (inPriorityQueue[edge.destination]) {

                    int destination = edge.destination;
                    int newKey = edge.weight;

                    //Check if newKey < then existing key , if yes update it in priority queue and key[]
                    if (newKey < key[destination]) {

                        decreaseKey(pq, newKey, destination);

                        //Update the parent node for destination
                        resultSets[destination].parent = extractedVertex;
                        resultSets[destination].weight = newKey;
                        key[destination] = newKey;
                    }
                }
            }
        }

        //Print MST
        printMST(resultSets);
    }

    private static void decreaseKey(PriorityQueue<HeapNode> pq, int newKey, int vertex) {

        //Iterate through nodes in priority queue and update the key for the vertex
        Iterator it = pq.iterator();

        while(it.hasNext()) {

            HeapNode heapNode = (HeapNode) it.next();

            if (heapNode.vertex == vertex) {

                pq.remove(heapNode);
                heapNode.key = newKey;
                pq.offer(heapNode);
                break;
            }
        }
    }

    private static void printMST(ResultSet[] resultSets) {

        int totalMinWeight = 0;
        System.out.println("Minimum Spanning Tree: ");
        for (int i = 1; i < vertices; i++) {

            System.out.println("Edge: " + i + " - " + resultSets[i].parent + " Weight: " + resultSets[i].weight);
            totalMinWeight += resultSets[i].weight;
        }
        System.out.println("Total minimum key: " + totalMinWeight);
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
