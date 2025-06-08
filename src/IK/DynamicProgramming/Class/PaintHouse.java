package IK.DynamicProgramming.Class;

/**
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with the color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Return the minimum cost to paint all houses.
 *
 * Example 1:
 *
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 * Example 2:
 *
 * Input: costs = [[7,6,2]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 *
 * Recursive Approach
 *
 * 1) Recursion is top-down approach
 * 2) We will paint nth house with red, blue and green color
 * 3) And return min of it
 * 4) Create recursive function(costs, n, color)
 * 5) totalCost = cost[n][color]
 * 6) Base Case: If n == 0, then it last house and no more house to paint
 * 7) Return totalCost
 * 8) If color == 0; red color then totalCost += Min(f(costs, n - 1, 1), f(costs, n - 1, 2))
 * 9) If color == 1; blue color then totalCost += Min(f(costs, n - 1, 0), f(costs, n - 1, 2))
 * 10) If color == 2; green color then totalCost += Min(f(costs, n - 1, 0), f(costs, n - 1, 1))
 * 11) return totalCost
 *
 * TC: O(2^n)
 * SC: O(n)
 *
 * DP Approach
 *
 * 1) Create 2D int array of size n x 3
 * 2) Iterate row by row
 * 3) If i == 0, first house, then dp[i][j] = costs[i][j]
 * 4) else if j == 0; red color then dp[i][j] = costs[i][j] + Math.min(dp[i - 1][1], dp[i - 1][2])
 * 5) else if j == 1; blue color then dp[i][j] = costs[i][j] + Math.min(dp[i - 1][0], dp[i - 1][2])
 * 6) else if j == 2; green color then dp[i][j] = costs[i][j] + Math.min(dp[i - 1][0], dp[i - 1][1])
 * 7) return min(dp[n - 1][0], dp[n - 1][1], dp[n - 1][2])
 * 8) Instead of creating new dp table we can write results directly into the input costs table
 * 9) This will reduce space complexity to O(1)
 * 10) We could also do on-the-fly space optimization. This will not change input array
 * 11) And reduce space complexity to O(1)
 *
 * TC: O(n)
 * SC: O(n)
 */
public class PaintHouse {

    private static int minCost(int[][] costs) {

        int n = costs.length - 1;

        return Math.min(minCostHelper(n, 0, costs), Math.min(minCostHelper(n, 1, costs), minCostHelper(n, 2, costs)));
    }

    private static int minCostHelper(int n, int color, int[][] costs) {

        int totalCost = costs[n][color];

        //Base Case
        if(n == 0) { //last house so do nothing and return totalCost
        } else if(color == 0) { //Red
            totalCost += Math.min(minCostHelper(n - 1, 1, costs), minCostHelper(n - 1, 2, costs));
        } else if(color == 1) {
            totalCost += Math.min(minCostHelper(n - 1, 0, costs), minCostHelper(n - 1, 2, costs));
        } else {
            totalCost += Math.min(minCostHelper(n - 1, 0, costs), minCostHelper(n - 1, 1, costs));
        }
        return totalCost;
    }

    private static int minCostDP(int[][] costs) {

        int n = costs.length;

        int[][] dp = new int[n][3];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 3; j++) {

                if(i == 0) {
                    dp[i][j] = costs[i][j];
                } else if(j == 0) { //red
                    dp[i][j] = costs[i][j] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                } else if(j == 1) { //blue
                    dp[i][j] = costs[i][j] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                } else {
                    dp[i][j] = costs[i][j] + Math.min(dp[i - 1][0], dp[i - 1][1]);
                }
            }
        }
        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }

    public static void main(String[] args) {
        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};

        System.out.println("Recursive minCost to paint houses " + minCost(costs));
        System.out.println("DP minCost to paint houses " + minCostDP(costs));
    }
}
