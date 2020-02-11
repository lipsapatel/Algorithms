import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Count Basins
 Problem Statement:
 You are given a matrix where each number represents altitude of that cell, such that, water flows towards lower altitudes.
 If a cell’s four neighboring cells all have higher altitudes, we call this cell a sink; water collects in sinks.
 Otherwise, water will flow to the neighboring cell with the lowest altitude. If a cell is not a sink, you may assume
 it has a unique lowest neighbor and that this will be lower than the cell.

 Cells that drain into the same sink – directly or indirectly – are said to be part of the same basin.
 Your challenge is to partition the map into basins. Your code should output the sizes of the basins,
 in non-decreasing order.

 Input Format:
 There will be only one argument matrix denoting the matrix of size row_count * col_count, with the altitude
 represented as int in each cell.

 Output Format:
 Return an array of integers, denoting the sizes of basins, in non-decreasing order.

 Input Format:

 For row_count=3, col_count=3 and matrix = [ [1, 5, 2], [2, 4, 7], [3, 6, 9]]

 3
 3
 1 5 2
 2 4 7
 3 6 9

 Output Format:

 The output contains an array of numbers basin_sizes. Values of the basin_sizes array will be represented as one on a line.
 For row_count=3, col_count=3 and matrix = [ [1, 5, 2], [2, 4, 7], [3, 6, 9]], output will be:

 2
 7

 Constraints:

 1 <= row_count * col_count <= 1000000.
 0 <= matrix[i][j] <= 1000000 where 0<=i<row_count, 0<=j<col_count.

 Sample Test Cases:

 3
 3
 1 5 2
 2 4 7
 3 6 9

 Sample Output:
 2
 7

 Explanation:
 The basins, labeled with A’s and B’s, are:
 A A B
 A A B
 A A A

 Every cell labeled with A will sink at (0, 0), whereas B will sink at (0, 2).

 Sample Input 2:
 4
 4
 0 2 1 3
 2 1 0 4
 3 3 3 3
 5 5 2 1

 Sample Output 2:
 4
 5
 7

 Explanation:
 The basins, labeled with A’s and B’s, are:
 A A B B
 A B B B
 A B B C
 A C C C

 Every cell labeled with A will sink at (0, 0), B will sink at (1, 2) and C will sink at (3, 3).

 * Approach
 * 1) Create grid containing Vertex which has altitude, parent and count information
 * 2) Iterate the grid and for each cell find the min neighbor
 * 3) Do union with the minimum neighbor.
 * 4) All nodes with parent null are sink.
 * 5) The count of all sink nodes needs to be store in array
 * 6) Sort the array and return.
 *
 * resources/CountBasins.jpg
 * TC = O(rows * cols) if not using counting sort then TC = O(rows * cols) log(rows * cols)
 * SC = O(rows * cols)
 *
 * Approach 2:
 *
 * Initialize another grid with -1.
 * 1)Do the DFS
 * 2) Get sink for that node, which in turn get sink for min neighbor node recursively
 * 3) Keep the track of tag id
 * 4) Tag all nodes which belong to same cluster with the same id
 *
 * Time Complexity= O(rows * cols)
 * Space Complexity= O(rows * cols)
 */
public class CountBasins {

    //Define neighbors
    private static int moves = 4;
    private static int[] rows = {1, -1, 0, 0};
    private static int[] cols = {0, 0, 1, -1};

    private static List<Integer> countBasinsClusters(List<List<Integer>> matrix) {

        //Union and find approach to find the connected components
        Vertex[][] grid = createGrid(matrix);

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                Vertex me = grid[i][j];
                Vertex minNeighbor = getMinNeighbor(i, j, grid);

                if(minNeighbor.altitude != me.altitude) { //Both are not same node, then do union where minNeighbor becomes parent
                    union(minNeighbor, me);
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j].parent == null) {
                    result.add(grid[i][j].count);
                }
            }
        }

        //Collections.sort(result); //The worst case TC = O((rows*cols)log(rows*cols))

        return countingSort(result); //TC = O(rows * cols)
        //return result;
    }

    private static List<Integer> countingSort(List<Integer> array) {
        //Copy the List<Integer> to array
        int[] input = new int[array.size()];
        int[] result = new int[array.size() + 1]; //0 index is unused

        for(int i = 0; i < array.size(); i++) {
            input[i] = array.get(i);
        }

        //Find the max element in input array to determine the size of count array
        int max = findMax(input);

        //Create count array of size max and initialize with 0
        int[] count = new int[max + 1];
        Arrays.fill(count, 0);

        //Store the count of input in count array
        for(int i = 0; i < input.length; i++) {
            count[input[i]] = count[input[i]] + 1;
        }

        //Update the count to sum till prev step
        for(int i = 1; i < count.length; i++) {
            count[i] = count[i - 1] + count[i];
        }

        //Scan the input array and find the index of element in the count array
        for(int i = 0; i < input.length; i++) {
            result[count[input[i]]] = input[i];
            //Decrement the count
            count[input[i]] = count[input[i]] - 1;
        }

        //Copy result array to result list for returning
        List<Integer> resultList = new ArrayList<>();
        for(int i = 1; i < result.length; i++) {
            resultList.add((result[i]));
        }
        return resultList;
    }

    private static int findMax(int[] array) {
        int max = array[0];

        for(int i = 0; i < array.length; i++) {
            if(array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private static void union(Vertex parent, Vertex child) {
        Vertex parentByPathCompression = find(parent);
        child.parent = parentByPathCompression;

        parentByPathCompression.count = child.count + parentByPathCompression.count;
    }

    private static Vertex find(Vertex v) {
        if(v.parent != null) {
            v.parent = find(v.parent);
            return v.parent;
        }
        return v;
    }

    private static Vertex getMinNeighbor(int row, int col, Vertex[][] grid) {
        Vertex minVertex = grid[row][col];

        for(int i = 0; i < moves; i++) {
            int nr = row + rows[i];
            int nc = col + cols[i];

            if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid.length) {
                Vertex neighbor = grid[nr][nc];

                if(neighbor.altitude < minVertex.altitude) {
                    minVertex = neighbor;
                }
            }
        }
        return minVertex;
    }

    private static Vertex[][] createGrid(List<List<Integer>> matrix) {
        int row = matrix.size();
        int col = matrix.get(0).size();

        Vertex[][] grid = new Vertex[row][col];

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                grid[i][j] = new Vertex(matrix.get(i).get(j));
            }
        }
        return grid;
    }

    public static class Vertex {
        int altitude;
        int count;
        Vertex parent;

        public Vertex(int altitude) {
            this.altitude = altitude;
            this.count = 1; //each node is in cluster made of itself
            this.parent = null; //By default the parent is pointing to null
        }
    }

    /***********************************************************************************************************/

    private static List<Integer> countBasins(List<List<Integer>> matrix) {
        int[][] grid = createGrid1(matrix);

        int tagId = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(getSink(i, j, grid, matrix, tagId) == tagId) { //Increment the tag id since it's already set
                    tagId++;
                }
            }
        }

        int[] basinsCount =new int[tagId];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                basinsCount[grid[i][j]]++;
            }
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < basinsCount.length; i++) {
            result.add(basinsCount[i]);
        }
        return countingSort(result);
    }

    private static int getSink(int row, int col, int[][] grid, List<List<Integer>> matrix, int tagId) {
          if(grid[row][col] == -1) {
              int[] minNeighbor = getMinNeighbor(row, col, matrix);

              if(minNeighbor[0] == row && minNeighbor[1] == col) { //the node itself is sink
                  grid[row][col] = tagId;
              } else {
                  grid[row][col] = getSink(minNeighbor[0], minNeighbor[1], grid, matrix, tagId);
              }
          }
          return grid[row][col];
    }

    private static int[] getMinNeighbor(int row, int col, List<List<Integer>> matrix) {
        int[] minNeighbor = new int[2];
        minNeighbor[0] = row;
        minNeighbor[1] = col;
        int minNeighborVertex = matrix.get(row).get(col);

        for(int i = 0; i < moves; i++) {
            int nr = row + rows[i];
            int nc = col + cols[i];

            if(nr >= 0 && nr < matrix.size() && nc >= 0 && nc < matrix.get(0).size()) {
                if(matrix.get(nr).get(nc) < minNeighborVertex) {
                    minNeighborVertex = matrix.get(nr).get(nc);
                    minNeighbor[0] = nr;
                    minNeighbor[1] = nc;
                }
            }
        }
        return minNeighbor;
    }

    private static int[][] createGrid1(List<List<Integer>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        int[][] grid = new int[rows][cols];

        for(int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], -1);
        }
        return grid;
    }

    public static void main(String[] args) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        row.add(5);
        row.add(2);

        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(row);

        row = new ArrayList<>();
        row.add(2);
        row.add(4);
        row.add(7);
        matrix.add(row);

        row = new ArrayList<>();
        row.add(3);
        row.add(6);
        row.add(9);
        matrix.add(row);

        List<Integer> result = countBasinsClusters(matrix);

        System.out.println("The count of basins clusters are: ");
        for(int i: result) {
            System.out.print(i + " ");
        }

        System.out.println();
        result = countBasins(matrix);

        System.out.println("The count of basins clusters are: ");
        for(int i: result) {
            System.out.print(i + " ");
        }
    }
}
