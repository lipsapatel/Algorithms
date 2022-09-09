package IK.Graphs.PreClass;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Implement graph using Adjacency Map
 * addEdge
 * hasEulerianCycle
 * hasEulerianPath
 *
 * Approach
 * 1) Create HashMap of Integer and HashSet
 * 2) In constructor, initialize the vertices and HashMap of size n with empty HashSet
 * 3) hasEdge(start, end, bidir) Add edge to hashmap in both direction if bidir is true
 * 4) hasEulerianCycle - Iterate HashMap and if the size of HashSet is odd then return false
 * 5) hasEulerianPath - Iterate HashMap and if the size of HashSet is odd increment odd variable.
 * 6) If odd == 0 or odd == 2 return true else false
 *
 * TC: O(V + E) or O(n + m) for Graph construction
 * O(V) or O(n) for hasEulerianCycle
 * O(V) or O(n) for hasEulerianPath
 *
 * SC: O(V + E) or O(n + m) for graph
 */
public class GraphImplementationAdjacencyMap {

    private static int vertices;
    private static HashMap<Integer, HashSet<Integer>> graph;

    public GraphImplementationAdjacencyMap(int n) {
        vertices = n;
        graph = new HashMap(5);

        for(int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
    }

    private static void addEdge(int start, int end, boolean bidir) {
        graph.get(start).add(end);

        if(bidir) {
            graph.get(end).add(start);
        }
    }

    private static boolean hasEulerianCycle() {

        for(HashSet<Integer> edge: graph.values()) {
            if(edge.size() % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasEulerianPath() {

        int odd = 0;

        for(HashSet<Integer> edge: graph.values()) {
            if(edge.size() % 2 != 0) {
                odd++;
            }
        }

        return odd == 0 || odd == 2;
    }

    public static void main(String[] args) {
        GraphImplementationAdjacencyMap graphImplementationAdjacencyMap = new GraphImplementationAdjacencyMap(5);
        addEdge(0,1, true);
        addEdge(0, 4, true);
        addEdge(1, 2, true);
        addEdge(1, 3, true);
        addEdge(1, 4, true);
        addEdge(2, 3, true);
        addEdge(3, 4, true);

        System.out.println("Has Eulerian Cycle " + hasEulerianCycle());
        System.out.println("Has Eulerian Path " + hasEulerianPath());
    }

}
