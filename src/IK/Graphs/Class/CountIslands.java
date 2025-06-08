package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.List;

/**
 *Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 *
 * Approach
 * 1) This is finding number of connected components.
 * 2) No need to construct graph. getNeighbors method will return all neighbors.
 * 3) No need to maintain visited array separately. The same array can be used for visited. 0 means visited and 1 means not visited.
 * 4) Do DFS with outer loop.
 *
 * TC: O(V + E) where V = rows * cols E = 4 for each vertex so its 4V
 * SC: O(1)
 * O(V) Call stack space or DFS
 *
 */
public class CountIslands {

    private static int countNoOfIslands(int[][] grid) {

        int numRows = grid.length;
        int numCols = grid[0].length;
        int count = 0;

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {

                //If not visited
                if(grid[i][j] == 1) {
                    dfs(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int row, int col, int[][] grid) {

        //Mark as visited
        grid[row][col] = 0;

        for(int[] w: getNeighbors(row, col, grid)) {

            //If not visited
            if(grid[w[0]][w[1]] == 1) {
                dfs(w[0], w[1], grid);
            }
        }
    }

    private static List<int[]> getNeighbors(int row, int col, int[][] grid) {
       List<int[]> result = new ArrayList<>();
       int numRows = grid.length;
       int numCols = grid[0].length;

       int[][] neighbors = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

       for(int[] v: neighbors) {
           int r = row + v[0];
           int c = col + v[1];

           if(r >= 0 && r < numRows && c >= 0 && c < numCols) {
               int[] a = {r, c};
               result.add(a);
           }
       }
       return result;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {1, 0, 1}, {0, 1, 0}};
        System.out.println("The number of islands are " + countNoOfIslands(grid));
    }
}
