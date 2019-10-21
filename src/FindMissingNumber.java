/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 Example 1:

 Input: [3,0,1]
 Output: 2
 Example 2:

 Input: [9,6,4,2,3,5,7,0,1]
 Output: 8
 Note:
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

 Approach

 1) From the length of array, use formula n*(n + 1)/2 to determine the sum
 2) Scan the array and determine the current sum
 3) Subtract current sum from sum which will give the missing number

 Time Complexity: O(n)
 Space Complexity: O(1)
 */
public class FindMissingNumber {

    public static int missingNumber(int[] nums) {

        int n = nums.length;

        int sum = n * (n + 1)/2;

        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += nums[i];
        }

        return sum - currentSum;
    }

    public static void main(String[] args) {
        int[] nums = {9,6,4,2,3,5,7,0,1};

        System.out.println("Missing number is: " + missingNumber(nums));
    }
}
