import java.util.LinkedList;

/**
 * Graph - Detect cycle in Undirected Graph using DFS
 *
 * Given undirected graph, write an algorithm to find out whether graph contains cycle or not.
 *
 * resources/CycleInUndirectedGraph.png
 *
 * Use DFS
 *
 * Approach:
 *
 * 1) Do DFS from every vertex
 * 2) During DFS, for any current vertex x if there is an adjacent vertex y is present
 * which is already visited and not a direct parent of x then there is a cycle in graph.
 * 3)
 * Why not parent
 *
 * Lets assume vertex x and y and we have edge between them x -- y
 * Now do DFS from x, once you reach y, will do DFS from y and adjacent vertex is x and since
 * it's already visited there should be cycle but actually there is no cycle since x
 * is a parent of y. That's why we will ignore visited vertex if it's parent of current vertex.
 *
 */
public class DetectCycleInUndirectedGraph {

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

    private static boolean hasCycle() {

        boolean[] visited = new boolean[vertices];

        //Do DFS from each vertex
        for(int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (hasCycleUtil(i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasCycleUtil(int currentVertex, boolean[] visited, int parent) {

        //Add this vertex to visited vertex
        visited[currentVertex] = true;

        //Visit neighbors except direct parent
        for (int i = 0; i < graphList[currentVertex].size(); i++) {

            int adjacentVertex = graphList[currentVertex].get(i);

            //Check if adjacentVertex is parent
            if (adjacentVertex != parent) {

                if (visited[adjacentVertex]) {

                    //It's not a parent and visited so there's a cycle
                    return true;
                } else {

                    //Recursion to other adjacent vertex
                    if (hasCycleUtil(adjacentVertex, visited, currentVertex)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int vertices = 6;
        initializeGraph(vertices);
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 4);
        addEdge(4, 5);
        addEdge(5, 2);
        boolean result = hasCycle();
        System.out.println("is Cycle present: " + result);
    }
}
