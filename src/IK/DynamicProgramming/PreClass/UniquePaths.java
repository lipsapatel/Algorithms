package IK.DynamicProgramming.PreClass;

/**
 * Unique Paths
 * Given a grid of size n x m and a robot initially staying at the top-left position, return the number of unique paths for the robot to reach the bottom-right corner of the grid. The robot is allowed to move either down or right at any point in time.
 *
 * Example One
 * {
 * "n": 3,
 * "m": 2
 * }
 * Output:
 *
 * 3
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 *
 * Right -> Down -> Down
 * Down -> Down -> Right
 * Down -> Right -> Down
 * Example Two
 * {
 * "n": 4,
 * "m": 1
 * }
 * Output:
 *
 * 1
 * From the top-left corner, there is only one way to reach bottom-right corner:
 *
 * Down -> Down -> Down
 * Notes
 * Return the answer modulo 109 + 7.
 *
 * Constraints:
 *
 * 1 <= n, m <= 103
 *
 * ***********************************************************************************************
 *
 * Recursive Approach
 * 1) Base Case: If m == 0 or n == 0, return 1 because there is only one path for first row and col
 * 2) Recursive Case: return func(m - 2, n - 1) + func(m - 1, n - 2)
 *
 * TC: O(2^m + n)
 * SC: O(m + n)
 *
 * DP Approach
 * 1) Create 2D int array of size m x n
 * 2) Fill the first row and first col with 1
 * 3) dp[row][col] = dp[row - 1][col] + dp[row][col - 1]
 * 4) Return dp[m - 1][n - 1]
 *
 * TC: O(mn)
 * SC: O(mn)
 */
public class UniquePaths {

    private static int uniquePathsRecursive(int m, int n) {
        //Base Case - Only one row or one column
        if(m == 0 || n == 0) {
            return 1;
        }

        return uniquePathsRecursive(m - 1, n - 2) + uniquePathsRecursive(m - 2, n - 1);
    }

    private static int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];
        final int MOD = 1000000007;

        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {

                //Base Case
                if(row == 0 || col == 0) {
                    dp[row][col] = 1;
                } else {
                    dp[row][col] = (dp[row - 1][col] + dp[row][col - 1]) % MOD;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Using recursion 3 x 2: " + uniquePathsRecursive(3, 2));
        System.out.println("Using DP 3 x 2: " + uniquePathsDP(3, 2));
    }
}
