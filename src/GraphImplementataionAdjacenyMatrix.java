/**
 * Graph implementation - Adjacency Matrix
 *
 * What is Graph?
 *
 * Graph(V, E) - is a collection of nodes or vertices and edges between them.
 * We can traverse the nodes using edges.
 * These edges might be weighted or non-weighted.
 *
 * Adjacency Matrix:
 * Adjacency Matrix is a two-dimensional array which has size VXV where V is the number
 * of vertices.
 *
 * resources/GraphImplementationAdjacencyMatrix.png
 *
 * Approach
 *
 * 1) matrix[i][j] = 1 if there is edge between vertex i and j or else 0
 * 2) Easy to implement because removing and adding edge take O(1) time
 * 3) Drawback - Takes O(v2) space even though there are less edges.
 *
 * Find all adjacent nodes - O(V) because we need to scan v columns.
 * Find if two nodes are connected - O(1)
 *
 */
public class GraphImplementataionAdjacenyMatrix {

    private static int vertex;
    private static int[][] matrix;

    private static void initializeGraph(int vertexNumber) {
        vertex = vertexNumber;
        matrix = new int[vertex][vertex];
    }

    private static void addEdge(int source, int destination) {

        //add edge for directed graph
        matrix[source][destination] = 1;

        //add back edge for undirected graph
        matrix[destination][source] = 1;
    }

    private static void printGraph() {

        System.out.println("Graph using Adjacency Matrix");

        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < vertex; i++) {
            System.out.print("Vertex " + i + " is connected to: ");

            for (int j = 0; j < vertex; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        initializeGraph(5);
        addEdge(0, 1);
        addEdge(0, 4);
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(2, 3);
        addEdge(3, 4);
        printGraph();
    }
}
