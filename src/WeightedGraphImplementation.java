import java.util.LinkedList;

/**
 * Weighted Graph Implementation
 *
 * What is Weighted Graph?
 *
 * A Graph is called weighted graph when it has weighted edges which means there are some
 * cost associated with each edge in graph.
 *
 * resources/WeightedGraph.png
 *
 * Implementation:
 *
 * 1) Each edge of a graph has an associated numerical value called a weight.
 * 2) The edge weights are non negative numbers.
 * 3) Weighted Graph may be either directed or undirected.
 * 4) The weight of an edge is often referred to as the "cost of the edge"
 * 5) Create an Edge class and put weight on each edge.
 */
public class WeightedGraphImplementation {

    private static int vertices;
    private static LinkedList<Edge>[] graphList;

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

    private static void initializeGraph(int nodes) {

        vertices = nodes;
        graphList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination, int weight) {

        Edge edge = new Edge(source, destination, weight);
        graphList[source].addFirst(edge);
    }

    private static void printGraph() {

        for (int i = 0; i < vertices; i++) {

            LinkedList<Edge> adjacencyList = graphList[i];

            for (int j = 0; j < adjacencyList.size(); j++) {

                System.out.println("Vertex " + i + " is connected to " +
                adjacencyList.get(j).destination + " with weight " + adjacencyList.get(j).weight);
            }
        }
    }

    public static void main(String[] args) {

        int vertices = 6;

        initializeGraph(vertices);
        addEdge(0, 1, 4);
        addEdge(0, 2, 3);
        addEdge(1, 3, 2);
        addEdge(1, 2, 5);
        addEdge(2, 3, 7);
        addEdge(3, 4, 2);
        addEdge(4, 0, 4);
        addEdge(4, 1, 4);
        addEdge(4, 5, 6);

        printGraph();
    }
}
