package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 *  Each cell is empty(0) or blocked(1)
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.

 * Example 1:
 *
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 *
 * Approach
 * 1) If the first cell with row = 0 and col = 0 is 1 then return -1
 * 2) Do BFS with distance array
 * 3) If rows and cols are 1 that means 1 cell then check for that before going into while loop
 * 4) There are 8 possible neighbors including diagonal
 * 5) No need for visited array, cell value = 1 means visited.
 * 6) If we are at the last cell then return the distance
 *
 * TC: O(row * col) Each vertex has at most 8 neighbors
 * SC: O(row * col) for distance array and for queue
 */
public class ShortestPathInBinaryMatrix {

    private static int shortestPathBinaryMatrix(int[][] grid) {

        int numRows = grid.length;
        int numCols = grid[0].length;

        //If the first cell is 1 or dst cell is 1, then return -1
        if(grid[0][0] == 1 || grid[numRows - 1][numCols - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        HashMap<int[], Integer> distance = new HashMap<>();
        HashMap<int[], int[]> parent = new HashMap<>();

        int[] start = {0, 0};
        queue.add(start);
        distance.put(start, 1);

        while(!queue.isEmpty()) {
            int[] v = queue.remove();

            if(v[0] == numRows - 1 && v[1] == numCols - 1) {

                //If it's asked to print path or return path
                List<int[]> path = new ArrayList<>();
                dfsPath(parent, v, path);
                Collections.reverse(path);
                for(int i = 0; i < path.size(); i++) {
                    System.out.println(Arrays.toString(path.get(i)));
                }

                return distance.get(v);
            }
            for(int[] w: getNeighbors(v, grid)) {
                if(grid[w[0]][w[1]] == 0) {
                    //Mark visited
                    grid[w[0]][w[1]] = 1;
                    queue.add(w);
                    distance.put(w, distance.get(v) + 1);
                    parent.put(w, v);
                }
            }
        }
        return -1;
    }

    private static List<int[]> getNeighbors(int[] v, int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        int row = v[0];
        int col = v[1];

        int[][] neighbors = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        List<int[]> list = new ArrayList<>();

        for(int i = 0; i < neighbors.length; i++) {
            int r = row + neighbors[i][0];
            int c = col + neighbors[i][1];

            if(r >= 0 && r < numRows && c >= 0 && c < numCols) {
                int[] a = {r, c};
                list.add(a);
            }
        }

        return list;
    }

    //Do DFS
    private static void dfsPath(HashMap<int[], int[]> parent, int[] start, List<int[]> path) {

        path.add(start);

        if(parent.containsKey(start)) {
            dfsPath(parent, parent.get(start), path);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1}, {1, 0}};

        System.out.println("Shortest path " +shortestPathBinaryMatrix(grid));
    }
}
