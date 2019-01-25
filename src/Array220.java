/**
 * Given an array of ints, compute recursively if the array contains somewhere a
 * value followed in the array by that value times 10.
 * We'll use the convention of considering only the part of the array that begins at the given index.
 * In this way, a recursive call can pass index+1 to move down the array. The initial call will pass in index as 0.


 array220([1, 2, 20], 0) → true
 array220([3, 30], 0) → true
 array220([3], 0) → false
 */
public class Array220 {

    private static boolean array220(int[] nums, int index) {
        //Base Case
        if (nums.length == 0 || index == nums.length - 1) {
            return false;
        } else { //Recursive Case
            if (nums[index + 1] == 10 * nums[index]) {
                return true;
            } else {
                return array220(nums, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Array {1, 2, 20} contains value followed in the array by that value 10 times: " +array220(new int[]{1, 2, 20}, 0));
        System.out.println("Array {3, 30} contains value followed in the array by that value 10 times: " +array220(new int[]{3, 30}, 0));
        System.out.println("Array {3} contains value followed in the array by that value 10 times: " +array220(new int[]{3}, 0));
        System.out.println("Array {} contains value followed in the array by that value 10 times: " +array220(new int[]{}, 0));
    }


}
