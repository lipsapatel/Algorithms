package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Mother Vertex
 * Find a mother vertex in a given directed graph.
 * A mother vertex is a vertex through which all the other nodes in the
 * graph can be reached by a directed path.
 *
 * Example One
 * Graph
 *
 * {
 * "n": 5,
 * "edges": [
 * [0, 4],
 * [1, 0],
 * [1, 3],
 * [4, 1],
 * [4, 2]
 * ]
 * }
 * Output:
 *
 * 0
 * Vertex 0 is the mother vertex. 1 can be reached using 0 -> 4 -> 1. 2 can be reached using 0 -> 4 -> 2. 3 can be reached using 0 -> 4 -> 1 -> 3. 4 can be reached using 0 -> 4. Note that vertices 1 and 4 are also mother vertices.
 *
 * Example Two
 * Graph
 *
 * {
 * "n": 3,
 * "edges": [
 * [1, 0],
 * [2, 0]
 * ]
 * }
 * Output:
 *
 * -1
 * Notes
 * The graph has n nodes, with each node having a distinct value from 0 to n - 1.
 * Edges are given as a list of pairs: a list of lists where each inner
 * list has exactly two elements. Each pair [X, Y] represents a directed edge
 * from X to Y.
 * Return any mother vertex in case multiple mother vertices exist and return -1 if no such vertex is present.
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * 0 <= number of edges <= 2 * 104
 * 0 <= value of each vertex <= n - 1
 * No self loop or duplicate edges are present.
 *
 * Approach
 *
 * 1) Mother vertex is the vertex from which we can reach all vertex
 * 2) If there are multiple mother vertex return any
 * 3) Do DFS with outer loop
 * 4) Do DFS once again to verify if the vertex is really mother vertex
 * 5) If there's no mother vertex, return -1
 *
 * TC: O(V + E) - for DFS
 * SC: O(V + E) - for graph, visited, recursive stack trace
 */
public class MotherVertex {

    private static int vertices;
    private static ArrayList<Integer>[] graph;

    private static void initializeGraph(int n) {
        vertices = n;
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void addEdge(int start, int end) {
        graph[start].add(end);
    }

    private static int findMotherVertex(int n, ArrayList<ArrayList<Integer>> edges) {
        initializeGraph(n);

        for(ArrayList<Integer> edge: edges) {
            addEdge(edge.get(0), edge.get(1));
        }

        boolean[] visited = new boolean[n];
        int motherVertex = -1;

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, visited);
                motherVertex = i;
            }
        }

        Arrays.fill(visited, false);
        if(motherVertex != -1) {
            dfs(motherVertex, visited);

            for(int i = 0; i < n; i++) {
                if(!visited[i]) {
                    motherVertex = -1;
                    break;
                }
            }
        }
        return motherVertex;
    }

    private static void dfs(int start, boolean[] visited) {
        visited[start] = true;

        for(int v: graph[start]) {
            if(!visited[v]) {
                dfs(v, visited);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        ArrayList<Integer> row = new ArrayList<>();
        row.add(0);
        row.add(4);

        edges.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(0);

        edges.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(3);

        edges.add(row);

        row = new ArrayList<>();
        row.add(4);
        row.add(1);

        edges.add(row);

        row = new ArrayList<>();
        row.add(4);
        row.add(2);

        edges.add(row);

        System.out.println("Mother Vertex is " +findMotherVertex(5, edges));
    }
}
