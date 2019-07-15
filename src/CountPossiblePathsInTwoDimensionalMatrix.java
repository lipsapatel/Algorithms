import java.util.Stack;

/**
 * Given two dimensional matrix write an algorithm to count
 * all possible paths from top left corner to bottom right corner.
 *
 * You are allowed to move only in two directions:
 * Move right
 * Move down
 *
 *images/CountPossiblePathsInTwoDimensionalMatrix
 *
 * Time Complexity: Exponential
 *
 * From every cell we can either go to right or down
 * Base Case: Check if you have reached last row and last column
 * If reached the out of boundary, return 0

  * Time Complexity: O(2 ^ (n + m - 1)) = (Branching Factor(Degree) ^ height
 * Space Complexity: O(m + n) = O(height)
 *
 * Bottom-up approach of Dynamic Programming
 * And store the results of sub problems
 * to reuse in future
 *
 * All paths will be of same length = m - 1 + n - 1 = No of steps which needs to be covered
 * Total number of paths = (m+n-2)!/(n - 1) ! (m - 1)! = (m + n - 2) C (n - 1)
 *
 */
public class CountPossiblePathsInTwoDimensionalMatrix {

    private static int recursiveCountPossiblePaths(int[][] array, int currentRow, int currentColumn) {

        int numRows = array.length;
        int numCols = array[0].length;

        //Guard
        if (currentRow >= numRows || currentColumn >= numCols) {
            return 0;
        }

        //Base case: if currentRow or currentColumn is the last row or column
        if (currentRow == numRows - 1 && currentColumn == numCols - 1) { //|| because we reached at the column end or row end and there's only 1 path to go to end; don't need guard
            return 1;
        }

        //options
        int numRight = recursiveCountPossiblePaths(array, currentRow, currentColumn + 1);
        int numDown = recursiveCountPossiblePaths(array, currentRow + 1, currentColumn);
        return numRight + numDown;
    }

    private static int recursiveCountAndPrintPossiblePaths(int[][] array, int currentRow, int currentColumn, Stack<Integer> stack) {

        int numRows = array.length;
        int numCols = array[0].length;

        //Guard
        if (currentRow >= numRows || currentColumn >= numCols) {
            return 0;
        }

        //Obstruction

        //Add to stack
        stack.push(array[currentRow][currentColumn]);

        //Base case: if currentRow or currentColumn is the last row or column
        if (currentRow == numRows - 1 && currentColumn == numCols - 1) { //|| because we reached at the column end or row end and there's only 1 path to go to end; don't need guard
            printStack(stack);
            stack.pop();
            return 1;
        }

        //options
        int numRight = recursiveCountAndPrintPossiblePaths(array, currentRow, currentColumn + 1, stack);
        int numDown = recursiveCountAndPrintPossiblePaths(array, currentRow + 1, currentColumn, stack);
        stack.pop();

        return numRight + numDown;
    }

    //Return the maximum sum of path
    private static int maxSum = Integer.MIN_VALUE;

    private static int maxSumPath(int[][] array, int currentRow, int currentCol) {

        int numRows = array.length;
        int numCols = array[0].length;

        //Guard
        if (currentRow >= numRows || currentCol >= numCols) {
            return Integer.MIN_VALUE; //If the grid has negative values, this will always be less
        }

        //Base Case
        if (currentRow == numRows - 1 && currentCol == numCols - 1) {
            return array[currentRow][currentCol];
        }

        //Right
        return Math.max(maxSumPath(array, currentRow, currentCol + 1), maxSumPath(array, currentRow + 1, currentCol)) + array[currentRow][currentCol];
    }

    private static void printStack(Stack<Integer> stack) {
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i) + " ");
        }
        System.out.println();
    }

    //Count all possible paths with cells having either 0 or 1. No path should pass from 0
    private static int countPossiblePathsWithObstruction(int[][] array, int currentRow, int currentCol) {

        int numRows = array.length;
        int numCols = array[0].length;

        //Guard
        if (currentRow >= numRows || currentCol >= numCols) {
            return 0;
        }

        //Base case
        if (currentRow == numRows - 1 && currentCol == numCols - 1) {
            return 1;
        }

        //Obstruction
        if (array[currentRow][currentCol] == 0) {
            return 0;
        }

        //Options - Recursive call
        int numRight = countPossiblePathsWithObstruction(array, currentRow, currentCol + 1);
        int numDown = countPossiblePathsWithObstruction(array, currentRow + 1, currentCol);
        return numRight + numDown;
    }

    private static int dynamicProgrammingCountPossiblePaths(int[][] array) {

        int[][] result = new int[array.length][array.length];

        //Base case: If there is only one cell
        result[0][0] = 1;

        //No of possible paths to reach any cell in first row = 1
        for (int i = 0; i < array.length; i++) {
            result[0][i] = 1;
        }

        //No of possible paths to reach any cell in first column = 1
        for (int i = 0; i < array.length; i++) {
            result[i][0] = 1;
        }

        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }

        return result[array.length - 1][array.length - 1];
    }

    public static void main(String[] args) {

        int arrA [][] = {{1,1,1},{1,1,1},{1,1,1}};

        System.out.println("No Of Path (Recursion):- " +recursiveCountPossiblePaths(arrA,0,0));
        System.out.println("No Of Path (DP):- " +dynamicProgrammingCountPossiblePaths(arrA));

        int array[][] = {{1, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        System.out.println("No of possible paths with obstuction are: " + countPossiblePathsWithObstruction(array, 0, 0));

        int[][] array1 = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        System.out.println("Printed all paths and count is: " + recursiveCountAndPrintPossiblePaths(array1, 0, 0, new Stack<Integer>()));

        //Max sum of path
        int[][] array2 = {{1, 2, 100}, {4, 5, 6}, {7, 8, 9}};
        maxSumPath(array2, 0, 0);
        System.out.println("The max sum is: " +maxSumPath(array2, 0, 0));
    }
}
