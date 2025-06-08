package IK.DynamicProgramming.PreClass;

/**
 * N Choose R
 * Consider you have n distinct elements, find the number of ways through which you can choose exactly r number of elements out of those.
 *
 * Example One
 * {
 * "n": 5,
 * "r": 3
 * }
 * Output:
 *
 * 10
 * There is a set of 5 elements {1, 2, 3, 4, 5}. You need to pick a subset of size 3. Eligible subsets are {1, 2, 3}, {1, 2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}. There are 10 subsets of size 3.
 *
 * Example Two
 * {
 * "n": 3,
 * "r: 5
 * }
 * Output:
 *
 * 0
 * There is a set of 3 elements {1, 2, 3}. You need to pick a subset of size 5. Which is not possible, that's why the answer is 0.
 *
 * Notes
 * Here the answer can be very big, find it modulo 10^9 + 7.
 *
 * Constraints:
 *
 * 0 <= n, r <= 1000
 *
 * ********************************************************************************************************************************************************
 * Count number of subsets of size k given n items.
 *
 * Recursive Approach
 * 1) Base Case: If k == 0 or k == n, return 1 because there is only one way to choose subset of size 0 and n
 * 2) Recursive Case: Include (n - 1, k - 1) and Exclude (n - 1, k) and add them
 *
 * TC: O(2^n)
 * SC: (n)
 *
 * DP Approach
 * 1) Create 2D int array of size n + 1 and k + 1
 * 2) For k = 0, its 1 so set first column 1
 * 3) For k == n, its 1 so diagonal is 1. Iterate till min(row, k)
 * 4) dp[row][col] = dp[row - 1][col - 1] + dp[row - 1][col]
 * 5) Return dp[n][k]
 * 6) MOD the result with 10^9 + 7 because the result can be very big.
 *  *
 * TC: O(nk)
 * SC: O(nk)
 */
public class CountSubsetsOfSizek {

    private static final int MOD = 1000000007;

    private static int subsetOfSizeKRecursive(int n, int k) {

        //Base Case
        if(k == 0 || k == n) {
            return 1;
        } else { //Recursive Case
            return subsetOfSizeKRecursive(n - 1, k - 1) + subsetOfSizeKRecursive(n - 1, k);
        }
    }

    private static int subsetOfSizeKDP(int n, int k) {
        //Edge Case
        if(k > n) {
            return 0;
        }

        int[][] dp = new int[n + 1][k + 1];

        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= Math.min(row, k); col++) {

                if(row == col || col == 0) {
                    dp[row][col] = 1;
                } else {
                    dp[row][col] = (dp[row - 1][col - 1] + dp[row - 1][col]) % MOD;
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int n = 10;
        int k = 5;

        System.out.println("The subset of size 5 from 10 items is (Using Recursion) " + subsetOfSizeKRecursive(n, k));
        System.out.println("The subset of size 5 from 10 items is (Using DP) " + subsetOfSizeKDP(n, k));
    }
}
