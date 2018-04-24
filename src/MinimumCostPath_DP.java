/**
 * Given 2D matrix where each cell has cost to travel. Write an algorithm to find path
 * from top left to bottom right with minimum travel cost.
 *
 * You can move only right or down
 *
 * resources/MinimumCostPathGivenMatrix.png
 *
 * Dynamic Programming:
 *
 * 1) Create solution matrix of same size as given matrix.
 * 2) We will do bottom-up approach
 * 3) Fill the first row
 * solution[0][i] = A[0][i] + solution[0][i - 1]
 * 4) Fill the first column
 * solution[i][0] = A[i][0] + solution[i - 1][0]
 * 5) Fill the rest of the matrix
 * solution[i][j] = A[i][j] + Math.min(solution[i - 1][j], solution[i][j - 1])
 * 6) return solution[A.length - 1][A.length - 1]
 *
 * resources/MinimumCostPathSolutionMatrix.png
 *
 * Time complexity: O(n2)
 */
public class MinimumCostPath_DP {

    private static int findMinimumCostPath_DP(int[][] A) {

        int[][] solution = new int[A.length][A.length];

        solution[0][0] = A[0][0];

        //Fill the first row
        for (int i = 1; i < A.length; i++) {
            solution[0][i] = A[0][i] + solution[0][i - 1];
        }

        //Fill the first column
        for (int i = 1; i < A.length; i++) {
            solution[i][0] = A[i][0] + solution[i - 1][0];
        }

        //Fill the rest of the matrix
        //Path will be either from left or top, choose the minimum
        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < A.length; j++) {

                solution[i][j] = A[i][j] + Math.min(solution[i - 1][j], solution[i][j - 1]);
            }
        }

        return solution[A.length - 1][A.length - 1];
    }

    public static void main(String[] args) {
        int[][] A = { { 1, 7, 9, 2 }, { 8, 6, 3, 2 }, { 1, 6, 7, 8 },
                { 2, 9, 8, 2 } };
        System.out.println("Minimum Cost Path " + findMinimumCostPath_DP(A));
    }
}
