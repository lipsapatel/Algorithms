package IK.Graphs.PreClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem
 *
 * Check If Eulerian Cycle Exists
 * Check if there exists any eulerian cycle in a given undirected connected graph. The Euler cycle is a path in the graph that visits
 * every edge exactly once and starts and finishes at the same vertex.
 *
 * Example One
 * Graph
 *
 * {
 * "n": 5,
 * "edges": [
 * [0, 1],
 * [0, 2],
 * [1, 3],
 * [3, 0],
 * [3, 2],
 * [4, 3],
 * [4, 0]
 * ]
 * }
 * Output:
 *
 * 1
 * For example, the graph has an Eulerian Cycle, [2, 0, 1, 3, 0, 4, 3, 2].
 *
 * resources/EulerianCycle1.jpg
 *
 * Example Two
 * Graph
 *
 * {
 * "n": 6,
 * "edges": [
 * [0, 4],
 * [0, 5],
 * [1, 2],
 * [2, 3],
 * [3, 1],
 * [4, 3],
 * ]
 * }
 * Output:
 *
 * 0
 *
 * resources/EulerianCycle2.jpg
 *
 * Notes
 * The graph has n vertices, with each vertex having a distinct value from 0 to n - 1.
 * Edges are given as a list of lists where each inner list has exactly two elements. Each list [X, Y] represents an undirected edge from X to Y.
 * The list won't contain any duplicate edges i.e. if [X, Y] is present, then there will be no other occurrence of [X, Y] or [Y, X].
 * Constraints:
 *
 * 1 <= n <= 103
 * 0 <= value of each vertex <= n - 1
 * 0 <= number of edges <= (n * (n - 1)) / 2
 * The graph won't contain self loops.
 *
 * *****************************************************************************************************************************************
 *
 * Approach:
 *
 * 1) Create HashMap of Integer, Integer
 * 2) This will store vertices and their count.
 * 3) Iterate the edges and store the count of vertices
 * 4) If the vertices already present in hashmap then increment the count.
 * 5) Iterate the HashMap and find if there's any odd count.
 * 6) Return false for odd and true for even.
 *
 * TC: O(n^2) because there could be n(n -1)/2 edges + O(n) Iterate hashmap with vertices and their degree.
 * SC: O(n)
 */
public class CheckIfEulerianCycleExists {

    private static boolean checkIfEulerianCycleExists(Integer n, ArrayList<ArrayList<Integer>> edges) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for(ArrayList<Integer> edge: edges) {

            map.put(edge.get(0), map.getOrDefault(edge.get(0), 0) + 1);
            map.put(edge.get(1), map.getOrDefault(edge.get(1), 0) + 1);

        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(0);
        edge.add(1);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(0);
        edge.add(2);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(3);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(0);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(2);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(4);
        edge.add(3);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(4);
        edge.add(0);

        edges.add(edge);

        System.out.println("The graph has eulerian cycle " + checkIfEulerianCycleExists(6, edges));
    }

}
