/**
 * Given a partially filled 9X9 2D array, the goal is to assign digits from 1 to 9 to the empty cells so
 * that every row, column and subgrid of size 3X3 contains exactly one instance of the digits from 1 to 9.
 *
 * Backtracking Algorithm:
 *
 * Find row,col of an unassigned cell
 * If there's none return true, grid is complete
 *
 * For digits 1 to 9 - options
 * If there's no conflict for digit at row, col and subgrid then place digit in that row and col
 *
 * recursive call, if successful return true
 * else remove the digit from that row and col and try another one
 *
 * If all digits have been tried and nothing worked, return false
 *
 * Time Complexity: n ^ n x n
 * Space Complexity: n x n
 */
public class Sudoku {

    private static boolean noConflict(int[][] board, int num, int row, int col) {

        //Check row
        for (int i = 0; i < board.length; i++) {
            if(board[row][i] == num) {
                return false;
            }
        }

        //Check column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        //Check subgrid
        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int i = boxRowStart; i < boxRowStart + sqrt; i++) {
            for (int j = boxColStart; j < boxColStart + sqrt; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printSudoku(int[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static boolean solveSudoku(int[][] board) {

        int row = -1;
        int col = -1;
        boolean isFilled = true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isFilled = false;
                    break;
                }
            }
        }

        if (isFilled) {
            return true; //All locations successfully assigned
        }

        //if (!findUnassignedLocation(board, row, col)) {
         //   return true; //All locations successfully assigned
        //}

        for (int num = 1; num <= 9; num++) { //options are 1-9
            if (noConflict(board, num, row, col)) { //If looks good
                board[row][col] = num; //Place num
                if (solveSudoku(board)) {
                    return true;
                } //recur if succeed stop
                board[row][col] = 0; //Remove num and try again
            }
        }

        return false; //This triggers backtracking from early decisions
    }

    private static boolean solveSudokuHelper(char[][] board) {

        int row = -1;
        int col = -1;
        boolean isFilled = true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == '.') {
                    isFilled = false;
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        //Base Case
        if (isFilled) {
            return true;
        }

        //Recursive Case
        for (int i = 1; i <= 9; i++) { //options

            if (isSafe(board, row, col, Character.forDigit(i, 10))) {

                //Set
                board[row][col] = Character.forDigit(i, 10);

                //Recur
                if (solveSudokuHelper(board)) {
                    return true;
                }

                //try with other options
                board[row][col] = '.';
            }
        }
        return false; //Backtrack
    }

    private static boolean isSafe(char[][] board, int row, int col, char num) {

        //Check row
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        //Check col
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        //Check matrix n x n
        int sqrt = (int)Math.sqrt(board.length);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;

        for (int i = rowStart; i < rowStart + sqrt; i++) {
            for (int j = colStart; j < colStart + sqrt; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printSudoku(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = { {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0} };

        if (solveSudoku(board)) {
            printSudoku(board);
        } else {
            printSudoku(board);
            System.out.println("No solution");
        }

        System.out.println("Character");
        char[][] board1 = { {'5','3','.','.','7','.','.','.','.'},
                            {'6','.','.','1','9','5','.','.','.'},
                            {'.','9','8','.','.','.','.','6','.'},
                            {'8','.','.','.','6','.','.','.','3'},
                            {'4','.','.','8','.','3','.','.','1'},
                            {'7','.','.','.','2','.','.','.','6'},
                            {'.','6','.','.','.','.','2','8','.'},
                            {'.','.','.','4','1','9','.','.','5'},
                            {'.','.','.','.','8','.','.','7','9'}};

        if (solveSudokuHelper(board1)) {
            printSudoku(board1);
        } else {
            printSudoku(board1);
            System.out.println("No Solution");
        }
    }
}
