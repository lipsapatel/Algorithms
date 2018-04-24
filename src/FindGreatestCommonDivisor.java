/**
 * Euclidean Algorithm - Greatest Common Divisor (GCD)
 *
 * Largest positive integer that divides the numbers without a remainder.
 *
 * For example, the GCD of 10 and 15 is 5.
 */
public class FindGreatestCommonDivisor {

    private static int findGreatestCommonDivisor(int n1, int n2) {

        if (n2 == 0) {
            return n1;
        }

        return findGreatestCommonDivisor(n2, n1 % n2);
    }

    public static void main(String[] args) {
        System.out.println("The GCD of 156 and 282 is " + findGreatestCommonDivisor(156, 282));
    }
}
