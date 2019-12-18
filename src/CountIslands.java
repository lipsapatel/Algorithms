import java.util.ArrayList;
import java.util.List;

/**
 * Count the number of islands
 *
 *   1 0 1
 *   1 0 1
 *   0 1 0
 *
 *   Output: 3
 *
 *  Time Complexity: O(R * C)
 *  where R is the number of rows and C is the number of columns
 *
 *  If you are not allowed to modify the matrix, copy and modify it. It will consume extra space.
 *
 *  Approach
 *
 *  1) Start with the cell and exhaust all connected neighboring cells with 1.
 *  2) Run the for loop and call exhaust for cell if it's 1 and increment the count
 *  3) Return the count.
 *
 *  Exploring the neighbors in exhaust function is DFS
 *
 *  Time Complexity is O(RC) because every cell is visited once. Actually twice if all the nodes are connected.
 *  so it is 2RC which is till RC.
 *
 */
public class CountIslands {

    private static int countNoOfIslands(int[][] grid) {

        int count = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

                if (grid[row][col] == 1) {
                    //Exhaust - DFS
                    exhaust(grid, row, col);
                    count++;
                }
            }
        }
        return count;
    }

    private static void exhaust(int[][] grid, int row, int col) {

        //Base Case - If 0, water search ends
        if (grid[row][col] == 0) {
            return;
        }

        grid[row][col] = 0;

        //DFS - exhaust neighbors
        for (int[] array: getNeighbors(grid, row, col)) {
            exhaust(grid, array[0], array[1]);
        }
    }

    private static List<int[]> getNeighbors(int[][] grid, int row, int col) {

        //This returns top, bottom, left and right neighbor if it falls within the boundaries
        List<int[]> result = new ArrayList<>();

        if (row - 1 >= 0) {
            int[] array = {row - 1, col};
            result.add(array);
        }

        if (col - 1 >= 0) {
            int[] array = {row, col - 1};
            result.add(array);
        }

        if (row + 1 < grid.length) {
            int[] array = {row + 1, col};
            result.add(array);
        }

        if (col + 1 < grid[0].length) {
            int[] array = {row, col + 1};
            result.add(array);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {1, 0, 1}, {0, 1, 0}};
        System.out.println("The number of islands are " + countNoOfIslands(grid));
    }
}
