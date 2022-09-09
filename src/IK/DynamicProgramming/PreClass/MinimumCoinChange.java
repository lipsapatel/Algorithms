package IK.DynamicProgramming.PreClass;

import java.util.Arrays;

/**
 * Given a variety of coin types defining a currency system, find the minimum number of coins required to express a given amount of money.
 * Assume infinite supply of coins of every type.
 *
 * Example
 * {
 * "coins": [1, 3, 5],
 * "value": 9
 * }
 * Output:
 *
 * 3
 * Here are all the unique ways to express 9 as a sum of coins 1, 3 and 5:
 *
 * 1, 1, 1, 1, 1, 1, 1, 1, 1
 * 1, 1, 1, 1, 1, 1, 3
 * 1, 1, 1, 1, 5
 * 1, 1, 1, 3, 3
 * 1, 3, 5
 * 3, 3, 3
 * Last two ways use the minimal number of coins, 3.
 *
 * Notes
 * There will be no duplicate coin types in the input.
 *
 * Constraints:
 *
 * 1 <= number of coin types <= 102
 * 1 <= coin value <= 102
 * 1 <= amount of money to express <= 104
 ******************************************************************************
 *
 * If amount is 0, the number of coins required are 0
 * If amount is negative, there is no answer to return MAX or -1
 *
 * Recursive Approach
 *
 * 1) As a lazy manager, I will ask my subordinate to find the number coins required for amount n - 1, n - 2 and n -5 if the coins array has [1, 2, 5]
 * 2) Base Case: If amount is 0, return 0
 * 3) If amount < 0 then return Integer.MAX_VALUE
 * 4) Recursive Function = 1 + Min(func(amt - coin[i]), func(amt - coin[i + 1]) ....)
 * 5) Iterate coin array and initialize min with Integer.MAX_VALUE
 * 6) min = Math.min(min, func(amt - coin[i]))
 * 7) Return min + 1
 *
 * TC: O(k^a) If there are k coins there will be k branches and a = amount
 * SC: O(a) where a = amount
 *
 * DP Approach
 *
 * 1) Create 1D int table of length a + 1
 * 2) Initialize table with Integer.MAX_VALUE
 * 3) dp[0] = 0, for amount 0, coins required are 0
 * 4) Iterate from i = 0 to a
 * 5) Iterate coins array
 * 6) If i - coin[j] >= 0
 * 7) dp[i] = Math.min(dp[i], dp[i - coins[j]])
 * 8) Return dp[a]
 *
 * TC: O(ak) where a = amount k = number of coins
 * SC: O(a)
 */
public class MinimumCoinChange {

    private static int minCoinChangeRecursive(int[] coins, int amt) {

        //Base Case
        if(amt == 0) {
            return 0;
        }

        if(amt < 0) {
            return Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < coins.length; i++) {
            min = Math.min(min, minCoinChangeRecursive(coins, amt - coins[i]));
        }
        return min + 1;
    }

    private static int minCoinChangeDP(int[] coins, int amt) {
        int[] dp = new int[amt + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for(int i = 1; i <= amt; i++) {

            for(int c = 0; c < coins.length; c++) {
                if(i - coins[c] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[c]]);
                }
            }
            dp[i] = dp[i] + 1;
        }
        return dp[amt];
    }

    public static void main(String[] args) {
        int[] coins = {1, 3, 5};
        System.out.println("Minimum coins required Recursive for amt 9 " + minCoinChangeRecursive(coins, 9));
        System.out.println("Minimum coins required DP for amt 9 " + minCoinChangeDP(coins, 9));
    }
}
