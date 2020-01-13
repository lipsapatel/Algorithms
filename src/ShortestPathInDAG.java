import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 * Find the shortest path in weighted Directed Acyclic Graph.
 *
 * You can use Dijkstra if the graph has only positive weights. But it's time complexity is O((V + E)logV)
 * You can use Bellman Ford if the graph has negative weights. But it's time complexity is O(VE)
 *
 * Whenever you are given DAG, you need to do topological sort.
 * Because the graph can be arranged in the order of their dependency going from higher departure time to lower departure
 * time.
 *
 * Are topological sort, you need to do linear scan one more time to do your processing.
 *
 * The answer interviewer is expecting when given DAG should be in linear time.
 *
 * Time Complexity: O(V + E)
 *
 * Approach:
 *
 * 1) Do the DFS and determine topological order by adding to stack.
 * 2) Scan the topological order and update the weights of all neighbors.
 *
 * resources/ShortestPathInDAG.jpg
 *
 */
public class ShortestPathInDAG {

    private static HashMap<Integer, HashSet<Pair<Integer, Integer>>> graph = new HashMap<>();

    private static void findShortestPath(int s) {

        Stack<Integer> stack = new Stack<>();

        //TC = O(V + E)
        getTopologicalOrder(stack);

        //TC = O(V + E)
        findShortestDistance(stack, s);
    }

    private static void getTopologicalOrder(Stack<Integer> stack) {

        HashSet<Integer> visited = new HashSet<>();

        for(Integer vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                explore(vertex, visited, stack);
            }
        }
    }

    private static void explore(Integer s, HashSet<Integer> visited, Stack<Integer> stack) {
        visited.add(s);
        HashSet<Pair<Integer, Integer>> neighbor = graph.get(s);

        if (neighbor != null) {
            for(Pair<Integer, Integer> av: neighbor) {
                if(!visited.contains(av.getKey())) {
                    explore(av.getKey(), visited, stack);
                } else {
                    //Find cycle
                }
            }
        }
        stack.push(s);
    }

    private static void findShortestDistance(Stack<Integer> stack, int source) {

        HashMap<Integer, Integer> distance = new HashMap<>();

        for(Integer vertex: graph.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }
        distance.put(source, 0);

        //Iterate topological order
        while(!stack.isEmpty()) {
            Integer vertex = stack.pop();

            HashSet<Pair<Integer, Integer>> neighbor = graph.get(vertex);

            if (neighbor != null) {
                for(Pair<Integer, Integer> av: neighbor) {

                    //Because when you add 1 to java integer max, it changes it's sign bit and becomes negative
                    if (distance.get(vertex) != Integer.MAX_VALUE && distance.get(vertex) + av.getValue() < distance.get(av.getKey())) {
                        distance.put(av.getKey(), distance.get(vertex) + av.getValue());
                    }
                }
            }
        }

        //Print distance
        for(Map.Entry<Integer, Integer> entry: distance.entrySet()) {
            System.out.println("The distance of vertex " + entry.getKey() + " from source is " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        HashSet<Pair<Integer, Integer>> neighbor = new HashSet<>();
        Pair<Integer, Integer> pair = new Pair<>(2, 3);

        neighbor.add(pair);

        pair = new Pair<>(3, 5);
        neighbor.add(pair);

        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(4, 7);
        neighbor.add(pair);
        pair = new Pair<>(6, 2);
        neighbor.add(pair);
        pair = new Pair<>(5, 4);
        neighbor.add(pair);
        graph.put(2, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(4, 6);
        neighbor.add(pair);
        pair = new Pair<>(2, 2);
        neighbor.add(pair);
        graph.put(3, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(5, -1);
        neighbor.add(pair);
        pair = new Pair<>(6, 1);
        neighbor.add(pair);
        graph.put(4, neighbor);

        neighbor = new HashSet<>();
        pair = new Pair<>(6, -2);
        neighbor.add(pair);
        graph.put(5, neighbor);

        neighbor = new HashSet<>();
        graph.put(6, neighbor);

        findShortestPath(3);
    }
}
