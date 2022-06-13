package IK.Recursion.PreClass;

/**
 * Recursive Solution
 *
 * f(r,c) =
 *   if r >= n || c >= m
 *      return 0;
 *
 *   if r == n - 1 && c == m - 1
 *      return 1;
 *
 *   return f(r, c+1) + f(r+1, c)
 *
 *   Time Complexity = O(2^(m + n))
 *   Space Complexity = O(m + n)
 *
 *   Dynamic Programming
 *
 *   1) Create table of size n + 1 and m + 1
 *   2) Fill the last row and last col with 0
 *   3) Fill the last cell with 1
 *   4) dp[i][j] = dp[i][j + 1] + dp[i + 1][j]
 *
 *   Time Complexity = O(mn)
 *   Space Complexity = O(mn)
 */
public class CountPaths {

    private static int countPathsRecursive(int[][] grid, int row, int col) {

        int numRows = grid.length;
        int numCols = grid[0].length;

        //Guard
        if(row >= numRows || col >= numCols) {
            return 0;
        }

        //Base
        if(row == numRows - 1 && col == numCols - 1) {
            return 1;
        }

        return countPathsRecursive(grid, row, col + 1) + countPathsRecursive(grid, row + 1, col);
    }

    private static int countPathsDP(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        int[][] dp = new int[numRows + 1][numCols + 1];

        //Initialize last row and last col with 0
        for(int i = 0; i <= numCols; i++) {
            dp[numRows][i] = 0;
        }

        for(int i = 0; i <= numRows; i++) {
            dp[i][numCols] = 0;
        }

        //Base Case
        dp[numRows - 1][numCols - 1] = 1;

        for(int i = numRows - 1; i >= 0; i--) {
            for (int j = numCols - 1; j >= 0; j--) {

                if (i == numRows - 1 && j == numCols - 1) {
                    continue;
                }

                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};

        System.out.println("Count Paths recursive: " + countPathsRecursive(grid, 0, 0));
        System.out.println("Count Paths DP: " + countPathsDP(grid));
    }
}
