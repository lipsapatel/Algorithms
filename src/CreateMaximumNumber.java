import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two.
 * The relative order of the digits from the same array must be preserved.
 * Return an array of the k digits.
 *
 * Note: You should try to optimize your time and space complexity.
 *
 * Example 1:
 *
 * Input:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * Output:
 * [9, 8, 6, 5, 3]
 *
 * Example 2:
 *
 * Input:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * Output:
 * [6, 7, 6, 0, 4]
 *
 * Example 3:
 *
 * Input:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * Output:
 * [9, 8, 9]
 *
 * Approach
 *
 * 1) First we divide k into two parts - i and k - i - O(k)
 * 2) Find the maximum number of length i in nums1 by building monotonic decreasing
 *    sequence. - O(n)
 * 3) Find maximum number of length k - i in nums2 by building monotonic decreasing
 *    sequence. - O(m)
 * 4) Merge both results using merge sort O(k)
 * 5) Keep the track of maximum number.
 *
 * Time Complexity = O(k (m + n + k))
 * Space Complexity = O(k)
 *
 * resources/CreateMaximumNum1.jpg
 * resources/CreateMaximumNum2.jpg
 * resources/CreateMaximumNum3.jpg
 */
public class CreateMaximumNumber {

    private static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;

        int[] maxNumber = new int[k];

        //Divide into two parts i and k - i
        for(int i = Math.max(0, k - m); i <= k && i <= n; i++) {
            int[] number = merge(createMaxNumber(nums1, i),
                                 createMaxNumber(nums2, k - i), k);

            if(greater(number, 0, maxNumber, 0)) {
                maxNumber = number;
            }
        }
        return maxNumber;
    }

    private static int[] createMaxNumber(int[] nums, int k) {
        int n = nums.length;

        Stack<Integer> stack = new Stack<>();
        int[] result = new int[k];

        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && stack.peek() < nums[i] &&
                    (n - i) + stack.size() > k) {
                stack.pop();
            }
            if(stack.size() < k) {
                stack.push(nums[i]);
            }
        }

        int i = 0;
        for(int element: stack) {
            result[i] = element;
            i++;
        }
        return result;
    }

    private static int[] merge(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];

        int i = 0;
        int j = 0;
        int x = 0;

        while(x < k) {
            if(greater(nums1, i, nums2, j)) {
                result[x] = nums1[i];
                i++;
            } else {
                result[x] = nums2[j];
                j++;
            }
            x++;
        }
        return result;
    }

    //When two digits are equal, we continue to look for next digit
    //until they are not equal
    private static boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k = 5;

        System.out.println("The maximum number is " + Arrays.toString(maxNumber(nums1, nums2, k)));

        int[] nums11 = {6, 7};
        int[] nums21 = {6, 0, 4};
        k = 5;

        System.out.println("The maximum number is " + Arrays.toString(maxNumber(nums11, nums21, k)));

        int[] nums12 = {3, 9};
        int[] nums22 = {8, 9};
        k = 3;
        System.out.println("The maximum number is " + Arrays.toString(maxNumber(nums12, nums22, k)));

        int[] nums13 = {8, 9};
        int[] nums23 = {3, 9};
        k = 3;
        System.out.println("The maximum number is " + Arrays.toString(maxNumber(nums13, nums23, k)));

        int[] nums14 = {2,5,6,4,4,0};
        int[] nums24 = {7,3,8,0,6,5,7,6,2};
        k = 15;
        System.out.println("The maximum number is " + Arrays.toString(maxNumber(nums14, nums24, k)));
    }
}
