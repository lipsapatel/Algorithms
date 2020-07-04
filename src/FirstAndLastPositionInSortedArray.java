/**
 * Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in ascending order, find the
 * starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Approach: Modified Binary Search - 2 pass
 *
 * 1) We don't exit out when array[mid] == target, we also make sure it's the first key.
 * 2) We don't exit out when array[mid] == target, we also make sure it's the last key.
 * 3) The base condition differs in both binary search.
 * 4) For first binary search, to find the starting index, if target <= array[mid], go to left
 * 5) For last binary search, to find the ending index, if target >= array[mid], go to right.
 *
 * Time Complexity: O(logn)
 * Space Complexity: O(1)
 *
 * resources/FirstAndLastPositionInSortedArray1.jpg
 * resources/FirstAndLastPositionInSortedArray2.jpg
 */
public class FirstAndLastPositionInSortedArray {

    private static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = firstBinarySearch(nums, target, 0, nums.length - 1);
        result[1] = lastBinarySearch(nums, target, 0, nums.length - 1);
        return result;
    }

    private static int firstBinarySearch(int[] nums, int target, int low, int high) {
        if(high >= low) {
            int mid = low + (high - low)/2;

            //Base Case
            if((mid == 0 || nums[mid - 1] < target) && nums[mid] == target) {
                return mid;
            } else if(target > nums[mid]) { //Go right
                return firstBinarySearch(nums, target, mid + 1, high);
            } else {
                return firstBinarySearch(nums, target, low, mid - 1);
            }
        }
        return -1;
    }

    private static int lastBinarySearch(int[] nums, int target, int low, int high) {
        if(high >= low) {
            int mid = low + (high - low)/2;

            //Base Case
            if((mid == nums.length - 1 || nums[mid + 1] > target) && nums[mid] == target) {
                return mid;
            } else if(target < nums[mid]) { //Go left
                return lastBinarySearch(nums, target, low, mid - 1);
            } else {
                return lastBinarySearch(nums, target, mid + 1, high);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 5, 5, 5, 5, 6, 6, 67, 123, 125};
        int target = 5;

        int[] result = searchRange(nums, target);
        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }
}
