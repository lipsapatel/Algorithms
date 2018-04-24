/**
 * Find missing number from a sequence of consecutive numbers.
 *
 * Input:
 * int[] array;
 * int range;
 *
 * Output:
 * int missingNumber;
 *
 * int[] array = {1, 2, 3, 4, 6, 7};
 * int range = 7;
 *
 * Output: missingNumber = 5;
 *
 * In our earlier approach, we have seen a method where we calculated sum of numbers,
 * but this approach might fail when number goes beyond the integer range.
 *
 * XOR will be the better solution in that case.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * 1) Do the XOR from 1 to n = A
 * 2) Do the XOR of given array = B
 * 3) A ^ B will give the missing number
 */
public class FindMissingNumberFromConsecutiveNumber_XOR {

    private static int findMissingNumber(int[] array, int range) {

        int A = 0;
        int B = 0;

        for (int i = 1; i <= range; i++) {
            A = A ^ i;
        }

        for (int i = 0; i < array.length; i++) {
            B = B ^ array[i];
        }

        return A ^ B;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 6, 7};
        int range = 7;

        System.out.println("The missing number is " + findMissingNumber(array, range));
    }
}
