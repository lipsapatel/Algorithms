import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Disjoint Set or Union Find Algorithm
 * Union By Rank and Path Compression
 *
 * /resouces/DisjointSet.png
 *
 * Disjoint Set Operations:
 *
 * 1) makeSet(x)
 * 2) Find(x)
 * 3) Union(x, y)
 *
 * How Disjoint Set is optimized:
 *
 * 1) Union by rank
 * 2) Path by compression
 *
 * Union By Rank:
 *
 * Uses find to determine the roots of the trees x and y belong to.
 * If root are distinct, the trees are combined by attaching the root of one to the root
 * of the other. If this is done naively, such as by always making x as parent of y, the
 * height of the trees can grow as O(n). We can optimize it by using union by rank.
 *
 * Union by rank always attaches the shorter tree to the root of taller tree.
 * To implement union by rank, each element is associated with a rank. Initially a set has
 * one element and a rank of zero.
 *
 * If we union two sets
 *
 * 1) Both the trees have same rank, then the resulting set's rank is one larger.
 * 2) Both the trees have different ranks - the resulting set's rank is the larger of the two.
 *
 * Ranks are used instead of height or depth because path compression will change the tree's
 * height over time.
 *
 * Worst Case Time Complexity: O(logn)
 *
 * resources/DisjointSetUnionByRank.png
 *
 * Path Compression:
 *
 * Path Compression is a way of flattening the structure of the tree whenever Find is used on it.
 * Since each element visited on the way to a root is part of the same set, all of these visited
 * elements can be reattached directly to the root.
 *
 * The resulting tree is much flatter, speeding up future operations not only on those elements but
 * also on those referencing them.
 *
 * resources/DisjointSetPathCompression.png
 *
 * MakeSet:
 *
 * The MakeSet operation makes a new set by creating a new element with a unique id, a rank of 0
 * and a parent pointer to itself. The parent pointer to itself indicates that the element
 * is the representative member of its own set.
 *
 * MakeSet operation has O(1) time complexity
 */
public class DisjointSetUnionByRank {

    //Edge class which has source and destination
    private static class Edge{

        int source;
        int destination;

        public Edge(int source, int destination) {

            this.source = source;
            this.destination = destination;
        }

    }

    //SubSet class which has rank of 0 and parent pointer to itself
    private static class SubSet {

        int parent;
        int rank;
    }

    //Graph variables
    private static int vertices;
    private static LinkedList<Edge>[] graphList;
    private static ArrayList<Edge> allEdges = new ArrayList<>();

    //Initialize graph
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

        //Add to total edges
        allEdges.add(edge);
    }

    //(Path Compression)
    private static int find(SubSet[] subSet, int vertex) {

        if (subSet[vertex].parent != vertex) {
            return find(subSet, subSet[vertex].parent);
        }
        return vertex;
    }

    //Union By rank
    public static void union(SubSet[] subSet, int x, int y) {

        int x_set_parent = find(subSet, x);
        int y_set_parent = find(subSet, y);

        //Attach smaller rank tree to the higher rank tree
        if (subSet[x_set_parent].rank > subSet[y_set_parent].rank) {

            //Make x parent of y
            subSet[y_set_parent].parent = x_set_parent;

        } else if (subSet[x_set_parent].rank < subSet[y_set_parent].rank) {

            //Make y parent of x
            subSet[x_set_parent].parent = y_set_parent;

        } else { //Equal make any tree child of other tree and increment rank

            subSet[y_set_parent].parent = x_set_parent;

            //Now increase the rank of x for the next time
            subSet[x_set_parent].rank++;
        }
    }

    private static void makeSet(SubSet[] subSets) {

        //Make set
        for (int i = 0; i < vertices; i++) {

            subSets[i] = new SubSet();
            subSets[i].parent = i;
            subSets[i].rank = 0;
        }
    }

    private static void disjointSets() {

        //Create subsets[]
        SubSet[] subSets = new SubSet[vertices];

        //Make set
        makeSet(subSets);

        //Iterate through all the edges and keep making the sets
        for (int i = 0; i < allEdges.size(); i++) {

            Edge edge = allEdges.get(i);

            int x_set = find(subSets, edge.source);
            int y_set = find(subSets, edge.destination);

            //Check if source and destination vertex belongs to the same set
            //If in the same set then cycle has been detected else combine them into one set
            if (x_set == y_set) {
                //Do nothing
            } else {
                union(subSets, x_set, y_set);
            }
        }
        printSets(subSets);
    }

    private static void printSets(SubSet[] subSets) {

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < subSets.length; i++) {

            if (map.containsKey(subSets[i].parent)) {

                ArrayList<Integer> list = map.get(subSets[i].parent);
                list.add(i); //Add vertex
                map.put(subSets[i].parent, list);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(subSets[i].parent, list);
            }
        }

        //Print the different sets
        Set<Integer> keys = map.keySet();
        for (int key: keys) {

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

        System.out.println("Disjoint Sets: ");
        disjointSets();
    }
}
