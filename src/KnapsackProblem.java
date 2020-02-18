/**
 * Given weights and values of n items, put these items
 * in a knapsack of capacity W to get the maximum total value in knapsack.
 *
 * Subsets problem
 *
 * Pick an item or don't pick an item
 * resources/KnapsackProblem.png
 *
 * If the weight of nth item is greater than restWeight then don't include
 * nth item.
 *
 * 2 Possibility
 * 1) nth item included
 * 2) nth item not included
 *
 * Recursive Approach
 *
 * TC = O(2^W) where W is the number of items
 * SC = O(W)
 *
 * DP Approach
 *
 *  TC = O(items * targetWeight)
 *  SC = O(items * targetWeight)
 *
 *  resources/KnapsackProblemRecursive.jpg
 */
public class KnapsackProblem {

    private static int getMaxValueKnapsackRecursive(int[] values, int[] weight, int targetWeight, int index) {
        //Base Case
        if(targetWeight == 0) {
            return 0;
        }

        if(index == weight.length) {
            return 0;
        }

        int maxValue = -1;

        if(weight[index] <= targetWeight) {
            //include
            maxValue = Math.max(maxValue, values[index] + getMaxValueKnapsackRecursive(values, weight, targetWeight - weight[index], index + 1));
        }

        //Exclude
        return Math.max(maxValue, getMaxValueKnapsackRecursive(values, weight, targetWeight, index + 1));
    }

    private static int getMaxValueKnapsackDP(int[] values, int[] weight, int targetWeight) {

        int[][] dp = new int[values.length + 1][targetWeight + 1];

        //For targetWeight == 0, initialize the first column with 0
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        //For index == number of items + 1, 0 items left, initialize last row with 0
        for(int i = 0; i < dp[0].length; i++) {
            dp[dp.length - 1][i] = 0;
        }

        //Traversal direction
        //index = n - 0
        for(int i = dp.length - 2; i >= 0; i--) {

            //target weight 0 - 50
            for(int j = 1; j <= targetWeight; j++) {

                int maxValue = -1;

                //Avoid negatives
                if(weight[i] <= j) {
                    //include
                    maxValue = Math.max(maxValue, values[i] + dp[i + 1][j - weight[i]]);
                }

                //Exclude
                maxValue = Math.max(maxValue, dp[i + 1][j]);

                dp[i][j] = maxValue;
            }
        }
        return dp[0][targetWeight];
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int targetWeight = 50;

        System.out.println(getMaxValueKnapsackRecursive(values, weight, targetWeight, 0));
        System.out.println(getMaxValueKnapsackDP(values, weight, targetWeight));
    }
}
