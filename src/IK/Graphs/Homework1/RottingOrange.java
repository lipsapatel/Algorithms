package IK.Graphs.Homework1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Rotting Oranges
 * Given a grid of numbers where each cell can have one of three values:
 *
 * 0: Represents an empty cell.
 * 1: Represents a fresh orange.
 * 2: Represents a rotten orange.
 * Every minute, any fresh orange that shares a side with a rotten orange also becomes rotten.
 * Your task is to return the minimum time after which all the oranges will rot.
 *
 * Example One
 * Example1 input grid
 *
 *  2  1  1
 *  1  0  0
 *  1  1  0
 *
 * Output:
 *
 * 3
 *
 * Example1 input grid
 * After 1 min
 *
 * 2  2  1
 * 2  0  0
 * 1  1  0
 *
 * After 2 min
 *
 * 2  2  2
 * 2  0  0
 * 2  1  0
 *
 * After 3 min
 *
 * 2  2  2
 * 2  0  0
 * 2  2  0
 *
 * Example Two
 *
 * 2  1  1
 * 1  0  0
 * 1  0  1
 *
 * Output:
 *
 * -1
 *
 * The orange at the bottom-right cell will never rot.
 *
 * Notes
 * If there exists any orange that will never rot, return -1.
 *
 * Constraints:
 *
 * 1 <= number of rows <= 1000
 * 1 <= number of columns <= 1000
 * 0 <= value of any cell <= 2
 *
 * Approach
 * 1) Start BFS from rotten orange in outer loop. Add all rotten oranges to queue
 * 2) Maintain distance array
 * 3) When removed from queue, get all neighbors and add to queue if it's not 2 that is rotten
 * and 0 not empty
 * 4) Traverse distance array and find if there's any fresh orange in grid, if there is any return -1
 * 5) Get the max distance and return that if all oranges are rotten
 *
 * TC: O(rc) - BFS vertices and edges
 * SC: O(rc) distance array, queue
 *
 */
public class RottingOrange {

    private static int rottingOranges(ArrayList<ArrayList<Integer>> grid) {
        int n = grid.size();
        int m = 0;

        if(grid.get(0) != null) {
            m = grid.get(0).size();
        }

        int[][] distance = new int[n][m];
        int dist = 0;
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid.get(i).get(j) == 2) {
                    int[] a = {i, j};
                    queue.add(a);
                 }
            }
        }

        bfs(queue, grid, distance);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid.get(i).get(j) == 1) {
                    return -1;
                } else {
                    dist = Math.max(distance[i][j], dist);
                }
            }
        }
        return dist;
    }

    private static void bfs(Queue<int[]> queue, ArrayList<ArrayList<Integer>> grid, int[][] distance) {

        while(!queue.isEmpty()) {
            int[] v = queue.remove();

            for(int[] w: getNeighbors(v, grid)) {

                if(grid.get(w[0]).get(w[1]) == 1) {
                    grid.get(w[0]).set(w[1], 2);
                    queue.add(w);
                    distance[w[0]][w[1]] = distance[v[0]][v[1]] + 1;
                }
            }
        }

    }

    private static List<int[]> getNeighbors(int[] v, ArrayList<ArrayList<Integer>> grid) {
        int[][] neighbors = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        List<int[]> result = new ArrayList<>();
        int numRows = grid.size();
        int numCols = grid.get(0).size();

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
        row.add(1);

        grid.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(0);
        row.add(0);

        grid.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(1);
        row.add(0);

        grid.add(row);

        System.out.println("Time by which all oranges will be rotten " + rottingOranges(grid));

        //2  1  1  1
        //1  1  1  1
        //1  1  1  1
        //1  1  1  2
    }
}
