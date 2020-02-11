/**
 * Two ways of solving
 *
 * 1) Recursive Solution
 *
 * Given grid where there are n rows and m cols
 *
 * f(grid, r, c) =
 *
 * r == n || c == m
 * return 0 or Integer.MIN_VALUE (if there are negative numbers)
 *
 * r == n - 1 && c == m - 1
 * return grid[r][c]
 *
 * grid[r][c] = grid[r][c] + Math.max(f(r+1, c), f(r, c + 1))
 *
 * Time Complexity = O(2 ^ (m + n))
 * Space Complexity = (m + n)
 *
 * Recursive solution but with memorization
 *
 * Time Complexity: Recursive Stack = O(m + n) + O(mn)
 * Space Complexity: O(mn)
 *
 * DP solution
 *
 * 1) Create DP table to size rows + 1 and cols + 1
 * 2) Initialize last row and last col with 0(if all values are positive)
 * 3) Start from rows - 1 and cols - 1 and go left and upward till you reach cell 0,0
 * 4) Return DP[0][0]
 *
 * Time Complexity: O(mn)
 * Space Complexixty: O(mn)
 */
public class MaxPathSum {

    private static int maxPathSumRecursive(int[][] grid, int row, int col) {


        int numRows = grid.length;
        int numCols = grid[0].length;

        //int[][] solution = new int[numRows + 1][numCols + 1]; memorization

        //Guard Case
        if (row >= numRows || col >= numCols) {
            //return 0;
            //If there are negative values
            return Integer.MIN_VALUE;
        }

        //Base Case
        if (row == numRows - 1 && col == numCols - 1) {
            //solution[row][col] = grid[row][col];
            return grid[row][col];
        }

        //Recursive Case
        int right = /*(solution[row][col + 1] != 0 ) ? solution[row][col + 1] : */maxPathSumRecursive(grid, row, col + 1);
        int down = /*(solution[row + 1][col] != 0) ? solution[row + 1][col] : */maxPathSumRecursive(grid, row + 1, col);

        //solution[row][col] = grid[row][col] + Math.max(right, down);
        return grid[row][col] + Math.max(right, down);
    }

    private static int maxPathSumDP(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        //Create DP table of size rows + 1 and cols + 1
        int[][] DP = new int[rows + 1][cols + 1];

        //Initialize last row and last col = 0 - This is handling of guard condition
        for (int i = 0; i <= cols; i++) {
            //DP[rows][i] = 0;
            DP[rows][i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i <= rows; i++) {
            //DP[i][cols] = 0;
            DP[i][cols] = Integer.MIN_VALUE;
        }

        //Base Case
        //This is required only when there are negative values
        DP[rows - 1][cols - 1] = grid[rows - 1][cols - 1];

        for(int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {

                //For negative values
                //Do nothing for this iteration since we have already set the value for base case.
                if(i == rows - 1 && j == cols - 1) {
                    continue;
                }
                DP[i][j] = grid[i][j] + Math.max(DP[i + 1][j], DP[i][j + 1]);
            }
        }
        printMaxPath(DP, grid);
        return DP[0][0];
    }

    public static void printMaxPath(int[][] DP, int[][] grid) {

        int row = 0;
        int col = 0;

        System.out.println(grid[row][col]);

        while(row < grid.length - 1 || col < grid[0].length - 1) { //next max cell gets printed.
            if(DP[row + 1][col] > DP[row][col + 1]) {
                System.out.println(grid[row + 1][col]);
                row = row + 1;
            } else {
                System.out.println(grid[row][col + 1]);
                col = col + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{5, 10, 20, 30}, {35, 4, 25, 15}, {92, 80, 61, 72}, {12, 16, 70, 2}, {6, 18, -41, -3}};

        System.out.println("Max sum path using recursion: " + maxPathSumRecursive(grid, 0, 0));
        System.out.println("Max sum path using DP: " + maxPathSumDP(grid));
    }
}
