/**
 * Matrix Chain Multiplication
 * Given a sequence of matrices, find the most efficient way to multiply these matrices together.
 * The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.
 * We have many options to multiply a chain of matrices because matrix multiplication is associative.
 * In other words, no matter how we parenthesize the product, the result will be the same.
 * For example, if we had four matrices A, B, C, and D, we would have:
 *
 * (ABC)D = (AB)(CD) = A(BCD) = ....
 * However, the order in which we parenthesize the product affects the number of simple arithmetic operations needed
 * to compute the product, or the efficiency. For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix.
 * Then,
 *
 * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
 *
 * A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
 *
 * Clearly, the first parenthesization requires less number of operations.
 *
 * For input n = 4 and mtxSizes = [10, 30, 5, 60], output will be:
 * 4500
 *
 * Constraints:
 *     3 <= len(mtxSizes) <= 100
 *     0 <= mtxSizes[i] <= 100
 *     For any matrix, either both the dimensions will be zero, or both the dimensions will be non zero.
 *
 * **********************************************************************************************************************************
 * Given matrix, multiply it in the optimal way.
 *
 * MatrixA = m * n
 * MatrixB = n * k
 * Operations = m * n * k
 * Resultant matrix = m * k
 *
 * There can be many ways to multiply the matrix, return the min number of operations.
 *
 * Recursion Approach:
 *
 * 1) Make cuts at k = i + 1 to j - 1
 * 2) Choose the minimum of (x + y + (input[i] * input[k] * input[j])
 *
 * Time Complexity = O(n - 2) ^ (n- 2)
 * Space Complexity = O(n - 2)
 *
 *  resources/MatrixChainMultiplication.jpg
 *  resources/MatrixChainMultiplicationRecursion.jpg
 *  resources/MatrixChainMultiplicationDP.jpg
 *
 * DP Approach
 *
 * 1) Identify the DP table - int[input.length][input.length]  2 changing params i and j and i = 0 - n - 1
 * 2) Initialize the DP table - j - i < 2, return 0 is the base condition, initialize the bottom triangular matrix 0
 * 3) Traversal direction - Recursion i = 0 and j = n - 1
 * Opposite = i = n - 3 to 0
 * and j = i + 2 to n - 1
 * k = i + 1 to j - 1
 * 4) Populate dp
 * 5) Return dp[0][n - 1]
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(n^2)
 *
 */
public class MatrixChainMultiplication {

    private static int matrixChainMultiplicationRecursive(int[] input, int i, int j) {

        //Base case - 10 x 30 = return 0
        if (j - i < 2) {
            return 0;
        }

        //All possible cuts
        int min = Integer.MAX_VALUE;
        for(int k = i + 1; k <= j - 1; k++) {

            int cut1Operations = matrixChainMultiplicationRecursive(input, i, k);
            int cut2Operations = matrixChainMultiplicationRecursive(input, k, j);

            int operations = cut1Operations + cut2Operations + (input[i] * input[k] * input[j]);

            //Min of all cuts
            min = Math.min(min, operations);
        }
        return min;
    }

    private static int matrixChainMultiplicationDP(int[] input) {

        //Identify the DP table
        //Since there are 2 parameters changing, i and j and return type is int
        //Values it can take = 0 to n - 1
        int[][] dp = new int[input.length][input.length];

        //Initialize the DP table
        //Base case = j - i < 2 return 0
        //Lower triangle of matrix will be 0
        for (int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp.length; j++) {
                if(j - i < 2) {
                    dp[i][j] = 0;
                }
            }
        }

        //Traversal direction - opposite of recursion
        //i = 0 - n - 1 in recursion and j = n - 1 to 0 in recursion
        for(int i = dp.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < dp.length; j++) {

                int min = Integer.MAX_VALUE;
                //Populate the dp
                for (int k = i + 1; k <= j - 1; k++) {

                    int cut1Operations = dp[i][k];
                    int cut2Operations = dp[k][j];

                    int operations = cut1Operations + cut2Operations + (input[i] * input[k] * input[j]);

                    //Min of all cuts
                    min = Math.min(min, operations);
                }
                dp[i][j] = min;
            }
        }
        return dp[0][dp.length - 1]; //Same as the recursion start
    }

    public static void main(String[] args) {
        int[] input = {10, 20, 5, 30, 6, 25};
        System.out.println("The minimum number of operations required for matrix multiplication are " +
                matrixChainMultiplicationRecursive(input, 0, input.length - 1));

        System.out.println("The minimum number of operations required for matrix multiplication are using DP " +
                matrixChainMultiplicationDP(input));
    }
}
