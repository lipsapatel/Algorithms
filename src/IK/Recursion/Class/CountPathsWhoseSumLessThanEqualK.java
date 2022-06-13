package IK.Recursion.Class;

/**
 * Count total number of paths whose sum is less than equal to k
 *
 * 1) Recursive Function (grid, row, col, sum)
 * 2) Guard Case: If row >= numRows or col >= numCols then return 0
 * 3) Base Case: If row == numRows - 1 and col == numCols - 1 then check if the sum is less than equal to k
 * 4) sum + grid[row][col] <= k then return 1 else return 0
 * 5) If sum > k then return 0
 * 6) sum = sum + grid[row][col] - add my self
 * 7) Right = (grid, row, col + 1, sum)
 * 8) down = (grid, row + 1, col, sum)
 * 9) return right + down
 *
 * Time Complexity: O(2^(m + n))
 * Space Complexity: O(m + n)
 *
 */
public class CountPathsWhoseSumLessThanEqualK {

    private static int countPaths(int[][] grid, int row, int col, int sum, int k) {

        int numRows = grid.length;
        int numCols = grid[0].length;

        //Guard Case
        if(row >= numRows || col >= numCols) {
            return 0;
        }

        //Base Case
        if(row == numRows - 1 && col == numCols - 1) {

            //Check if sum is less than equal to k
            if((sum + grid[row][col]) <= k) {
                return 1;
            } else {
                return 0;
            }
        }

        //Edge case
        if(sum > k) {
            return 0;
        }

        //Add my self to sum
        sum = sum + grid[row][col];

        int right = countPaths(grid, row, col + 1, sum , k);
        int down = countPaths(grid, row + 1, col, sum, k);
        return right + down;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Number of paths whose sum is less than equal to 24 is " + countPaths(grid, 0, 0, 0, 24));
    }
}
