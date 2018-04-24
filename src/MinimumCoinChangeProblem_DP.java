/**
 * Given an amount A and n coins, write a program to find minimum number of coins required
 * to make the change for amount A.
 *
 * Example: Amount = 5
 * coins = {1, 2, 3}
 *
 * No of ways to make change
 * {1, 1, 1, 1, 1}, {1, 1, 3}, {1, 1, 1, 2}, {2, 3}, {2, 2, 1}
 *
 * Minimum number of coins Required = 2
 *
 * For every coin we have two options, whether to select it or not. We will try both the options
 * and select the optimal out of it which is minimum.
 *
 * Break the problem into smaller problems
 *
 * MC(j) = minimum number of coins required to make the change for amount j.
 *
 * Select first coin value = v1
 * Minimum number of coins required to make change =  MC(j - v1)
 *
 * MC(j - v2)
 *
 * and so on
 *
 * So we will select minimum of smaller problem and add 1 to it because we have select 1 coin
 *
 * Using Recursion every coin has two options - selected or unselected
 * Time Complexity: O(c^n)
 *
 * Dynamic Programming:
 * Bottom-up Approach
 *
 * 1) Maintain an array coinsRequiredToMakeAmount[amount + 1]
 * 2) Answer = coinsRequiredToMakeAmount(n)
 * 3) coinsRequiredToMakeAmount(0) = 0
 * 4) CC[] = size of coins, This will store the solution for amount n using all the coins
 * 5) Minimum of CC = coinsRequiredToMakeAmount(n)
 * 6) Reset CC every time for amount 1 to n
 */
public class MinimumCoinChangeProblem_DP {

    private static int minimumNumberOfCoinsRequiredToMakeChange(int[] coins, int amount) {

        //Store the optimal(minimum) solution for amount 0 to amount
        int[] coinsRequiredToMakeAmount = new int[amount + 1];

        int[] CC = new int[coins.length]; //Reset for every amount

        //0 coins required to make amount 0
        coinsRequiredToMakeAmount[0] = 0;

        //Solve for all the amounts
        for (int amt = 1; amt <= amount; amt++) {

            //Reset CC
            for (int j = 0; j < CC.length; j++) {

                CC[j] = -1;
            }

            //Take one coin at a time and fill CC
            for (int j = 0; j < coins.length; j++) {

                //Check if the coin value is less than equal to amount
                if (coins[j] <= amt) {

                    //Add 1 because you selected one coin
                    CC[j] = coinsRequiredToMakeAmount[amt - coins[j]] + 1;

                }
            }

            //Find the minimum of CC and store it in coinsRequiredToMakeAmount(amt)
            coinsRequiredToMakeAmount[amt] = -1;

            for (int j = 0; j < CC.length; j++) {
                if (CC[j] > 0 && (coinsRequiredToMakeAmount[amt] == -1 ||
                        coinsRequiredToMakeAmount[amt] > CC[j])) {
                    coinsRequiredToMakeAmount[amt] = CC[j];
                }
            }

        }

        //Return the optimal solution for the amount
        return coinsRequiredToMakeAmount[amount];

    }

    public static void main(String[] args) {
        int[] coins = { 1, 2, 3 };
        int amount = 20;

        System.out.println("(Dynamic Programming) Minimum Coins required to make change for "
                + amount + " are: " + minimumNumberOfCoinsRequiredToMakeChange(coins, amount));
    }
}
