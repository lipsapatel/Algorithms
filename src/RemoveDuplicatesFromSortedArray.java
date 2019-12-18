/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory
 *
 * Example:
 *
 * nums = {1, 1, 2}
 * Output = {1, 2, .}
 * length = 2
 *
 * nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}
 * Output = {0, 1, 2, 3, 4, ., ., ., ., .}
 * length = 5
 *
 * It doesn't matter what you leave beyond the returned length
 *
 * Clarification:

 Confused why the returned value is an integer but your answer is an array?

 Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

 Internally you can think of this:

 // nums is passed in by reference. (i.e., without making a copy)
 int len = removeDuplicates(nums);

 // any modification to nums in your function would be known by the caller.
 // using the length returned by your function, it prints the first len elements.
 for (int i = 0; i < len; i++) {
 print(nums[i]);
 }

 Approach

 1) Since the array is already sorted, we can keep two pointers i and j, where i
 is the slow-runner while j is the fast-runner. As long as nums[i] = nums[j], we increment j to skip the duplicate.
2) When we encounter nums[j] != nums[i], the duplicate run has ended so we must copy its value to nums[i + 1].
 3) i is then incremented and we repeat the same process again until j reaches the end of array.

 Time Complexity: O(n)
 Space Complexity: O(1)
 */
public class RemoveDuplicatesFromSortedArray {

    private static int removeDuplicates(int[] nums) {

        //Base Case
        if (nums.length == 0) {
            return 0;
        }

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int length = removeDuplicates(nums);
        System.out.println("The length of array is " + length);
        for (int i = 0; i < length; i++) {
            System.out.print(" " + nums[i]);
        }
    }
}
