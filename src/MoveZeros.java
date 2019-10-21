import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 Example:

 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]
 Note:

 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.

 Time Complexity: O(n)
 Space Complexity: O(1)

 Approach:

 1) Two pointers
 2) One pointer for last non zero element
 3) Second current pointer which keeps moving.
 4) All the elements before last non zero pointer are non-zeros
 5) All the elements between 2 pointers are 0
 6) All the elements after 2nd pointer is yet to be processed.
 */
public class MoveZeros {

    private static void moveZeros(int[] nums) {

        for (int lastNonZeroPointer = 0, current = 0; current < nums.length; current++) {

            //If the current element is non zero then swap it with the last nonZero pointer position and increment the pointer
            if (nums[current] != 0) {
                swap(nums, lastNonZeroPointer, current);
                lastNonZeroPointer++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 3, 0, 4, 5, 12, 0};

        moveZeros(nums);
    }
}
