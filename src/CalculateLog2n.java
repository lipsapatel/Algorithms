/**
 * Calculate Log2n without using build-in function
 *
 * Given a number n, write a program to calculate Log2 n without using
 * built-in function.
 *
 * Example:
 *
 * n = 32
 * Log2 32 = 5
 *
 * n = 64
 * Log2 64 = 6
 *
 * Approach:
 *
 * 1) Initialize result = 0;
 * 2) Keep dividing the given number by 2 until number is greater than 0
 * and add one to the result if n is greater than equal to 1.
 */
public class CalculateLog2n {

    private static int computeLogN(int n) {

        int result = 0;

        while (n > 0) {
            n = n/2;

            if (n >= 1) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int n = 64;
        System.out.println("Log " + n + " vale: " + computeLogN(n));

        n = 32;
        System.out.println("Log " + n + " vale: " + computeLogN(n));

        n = 8;
        System.out.println("Log " + n + " vale: " + computeLogN(n));
    }
}
