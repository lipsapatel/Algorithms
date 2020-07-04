import java.util.Arrays;
import java.util.Stack;

/**
 * Given one array of length n, create maximum number of length k.
 * Build monotonic decreasing sequence using stack.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Approach
 *
 * 1) If the top of stack is less than number then pop.
 * 2) Pop only if the remaining number in array + stack size > k
 * 3) If that condition becomes false, then we can't pop.
 * 4) Push to stack only if stack size is less than k
 *
 * resources/CreateMaximumNumberOfLengthKGivenOneArray.jpg
 */
public class CreateMaximumNumberOfLengthKGivenOneArray {

    public static int[] maxNumber(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        int m = nums.length;

        for(int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty() && stack.peek() < nums[i] &&
                    (m - i) + stack.size() > k) {
                stack.pop();
            }

            if(stack.size() < k) {
                stack.push(nums[i]);
            }
        }

        int[] array = new int[k];
        int i = 0;

        for(int element: stack) {
            array[i] = element;
            i++;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] nums = {9, 1, 2, 5, 8, 3, 6};
        int k = 2;

        System.out.println("Maximum number " + Arrays.toString(maxNumber(nums, k)));

        int[] nums1 = {1, 2, 3, 4, 5};
        k = 3;

        System.out.println("Maximum number " + Arrays.toString(maxNumber(nums1, k)));

        int[] nums2 = {5, 4, 3, 2, 1};
        k = 3;

        System.out.println("Maximum number " + Arrays.toString(maxNumber(nums2, k)));
    }
}
