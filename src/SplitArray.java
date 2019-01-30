import java.util.ArrayList;

/**
 * Given an array of ints, is it possible to divide the ints into two groups,
 * so that the sums of the two groups are the same. Every int must be in one group or the other.
 * Write a recursive helper method that takes whatever arguments you like, and
 * make the initial call to your recursive helper from splitArray(). (No loops needed.)


 splitArray([2, 2]) → true
 splitArray([2, 3]) → false
 splitArray([5, 2, 3]) → true
 */
public class SplitArray {

    private static boolean splitArray(int[] nums) {

        //Empty Array
        if (nums.length == 0) {
            return true;
        }

        //If the sum of elements is odd, then return false
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }

        //If odd, return false
        if (sum % 2 != 0) {
            return false;
        }

        return splitArrayHelper(nums, sum/2, nums.length);
    }

    private static boolean splitArrayHelper(int[] nums, int sum, int n) {

        //Base Case
        if (sum == 0) {
            return true;
        }

        //If we are done with all, when n == 0 and still sum is not 0
        if (n == 0 && sum != 0) {
            return false;
        }

        //If last element is greater than sum, then exclude
        if (nums[n - 1] > sum) {
            return splitArrayHelper(nums, sum, n - 1);
        }

        //Include
        if (splitArrayHelper(nums, sum - nums[n - 1], n - 1)) {
            return true;
        }

        //Exclude
        if (splitArrayHelper(nums, sum, n - 1)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Sum of two groups are same for array {2, 2}: " + splitArray(new int[]{2,2}));
        System.out.println("Sum of two groups are same for array {2, 3}: " + splitArray(new int[]{2,3}));
        System.out.println("Sum of two groups are same for array {5, 2, 3}: " + splitArray(new int[]{5, 2, 3}));
        System.out.println("Sum of two groups are same for array {2, 2, 10, 10, 1, 1}: " + splitArray(new int[]{2, 2, 10, 10, 1, 1}));
    }
}
