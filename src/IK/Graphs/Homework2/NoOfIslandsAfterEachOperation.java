package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Number Of Islands After Each Operation
 * Given the total number of rows and columns of a two-dimensional grid with a list of location points for update operations,
 * count the total number of islands after each update operation.
 *
 * The grid represents a map where 0 represents water and 1 represents land. Initially, all of the cells of the grid are
 * assumed to be water cells.
 *
 * An update operation on a point turns the water at that specific location into land.
 *
 * Return an array of integers where the ith element is the number of islands after applying the ith operation.
 *
 * Example One
 * {
 * "row_count": 2,
 * "column_count": 2,
 * "update_positions": [
 * [0, 0],
 * [1, 1],
 * [0, 1],
 * [1, 0]
 * ]
 * }
 * Output:
 *
 * [1, 2, 1, 1]
 * Explanation: Initially the grid looks as following:
 *
 * 0 0
 * 0 0
 * After the 1st update operation at location (0, 0), it becomes:
 *
 * 1 0
 * 0 0
 * Total number of islands is 1 now. After the 2nd update, it looks like following:
 *
 * 1 0
 * 0 1
 * Now, the total number of islands becomes 2.
 *
 * Example Two
 * {
 * "row_count": 3,
 * "column_count": 3,
 * "update_positions": [
 * [0, 0],
 * [0, 2],
 * [0, 1],
 * [2, 1],
 * [1, 1]
 * ]
 * }
 * Output:
 *
 * [1, 2, 1, 2, 1]
 * Notes
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. It can be assumed that all four edges of the grid are all surrounded by water.
 *
 * Constraints:
 *
 * 1 <= number of rows, columns <= 104
 * 1 <= number of rows * number of columns <= 106
 * 1 <= update operations <= 106
 * All update locations will be distinct, valid, and inside the grid.
 *
 * Approach
 * 1) To count number of islands after each operation, we need to do union and find.
 * 2) Create 1D int parent array of size rowCount * colCount
 * 3) Initialize it with -1
 * 4) Create 1D size array of size rowCount * colCount
 * 5) Initialize it with 1
 * 6) compCount variable and countList
 * 7) For each operation, find the index = row * colCount + col
 * 8) Find all the neighbors whose parent is not -1
 * 9) Add them to list because those are the edges
 * 10) If parent is not -1 then set parent to the index and increment compCount
 * 10) Iterate the edges and call union and find which return compCount
 * 11) Add compCount to countList
 * 12) In Union and find do find and union and return compCount
 *
 * TC: O(n)  Union takes O(n) and find path using path compression is O(1)
 * where n = number of vertices which is rowCount * colCount
 * SC: O(n) - parent and size
 */
public class NoOfIslandsAfterEachOperation {

    private static ArrayList<Integer> noOfIslands(int rowCount, int colCount, ArrayList<ArrayList<Integer>> positions) {
        int n = rowCount * colCount;

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] size = new int[n];
        Arrays.fill(size, 1);

        ArrayList<Integer> countList = new ArrayList<>();
        int compCount = 0;

        for(ArrayList<Integer> pos: positions) {
            int row = pos.get(0);
            int col = pos.get(1);

            int posIndex = row * colCount + col;

            //Get all edges
            int[][] neighbors = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
            List<Integer> edges = new ArrayList<>();

            for(int[] ngb: neighbors) {
                int r = row + ngb[0];
                int c = col + ngb[1];

                int index = r * colCount + c;
                if(r >= 0 && r < rowCount && c >= 0 && c < colCount && parent[index] != -1) {
                    edges.add(index);
                }
            }

            if(parent[posIndex] == -1) {
                parent[posIndex] = posIndex;
                compCount++;
            }

            for(int v: edges) {
                compCount = unionAndFind(parent, size, posIndex, v, compCount);
            }
            countList.add(compCount);
        }
        return countList;
    }

    private static int unionAndFind(int[] parent, int[] size, int u, int v, int compCount) {
        int rootu = find(u, parent);
        int rootv = find(v, parent);

        if(rootu != rootv) {
            if(size[rootu] > size[rootv]) {
                parent[rootv] = rootu;
                size[rootu] += size[rootv];
            } else {
                parent[rootu] = rootv;
                size[rootv] += size[rootu];
            }
            compCount--;
        }
        return compCount;
    }

    private static int find(int x, int[] parent) {
        if(x == parent[x]) {
            return x;
        } else {
            int rootx = find(parent[x], parent);
            parent[x] = rootx;
            return rootx;
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> positions = new ArrayList<>();

        ArrayList<Integer> row = new ArrayList<>();
        row.add(0);
        row.add(0);

        positions.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(1);

        positions.add(row);

        row = new ArrayList<>();
        row.add(0);
        row.add(1);

        positions.add(row);

        row = new ArrayList<>();
        row.add(1);
        row.add(0);

        positions.add(row);

        System.out.println("Number of islands after each operations " +noOfIslands(2, 2, positions));
    }
}
