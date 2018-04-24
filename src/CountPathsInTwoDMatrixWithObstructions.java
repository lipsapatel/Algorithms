/**
 * Given two dimensional matrix write an algorithm to count
 * all possible paths from top left to bottom right.
 *
 * You are allowed to move in only two directions:
 * Move right
 * Move down
 *
 * There are few obstructions as well means fewer cells are marked as
 * blocked and you can not travel.
 *
 * This problem is also referred as "Robot Travel Problem"
 *
 * Given a 2D matrix how many ways a robot can travel from
 * top left to bottom right corner and there are few cells in which
 * robot can not travel.
 *
 * images/CountPathsInTwoDMatrixWithObstructions.png
 *
 * In recursive solution "Count all possible paths from top left
 * to bottom right" just check for the condition that the cell is not blocked
 *
 * In Dynamic programming solution, we need to check condition at two places
 * Not solving for blocked cells and while solving for other cells blocked cells
 * are not involved.
 */
public class CountPathsInTwoDMatrixWithObstructions {

    private static int recursiveCountPathsWithObstructions(int[][] array, int row, int column) {

        //Base case: If it's the last row or column then there is only one path
        if (row == array.length - 1 && column == array.length -1) {
            return 1;
        }

        int right = 0;
        int down = 0;

        if (row != array.length - 1 && array[row+1][column] != -1) {
            down = recursiveCountPathsWithObstructions(array, row + 1, column);
        }

        if (column != array.length - 1 && array[row][column+1] != -1) {
            right = recursiveCountPathsWithObstructions(array, row, column + 1);
        }

        return right + down;
    }

    private static int dynamicProgrammingCountPathsWithObstructions(int[][] array) {

        int[][] result = array;

        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {

                if (result[i][j] != -1) {

                    result[i][j] = 0;

                    if (result[i-1][j] != -1) {
                        result[i][j] = result[i][j] + result[i-1][j];
                    }
                    if (result[i][j-1] != -1) {
                        result[i][j] = result[i][j] + result[i][j-1];
                    }
                }
            }
        }
        return result[array.length-1][array.length-1];
    }

    public static void main(String[] args) {
        int arrA [][] = {{1,1,1},{1,-1,1},{1,-1,1}};

        System.out.println("No Of Path (Recursion):- " +recursiveCountPathsWithObstructions(arrA,0,0));
        System.out.println("No Of Path (DP):- " +dynamicProgrammingCountPathsWithObstructions(arrA));

        int arrB [][] = {{1,1,1},{1,-1,1},{1,1,1}};

        System.out.println("No Of Path (Recursion):- " +recursiveCountPathsWithObstructions(arrB,0,0));
        System.out.println("No Of Path (DP):- " +dynamicProgrammingCountPathsWithObstructions(arrB));

        int arrC [][] = {{1,1,1},{1,1,1},{1,-1,1}};

        System.out.println("No Of Path (Recursion):- " +recursiveCountPathsWithObstructions(arrC,0,0));
        System.out.println("No Of Path (DP):- " +dynamicProgrammingCountPathsWithObstructions(arrC));

    }
}
