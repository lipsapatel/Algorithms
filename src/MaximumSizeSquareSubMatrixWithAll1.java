/**
 * Dynamic Programming - Maximum Size Square sub matrix with all 1.
 * Given a matrix of 0's and 1's, find out maximum square sub-matrix with all 1.
 *
 * resources/MaximumSizeSquareSubmatrixWithAll1.png
 *
 * Best Case
 *
 * 1) If only one row is given, the cells with 1 is the maximum size square submatrix
 * with size 1
 * 2) If only one column is given, then the cells with 1 is the maximum size square submatrix with
 * size 1
 *
 * Bottoms-up
 *
 * 1) Create an auxiliary array of the same size as input array.
 * 2) We will fill the array with maximum size square sub matrix with all 1's with respect to that
 * particular cell.
 * 3) Once filled, scan it and find out the maximum entry - this will be the size of maximum square
 * sub matrix with all 1.
 *
 * Fill the auxiliary array
 *
 * 1) Copy the first row and first column from the given array into the auxiliary array.
 * 2) If particular cellValue = 0 in the given array, then enter it as 0 in auxiliary array.
 * 3) If particular cellValue = 1 in the given array, then minimum(left, top, diagonal) cell.
 *
 * resources/MaximumSizeSquareSubMatrixAuxiliaryArray.png
 */
public class MaximumSizeSquareSubMatrixWithAll1 {

    private static void maximumSizeSquareSubMatrix(int[][] givenArray, int rows, int cols) {

        int auxiliaryArray[][] = new int[rows][cols];

        //Fill the first row
        for (int i = 0; i < cols; i++) {
            auxiliaryArray[0][i] = givenArray[0][i];
        }

        //Fill first column
        for (int i = 0; i < rows; i++) {
            auxiliaryArray[i][0] = givenArray[i][0];
        }

        //Fill the rest of the matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {

                if (givenArray[i][j] == 0) {
                    auxiliaryArray[i][j] = 0;
                } else {
                    auxiliaryArray[i][j] = Math.min(auxiliaryArray[i - 1][j], Math.min(auxiliaryArray[i][j - 1],
                            auxiliaryArray[i - 1][j - 1])) + 1;
                }
            }
        }

        //Find the maximum size of square sub matrix
        int maxSize = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (auxiliaryArray[i][j] > maxSize) {
                    maxSize = auxiliaryArray[i][j];
                }
            }
        }

        System.out.println("The maximum size of square sub matrix with all 1's is "  + maxSize);
    }

    public static void main(String[] args) {
        int[][] arrA = { { 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0 },
                { 0, 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1 } };
        maximumSizeSquareSubMatrix(arrA, 5, 6);
    }
}
