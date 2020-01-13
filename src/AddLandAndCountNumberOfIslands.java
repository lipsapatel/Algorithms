import java.util.ArrayList;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.

 Example:
 Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 Output: [1,1,2,3]

 Explanation:
 Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

 0 0 0
 0 0 0
 0 0 0

 Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

 1 0 0
 0 0 0   Number of islands = 1
 0 0 0

 Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

 1 1 0
 0 0 0   Number of islands = 1
 0 0 0

 Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

 1 1 0
 0 0 1   Number of islands = 2
 0 0 0

 Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

 1 1 0
 0 0 1   Number of islands = 3
 0 1 0

 Follow up:

 Can you do it in time complexity O(k log mn), where k is the length of the positions?

 Approach

 1) Create a grid where each cell is a Node which has value and parent.
 2) Iterate the position and check if it's already land, do nothing
 3) Add land and increment the global count
 4) Then call union for all 4 valid neighbors(with in boundary and is land)
 5) When you find the parent of nodes make sure the find uses path compression which will reduce it's time complexity
 to logn

 Time Complexity: klogmn where k is the size of positions array and mXn is the size of grid.

 */
public class AddLandAndCountNumberOfIslands {

    //Node call; each cell in grid is node
    static class Node {

        int value;
        Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    private static int count = 0;

    private static List<Integer> countNumberOfIslandsAfterAddingLand(int m, int n, int[][] positions) {

        List<Integer> result = new ArrayList<>();

        //Validate the inputs
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0 || positions[0].length == 0) {
            return result;
        }

        //Initialize the Node grid; parent points to itself
        Node[][] grid = new Node[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = new Node(0);
                grid[i][j].parent = grid[i][j];
            }
        }

        for (int i = 0; i < positions.length; i++) {
            int row = positions[i][0];
            int col = positions[i][1];

            //If it's already 1, no need to add land
            if (grid[row][col].value == 1) {
                result.add(count);
                continue;
            }

            //Add land
            grid[row][col].value = 1;
            count++;

            for(int[] array: getNeighbors(grid, row, col)) {

                int rowx = array[0];
                int colx = array[1];
                union(grid, row, col, rowx, colx);
            }
            result.add(count);
        }
        return result;
    }

    private static void union(Node[][] grid, int row, int col, int rowx, int colx) {

        Node p = find(grid[row][col]);
        Node px = find(grid[rowx][colx]);

        if (p != px) {
            p.parent = px;
            count--;
        }
    }

    //Find with path compression
    private static Node find(Node v) {

        if (v.parent != v) {
            v.parent = find(v.parent);
            return v.parent;
        }

        return v;
    }

    private static List<int[]> getNeighbors(Node[][] grid, int row, int col) {
        List<int[]> result = new ArrayList<>();

        if (row - 1 >= 0 && grid[row - 1][col].value == 1) {
            int[] array = {row - 1, col};
            result.add(array);
        }

        if (col - 1 >= 0 && grid[row][col - 1].value == 1) {
            int[] array = {row, col - 1};
            result.add(array);
        }

        if (row + 1 < grid.length && grid[row + 1][col].value == 1) {
            int[] array = {row + 1, col};
            result.add(array);
        }

        if(col + 1 < grid[0].length && grid[row][col + 1].value == 1) {
            int[] array = {row, col + 1};
            result.add(array);
        }
        return result;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] positions = {{0,0}, {0,1}, {1,2}, {1, 2}};

        List<Integer> islandCount = countNumberOfIslandsAfterAddingLand(m, n, positions);
        System.out.println("The number of islands after each add land operation");
        for(int c: islandCount) {
            System.out.print(c + " ");
        }
    }
}
