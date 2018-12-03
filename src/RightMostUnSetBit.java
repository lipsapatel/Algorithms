/**
 * Find the right most unset bit or zero bit of a number
 *
 * Given a number, write an algorithm to find the right most unset bit or zero bit in it (binary representation)
 *
 * Example:
 *
 * Number = 11
 * Binary representation: 1011
 * Position of right most unset bit = 2
 *
 * Number = 6
 * Binary representation: 0110
 * Position of right most unset bit = 0
 *
 * Number: 13
 * Binary representation: 1101
 * Position of right most unset bit = 1
 *
 * Approach:
 *
 * If N is a number, then the expression below will give you the right most unset bit.
 * ~n & (n + 1)
 *
 * 1) We know that n & ~n = 0
 * 2) If we add 1 to the number, it will make the most unset bit to 1, and if there are any set bits
 * in the right side of unset bit those bit will become zero
 * 3) Example 1011 + 0001 = 1100
 * 4) So if we negate the original number it will make the right most unset bit to 1
 * 5) Now !n & (n + 1) will make all the bits to 0 but the right most unset bit of a number.
 *
 * Example
 *
 * n = 11
 * so n = 1011
 * n + 1 = 0101
 * ~n = 0100
 * ~n & (n + 1) = 0100 2nd bit assuming right most index is 0
 */
public class RightMostUnSetBit {

    private static double getRightMostUnsetBit(int n) {
        if (n == 1) {
            return -1;
        }
        return Math.log(~n & (n + 1))/Math.log(2);
    }

    public static void main(String[] args) {
        int n = 11;
        System.out.println("Right most unset bit: " + getRightMostUnsetBit(n));
    }
}
