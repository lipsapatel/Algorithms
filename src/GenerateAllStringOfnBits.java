import java.util.Arrays;

/**
 * Generate all strings of n bits.
 *
 * For Example:
 * n = 3
 *
 * Output:
 * [0, 0, 0]
 * [0, 1, 1]
 * [0, 1, 0]
 * [0, 0, 1]
 * [1, 0, 0]
 * [1, 1, 0]
 * [1, 0, 1]
 * [1, 1, 1]
 *
 * Time complexity: O(2^n)
 *
 * Approach
 *
 * 1) Recursion is the key here
 * 2) Create integer array of size n
 * 3) Every bit can take 2 values, either 0 or 1
 * 4) Starting from the end of the string, set bit to 0 then make recursive call
 * array[n - 1] = 0; generateAllStringsOfnBits(n - 1);
 * 5) set bit to 1 then make recursive call
 * array[n - 1] = 1; generateAllStringsOfnBits(n - 1);
 */
public class GenerateAllStringOfnBits {

    private static int[] array;

    private static void generateAllStringsOfnBits(int n) {

        if (n <= 0) {
            System.out.println(Arrays.toString(array));
        } else {
            array[n - 1] = 0;
            generateAllStringsOfnBits(n - 1);
            array[n - 1] = 1;
            generateAllStringsOfnBits(n - 1);
        }
    }

    public static void main(String[] args) {

        array = new int[3];
        generateAllStringsOfnBits(3);
    }
}
