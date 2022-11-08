package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 *
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color
 * as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on.
 * Replace the color of all of the aforementioned pixels with color.
 *
 * Return the modified image after performing the flood fill.
 *
 * Example 1:
 *
 *
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the
 * starting pixel (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 * Example 2:
 *
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 *
 *
 * Constraints:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], color < 216
 * 0 <= sr < m
 * 0 <= sc < n
 *
 * Approach
 * 1) We can do BFS or DFS and fill in the new color when marking visited.
 * 2) Here we should choose BFS approach because at any given point we will be storing vertices at two levels.
 * 3) If we choose DFS then it might go in depth and result in stack overflow.
 * 4) We need separate visited array because the old color might be same as new color and we might know which vertices are visited.
 * 5) We don't need to construct graph, getNeighbors() method will give the neighbors of vertex
 *
 * TC: O(V + E) where V = rows * cols and E = 4V 4 neighbors for each vertex
 * SC: O(rows * cols) - For visited array and queue.
 *
 * If we use DFS the recursive call stack space will be O(rows * cols)
 */
public class FloodFill {

    private static int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int numRows = image.length;
        int numCols = image[0].length;

        int oldColor = image[sr][sc];

        boolean[][] visited = new boolean[numRows][numCols];

        //bfs(image, visited, sr, sc, color, oldColor);
        dfs(image, visited, sr, sc, color, oldColor);
        return image;
    }

    private static void dfs(int[][] image, boolean[][] visited, int row, int col, int color, int oldColor) {

        //Mark as visited
        visited[row][col] = true;
        image[row][col] = color;

        for(int[] w: getNeighbors(row, col, image)) {
            //If not visited and same color
            if(!visited[w[0]][w[1]] && image[w[0]][w[1]] == oldColor) {
                dfs(image, visited, w[0], w[1], color, oldColor);
            }
        }
    }

    private static void bfs(int[][] image, boolean[][] visited, int row, int col, int color, int oldColor) {
        Queue<int[]> queue = new LinkedList<>();
        int[] a = {row, col};

        queue.add(a);

        //Mark as visited
        visited[row][col] = true;
        image[row][col] = color;

        while(!queue.isEmpty()) {
            int[] v = queue.remove();

            for(int[] w: getNeighbors(v[0], v[1], image)) {
                //If not visited and color is same
                if(!visited[w[0]][w[1]] && image[w[0]][w[1]] == oldColor) {

                    //Mark as visited and add to queue
                    visited[w[0]][w[1]] = true;
                    image[w[0]][w[1]] = color;

                    queue.add(w);
                }
            }
        }
    }

    private static List<int[]> getNeighbors(int row, int col, int[][] image) {
        List<int[]> neighbors = new ArrayList<>();

        if(row - 1 >= 0) {
            int[] a = {row - 1, col};
            neighbors.add(a);
        }

        if(col - 1 >= 0) {
            int[] a = {row, col - 1};
            neighbors.add(a);
        }

        if(row + 1 < image.length) {
            int[] a = {row + 1, col};
            neighbors.add(a);
        }

        if(col + 1 < image[0].length) {
            int[] a = {row, col + 1};
            neighbors.add(a);
        }
        return neighbors;
    }

    public static void main(String[] args) {
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};

        int[][] result = floodFill(image, 1, 1, 2);

        System.out.println("After filling color ");
        for(int[] a: result) {
            System.out.println(Arrays.toString(a));
        }

        int[][] image1 = {{0,0,0},{0,0,0}};

        int[][] result1 = floodFill(image1, 0, 0, 0);

        System.out.println("After filling color ");
        for(int[] a: result1) {
            System.out.println(Arrays.toString(a));
        }

    }
}
