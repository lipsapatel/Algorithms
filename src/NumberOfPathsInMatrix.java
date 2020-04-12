import java.util.ArrayList;
import java.util.List;

/**
 * Number Of Paths In A Matrix
 *
 * Consider a maze mapped to a matrix with an upper left corner at
 * coordinates (row, column) = (0, 0). You can only move either towards right
 * or down from a cell. You must determine the number of distinct paths
 * through the maze. You will always start at a position (0, 0), the top left,
 * and end up at (n-1, m-1), the bottom right.
 *
 * As an example, consider the following diagram where '1' indicates an open
 * cell and '0' indicates blocked. You can only travel through open cells,
 * so no path can go through the cell at (0, 2). There are two distinct paths
 * to the goal.
 *
 * There are two possible paths from the cell (0, 0) to cell (1, 3) in this matrix.
 * Complete the function numberOfPaths. The function must return the number of paths through the matrix, modulo (10^9 + 7).
 *
 * For example:
 *
 * 3
 * 3
 * 1 1 0
 * 1 1 1
 * 0 1 1
 *
 * Output Format:
 * 4
 *
 * Constraints:
 *     1 <= n*m <= 2*10^6
 *    Each cell, matrix[i][j], contains 1, indicating it is accessible or 0,
 *    indicating it is not accessible, where 0<=i<n and 0<=j<m.
 *
 * Input 1:
 * 3
 * 4
 * 1 1 1 1
 * 1 1 1 1
 * 1 1 1 1
 *
 * Sample Output 1:
 * 10
 *
 * Explanation 1:
 * There are 10 possible paths from cell (0, 0) to cell (2, 3).﻿﻿﻿
 *
 * Sample Input 2:
 * 2
 * 2
 * 1 1
 * 0 1
 *
 * Sample Output 2:
 * 1
 *
 * Explanation 2:
 * There is 1 possible path from the cell (0, 0) to cell (1, 1).
 *
 * resources/NumberOfPathsInMatrixDp.jpg
 * resources/NumberOfPathsInMatrixRecursion.jpg
 *
 * Recursion:
 *
 * 1) Start from (0,0), go right and down. Add them.
 * 2) Base cases
 *      if (row > n - 1 || col > m - 1) return 0
 *      if (matrix[row][col] == 0) return 0
 *     if (row == n - 1 && col == m - 1) return 1
 *
 *  Time Complexity: O(2 ^ (m + n))
 *  Space Complexity: O(m + n)
 *
 *  Dynamic Programming
 *
 * Time Complexity: O(mn) where m = number of rows and n = number of columns
 * Space Complexity: O(mn) where m = number of rows and n = number of columns
 */
public class NumberOfPathsInMatrix {

    private static int numberOfPathsRecursion(List<List<Integer>> a) {

        return numberOfPathsRecursionHelper(a, a.size(), a.get(0).size(), 0, 0);
    }

    private static int numberOfPathsRecursionHelper(List<List<Integer>> a, int m, int n, int row, int col) {

        //Base Cases
        //Guard
        if(row > m - 1 || col > n - 1) {
            return 0;
        }

        //Obstruction
        if(a.get(row).get(col) == 0) {
            return 0;
        }

        //Base Case
        if(row == m - 1 && col == n - 1) {
            return 1;
        }

        int right = numberOfPathsRecursionHelper(a, m, n, row, col + 1) % 1000000007;
        int down = numberOfPathsRecursionHelper(a, m, n, row + 1, col) % 1000000007;
        return (right + down) % 1000000007;
    }

    private static int numberOfPathsDp(List<List<Integer>> a) {
        int rows = a.size();
        int cols = a.get(0).size();

        //Identify the dp table. Recursion two params changing row and col
        int[][] dp = new int[rows + 1][cols + 1];

        //Initialize the dp table.
        //Base case: last row and last col should be 0
        for (int i = 0; i <= cols; i++) {
            dp[rows][i] = 0;
        }

        for (int i = 0; i <= rows; i++) {
            dp[i][cols] = 0;
        }

        if(a.get(rows - 1).get(cols - 1) == 0) {
            dp[rows - 1][cols - 1] = 0; //No Paths
        } else {
            dp[rows - 1][cols - 1] = 1;
        }

        //Traversal directions
        for(int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                //Base case already set
                if(i == rows - 1 && j == cols - 1) {
                    continue;
                }

                //Obstruction
                if(a.get(i).get(j) == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (dp[i + 1][j] % 1000000007 + dp[i][j + 1] % 1000000007) % 1000000007;
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        row.add(1);
        row.add(1);
        row.add(1);

        List<List<Integer>> a = new ArrayList<>();
        a.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(1);
        row.add(1);
        row.add(1);

        a.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(1);
        row.add(1);
        row.add(1);

        a.add(row);

        System.out.println("Number of paths using recursion " + numberOfPathsRecursion(a));
        System.out.println("Number of paths using dp " + numberOfPathsDp(a));

        a = new ArrayList<>();

        row = new ArrayList<>();
        row.add(1);
        row.add(1);

        a.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(0);

        a.add(row);

        System.out.println("Number of paths using recursion " + numberOfPathsRecursion(a));
        System.out.println("Number of paths using dp " + numberOfPathsDp(a));
    }
}
