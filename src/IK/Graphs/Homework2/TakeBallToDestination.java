package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a grid consisting of empty cells, walls, a source, and a destination,
 * return true if a ball can stop at the destination starting from the source,
 * false otherwise.
 *
 * Following are the different types of cells in the grid.
 *
 * S is the source.
 * D is the destination.
 * O is an empty cell, and a ball can travel through these cells.
 * X is a wall, and a ball can not pass through these cells.
 * A ball can go through the empty cells by rolling up, down, left or right.
 * The ball stops when it hits a wall; it can not stop or change its direction
 * until then. After stopping, it can choose the next direction.
 *
 * Example One
 * {
 * "grid": [
 * "XXXXX",
 * "OSOXO",
 * "OOOOO",
 * "OXODX",
 * "XXXXX"
 * ]
 * }
 * Output:
 *
 * true
 * One possible way is : right -> down -> right.
 *
 * Example Two
 * {
 * "grid": [
 * "SOO",
 * "ODO",
 * "XOO"
 * ]
 * }
 * Output:
 *
 * false
 * It is possible to pass through the destination,
 * but there is no way for the ball to stop at the destination.
 *
 * Notes
 * Consider the borders of the grid are all walls.
 *
 * Constraints:
 *
 * 1 <= number of rows and columns in the grid <= 500
 * The grid will have exactly one cell marked S and one cell marked D.
 *
 * Approach
 * 1) Do BFS and keep rolling until you reach X or invalid cell
 * 2) Mark as visited when the ball stops with character '.'
 * 3) First find the source and add to queue
 * 4) If you stop at destination while rolling, return true
 *
 * TC: O(n + m) = O(rc)
 * SC: O(n + m) = O(rc) - for queue
 */
public class TakeBallToDestination {

    private static Boolean hasPath(ArrayList<String> grid) {
        int numRows = grid.size();
        int numCols = grid.get(0).length();

        //Find the source
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(grid.get(i).charAt(j) == 'S') {
                    int[] start = {i, j};
                    queue.add(start);
                }
            }
        }

        //Do BFS
        while(!queue.isEmpty()) {
            int[] v = queue.remove();

            int[][] neighbors = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

            for(int[] n: neighbors) {
                int row = n[0] + v[0];
                int col = n[1] + v[1];

                //Keep rolling till we hit X or invalid cell
                while(row >= 0 && row < numRows && col >= 0 && col < numCols && grid.get(row).charAt(col) != 'X') {
                    row = row + n[0];
                    col = col + n[1];
                }

                //If reached destination return true
                if(grid.get(row - n[0]).charAt(col - n[1]) == 'D') {
                    return true;
                }

                //If not visited then mark as visited and add to queue
                if(grid.get(row - n[0]).charAt(col - n[1]) == 'O') {
                    StringBuilder sb = new StringBuilder(grid.get(row - n[0]));
                    sb.setCharAt(col - n[1], '.');

                    grid.set(row - n[0], sb.toString());

                    //Add to queue
                    int[] a = {row - n[0], col - n[1]};
                    queue.add(a);
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        ArrayList<String> grid = new ArrayList<>();
        grid.add("XXXXX");
        grid.add("OSOXO");
        grid.add("OOOOO");
        grid.add("OXODX");
        grid.add("XXXXX");

        System.out.println(hasPath(grid));

        grid = new ArrayList<>();
        grid.add("SOO");
        grid.add("ODO");
        grid.add("XOO");

        System.out.println(hasPath(grid));
    }
}
