/**
 * In an array, find the contiguous subarray with sum to a given value
 *
 * Given an array and integer, find the subarray whose sum is equal to given integer.
 *
 * int[] array = {25, 12, 14, 22, 19, 15, 10, 23};
 * Integer = 55;
 *
 * Output: 55 is found between indexes 2 and 4
 * And elements are: 14, 22, 19
 *
 * Approach:
 *
 * Naive Approach: Use two loops. Time Complexity - O(n2)
 *
 * Better Approach
 *
 * 1) Keep adding all the elements to currentSum
 * 2) If currentSum > sum then start reducing currentSum from the beginning of the array
 * using start.
 * 3) If currentSum == sum, return
 */
public class FindContiguousSubarrayWithSumToGivenValue {

    private static void findContiguousSubarrayWithSumToGivenValue(int[] array, int sum) {

        int currentSum = 0;
        int start = 0;

        //Iterate the arrray
        for (int i = 0; i <= array.length; i++) {

            while(currentSum > sum) {

                currentSum = currentSum - array[start++];
            }

            if (currentSum == sum) {
                System.out.println("Sum found between the indexes " + start + " and " + (i -1));

                System.out.println("Elements are:");

                for (int j = start; j <= i - 1; j++) {
                    System.out.print(" " + array[j]);
                }
                return;
            }

            if (currentSum < sum) {
                currentSum = currentSum + array[i];
            }
        }

        System.out.println("No subarray is found with sum equals to " + sum);
    }

    public static void main(String[] args) {

        int[] arrA = { 25, 12, 14, 22, 19, 15, 10, 23 };
        int sum = 55;

        findContiguousSubarrayWithSumToGivenValue(arrA, sum);
    }
}
