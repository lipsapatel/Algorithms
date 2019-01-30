/**
 *
 Given an array of ints, is it possible to divide the ints into two groups,
 so that the sum of one group is a multiple of 10, and the sum of the other group is odd.
 Every int must be in one group or the other. Write a recursive helper method that takes
 whatever arguments you like, and make the initial call to your recursive helper from splitOdd10(). (No loops needed.)


 splitOdd10([5, 5, 5]) → true
 splitOdd10([5, 5, 6]) → false
 splitOdd10([5, 5, 6, 1]) → true
 */
public class SplitOdd10 {

    private static boolean splitOdd10(int[] nums) {

        //Empty Array
        if (nums.length == 0) {
            return false;
        }

        //Calculate totalSum
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum = totalSum + nums[i];
        }

        if (totalSum % 2 == 0) {
            //Even
            return false;
        }

        int desired10Sum = totalSum - (totalSum % 10);

        return splitOdd10Helper(nums, nums.length, desired10Sum);
    }

    private static boolean splitOdd10Helper(int[] nums, int n, int sum) {

        //Base Case
        if (sum == 0) {
            return true;
        }

        //Done with all and still sum is not 0
        if ( n == 0 && sum != 0) {
            return false;
        }

        //If last element is greater than sum, exclude
        if (nums[n - 1] > sum) {
            return splitOdd10Helper(nums, n - 1, sum);
        }

        //Include
        if (splitOdd10Helper(nums, n - 1, sum - nums[n - 1])) {
            return true;
        }

        //Exclude
        if (splitOdd10Helper(nums, n - 1, sum)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Two groups one multiple of 10 and other odd for array {5, 5, 5}: " + splitOdd10(new int[]{5, 5, 5}));
        System.out.println("Two groups one multiple of 10 and other odd for array {5, 5, 6}: " + splitOdd10(new int[]{5, 5, 6}));
        System.out.println("Two groups one multiple of 10 and other odd for array {5, 5, 6, 1}: " + splitOdd10(new int[]{5, 5, 6, 1}));
    }
}
