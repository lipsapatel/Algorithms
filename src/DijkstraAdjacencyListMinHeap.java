import java.util.LinkedList;

/**
 * Dikjstra's Shortest Path Algorithm (SPT) Adjacency List and Min Heap
 *
 * Earlier we have seen the basics of Dijkstra Algorithm. In this article we will see its implementation
 * using adjacency list and Min Heap
 *
 * resources/DijkstraShortestPathAlgorithm.png
 *
 * Adjacency List and Min Heap
 *
 * 1) Create min Heap of size = no of vertices
 * 2) Create a headNode for each vertex which will store two information
 *      1) vertex 2) Distance for vertex from source vertex
 * 3) Use SPT[] to keep track of the vertices which are currently in min heap
 * 4) For each heapNode, initialize distance as infinity except the heapNode for the source vertex
 * for which distance will be 0.
 * 5) While min Heap is not empty
 *
 *      1) Extract the min node from the heap, say it vertex u, and add it to SPT
 *      2) Decrease the distance: For adjacent vertex v, if v is not in SPT[] and distance[v] > distance[u]
 *       + edge (u-v) weight then update distance[v] = distance[u] + edge u-v weight
 *
 * Time Complexity:
 *
 * O(logV) - to extract each vertex from heal. So for V vertices - O(Vlogv)
 * O(logV) - each time decrease the distance of a vertex. Decrease distance will be called for
 * at most once for each edge. So for total E edge - O(ElogV)
 * So overall time complexity:  O(VlogV) + O(ElogV) = O((E + V)logV) = O(ElogV)
 */
public class DijkstraAdjacencyListMinHeap {

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
        int distance;

    }

    private static class Graph {

        public static int vertices;
        LinkedList<Edge>[] graph;

        public Graph(int vertices) {
            this.vertices = vertices;
            graph = new LinkedList[vertices];

            //Initialize graph for all the vertices
            for (int i = 0; i < vertices; i++) {
                graph[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {

            Edge edge = new Edge(source, destination, weight);
            graph[source].addFirst(edge);

            edge = new Edge(destination, source, weight);
            graph[destination].addFirst(edge);
        }

        public void dijkstraGetMinimumDistances(int sourceVertex) {
            int INFINITY = Integer.MAX_VALUE;
            boolean[] SPT = new boolean[vertices];

            //Create HeapNode for all the vertices
            HeapNode[] heapNodes = new HeapNode[vertices];

            for (int i = 0; i < vertices; i++) {
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].distance = INFINITY;
            }

            //Decrease the distance for the first vertex
            heapNodes[sourceVertex].distance = 0;

            //Add all the vertices to MinHeap
            MinHeap minHeap = new MinHeap(vertices);
            for (int i = 0; i < vertices; i++) {

                minHeap.insert(heapNodes[i]);
            }

            //While minHeap is not empty, extract the minimum
            while (!minHeap.isEmpty()) {

                //Extract the min node
                HeapNode extractedMinNode = minHeap.extractMin();

                //Extracted vertex
                int extractedMinVertex = extractedMinNode.vertex;
                SPT[extractedMinVertex] = true;

                //Iterate through all the adjacent vertices
                LinkedList<Edge> adjacencyList = graph[extractedMinVertex];

                for (int i = 0; i < adjacencyList.size(); i++) {

                    Edge edge = adjacencyList.get(i);

                    int destination = edge.destination;

                    //If the destination vertex is not present in SPT
                    if (SPT[destination] == false) {

                        //Check if the distance needs to be updated or not
                        //Check the total weight + distance is less than current distance, then update the current distance
                        int newDistance = heapNodes[extractedMinVertex].distance + edge.weight;
                        int currentDistance = heapNodes[destination].distance;

                        if (currentDistance > newDistance) {

                            //Decrease the distance
                            decreaseDistance(minHeap, newDistance, destination);
                            heapNodes[destination].distance = newDistance;

                        }
                    }
                }
            }

            printDijkstra(heapNodes, sourceVertex);
        }

        private static void printDijkstra(HeapNode[] resultSet, int sourceVertex) {

            System.out.println("Dijkstra Algorithm: (Adjacency List + Min Heap)");

            for (int i = 0; i < vertices; i++) {

                System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i + " Distance: " + resultSet[i].distance);
            }
        }

        private static void decreaseDistance(MinHeap minHeap, int newDistance, int vertex) {

            //Get the index whose distance needs to be decrease
            int index = minHeap.indexes[vertex];

            //Get the node and update it's value
            HeapNode node = minHeap.minHeap[index];
            node.distance = newDistance;
            minHeap.bubbleUp(index);
        }


    }

    private static class MinHeap {
        private static int size;
        private static int position;
        private static HeapNode[] minHeap;
        private static int[] indexes; //Vertex and corresponding position in min heap

        public MinHeap(int size) {

            this.size = size;
            minHeap = new HeapNode[size + 1];
            indexes = new int[size];

            minHeap[0] = new HeapNode();
            minHeap[0].distance = Integer.MIN_VALUE;
            minHeap[0].vertex = -1;
            position = 0;
        }

        private static void displayMinHeap() {

            for (int i = 0; i <= size; i++) {
                System.out.println("Vertex: " + minHeap[i].vertex + " Distance: " + minHeap[i].distance);
            }
            System.out.println("------------");
        }

        public static void insert(HeapNode x) {

            position++;
            int idx = position;
            minHeap[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        private static void bubbleUp(int pos) {

            int parentPosition = pos/2;
            int currentPosition = pos;

            while(currentPosition > 0 && minHeap[parentPosition].distance > minHeap[currentPosition].distance) {

                HeapNode currentNode = minHeap[currentPosition];
                HeapNode parentNode = minHeap[parentPosition];

                //Swap the positions
                indexes[currentNode.vertex] = parentPosition;
                indexes[parentNode.vertex] = currentPosition;

                swap(currentPosition, parentPosition);

                currentPosition = parentPosition;
                parentPosition = parentPosition/2;
            }
        }

        private static void swap(int a, int b) {
            HeapNode temp = minHeap[a];
            minHeap[a] = minHeap[b];
            minHeap[b] = temp;
        }

        private static HeapNode extractMin() {

            HeapNode min = minHeap[1];
            HeapNode lastNode = minHeap[position];

            //Update the indexes and move the last node to the top
            //And perform sinkdown
            indexes[lastNode.vertex] = 1;
            minHeap[1] = lastNode;
            minHeap[position] = null;
            sinkDown(1);
            position--;
            return min;
        }

        private static void sinkDown(int elementPosition) {

            int smallesElementPosition = elementPosition;
            int leftChildPosition = 2 * elementPosition;
            int rightChildPosition = 2 * elementPosition + 1;

            if (leftChildPosition < position && minHeap[smallesElementPosition].distance > minHeap[leftChildPosition].distance) {
                smallesElementPosition = leftChildPosition;
            }

            if (rightChildPosition < position && minHeap[smallesElementPosition].distance > minHeap[rightChildPosition].distance) {
                smallesElementPosition = rightChildPosition;
            }

            if (smallesElementPosition != elementPosition) {

                HeapNode smallestNode = minHeap[smallesElementPosition];
                HeapNode elementNode = minHeap[elementPosition];

                //Swap the Positions and update the indexes
                indexes[smallestNode.vertex] = elementPosition;
                indexes[elementNode.vertex] = smallesElementPosition;

                swap(elementPosition, smallesElementPosition);
                sinkDown(smallesElementPosition);
            }
        }

        public static boolean isEmpty() {
            return position == 0;
        }
    }

    public static void main(String[] args) {

        int vertices = 6;
        Graph graph = new Graph(vertices);

        int sourceVertex = 0;
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);

        graph.dijkstraGetMinimumDistances(sourceVertex);
    }
}
