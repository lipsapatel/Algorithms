package IK.Recursion.PreClass;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * N Queen Problem - Backtracking
 *
 * The N Queen is the problem of placing N queens on N X N chess board so
 * that no two queens attack each other.
 * The queens can attack each other horizontally, vertically or diagonally.
 *
 * The idea is to place queens one by one in different columns starting
 * from the leftmost column.
 *
 * 1) Start in the left most column
 * 2) If all queens are placed return true
 * 3) Try all the rows in the current column.
 * Do following for every tried row
 *
 *    1) If queen can be placed safely, then place the queen
 *    2) Solve recursively for col + 1
 *    3) If it doesn't lead to solution then backtrack and go to
 *    step 3 to try other rows.
 * 4) If all rows has been tried for this column return false
 *
 * Time Complexity: N (for all possible rows - degree or branching factor) ^ N X N(for all
 * possible rows)
 * Space Complexity: N x N
 *
 * *****************************************************************************************************************
 * n Queen Problem

 Problem Statement:
 The n-queen problem is the problem of placing n chess queens on an n * n
 chessboard, so that no two queens attack each other.
 Your task is to find ALL possible arrangements for the n-queen problem.
 You have to solve this problem using recursion. (There may be other ways of
 solving this problem, but for the purpose of this exercise,
 please use recursion only).
 A queen can move horizontally, vertically, or diagonally.
 The purpose of this problem is to learn recursion and not DP.
 So, you must write at least one recursive solution.
 After that, you can write a DP solution if you want.

 Input/Output Format For The Function:
 Input Format:
 There is only one argument denoting integer n.

 Output Format:
 Return 2D string array res, of size m * n, where length of each string is n
 and m is the total no. of distinct arrangements possible.
 Each character in res[i][j] describes a square on chessboard.
 So, any character in string should be one of {'q', '-'}.
 Character 'q' means queen is present on it and
 character '-' means it is empty.
 To be precise, character at kth position of string res[i][j] describes
 content of square in kth column of jth row of chessboard in ith arrangement.
 (To be more clear about the output, look at the sample test case.)

 Input/Output Format For The Custom Input:
 Input Format:
 The first and only line of input should contain an integer n.
 If n = 4, then input should be:
 4

 Output Format:
 There will be (m*(n+1)) lines of output,
 describing m different possible arrangements.
 Order of printing of arrangements will be as per order in res array,
 i.e. res[0], res[1], …, res[m-1].
 For ith arrangement (described by res[i]):
 There will be total n + 1 lines.
 In first n lines, jth line contains a string res[i][j],
 denoting string at index j of res[i]. Last line will be an empty line.

 For input n = 4, output will be:
 -q--
 ---q
 q---
 --q-

 --q-
 q---
 ---q
 -q--

 Constraints:
 1 <= n <= 12

 Sample Test Case:
 Sample Input:
 4

 Sample Output:
 Suppose name of the returned array is ret.
 ret [0] =
 [
 "--q-",
 "q---",
 "---q",
 "-q--"
 ]

 ret [1] =
 [
 "-q--",
 "---q",
 "q---",
 "--q-"
 ]

 Explanation:
 There are 2 possible solutions for 4 queen problem, hence size of ret is 2 * 4, and length of each string is 4.
 ret [i] will denote ith arrangement.
 ret [i][j] will denote jth row of ith arrangement.
 ret [i][j][k] will denote kth character (if it is a queen or empty cell) of jth row in ith arrangement.
 You need not to worry about the order of arrangements in your returned. So output
 ret [0] =
 ["-q--","---q","q---","--q-"]

 ret [1] =
 ["--q-","q---","---q","-q--"]

 will also be considered as a valid answer.
 Notes:
 Suggested time in interview: 30 minutes; this is only a variation of permutations problems.
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

    /********************************************************************************/
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

            //The content of array shows that the queen is placed at that column for the row indicated by index.
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
        // y2 - y1/x2 - x1 = 1 or -1 = the different between col index/row index is always 1 or -1
        // 0 1 0 0
        // 0 0 0 0
        // 0 0 0 1 //Here the difference between col/difference between row = 1 or -1

        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (Math.abs(array[j] - array[i]) == Math.abs(j - i)) {
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

    /**************************************************************************************/
    private static String[][] find_all_arrangements(int n) {

        //Create board
        char[][] board = new char[n][n];
        createBoard(board);

        //Queue for storing each row
        Queue<String> queue = new LinkedList<>();

        solveNQueen(board, 0, queue);

        //Create m X n result to return
        int totalSolutions = queue.size()/n;
        String[][] result = new String[totalSolutions][n];

        for (int i = 0; i < totalSolutions; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = queue.poll();
            }
        }
        return result;
    }

    private static void createBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void solveNQueen(char[][] board, int col, Queue<String> queue) {

        //Base Case
        if (col >= board[0].length) {
            buildStringFromBoard(board, queue);
        } else { //Recursive Case

            //Consider this col and try to place queen in all rows
            for (int rowToTry = 0; rowToTry < board.length; rowToTry++) {

                //Check if it's safe to place queen
                if (isSafeForCharBoard(board, rowToTry, col)) {

                    //Place queen
                    board[rowToTry][col]  = 'q';

                    //Recur
                    solveNQueen(board, col + 1, queue);

                    //Remove and try other options
                    board[rowToTry][col] = '-';
                }
            }
        }
    }

    private static void buildStringFromBoard(char[][] board, Queue<String> queue) {
        StringBuilder row;

        for (int i = 0; i < board.length; i++) {
            row = new StringBuilder();
            for (int j = 0; j < board[0].length; j++) {
                row.append(board[i][j]);
            }
            queue.add(row.toString());
        }
    }

    private static boolean isSafeForCharBoard(char[][] board, int row, int col) {

        //Row
        for (int i = 0; i < col; i++) {

            if (board[row][i] == 'q') {
                return false;
            }
        }

        //Top left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'q') {
                return false;
            }
        }

        //Bottom left diagonal
        for (int i = row, j = col; j >= 0 && i < board.length; j--, i++) {
            if (board[i][j] == 'q') {
                return false;
            }
        }
        return true;
    }
/***********************************************************************************************/

    private static String[][] find_all_arrangements_using_permutation(int n) {

        int[] array = new int[n];

        //Initialize the array
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        //To store string row
        Queue<String> queue = new LinkedList<>();

        findAllValidPermutations(array, 0, n, queue);

        int totalSolutions = queue.size()/n;
        String[][] result = new String[totalSolutions][n];

        for (int i = 0; i < totalSolutions; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = queue.poll();
            }
        }
        return result;
    }

    //TC = O(n!)
    private static void findAllValidPermutations(int[] array, int left, int n, Queue<String> queue) {

        //Base Case
        if (left == array.length - 1) {

            //n x n
            if (isValid(array)) {
                setBoardAndCreateString(array, n, queue);
            }
        } else { //Recursive Case

            for (int i = left; i < array.length; i++) {

                swap(i, left, array);
                findAllValidPermutations(array, left + 1, n, queue);
                swap(i, left, array);
            }
        }
    }

    private static void setBoardAndCreateString(int[] array, int n, Queue<String> queue) {

        char[][] board = new char[n][n];

        //Default initialize board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }

        for (int i = 0; i < array.length; i++) {
            board[i][array[i]] = 'q';
        }

        StringBuilder row;
        for (int i = 0; i < board.length; i++) {

            row = new StringBuilder();

            for (int j = 0; j < board[0].length; j++) {
                row.append(board[i][j]);
            }
            queue.add(row.toString());
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

        int n = 4;
        String[][] result = find_all_arrangements(n);

        System.out.println(Arrays.deepToString(result));

        String[][] result1 = find_all_arrangements_using_permutation(5);
        System.out.println(Arrays.deepToString(result1));
    }
}
