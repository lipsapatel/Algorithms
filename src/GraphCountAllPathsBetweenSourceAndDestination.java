import java.util.LinkedList;

/**
 * Graph - Count all paths between source and destination.
 *
 * Given a graph, source vertex and destination vertex, write an algorithm to count all
 * possible paths between source and destination.
 *
 * resources/GraphCountAllPathsBetweenSourceAndDestination.png
 *
 * Approach:
 *
 * 1) Use Depth first search.
 * 2) Start from the source vertex and visit the adjacent list.
 * 3) Looking carefully, the new problem is to find paths from the current vertex
 * to the destination. For instance as per the example, start from vertex 0 and visit
 * the vertex 1. Now all paths from vertex 1 to vertex 5 will be included in our final result.
 * So make a recursive call with source as vertex 1.
 * 4) Keep track of visited nodes to avoid cycles.
 * 5) Add current vertex to the result to keep track of path from source.
 * 6) Once reached the destination vertex, print the path
 * 7) Now visit the next node in the adjacency list and repeat all the steps.
 *
 */
public class GraphCountAllPathsBetweenSourceAndDestination {

    private static int vertices;
    private static int pathCount = 0;
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

    private static void countAllPathsBetweenSourceAndDestination(int start, int end) {

        boolean[] visited = new boolean[vertices];
        pathCount = 0;
        visited[start] = true;

        countAllPaths(start, end, visited);

        System.out.println("No of paths between source: " +  start
                + " and destination: " +  end + " are: " + pathCount);
    }

    private static void countAllPaths(int start, int end, boolean[] visited) {

        visited[start] = true;

        LinkedList<Integer> adjacentList = graphList[start];

        for (int i = 0; i < adjacentList.size(); i++) {

            int destination = adjacentList.get(i);

            if (destination != end && visited[destination] == false) {

                countAllPaths(destination, end, visited);

            } else if (destination == end) {

                pathCount++;
            }
        }

        //Remove to start again
        visited[start] = false;
    }

    public static void main(String[] args) {

        int vertices = 6;
        initializeGraph(vertices);

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(3, 4);
        addEdge(2, 3);
        addEdge(4, 0);
        addEdge(4, 1);
        addEdge(4, 5);

        countAllPathsBetweenSourceAndDestination(0, 5);
    }
}
