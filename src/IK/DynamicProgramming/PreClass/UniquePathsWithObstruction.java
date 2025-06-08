package IK.DynamicProgramming.PreClass;

/**
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes
 * cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * Example 1:
 *
 *
 * Input: obstacleGrid = [[0,0,0],
 *                        [0,1,0],
 *                        [0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Example 2:
 *
 * Input: obstacleGrid = [[0,1],
 *                        [0,0]]
 * Output: 1
 *
 * Constraints:
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 *
 * Recursive Approach
 *
 * 1) Base Case: If grid[m][n] == 1, then return 0
 * 2) If m == 0 and n == 0, then return 1
 * 3) If m < 0 || n < 0, return 0
 * 4) f(m, n) = f(m - 1, n) + f(m, n - 1)
 *
 * TC: O(2 ^ (m + n))
 * SC: O(m + n)
 *
 * Dynamic Programming Approach
 *
 * 1) Initialize 2D int matrix of size m x n
 * 2) Iterate in row major order
 * 3) If grid[i][j] == 1, dp[i][j] = 0
 * 4) Else If i == 0 && j == 0, dp[i][j] = 1
 * 5) Else if i == 0, dp[i][j] = dp[i][j - 1]
 * 6) Else if j == 0, dp[i][j] = dp[i - 1][j]
 * 7) Else dp[i][j] = dp[i - 1][j] + d[i][j - 1]
 *
 * TC: O(mn)
 * SC: O(mn)
 */
public class UniquePathsWithObstruction {

    private static int countUniquePathsWithObstructionRecursive(int[][] grid) {

        int m = grid.length - 1;
        int n = grid[0].length - 1;

        return countUniquePathsWithObstructionRecursiveHelper(grid, m, n);
    }

    private static int countUniquePathsWithObstructionRecursiveHelper(int[][] grid, int m, int n) {

        //Base Case
        if(m < 0 || n < 0) {
            return 0;
        }

        if(grid[m][n] == 1) {
            return 0;
        }

        if(m == 0 && n == 0) {
            return 1;
        }

        return countUniquePathsWithObstructionRecursiveHelper(grid, m - 1, n) +
                countUniquePathsWithObstructionRecursiveHelper(grid, m , n - 1);
    }

    private static int countUniquePathsWithObstructionDP(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {

                if(grid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if(i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if(j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        System.out.println(countUniquePathsWithObstructionRecursive(grid));
        System.out.println(countUniquePathsWithObstructionDP(grid));

        int[][] grid1 = {{0, 1}, {0, 0}};

        System.out.println(countUniquePathsWithObstructionRecursive(grid1));
        System.out.println(countUniquePathsWithObstructionDP(grid1));

    }
}
