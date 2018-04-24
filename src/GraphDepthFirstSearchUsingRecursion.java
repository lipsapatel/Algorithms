import java.util.LinkedList;

/**
 * Graph Depth First Search Using Recursion
 *
 * Given a graph, do depth first traversal using recursion.
 * Earlier we have seen DFS using stack.
 * Here we will see how to do DFS using recursion.
 * Recursion also uses stack internally so more or less is the same.
 *
 * What is Depth First Traversal?
 * Depth First Search is an algorithm for traversing or searching tree or graph data structure.
 * One starts at the root (selecting some arbitrary node as root in case of graph) and explore
 * as far as possible before backtracking.
 *
 * resources/GraphDepthFirstTraversalUsingRecursion.png
 *
 * Approach:
 *
 * 1) Maintain visited to keep track of already visited vertices to avoid loops.
 * 2) Start from a source vertex and make recursive call for all adjacent vertex and in this
 * recursive call each adjacent vertex acts as source.
 *
 * Time Complexity: O(V + E)
 * V = number of vertices
 * E = number of Edges
 */
public class GraphDepthFirstSearchUsingRecursion {

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

    private static void printGraph() {

        for (int i = 0; i < vertices; i++) {
            if (graphList[i].size() > 0) {

                System.out.print("Vertex " + i + " is connected to: ");
                for (int j = 0; j < graphList[i].size(); j++) {
                    System.out.print(graphList[i].get(j) + " ");
                }
                System.out.println();
            }
        }
    }

    private static void DFSUsingRecursion(int startVertex) {

        boolean[] visisted = new boolean[vertices];
        dfs(startVertex, visisted);
    }

    private static void dfs(int start, boolean[] visited) {

        visited[start] = true;

        System.out.print(start + " ");
        for (int i = 0; i < graphList[start].size(); i++) {

            int adjacentVertex = graphList[start].get(i);

            if (!visited[adjacentVertex]) {
                dfs(adjacentVertex, visited);
            }
        }
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
        DFSUsingRecursion(0);
    }
}
