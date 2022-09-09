package IK.Graphs.PreClass;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Approach
 *
 * 1) Initialize Graph
 * 2) Add edge
 * 3) Initialize parent, captured and visited array
 * 4) Iterate all vertices and if not visited do exploreDfs
 * 5) Create stack and add source vertex to stack, mark it as visited.
 * 6) while stack is not empty, remove from stack and mark it captured and add to dfsList
 * 7) Iterate neighbors of s
 * 8) If they are not visited, mark them as visited, set parent to s, and add to stack
 * 9) Return dfsList
 *
 * resources/GraphBFSTraversal.png
 *
 * TC: O(V + E) or O(m + n) Iterating all vertices and its neighbors which is O(E)
 * SC: O(n) for stack and O(m + n) for graph
 */
public class GraphDFSTraversalUsingStack {

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

    private static ArrayList<Integer> dfsTraversal(int n, ArrayList<ArrayList<Integer>> edges) {
        initializeGraph(n);

        for(ArrayList<Integer> edge: edges) {
            addEdge(edge.get(0), edge.get(1), true);
        }

        boolean[] visited = new boolean[n];
        boolean[] captured = new boolean[n];
        ArrayList<Integer> dfsList = new ArrayList<>();
        int[] parent = new int[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                exploreDfs(i, dfsList, parent, captured, visited);
            }
        }
        return dfsList;
    }

    private static void exploreDfs(int start, ArrayList<Integer> dfsList, int[] parent, boolean[] captured, boolean[] visited) {

        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        visited[start] = true;

        while(!stack.isEmpty()) {
            int v = stack.pop();

            captured[v] = true;
            dfsList.add(v);

            for(int w: graph[v]) {
                if(!visited[w]) {
                    visited[w] = true;
                    parent[w] = v;
                    stack.add(w);
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

        System.out.println(dfsTraversal(n, edges));
    }
}
