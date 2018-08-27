import javafx.util.Pair;
import sun.awt.image.ImageWatched;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Dijkstra's Shortest Path Algorithm (SPT) - Adjacency List and Priority Queue
 *
 * Implementing Dijkstra's Algorithm using adjacency list and priority queue.
 *
 * resources/DijkstraShortestPathAlgorithm.png
 *
 * Approach:
 *
 * 1) Create priority queue of size = no of vertices
 * 2) Will create pair object for each vertex with two information, distance and vertex (similar to heap node)
 * 3) Override the Comparator interface of priority queue to sort them based on the key(which is distance)
 * 4) Use SPT[] to keep track of vertices which are currently in Shortest Path Tree (SPT)
 * 5) Create distance[] to keep track of distance for each vertex from source. Initialize all distances as
 *    Integer.MAX_VALUE except the first vertex for which distance will be 0.
 * 6) Create a pair object for vertex 0 with distance 0 and insert into priority queue.
 * 7) While priority queue is not empty
 *    1) Extract the min node from the priority queue, say it vertex u and add it to SPT
 *    2) For adjacent vertex v, if v is not in SPT[] and distance[v] > distance[u] + edge u-v weight then update
 *       distance[v] = distance[u] + edge u-v weight and add it to the priority queue.
 *
 * Time Complexity
 *
 * 1) O(logV) - to extract each vertex from queue. So for V vertices - O(VlogV)
 * 2) O(logV) - insert new pair for each vertex and it will be done for at most once for each edge.
 *    So for total E edge - O(ElogV)
 * 3) Overall time complexity = O(VlogV) + O(ElogV) = O((V+E)logV) = O(ElogV)
 */
public class DijkstraAdjacencyListPriorityQueue {

    private static class Edge{

        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {

            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class Graph {

        private static int vertices;
        private static LinkedList<Edge>[] graph;

        public Graph(int vertices) {

            this.vertices = vertices;
            graph = new LinkedList[vertices];

            //Initialize graph for all the vertices
            for (int i = 0; i < vertices; i++) {
                graph[i] = new LinkedList<>();
            }
        }

        private static void addEdge(int source, int destination, int weight) {

            Edge edge = new Edge(source, destination, weight);
            graph[source].addFirst(edge);

            edge = new Edge(destination, source, weight);
            graph[destination].addFirst(edge);
        }

        private static void dijkstraGetMinDistance(int sourceVertex) {

            boolean[] SPT = new boolean[vertices];

            //Distance used to store distance of vertex from a source vertex
            int[] distance = new int[vertices];

            //Initialize all the distance to infinity
            for (int i = 0; i < vertices; i++) {

                distance[i] = Integer.MAX_VALUE;
            }

            //Initialize priority queue and override the comparator interface to do sorting based on keys(distance)
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {

                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {

                    //Sort using distance values
                    int key1 = p1.getKey();
                    int key2 = p2.getKey();
                    return key1 - key2;
                }
            });

            //Create pair for first index, distance = 0 and vertex = 0
            distance[0] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(distance[0], 0);

            //Add to pq
            pq.offer(p0);

            //While priority queue is not empty
            while(!pq.isEmpty()) {

                //Extract the min
                Pair<Integer, Integer> extractedPair = pq.poll();

                //Extracted vertex
                int extractedVertex = extractedPair.getValue();

                if (!SPT[extractedVertex]) {

                    SPT[extractedVertex] = true;

                    //Iterate through all the adjacent vertices and update the distance(keys)
                    LinkedList<Edge> adjacencyList = graph[extractedVertex];

                    for (int i = 0; i < adjacencyList.size(); i++) {

                        Edge edge = adjacencyList.get(i);

                        int destination = edge.destination;

                        //Only if destination edge is not present in SPT
                        if (!SPT[destination]) {

                            //Check if distance needs an update or not
                            //Means check if weight from source vertex to vertexv + distance < current distance value, if yes update the distance
                            int newDistance = distance[extractedVertex] + edge.weight;
                            int currentDistance = distance[destination];

                            if (currentDistance > newDistance) {

                                Pair<Integer, Integer> p = new Pair<>(newDistance, destination);
                                pq.offer(p);
                                distance[destination] = newDistance;
                            }
                        }
                    }
                }
            }

            //Print Shortest Path Tree
            printDijkstra(distance, sourceVertex);
        }

        private static void printDijkstra(int[] distance, int sourceVertex) {

            System.out.println("Dijkstra Algorithm: (Adjacency List and Priority Queue)");

            for (int i = 0; i < vertices; i++) {

                System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i + " Distance: " + distance[i]);
            }
        }
    }

    public static void main(String[] args) {

        int vertices = 6;
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        graph.dijkstraGetMinDistance(0);
    }
}
