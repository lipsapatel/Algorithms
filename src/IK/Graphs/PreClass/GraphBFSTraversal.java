package IK.Graphs.PreClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS Traversal Of A Graph
 * Given an undirected graph, perform a Breadth-First Search Traversal on it.
 *
 * Example
 * Graph
 *
 * {
 * "n": 6,
 * "edges": [
 * [0, 1],
 * [0, 2],
 * [0, 4],
 * [2, 3]
 * ]
 * }
 * Output:
 *
 * [0, 1, 2, 4, 3, 5]
 * Below are some other following valid outputs if the BFS traversal starts from the vertex 0:
 *
 * [0, 1, 4, 2, 3, 5]
 * [0, 2, 1, 4, 3, 5]
 * [0, 2, 4, 1, 3, 5]
 * [0, 4, 1, 2, 3, 5]
 * [0, 4, 2, 1, 3, 5]
 * BFS starting from another node will also be considered valid.
 *
 * Notes
 * There are n nodes in the graph and each node has a distinct value from 0 to n-1.
 * Edges are represented in the form of a two-dimensional list where each inner list consists of two values [u, v]. This represents an undirected edge from node u to node v.
 * The list won't contain duplicate edges. That is, if [u, v] is present, then there will be no other occurrence of [u, v] or [v, u].
 * If multiple BFS traversals exist, you may return any.
 * Constraints:
 *
 * 1 <= n <= 104
 * 0 <= number of edges <= 104
 * 0 <= value of each vertex <= n - 1
 * No self loop is present.
 *
 * resources/GraphBFSTraversal.png
 *
 * Approach
 *
 * 1) Initialize graph
 * 2) Add edges
 * 3) Initialize boolean visited and captured array of size n
 * 4) Initialize int parent array of size n
 * 5) Create bfsList for result
 * 6) Iterate the vertices and if not visited, do BFS starting from that vertex.
 * 7) In BFS, create queue and add source vertex
 * 8) while queue is not empty, set capture of that vertex true and add it to BFSList
 * 9) Iterate all adjacent vertices of that vertex and if not visited, set visited true, set parent and add to queue.
 * 10) In the main bfsTraversal return bfsList.
 *
 * TC: O(V + E) or O(n + m) - for BFS and O(V + E) or O(n + m) - for graph construction
 * SC: O(V) or O(n) - for BFS queue and O(V + E) or O(n + m) - for graph construction.
 *
 */
public class GraphBFSTraversal {

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

    private static ArrayList<Integer> bfsTraversal(int n, ArrayList<ArrayList<Integer>> edges) {

        initializeGraph(n);

        for(ArrayList<Integer> edge: edges) {
            addEdge(edge.get(0), edge.get(1), true);
        }

        //Initialize visited and captured boolean array
        boolean[] visited = new boolean[n];
        boolean[] captured = new boolean[n];

        //Initialize parent
        int[] parent = new int[n];

        //Initialize result list
        ArrayList<Integer> bfsList = new ArrayList<>();

        for(int i = 0; i < n; i++) {

            if(!visited[i]) {
                exploreBfs(i, bfsList, visited, captured, parent);
            }
        }
        return bfsList;
    }

    private static void exploreBfs(int s, ArrayList<Integer> bfsList, boolean[] visited, boolean[] captured, int[] parent) {

        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;

        while(!queue.isEmpty()) {
            int v = queue.remove();

            captured[v] = true;
            bfsList.add(v);

            //All neighbors
            for(int w: graph[v]) {

                if(!visited[w]) {
                    visited[w] = true;
                    parent[w] = v;
                    queue.add(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(0);
        edge.add(1);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(0);
        edge.add(2);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(0);
        edge.add(4);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(3);

        edges.add(edge);

        System.out.println(bfsTraversal(n, edges));
    }
}
