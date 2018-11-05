import java.util.LinkedList;

/**
 * Prim's Minimum Spanning Tree (MST) using Adjacency List and Min Heap
 *
 * resources/PrimsAlgorithmMST.png
 *
 * Implementation - Adjacency List and Min Heap
 *
 * 1) Create min Heap of size = no of vertices.
 * 2) Create heapNode for each vertex which will store two information
 *    a) vertex
 *    b) key
 * 3) Use inHeap[] to keep track of vertices which are currently in min heap.
 * 4) Create key[] to keep track of key weight for each vertex.
 * 5) For each heapNode, initialize key as Integer.MAX_VALUE except the heapNode for first vertex for which key will be 0.
 * 6) Insert all the heapNodes into min heap.
 * 7) inHeap[v] = true for all vertices
 * 8) while minHeap is not empty
 *    1) Extract the min node
 *    2) Decrease key: Iterate through all the adjacent vertices of above vertex u and if adjacent vertex is still part of inHeap[]
 *       and key of vertex v > u-v weight then key of vertex v = u-v weight
 * 9) We will use Result object to store the result of each vertex.
 *    Result object will store 2 informations
 *    1) Parent vertex - from which vertex you can visit this vertex. If for vertex v, you have included edge u-v then vertex u will
 *       be the parent vertex.
 *    2) Weight edge u-v. If you all all these weights for all the vertices then you will get MST weight.
 *
 * Time Complexity
 *
 * Total vertices = V, Total Edges = E
 *
 * O(logV) - to extract each vertex from queue. So for V vertices = O(VlogV)
 * O(logV) - Each time decrease key value of a vertex. Decrease key will be called for at most once for each edge.
 *           So for total E edges = O(ElogV)
 * Overall time complexity = O(VlogV) + O(ElogV) = O(E+V)logV = O(ElogV)
 */
public class PrimsMSTUsingAdjacencyListAndMinHeap {

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

        //Initialize graphList for all the vertices
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

    private static void primMSTUsingAdjacencyListMinHeap() {

        boolean[] inHeap = new boolean[vertices];
        ResultSet[] resultSets = new ResultSet[vertices];

        //keys[] used to store the weight to know whether min heap update is required
        int[] key = new int[vertices];

        //Create heapNodes for all the vertices
        HeapNode[] heapNodes = new HeapNode[vertices];

        //Initialize everything
        for (int i = 0; i < vertices; i++) {

            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].key = Integer.MAX_VALUE;

            resultSets[i] = new ResultSet();
            resultSets[i].parent = -1;

            inHeap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        //decrease key for first vertex
        heapNodes[0].key = 0;

        //Add all the vertices to the Min Heap
        MinHeap minHeap = new MinHeap(vertices);

        for (int i = 0; i < vertices; i++) {
            minHeap.insert(heapNodes[i]);
        }

        //while minHeap is not empty
        while(!minHeap.isEmpty()) {

            //Extract the min node
            HeapNode extractedMinNode = minHeap.extractMin();

            //Extracted vertex
            int extractedMinVertex = extractedMinNode.vertex;

            inHeap[extractedMinVertex] = false;

            //Iterate through all the adjacent vertices
            LinkedList<Edge> adjacencyList = graphList[extractedMinVertex];

            for (int i = 0; i < adjacencyList.size(); i++) {

                Edge edge = adjacencyList.get(i);

                //Only if edge destination is present in minheap
                if (inHeap[edge.destination]) {

                    int destination = edge.destination;
                    int newKey = edge.weight;

                    //Check if newKey < existing key, if yes then update it
                    if (newKey < key[destination]) {

                        //Decrease key in minHeap
                        decreaseKey(minHeap, newKey, destination);

                        //Update the resultSet
                        resultSets[destination].parent = extractedMinVertex;
                        resultSets[destination].weight = newKey;
                        key[destination] = newKey;
                    }
                }
            }
        }

        //Print MST
        printMST(resultSets);
    }

    private static void decreaseKey(MinHeap minHeap, int newKey, int vertex) {

        //Get the index for the vertex whose key needs to be decreased
        int index = minHeap.indexes[vertex];

        //Get the node at that index
        HeapNode node = minHeap.mH[index];
        node.key = newKey;
        minHeap.bubbleUp(index);
    }

    private static void printMST(ResultSet[] resultSets) {

        int totalMinWeight = 0;
        System.out.println("Minimum Spanning Tree: ");

        for (int i = 1; i < vertices; i++) {

            System.out.println("Edge: " + i + " - " + resultSets[i].parent + " weight: " + resultSets[i].weight);
            totalMinWeight += resultSets[i].weight;
        }
        System.out.println("Total minimum weight: " + totalMinWeight);
    }

    private static class MinHeap {

        private static int capacity;
        private static int currentSize;
        private static HeapNode[] mH;
        private static int[] indexes; //Will be used to decrease key

        public MinHeap(int capacity) {

            this.capacity = capacity;
            mH = new HeapNode[capacity + 1];
            indexes = new int[capacity];

            mH[0] = new HeapNode();
            mH[0].vertex = -1;
            mH[0].key = Integer.MIN_VALUE;

            currentSize = 0;
        }

        private static void display() {

            for (int i = 0; i <= currentSize; i++) {
                System.out.println(mH[i].vertex + " Weight/Key: " + mH[i].key);
            }
            System.out.println("--------------------------------------");
        }

        private static void insert(HeapNode x) {

            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        private static void bubbleUp(int pos) {
            int parentIdx = pos/2;
            int currentIdx = pos;

            while(currentIdx > 0 && mH[parentIdx].key > mH[currentIdx].key) {
                HeapNode currentNode = mH[currentIdx];
                HeapNode parentNode = mH[parentIdx];

                //Swap positions and indexes
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;

                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx/2;
            }
        }

        private static HeapNode extractMin() {
            HeapNode min = mH[1];

            HeapNode lastNode = mH[currentSize];

            //Update the indexes[] and move last node to the top and perform sinkDown
            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            sinkDown(1);
            currentSize--;
            return min;
        }

        private static void sinkDown(int k) {

            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k + 1;

            if (leftChildIdx < heapSize() && mH[smallest].key > mH[leftChildIdx].key) {
                smallest = leftChildIdx;
            }

            if (rightChildIdx < heapSize() && mH[smallest].key > mH[rightChildIdx].key) {
                smallest = rightChildIdx;
            }

            if (smallest != k) {

                HeapNode smallestNode = mH[smallest];
                HeapNode kNode = mH[k];

                //Swap the indexes and position and then perform sinkDown
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        private static void swap(int a, int b) {
            HeapNode temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        private boolean isEmpty() {
            return currentSize == 0;
        }

        private static int heapSize() {
            return  currentSize;
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        initializeGraph(6);
        addEdge(0, 1, 4);
        addEdge(0, 2, 3);
        addEdge(1, 2, 1);
        addEdge(1, 3, 2);
        addEdge(2, 3, 4);
        addEdge(3, 4, 2);
        addEdge(4, 5, 6);

        primMSTUsingAdjacencyListMinHeap();
    }
}
