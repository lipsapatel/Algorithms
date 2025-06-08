package IK.Recursion.PreClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Pascal's Triangle
 *
 * Pascal’s triangle is a triangular array of the binomial coefficients.
 * Write a function that takes an integer value n as
 * input and returns a two-dimensional array representing pascal’s triangle.
 *
 * pascalTriangleArray is a two-dimensional array of size n*n, where
 * pascalTriangleArray[i][j] = BinomialCoefficient(i, j); if j<=i,
 * pascalTriangleArray[i][j] = 0; if j>i
 *
 * Cell of pascal triangle represents nCk which is (n - 1, k) (don't include n) + (n - 1, k - 1) (Include n)
 * Base Case: If k = 0 or k = n then return 1 because nC0 = 1 and nCn = 1
 *
 * Following are the first 6 rows of Pascal’s Triangle:
 *
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 * 1 5 10 10 5 1
 *
 * Example One
 * Input: 4
 *
 * Output:
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 *
 * Pascal's Triangle for given n=4:
 *
 * Using equation,
 * pascalTriangleArray[i][j] = BinomialCoefficient(i, j); if j<=i,
 * pascalTriangleArray[i][j] = 0; if j>i
 *
 * Generated pascal’s triangle will be:
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 *
 * Notes
 *
 * Input Parameters: There is only one argument n, denoting the number of lines
 * of Pascal's triangle to be considered.
 *
 * Output: Return a two-dimensional integer array result, denoting pascal’s
 * triangle where each value must be modulo with (10^9 + 7).
 * Size of result[i] for 0 <= i < n should be (i + 1)
 * i.e. 0s for pascalTriangleArray[i][j] = 0; if j>i, should be ignored.
 *
 * Constraints:
 *     1 <= n <= 1700
 *
 * Brute Force Approach
 *
 * 1) Calculate each row using binomial theorem.
 * 2) For each cell, calculate (nCk) = n!/k! * (n - k)!
 * 3) Time complexity for calculating each cell is O(n) using factorial
 * 4) There are total n^2 cells.
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(n^2)
 *
 * Optimal Solution
 *
 * 1) a[i][j] = a[i - 1][j - 1] + a[i - 1][j]
 *    a[i][0] = 1
 *    a[i][i] = 1
 *
 *    0 <= i < n
 *    0 <= j <= i
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2) - output space
 *
 * resources/PascalTriangle1.jpg
 * resources/PascalTriangle2.jpg
 * resources/PascalTriangle3.jpg
 * resources/PascalTriangle4.jpg
 */
public class PascalTriangleDP {

    private static List<List<Integer>> findPascalTriangle(int n) {
        int mod = 1000000007;

        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();

            for(int j = 0; j <= i; j++) {
                if(j == i || j == 0) {
                    row.add(1);
                } else {
                    row.add((result.get(i - 1).get(j - 1) + result.get(i - 1).get(j)) % mod);
                }
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findPascalTriangle(6);

        System.out.println(result.toString());

    }
}
