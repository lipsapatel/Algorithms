import java.util.ArrayList;
import java.util.List;

/**
 * Largest sub-square matrix with all 1s
 * Given a 2D matrix mat of integers with n rows and m columns. All the elements in the matrix mat are either 0 or 1.
 * Your task is to determine the largest sub-square size of the matrix that contains only 1s.
 *
 * Input Format:
 * If n=3, m=3 and mat=[ [1,0,0], [0,1,1], [0,1,1]], then input should be:
 * 3
 * 3
 * 1 0 0
 * 0 1 1
 * 0 1 1
 *
 * Output Format:
 * There should be a single number representing result.
 * output should be:
 * 2
 *
 * Constraints:
 *     1 <= n, m <= 1000
 *     mat[i][j] can be 0 or 1 where 0<=i<n and 0<=j<m.
 *
 *  n = 3
 * m = 3
 * mat = [ [1,0,0] , [0,1,1] , [0,1,1] ]
 *
 * Sample Output:
 * 2
 *
 * Explanation:
 * The given matrix is represented below:
 * 1 0 0
 * 0 1 1
 * 0 1 1
 *
 * Here, we can easily infer that the 1s in bold form a sub-square matrix and is of the largest size(2*2) in the
 * matrix such that all the elements in the sub-matrix are 1. Hence, the answer is 2.
 *
 * *******************************************************************************************************************
 *
 * Solution
 * 1) brute_force_solution:
 *
 * Description:
 * In this approach we assume every cell in the matrix as the top-left.
 * We iterate over matrix and try to see what is the maximum size of sub-square matrix we can obtain
 * satisfying that all elements in the sub-square matrix are 1.
 *
 * Time Complexity:
 * O( (n*m)^2) where n is number of rows of matrix and m is number of columns of matrix.
 *
 * To visit each cell and choose it as top-left cell of the sub-square matrix take O(n*m) time.
 * Now to calculate the maximum size of sub-square matrix we start looking if it is feasible for a size 1 matrix,
 * then for size 2 and so on. Next is to check if the corresponding size is possible or not.
 * Since it feasible to have a sub-square matrix of all 1s for (size-1).
 * So, for sub-matrix of size, it can done by two linear traversal one row wise and another column wise for the
 * last row and last column of the sub-matrix.
 *
 * Therefore, the total time complexity becomes O(n*m)*O(n*m) → O((m*n)^2).
 *
 * Auxiliary Space Used:
 * O(1).
 * Since we are only traversing on the original matrix without storing anything extra.
 *
 * Space Complexity:
 * * O(n*m) where n is number of rows of matrix and m is number of columns of matrix.
 * For storing input it will take space of O(n*m) and auxiliary space used is O(1).
 * So, O(n*m) + O(1) → O(n*m).
 *
 * 2) DP solution:
 *
 * Description:
 * In this solution, we approach the problem dynamically.
 * Let’s first decide a state for our DP solution. Here we choose state(i, j), which will tell us the
 * maximum size of sub-square matrix with all 1s considering the cell (i, j) as the bottom-right most cell of the
 * sub matrix. Now, we see that for a state(i, j), its value can be computed using the below state relation:
 *
 * state(i, j) = min(state(i, j-1) ,state(i-1, j) ,state(i-1, j-1)) + 1 if mat[i][j] = 1
 * state(i, j) = 0 otherwise.
 *
 * The approach in this solution is same as the other_solution that uses the same dynamic programming state relation.
 * Here, instead of taking an auxiliary memory we use the provided input matrix to store the DP state and
 * once when all the DP states are computed and we have our answer.
 *
 *  Time Complexity:
 * O(n*m)
 *
 * Same as other_solution O(n*m) as the algorithm remains the same.
 *
 * Auxiliary Space Used:
 * O(1).
 * Since we are using the original input matrix to store DP states.
 *
 * Space Complexity:
 * O(n*m) where n is number of rows of matrix and m is number of columns of matrix.
 * For storing input it will take space of O(n*m) and auxiliary space used is O(1).
 *
 * So, O(n*m) + O(1) → O(n*m).
 * **********************************************************************************************************************
 *
 * Recursion Solution
 *
 * Time Complexity: O(3 ^ (max(m,n)))
 * Space Complexity: O(max(m, n))
 *
 * resources/LargestSubSquareMatrixBruteForce.jpg
 * resources/LargestSubSquareMatrixRecursion.jpg
 * resources/LargestSubSquareMatrixDp.jpg
 *
 * To find the size of the matrix ending in i, j, find the min size of matrix ending in top, left and topLeft
 * and add 1.
 *
 * For all the cells in the first row and first col, the size = matrix value.
 *
 * ****************************************************************************************************************
 *
 * DP Solution
 *
 * Time Complexity: O(mn)
 * Space Complexity: O(mn)
 *
 * *************************************************************************************
 *
 * Dp Solution reusing the same matrix
 * This saves space
 * You can recover the original matrix after done.
 *
 * Time Complexity: O(mn)
 * Space Complexity: O(1)
 */
public class LargestSubSquareMatrix {

    public static int maxSize = 0;

    public static int largestSubSquareMatrixRecursion(int n, int m, List<List<Integer>> matrix) {

        largestSubSquareMatrixRecursionHelper(n - 1, m - 1, matrix);
        return maxSize;
    }

    public static int largestSubSquareMatrixRecursionHelper(int i, int j, List<List<Integer>> matrix) {

        //Base Case
        if(i == 0 || j == 0) {
            maxSize = Math.max(maxSize, matrix.get(i).get(j));
            return matrix.get(i).get(j);
        }

        //Find the largest square matrix ending in i, j - 1 - Left
        int left = largestSubSquareMatrixRecursionHelper(i, j - 1, matrix);

        //Find the largest square matrix ending in i - 1, j - top
        int top = largestSubSquareMatrixRecursionHelper(i - 1, j, matrix);

        //Find the largest square matrix ending in i - 1 and j - 1 - top left
        int topLeft = largestSubSquareMatrixRecursionHelper(i - 1, j - 1, matrix);

        int size = 0;
        if(matrix.get(i).get(j) == 1) {
            size = 1 + Math.min(left, Math.min(top, topLeft));
        }

        //Update the max size found so far
        maxSize = Math.max(maxSize, size);

        //Return the size of matrix ending in i, j
        return size;
    }

    public static int largestSubSquareMatrixDp(int n, int m, List<List<Integer>> matrix) {
        int maxSize = 0;

        //Identify the dp table
        int[][] dp = new int[n][m];

        //Initialize the dp table. Base case i == 0 and j == 0
        //Fill the first row and first col with original matrix values
        for(int i = 0; i < m; i++) {
            //row
            dp[0][i] = matrix.get(0).get(i);
            maxSize = Math.max(maxSize, dp[0][i]);
        }

        for(int j = 0; j < n; j++) {
            //col
            dp[j][0] = matrix.get(j).get(0);
            maxSize = Math.max(maxSize, dp[j][0]);
        }

        //Traversal Direction. Recursion i = n - 1 and j = m - 1
        // i = 1 to n - 1 and j = 1 to m - 1
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                int size = 0;
                if(matrix.get(i).get(j) == 1) {
                    size = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
                dp[i][j] = size;
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }
        return maxSize;
    }

    private static int largestSubSquareMatrixDpReusingSpace(int n, int m, List<List<Integer>> matrix) {

        int maxSize = 0;

        //Identify the dp table. Two params changing i and j
        //Let's use the same given matrix

        //Initialize the dp table. Base case: If i == 0 or j == 0
        //Reusing the same matrix so first row and first col is original

        //Update maxSize to the max of first row and first col
        //First Row
        for(int i = 0; i < m; i++) {
            maxSize = Math.max(maxSize, matrix.get(0).get(i));
        }

        //First Col
        for(int i = 0; i < n; i++) {
            maxSize = Math.max(maxSize, matrix.get(i).get(0));
        }

        //Traversal direction. Recursion i = n - 1 and j = m - 1
        for(int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {

                //Populate dp table
                int size = 0;
                if (matrix.get(i).get(j) == 1) {
                    size = 1 + Math.min(matrix.get(i).get(j - 1),
                            Math.min(matrix.get(i - 1).get(j), matrix.get(i - 1).get(j - 1)));
                }
                matrix.get(i).set(j, size);
                maxSize = Math.max(maxSize, size);
            }
        }

        //Recover the original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix.get(i).get(j) > 1) {
                    matrix.get(i).set(j, 1);
                }
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(0);
        list.add(0);
        matrix.add(list);

        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(1);

        matrix.add(list);

        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(1);

        matrix.add(list);

        System.out.println("The max size sub square matrix " + largestSubSquareMatrixRecursion(3, 3, matrix));
        System.out.println("The max size sub square matrix using Dp " + largestSubSquareMatrixDp(3, 3, matrix));
        System.out.println("The max size sub square matrix using Dp but saving space " + largestSubSquareMatrixDpReusingSpace(3, 3, matrix));
    }
}
