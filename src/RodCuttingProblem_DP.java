import java.util.Arrays;

/**
 * Given a rod of n inches and a table of prices. Write an algorithm to find maximum revenue
 * by cutting rod and selling pieces.
 *
 * Example:
 *
 * Length: 0 1  2  3  4  5  6  7  8  9  10
 * Price:  0 1  5  8  9  10 17 17 20 24 30
 *
 * For rod of length 4
 *
 * Ways to sell:
 *
 * Cut into 2,2 pieces - Price: 10
 * Cut into 1, 1, 1, 1 pieces - Price: 4
 * Cut into 3, 1 pieces - Price: 9
 * Cut into 2, 1, 1 pieces - Price: 7
 * 4 - Price: 9
 *
 * Naive Approach: Recursion
 *
 * 1) There can be n - 1 cuts made on the rod of length n.
 * 2) There are n^n - 1 ways to cut rod
 * 3) For every length we have 2 options whether to cut the rod or not.
 *
 * Time Complexity: n^n-1 ~ O(n!)
 * Space Complexity: O(n)
 * But this complexity is very high since we are solving many subproblems repeatedly.
 *
 * resources/RodCuttingProblemOverlappingSubproblems.png
 * resources/RodCuttingProblem.png
 *
 * Dynamic Programming:
 *
 * Bottoms up
 *
 * 1) Instead of solving problem again and again we can store the result in the array and use it.
 *
 * resources/RodCuttingPrintPaths.jpg
 */
public class RodCuttingProblem_DP {

    //TC = O(n^n)
    //SC = O(n)
    private static int rodCuttingProblemMaximumRevenue_UsingRecursion(int[] price, int rodLength) {

        if (rodLength == 0) {
            return 0;
        }

        int max = -1;
        //Either we will cut the rod or not
        for (int i = 1; i <= rodLength; i++) {

            max = Math.max(max, price[i] + rodCuttingProblemMaximumRevenue_UsingRecursion(price, rodLength - i));
        }
        return max;
    }

    private static int rodCuttingRecursionMemorization(int[] price, int rodLength) {
        int[] bestPrice = new int[rodLength + 1];
        Arrays.fill(bestPrice, -1);

        return rodCuttingRecursionMemorizationHelper(price, rodLength, bestPrice);
    }

    private static int rodCuttingRecursionMemorizationHelper(int[] price, int rodLength, int[] bestPrice) {

        //Base Case
        if(rodLength == 0) {
            bestPrice[0] = 0;
            return 0;
        }

        if(bestPrice[rodLength] != -1) { //already computed, return that
            return bestPrice[rodLength];
        }

        //Recursive case
        int max = -1;
        for(int i = 1; i <= rodLength; i++) {
            max = Math.max(max, price[i] + rodCuttingRecursionMemorizationHelper(price, rodLength - i, bestPrice));
        }
        bestPrice[rodLength] = max;
        return max;
    }

    //TC = O(n^2)
    //SC = O(n)
    private static int rodCuttingProblemMaximumRevenue_DP(int[] price, int rodLength) {

        int[] dp = new int[rodLength + 1];
        dp[0] = 0; //Base Case

        for (int i = 1; i <= rodLength; i++) { //Length
            int max = -1;
            for (int j = 1; j <= i; j++) { //Cut length

                max = Math.max(max, price[j] + dp[i - j]);
            }
            dp[i] = max;
        }

        printPaths(dp, price, rodLength);
        System.out.println();
        return dp[rodLength];
    }

    private static void printPaths(int[] dp, int[] price, int length) {
        if(length == 0) { //no further cuts
            return;
        }
        int maxPrice = dp[length];

        //Cuts
        for(int i = 1; i <= length; i++) {
            int remPrice = maxPrice - price[i];
            int remCut = length - i;

            //best price for rem cut == rem price
            if(dp[remCut] == remPrice) {
                System.out.print(i + " ");
                printPaths(dp, price, length - i);
                break; //print only one path
            }
        }
    }

    public static void main(String[] args) {
        int[] value = {0, 2, 3, 7, 8, 9 };
        int len = 5;
        System.out.println("Max profit for length is " + len + ":"
                + rodCuttingProblemMaximumRevenue_UsingRecursion(value, len));

        System.out.println("Max profit for length using recursion + memorization is " + len + ":"
                + rodCuttingRecursionMemorization(value, len));


        System.out.println("Max profit for length is " + len + ":"
                + rodCuttingProblemMaximumRevenue_DP(value, len));
    }
}
