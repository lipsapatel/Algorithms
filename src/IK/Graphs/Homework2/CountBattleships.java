package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.List;

/**
 * Count Battleships
 * Given a two-dimensional board, count the number of battleships.
 *
 * The following properties describe a board:
 *
 * Any cell of the board is either a part of a battleship ('X') or empty ('.').
 * The shape of a battleship is either 1 x length or length x 1.
 * All the battleships are axis-aligned.
 * There are no two adjacent battleships, which means at least one horizontal or vertical cell separates between two battleships.
 * Example
 * {
 * "board": [
 * ['X', '.', 'X', 'X', '.'],
 * ['.', 'X', '.', '.', 'X'],
 * ['.', '.', '.', '.', 'X'],
 * ['.', '.', '.', '.', 'X'],
 * ['.', '.', '.', '.', 'X']
 * ]
 * }
 * Output:
 *
 * 4
 * Following are the locations of the four battleships on the board.
 *
 * Battleship1 of shape 1 x 1 located at cell (0, 0).
 * Battleship2 of shape 1 x 2 located at cell (0, 2).
 * Battleship3 of shape 1 x 1 located at cell (1, 1).
 * Battleship4 of shape 4 x 1 located at cell (1, 4).
 * All the locations represent the top or left corner of a battleship.
 *
 * Notes
 * Constraints:
 *
 * 1 <= number of rows and columns in the board <= 1000
 * Each cell of the board is either 'X' or '.'.
 * The length of a battleship will always be less than the size of any
 * dimension of the board.
 *
 * Approach
 * 1) Count number of connected components
 * 2) Use DFS with outer loop to count number of connected components
 * 3) No need for visited array, use the given array for visited by changing X to .
 *
 * TC: O(n + m) = O(rc)
 * SC: O(n + m) = O(rc) - Recursive call stack
 */
public class CountBattleships {

    private static Integer countBattleships(ArrayList<ArrayList<Character>> board) {
        int numRows = board.size();
        int numCols = board.get(0).size();
        int count = 0;

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(board.get(i).get(j).equals('X')) {
                    dfs(i, j, board);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int row, int col, ArrayList<ArrayList<Character>> board) {
        board.get(row).set(col, '.');

        for(int[] v: getNeighbors(row, col, board)) {
            if(board.get(v[0]).get(v[1]).equals('X')) {
                dfs(v[0], v[1], board);
            }
        }
    }

    private static List<int[]> getNeighbors(int row, int col, ArrayList<ArrayList<Character>> board) {
        int numRows = board.size();
        int numCols = board.get(0).size();
        List<int[]> result = new ArrayList<>();
        int[][] neighbors = {{-1, 0}, {0, - 1}, {0, 1}, {1, 0}};

        for(int[] n: neighbors) {
            int r = n[0] + row;
            int c = n[1] + col;

            if(r >= 0 && r < numRows && c >= 0 && c < numCols) {
                int[] a = {r, c};
                result.add(a);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> board = new ArrayList<>();

        ArrayList<Character> row = new ArrayList<>();
        row.add('X');
        row.add('.');
        row.add('X');
        row.add('X');
        row.add('.');

        board.add(row);

        row = new ArrayList<>();
        row.add('.');
        row.add('X');
        row.add('.');
        row.add('.');
        row.add('X');

        board.add(row);

        row = new ArrayList<>();
        row.add('.');
        row.add('.');
        row.add('.');
        row.add('.');
        row.add('X');

        board.add(row);

        row = new ArrayList<>();
        row.add('.');
        row.add('.');
        row.add('.');
        row.add('.');
        row.add('X');

        board.add(row);

        row = new ArrayList<>();
        row.add('.');
        row.add('.');
        row.add('.');
        row.add('.');
        row.add('X');

        board.add(row);

        System.out.println("Number of battleships " + countBattleships(board));

    }
}
