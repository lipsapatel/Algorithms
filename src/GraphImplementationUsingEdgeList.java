import java.util.ArrayList;

/**
 * Implement graph using edgeList.
 *
 * To perform operations like
 * 1) Find the adjacent nodes
 * 2) Check if given nodes are connected
 *
 * Time Complexity: O(|E|) because we have to scan the whole edgelist.
 *
 * Space Complexity = O(|E|)
 */
public class GraphImplementationUsingEdgeList {

    private static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    //private static int vertices;
    private static ArrayList<Edge> allEdges = new ArrayList<>();

    /*private static void initializeGraph(int nodes) {
        vertices = nodes;
    }*/

    private static void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        allEdges.add(edge);
    }

    private static void printGraph() {

        for (int i = 0; i < allEdges.size(); i++) {
            Edge edge = allEdges.get(i);
            System.out.println("Edge - " + i + " source: " + edge.source + " destination: " + edge.destination + " weight: " + edge.weight);
        }
    }

    public static void main(String[] args) {
        //int vertices = 6;
        //initializeGraph(vertices);
        addEdge(0, 1, 4);
        addEdge(0, 2, 3);
        addEdge(1, 2, 1);
        addEdge(1, 3, 2);
        addEdge(2, 3, 4);
        addEdge(3, 4, 2);
        addEdge(4, 5, 6);
        printGraph();
    }
}
