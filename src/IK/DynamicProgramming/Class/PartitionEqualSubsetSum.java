package IK.DynamicProgramming.Class;

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 * Recursive Approach
 *
 * 1) Given array, find sum of array
 * 2) If sum is odd, return false because we cannot partition array
 * into equal sum subset
 * 3) If sum is even, find target = sum/2
 * 4) Now the problem becomes of finding subset sum equals to target
 * 5) Recursive function (nums, nums.length - 1, target)
 * 6) Base Case: If (target == 0) return true
 * 7) If index < 0 then return false
 * 8) If index == 0 && nums[index] == target then return true
 * 9) Recursive Case: Include = false if (target - nums[index] < 0)  else
 * 10) Include = f(nums, index - 1, target - nums[index])
 * 11) Exclude = f(nums, index - 1, target)
 * 12) Return include || exclude
 *
 * TC: O(2^n)
 * SC: O(n) - Recursive Call Stack space
 *
 * DP Approach
 *
 * 1) Create 2D boolean table of size n * T + 1
 * 2) Iterate row by row
 * 3) dp[i][0] = true
 * 4) dp[0][j] = true if (nums[0] == j) else false
 * 5) if(j - nums[i] >= 0) include = dp[i - 1][j - nums[i]]
 * 6) Exclude = dp[i - 1][j]
 * 7) If dp[i][target] == true then return true
 * 8) Return false
 *
 * TC: O(nT)
 * SC: O(nT)
 */
public class PartitionEqualSubsetSum {

    private static boolean canPartition(int[] nums) {

        int sum = findSum(nums);

        if(sum % 2 == 1) { //odd
            return false;
        }

        int target = sum/2;

        return findSubsetSum(nums, nums.length - 1, target);
    }

    private static boolean findSubsetSum(int[] nums, int index, int target) {

        //Base Case
        if(target == 0) {
            return true;
        }

        if(index < 0) {
            return false;
        }

        if(index == 0 && nums[index] == target) {
            return true;
        }

        //Recursive Case
        boolean include = false;
        if(target - nums[index] >= 0) {
            include = findSubsetSum(nums, index - 1, target - nums[index]);
        }

        boolean exclude = findSubsetSum(nums, index - 1, target);

        return include || exclude;
    }

    private static int findSum(int[] nums) {
        int sum = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        return sum;
    }

    private static boolean canPartitionDP(int[] nums) {

        int sum = findSum(nums);

        if(sum % 2 == 1) {
            return false;
        }

        int target = sum/2;

        return findSubsetSumDP(nums, target);
    }

    private static boolean findSubsetSumDP(int[] nums, int target) {

        int n = nums.length;
        int t = target + 1;

        boolean[][] dp = new boolean[n][t];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < t; j++) {

                //Target == 0
                if(j == 0) {
                    dp[i][j] = true;

                } else if(i == 0) {

                    if(nums[i] == target) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                } else {

                    boolean include = false;

                    if(j - nums[i] >= 0) {
                        include = dp[i - 1][j - nums[i]];
                    }

                    boolean exclude = dp[i - 1][j];

                    dp[i][j] = include || exclude;
                }

                if(dp[i][target] == true) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * On the fly space optimization
     *
     * 1) We need only 2 rows
     * 2) prevRow and currRow
     *
     * TC: O(NT)
     * SC: O(T)
     */
    private static boolean canPartitionSpaceOptimization(int[] nums) {

        int sum = findSum(nums);

        if(sum % 2 == 1) {
            return false;
        }

        int target = sum/2;

        return findSubsetSumDPSpaceOptimization(nums, target);
    }

    private static boolean findSubsetSumDPSpaceOptimization(int[] nums, int target) {
        int n = nums.length;
        int t = target + 1;

        boolean[] prevRow = new boolean[t];

        for(int i = 0; i < n; i++) {

            boolean[] currRow = new boolean[t];

            for(int j = 0; j < t; j++) {

                //Base Case
                if(j == 0) { //target == 0

                    currRow[j] = true;
                } else if(i == 0) {

                    if(nums[i] == j) { //nums[0] == target
                        currRow[j] = true;
                    } else {
                        currRow[j] = false;
                    }
                } else {

                    boolean include = false;

                    if(j - nums[i] >= 0) {
                        include = prevRow[j - nums[i]];
                    }

                    boolean exclude = prevRow[j];

                    currRow[j] = include || exclude;
                }

                if(currRow[target] == true) {
                    return true;
                }
            }
            prevRow = currRow;
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};

        System.out.println(canPartition(nums));
        System.out.println(canPartitionDP(nums));
        System.out.println(canPartitionSpaceOptimization(nums));

        int[] nums1 = {1, 2, 3, 5};
        System.out.println(canPartition(nums1));
        System.out.println(canPartitionDP(nums1));
        System.out.println(canPartitionSpaceOptimization(nums1));
    }
}
