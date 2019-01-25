/**
 * Given an array of ints, compute recursively the number of times that the value 11 appears in the array.
 * We'll use the convention of considering only the part of the array that begins at the given index.
 * In this way, a recursive call can pass index+1 to move down the array. The initial call will pass in index as 0.


 array11([1, 2, 11], 0) → 1
 array11([11, 11], 0) → 2
 array11([1, 2, 3, 4], 0) → 0
 */
public class Array11 {

    private static int array11(int[] nums, int index) {
        return array11Helper(nums, index, 0);
    }

    private static int array11Helper(int[] nums, int index, int result) {
        //Base Case
        if(index == nums.length) {
            return result;
        } else { //Recursive Case
            if (nums[index] == 11) {
                return array11Helper(nums, index + 1, result + 1);
            } else {
                return array11Helper(nums, index + 1, result);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 11};
        System.out.println("Number of 11 in array {1, 2, 11}: " + array11(nums, 0));

        System.out.println("Number of 11 in array {11, 11, 0}: " + array11(new int[]{11, 11, 0}, 0));
        System.out.println("Number of 11 in array {1, 2, 3, 4}: " + array11(new int[]{1, 2, 3, 4}, 0));
    }
}
