import java.util.LinkedList;

/**
 * Graph - Print all paths between source and destination
 *
 * Given a graph, source vertex and destination vertex. Write an algorithm to
 * print all possible paths between source and destination.
 *
 * This problem also known as "Print all paths between two nodes"
 *
 * resources/PrintAllPathsBetweenSourceAndDestination.png
 *
 * Approach:
 *
 * 1) Use Depth first search.
 * 2) Start from the source vertex and visit the adjacent list.
 * 3) Looking carefully, the new problem is to find paths from the current vertex
 * to the destination. For instance as per the example, start from vertex 0 and visit the vertex
 * 1. Now all paths from vertex 1 to vertex 5 will be included in our final result.
 * So make a recursive call with source as vertex 1.
 * 4) Keep track of visited nodes to avoid cycles.
 * 5) Add current vertex to the result to keep track of path from source.
 * 6) Once reached the destination vertex, print the path.
 * 7) Now visit the next node in the adjacency list and repeat all the steps.
 *
 */
public class GraphPrintAllPathsBetweenSourceAndDestination {

    private static int vertices;
    private static LinkedList<Integer>[] graphList;

    private static void initializeGraph(int nodes) {

        vertices = nodes;
        graphList = new LinkedList[vertices];

        for(int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination) {

        graphList[source].addLast(destination);
    }

    private static void printAllPathsBetweenSourceAndDestination(int start, int end) {

        boolean[] visited = new boolean[vertices];
        visited[start] = true;
        printAllPaths(start, end, "", visited);
    }

    private static void printAllPaths(int start, int end, String path, boolean[] visited) {

        String newPath = path + "->" + start;
        visited[start] = true;

        LinkedList<Integer> adjacentList = graphList[start];

        for (int i = 0; i < adjacentList.size(); i++) {

            int destination = adjacentList.get(i);

            if (destination != end && visited[destination] == false) {

                printAllPaths(destination, end, newPath, visited);
            } else if(destination == end) {

                System.out.println(newPath + "->" + destination);
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

        printAllPathsBetweenSourceAndDestination(0, 5);
    }
}
