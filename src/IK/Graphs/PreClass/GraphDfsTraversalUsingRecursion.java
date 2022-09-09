package IK.Graphs.PreClass;

import java.util.ArrayList;

/**
 * Approach
 * 1) Initialize graph
 * 2) Add edges
 * 3) Initialize visited and parent
 * 4) Iterate all vertices and if not visited do dfsExplore
 * 5) In dfsExplore, mark source vertex as visited and add to dfsList
 * 6) Iterate all neighbors of source vertex and if not visited, marked as visited and set parent.
 * 7) Make recursive call
 * 8) Return dfsList
 *
 * TC: O(V + E) Graph construction = O(n + m)
 * SC: O(V + E) Graph and O(V) recursive call stack space. = O(n + m)
 */
public class GraphDfsTraversalUsingRecursion {

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
        int[] parent = new int[n];
        ArrayList<Integer> dfsList = new ArrayList<>();

        for(int i = 0; i < n; i++) { //This is for connected components

            if(!visited[i]) {
                dfsExplore(i, dfsList, visited, parent);
            }
        }
        return dfsList;
    }

    private static void dfsExplore(int start, ArrayList<Integer> dfsList, boolean[] visited, int[] parent) {

        visited[start] = true;
        dfsList.add(start);

        for(int w: graph[start]) {
            if(!visited[w]) {
                parent[w] = start;
                dfsExplore(w, dfsList, visited, parent);
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
