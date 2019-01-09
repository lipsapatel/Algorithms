/**
 * Write an algorithm to calculate power(k, n)
 * K^n
 *
 * This handles both the case where n is positive and negative.
 *
 * For example
 * k = 4 and n = 5
 * 4 ^5 = 4*4*4*4*4 = 1024
 *
 * k = 2 and n = 3
 * 2^3 = 2*2*2 = 8
 *
 * If we have negative power
 * 2^-4 = 1/16 = 0.0625
 *
 * Use recursion
 * Divide the problem into sub problem with size n/2 and solve it recursively
 * If n is even, then keep multiplying halfResult
 * If n is odd, then multiply finalResult by k
 * If n is less than zero instead of multiplying with k divide with k, so it will be 1/4 for
 * first iteration then 1/8 x 1/8
 *
 * Time Complexity: O(logn)
 */
public class CalculatePower_Ologn {

    private static double calculatePower(int k, int n) {

        if (n==0) {
            return 1;
        }

        double halfResult = calculatePower(k, n/2);

        if (n%2 == 0) { //If n is even
            return halfResult * halfResult; //Second step 1/4 * 1/4 = 1/16
        } else if (n > 0) { //If n is odd
            return halfResult * halfResult * k;
        } else { //If n is negative
            return halfResult * halfResult/k; //1*1/4
        }
    }

    public static void main(String[] args) {
        System.out.println("4 power of -2 is " + calculatePower(4, -2));
        System.out.println("4 power of 3  is " + calculatePower(4, 3));
        System.out.println("4 power of 4 is " + calculatePower(4, 4));
    }
}
