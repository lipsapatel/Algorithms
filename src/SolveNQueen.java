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
 *    3) If it doesn't lead to solution then backtract and go to step 3 to try other rows.
 * 4) If all rows has been tried for this column return false
 */
public class SolveNQueen {

    private static boolean solveNQueen(int[][] board, int col) {

        //Base Case: If all queens are placed return true
        if (col >= board[0].length) {
            return true;
        }

        //Consider this column and try place queen in all rows one by one
        for (int rowToTry = 0; rowToTry < board.length; rowToTry++) {

            //Check if it is safe to place queen here
            if (isSafe(board, rowToTry, col)) {

                //Place queen
                board[rowToTry][col] = 1;

                //Recur to place rest of the queens
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

    public static void main(String[] args) {

        int[][] board = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};

        if (solveNQueen(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("Solution does not exist");
        }
    }
}
