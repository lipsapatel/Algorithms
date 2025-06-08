package IK.Graphs.Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Given directed acyclic graph with weights, find the longest path from source vertex to every other vertex
 *
 * 1) Longest path cannot be solved by using Dijkstra which we used for shortest path.
 * 2) Longest path problem don't have optimal substructure and is NP hard problem
 * 3) But using topological sort we can do it in linear time
 * 4) First construct graph
 * 5) Do DFS and find topological order
 * 6) Iterate topological order and find if we get larger distance
 * if(distance[u] + w > distance[v]) then update distance[v]
 * 7) Initialize distance array with Integer.MIN_VALUE and distance[src] = 0
 * 8) Return distance array
 *
 * TC: O(n + m) for Graph construction, DFS and find the longest path
 * SC: O(n + m) for graph, visited, topo order, distance
 */
public class LongestPathInDAGTopological {

    private static int vertices;
    private static HashMap<Integer, HashMap<Integer, Integer>> graph;

    private static void initializeGraph(int n) {
        vertices = n;

        graph = new HashMap<>(n);

        for(int i = 0; i < n; i++) {
            graph.put(i, new HashMap<>());
        }
    }

    private static void addEdge(int start, int end, int weight) {
        graph.get(start).put(end, weight);
    }

    private static int[] longestPathDAG(int n, int[][] edges, int src) {
        initializeGraph(n);

        for(int[] edge: edges) {
            addEdge(edge[0], edge[1], edge[2]);
        }

        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MIN_VALUE);
        distance[src] = 0;

        boolean[] visited = new boolean[n];
        ArrayList<Integer> topoOrder = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, topoOrder, visited);
            }
        }

        Collections.reverse(topoOrder);

        for(int u: topoOrder) {
            for(Map.Entry<Integer, Integer> entry: graph.get(u).entrySet()) {
                int v = entry.getKey();
                int w = entry.getValue();

                if(distance[u] + w > distance[v]) {
                    distance[v] = distance[u] + w;
                }
            }
        }
        return distance;

    }

    private static void dfs(int start, ArrayList<Integer> topoOrder, boolean[] visited) {
        visited[start] = true;

        for(Map.Entry<Integer, Integer> entry: graph.get(start).entrySet()) {

            int v = entry.getKey();

            if(!visited[v]) {
                dfs(v, topoOrder, visited);
            }
        }

        topoOrder.add(start);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 5}, {0, 2, 3}, {1, 3, 6}, {1, 2, 2}, {2, 4, 4}, {2, 5, 2}, {2, 3, 7}, {3, 5, 1}, {3, 4, -1}, {4, 5, -2}};
        int n = 6;
        int start = 1;

        System.out.println("The Longest path in DAG from source vertex is " + Arrays.toString(longestPathDAG(n, edges, start)));
    }

}
