import java.util.HashMap;
import java.util.HashSet;

/**
 * Detect cycle in undirected graph using DFS.
 * In DFS, if there's an edge to already visited vertex, then it's the back edge and it represents cycle.
 *
 * Approach
 * 1) Do DFS
 * 2) If there's an edge to already visited vertex, return true for cycle.
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 *
 * resources/DetectCycleInUndirectedGraphUsingDFS.jpg
 *
 * DFS tree of an undirected graph will have only backedge which will represent cycle.
 */
public class DetectCycleInUndirectedGraphUsingDFS {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    //Maintain parent because by default one of the edge will be to parent; ignore as it's a tree edge
    private static HashMap<Integer, Integer> parent = new HashMap<>();

    private static boolean hasCycle() {

        HashSet<Integer> visited = new HashSet<>();

        for(Integer vertex: graph.keySet()) {

            if (!visited.contains(vertex)) {
                if (explore(vertex, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean explore(Integer start, HashSet<Integer> visited) {

        visited.add(start);

        HashSet<Integer> neighbor = graph.get(start);

        if (neighbor != null) {
            for(Integer adjacentVertex: neighbor) {
                if(!visited.contains(adjacentVertex)) {
                    parent.put(adjacentVertex, start); //child, parent
                    if (explore(adjacentVertex, visited)) {
                        return true;
                    }
                } else { //For undirected graph, an edge to already visited vertex represent backedge and hence cycle.
                    //Ignore if one of the edge goes to parent since it's a tree edge.
                    //There's a back edge from start to adjacent vertex.
                    if (parent.get(start) != adjacentVertex)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HashSet<Integer> neighbor = new HashSet<>();
        neighbor.add(3);
        neighbor.add(4);

        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(5);

        graph.put(2, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(1);
        neighbor.add(4);
        neighbor.add(5);

        graph.put(3, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(1);
        neighbor.add(3);
        neighbor.add(5);
        neighbor.add(6);

        graph.put(4, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(2);
        neighbor.add(3);
        neighbor.add(4);

        graph.put(5, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(4);

        graph.put(6, neighbor);

        System.out.println("The undirected graph has cycle: " + hasCycle());
    }
}
