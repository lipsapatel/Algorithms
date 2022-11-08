package IK.Graphs.Optional;

import java.util.Arrays;

/**
 * Find the shortest path with negative edges and without constraint
 *
 * Bellman Ford with single column
 * 1) Since there are not constraint for number of edges, we can do with one column
 * 2) Take 1D array of size n
 * 3) Initialize array with Infinity
 * 4) dp[src] = 0
 * 5) Do n - 1 iterations
 * 6) For each edge, dp[v] = Min(dp[v], dp[u] + w)
 * 7) To find negative weight cycle, do one more iteration and if the value changes then there is negative weight cycle
 *
 * TC: O(mn)
 * SC: O(n)
 */
public class BellmanFord {

    private static int shortestPath(int src, int dst, int n, int[][] edges) {
        int[] dp = new int[n];

        //Initialize with Infinity
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[src] = 0;

        for(int i = 0; i < n; i++) {
            for(int[] edge: edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                dp[v] = Math.min(dp[v], (dp[u] == Integer.MAX_VALUE) ? dp[u] : dp[u] + w);
            }
        }

        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if(dp[u] != Integer.MAX_VALUE && dp[u] + w < dp[v]) {
                //Negative weight cycle
                return -1;
            }
        }

        return (dp[dst] == Integer.MAX_VALUE) ? -1 : dp[dst];
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, -3}, {0, 2, 1}, {2, 1, -1}};
        int src = 0;
        int dst = 1;
        int n = 3;

        System.out.println("Shortest path from 0 to 1 is " + shortestPath(src, dst, n, edges));
    }
}
