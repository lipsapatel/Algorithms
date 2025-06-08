package IK.Graphs.Optional;

import java.util.Arrays;

/**
 * There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k,
 * return the cheapest price from src to dst with at most k stops.
 * If there is no such route, return -1.
 *
 * Example 1:
 *
 *
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 *
 * Example 2:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 *
 * Example 3:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 *
 * resources/BellmanFord1.png
 * resouces/BellmanFord2.png
 * resouces/BellmanFord3.png
 *
 * Approach
 *
 * 1) Single source shortest path with constraint so we cannot use Dijkstra
 * 2) F(V, i) = Math.min(F(V1, i - 1) + w, F(V2, i - 1) + w)
 * 3) Lazy Manager Analogy and ask the preceding vertex to return shortest path from src to V1 with <= i - 1 edges. Do this for all preceding vertex.
 * 4) The problem has both overlapping subproblems and optimal substructure
 * 5) So we can use DP
 * 6) Create 2D array of size n * k + 2
 * 7) Initialize it with infinity
 * 8) dp[src][0] = 0 Shortest path from src to src with <= 0 edges
 * 9) Iterate col by col
 * 10) Copy the preceding col
 * 11) Iterate edges (u, v, w)
 * 12) dp[v][k] = Math.min(dp[v][k], dp[u][k - 1] + w)
 * 13) Return dp[dst][k + 1]
 *
 * TC: O((m + n) * k = O(mk) m > n
 * SC: O(nk)
 * */
public class BellmanFordCheapestFlightWithAtMostKStops {

    private static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k1) {

        int[][] dp = new int[n][k1 + 2];

        //Initialize array with infinity
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        //Shortest path from src to src <= 0 edges
        dp[src][0] = 0;

        //Iterate col by col
        for(int k = 1; k <= k1 + 1; k++) {

            //Copy preceding col values
            for(int i = 0; i < n; i++) {
                dp[i][k] = dp[i][k - 1];
            }

            //Iterate edges
            for(int[] edge: flights) {
                //(u, v, w)
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                dp[v][k] = Math.min(dp[v][k], (dp[u][k - 1] == Integer.MAX_VALUE) ? dp[u][ k - 1] : dp[u][k - 1] + w); //u -> v
            }
        }
        return (dp[dst][k1 + 1] == Integer.MAX_VALUE) ? -1 : dp[dst][k1+1];
    }

    public static void main(String[] args) {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}};
        int src = 0;
        int dst = 3;
        int k = 1;

        System.out.println("The shortest path from src to dst with <= k edges is " +findCheapestPrice(4, flights, src, dst, k));

    }
}
