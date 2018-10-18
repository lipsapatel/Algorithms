import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Kruskal's Algorithm - Minimum Spanning Tree (MST)
 *
 * What is Kruskal Algorithm?
 *
 * 1) Kruskal's algorithm for finding the Minimum Spanning Tree (MST), which finds an edge of the least possible
 * weight that connects any two trees in the forest.
 * What this means is all vertices are connected by edge and if any edge is broken
 * 2) It is a greedy algorithm.
 * 3) It finds a subset of the edges that forms a tree that includes every vertex, hwere the total weight of all the edges in the tree
 * is minimized.
 * 4) If the graph is not connected, then if finds a minimum spanning forest ( a minimum spanning tree for each connected component)
 * 5) Number of edges in MST = V - 1 (V = no of vertices in Graph)
 *
 * resources/KruskalMinimumSpanningTree.png
 *
 * Approach:
 *
 * 1) Sort the edges in ascending order of weights.
 * 2) Pick the edge with the least weight. Check if including this edge in spanning tree will form a cycle.
 * If yes the ignore it. If no then add it to the spanning tree
 * 3) Repeat step 2 till spanning tree has v - 1 edges.
 * 4) Spanning tree with least weight will be formed, called Minimum Spanning Tree.
 */
public class KruskalMST {

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

    private static int vertices;
    private static ArrayList<Edge> allEdges = new ArrayList<>();

    private static void initializeGraph(int nodes) {
        vertices = nodes;
    }

    private static void addEdge(int source, int destination, int weight) {

        Edge edge = new Edge(source, destination, weight);
        allEdges.add(edge);
    }

    private static void kruskalMST() {

        //Sort the edges by weight using priority queue
        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), new Comparator<Edge>() {

            @Override
            public int compare(Edge edge1, Edge edge2) {
                return edge1.weight - edge2.weight;
            }
        });

        //Add all the edges to priority queue
        for (int i = 0; i < allEdges.size(); i++) {
            pq.add(allEdges.get(i));
        }

        //Create parent
        int[] parent = new int[vertices];

        makeSet(parent);

        ArrayList<Edge> mst = new ArrayList<>();

        //Process vertices
        for (int i = 0; i < allEdges.size(); i++) {

            Edge edge = pq.remove();

            //Find parent
            int x_set = findParent(parent, edge.source);
            int y_set = findParent(parent, edge.destination);

            if (x_set == y_set) {
                //Cycle so ignore this edge
            } else {
                //Add to mst
                mst.add(edge);

                //Do union - Make x as parent of y
                parent[y_set] = x_set;
            }
        }

        //Print MST
        System.out.println("Minimum Spanning Tree: ");
        printGraph(mst);
    }

    private static void makeSet(int[] parent) {
        //Make set - create a new element with a parent pointer to itself.
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    private static int findParent(int[] parent, int vertex) {

        //Chain of parent pointers from vertex to upwards through the tree until
        //an element is reached whose parent is itself
        if (parent[vertex] != vertex) {
            return findParent(parent, parent[vertex]);
        }

        return vertex;
    }

    private static void printGraph(ArrayList<Edge> edgeList) {

        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);

            System.out.println("Edge - " + i + " source: " + edge.source + " destination: " + edge.destination + " weight: " + edge.weight);
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        initializeGraph(vertices);
        addEdge(0, 1, 4);
        addEdge(0, 2, 3);
        addEdge(1, 2, 1);
        addEdge(1, 3, 2);
        addEdge(2, 3, 4);
        addEdge(3, 4, 2);
        addEdge(4, 5, 6);
        kruskalMST();
    }
}
