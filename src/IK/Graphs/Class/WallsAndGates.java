package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 2^31 - 1 = 2147483647
 * to represent INF as you may assume that the distance to a gate
 * is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate.
 * If it is impossible to reach a gate, it should be filled with INF.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * Example 2:
 *
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 *
 *
 * Constraints:
 *
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 231 - 1.
 *
 * resources/WallsAndGates.jpg
 *
 * Approach
 * 1) Do BFS from all the gates at the same time
 * 2) Use the given array for distance and visited.
 * 3) Cell is not visited, if it's Integer.MAX_VALUE
 *
 * TC: O(nm) for BFS for all vertices = nm
 * SC: O(nm) for queue
 */
public class WallsAndGates {

    private static void wallsAndGates(int[][] rooms) {

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length; j++) {

                //If gate
                if(rooms[i][j] == 0) {
                    int[] a = {i, j};
                    queue.add(a);
                }
            }
        }

        //BFS
        while(!queue.isEmpty()) {
            int[] v = queue.remove();

            for(int[] w: getNeighbors(v, rooms)) {

                if(rooms[w[0]][w[1]] == Integer.MAX_VALUE) { //Not visited
                    rooms[w[0]][w[1]] = rooms[v[0]][v[1]] + 1;
                    queue.add(w);
                }
            }
        }
    }

    private static List<int[]> getNeighbors(int[] v, int[][] rooms) {
        int r = v[0];
        int c = v[1];

        int numRows = rooms.length;
        int numCols = rooms[0].length;

        List<int[]> result = new ArrayList<>();

        int[][] ngb = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        for(int[] n: ngb) {
            int row = n[0] + r;
            int col = n[1] + c;

            if(row >= 0 && row < numRows && col >= 0 && col < numCols) {
                int[] a = {row, col};
                result.add(a);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] rooms = {{2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}};

        wallsAndGates(rooms);

        System.out.println(Arrays.deepToString(rooms));
    }
}
