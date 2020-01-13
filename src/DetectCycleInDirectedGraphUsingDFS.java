import java.util.HashMap;
import java.util.HashSet;

/**
 * Detect cycle in directed graph using DFS.
 *
 * The DFS tree of directed graph will have following edges
 *
 * 1) Tree Edge
 * 2) Forward Edge - where destination contained within source aTime[s] < aTime[d]
 * 3) Cross edge - no overlap aTime[d] < dTime[d] < aTime[s]
 * 4) Back Edge - where source is contained with in destination aTime[d] < aTime[s]
 *
 * Approach
 * 1) Do the DFS
 * 2) Everytime before starting DFS on vertex, increment the arrival time
 * 3) After the dfs is done on the vertex, increment the departure time
 * 4) If there's an edge to an already visited vertex, check if it's a back edge then there is cycle.
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 *
 * resources/DetectCycleInDirectedGraphUsingDFS.jpg
 */
public class DetectCycleInDirectedGraphUsingDFS {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
    private static HashMap<Integer, Integer> aTime = new HashMap<>();
    private static HashMap<Integer, Integer> dTime = new HashMap<>();
    private static int time = 0;

    private static boolean hasCycle() {

        HashSet<Integer> visited = new HashSet<>();

        for(Integer vertex: graph.keySet()) {

            if(!visited.contains(vertex)) {
                if(explore(vertex, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean explore(Integer s, HashSet<Integer> visited) {
        visited.add(s);
        aTime.put(s, time++);

        HashSet<Integer> neighbor = graph.get(s);

        if (neighbor != null) {
            for(Integer av: neighbor) {

                //Edge s --> av
                if(!visited.contains(av)) {
                    if(explore(av, visited)) {
                        return true;
                    }
                } else { //cycle if there's backedge

                    if (aTime.get(av) > aTime.get(s)) {
                        //Forward edge
                    } else if (dTime.get(av) != null && aTime.get(av) < dTime.get(av) && dTime.get(av) < aTime.get(s)) {
                        //Cross edge
                    } else if (aTime.get(av) < aTime.get(s)) {
                        //BackEdge - cycle
                        return true;
                    }
                }
            }
        }
        dTime.put(s, time++);
        return false;
    }

    public static void main(String[] args) {
        HashSet<Integer> neighbor = new HashSet<>();
        neighbor.add(2);
        neighbor.add(5);

        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(3);
        neighbor.add(5);

        graph.put(2, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(1);

        graph.put(3, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(1);
        neighbor.add(5);

        graph.put(4, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(6);

        graph.put(5, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(3);

        graph.put(6, neighbor);

        System.out.println("The graph has cycle: " + hasCycle());

    }
}
