/**
 * Given an integer n check whether it's a perfect square.
 *
 * Iterate numbers from 1 to n
 * Calculate x = i*i;
 * If x == n then n is the perfect square, else continue
 * If x > n then return false
 *
 * Time Complexity: O(sqrt(n))
 */
public class PerfectSquare {

    private static boolean isPerfectSquare(int n) {

        for (int i = 0; i <= n; i++) {
            int x = i * i;

            if (x == n) {
                return true;
            } else if (x > n) {
                return false;
            } else {
                continue;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int x = 1;
        int x1 = 36;
        int x2 = 48;

        System.out.println(x + " is a perfect square " + isPerfectSquare(x));
        System.out.println(x1 + " is a perfect square " + isPerfectSquare(x1));
        System.out.println(x2 + " is a perfect square " + isPerfectSquare(x2));
    }
}
