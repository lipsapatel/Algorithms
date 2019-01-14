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
 * The above method computes the same subproblem again and again.
 * K(1,1) is being evaluated twice.
 * The Time complexity of recursive solution is 2^n which is exponential.
 *
 * resources/KnapsackProblemRecursiveTree.png
 *
 * Dynamic Programming implememntation
 */
public class KnapsackProblem {

    private static int getMaxKnapsackRecursive(int[] values, int[] weight, int restWeight, int restItems) {

        //Base Case
        if (restItems == 0 || restWeight == 0) {
            return 0;
        }

        //If nth item weight is greater than restWeight then don't include n
        if (weight[restItems - 1] > restWeight) {
            return getMaxKnapsackRecursive(values, weight, restWeight, restItems - 1);
        } else {

            //If nth item is included and not included
            return Math.max(values[restItems - 1] + getMaxKnapsackRecursive(values, weight, restWeight - weight[restItems - 1], restItems - 1),
                    getMaxKnapsackRecursive(values, weight, restWeight, restItems - 1));
        }
    }

    private static int getMaxKnapsackDP(int[] values, int[] weight, int restWeight, int restItems) {

        int[][] k = new int[restItems + 1][restWeight + 1];

        //Build table in bottom up
        for (int i = 0; i <= restItems; i++) {
            for (int w = 0; w <= restWeight; w++) {

                //Base case
                if (i == 0 || w == 0) {
                    k[i][w] = 0;
                } else if(weight[i - 1] <= w) {
                    //Weight of nth item is less than rem weight
                    //nth item included, nth item not included
                    k[i][w] = Math.max(values[i - 1] + k[i-1][w - weight[i - 1]],
                                       k[i - 1][w]);
                } else {
                    //nth item not included
                    k[i][w] = k[i - 1][w];
                }
            }
        }
        return k[restItems][restWeight];
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int restWeight = 50;
        int restItems = values.length;

        System.out.println(getMaxKnapsackRecursive(values, weight, restWeight, restItems));
        System.out.println("DP " + getMaxKnapsackDP(values, weight, restWeight, restItems));
    }
}
