import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an undirected graph, return true if and only if it's bipartite.
 *
 * A graph is bipartite if we can split it's set of nodes into two independent subsets A and B
 * such that every edge in the graph have one node in A and other in B.
 *
 * The graph is not bipartite if there's a cross edge at the same level.
 * The cross edge at the same level have same distance.
 *
 * Maintain the distance array.
 * If there's any cross edge, check it's distance with parent, if it's same then the graph
 * is not bipartite.
 *
 * Do the BFS and maintain the distance array.
 * If the node is already visited, check if it's distance is same.
 * If it's same then the graph is not bipartite.
 *
 * In a bipartite graph, all cycles must be of even length.
 * If the graph is not bipartite then the cycle is of odd length.
 *
 * Time Complexity: O(E + V) same as BFS
 *
 */
public class IsGraphBipartite {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    private static boolean isGraphBipartiteUsingBfs(int start) {

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distance = new HashMap<>();

        //Add start node
        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while(!queue.isEmpty()) {
            Integer poppedNode = queue.remove();

            HashSet<Integer> neighbors = graph.get(poppedNode);

            if (neighbors != null) {
                for(Integer vertex: neighbors) {

                    if(!visited.contains(vertex)) {
                        queue.add(vertex);
                        visited.add(vertex);
                        distance.put(vertex, distance.get(poppedNode) + 1);
                    } else {
                        //Cross edge indicate cycle. If the cross edge is at the same level then it's not bipartite.
                        if (distance.get(poppedNode) == distance.get(vertex)) {
                            //The graph will have cycle of odd length edges if it's not bipartite
                            return false;
                        }
                    }
                }
            }
        }
        //The graph is bipartite so the cycle will be of even length edges if there's any.
        return true;
    }

    public static void main(String[] args) {
        HashSet<Integer> neighbors = new HashSet<>();
        neighbors.add(3);
        neighbors.add(5);
        //neighbors.add(4);

        graph.put(1, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(4);

        graph.put(2, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(3);
        neighbors.add(5);
        neighbors.add(2);
        //neighbors.add(1);

        graph.put(4, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(5);

        graph.put(6, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(1);
        neighbors.add(4);

        graph.put(3, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(1);
        neighbors.add(4);
        neighbors.add(6);

        graph.put(5, neighbors);

        System.out.println("The given graph is bipartite: " + isGraphBipartiteUsingBfs(1));
    }
}
