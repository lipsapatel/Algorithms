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
 * Base Case: Check if you have reached last row or last column
 *
 * Then there is only one way to reach last cell
 * Travel through that row or column
 *
 * Bottom-up approach of Dynamic Programming
 * And store the results of sub problems
 * to reuse in future
 *
 */
public class CountPossiblePathsInTwoDimensionalMatrix {

    private static int recursiveCountPossiblePaths(int[][] array, int currentRow, int currentColumn) {

        //Base case: if currentRow or currentColumn is the last row or column
        if (currentRow == array.length - 1 || currentColumn == array.length - 1) {
            return 1;
        }

        return recursiveCountPossiblePaths(array, currentRow + 1, currentColumn) +
                recursiveCountPossiblePaths(array, currentRow, currentColumn + 1);
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

    }
}
