import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * We use Bellman-Ford Algorithm to find the shortest path from a single source node/vertex to all
 * destination nodes/vertices.
 *
 * The edges could be relax in any order.
 * To get the shortest path, we need V - 1 iterations.
 * There could be nodes which are unreachable.
 *
 * To find if the graph contains negative weight cycle, do one more iteration
 * that is Vth iteration. If that changes distance value for any node, that means there exists a negative
 * cycle (a cycle whose edges sum to negative value).
 *
 * Negative edge doesn't mean a negative cycle.
 *
 * All V - 1 iterations are not required. If an iteration does not change any distance value
 * then we can terminate the algorithm there and return valid result.
 * It also means there's no negative cycle.
 *
 * resources/BellmanFord.png
 *
 * Time Complexity: O(EV)
 *
 * Approach:
 *
 * 1) Iterate v-1 times
 * 2) Do the Edge relaxation
 * 3) Iterate vth time
 * 4) Check for negative weight cycle
 */
public class BellmanFordShortestPath {

    private static HashMap<Integer, HashSet<Pair<Integer, Integer>>> graph = new HashMap<>();

    private static void bellmanFord(int source) {

        //Distance used to store distance from source vertex
        HashMap<Integer, Integer> distance = new HashMap<>();

        //Initialize all distance to infinity
        for (Integer vertex: graph.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }

        //Distance for source vertex is 0
        distance.put(source, 0);

        //Relax all the edges V - 1 times
        //A simple shortest path from src to any other vertex can have atmost V - 1 edges
        //O(V)
        for (int i = 0; i < graph.size() - 1; i++) {
            //Stop the iteration if the distance is not updated
            boolean valueChanged = false;

            //O(E)
            for(Integer vertex: graph.keySet()) {
                HashSet<Pair<Integer, Integer>> neighbor = graph.get(vertex);

                if (neighbor != null) {
                    for(Pair<Integer, Integer> av: neighbor) {
                        if (distance.get(vertex) != Integer.MAX_VALUE && distance.get(vertex) + av.getValue() < distance.get(av.getKey())) {
                            distance.put(av.getKey(), distance.get(vertex) + av.getValue());
                            valueChanged = true;
                        }
                    }
                }
            }

            //All edges relaxed but the value not changed then stop we are done finding the shortest path
            if (!valueChanged) {
                break;
            }
        }

        //Check for negative-weight cycle.
        //If we get shorter path (distance updated) then there's negative-weight cycle
        //vth iteration will determine negative weight cycle
        //O(E)
        for(Integer vertex: graph.keySet()) {
            HashSet<Pair<Integer, Integer>> neighbor = graph.get(vertex);

            if (neighbor != null) {
                for(Pair<Integer, Integer> av: neighbor) {
                    if (distance.get(vertex) != Integer.MAX_VALUE && distance.get(vertex) + av.getValue() < distance.get(av.getKey())) {
                        System.out.println("Graph contains negative weight cycle");
                        return;
                    }
                }
            }
        }

        //Print distance
        for(Map.Entry<Integer, Integer> entry: distance.entrySet()) {
            System.out.println("The distance of vertex " + entry.getKey() + " from source vertex is " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        HashSet<Pair<Integer, Integer>> neighbor = new HashSet<>();
        Pair<Integer, Integer> pair = new Pair<>(2, -1);
        neighbor.add(pair);
        pair = new Pair<>(3, 4);
        neighbor.add(pair);
        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(3, 3);
        neighbor.add(pair);
        pair = new Pair<>(4, 2);
        neighbor.add(pair);
        pair = new Pair<>(5, 2);
        neighbor.add(pair);
        graph.put(2, neighbor);

        neighbor = new HashSet<>();
        graph.put(3, neighbor);

        pair = new Pair<>(2, 1);
        neighbor.add(pair);
        pair = new Pair(3, 5);
        neighbor.add(pair);
        graph.put(4, neighbor);

        pair = new Pair<>(4, -3);
        neighbor = new HashSet<>();
        neighbor.add(pair);
        graph.put(5, neighbor);

        bellmanFord(1);
    }
}
