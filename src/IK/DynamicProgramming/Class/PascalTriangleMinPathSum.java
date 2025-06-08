package IK.DynamicProgramming.Class;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row,
 * you may move to either index i or index i + 1 on the next row.
 *
 *
 *
 * Example 1:
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 *
 * Input: triangle = [[-10]]
 * Output: -10
 *
 *
 * Constraints:
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 *
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 *
 * Recursive Approach
 *
 * 1) f(row, col) = grid.get(row).get(col) + Math.min(f(row - 1, col), f(row - 1, col - 1))
 * 2) Base Case: If row == 0 && col == 0, return grid.get(row).get(col)
 * 3) Base Case: If row < 0 || col < 0 || col > row, return Integer.MAX_VALUE
 * 4) Iterate last col and call function recursively and take min
 *
 * TC: O(2 ^(n + m))
 * SC: O(n + m)
 *
 * DP Approach
 *
 * 1) Take list of the same size as given grid
 * 2) Initialize the first row and first col
 * 3) Iterate row by row
 * 4) Iterate col from 0 to row
 * 5) If it's first col, dp.get(i).set(j, grid.get(i).get(j) + dp.get(i - 1).get(j))
 * 6) Else If it's last col, dp.get(i).set(j, grid.get(i).get(j) + dp.get(i - 1).get(j - 1))
 * 7) Else dp.get(i).set(j, Math.min(dp.get(i - 1).get(j), dp.get(i - 1).get(j - 1)) +
 *                               grid.get(i).get(j)
 * 8) Iterate the last row and take min
 *
 * TC: O(mn) where m = no of cols and n = no of rows
 * We have r rows.
 * First row has 1 cell
 * Second row has 2 cells
 *  so we have 1 + 2 + 3 + 4....n = n * (n + 1)/2
 *  So Time complexity = O(n^2)
 *
 * SC: O(mn)
 * O(n^2) with the same explanation above.
 *
 */
public class PascalTriangleMinPathSum {

    private static int minPathSumRecursive(List<List<Integer>> triangle) {

        int n = triangle.size() - 1;
        int m = triangle.get(n).size() - 1; //Number of cols in last row

        int min = Integer.MAX_VALUE;

        for(int col = 0; col <= m; col++) {
            min = Math.min(minPathSumRecursiveHelper(triangle, n, col), min);
        }

        return min;
    }

    private static int minPathSumRecursiveHelper(List<List<Integer>> grid, int row, int col) {

        //Base Case
        if(row == 0 && col == 0) {
            return grid.get(row).get(col);
        }

        if(row < 0 || col < 0 || col > row) {
            return Integer.MAX_VALUE;
        }

        return grid.get(row).get(col) + Math.min(minPathSumRecursiveHelper(grid, row - 1, col),
                                                minPathSumRecursiveHelper(grid, row - 1, col - 1));
    }

    private static int minPathSumDP(List<List<Integer>> grid) {

        List<List<Integer>> dp = new ArrayList<>(grid.size());

        //Initialize first row and col
        dp.add(new ArrayList<>());
        dp.set(0, grid.get(0));

        int rows = grid.size() - 1;

        for(int i = 1; i <= rows; i++) {
            dp.add(grid.get(i));

            for(int j = 0; j <= i; j++) {

                //Left most column
                if(j == 0) {
                    dp.get(i).set(j, grid.get(i).get(j) + dp.get(i - 1).get(j));
                } else if (j == i) { //right most column
                    dp.get(i).set(j, grid.get(i).get(j) + dp.get(i - 1).get(j - 1));
                } else {
                    dp.get(i).set(j, grid.get(i).get(j) + Math.min(dp.get(i - 1).get(j),
                                                        dp.get(i - 1).get(j - 1)));
                }
            }
        }

        //Iterate last row and find min
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= rows; i++) {
            min = Math.min(min, dp.get(rows).get(i));
        }
        return min;
    }

    public static void main(String[] args) {

        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row = new ArrayList<>();

        row.add(2);
        triangle.add(row);

        row = new ArrayList<>();
        row.add(3);
        row.add(4);

        triangle.add(row);

        row = new ArrayList<>();
        row.add(6);
        row.add(5);
        row.add(7);

        triangle.add(row);

        row = new ArrayList<>();
        row.add(4);
        row.add(1);
        row.add(8);
        row.add(3);

        triangle.add(row);

        System.out.println("Min Path Sum: " + minPathSumRecursive(triangle));
        System.out.println("Min Path Sum DP: " + minPathSumDP(triangle));
    }
}
