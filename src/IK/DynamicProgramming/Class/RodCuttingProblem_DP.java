package IK.DynamicProgramming.Class;

import java.util.Arrays;

/**
 * Given a rod of n inches and a table of prices. Write an algorithm to find
 * maximum revenue by cutting rod and selling pieces.
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
 * 1) There can be n  cuts made on the rod of length n.
 * 2) There are n^n  ways to cut rod
 * 3) For every length we have 2 options whether to cut the rod or not.
 * 4) for(int cutSize = 1 to rodSize)
 * 5) maxProfit = Max(price[cutSize] + f(rodSize - cutSize), maxProfit)
 * 6) Return maxProfit
 * 7) Base case: if rodSize == 0, return 0
 *
 * Time Complexity: O(2^n) - At every index we are deciding whether to cut the rod or not
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
 * 1) Instead of solving problem again and again we can store the result in the
 * array and use it.
 * 2) One changing parameter which is rod length so 1D array
 * 3) Need to return max profit which is int so int array
 * 4) Rod length from 0 to n so size = n + 1
 * 5) Traverse from left to right
 * 6) Initialize dp[0] = 0 and rest with Integer.MIN_VALUE
 * 7) For rodLength = 1 to n
 * 8) For cutSize = 1 to rodLength
 * 9) dp[rodLength] = Max(prices[cutSize] + dp[rodLength - cutSize], dp[rodLength])
 * 10) return dp[rodLength]
 *
 * TC: O(n^2)
 * SC: O(n) - On the fly space optimization cannot be done because it depends
 * on previous n - 1 subproblems
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
        for (int cutSize = 1; cutSize <= rodLength; cutSize++) {

            max = Math.max(max, price[cutSize] + rodCuttingProblemMaximumRevenue_UsingRecursion(price, rodLength - cutSize));
        }
        return max;
    }

    private static int rodCuttingRecursionMemorization(int[] price, int rodLength) {
        int[] bestPrice = new int[rodLength + 1];
        Arrays.fill(bestPrice, -1);

        return rodCuttingRecursionMemorizationHelper(price, rodLength, bestPrice);
    }

    //TC: O(n^2) and SC: O(n) but here we have recursive call stack overhead.
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
        for(int cutSize = 1; cutSize <= rodLength; cutSize++) {
            bestPrice[rodLength] = Math.max(bestPrice[rodLength], price[cutSize] + rodCuttingRecursionMemorizationHelper(price, rodLength - cutSize, bestPrice));
        }
        return bestPrice[rodLength];
    }

    //TC = O(n^2)
    //SC = O(n)
    private static int rodCuttingProblemMaximumRevenue_DP(int[] price, int rodLength) {

        int[] dp = new int[rodLength + 1];

        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[0] = 0; //Base Case

        for (int rodSize = 1; rodSize <= rodLength; rodSize++) { //Rod Length
            for (int cutSize = 1; cutSize <= rodSize; cutSize++) { //Cut length

                dp[rodSize] = Math.max(dp[rodSize], price[cutSize] + dp[rodSize - cutSize]);
            }
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
        for(int cutSize = 1; cutSize <= length; cutSize++) {
            int remPrice = maxPrice - price[cutSize];
            int remCut = length - cutSize;

            //best price for rem cut == rem price
            if(dp[remCut] == remPrice) {
                System.out.print(cutSize + " ");
                printPaths(dp, price, length - cutSize);
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
