/**
 * Coin Play
 * Consider a row of n coins of values v1, . . ., vn. We play a game against an opponent by alternating turns.
 * In each turn, a player selects either the first or last coin from the row, removes it from the row permanently,
 * and receives the value of the coin. Determine the maximum possible amount of money we can definitely win if we move first.
 * Assume full competency by both players.
 *
 * If n = 4 and v = [8, 15, 3, 7], then input should be:
 * 4
 * 8
 * 15
 * 3
 * 7

 * Output Format:
 * There will be only one line, containing an integer max, denoting the maximum possible amount of sum that you can accumulate.
 * For input n = 4 and v = [8, 15, 3, 7], output will be:
 * 22
 *
 * Constraints:
 *     1 <= n <= 1000
 *     1 <= v[i] <= 10^6

 * Sample Test Case:
 * v = [8, 15, 3, 7]

 * Sample Output:
 * 22
 *
  Explanation:
 * Player 1 can start two different ways: either picking 8 or picking 7. Depending on the choice s/he makes, the end reward will be different.
 * We want to find the maximum reward the first player can collect.
 *
 * 1. Player 1 start by picking coin of amount 8.
 * Remaining v = [15, 3, 7].
 *
 * Opponent will have two choices, either pick coin of value 15 or 7. Opponent will always pick 15 (to maximize his/her own amount).
 * Remaining v = [3, 7].
 *
 * Player 1 will have two choices, either pick coin of value 3 or 7.
 * Player 1 will always pick 7 (to maximize his/her own amount).
 * Hence in this case, player 1 can get maximum amount 8 + 7 = 15.

 * (This is greedy strategy i.e. pick the highest at every step.)

 * 2. Player 1 start by picking coin of amount 7.
 * Remaining v = [8, 15, 3].
 * Opponent will have two choices, either pick coin of value 8 or 3.
 * Opponent will pick 8 (to maximize his/her own amount). (Even if he/she picks 3, then also answer will be same,
 * because in next turn player 1 is looking for coin of value 15.)
 *
 * Remaining v = [15, 3].
 * Player 1 will have two choices, either pick coin of value 15 or 3.
 * Player 1 will always pick 15 (to maximize his/her own amount).
 * * Hence in this case, player 1 can get maximum amount 7 + 15 = 22.
 * *
 * Given these two strategies, we want 22 as the answer, and not 15.
 *
 * **********************************************************************************************************************
 * Solution
 Recursive solution
 There are two choices:
 1. The user chooses the ith coin with value Vi:
 The opponent either chooses (i+1)th coin or jth coin. The opponent intends to choose the coin which leaves the user with minimum value.
 i.e. The user can collect the value Vi + min(F(i+2, j), F(i+1, j-1) )

 coinGame1
 2. The user chooses the jth coin with value Vj: The opponent either chooses ith coin or (j-1)th coin.
 The opponent intends to choose the coin which leaves the user with minimum value.
 i.e. The user can collect the value Vj + min(F(i+1, j-1), F(i, j-2) )

 Recurrence relationship
 F(i, j) ==> represents the maximum value the user can collect from i'th coin to j'th coin.
 F(i, j) = Max(Vi + min(F(i+2, j), F(i+1, j-1)), Vj + min(F(i+1, j-1), F(i, j-2) ))

 Base Cases
 F(i, j) = Vi      If j == i
 F(i, j) = max(Vi, Vj) If j == i+1

 Time Complexity: O(2 ^ n)
 Space Complexity: O(n)

 Optimal solution
 We can memoize the recurrence relationship mentioned above or build an iterative version for the same problem.

 Space Complexity: O(n^2)
 Time Complexity: O(n^2)

 resources/CoinPlayRecursion.jpg
 resources/CoinPlayDp.jpg
 */
public class CoinPlay {

    private static int maxWinRecursion(int[] v) {
        return maxWinRecursionHelper(v, 0, v.length - 1);
    }

    private static int maxWinRecursionHelper(int[] v, int i, int j) {
        //Base Case
        if(i == j) { //one element
            return v[i];
        }

        if(i + 1 == j) { //two element
            return Math.max(v[i], v[j]);
        }

        return Math.max(v[i] + Math.min(maxWinRecursionHelper(v, i + 2, j), maxWinRecursionHelper(v, i + 1, j - 1)),
                        v[j] + Math.min(maxWinRecursionHelper(v, i + 1, j - 1), maxWinRecursionHelper(v, i, j - 2)));
    }

    private static int maxWinDp(int[] v) {

        //Identify the Dp table
        int[][] dp = new int[v.length][v.length];

        //Initialize dp table - Base case
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp.length; j++) {

                if(i == j) {
                    dp[i][j] = v[i];
                }

                if(i + 1 == j) {
                    dp[i][j] = Math.max(v[i], v[j]);
                }
            }
        }

        //Traversal direction
        for(int i = v.length - 3; i >= 0; i--) {
            for(int j = i + 2; j < dp.length; j++) {

                //Populate dp table
                dp[i][j] = Math.max(v[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]),
                                    v[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]));
            }
        }
        return dp[0][v.length - 1];
    }

    public static void main(String[] args) {
        int[] v = {8, 15, 3, 7};
        System.out.println("The max amount I can win " + maxWinRecursion(v));
        System.out.println("The max amount I can win using dp " + maxWinDp(v));
    }
}