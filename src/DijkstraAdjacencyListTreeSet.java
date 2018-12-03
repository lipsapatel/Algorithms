import javafx.util.Pair;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Dijkstra Algorithm Implementation using Adjacency List, Tree Set and Pair
 *
 * resources/DijkstraShortestPathAlgorithm.png
 *
 * Approach:
 *
 * 1) Create tree set of size = no of vertices
 * 2) Will create pair object for each vertex with two information, distance and vertex (similar to heap node)
 * 3) Override the comparator interface of TreeSet to sort them based on the key (which is distance)
 * 4) Use SPT[] to keep track of vertices which are currently in Shortest Path Tree (SPT)
 * 5) Create distance[] to keep track of distance for each vertex from source. Initialize all distances as
 * Integer.MAX_VALUE except the first vertex 0 with distance 0 and insert into TreeSet
 * 6) Create a pair object for vertex 0 with distance 0 and insert into Tree Set.
 * 7) While TreeSet is not empty
 *  1) Extract the min node from the Tree Set, say it vertex u and add it to SPT
 *  2) For adjacent vertex v, if v is not in SPT[] and distance[v] > distance[u] + edge u-v weight then update
 *      distance[v] = distance[u] + edge u-v weight and add it to Tree Set.
 *
 * Time Complexity
 *
 * 1) O(logV) - to extract each vertex from tree set. so for V vertices - O(VlogV)
 * 2) O(logV) - insert new pair for each vertex and it will be done for at most once for each edge
 * so for total E edge - O(ElogV)
 * 3) Overall time complexity = O(VlogV) + O(ElogV) = O((V + E)logV) = O(ElogV)
 */
public class DijkstraAdjacencyListTreeSet {

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

    private static class Graph {

        private static int vertices;
        private static LinkedList<Edge>[] graph;

        public Graph(int nodes) {

            this.vertices = nodes;
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

        public void dijkstraGetMinDistance(int sourceVertex) {

            boolean[] SPT = new boolean[vertices];

            //Distance used to store distance of vertex from source vertex
            int[] distance = new int[vertices];

            //Initialize all the distance to infinity
            for (int i = 0; i < vertices; i++) {
                distance[i] = Integer.MAX_VALUE;
            }

            //Initialize tree set and override the comparator interface to do sorting based on the keys(distance)
            TreeSet<Pair<Integer, Integer>> treeSet = new TreeSet<>(new Comparator<Pair<Integer, Integer>>() {

                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {

                    //Sort using distance values
                    int key1 = p1.getKey();
                    int key2 = p2.getKey();
                    return key1 - key2;
                }
            });

            //Create the pair for first index, 0 distance and 0 index
            distance[0] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(distance[0], 0);

            //Add to tree set
            treeSet.add(p0);

            //while tree set is not empty
            while (!treeSet.isEmpty()) {

                //Extract the min pair
                Pair<Integer, Integer> extractedPair = treeSet.pollFirst();

                //Extracted vertex
                int extractedVertex = extractedPair.getValue();

                //If the extracted vertex is not in SPT
                if (!SPT[extractedVertex]) {

                    SPT[extractedVertex] = true;

                    //Iterate through all the adjacent vertices and update the keys(distance)
                    LinkedList<Edge> list = graph[extractedVertex];

                    for (int i = 0; i < list.size(); i++) {

                        Edge edge = list.get(i);
                        int destination = edge.destination;

                        //Only if edge destination is not preset in spt
                        if (!SPT[destination]) {

                            //Check if distance needs to be updated or not
                            //Means check total weight from source to vertex v is less than the current distance value,
                            //If yes then update the distance
                            int newDistance = distance[extractedVertex] + edge.weight;
                            int currentDistance = distance[destination];

                            if (currentDistance > newDistance) {

                                Pair<Integer, Integer> p = new Pair<>(newDistance, destination);
                                treeSet.add(p);
                                distance[destination] = newDistance;
                            }
                        }
                    }
                }
            }
            printDijkstra(distance, sourceVertex);
        }

        public void printDijkstra(int[] distance, int sourceVertex) {

            System.out.println("Dijkstra Algorithm: Using Adjacency List and Tree Set: ");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i + " distance: " + distance[i]);
            }
        }

    }
    public static void main(String[] args) {

        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3,2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        graph.dijkstraGetMinDistance(0);
    }
}
