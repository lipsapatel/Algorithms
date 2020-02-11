import java.util.Arrays;

/**
 * Find the minimum number of coins required to make the change
 * Input:
 * {2, 3, 5}
 * Amount = 8
 *
 * Output: 2
 *
 * Recursive approach
 *
 * 1) Find the min number of coins required to make amount = (amount - 2), (amount - 3) and (amount -5)
 * 2) Take min of all 3 and add 1
 * 3) return that.
 * 4) If amount = 0, 0 coins required
 * 5) If amount < 0, then Integer.MAX_VALUE
 *
 * Time Complexity: O(K ^ A)
 * Space Complexity: O(A)
 *
 * resources/MinCoinsRequiredToMakeChange.jpg
 *
 * DP Approach
 *
 * 1) Amount is changing, so create dp table with size amount + 1
 * 2) Fill it with Integer.MAX_VALUE
 * 3) 0th index value is 0
 * 4) iterate the amount array and for each amount, iterate coins and find the (1 + min(all coins))
 * 5) Return the last value of dp table
 *
 * Time Complexity: O(KA)
 * Space Complexity: O(A)
 *
 * resources/MinCoinsRequiredToMakeChangeDp.jpb
 *
 */
public class MinCoinsRequiredToMakeChange {

    private static int minCoins(int[] coins, int amount) {

        //Base Case
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        int minCoins = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++) {
            int coinsCount = minCoins(coins, amount - coins[i]);

            if(coinsCount < minCoins) {
                minCoins = coinsCount;
            }
        }

        //To avoid overflow
        if(minCoins != Integer.MAX_VALUE) {
            return 1 + minCoins;
        } else {
            return minCoins;
        }
    }

    private static int minCoinsDp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i < dp.length; i++) {
            int minCount = Integer.MAX_VALUE;

            for(int j = 0; j < coins.length; j++) {
                if(coins[j] <= i) {
                    int count = dp[i - coins[j]];

                    if(count < minCount) {
                        minCount = count;
                    }
                }
            }

            //To avoid overflow
            if(minCount != Integer.MAX_VALUE) {
                dp[i] = 1 + minCount;
            }
        }

        System.out.println("The coins required to make the change");
        printCoins(coins, dp, dp.length - 1);
        System.out.println();

        System.out.println("The coins required to make the change - iterative method");
        printCoinsIterative(coins, dp, dp.length - 1);
        System.out.println();

        return dp[dp.length - 1];
    }

    //Recursive print coins
    private static void printCoins(int[] coins, int[] dp, int amount) {
        if(amount == 0) {
            return;
        }

        if(amount < 0) {
            return;
        }

        int noOfCoins = dp[amount] - 1;

        for (int i = 0; i < coins.length; i++) {
            int remainingAmount = amount - coins[i];

            if(dp[remainingAmount] == noOfCoins) {
                System.out.print(coins[i] + " ");
                printCoins(coins, dp, remainingAmount);
                return;
            }
        }
    }

    //Iterative print coins
    private static void printCoinsIterative(int[] coins, int[] dp, int amount) {

        while(amount > 0) {
            int noOfCoins = dp[amount] - 1;

            for(int i = 0; i < coins.length; i++) {
                int remainingAmount = amount - coins[i];

                if(dp[remainingAmount] == noOfCoins) {
                    System.out.print(coins[i] + " ");
                    amount = remainingAmount;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] coins = {2, 3, 5};
        int amount = 8;

        System.out.println("The minimum number of coins required to make the change is " + minCoins(coins, amount));
        System.out.println("The minimum number of coins required to make the change using dp is " + minCoinsDp(coins, amount));
    }
}
