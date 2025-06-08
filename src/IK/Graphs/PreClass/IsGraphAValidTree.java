package IK.Graphs.PreClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an undirected graph, find out whether it is a tree.
 *
 * The undirected edges are given by two arrays edge_start and edge_end of equal size. Edges of the given graph connect nodes edge_start[i] and edge_end[i] for all valid i.
 *
 * Example One
 * Graph 1
 *
 * {
 * "node_count": 4,
 * "edge_start": [0, 0, 0],
 * "edge_end": [1, 2, 3]
 * }
 * Output:
 *
 * 1
 * This graph is a tree because all the nodes are connected, and it does not have cycles.
 *
 * Example Two
 * Graph 2
 *
 * {
 * "node_count": 4,
 * "edge_start": [0, 0],
 * "edge_end": [1, 2]
 * }
 * Output:
 *
 * 0
 * This graph is not a tree because node 3 is not connected to the other nodes.
 *
 * Example Three
 * Graph 3
 *
 * {
 * "node_count": 4,
 * "edge_start": [0, 0, 1, 2],
 * "edge_end": [3, 1, 2, 0]
 * }
 * Output:
 *
 * 0
 * This graph is not a tree: nodes 0, 1 and 2 form a cycle.
 *
 * Example Four
 * Graph 4
 *
 * {
 * "node_count": 4,
 * "edge_start": [0, 0, 0, 1],
 * "edge_end": [1, 2, 3, 0]
 * }
 * Output:
 *
 * 0
 * This graph is not a tree because the two edges that connect nodes 0 and 1 form a cycle.
 *
 * Notes
 * A tree is an undirected connected acyclic graph.
 * Constraints:
 *
 * 1 <= number of nodes <= 105
 * 1 <= number of edges <= 105
 * 0 <= node value < number of nodes
 *
 * resources/GraphValidTree1.png
 * resources/GraphValidTree2.png
 * resources/GraphValidTree3.png
 * resources/GraphValidTree4.png
 *
 * For a graph to be a valid tree, the graph has to be connected and without cycle.
 *  The graph cannot have self-loops. Otherwise the graph will lack the 2nd property. Self loops(An edge connecting a node with itself) also creates a cycle.
 *
 * Approach
 *
 * 1) Initialize Graph
 * 2) Add edges
 * 3) Iterate all the vertices and if not visited do bfs or dfs
 * 4) Increment the count, if count > 1 then return false
 * 5) If BFS or DFS return true then return false
 * 6) Outside outer loop return true
 * 7) If BFS, create queue and add start vertex to queue, mark it visited.
 * 8) While queue is not empty, remove from queue.
 * 9) For all neighbors of node, if not visited, mark as visited, set parent and add to queue
 * 10) If already visited, check if neighbor is not parent of node, if it is not then return true because that is cross edge and there is cycle in graph
 * 11) Return false outside
 * 12) If DFS, mark start vertex visited.
 * 13) For all neighbors of start vertex, if it's not visited, set parent and make recursive call. If recursive call return true, return true
 * 14) Else if neighbor is not parent of start vertex, return true because that's back edge and there is cycle in graph.
 *
 * TC: O(V + E) or O(n + m) Graph construction and O(E) for BFS or DFS
 * SC: O(V + E) or O(n + m) for graph and O(V) for BFS or DFS
 *
 */
public class IsGraphAValidTree {

    private static int vertices;
    private static ArrayList<Integer>[] graph;

    private static void initializeGraph(int n) {
        vertices = n;
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void addEdge(int start, int end, boolean bidir) {
        graph[start].add(end);

        if(bidir) {
            graph[end].add(start);
        }
    }

    private static boolean isGraphValidTree(Integer n, ArrayList<Integer> start, ArrayList<Integer> end) {
        initializeGraph(n);

        for(int i = 0; i < start.size(); i++) {

            if(start.get(i).equals(end.get(i))) { //If the graph has self loops then it is not a valid tree
                return false;
            }
            addEdge(start.get(i), end.get(i), true);
        }

        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        int count = 0;

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                count++;

                if(count > 1) {
                    return false;
                }

                if(bfs(i, visited, parent)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean bfs(int start, boolean[] visited, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int v = queue.remove();

            for(int w: graph[v]) {
                if(!visited[w]) {
                    visited[w] = true;
                    queue.add(w);
                    parent[w] = v;
                } else if(w != parent[v]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int start, boolean[] visited, int[] parent) {
        visited[start] = true;

        for(int w: graph[start]) {
            if(!visited[w]) {
                parent[w] = start;
                if(dfs(w, visited, parent)) {
                    return true;
                }
            } else if (w != parent[start]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 4;
        ArrayList<Integer> start = new ArrayList<>();
        Collections.addAll(start, 0, 0, 0);

        ArrayList<Integer> end = new ArrayList<>();
        Collections.addAll(end, 1, 2, 3);

        System.out.println("Graph a valid tree " + isGraphValidTree(n, start, end));

        n = 4;
        start = new ArrayList<>();
        Collections.addAll(start, 0, 0);

        end = new ArrayList<>();
        Collections.addAll(end, 1, 2);

        System.out.println("Graph a valid tree " + isGraphValidTree(n, start, end));

        n = 4;
        start = new ArrayList<>();
        Collections.addAll(start, 0, 0, 0, 0);

        end = new ArrayList<>();
        Collections.addAll(end, 1, 2, 3, 0);

        System.out.println("Graph a valid tree " + isGraphValidTree(n, start, end));
    }
}
