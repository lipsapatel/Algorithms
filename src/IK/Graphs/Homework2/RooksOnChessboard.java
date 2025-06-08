package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * There are some rooks on a chessboard. If a rook A attacks a rook B,
 * then A will come at B’s position, and B will be removed from the board.
 * Given a list rooks where rooks[i] = [xi, yi] represents the location
 * of the ith rook on the chessboard. Return the highest number of rooks
 * that can be removed.
 *
 * Example
 * {
 * "rook_positions": [
 * [0, 0],
 * [0, 1],
 * [1, 0],
 * [1, 2],
 * [2, 1],
 * [2, 2]
 * ]
 * }
 * Output:
 *
 * 5
 * Marking the rooks as A-F and vacant positions as X, a maximum of 5 rooks can be removed in the following way.
 *
 * Initial position,
 *
 * A B X
 * C X D
 * X E F
 * After B takes A,
 *
 * B X X
 * C X D
 * X E F
 * After B takes C,
 *
 * X X X
 * B X D
 * X E F
 * After B takes D,
 *
 * X X X
 * X X B
 * X E F
 * After B takes F,
 *
 * X X X
 * X X X
 * X E B
 * After B takes E,
 *
 * X X X
 * X X X
 * X B X
 * Notes
 * A rook attacks another rook if it is in the same row or column of that rook.
 *
 * Constraints:
 *
 * 0 <= xi, yi < 105
 * 0 <= total number of rooks <= 105
 * No two rooks share the same coordinate point.
 *
 * Approach
 *
 * 1) Rook A can attack rook B if B is in the same row or column
 * 2) Which means either x or y is same.
 * 3) Do union and find of x and y coordinate
 * 4) parent for x will be 1 to 10^5 and parent for y will be 10^5 to 10^5 * 2
 * 5) After doing union and find, iterate over the rook positions x coordinate
 * and find unique parent
 * 6) Unique parent size represents that many rooks are alive, rest are
 * destroyed or dead
 * 7) So total rooks - unique parent size = maximum destroyed rooks
 *
 * TC: O(n) where n = number of rooks or rook_positions
 * SC: O(n) where n = 10^5 for parent and unique parent hashset
 */
public class RooksOnChessboard {

    private static int countMaximumRemoved(ArrayList<ArrayList<Integer>> rookPositions) {
        int n = 100000;
        int[] parent = new int[n * 2];
        int[] size = new int[n * 2];

        for(int i = 0; i < n * 2; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < n * 2; i++) {
            size[i] = 1;
        }

        for(ArrayList<Integer> position: rookPositions) {
            int u = position.get(0);
            int v = position.get(1) + n;

            int rootu = find(parent, u);
            int rootv = find(parent, v);

            if(rootu != rootv) {
                if(size[rootu] < size[rootv]) {
                    parent[rootu] = rootv;
                    size[rootv] += size[rootu];
                } else {
                    parent[rootv] = rootu;
                    size[rootu] += size[rootv];
                }
            }
        }

        HashSet<Integer> uniqueParent = new HashSet<>();
        for(ArrayList<Integer> position: rookPositions) {
            uniqueParent.add(find(parent, position.get(0)));
        }

        return rookPositions.size() - uniqueParent.size();
    }

    private static int find(int[] parent, int x) {
        if(x == parent[x]) {
            return x;
        } else {
            int rootx = find(parent, parent[x]);
            parent[x] = rootx;
            return rootx;
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> rookPositions = new ArrayList<>();

        ArrayList<Integer> row = new ArrayList<>();
        row.add(0);
        row.add(0);

        rookPositions.add(row);

        row = new ArrayList<>();
        row.add(0);
        row.add(1);

        rookPositions.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(0);

        rookPositions.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(2);

        rookPositions.add(row);

        row = new ArrayList<>();
        row.add(2);
        row.add(1);

        rookPositions.add(row);

        row = new ArrayList<>();
        row.add(2);
        row.add(2);

        rookPositions.add(row);

        System.out.println("Number of rooks removed " + countMaximumRemoved(rookPositions));
    }
}
