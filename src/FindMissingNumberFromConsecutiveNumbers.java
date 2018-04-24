/**
 * Write an algorithm to find a missing number from a sequence of consecutive numbers.
 *
 * Input: int[] array, int range
 * Output: int missingNumber
 *
 * Approach is simple:
 * 1) Add all numbers = Sum
 * 2) Calculate the sum of N numbers NSum = N(N+1)/2
 * 3) Find missing number = NSum - Sum;
 *
 * Example:
 * int[] array = {1, 2, 3, 4, 5, 6, 8, 9, 10};
 * int range = 10;
 *
 * Sum = 48;
 * nSum = 10(10+1)/2 = 55;
 * missingNumber = 55 - 48 = 7;
 *
 * Time Complexity: O(n)
 */
public class FindMissingNumberFromConsecutiveNumbers {

    private static int findMissingNumber(int[] array, int size) {

        int nSum = size * (size + 1)/2;
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        return nSum - sum;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 7, 8, 9, 10};
        System.out.println("Missing number is: " + findMissingNumber(array, 10));
    }
}
