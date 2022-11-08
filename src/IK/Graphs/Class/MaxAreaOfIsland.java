package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid =
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 *
 * Approach
 * 1) This is similar to finding connected component. But here you count the number of vertices visited.
 * 2) We don't need to construct graph. getNeighbors() method will return all the neighbor for given vertex.
 * 3) We also don't need to maintain visited array separately because we can assume 0 as not visited and marked the node visited in same array.
 * 4) Do DFS with outer for loop and maintain the max number of vertices.
 *
 * TC: O(V + E) - where V = rows * cols E = 4 for each vertex so it's 4V
 * SC: O(1)
 * O(V) recursive call stack space for DFS
 *
 */
public class MaxAreaOfIsland {

    private static int maxAreaOfIsland(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        int max = 0;

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {

                //If not visited
                if(grid[i][j] == 1) {
                    max = Math.max(dfs(i, j, grid), max);
                }
            }
        }
        return max;
    }

    private static int dfs(int row, int col, int[][] grid) {

        //Mark as visited
        grid[row][col] = 0;

        int noOfVertices = 0;
        for(int[] w: getNeighbors(row, col, grid)) {

            //If not visited
            if(grid[w[0]][w[1]] == 1) {
                noOfVertices = noOfVertices + dfs(w[0], w[1], grid);
            }
        }
        return noOfVertices + 1;
    }

    private static List<int[]> getNeighbors(int row, int col, int[][] grid) {
        List<int[]> neighbors = new ArrayList<>();

        if(row - 1 >= 0) {
            int[] a = {row - 1, col};
            neighbors.add(a);
        }

        if(col - 1 >= 0) {
            int[] a = {row, col - 1};
            neighbors.add(a);
        }

        if(row + 1 < grid.length) {
            int[] a = {row + 1, col};
            neighbors.add(a);
        }

        if(col + 1 < grid[0].length) {
            int[] a = {row, col + 1};
            neighbors.add(a);
        }
        return neighbors;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {1, 0, 1}, {0, 1, 0}};
        System.out.println("The max area of islands is " + maxAreaOfIsland(grid));

        int[][] grid1 = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        System.out.println("The max area is " + maxAreaOfIsland(grid1));
    }
}
