package IK.Recursion.Class;

/**
 * Find the maximum Sum path.
 * Given 2d array with values, find the path whose sum is maximum
 *
 * Approach: Recursion
 * 1) Recursive Function (grid, row, col)
 * 2) Guard case: If row >= numRows || col >= numCols then return Integer.MIN_VALUE
 * 3) Base Case: If row == numRows - 1 and col == numCols - 1 then return the value in that cell.
 * 4) Recursive Case: Right = (grid, row, col + 1)
 * 5) Down = (grid, row + 1, col)
 * 6) Take max (Right, Down) and add yourself to it.
 *
 * Time Complexity: O(2^(m + n))
 * Space Complexity: O(m + n)
 *
 * Dynamic Programming
 * 1) Take 2d int array of size numRows + 1 and numCols + 1
 * 2) Initialize last row and last col to Integer.MIN_VALUE
 * 3) Initialize numRows - 1 and numCols - 1 with grid value
 * 4) Iterate from numRows - 1 and numCols - 1
 * 5) dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]) + grid[i][j]
 * 6) Return dp[0][0]
 *
 * Time Complexity: O(mn)
 * Space Complexity: O(mn)
 * */
public class MaximumSumPath {

    private static int maxSumPathRecursion(int[][] grid, int row, int col) {

        int numRows = grid.length;
        int numCols = grid[0].length;

        //Guard Case
        if(row >= numRows || col >= numCols) {
            return Integer.MIN_VALUE;
        }

        //Base Case
        if(row == numRows - 1 && col == numCols - 1) {
            return grid[row][col];
        }

        //Recursive Case
        int right = maxSumPathRecursion(grid, row, col + 1);
        int down = maxSumPathRecursion(grid, row + 1, col);

        return Math.max(right, down) + grid[row][col];
    }

    private static int maxSumPathDp(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        int[][] dp = new int[numRows + 1][numCols + 1];

        //Initialize last row with Integer.MIN_VALUE
        for(int i = 0; i <= numCols; i++) {
            dp[numRows][i] = Integer.MIN_VALUE;
        }

        //Initialize last column with Integer.MIN_VALUE
        for(int i = 0; i <= numRows; i++) {
            dp[i][numCols] = Integer.MIN_VALUE;
        }

        dp[numRows - 1][numCols - 1] = grid[numRows - 1][numCols - 1];

        for(int i = numRows - 1; i >= 0; i--) {
            for(int j = numCols - 1; j >= 0; j--) {

                if(i == numRows - 1 && j == numCols - 1) {
                    continue;
                }

                dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] grid = {{3, 2, 4}, {1, 5, 2}, {3, 8, 7}};

        System.out.println("Recursion max sum path " + maxSumPathRecursion(grid, 0, 0));
        System.out.println("DP max sum path " + maxSumPathDp(grid));
    }
}
