package MaximumSubarrayProblem;

/**
 * Maximum subarray problem is the task of finding contiguous subarray
 * within one dimensional array of numbers which has the largest sum
 *
 * Example:
 * int[] array = {1, 2, -3, -4, 2, 7, -2, 3}
 * Output: Contiguous subarray with largest sum is 2, 7, -2, 3 = 10
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Find the max_ending_here sum
 * If you get something greater by adding max_ending_here + array[i]
 * then update that otherwise reset it to array[i]
 *
 * update max_sum with maximum of two - max_end_here and max_sum
 */
public class MaximumSubarrayProblem_KadanesAlgorithm_On {

    private static int findMaximumSubarray(int[] array) {

        int max_end_here = array[0];
        int max_sum = array[0];

        for (int i = 1; i < array.length; i++) {

            max_end_here = Math.max(array[i], max_end_here + array[i]);
            max_sum = Math.max(max_end_here, max_sum);
        }
        return max_sum;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, -3, -4, 2, 7, -2, 3};

        System.out.println("The maximum subarray sum is " +findMaximumSubarray(array) );

        int[] array1 = { -2, -3, -4, -2, -7, -2, -3, -11};

        System.out.println("The maximum subarray sum is " +findMaximumSubarray(array1));

        int[] array2 = {1, 2, -3};

        System.out.println("The maximum subarray sum is " +findMaximumSubarray(array2));
    }
}
