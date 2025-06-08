package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Given the number of separated cities and a list of connections, return the
 * minimum cost to connect all the cities or return -1 if it is impossible to
 * connect all the cities.
 *
 * A list of three numbers represents a connection, the first two represent
 * the cities it connects, and the last one represents the cost to connect them.
 *
 * Example One
 * {
 * "n_cities": 3,
 * "connections": [
 * [1, 2, 4],
 * [2, 3, 2],
 * [3, 1, 5]
 * ]
 * }
 * Output:
 *
 * 6
 * Making connections [1, 2, 4] and [2, 3, 2] will connect all the three cities with the minimum cost of 6.
 *
 * Example1 output
 *
 * Example Two
 * {
 * "n_cities": 4,
 * "connections": [
 * [1, 3, 1],
 * [2, 4, 1]
 * ]
 * }
 * Output:
 *
 * -1
 * There is no way to connect all the cities.
 *
 * Notes
 * Constraints:
 *
 * 2 <= number of cities <= 104
 * 1 <= number of connections <= 105
 * 1 <= cost of any connection <= 105
 *
 * Approach
 * 1) Kruskal's MST
 * 2) Sort the edges and do union and find.
 * 3) Keep track of number of connected components and cost
 * 4) If connected components greater than 1 return -1
 * 5) If there's cycle it will not count the edge because parent will be same
 *
 * TC: O(n) + O(nlogn) = O(nlogn) (union and find and sort)
 * SC: O(n) - parent, size
 */
public class MinimumCostToConnectCities {

    private static int minCost(int n, ArrayList<ArrayList<Integer>> connections) {
        Collections.sort(connections, (a, b) -> {
            return a.get(2) - b.get(2);
        });

        int[] parent = new int[n + 1];
        int[] size = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        Arrays.fill(size, 1);
        int cost = 0;
        int connCount = n;

        for(ArrayList<Integer> edge: connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);

            int rootu = find(parent, u);
            int rootv = find(parent, v);

            if(rootu != rootv) {
                if(size[rootu] > size[rootv]) {
                    parent[rootv] = rootu;
                    size[rootu] += size[rootv];
                } else {
                    parent[rootu] = rootv;
                    size[rootv] += size[rootu];
                }
                connCount--;
                cost += w;
            }
        }
        return cost;
    }

    private static int find(int[] parent, int x) {
        if(x == parent[x]) {
            return x;
        } else {
            int rootx = find(parent, parent[x]);
            parent[x] = rootx;
            return rootx;
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();
        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(1);
        edge.add(2);
        edge.add(4);
        connections.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(3);
        edge.add(2);
        connections.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(1);
        edge.add(5);
        connections.add(edge);

        System.out.println("Minimum cost to connect n cities " + minCost(3, connections));


    }
}
