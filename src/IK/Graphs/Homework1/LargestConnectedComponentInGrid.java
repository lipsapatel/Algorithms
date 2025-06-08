package IK.Graphs.Homework1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Largest Connected Component In Binary Square Grid
 * Each of the cells in a given square grid is assigned a value of either "0" or "1".
 *
 * A grid cell is connected to another cell only if they share a common side.
 * A connected component is a set of directly or indirectly connected cells, each with the value "1".
 *
 * Find the largest possible size of a connected component achievable by changing the
 * value of at most one cell from "0" to "1" in the grid.
 *
 * Example One
 * {
 * "grid": [
 * [1, 0],
 * [0, 0]
 * ]
 * }
 * Output:
 *
 * 2
 * Changing any of the two "0"s adjacent to the "1" forms a component of size 2.
 *
 * Example Two
 * {
 * "grid": [
 * [1, 1],
 * [1, 1]
 * ],
 * }
 * Output:
 *
 * 4
 * There are no cells with the value "0", so no operations can be performed. But the whole grid is already connected with size 4.
 *
 * Notes
 * Constraints:
 *
 * 1 <= dimensions of the input grid <= 500
 * 0 <= value of a cell in the grid <= 1
 *
 * Approach
 * 1) Do DFS with outer loop starting at cell with value 1
 * 2) DFS returns the count of vertices in that connected component
 * 3) Marked all those vertices with connected component id
 * 4) Since the grid has 0 and 1, the connected component id starts with 2
 * 5) Create hashmap of connected component id and count
 * 6) Iterate the cell of the grid and where there is 0, get all the neighbors and if the neighbor is not
 * 0 then get the count of vertices in that connected component and add them all
 * 7) Also have visited hashset for connected component id so if we visit the same component id
 * in neighbor we don't need to add again
 * 8)Maintain the max count of vertices in any connected component
 * 9) return the max count
 *
 * TC: O(n + m) = O(rc)
 * SC: O(n) - In worst case visited will all single vertex = O(rc)
 *
 */
public class LargestConnectedComponentInGrid {

    private static int largestConnectedComponent(ArrayList<ArrayList<Integer>> grid) {
        int numRows = grid.size();
        int numCols = 0;

        if(grid.get(0) != null) {
            numCols = grid.get(0).size();
        }

        HashMap<Integer, Integer> connectedCompCount = new HashMap<>();
        int compId = 2;
        int maxCount = 0;

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(grid.get(i).get(j) == 1) {
                    maxCount = dfs(i, j, compId, grid);
                    connectedCompCount.put(compId, maxCount);
                    compId++;
                }
            }
        }

        //Change 0 to 1 and then find the number of vertices in connected component
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {

                if(grid.get(i).get(j) == 0) {
                    HashSet<Integer> visited = new HashSet<>();
                    int count = 1;

                    //Get all neighbors
                    for(int[] n: getNeighbors(i, j, grid)) {

                        int id = grid.get(n[0]).get(n[1]);

                        if(id != 0 && !visited.contains(id)) {
                            visited.add(id);
                            count += connectedCompCount.get(id);
                        }
                    }
                    maxCount = Math.max(maxCount, count);
                }
            }
        }
        return maxCount;
    }

    private static int dfs(int row, int col, int compId, ArrayList<ArrayList<Integer>> grid) {
        grid.get(row).set(col, compId);
        int count = 1;

        for(int[] v: getNeighbors(row, col, grid)) {
            if(grid.get(v[0]).get(v[1]) == 1) {
                count += dfs(v[0], v[1], compId, grid);
            }
        }
        return count;
    }

    private static List<int[]> getNeighbors(int row, int col, ArrayList<ArrayList<Integer>> grid) {
        List<int[]> result = new ArrayList<>();
        int numRows = grid.size();
        int numCols = grid.get(0).size();

        int[][] neighbors = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

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
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();

        ArrayList<Integer> row = new ArrayList<>();
        row.add(1);
        row.add(0);
        grid.add(row);

        row = new ArrayList<>();
        row.add(0);
        row.add(0);
        grid.add(row);

        System.out.println("Largest connected component: " + largestConnectedComponent(grid));
    }
}
