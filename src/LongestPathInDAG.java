import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 * Longest Path in graph with cycle is NP hard problem.
 * But longest path in Directed Acyclic Graph (DAG) can be done in linear time using topological order.
 *
 *  Approach
 *
 *  1) Find the topological order of the graph using DFS - TC = O(V + E)
 *  2) Iterate the topological order and find the longest distance for each adjacent vertex - TC = O(V + E)
 *
 *  TC = O(V + E)
 *
 *  resources/LongestPathInDAG.jpg
 *
 */
public class LongestPathInDAG {

    private static HashMap<Integer, HashSet<Pair<Integer, Integer>>> graph = new HashMap<>();

    private static void findLongestPath(int source) {
        Stack<Integer> stack = new Stack<>();

        //TC = O(V + E)
        getTopologicalOrder(stack);

        //TC = O(V + E)
        findLongestDistance(stack, source);

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

        HashSet<Pair<Integer, Integer>> neighbors = graph.get(s);

        if (neighbors != null) {
            for (Pair<Integer, Integer> av: neighbors) {
                if (!visited.contains(av.getKey())) {
                    explore(av.getKey(), visited, stack);
                } else { //Find cycle

                }
            }
        }
        stack.push(s);
    }

    private static void findLongestDistance(Stack<Integer> stack, Integer source) {

        HashMap<Integer, Integer> distance = new HashMap<>();

        for(Integer vertex: graph.keySet()) {
            distance.put(vertex, Integer.MIN_VALUE);
        }
        distance.put(source, 0);

        while(!stack.isEmpty()) {
            Integer vertex = stack.pop();

            HashSet<Pair<Integer, Integer>> neighbors = graph.get(vertex);

            if (neighbors != null) {
                for(Pair<Integer, Integer> av: neighbors) {
                    if(distance.get(vertex) + av.getValue() > distance.get(av.getKey())) {
                        distance.put(av.getKey(), distance.get(vertex) + av.getValue());
                    }
                }
            }
        }

        //Print the distance
        for(Map.Entry<Integer, Integer> entry: distance.entrySet()) {
            System.out.println("The distance of vertex " + entry.getKey() + " from the source vertex is " + entry.getValue());
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

        findLongestPath(3);
    }
}
