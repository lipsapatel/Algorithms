/**
 * Swap two numbers using Bitwise XOR Operator
 *
 * Given two numbers, swap both the numbers using XOR operators
 *
 * Example:
 *
 * x = 4; y = 8;
 * Output: x = 8; y = 4;
 *
 * Approach: XOR operator
 *
 * There are many ways to swap two numbers but here we will discuss a solution to swap numbers using XOR operator
 *
 * 1) Say numbers are x and y
 * 2) Do x = x XOR y, this will set the bits which are set either in x or in y. Store it in x.
 * 3) Do y = x XOR y, this will set the bits which are set in original x so this will store original value of x in y
 * 4) Do x = x XOR y, this will set the bits which are set in original y so this will store original value of y in x
 *
 * resources/SwapNumbersUsingBitwiseXOR.png
 *
 */
public class SwapNumbersUsingBitwiseXOR {

    private static void swapNumbersUsingBitwiseXOR(int x, int y) {

        System.out.println("Original x: " + x + ", y: " + y);

        x = x ^ y;
        y = x ^ y;
        x = x ^ y;

        System.out.println("After swapping:");
        System.out.println("x: " + x + ", y: " + y);
    }

    public static void main(String[] args) {
        int x = 4;
        int y = 8;

        swapNumbersUsingBitwiseXOR(x, y);
    }
}
