package IK.Graphs.Optional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Shortest Path Algorithm
 * Dijkstra algorithm is used to find single source shortest path
 *
 * You can find shortest path from single source to all vertices.
 * This algorithm can be applied to directed as well as undirected graph
 *
 * 0) Construct graph
 * 1) Here the edges have positive cost or weight
 * 2) We will use priority queue, distance and captured.
 * 3) Start the source vertex.
 * 4) Add source vertex to priority queue
 * 5) Remove from priority queue while its not empty
 * 6) Add to list of captured and add distance to distance array
 * 7) For the node that is removed from priority queue, if that node is already captured then continue
 * 8) For all neighbors of that node, if not already captured, add to priority queue where distance = cost of that neighbor + distance[node]
 *
 * TC: O(mlogm) - To insert and remove from priority queue
 *  O(m + n) for graph construction
 *
 *  m = n^2 so O(mlogn^2) = 2mlogn and if graph is sparse then m = n so nlogn
 *
 *  SC: O(m) for priority queue and O(m + n) for graph
 */
public class DijkstraShortestPath {

    private static HashMap<Integer, HashMap<Integer, Integer>> graph;
    private static int vertices;

    private static void initializeGraph(int n) {
        vertices = n;
        graph = new HashMap<>(n);

        for(int i = 0; i < n; i++) {
            graph.put(i, new HashMap<>());
        }
    }

    private static void addEdge(int start, int end, int cost) {
        graph.get(start).put(end, cost);
        graph.get(end).put(start, cost);
    }

    private static int[] dijkstra(int source, int n, int[][] edges) {
        initializeGraph(n);

        for(int[] edge: edges) {
            addEdge(edge[0], edge[1], edge[2]);
        }

        int[] distance = new int[n];
        Arrays.fill(distance, -1);

        boolean[] captured = new boolean[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        int[] item = {source, 0};
        pq.add(item);

        while(!pq.isEmpty()) {
            int[] node = pq.remove();

            if(captured[node[0]]) {
                continue;
            }

            captured[node[0]] = true;
            distance[node[0]] = node[1];

            for(Map.Entry<Integer, Integer> entry: graph.get(node[0]).entrySet()) {
                if(!captured[entry.getKey()]) {
                    int[] vertex = {entry.getKey(), entry.getValue() + distance[node[0]]};
                    pq.add(vertex);
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1, 4}, {0, 2, 3}, {1, 2, 1}, {1, 3, 2}, {2, 3, 4}, {3, 4, 2}, {4, 5, 6}};

        int[] distance = dijkstra(0, n, edges);

        System.out.println(Arrays.toString(distance));
    }
}
