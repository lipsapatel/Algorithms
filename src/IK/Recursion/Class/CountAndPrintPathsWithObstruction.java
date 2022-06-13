package IK.Recursion.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Recursion
 *
 * 1) Recursive Function (int[][] grid, int row, int col, Stack<List<Integer> stack)
 * 2) Stack keeps track of the path in the form of row and col
 * 3) Guard Case: If row >= numRows or col >= numRows then return 0
 * 4) Obstruction: If grid[row][col] == 0, then return 0. Here "0" is the obstruction
 * 5) Base Case: if row == numRows - 1 and col == numCols - 1, then push the last cell to path and print the path, pop from stack and return 1
 * 6) Recursive Case: Push myself to stack
 * 7) Go right (grid, row, col + 1, stack)
 * 8) Go down (grid, row + 1, col, stack)
 * 9) Pop from stack
 *
 * Time Complexity: (Branching Factor)^height = O(2^(m + n))
 * Space Complexity: Height O(m + n)
 */
public class CountAndPrintPathsWithObstruction {

    private static int countPaths(int[][] grid, int row, int col, Stack<List<Integer>> stack) {

        int numRows = grid.length;
        int numCols = grid[0].length;

        //Guard Case
        if(row >= numRows || col >= numCols) {
            return 0;
        }

        //Obstruction Case
        if(grid[row][col] == 0) {
            return 0;
        }

        //Base Case
        if(row == numRows - 1 && col == numCols - 1) {
            //Print Paths

            //Push the last cell
            List<Integer> path = new ArrayList<>();
            path.add(row);
            path.add(col);
            stack.push(path);

            System.out.println("Path");
            for(List<Integer> p: stack) {
                System.out.println(p.get(0) + "," + p.get(1));
                //System.out.println(p.toString());
            }

            //Pop yourself before returning
            stack.pop();

            return 1;
        }

        //Recursive Case

        //Push my self in stack
        List<Integer> path = new ArrayList<>();
        path.add(row);
        path.add(col);
        stack.push(path);

        int right = countPaths(grid, row, col + 1, stack);

        int down = countPaths(grid, row + 1, col, stack);

        //Pop myself from stack
        stack.pop();

        return right + down;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1}, {0, 1, 1}, {1, 0, 1}};

        System.out.println("Number of paths with obstruction " + countPaths(grid, 0, 0, new Stack<>()));
    }
}
