import java.util.LinkedList;

/**
 * Graph - Find the number of non-reachable vertices from a given vertex.
 *
 * Given undirected graph, write an algorithm to count the number of non reachable
 * vertices from a given vertex.
 *
 * resources/GraphFindNumberOfNonReachableVertices.png
 *
 * Approach:
 *
 * 1) Do DFS
 * 2) Do the Depth First Search from the given vertex A, Recursively do the DFS from
 * all the adjacent vertices of given vertex A.
 * 3) Use the visited array to keep track of visited vertices.
 * 4) Once DFS is completed, count the number of false in visited array. These are
 * the vertices which did not get visited.
 */
public class GraphFindNumberOfNonReachableVertices {

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
        graphList[destination].addFirst(source);
    }

    private static int countNonReachableVertices(int vertex) {

        boolean[] visited = new boolean[vertices];

        //Do DFS from the given vertex
        dfs(vertex, visited);

        //Count the number of non reachable vertices
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                count++;
            }
        }

        return count;
    }

    private static void dfs(int start, boolean[] visited) {

        visited[start] = true;

        for (int i = 0; i < graphList[start].size(); i++) {

            int adjacentVertex = graphList[start].get(i);

            if (!visited[adjacentVertex]) {
                dfs(adjacentVertex, visited);
            }
        }
    }

    public static void main(String[] args) {

        int vertices = 8;
        initializeGraph(8);

        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 1);
        addEdge(4, 5);
        addEdge(5, 6);

        int nonReachableVertices = countNonReachableVertices(0);
        System.out.println("Number of non reachable vertices from vertex 0 are: " + nonReachableVertices);

        nonReachableVertices = countNonReachableVertices(5);
        System.out.println("Number of non reachable vertices from vertex 5 are: " + nonReachableVertices);

    }
}
