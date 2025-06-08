package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Find Closest Gates
 * Given a grid with several walls, gates, and empty rooms,
 * find the distance from each room to its nearest gate.
 *
 * One cannot make a move to a cell containing a wall.
 * A movement can be made in any of the four directions within the grid
 * (UP, DOWN, LEFT, RIGHT).
 *
 * Representations:
 *
 * -1 : A wall.
 * 0 : A gate.
 * INF : An empty room. The value 231-1 = 2147483647 has been used to represent INF.
 * Example One
 * {
 * "grid": [
 * [2147483647, 2147483647, 0, 2147483647],
 * [2147483647, 2147483647, 2147483647, -1],
 * [0, -1, -1, -1],
 * [2147483647, 2147483647, 2147483647, 0]
 * ]
 * }
 * Output:
 *
 * [
 * [2, 1, 0, 1],
 * [1, 2, 1, -1],
 * [0, -1, -1, -1],
 * [1, 2, 1, 0]
 * ]
 * Example Two
 * {
 * "grid": [
 * [0, -1],
 * [-1, 2147483647]
 * ]
 * }
 * Output:
 *
 * [
 * [0, -1],
 * [-1, 2147483647]
 * ]
 * Notes
 * Gates that are not reachable from any room should be filled with INF.
 *
 * Constraints:
 *
 * 1 <= number of rows and columns in grid <= 500
 *
 * Approach
 * 1) Add all gates to queue
 * 2) Start BFS from all gates
 * 3) Update the same grid with distance.
 *
 * TC: O(n + m) = O(rc)
 * SC: O(n + m) = O(rc) - For queue
 */
public class FindClosestGates {

    private static ArrayList<ArrayList<Integer>> findClosestGates(ArrayList<ArrayList<Integer>> grid) {
        Queue<int[]> queue = new LinkedList<>();
        int numRows = grid.size();
        int numCols = 0;

        if(grid.get(0) != null) {
            numCols = grid.get(0).size();
        }

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(grid.get(i).get(j).equals(0)) {
                    int[] a = {i, j};
                    queue.add(a);
                }
            }
        }

        bfs(queue, grid);
        return grid;
    }

    private static void bfs(Queue<int[]> queue, ArrayList<ArrayList<Integer>> grid) {
        while(!queue.isEmpty()) {
            int[] v = queue.remove();

            for(int[] w: getNeighbors(v, grid)) {
                if(grid.get(w[0]).get(w[1]).equals(Integer.MAX_VALUE)) {
                    queue.add(w);
                    grid.get(w[0]).set(w[1], grid.get(v[0]).get(v[1]) + 1);
                }
            }
        }
    }

    private static List<int[]> getNeighbors(int[] v, ArrayList<ArrayList<Integer>> grid) {
        List<int[]> result = new ArrayList<>();
        int numRows = grid.size();
        int numCols = grid.get(0).size();
        int[][] neighbors = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        for(int[] n: neighbors) {
            int row = n[0] + v[0];
            int col = n[1] + v[1];

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
        row.add(Integer.MAX_VALUE);
        row.add(Integer.MAX_VALUE);
        row.add(0);
        row.add(Integer.MAX_VALUE);

        grid.add(row);

        row = new ArrayList<>();
        row.add(Integer.MAX_VALUE);
        row.add(Integer.MAX_VALUE);
        row.add(Integer.MAX_VALUE);
        row.add(-1);

        grid.add(row);

        row = new ArrayList<>();
        row.add(0);
        row.add(-1);
        row.add(-1);
        row.add(-1);

        grid.add(row);

        row = new ArrayList<>();
        row.add(Integer.MAX_VALUE);
        row.add(Integer.MAX_VALUE);
        row.add(Integer.MAX_VALUE);
        row.add(0);

        grid.add(row);

        System.out.println("The closest gates " + findClosestGates(grid));

    }
}
