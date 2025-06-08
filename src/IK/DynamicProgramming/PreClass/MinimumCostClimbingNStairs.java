package IK.DynamicProgramming.PreClass;

/**
 * There are n stairs indexed 0 to n – 1. Each stair has a cost associated with it. After paying the cost, it's allowed either to
 * climb one or two steps further. It's allowed to either start from the step with index 0, or the step with index 1.
 * Given the cost array, find the minimum cost to reach the top of the floor.
 * cost[i] represents the cost of i-th stair.
 *
 * Example One
 * {
 * "cost": [1, 1, 2]
 * }
 * Output:
 *
 *
 * There are 5 ways to reach the top floor.
 *
 * step 0 → step 1 → step 2 → top floor.
 * step 0 → step 1 → top floor.
 * step 0 → step 2 → top floor.
 * step 1 → step 2 → top floor.
 * step 1 → top floor.
 * Here, the last way(step 1 → top floor) will provide the most optimal cost 1.
 *
 * Example Two
 * {
 * "cost": [3, 4]
 * }
 * Output:
 *
 * 3
 * Notes
 * Constraints:
 *
 * 2 <= length of the input array <= 1000
 * 0 <= cost[i] <= 999, for all i.
 *
 * ***************************************************************************************************************************
 *
 * There is no cost floor below and floor above.
 * And the cost for first step or 0th step is cost[0]
 *
 * The cost for floor below and floor above is 0.
 *
 * Recursive Approach
 *
 * 1) When you reach the floor below or first(0th step), the cost for floor below is 0 and 0th step is cost[0]
 * 2) For ith step, the minCost = x + Math.min(func(i - 1), func(i - 2)) where x = 0 if i == n else x = cost[i]
 * 3) The cost for last step or floor above is 0.
 * 4) Base Case: If n == -1(Floor below) return 0
 * 5) If n == 0, return cost[0]
 * 6) Recursive Case: x = (n == cost.length) ? 0: cost[n]
 * 7) Return x + Math.min(func(n - 1), func(n - 2))
 *
 * TC: O(2^n)
 * SC: O(n)
 *
 * DP Approach
 * 1) Create 1D int array of size n + 2 which includes floor below and floor above
 * 2) dp[0] = 0, floor below
 * 3) dp[1] = cost[0] For first step, cost is at 0th index.
 * 4) Iterate from i = 2 to n + 1
 * 5) x = (i == n + 1) ? 0 : cost[i - 1] //Here we are doing i - 1 because cost index is one less for each step, For step 2, cost index is 1
 * 6) dp[i] = x + Math.min(dp[i - 1], dp[i - 2])
 * 7) return dp[n + 1]
 *
 * TC: O(n)
 * SC: O(n)
 */
public class MinimumCostClimbingNStairs {

    private static int minCostClimbingStairsRecursive(int[] cost, int n) {

        //Base Case
        if(n == -1) { //Floor below cost is 0
            return 0;
        }

        if(n == 0) { //First Step
            return cost[0];
        }

        int x = (n == cost.length) ? 0: cost[n]; //For floor above cost is 0

        return x + Math.min(minCostClimbingStairsRecursive(cost, n - 1), minCostClimbingStairsRecursive(cost, n - 2));
    }

    private static int minCostClimbingStairsDP(int[] cost, int n) {

        //Count floor below and floor above
        int[] dp = new int[n + 2];

        dp[0] = 0; //Floor Below
        dp[1] = cost[0]; //First step, cost index is one less

        for(int i = 2; i < n + 2; i++) {
            int x = (i == n + 1) ? 0: cost[i - 1]; //For floor above cost is 0. Cost index is one less

            dp[i] = x + Math.min(dp[i - 1], dp[i - 2]);
        }
        return dp[n + 1];
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};

        System.out.println("The minimum Cost climbing stairs using recursion " +minCostClimbingStairsDP(cost, 3));
        System.out.println("The minimum cost climbing stairs using DP " +minCostClimbingStairsDP(cost, 3));

        int[] cost1 = {10, 15, 20, 10, 12};

        System.out.println("The minimum Cost climbing stairs using recursion " +minCostClimbingStairsDP(cost1, 5));
        System.out.println("The minimum cost climbing stairs using DP " +minCostClimbingStairsDP(cost1, 5));
    }
}
