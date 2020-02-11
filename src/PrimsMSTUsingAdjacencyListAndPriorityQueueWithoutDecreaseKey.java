import javafx.util.Pair;

import java.util.*;

/**
 * What is MST?
 * V vertices are connected using V - 1 edges where sum of weight of all edges is minimum.
 *
 * Prim's Minimum Spanning Tree using Adjacency List and Priority Queue without decrease key
 * O(ElogV)
 *
 * resources/PrimsAlgorithmMST.png
 * resources/PrimsAlgorithm.jpg
 *
 * First we saw implementation using adjacency matrix which was easy to implement but complexity was O(V^2)
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
 * Implementation is same as Dijkstra, except the edge relaxation condition.
 *
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
 *    2) For adjacent vertex v, if v is not in exhausted[] and distance[v] > edge u-v weight then update
 *       distance[v] = edge u-v weight and add it to the priority queue.
 *
 * 1) Find the closest unexhausted node
 * 2) Update the distance for it's neighbours
 * 3) Update the priority queue and distance map
 * 4) Mark the node as exhausted.
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

    private static HashMap<Integer, HashSet<Pair<Integer, Integer>>> graph = new HashMap<>();

    private static void primMST(int source) {

        //This is just for printing result - need to preserve insertion order
        List<Integer> MSTOrder = new ArrayList<>();

        HashMap<Integer, Integer> weight = new HashMap<>(); //vertex, weight

        for(int vertex: graph.keySet()) {
            weight.put(vertex, Integer.MAX_VALUE);
        }
        weight.put(source, 0);

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {

            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                return p1.getValue() - p2.getValue();
            }
        });

        HashSet<Integer> exhaustedMST = new HashSet<>();

        pq.offer(new Pair<>(source, 0));

        while(!pq.isEmpty()) {

            Pair<Integer, Integer> extractedPair = pq.poll();
            int extractedVertex = extractedPair.getKey();

            for(Pair<Integer, Integer> neighbor: graph.get(extractedVertex)) {

                if(!exhaustedMST.contains(neighbor.getKey())) {

                    if(neighbor.getValue() < weight.get(neighbor.getKey())) {
                        weight.put(neighbor.getKey(), neighbor.getValue());
                        pq.offer(new Pair<>(neighbor.getKey(), neighbor.getValue()));
                    }
                }
            }

            exhaustedMST.add(extractedVertex);
            //For printing
            if(!MSTOrder.contains(extractedVertex)) {
                MSTOrder.add(extractedVertex);
            }
        }

        //PrintMST
        for(Integer vertex: MSTOrder) {
            System.out.print(vertex + "-->");
        }
        System.out.println();

        System.out.print("Total weight of minimum spanning tree is: ");
        int totalWeight = 0;
        for(Integer w: weight.values()) {
            totalWeight += w;
        }
        System.out.println(totalWeight);
    }

    public static void main(String[] args) {
        HashSet<Pair<Integer, Integer>> neighbor = new HashSet<>();
        Pair<Integer, Integer> pair = new Pair<>(1, 4);
        neighbor.add(pair);
        pair = new Pair<>(2, 3);
        neighbor.add(pair);
        graph.put(0, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(0, 4);
        neighbor.add(pair);
        pair = new Pair<>(2, 1);
        neighbor.add(pair);
        pair = new Pair<>(3, 2);
        neighbor.add(pair);
        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(0, 3);
        neighbor.add(pair);
        pair = new Pair<>(1, 1);
        neighbor.add(pair);
        pair = new Pair<>(3, 4);
        neighbor.add(pair);
        graph.put(2, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(1, 2);
        neighbor.add(pair);
        pair = new Pair<>(2, 4);
        neighbor.add(pair);
        pair = new Pair<>(4, 2);
        neighbor.add(pair);
        graph.put(3, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(3, 2);
        neighbor.add(pair);
        pair = new Pair<>(5, 6);
        neighbor.add(pair);
        graph.put(4, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(4,6);
        neighbor.add(pair);
        graph.put(5, neighbor);

        primMST(0);
    }
}
