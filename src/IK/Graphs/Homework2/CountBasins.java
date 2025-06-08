package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Count Basins
 * Given the altitudes of the regions on a surface, determine the basins
 * where water would collect if poured onto that surface.
 *
 * Region whose four neighbors (right, left, up and down) are all higher in altitude is
 * called a sink.
 * All the water would collect in sinks. If a region is not a sink, it is guaranteed to
 * have a single
 * lowest neighbor where water will drain to. All regions that drain to a particular
 * sink–directly or
 * indirectly–collectively form one basin. Partition the surface into the basins and
 * return their sizes in the non-decreasing order.
 *
 * Example One
 * {
 * "matrix": [
 * [1, 5, 2],
 * [2, 4, 7],
 * [3, 6, 9]
 * ]
 * }
 * Output:
 *
 * [2, 7]
 * There are two basins, one consists of two cells and the other consists of seven.
 * They are labeled with A’s and B’s here:
 * B B A
 * B B A
 * B B B
 *
 * The sink of basin A is cell (0, 2). The sink of basin B is cell (0, 0).
 *
 * Example Two
 * {
 * "matrix": [
 * [0, 2, 1, 3],
 * [2, 1, 0, 4],
 * [3, 3, 3, 3],
 * [5, 5, 2, 1]
 * ]
 * }
 * Output:
 *
 * [4, 5, 7]
 * There are three basins. They are labeled with A, B and C here:
 * B B C C
 * B C C C
 * B C C A
 * B A A A
 *
 * The sinks of basins A, B and C are (3, 3), (0, 0) and (1, 2) respectively.
 *
 * Notes
 * The function has one argument, a two-dimensional array of integers representing the altitudes of the regions of a rectangular surface.
 * Return an array of integers representing the sizes of basins in the non-decreasing order.
 * Constraints:
 *
 * 1 <= total number of regions on the given surface <= 1000000
 * 0 <= altitude of any region on the surface <= 1000000
 *
 * Approach
 * 1) Create 2D matrix initialized with -1
 * 2) Iterate the cells of the given matrix and find sink
 * 3) The cell itself can be sink or find cell where it can drain
 * 4) If the cell itself is sink, assign basinId
 * 5) If the getSink returns the same basinId then that basinId is used
 * 6) Increment basinId
 * 7) Iterate the basin matrix and count cells with basinId
 * 8) Use counting sort to sort the arraylist
 *
 * TC: O(n) where n = rows * cols
 * SC: O(n) for basin 2D matrix
 *
 */
public class CountBasins {

    private static ArrayList<Integer> countBasins(ArrayList<ArrayList<Integer>> matrix) {
        int numRows = matrix.size();
        int numCols = matrix.get(0).size();
        int[][] basin = new int[numRows][numCols];

        //Initialize basin with -1
        for(int i = 0; i < numRows; i++) {
            Arrays.fill(basin[i], -1);
        }

        //Iterate each cell and find sink
        int basinId = 0;
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(getSink(i, j, basin, matrix, basinId) == basinId) {
                    basinId++;
                }
            }
        }

        //Count the cells with basinId
        ArrayList<Integer> basinCount = new ArrayList<>(Collections.nCopies(basinId, 0));

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                basinCount.set(basin[i][j], basinCount.get(basin[i][j]) + 1);
            }
        }

        countingSort(basinCount);
        return basinCount;
    }

    private static int getSink(int row, int col, int[][] basin, ArrayList<ArrayList<Integer>> matrix, int basinId) {
        //If the cell is not visited, basinId not set
        if(basin[row][col] == -1) {
            //Find minRow and minCol
            int minRow = row;
            int minCol = col;
            int numRows = basin.length;
            int numCols = basin[0].length;

            int[][] neighbors = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

            for(int[] n: neighbors) {
                int r = n[0] + row;
                int c = n[1] + col;

                if(r >= 0 && r < numRows && c >= 0 && c < numCols && matrix.get(r).get(c) < matrix.get(minRow).get(minCol)) {
                    minRow = r;
                    minCol = c;
                }
            }

            if(minRow == row && minCol == col) {
                //Itself is sink
                basin[row][col] = basinId;
            } else {
                //Find where it should drain
                basin[row][col] = getSink(minRow, minCol, basin, matrix, basinId);
            }
        }
        return basin[row][col];
    }

    private static void countingSort(ArrayList<Integer> basinCount) {
        int min = Collections.min(basinCount);
        int max = Collections.max(basinCount);
        int range = max - min + 1;

        int[] countArray = new int[range];

        //Add count
        for(int value: basinCount) {
            countArray[value - min]++;
        }

        int j = 0;
        for(int i = 0; i < countArray.length; i++) {
            int value = i + min;
            int count = countArray[i];

            while(count > 0) {
                basinCount.set(j, value);
                j++;
                count--;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        ArrayList<Integer> row = new ArrayList<>();
        row.add(0);
        row.add(2);
        row.add(1);
        row.add(3);

        matrix.add(row);

        row = new ArrayList<>();
        row.add(2);
        row.add(1);
        row.add(0);
        row.add(4);

        matrix.add(row);

        row = new ArrayList<>();
        row.add(3);
        row.add(3);
        row.add(3);
        row.add(3);

        matrix.add(row);

        row = new ArrayList<>();
        row.add(5);
        row.add(5);
        row.add(2);
        row.add(1);

        matrix.add(row);

        System.out.println("Number of basins " + countBasins(matrix));
    }
}
