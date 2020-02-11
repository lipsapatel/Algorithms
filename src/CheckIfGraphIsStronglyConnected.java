import java.util.HashMap;
import java.util.HashSet;

/**
 * Check if a graph is strongly connected.
 * Given a directed graph, find out whether the graph is strongly connected or not.
 * A directed graph is strongly connected if there is a path between any two pair of vertices.
 *
 * For undirected graph, we can just do BFS or DFS starting from any vertex. If BFS or DFS visits all vertices then it's strongly
 * connected else it's not.
 *
 * resources/CheckIfGraphIsStronglyConnected.jpg
 * Approach: Kosaraju's DFS based simple algorithm which does 2 DFS traversals
 *
 * Time Complexity: O(V + E)
 *
 * If the number of strongly connected components(SCC) is 1 then the graph is strongly connected.
 *
 * Kosaraju's DFS based simple algorithm that does 2 DFS traversals
 *
 * 1) Initialize all vertices as false(not visited)
 * 2) Do DFS traversal
 * 3) If all the vertices are not visited, return false or else continue
 * 4) Reverse all the edges of graph.
 * 5) Mark all the vertices as false(not visited)
 * 6) Do second DFS traversal
 * 7) If all the vertices are not visited return false
 * 8) return true at end
 *
 */
public class CheckIfGraphIsStronglyConnected {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    private static boolean isGraphStronglyConnected() {
        HashSet<Integer> visited = new HashSet<>();

        //DFS traversal from any arbitrary vertex
        dfs(graph, visited, 0);

        //Check if all the vertices are visited
        for(Integer vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                return false;
            }
        }

        //Reverse all the edges of graph
        HashMap<Integer, HashSet<Integer>> reverseGraph = getReverseGraph(graph);

        visited = new HashSet<>();

        //DFS traversal of reversed graph from the same arbitrary vertex
        dfs(reverseGraph, visited, 0);

        //Check if all the vertices are visited
        for(Integer vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                return false;
            }
        }

        return true;
    }

    private static HashMap<Integer, HashSet<Integer>> getReverseGraph(HashMap<Integer, HashSet<Integer>> graph) {
        HashMap<Integer, HashSet<Integer>> reverseGraph = new HashMap<>();

        for(Integer vertex: graph.keySet()) {
            HashSet<Integer> neighbor = graph.get(vertex);

            if (neighbor != null) {
                for(Integer av: neighbor) {
                    if(reverseGraph.containsKey(av)) {
                        HashSet<Integer> rgNeighbor = reverseGraph.get(av);
                        rgNeighbor.add(vertex);
                    } else {
                        HashSet<Integer> rgNeighbor = new HashSet<>();
                        rgNeighbor.add(vertex);
                        reverseGraph.put(av, rgNeighbor);
                    }
                }
            }
        }
        return reverseGraph;
    }

    private static void dfs(HashMap<Integer, HashSet<Integer>> graph, HashSet<Integer> visited, Integer s) {
        visited.add(s);

        HashSet<Integer> neighbor = graph.get(s);
        if(neighbor != null) {
            for(Integer av: neighbor) {
                if(!visited.contains(av)) {
                    dfs(graph, visited, av);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> neighbor = new HashSet<>();
        neighbor.add(1);
        graph.put(0, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(2);
        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(4);
        neighbor.add(3);
        graph.put(2, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(0);
        graph.put(3, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(2);
        graph.put(4, neighbor);

        System.out.println("The graph is strongly connected " + isGraphStronglyConnected());

        graph = new HashMap<>();

        neighbor = new HashSet<>();
        neighbor.add(1);
        graph.put(0, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(2);
        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(3);
        graph.put(2, neighbor);

        graph.put(3, new HashSet<>());

        System.out.println("The graph is strongly connected: " + isGraphStronglyConnected());
    }
}
