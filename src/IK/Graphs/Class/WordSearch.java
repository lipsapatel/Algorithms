package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 * resources/WordSearch.jpg
 *
 * Approach
 *
 * 1) Use DFS with outer loop. Reinitialize visited 2D array in outer loop because word to start anywhere.
 * 2) In DFS, when backtracking, set visited to false because there could be different path and if keep visited as true
 * then we will not visit that node.
 * 3) If we found the word that reached end of the word then return true
 *
 * Let n = rows * cols
 * L = length of word
 * TC: O(n * 3^L) because there are 3 neighbors of each vertices.
 * SC: O(L + n) because recursive call stack =  L and n = visited array.
 *
 */
public class WordSearch {

    private static boolean wordExist(char[][] board, String word) {

        int numRows = board.length;
        int numCols = board[0].length;
        boolean[][] visited = new boolean[numRows][numCols];

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(dfs(i, j, visited, word, 0, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int row, int col, boolean[][] visited, String word, int i, char[][] board) {

        if(board[row][col] == word.charAt(i)) {

            //Found word
            if(i == word.length() - 1) {
                return true;
            }

            visited[row][col] = true;

            for(int[] w: getNeighbors(row, col, board)) {

                if(!visited[w[0]][w[1]]) {
                    if(dfs(w[0], w[1], visited, word, i + 1, board)) {
                        return true;
                    }
                }
            }
        }
        visited[row][col] = false; //Revert the visited while backtracking because there could be another path possible
        return false;
    }

    private static List<int[]> getNeighbors(int row, int col, char[][] board) {
        List<int[]> result = new ArrayList<>();
        int numRows = board.length;
        int numCols = board[0].length;

        int r = row - 1;
        int c = col;

        if(r >= 0) {
            int[] a = {r, c};
            result.add(a);
        }

        r = row;
        c = col - 1;

        if(c >= 0) {
            int[] a = {r, c};
            result.add(a);
        }

        r = row;
        c = col + 1;

        if(c < numCols) {
            int[] a = {r, c};
            result.add(a);
        }

        r = row + 1;
        c = col;

        if(r < numRows) {
            int[] a = {r, c};
            result.add(a);
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCESEEEFS";

        System.out.println("Word found " + wordExist(board, word));
    }
}
