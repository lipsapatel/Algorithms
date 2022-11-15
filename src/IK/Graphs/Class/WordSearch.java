package IK.Graphs.Class;

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
 * 2) In DFS, when backtracking, set visited to false because there could be different path and if keep visited as true then we will not visit that node.
 * 3) If we found the word that reached end of the word then return true
 *
 * Let n = rows * cols
 * L = length of word
 * TC: O(n * 3^L) because there are 3 neighbors of each vertices.
 * SC: O(L + n) because recursive call stack =  L and n = visited array.
 *
 */
public class WordSearch {


}
