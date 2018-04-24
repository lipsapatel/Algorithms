import java.util.LinkedList;

/**
 * Depth first search in disconnected graph
 *
 * Given a graph, in which one or more vertices are disconnected, do the depth first traversal.
 *
 * Earlier we have seen DFS where all the vertices in graph were connected.
 *
 * resources/DFSDisconnectedGraph.png
 *
 * Approach:
 *
 * 1) Maintain the visited[] to keep track of already visited vertices to avoid loops.
 * 2) Iterate through all the vertices, and for each vertex, make recursive call for all it's
 * adjacent vertices, where each vertices will act as source.
 *
 * Time Complexity: O(V + E)
 * V = number of vertices
 * E = number of edges *
 *
 */
public class DepthFirstSearchInDisconnectedGraph {

    private static int vertices;
    private static LinkedList<Integer>[] graphList;

    private static void initializeGraph(int nodes) {
        vertices = nodes;

        graphList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination) {
        graphList[source].addFirst(destination);
    }

    private static void dfsInDisconnectedGraph() {

        boolean[] visited = new boolean[vertices];
        System.out.println("Depth First Search: ");

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsRecursion(i, visited);
            }
        }
    }

    private static void dfsRecursion(int start, boolean[] visited) {

        visited[start] = true;
        System.out.print(start + " ");

        for (int i = 0; i < graphList[start].size(); i++) {

            int adjacentVertex = graphList[start].get(i);

            if (!visited[adjacentVertex]) {
                dfsRecursion(adjacentVertex, visited);
            }
        }
    }

    public static void main(String[] args) {

        int vertices = 7;
        initializeGraph(vertices);

        addEdge(1, 3);
        addEdge(2, 3);
        addEdge(0, 4);
        addEdge(4, 5);
        addEdge(5, 6);

        dfsInDisconnectedGraph();
    }
}
