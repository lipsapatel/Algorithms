import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Count number of paths from source to destination in directed acyclic graph (DAG)
 *
 * resources/CountNoOfPathsInDAG.jpg
 *
 * Approach:
 *
 * 1) Do DFS and get the reverse toplological sorted order (lower departure time to higher departure time)
 * 2) Take map which stores the source and no of paths
 * 3) Initialize with 0
 * 4) Except the no of paths from destination to destination is 1
 * 5) Iterate the reverse topological order. For all adjacent vertices update the no of paths.
 *
 * Two linear scan
 * TC = O(V + E)
 */
public class CountNoOfPathsInDAG {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    private static int numberOfPaths(int source, int destination) {

        List<Integer> reverseTopologicalOrder = new ArrayList<>();

        dfs(reverseTopologicalOrder);

        //Source, no of paths to destination
        HashMap<Integer, Integer> noOfPaths = new HashMap<>();

        //Initialize with all 0 except the path from destination to destination is 1
        for(Integer vertex: graph.keySet()) {
            noOfPaths.put(vertex, 0);
        }
        noOfPaths.put(destination, 1);

        //Iterate the reverse topological order
        for(Integer vertex: reverseTopologicalOrder) {
            HashSet<Integer> neighbor = graph.get(vertex);

            if(neighbor != null) {
                for(Integer av: neighbor) {
                    int paths = noOfPaths.get(vertex) + noOfPaths.get(av);
                    noOfPaths.put(vertex, paths);
                }
            }
        }
        return noOfPaths.get(source);
    }

    private static void dfs(List<Integer> reverseTopologicalOrder) {
        HashSet<Integer> visited = new HashSet<>();

        for(Integer vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                explore(vertex, visited, reverseTopologicalOrder);
            }
        }
    }

    private static void explore(Integer s, HashSet<Integer> visited, List<Integer> reverseTopologicalOrder) {
        visited.add(s);

        HashSet<Integer> neighbor = graph.get(s);

        if(neighbor != null) {
            for(Integer av: neighbor) {
                if(!visited.contains(av)) {
                    explore(av, visited, reverseTopologicalOrder);
                }
            }
        }
        reverseTopologicalOrder.add(s);
    }

    public static void main(String[] args) {
        HashSet<Integer> neighbor = new HashSet<>();
        neighbor.add(1);
        neighbor.add(2);
        neighbor.add(3);
        neighbor.add(4);
        graph.put(0, neighbor);

        neighbor = new HashSet<>();
        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(3);
        graph.put(2, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(4);
        graph.put(3, neighbor);

        neighbor = new HashSet<>();
        graph.put(4, neighbor);

        System.out.println("The number of paths from 0 to 4 are " + numberOfPaths(0, 4));
    }
}
