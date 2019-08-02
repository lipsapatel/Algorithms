/**
 * N Queen Problem - Backtracking
 *
 * The N Queen is the problem of placing N queens on N X N chess board so that no two queens attack each other.
 * The queens can attack each other horizontally, vertically or diagonally.
 *
 * The idea is to place queens once by one in different columns starting from the leftmost column.
 *
 * 1) Start in the left most column
 * 2) If all queens are placed return true
 * 3) Try all the rows in the current column.
 * Do following for every tried row
 *
 *    1) If queen can be placed safely, then place the queen
 *    2) Solve recursively for col + 1
 *    3) If it doesn't lead to solution then backtrack and go to step 3 to try other rows.
 * 4) If all rows has been tried for this column return false
 *
 * Time Complexity: N (for all possible rows - degree or branching factor) ^ N X N(for all possible rows)
 */
public class SolveNQueen {

    private static boolean solveNQueen(int[][] board, int col) { //n = 3 means print 3 solutions

        //Base Case: If all queens are placed return true
        if (col >= board[0].length) { //&& n > 0) {
            //System.out.println();
            //printBoard(board);
            //System.out.println();
            return true;
        }

        //Consider this column and try place queen in all rows one by one
        for (int rowToTry = 0; rowToTry < board.length; rowToTry++) {

            //Check if it is safe to place queen here
            if (isSafe(board, rowToTry, col)) {

                //Place queen
                board[rowToTry][col] = 1;

                //Recur to place rest of the queens
                //if (solveNQueen(board, col + 1, n--) && n == 0) { - if print n solutions
                if (solveNQueen(board, col + 1)) {
                    return true;
                }

                //If placing doesn't lead to solution then remove queen
                board[rowToTry][col] = 0; //Backtrack
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col) {

        int i, j;

        //Check for this row on left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        //Check for upper left diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        //Check for lower left diagonal
        for (i = row, j = col; j >= 0 && i < board.length; j--, i++) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void printBoard(int[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Time Complexity: O(N! * NxN)
    private static void solveNQueenUsingPermutation(int[][] board) {

        //create array where index represent row and value represent column
        int[] array = new int[board[0].length];

        for (int i = 0; i < board[0].length; i++) {
            array[i] = i;
        }

        if (foundValidPermutation(array, 0, board)) {
            System.out.println("N Queen output using permutation");
            printBoard(board);
        } else {
            System.out.println("Solution does not exist");
        }
    }

    private static boolean foundValidPermutation(int[] array, int left, int[][] board) {

        //Base Case
        if (left == array.length - 1) {

            if (isValid(array)) {
                setBoard(array, board);
                return true;
            }
            return false;
        }

        for (int i = left; i < array.length; i++) {

            swap(i, left, array);
            if (foundValidPermutation(array, left + 1, board)) {
                return true;
            }
            swap(i, left, array);
        }

        return false;
    }

    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //nxn
    private static boolean isValid(int[] array) {

        //Diagonal is always at 45 degree angle to 135 degree angle
        // y2 - y1/x2 - x1 = 1 or -1

        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (Math.abs((array[j] - array[i]) / (j - i)) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void setBoard(int[] array, int[][] board) {

        for (int i = 0; i < array.length; i++) {
            board[i][array[i]] = 1;
        }
    }

    public static void main(String[] args) {

        int[][] board = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};

        if (solveNQueen(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("Solution does not exist");
        }

        int[][] board1 = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        solveNQueenUsingPermutation(board1);
    }
}
