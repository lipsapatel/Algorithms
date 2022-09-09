package IK.Graphs.PreClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Check If Eulerian Path Exists
 * Given an undirected connected graph, check if there exists any eulerian path in it. The Eulerian Path is a path in the graph that
 * visits every edge exactly once (allowing for revisiting vertices).
 *
 * Example One
 * Graph
 *
 * {
 * "n": 4,
 * "edges": [
 * [0, 1],
 * [1, 2],
 * [1, 3],
 * [2, 0],
 * [3, 2]
 * ]
 * }
 * Output:
 *
 * 1
 * For example, the graph has an Eulerian Path, [1, 2, 0, 1, 3, 2].
 * resources/EulerianPath1.jpg
 *
 * Example Two
 * Graph
 *
 * {
 * "n": 5,
 * "edges": [
 * [0, 3],
 * [1, 2],
 * [1, 3],
 * [3, 2],
 * [4, 1],
 * [4, 2]
 * ]
 * }
 * Output:
 *
 * 0
 *
 * resources/EulerianPath2.jpg
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
 * Approach:
 *
 * 1) Eulerian path exists if all vertices has even degree. It also exists if only 2 vertices has odd degree.
 * 2) Create Map of vertices and their degree
 * 3) Iterate the edges and add the vertex and degree count to map
 * 4) Iterate HashMap and find if there are 0 or 2 odd degree vertices.
 * 5) Return true if there's 0 or 2 odd degree vertices.
 * 6) Else return false
 *
 * TC: O(n) for hashmap iteration + O(n (n - 1)/2) for edges iteration
 * SC: O(n)
 */
public class CheckIfEulerianPathExists {

    private static boolean checkIfEulerianPathExists(Integer n, ArrayList<ArrayList<Integer>> edges) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int oddCount = 0;

        for(ArrayList<Integer> edge: edges) {
            map.put(edge.get(0), map.getOrDefault(edge.get(0), 0) + 1);
            map.put(edge.get(1), map.getOrDefault(edge.get(1), 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() % 2 != 0) {
                oddCount++;
            }
        }

        if(oddCount == 0 || oddCount == 2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(0);
        edge.add(1);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(2);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(3);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(0);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(2);

        edges.add(edge);

        System.out.println("The graph has eulerian cycle " + checkIfEulerianPathExists(4, edges));
    }
}
