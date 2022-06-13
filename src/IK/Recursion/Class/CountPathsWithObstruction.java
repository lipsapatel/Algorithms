package IK.Recursion.Class;

/**
 * Two Approach
 *
 * Recursive Approach
 *
 * f(r, c) =
 *      if r >= n || c >= m
 *          return 0
 *
 *      if(grid[r][c] == 0)
 *          return 0;
 *
 *      if r == n - 1 && c == m - 1
 *          return 0
 *
 *      return f(r, c + 1) + f(r + 1, c)
 *
 * Time Complexity: O(2 ^ (n + m))
 * Space Complexity: O(m + n)
 *
 * Dynamic Programming
 *
 * 1) Create table of size n + 1 rows and m + 1 cols
 * 2) Fill the last row and last col = 0
 * 3) Fill the last cell with 1 if there's no obstruction else fill with 0
 * 4) dp[i][j] = dp[i + 1][j] + dp[i][j + 1]
 *
 * Time Complexity: O(mn)
 * Space Complexity: O(mn)
 */
public class CountPathsWithObstruction {

    private static int countPathsWithObstructionRecursive(int[][] grid, int row, int col) {

        int numRows = grid.length;
        int numCols = grid[0].length;

        //Guard
        if(row >= numRows || col >= numCols) {
            return 0;
        }

        //Obstruction
        if(grid[row][col] == 0) {
            return 0;
        }

        //Base Case
        if(row == numRows - 1 && col == numCols - 1) {
            return 1;
        }

        return countPathsWithObstructionRecursive(grid, row, col + 1) + countPathsWithObstructionRecursive(grid, row + 1, col);
    }

    private static int countPathsWithObstructionDP(int[][] grid) {

        int numRows = grid.length;
        int numCols = grid[0].length;

        int[][] dp = new int[numRows + 1][numCols + 1];

        //Guard
        //Initialize last row and last col with 0
        for(int i = 0; i <= numCols; i++) {
            dp[numRows][i] = 0;
        }

        for(int i = 0; i <= numRows; i++) {
            dp[i][numCols] = 0;
        }

        //Base Case with obstruction
        if(grid[numRows - 1][numCols - 1] != 0) {
            dp[numRows - 1][numCols - 1] = 1;
        } else {
            dp[numRows - 1][numCols - 1] = 0;
        }

        for(int i = numRows - 1; i >= 0; i--) {
            for(int j = numCols - 1; j >= 0; j--) {

                if(i == numRows - 1 && j == numCols - 1) {
                    continue;
                }

                //Obstruction
                if(grid[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1}, {0, 1, 1}, {1, 0, 1}};

        System.out.println("The paths with obstruction recursive: " + countPathsWithObstructionRecursive(grid, 0, 0));
        System.out.println("The paths with obstruction DP: " + countPathsWithObstructionDP(grid));
    }
}
