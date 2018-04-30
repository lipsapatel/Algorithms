/**
 * You are given a matrix of size N*N. A sub-matrix of this matrix is considered
 * special if it satisfies the following conditions:
 *
 * 1) It must be square in shape.
 * 2) All the numbers in it must be prime.
 *
 * Now, you have to count the total number of special sub-matrices of the given matrix.
 *
 * Input:
 *
 * Integer N and matrix
 *
 * Output:
 * A single integer representing the number of magical sub-matrices.
 *
 * Sample Input:
 * 3
 * 2 3 8
 * 4 5 7
 * 2 7 5
 *
 * Output:
 * 8
 *
 * Explanation:
 *
 * There are 7 prime numbers in the given matrix (8 and 4 are not prime)
 * Count of 1X1 magical matrix: 7 (all 1X1 matrices with prime numbers)
 * 2X2: 1 (the bottom-right matrix, as it contains all prime numbers)
 *
 * So the final answer is (7 + 1 + 0) = 8
 */
public class MatrixFinder {

    public static void main(String[] args) {

        int[][] matrix = {{2,3,8},
                          {1,5,7},
                          {2,7,5}};
        int N = 3;

        System.out.println(countSquarePrimeSubMatrices(matrix, N));
    }

    private static int countSquarePrimeSubMatrices(int[][] matrix, int N) {

        //Count of total number of special matrix
        int count = 0;

        //Start from 0th row and column
        for (int row = 0; row < N; row++) {

            for (int col = 0; col < N; col++) {

                //Continue only if it's prime
                if (isPrime(matrix[row][col])) {

                    //The maxSize is maximum square matrix that can be formed from a given
                    //row and column
                    int maxSize = N - Math.max(row, col);

                    while (maxSize > 0) {

                        boolean found = true;

                        //Go till the maxSize from current position so row+maxSize
                        for (int rowStart = row; rowStart < maxSize + row; rowStart++) {

                            for (int colStart = col; colStart < maxSize + col; colStart++) {

                                if (!isPrime(matrix[rowStart][colStart])) {
                                    found = false;
                                }
                            }
                        }

                        maxSize--;

                        if (found) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    //checks whether an int is prime or not.
    //You have to go till the square root of n, because if you list all the factors
    //of n then square root will always be in the middle. (if it happens that square root
    //is not integer, we are still ok)
    private static boolean isPrime(int n) {

        //Number 2 is prime
        if (n == 2) {
            return true;
        }

        //check if n is a multiple of 2, then it's not prime
        if (n == 1 || n%2==0) {
            return false;
        }

        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }

}

