/**
 * Find two non-repeating numbers in an array in O(n) time and O(1) space
 * Given an array of integers which has all the repeating numbers (twice) but two numbers which are
 * non repeating. Write an algorithm to find out those two numbers.
 *
 * Example:
 *
 * int[] array = {4, 5, 4, 5, 3, 2, 9, 3, 9, 8}
 * Output: 2 and 8
 *
 * Approaches:
 *
 * The problem is similar to "Find two repeating elements in an array".
 * There could be multiple solutions
 *
 * 1) Sort the array - O(nlogn)
 * 2) Use HashMap - Time Complexity: O(n) and Space Complexity: O(n)
 * 3) XOR - Time Complexity: O(n) and Space Complexity: O(1)
 *
 * 1) Let's say the two non-repeating numbers are x and y
 * 2) A XOR A = 0
 * 3) XOR all the elements of array. This will cancel all the repeated elements.
 * 4) Result will be x XOR y since X and Y are non repeating.
 *
 * 5) 1 XOR 1 = 0 and 1 XOR 0 = 1 with this logic if any kth bit is set in result implies either kth bit is 1 either in X
 * or Y but not both.
 * 6) Let's have this kth bit as right most set bit
 *
 * 7) Divide all the elements in array in two groups
 * Group 1 which has all the elements whose kth bit is 1- set
 * Group 2 which has all the elements whose kth bit is 0
 *
 * Group 1: XOR all the elements whose kth bit is 1 will produce either x or y
 * Group 2: XOR all the elments whose kth bit is 0 will produce either x or y
 *
 * resources/FindTwoNonRepeatingNumbers.png
 */
public class FindTwoNonRepeatingNumbers {

    private static void findTwoNonRepeatingNumbers(int[] a) {

        //XOR all the elements, this will have x XOR y in the result
        int xor = a[0];

        for (int i = 1; i < a.length; i++) {
            xor ^= a[i];
        }

        //Get the rightmost set bit
        int rightMostSetBit = xor & ~(xor - 1);

        //Divide array in two groups
        int x = 0;
        int y = 0;

        for (int i = 0; i < a.length; i++) {

            int temp = a[i];

            if ((temp & rightMostSetBit) != 0) {
                x ^= temp;
            } else {
                y ^= temp;
            }
        }

        System.out.println("Two Non Repeating Numbers are: " + x + " and " + y);
    }

    public static void main(String[] args) {

        int[] a = {4, 5, 4, 5, 3, 2, 9, 3, 9, 8};
        findTwoNonRepeatingNumbers(a);
    }
}
