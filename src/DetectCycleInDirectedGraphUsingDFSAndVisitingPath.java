import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Detect cycle in a directed graph
 *
 * Given a directed graph, write an algorithm to detect if the graph contains cycle or not.
 *
 * resources/DetectCycleInDirectedGraphUsingDFS.jpg
 *
 * Approach:
 *
 * 1) Graph contains cycle if there is any back edge.
 * 2) There are two types of back edge:
 *    --> Edge from vertex to itself - self loop
 *    --> Edge from any descendant back to vertex
 *
 * 3) Use Depth First Search to detect back edge.
 * 4) Do DFS from each vertex.
 * 5) Keep the track of visiting vertices/path in visitingPath.
 * 6) If you encounter the visited vertex already present in visitingPath then there is a cycle.
 * 7) Use visited to keep the track of already visited nodes.
 *
 * Difference between visitingPath and visited:
 *
 * 1) visited - keeps the track of already visited vertices.
 * 2) visitingPath - Keeps the list of visiting path vertices.
 *
 * Time Complexity: O(V+E)
 * Space Complexity: O(V)
 */
public class DetectCycleInDirectedGraphUsingDFSAndVisitingPath {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    private static boolean hasCycle() {
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> visitingPath = new HashSet<>();

        for(Integer vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                if(explore(vertex, visited, visitingPath)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean explore(Integer s, HashSet<Integer> visited, HashSet<Integer> visitingPath) {
        visited.add(s);
        visitingPath.add(s);

        HashSet<Integer> neighbors = graph.get(s);

        if(neighbors != null) {
            for(Integer av: neighbors) {
                if(!visited.contains(av)) {
                    if(explore(av, visited, visitingPath)) {
                        return true;
                    }
                } else { //already visited vertices, check if it's in the visitingPath
                    if(visitingPath.contains(av)) {
                        return true;
                    }
                }
            }
        }
        visitingPath.remove(s);
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
