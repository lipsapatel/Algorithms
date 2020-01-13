import javafx.util.Pair;
import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * Dijkstra's Shortest Path Algorithm (SPT) - Adjacency List and Priority Queue
 *
 * Implementing Dijkstra's Algorithm using adjacency list and priority queue.
 *
 * resources/DijkstraShortestPathAlgorithm.png
 * resources/DijkstraShortestPathPriorityQueue.png
 *
 * Approach:
 *
 * 1) Create priority queue of size = no of vertices
 * 2) Will create pair object for each vertex with two information, vertex and distance (similar to heap node)
 * 3) Override the Comparator interface of priority queue to sort them based on the value(which is distance)
 * 4) Use exhausted to keep track of vertices already visited
 * 5) Create distance map to keep track of distance for each vertex from source. Initialize all distances as
 *    Integer.MAX_VALUE except the source vertex for which distance will be 0.
 * 6) Create a pair object for vertex 0 with distance 0 and insert into priority queue.
 * 7) While priority queue is not empty
 *    1) Extract the min node from the priority queue, say it vertex u and add it to exhausted
 *    2) For adjacent vertex v, if v is not in exhausted[] and distance[v] > distance[u] + edge u-v weight then update
 *       distance[v] = distance[u] + edge u-v weight and add it to the priority queue.
 *
 * 1) Find the closest unexhausted node
 * 2) Update the distance for it's neighbours
 * 3) Update the priority queue and distance map
 * 4) Mark the node as exhausted.
 *
 * Time Complexity
 *
 * 1) O(logV) - to extract each vertex from queue. So for V vertices - O(VlogV)
 * 2) O(logV) - insert new pair for each vertex and it will be done for at most once for each edge.
 *    So for total E edge - O(ElogV)
 * 3) Overall time complexity = O(VlogV) + O(ElogV) = O((V+E)logV) = O(E+ V)*logV = O(ElogV)
 *
 * Insert in priority queue = logV and for E edges so TC = O(ElogV) + VlogV for extract = O(E + V)logV = O(ElogV)
 *
 */
public class DijkstraAdjacencyListPriorityQueue {

    private static HashMap<Integer, HashSet<Pair<Integer, Integer>>> graph = new HashMap<>();

    private static void addEdge(int source, int destination, int weight) {

        if (graph.containsKey(source)) {
            HashSet<Pair<Integer, Integer>> set = graph.get(source);
            Pair<Integer, Integer> pair = new Pair<>(destination, weight);
            set.add(pair);
            graph.put(source, set);
        } else {
            Pair<Integer, Integer> pair = new Pair<>(destination, weight);
            HashSet<Pair<Integer, Integer>> set = new HashSet<>();
            set.add(pair);
            graph.put(source, set);
        }
    }

    private static void dijkstra(int source) {

        HashMap<Integer, Integer> distance = new HashMap<>();

        for(Integer vertex: graph.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }
        distance.put(source, 0);

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {

            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                return p1.getValue() - p2.getValue();
            }
        });

        pq.offer(new Pair<>(source, 0));

        HashSet<Integer> exhausted = new HashSet<>();

        while(!pq.isEmpty()) {

            Pair<Integer, Integer> extractedPair = pq.poll();
            int extractedVertex = extractedPair.getKey();
            int extractedDistance = extractedPair.getValue();

            for(Pair<Integer, Integer> neighbor: graph.get(extractedVertex)) { //E - No of edges in worst case is V - 1

                if(!exhausted.contains(neighbor.getKey())) {

                    if (extractedDistance + neighbor.getValue() < distance.get(neighbor.getKey())) {

                        distance.put(neighbor.getKey(), extractedDistance + neighbor.getValue());
                        pq.offer(new Pair<>(neighbor.getKey(), extractedDistance + neighbor.getValue())); // logV
                    }
                }
            }
            exhausted.add(extractedVertex);
        }

        //Print distance HashMap
        for (Map.Entry<Integer, Integer> map: distance.entrySet()) {
            System.out.println("The distance of vertex " + map.getKey() + " from source is " + map.getValue());
        }
    }

    public static void main(String[] args) {

        addEdge(0, 1, 4);
        addEdge(0, 2, 3);
        addEdge(1, 2, 1);
        addEdge(1, 3, 2);
        addEdge(1, 0, 4);
        addEdge(2, 3, 4);
        addEdge(2, 0, 3);
        addEdge(2, 1, 1);
        addEdge(3, 4, 2);
        addEdge(3, 1, 2);
        addEdge(3, 2, 4);
        addEdge(4, 5, 6);
        addEdge(4, 3, 2);
        addEdge(5, 4, 6);
        dijkstra(0);
    }
}
