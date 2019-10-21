/**
 * Given a set of coins and amount, write an algorithm to find out how many ways we can make
 * a change of amount using the coin given.
 *
 * Example:
 * Amount: 5
 * Coins = {1, 2, 3}
 *
 * Ways to make change = 5
 * {1, 1, 1, 1, 1}, {2, 3}, {1, 1, 3}, {1, 1, 1, 2}, {2, 2, 1}
 *
 * Approach:
 *
 * Recursive Solution:
 *
 * 1) For every coin, we have an option to include it or exclude it.
 *
 * Time Complexity: O(2^n)
 * For every coin we have 2 options:
 * Include it
 * Exclude it
 *
 * If we think in terms of binary (1) for include and (0) for exclude
 * So if we have two coins the options will be 00,01, 10, 11
 * 2^2
 *
 * Dynamic Programming
 *
 * 1) We will use bottom-up approach
 * 2) create solution[coins + 1][amount + 1]
 * 3) If amount = 0 then we return empty set so there's 1 way to make change
 * 4) If coins = 0 then 0 ways to make change
 * 5) For every coin we have an option to include it or exclude it.
 *
 *                                      0                                 i = 0 coins = 0
 *                                      1                                 j = 0 amount = 0
 * solution[i][j]    solution[i - 1][j] + solution[i][j - coins[i - 1]]   if (coins[i] <= j)
 *                   solution[i - 1][j]                                   if (coins[i] > j)
 *
 * resources/CoinChangeProblem.png
 *
 * TC = O(m x n) where m = coins length and n = amount
 * SC = O(m x n)
 */
public class CoinChangeProblem_DP {

    private static int numberOfWaysToGetChange(int[] coins, int amount) {

        int[][] solution = new int[coins.length + 1][amount + 1];

        //If amount = 0, then number of ways to have change = 1 which is empty set
        for (int i = 0; i <= coins.length; i++) {
            solution[i][0] = 1;
        }

        //If coins = 0 no coins are given then 0 ways to get change for amount
        for (int i = 0; i <= amount; i++) {
            solution[0][i] = 0;
        }

        //Fill the rest of the matrix
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {

                //If coins is less than equal to amount
                if (coins[i - 1] <= j) {

                    //Add the solution from the top and reduce the amount by coin
                    //coins[i - 1] because the coins array is from index 0
                    //Exclude + include
                    solution[i][j] = solution[i - 1][j] + solution[i][j - coins[i - 1]];
                } else {

                    solution[i][j] = solution[i - 1][j];
                }
            }
        }

        return solution[coins.length][amount];
    }

    public static void main(String[] args) {

        int amount = 5;
        int[] v = { 1, 2, 3 };
        System.out.println("The number of ways to get change by Dynamic Programming " +
                numberOfWaysToGetChange(v, amount));
    }
}
