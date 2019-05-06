/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:

 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.
 Example 2:

 Input: [2,7,9,3,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.

 Recursive Solution time complexity = 2^n
 Dynamic Programming Solution: Time Complexity: O(n)
 Space Complexity: O(n)

 Remove the need for extra space: TC = O(n)
 SC = O(1)
 */
public class HouseRobber {

    public static int rob(int[] nums, int n) {

        //Base Case
        if (n < 0) {
            return 0;
        } else { //Recursive case

            //Included or not included
            return Math.max(nums[n] + rob(nums, n - 2), rob(nums, n - 1));
        }
    }

    public static int robDP(int[] nums, int n) {

        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return nums[0];
        }

        int[] maxValue = new int[n + 1];

        maxValue[0] = nums[0];
        maxValue[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i <= n; i++) {
            maxValue[i] = Math.max(nums[i] + maxValue[i - 2], maxValue[i - 1]);
        }
        return maxValue[n];
    }

    public static int robNoExtraSpace(int[] nums, int n) {
        if (n < 0) {
            return 0;
        }

        int value1 = nums[0];
        if (n == 0) {
            return value1;
        }

        int value2 = Math.max(nums[0], nums[1]);
        if (n == 1) {
            return value2;
        }

        int maxValue = 0;

        for (int i = 2; i <= n; i++) {
            maxValue = Math.max(nums[i] + value1, value2);
            value1 = value2;
            value2 = maxValue;
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};

        System.out.println("Max amount: " + rob(nums, nums.length - 1));
        System.out.println("Max amount: " + robDP(nums, nums.length - 1));
        System.out.println("Max amount: " + robNoExtraSpace(nums, nums.length - 1));
    }
}
