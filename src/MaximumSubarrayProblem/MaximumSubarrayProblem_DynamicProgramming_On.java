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
 *
 * Find the maximum sum at index i
 * MS(i) = Math.max(solution(i-1)+array[i], array[i]) = solution[i]
 *
 * Return the maximum value for solution array
 */
public class MaximumSubarrayProblem_DynamicProgramming_On {

    private static int findMaximumSubarray(int[] array) {
        int[] solution = new int[array.length];

        solution[0] = array[0];

        for (int i = 1; i < array.length; i++) {
            solution[i] = Math.max(solution[i-1] + array[i], array[i]);
        }

        int result = solution[0];

        for (int i = 1; i < solution.length; i++) {
            if (result < solution[i]) {
                result = solution[i];
            }
        }

        return result;
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
