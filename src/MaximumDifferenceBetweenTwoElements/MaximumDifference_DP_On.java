package MaximumDifferenceBetweenTwoElements;

/**
 * Given an array A write an algorithm to find maximum difference between two elements
 * where larger element appears after smaller element
 * A[j] > A[i]
 * j > i
 * A[j] - A[i] is maximum
 *
 * int[] array = {2, 5, 1, 7, 3, 9, 5}
 * Output = 8
 *
 * int[] array = {22, 12, 2, 5, 19}
 * Output: 17
 *
 * Dynamic Programming
 * 1) Traverse the array from right to left
 * 2) Keep track of max_so_far
 * 3) if current element is greater than max_so_far update max_so_far
 * 4) if current element is less than max_so_far calculate max_diff = max(maxDiff,
 *      max_so_far - current element)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MaximumDifference_DP_On {

    private static int maximumDifference(int[] array) {

        int maximumDifference = -1;

        int max_so_far = array[array.length-1];

        for (int i = array.length - 2; i >= 0; i--) {

            if (array[i] > max_so_far) {
                max_so_far = array[i];
            } else { //if less than
                maximumDifference = Math.max(maximumDifference, max_so_far - array[i]);
            }
        }

        return maximumDifference;
    }

    public static void main(String[] args) {

        int[] array = {2, 5, 1, 7, 3, 9, 5};

        System.out.println("The maximum difference is " + maximumDifference(array));

        int[] array1 = {22, 12, 2, 5, 19};

        System.out.println("The maximum difference is " + maximumDifference(array1));

        int[] array2 = {22};

        System.out.println("The maximum difference is " + maximumDifference(array2));
    }
}
