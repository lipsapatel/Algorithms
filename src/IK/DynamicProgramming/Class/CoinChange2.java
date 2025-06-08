package IK.DynamicProgramming.Class;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a
 * total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 *
 * Recursive Approach
 *
 * 1) Here we need to find the number of combinations and repeating coins are allowed.
 * 2) We need to do include and exclude but since repeatation is allowed, when we include that coin we don't decrement the index.
 * 3) We do decrement the index while excluding
 * 4) Base Case: If amount == 0, return 1 We found the coins whose sum = amount
 * 5) If amount < 0 || index < 0 then return 0 //Not able to find the amount if < 0 and index < 0 that means array is empty
 * 6) Include = f(coins, amount - coins[index], index) //Don't decrement index because we can repeat the coin
 * 7) Exclude = f(coins, amount, index - 1)
 *
 * TC: O(2^amount)
 * SC: O(amount)
 *
 * DP Approach
 *
 * 1) Two parameters changing amount and index
 * 2) Return type int
 * 3) Create 2D int table of size amount + 1 and n + 1 where n = coins.length
 * 4) Iterate DP table in row major order
 * 5) If i == 0 (amount == 0), dp[i][j] = 1
 * 6) If j == 0 (index < 0, array empty), dp[i][j] = 0
 * 7) if (amount - coins[index] >= 0) so if(i - coins[j - 1] >= 0)
 * 8) Include = dp[i - coins[j - 1]][j] else include = 0
 * 9) Exclude = dp[i][j - 1]
 * 10) dp[i][j] = include + exclude
 * 11) Return dp[amount][n]
 *
 * TC: O(amount * n)
 * SC: O(amount * n)
 *
 * We can do on the fly space optimization with maintaining 2 columns
 * Doing so SC: O(amount)
 */
public class CoinChange2 {

    public static int coinChange(int amount, int[] coins) {

        int index = coins.length - 1;

        return coinChangeHelper(amount, index, coins);
    }

    public static int coinChangeHelper(int amount, int index, int[] coins) {
        //Base Case
        if(amount == 0) { //Found the change
            return 1;
        }

        if(amount < 0 || index < 0) { //Not able to find change
            return 0;
        }

        //Include + exclude
        return coinChangeHelper(amount - coins[index], index, coins) + coinChangeHelper(amount, index - 1, coins);
    }

    public static int coinChangeDP(int amount, int[] coins) {
        int n = coins.length;

        int[][] dp = new int[amount + 1][n + 1];

        for(int i = 0; i <= amount; i++) {

            for(int j = 0; j <= n; j++) {

                if(i == 0) { //amount == 0, found the change
                    dp[i][j] = 1;
                } else if(j == 0) { //index < 0, no change
                    dp[i][j] = 0;
                } else {

                    int include = 0;
                    if(i - coins[j - 1] >= 0) {
                        include = dp[i - coins[j - 1]][j]; //The element can be repeated so don't decrement index
                    }

                    int exclude = dp[i][j - 1];

                    dp[i][j] = include + exclude;
                }
            }
        }
        return dp[amount][n];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};

        System.out.println(coinChange(amount, coins));
        System.out.println(coinChangeDP(amount, coins));
    }
}
