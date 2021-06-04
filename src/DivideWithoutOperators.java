/**
 * 2^0	=	1	= 1000^0	(0% deviation)
 * 2^10	=	1 024	≈ 1000^1	(2.4% deviation)
 * 2^20	=	1 048 576	≈ 1000^2	(4.9% deviation)
 * 2^30	=	1 073 741 824	≈ 1000^3	(7.4% deviation)
 * 2^40	=	1 099 511 627 776	≈ 1000^4	(10.0% deviation)
 * 2^50	=	1 125 899 906 842 624	≈ 1000^5	(12.6% deviation)
 * 2^60	=	1 152 921 504 606 846 976	≈ 1000^6	(15.3% deviation)
 *
 * Divide two integers without using division, multiplication or mod operator.
 * If it is overflow, return MAX_INT
 *
 * resources/DivideWithoutOperators1.jpg
 * resources/DivideWithoutOperators2.jpg
 * resources/DivideWithoutOperators3.jpg
 * resources/DivideWithoutOperators4.jpg
 * resources/DivideWithoutOperators5.jpg
 *
 * Input: 5, 2
 * Output: 2
 *
 * 5/2 = 2
 *
 * a/b where b != 0
 *
 * Cannot use / operator
 * Cannot use * operator
 * Cannot use % operator
 *
 * Few alternatives of multiplication and division
 *
 * 1) To make number negative:
 *    Don't a = a * -1;
 *    Do a = -a;
 *
 * 2) Divide by 2
 *    Don't a/2
 *    Do a >> 1 right shift operator
 *
 * 3) Doubling or multiply by 2
 *    Don't a * 2
 *    Do a = a + a
 *       a += a
 *       a << 1
 *
 * Integer Range = -2^31 to 2 ^ 31 - 1
 *
 * If a = -2 ^ 31 and b = -1
 * then a/b = 2 ^ 31 overflow
 * so in this case return 2 ^ 31 - 1
 *
 * Approach 1 Brute Force Approach Repeated Subtraction
 *
 * 1) If dividend = -2 ^ 31, then Math.abs(dividend) will still give -2 ^ 31
 *    because of overflow.
 * 2) The problems are occuring because there are more negative signed
 * 32 or 64 bit integers than there are positives.
 * 3) The best solution is to work with negative instead of positive
 * numbers.
 *
 * 4) Special case of overflow = -2 ^ 31/-1 = 2 ^ 31 - 1
 * 5) Convert both dividend and divisor to -ve.
 * 6) Append appropriate sign to the quotient.
 * 7) We converted both to negative instead of positive because
 * the range of valid negative number is bigger and therefore overflow
 * can be cleanly avoided.
 *
 *  7/2 = -7/-2
 *   -7 - (-2) = -5
 *   -5 - (-2) = -3
 *   -3 - (-2) = -1
 *   -1 - (-2) = 1
 *
 *   Time Complexity: O(a)
 *   Consider worst case when divisor = 1
 *   Space Complexity: O(1)
 *
 *   Approach 2: Repeated Exponential Search
 *
 *   1) Linear search is too slow because at each step we subtract
 *   only one copy of divisor from dividend.
 *   2) A better way would be to try subtract multiple copies of divisor.
 *   Doubling the divisor until it no longer fit the dividend.
 *
 *   100/3 = 33
 *
 *   divisor     how many copies of divisor
 *   3           2^0
 *   6           2^1
 *   12          2^2
 *   24          2^3
 *   48          2^4
 *   96          2^5
 *   192         # Too big
 *
 *   We can fit 96 in 100.
 *
 *   How many copies of 3? 2^5 * 3 = 96
 *
 *   Left over = 100 - 96 = 4.
 *
 *   Repeat the same process
 *
 *   32 + 1 = 33 3's
 *   Left Shift - doubling
 *   Right Shift - divide by 2
 *
 *   resources/DivideWithoutOperators6.jpg
 *   resources/DivideWithoutOperators7.jpg
 *
 *   Time Complexity: O(log^2 n) = O(logn * logn)
 *
 *   We started by performing an exponential search to find the
 *   biggest number that fits into the current dividend.
 *   This search took O(logn) operations.
 *
 * After doing this search, we updated the dividend by subtracting the
 * number we found. In the worst case, we were left with a dividend
 * slightly less than half of the previous dividend (if it was more
 * than half, then we couldn't have found the maximum number that fit in
 * by doubling!).
 *
 * So how many of these searches did we need to do?
 * Well, with the dividend at least halving after each one,
 * there couldn't have been more than O(logn) of them.
 *
 * So combined together, in the worst case,
 * we have O(logn) searches with each search taking O(logn) time.
 * This gives us O((logn)⋅(logn))=O(log^2 n) as our total time complexity.
 *
 *   Space Complexity: O(1)
 */
public class DivideWithoutOperators {

    public static int divideBruteForce(int dividend, int divisor) {
        //Special case of overflow
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int quotient = 0;
        int ndividend = dividend;
        int ndivisor = divisor;

        if(dividend > 0) {
            ndividend = -dividend;
        }

        if(divisor > 0) {
            ndivisor = -divisor;
        }

        while(ndividend - ndivisor <= 0) {
            quotient++;
            ndividend = ndividend - ndivisor;
        }

        if((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) {
            return quotient;
        } else {
            return -quotient;
        }
    }

    public static int divideDoublingDivisor(int a, int b) {

        //Special Case: overflow
        if(a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }

        //Base Case
        if(b == 0) {
            return Integer.MAX_VALUE;
        }

        int dividend = a;
        int divisor = b;

        if(a > 0) {
            dividend = -dividend;
        }

        if(b > 0) {
            divisor = -b;
        }

        //Time Complexity: O(log^2 n)
        int quotient = 0;
        while(dividend <= divisor) {
            int power = 1; //2^0
            int leftShift = 0; //This keeps doubling by incrementing

            /**
             * 100/3
             * 3 -- power(The count of 3) = 2 ^ 0; leftShift = 0
             * 6 -- power = 2 ^ 1; leftShift = 1
             * 12 -- power = 2^2; leftShift = 2
             * 24 -- power = 2^3; leftShift = 3
             * 48 -- power = 2^4; leftShift = 4
             * 96 -- power = 2^5; leftShift = 5
             */

            while(dividend - (divisor << (leftShift + 1)) <= 0) {
                power = power + power;
                leftShift++;
            }
            dividend = dividend - (divisor << leftShift);
            quotient = quotient + power;
        }

        if((a < 0 && b < 0) || (a > 0 && b > 0)) {
            return quotient;
        } else {
            return -quotient;
        }
    }

    public static void main(String[] args) {

        System.out.println(divideDoublingDivisor(-2147483648, 3));
        System.out.println(divideDoublingDivisor(-2147483648, -1));
        System.out.println(divideDoublingDivisor(12,0));
        System.out.println(divideDoublingDivisor(12,2));

        System.out.println(divideBruteForce(-2147483648, -1));
        System.out.println(divideBruteForce(7, 2));
        System.out.println(divideBruteForce(-6, 2));
        System.out.println(divideBruteForce(100, 3));

        System.out.println(divideDoublingDivisor(-2147483648, -1));
        System.out.println(divideDoublingDivisor(7, 2));
        System.out.println(divideDoublingDivisor(-6, 2));
        System.out.println(divideDoublingDivisor(100, 3));
    }
}
