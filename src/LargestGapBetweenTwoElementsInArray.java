/**
 * Maximum Difference between any two elements in array - Largest Gap Problem
 *
 * Given an array of numbers, write an algorithm to find the maximum difference between any two elements
 * in the array.
 *
 * Example:
 * int[] array = {2, 8, 1, 6, 10, 4}
 * Output: Maximum Difference = 9 (elements are 1 and 10)
 *
 * int[] array = {10, 30, 5, 16, 19}
 * Output: Maximum Difference = 25 (elements are 30 and 5)
 *
 * Approach: Use Nested Loops
 *
 * Compare each element with all other elements in the array and calculate the difference and keep
 * track of maximum difference
 *
 * Time Complexity: O(n2)
 *
 * Better Approach: Track the maximum and minimum element
 *
 * Find the maximum and minimum element in the array and find the difference.
 * We need to keep track of maximum and minimum element and difference during the iteration
 *
 * 1) Iterate through all the elements in array
 * 2) For each element, check if the element is minimum element visited so far or maximum element visited so far,
 * else ignore the element
 * If current element is either minimum or maximum element in the step above then update the maximum element or minimum element accordingly
 * 3) Return the difference between maximum element and minimum element.
 *
 * Time Complexity: O(N)
 *
 *
 */
public class LargestGapBetweenTwoElementsInArray {

    private static void findLargestGapUsingNestedLoop(int[] array) {

        int maximumDifference = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {

                int x = array[i];
                int y = array[j];
                int difference = Math.abs(x - y);

                if (maximumDifference < difference) {
                    maximumDifference = difference;
                }
            }
        }

        System.out.println("Largest Gap between any two elements of array using Nested Loop is: " + maximumDifference);
    }

    private static void findLargestGap(int[] array) {

        if(array == null || array.length == 0) {

            System.out.println("Array is null or empty, no solution found");
        }

        int maxSoFar = array[0];
        int minSoFar = array[0];

        int maxDiff = 0;

        for (int i = 0; i < array.length; i++) {

            if (array[i] > maxSoFar) {
                maxSoFar = array[i];
            }

            if (array[i] < minSoFar) {
                minSoFar = array[i];
            }
        }

        maxDiff = maxSoFar - minSoFar;
        System.out.println("Largest Gap between two elements of array is: " + maxDiff);
    }

    public static void main(String[] args) {

        int[] array = {2, 8, 1, 6, 10, 4};
        findLargestGap(array);
        findLargestGapUsingNestedLoop(array);

        int[] array1 = {10, 30, 5, 16, 19};
        findLargestGap(array1);
        findLargestGapUsingNestedLoop(array1);
    }
}
