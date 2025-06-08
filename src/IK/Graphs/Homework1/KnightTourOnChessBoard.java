package IK.Graphs.Homework1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given a chessboard with rows rows and cols columns and a knight -
 * that moves like in normal chess - located at the start_row-th row and
 * start_col-th column. The knight needs to reach the position at the end_row-th
 * row and end_col-th column.
 *
 * Find minimum number of moves that knight needs to make to get from
 * starting position to ending position.
 *
 * start_row, start_col, end_row and end_col are all zero-indexed.
 *
 * Example
 * {
 * "rows": 5,
 * "cols": 5,
 * "start_row": 0,
 * "start_col": 0,
 * "end_row": 4,
 * "end_col": 1
 * }
 * Output:
 *
 * 3
 * 3 moves to reach from (0, 0) to (4, 1):
 * (0, 0) → (1, 2) → (3, 3) → (4, 1).
 *
 * Notes
 * If it is not possible to reach from starting position to ending position, then return -1.
 * Constraints:
 *
 * 1 <= rows * cols <= 105
 * 0 <= start_row, end_row < rows
 * 0 <= start_col, end_col < cols
 *
 * Approach
 * 1) Do BFS with distance array and visited array
 * 2) No need to construct graph. GetNeighbors will return all neighbors.
 *
 * TC: O(n + m) where n = rows * cols m = 8 * (rows * cols)
 * SC: O(n) for visited, queue and distance where n = rows * cols
 */
public class KnightTourOnChessBoard {

    private static Integer findMinimumNumberOfMoves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {

        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        int[] start = {start_row, start_col};

        queue.add(start);
        visited[start_row][start_col] = true;
        distance[start_row][start_col] = 0;

        while(!queue.isEmpty()) {
            int[] v = queue.remove();

            if(end_row == v[0] && end_col == v[1]) {
                return distance[v[0]][v[1]];
            }
            for(int[] w: getNeighbors(v, rows, cols)) {

                if(!visited[w[0]][w[1]]) {
                    visited[w[0]][w[1]] = true;
                    queue.add(w);
                    distance[w[0]][w[1]] = distance[v[0]][v[1]] + 1;
                }
            }
        }
        return -1;
    }

    private static List<int[]> getNeighbors(int[] v, int rows, int cols) {
        int[][] neighbors = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        List<int[]> result = new ArrayList<>();

        for(int[] n: neighbors) {
            int row = v[0] + n[0];
            int col = v[1] + n[1];

            if(row >= 0 && row < rows && col >= 0 && col < cols) {
                int[] a = {row, col};
                result.add(a);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(findMinimumNumberOfMoves(5, 5, 0, 0, 4, 1));
    }

}
