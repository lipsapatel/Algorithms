import java.lang.reflect.Array;
import java.util.*;

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
 *
 * Space Complexity: O(V)
 *
 * This works for connected or disconnected undirected graph to print connected components
 * For directed graph to print connected components, the starting vertex needs to be given for dfs.
 *
 * DFS traversal remains the same for connected/disconnected directed/undirected graph.
 */
public class GraphDepthFirstSearchUsingRecursion {

    private static HashMap<Integer, HashSet<Integer>> graphMap = new HashMap<>();

    private static void addEdge(int source, int destination) {

        if (graphMap.containsKey(source)) {
            HashSet set = graphMap.get(source);
            set.add(destination);
            graphMap.put(source, set);
        } else {
            HashSet<Integer> set = new HashSet<>();
            set.add(destination);
            graphMap.put(source, set);
        }
    }

    private static void printGraph() {

        for (Map.Entry<Integer, HashSet<Integer>> entry: graphMap.entrySet()) {
            HashSet<Integer> set = entry.getValue();

            System.out.print("Vertex " + entry.getKey() + " is connected to: ");

            for (Integer destination: set) {
                System.out.print(destination + " ");
            }
            System.out.println();
        }
    }

    private static void DFSUsingRecursion(HashMap<Integer, HashSet<Integer>> graphMap) {

        HashSet<Integer> visited = new HashSet<>(); //Keeps the track of nodes seen
        for (Integer vertex: graphMap.keySet()) { //This is if the graph is not connected - TC = O(V)
            if (!visited.contains(vertex)) { //Not seen so far

                ArrayList<Integer> dfsList = new ArrayList<>();
                //Explore that node if not visited
                explore(vertex, visited, dfsList); //O(E)

                //Print dfsList of connected components
                for (Integer dfs: dfsList) {
                    System.out.print(dfs + " ");
                }
                System.out.println();
            }
        }
    }

    private static void explore(Integer start, HashSet<Integer> visited, ArrayList<Integer> dfsList) {

        visited.add(start); //SC = O(V)
        dfsList.add(start);
        HashSet<Integer> adjacentSet = graphMap.get(start);

        if (adjacentSet != null) {
            for (Integer adjacentVertex : adjacentSet) { // Delta i for all the vertices = 2|E| = O(E)

                if (!visited.contains(adjacentVertex)) {
                    explore(adjacentVertex, visited, dfsList); //SC - stack Space = O(V)
                }
            }
        }
    }

    public static void main(String[] args) {

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(3, 4);
        addEdge(2, 3);
        addEdge(4, 0);
        addEdge(4, 1);
        addEdge(4, 5);
        addEdge(13, 14);
        printGraph();
        DFSUsingRecursion(graphMap);
    }
}
