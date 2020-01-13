import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Find an order in which all these courses can be taken
 *
 * Given a directed acyclic graph G, we can order the vertices of G so that every edge goes from left to right.
 * Goes from higher destination to lower destination. This ordering is called a topological sort.
 *
 * Approach:
 *
 * 1) Do the DFS with arrival and departure time.
 * 2) Add the vertices to stack at the time of departure.
 * 3) If there's cycle then it's not a valid graph
 *
 * resources/FindCourseOrder.jpg
 * resources/FindCourseOrder1.png
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
public class FindCourseOrder {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
    private static HashMap<Integer, Integer> aTime = new HashMap<>();
    private static HashMap<Integer, Integer> dTime = new HashMap<>();
    private static int time = 0;
    private static boolean hasCycle = false;

    private static void dfs() {

        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for(Integer vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                explore(vertex, visited, stack);
            }
        }

        //Print stack
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void explore(Integer s, HashSet<Integer> visited, Stack<Integer> stack) {

        visited.add(s);
        aTime.put(s, time++);

        HashSet<Integer> neighbor = graph.get(s);

        if (neighbor != null) {
            for(Integer av: neighbor) {
                if(!visited.contains(av)) {
                    explore(av, visited, stack);
                } else { //Find cycle

                    if (aTime.get(av) > aTime.get(s)) {
                        //Forward edge
                    } else if (dTime.get(av) != null && aTime.get(av) < dTime.get(av) && dTime.get(av) < aTime.get(s)) {
                        //cross edge
                    } else if (aTime.get(s) > aTime.get(av)) {
                        hasCycle = true;
                    }
                }
            }
        }

        dTime.put(s, time++);
        stack.add(s);
    }

    public static void main(String[] args) {
        HashSet<Integer> neighbor = new HashSet<>();
        neighbor.add(143);

        graph.put(142, neighbor);

        neighbor = new HashSet<>();

        neighbor.add(341);
        neighbor.add(321);
        neighbor.add(370);
        neighbor.add(378);

        graph.put(143, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(401);

        graph.put(378, neighbor);
        graph.put(341, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(322);
        neighbor.add(326);
        graph.put(321, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(401);
        neighbor.add(421);
        graph.put(322, neighbor);
        graph.put(326, neighbor);

        dfs();
    }
}
