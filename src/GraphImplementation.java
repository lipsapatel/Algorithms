import java.util.LinkedList;

/**
 * Graph implementation using Adjacency List.
 *
 * What is Graph?
 *
 * Graph = (V,E)
 *
 * Graph is a collection of nodes or vertices and edges between them. We can traverse
 * nodes using edges. Edges might be weighted or non-weighted.
 *
 * There are two kinds of Graph:
 * 1) Directed Graph - You can traverse only in specified direction between two nodes.
 * 2) Undirected Graph - You can traverse in either direction between two nodes.
 *
 * There are two ways to represent graph:
 * 1) Adjacency List
 * 2) Adjacency Matrix
 *
 * Adjacency List:
 *
 * Adjacency List is the array of linked list.
 * Array size is same as number of vertices in graph.
 * Each vertex has linked list
 * The weights can also be stored in linked list node.
 *
 * resources/UndirectedGraph.png
 */
public class GraphImplementation {

    private static int vertex;
    private static LinkedList<Integer> graph[];

    private static void initializeGraph(int nodes) {

        vertex = nodes;
        graph = new LinkedList[nodes];

        for (int i = 0; i < nodes; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    //Undirected graph can go in either direction
    private static void addEdge(int source, int destination) {

        graph[source].addFirst(destination);

        //Add backward edge for undirected
        graph[destination].addFirst(source);
    }

    private static void printGraph() {

        for (int i = 0; i < graph.length; i++) {

            if (graph[i].size() > 0) {

                System.out.print("Vertex " + i + " is connected to: ");

                for (int j = 0; j < graph[i].size(); j++) {

                    System.out.print(graph[i].get(j) + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        initializeGraph(5);
        addEdge(0,1);
        addEdge(0, 4);
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(2, 3);
        addEdge(3, 4);
        printGraph();
    }
}
