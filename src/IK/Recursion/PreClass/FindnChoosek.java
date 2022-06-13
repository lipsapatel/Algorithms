package IK.Recursion.PreClass;

/**
 * Approach
 *
 * 1) nCk = n! / (n - k)! * k!
 * 2) Cannot use multiplication and factorial
 * 3) We can use Pascal triangle.
 * 4) In pascal triangle, the value of any cell is sum of previous row (i - 1, j) + (i - 1, j - 1)
 * 5) The n row and kth column in pascal triangle represents nCk
 * 6) In pascal triangle k goes from 0 to n
 * 7) There are two ways
 *    1) Don't include nth element so this makes recursive call (n - 1, k)
 *    2) Include nth element so this makes recursive call (n - 1, k - 1)
 * 8) When we include nth element we have n - 1 left to choose from and we have only k - 1 elements left to choose
 * 9) The base case is when k == 0 or k == n, return 1 because there is only one possible way
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */
public class FindnChoosek {

    private static int nChoosek(int n, int k) {
        //Base Case
        if(k == 0 || k == n) {
            return 1;
        } else {
            return nChoosek(n - 1, k) + nChoosek(n - 1, k - 1); //Exclude + include
        }
    }

    public static void main(String[] args) {
        System.out.println("12 choose 4 is " + nChoosek(12, 4));
    }
}
