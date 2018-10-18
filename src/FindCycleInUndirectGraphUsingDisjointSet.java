import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Graph - Find cycle in Undirected Graph using Disjoint Set (Union-Find)
 *
 * Given a graph, check if the graph contains a cycle using disjoint set.
 * Disjoint-set data structure, also called a union-find data structure or merge-find set.
 *
 * resources/FindCycleInUndirectedGraphUsingDisjointSet.png
 *
 * Approach:
 *
 * 1) The makeSet operation makes a new set by creating a new element with a parent pointer to
 * itself.
 * 2) Then process each edge of the graph and perform find and union operations to make subsets
 * using both the vertices of the edge.
 * 3) If find operation on both the vertices, returns the same parent (means both the vertices
 * belongs to the same subset) then cycle is detected.
 */
public class FindCycleInUndirectGraphUsingDisjointSet {

    private static class Edge {

        int source;
        int destination;

        public Edge(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    private static int vertices;
    private static LinkedList<Edge>[] graphList;
    private static ArrayList<Edge> allEdges = new ArrayList<>();

    private static void initializeGraph(int nodes) {

        vertices = nodes;
        graphList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination) {

        Edge edge = new Edge(source, destination);
        graphList[source].addFirst(edge);

        allEdges.add(edge);
    }

    private static void makeSet(int[] parent) {

        //Make Set - creating a new element with a parent pointer to itself
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    private static int find(int[] parent, int vertex) {

        //Chain of parent pointers from x to upwards through the tree
        //until an element is reached whose parent is itself
        if (parent[vertex] != vertex) {
            return find(parent, parent[vertex]);
        }

        return vertex;
    }

    private static void union(int[] parent, int x, int y) {

        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);

        //Make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }

    private static boolean hasCycle() {

        //Create a parent[]
        int[] parent = new int[vertices];

        //makeSet
        makeSet(parent);

        //Iterate through all the edges and keep making sets
        for (int i = 0; i < allEdges.size(); i++) {

            Edge edge = allEdges.get(i);

            int x_set = find(parent, edge.source);
            int y_set = find(parent, edge.destination);

            //check if the source and destination vertex belongs to the same set
            //If it is in the same set then cycle is detected else combine them into one set
            if (x_set == y_set) {
                return true;
            } else {
                //union(parent, x_set, y_set);
                //Make x as parent of y
                parent[y_set] = x_set;
            }

        }

        //If here, means cycle was not found
        return false;
    }

    public static void main(String[] args) {

        int vertices = 6;
        initializeGraph(vertices);

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(3, 4);
        addEdge(2, 3);
        addEdge(4, 5);

        System.out.println("Graph contains cycle: " + hasCycle());
    }
}
