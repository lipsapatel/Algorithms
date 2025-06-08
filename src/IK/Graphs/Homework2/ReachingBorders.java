package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Reaching Borders
 * A grid has two types of borders:
 *
 * Type 1: It consists of the grid's left and top edges
 * Type 2: It consists of the grid's right and bottom edges
 * It is possible to move from one grid cell to its neighboring cells directly
 * north, south, east, and west if its height is less than or equal
 * to the current cell's height.
 *
 * Given a two-dimensional grid representing the height of each cell,
 * return a list of coordinates from where it is possible to reach both borders.
 *
 * Example One
 * {
 * "grid": [
 * [2, 1, 2, 2, 6],
 * [3, 2, 3, 4, 5],
 * [2, 3, 5, 2, 3],
 * [6, 8, 2, 4, 6],
 * [4, 1, 8, 2, 4]
 * ]
 * }
 * Output:
 *
 * [
 * [0, 4],
 * [1, 4],
 * [3, 0],
 * [3, 1],
 * [4, 0]
 * ]
 * Grid
 *
 * Example Two
 * {
 * "grid": [
 * [2, 3, 5],
 * [4, 3, 1]
 * ]
 * }
 * Output:
 *
 * [
 * [0, 1],
 * [0, 2],
 * [1, 0],
 * [1, 1]
 * ]
 * Notes
 * The grid is represented by a two-dimensional matrix where the
 * number in ith row and jth column represents the height of cell at coordinate (i, j).
 * The top-left cell of the grid has the coordinate (0, 0).
 * The coordinates can be returned in any order.
 * Constraints:
 *
 * 1 <= number of rows and columns in the grid <= 103
 * 1 <= height of any cell <= 106
 *
 * Approach
 * 1) Here we add all top left cells to queue and start BFS
 * 2) Add all bottom right cells to queue and start BFS
 * 3) Create two border matrix one for top-left and and one for bottom-right
 * 4) Mark top-left cells in border1 matrix as 1 or visited
 * 5) Mark bottom-right cells in border2 matrix as 1 or visited
 * 6) After doing BFS, take intersection of two border matrix and add cells to results
 * 7) Reverse the height condition if cell <= neighbor
 *
 * TC: O(n + m) = O(rc)
 * SC: O(n + m) - O(rc) for border matrix and queue
 */
public class ReachingBorders {

    private static ArrayList<ArrayList<Integer>> findCells(ArrayList<ArrayList<Integer>> grid) {
        int numRows = grid.size();
        int numCols = grid.get(0).size();

        int[][] border1 = new int[numRows][numCols];
        int[][] border2 = new int[numRows][numCols];

        //Do BFS from both border and find reachable cells
        bfs(border1, grid, 1);
        bfs(border2, grid, 2);

        //Take intersection of both the matrix and return the cell
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(border1[i][j] == 1 && border2[i][j] == 1) {
                    ArrayList<Integer> row = new ArrayList<>();
                    row.add(i);
                    row.add(j);
                    result.add(row);
                }
            }
        }
        return result;
    }

    private static void bfs(int[][] border, ArrayList<ArrayList<Integer>> grid, int type) {

        int numRows = grid.size();
        int numCols = grid.get(0).size();

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if((type == 1 && (i == 0 || j == 0)) ||
                        (type == 2 && (i == numRows - 1 || j == numCols - 1))) {
                    border[i][j] = 1;
                    int[] a = {i, j};
                    queue.add(a);
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] v = queue.remove();

            for(int[] w: getNeighbors(v, grid)) {
                //If not visited and meet condition
                if(border[w[0]][w[1]] == 0 && grid.get(v[0]).get(v[1]) <= grid.get(w[0]).get(w[1])) {
                    //Mark as visited and add to queue
                    border[w[0]][w[1]] = 1;
                    queue.add(w);
                }
            }
        }
    }

    private static List<int[]> getNeighbors(int[] v, ArrayList<ArrayList<Integer>> grid) {
        int numRows = grid.size();
        int numCols = grid.get(0).size();

        List<int[]> result = new ArrayList<>();
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
        row.add(2);
        row.add(1);
        row.add(2);
        row.add(2);
        row.add(6);

        grid.add(row);

        row = new ArrayList<>();
        row.add(3);
        row.add(2);
        row.add(3);
        row.add(4);
        row.add(5);

        grid.add(row);

        row = new ArrayList<>();
        row.add(2);
        row.add(3);
        row.add(5);
        row.add(2);
        row.add(3);

        grid.add(row);

        row = new ArrayList<>();
        row.add(6);
        row.add(8);
        row.add(2);
        row.add(4);
        row.add(6);

        grid.add(row);

        row = new ArrayList<>();
        row.add(4);
        row.add(1);
        row.add(8);
        row.add(2);
        row.add(4);

        grid.add(row);

        System.out.println("The cells from which both borders can be reached " + findCells(grid));


    }
}
