package IK.DynamicProgramming.PreClass;

/**
 * Given a two dimensional grid of numbers. Find a path from top-left corner to bottom-right corner, which maximizes the sum of all numbers along its path.
 *
 * You can only move either down or right from your current position.
 *
 * Example One
 * {
 * "grid": [
 * [4, 5, 8],
 * [3, 6, 4],
 * [2, 4, 7]
 * ]
 * }
 * Output:
 *
 * 28
 * Example 1 illustration
 *
 * The path 4 -> 5 -> 8 -> 4 -> 7 maximizes the sum. Every other path from top left to bottom right has sum less than 28.
 *
 * Example Two
 * {
 * "grid": [
 * [1, -4, 3],
 * [4, -2, 2]
 * ]
 * }
 * Output:
 *
 * 5
 * The path 1 -> 4 -> -2 -> 2 maximizes the sum. Note that there can be negative values in the grid as well.
 *
 * Notes
 * Constraints:
 *
 * 1 <= number of rows <= 300
 * 1 <= number of columns <= 300
 * -104 <= numbers in the grid <= 104
 *
 * resources/MaximumSumPath
 *
 * **************************************************************************************************************************
 * Recursive Approach
 *
 * 1) Base Case: If row == 0 and col == 0, return grid[row][col]
 * 2) If row == 0, first row then there is only one path
 * 3) return grid[row][col] + recursive(row, col - 1)
 * 4) If col == 0, first col, return grid[row][col] + recursive(row - 1, col)
 * 5) return grid[row][col] + Math.max(recursive(row, col - 1), recursive(row - 1, col))
 *
 *  TC: O(2^ (m + n))
 *  SC: O(m + n)
 *
 *  DP Approach
 *
 *  1) Create 2D int array of size m and n
 *  2) Iterate row from 0 to m - 1 and col from 0 to n - 1
 *  3) If row == 0 and col == 0, dp[row][col] = grid[row][col]
 *  4) Else if row == 0, dp[row][col] = grid[row][col] + dp[row][col - 1]
 *  5) Else if col == 0, dp[row][col] = grid[row][col] + dp[row - 1][col]
 *  6) Else dp[row][col] = grid[row][col] + Math.max(dp[row][col - 1], dp[row - 1][col])
 *  7) Return dp[m - 1][n - 1]
 *
 *  TC: O(mn)
 *  SC: O(mn)
 */
public class MaximumSumPathDPAndRecursion {

    private static int maxSumPathRecursive(int[][] grid) {
        return maxSumPathRecursiveHelper(grid, grid.length - 1, grid[0].length - 1);
    }

    private static int maxSumPathRecursiveHelper(int[][] grid, int row, int col) {

        //Base Case
        if(row == 0 && col == 0) {
            return grid[row][col];
        }

        if(row == 0) {
            return grid[row][col] + maxSumPathRecursiveHelper(grid, row, col - 1);
        }

        if(col == 0) {
            return grid[row][col] + maxSumPathRecursiveHelper(grid, row - 1, col);
        }

        return grid[row][col] + Math.max(maxSumPathRecursiveHelper(grid, row, col - 1), maxSumPathRecursiveHelper(grid, row - 1, col));
    }

    private static int maxSumPathDP(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {

                if(i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if(i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if(j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] a = {{4, 5, 8}, {3, 6, 4}, {2, 4, 7}};

        System.out.println("Max sum Path Recursive " + maxSumPathRecursive(a));
        System.out.println("Max sum Path DP " + maxSumPathDP(a));
    }
}
