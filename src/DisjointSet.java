import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Disjoint Set Data Structure - Union and Find algorithm
 *
 * What is Disjoint-set data structure?
 *
 * 1) Disjoint Set is a set whose intersection is empty
 * 2) A disjoint set data struture, also called Union-Find data structure or
 * Merge-Find data structure.
 * 3) It provides near-constant time operations to add new sets, to merge
 * existing sets, and to determine whether elements are in the same set.
 * 4) Plays a key role in Kruskal's Algorithm for finding the minimum spanning
 * tree of a graph.
 * 5) This can also be used to detect cycle in graph.
 *
 * How Disjoint Set is constructed:
 *
 * 1) A disjoint-set forest consists of a number of elements each of which
 * stores an id and parent pointer.
 * 2) If an element's parent pointer points to no other element, then that element
 * is the root of a tree and the representative member of its set.
 * 3) A set may consist of only a single element. However, if the element has a
 * parent, the element is part of whatever set is identified by following the chain
 * of parents upwards until a representative element (one without parent) is reached
 * at the root of the tree.
 *
 * resources/DisjointSet.png
 *
 * Disjoint Set Operations:
 *
 * MakeSet(X): This operation makes a new set by creating a new element with parent
 * pointer to itself. The parent pointer to itself indicates that the element is the
 * representative member of it's own set.
 *
 * makeSet operation has O(1) time complexity
 *
 * Find(x): Follows the chain of parent pointer from x in upwards direction
 * through tree until an element is reached whose parent is itself. This element
 * is the root of the tree and is the representative member of the set to which
 * x belongs.
 *
 * Union(x, y): Uses Find to determine the roots of the trees x and y belong to.
 * If the roots are distinct, the trees are combined by attaching the root of one to
 * the root of the other.
 * If this is done naively, such as by always making y a child of x, the height of
 * the tree can grow as O(n).
 *
 * Optimize this algorithm by "Union by rank and path compression"
 *
 * Applications using Disjoint Sets:
 *
 * 1) Represents network connectivity
 * 2) Image processing
 * 3) In game algorithms
 * 4) Kruskal's minimum spanning tree
 * 5) Find cycle in undirected graphs.
 *
 */
public class DisjointSet {

    private static class Edge{

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

        //Add to list
        allEdges.add(edge);
    }

    private static void makeSet(int[] parent) {

        //Make Set - Creating a new element with a parent pointer to itself
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    private static int find(int[] parent, int vertex) {

        //Chain of parent pointers from x to upwards through the tree
        //Until an element is reached whose parent is itself
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

    private static void disjointSets() {

        //Create a parent
        int[] parent = new int[vertices];

        //Make set
        makeSet(parent);

        //Iterate through all edges and keep making sets
        for (int i = 0; i < allEdges.size(); i++) {

            Edge edge = allEdges.get(i);

            int x_set = find(parent, edge.source);
            int y_set = find(parent, edge.destination);

            //Check if the source and destination vertex belongs to the same set
            //If it in the same set then cycle has been detected else combine
            //them into one set
            if (x_set == y_set) {
                //do nothing - cycle
            } else {
                union(parent, x_set, y_set);
            }
        }

        printSets(parent);
    }

    private static void printSets(int[] parent) {

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < parent.length; i++) {

            if (map.containsKey(parent[i])) {

                ArrayList<Integer> list = map.get(parent[i]);
                list.add(i); //Add vertex
                map.put(parent[i], list);

            } else {

                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(parent[i], list);
            }
        }

        Set<Integer> keys = map.keySet();

        for(int key: keys) {

            System.out.println("Set Id: " + key + " Elements: " + map.get(key));
        }
    }

    public static void main(String[] args) {

        int vertices = 6;
        initializeGraph(vertices);

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(4, 5);

        disjointSets();
    }
}
