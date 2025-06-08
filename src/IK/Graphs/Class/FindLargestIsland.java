package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Find Largest Island
 * Given a two-dimensional grid of 0s and 1s, find the size of the largest island.
 * If there is no island return 0.
 *
 * An island is a group of 1s connected vertically or horizontally.
 *
 * Example One
 * {
 * "grid": [
 * [1, 1, 0],
 * [1, 1, 0],
 * [0, 0, 1]
 * ]
 * }
 * Output:
 *
 * 4
 * There are two islands:
 *
 * [(0, 0), (0, 1), (1, 0), (1, 1)]
 * [(2, 2)]
 * Size of the largest (first) island is 4.
 *
 * Example Two
 * {
 * "grid": [
 * [0, 0, 0],
 * [0, 0, 0],
 * [0, 0, 0]
 * ]
 * }
 * Output:
 *
 * 0
 * Notes
 * Constraints:
 *
 * 1 <= number of rows <= 200
 * 1 <= number of columns <= 200
 *
 * Approach
 * 1) Find the connected component with maximum vertices.
 * 2) Do DFS which returns the count of vertices in that connected component.
 * 3) Maintain the max count returned
 * 4) Do DFS with outer loop and do DFS if not visited.
 *
 * TC: O(n + m) = O(rc)
 * SC: O(n + m) = O(rc) Recursive call stack space
 *
 */
public class FindLargestIsland {

    private static int largestIsland(ArrayList<ArrayList<Integer>> grid) {
        int largestConnectedComponent = 0;

        int numRows = grid.size();
        int numCols = 0;

        if(grid.get(0) != null) {
            numCols = grid.get(0).size();
        }

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(grid.get(i).get(j) == 1) {
                    largestConnectedComponent = Math.max(largestConnectedComponent, dfs(i, j, grid));
                }
            }
        }
        return largestConnectedComponent;
    }

    private static int dfs(int row, int col, ArrayList<ArrayList<Integer>> grid) {
        grid.get(row).set(col, 0);
        int count = 1;

        for(int[] v: getNeighbors(row, col, grid)) {
            if(grid.get(v[0]).get(v[1]) == 1) {
                count += dfs(v[0], v[1], grid);
            }
        }
        return count;
    }

    private static List<int[]> getNeighbors(int i, int j, ArrayList<ArrayList<Integer>> grid) {
        int[][] neighbors = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        List<int[]> result = new ArrayList<>();
        int numRows = grid.size();
        int numCols = grid.get(0).size();

        for(int[] n: neighbors) {
            int row = i + n[0];
            int col = j + n[1];

            if(row >= 0 && row < numRows && col >= 0 && col < numCols) {
                int[] a = {row, col};
                result.add(a);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();

        row.add(1);
        row.add(1);
        row.add(0);
        grid.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(1);
        row.add(0);
        grid.add(row);

        row = new ArrayList<>();
        row.add(0);
        row.add(0);
        row.add(1);
        grid.add(row);

        System.out.println("Largest connected component " +largestIsland(grid));
    }
}
