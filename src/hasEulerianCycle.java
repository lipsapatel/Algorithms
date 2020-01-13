import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Visit every edge exactly once and end where started
 * There exists eulerian cycle if and only if all the vertices have even degree.
 *
 * 1 -- 2 -- 4 -- 5 -- 3 -- 1
 *
 * Eulerian Path - In graph theory, an eulerian trail or path is a path in a finite graph that visits every edge
 exactly once (allowing for revisiting vertices)
 */
public class hasEulerianCycle {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    private static boolean hasEulerianCycle() {

        for (Map.Entry<Integer, HashSet<Integer>> entry: graph.entrySet()) {

            if (entry.getValue().size() % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasEulerianPath() {

        int odd = 0;

        for(HashSet<Integer> neighbors: graph.values()) {
            if (neighbors.size() % 2 == 1) {
                odd++;
            }
        }
        if (odd == 0 || odd == 2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        HashSet<Integer> neighbors = new HashSet<>();
        neighbors.add(2);
        neighbors.add(3);

        graph.put(1, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(1);
        neighbors.add(4);

        graph.put(2, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(2);
        neighbors.add(5);

        graph.put(4, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(4);
        neighbors.add(3);

        graph.put(5, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(1);
        neighbors.add(5);

        graph.put(3, neighbors);

        System.out.println("The graph has eulerian cycle: " + hasEulerianCycle());
        System.out.println("The graph has eulerian path: " + hasEulerianPath());
    }
}
