import java.util.LinkedList;

/**
 * Detect cycle in a directed graph
 *
 * Given a directed graph, write an algorithm to detect if the graph contains cycle or not.
 *
 * resources/CyclesInDirectedGraph.png
 *
 * Approach:
 *
 * 1) Graph contains cycle if there is any back edge.
 * 2) There are two types of back edge:
 *    --> Edge from vertex to itself - self loop
 *    --> Edge from any descendant back to vertex
 *
 * 3) Use Depth First Search to detect back edge.
 * 4) Do DFS from each vertex.
 * 5) Keep the track of visiting vertices in recursionArray.
 * 6) If you encounter the vertex already present in recursionArray then there is a cycle.
 * 7) Use visited to keep the track of already visited nodes.
 *
 * Difference between recursionArray and visited:
 *
 * 1) visited - keeps the track of already visited vertices.
 * 2) recursionArray - is true if there's cycle from this vertex or else it's reset to false.
 *
 * Time Complexity: O(V+E)
 */
public class DetectCycleInDirectedGraph {

    public static int vertices;
    public static LinkedList<Integer>[] graphList;

    public static void initializeGraph(int nodes) {

        vertices = nodes;
        graphList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {

            graphList[i] = new LinkedList<>();
        }
    }

    public static void addEdge(int source, int destination) {
        graphList[source].addFirst(destination);
    }

    public static boolean hasCycle() {

        boolean[] visited = new boolean[vertices];
        boolean[] recursionArray = new boolean[vertices];

        //Start DFS from each vertex
        for (int i = 0; i < vertices; i++) {

            if (hasCycleUtil(i, visited, recursionArray)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasCycleUtil(int vertex, boolean[] visited, boolean[] recursionArray) {

        //Set both of them to true, reset recursionArray if no cycle found
        visited[vertex] = true;
        recursionArray[vertex] = true;

        for (int i = 0; i < graphList[vertex].size(); i++) {

            //Get adjacent vertex
            int adjacentVertex = graphList[vertex].get(i);

            //Check if there's any cycle
            if (!visited[adjacentVertex] && hasCycleUtil(adjacentVertex, visited, recursionArray)) {
                return true;
            } else if (recursionArray[adjacentVertex]) { //if true then has cycle
                return true;
            }
        }

        //No cycle found from this vertex so reset the recursionArray
        recursionArray[vertex] = false;
        return false;

    }

    public static void main(String[] args) {
        int vertices = 4;
        initializeGraph(vertices);
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 1);

        boolean result = hasCycle();
        System.out.println("is Cycle present: " + result);
    }

}
