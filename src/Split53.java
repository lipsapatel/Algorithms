/**
 * Given an array of ints, is it possible to divide the ints into two groups,
 * so that the sum of the two groups is the same, with these constraints:
 * all the values that are multiple of 5 must be in one group, and all the values that are a multiple of 3 (and not a multiple of 5)
 * must be in the other. (No loops needed.)


 split53([1, 1]) → true
 split53([1, 1, 1]) → false
 split53([2, 4, 2]) → true
 **/
public class Split53 {

    private static boolean split53(int[] nums) {
        return split53Helper(nums, 0, 0, 0);
    }

    private static boolean split53Helper(int[] nums, int start, int mul5, int mul3) {
        //Base Case
        if (start > nums.length - 1) {
            return mul5 == mul3;
        }

        //Include in 5
        if (nums[start] % 5 == 0) {
            return split53Helper(nums, start + 1, mul5 + nums[start], mul3);
        }

        //Include in 3
        if (nums[start] % 3 == 0) {
            return split53Helper(nums, start + 1, mul5, mul3 + nums[start]);
        }

        //Include in 5 if it's not multiple of 5 or 3
        if (split53Helper(nums, start + 1, mul5 + nums[start], mul3)) {
            return true;
        }

        //Exclude from 5 and include in 3
        if (split53Helper(nums, start + 1, mul5, mul3 + nums[start])) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Array {1, 1} contains two group of same size where mul5 is in one group and mul3 is in another group: " + split53(new int[] {1, 1}));
        System.out.println("Array {1, 1, 1} contains two group of same size where mul5 is in one group and mul3 is in another group: " + split53(new int[] {1, 1, 1}));
        System.out.println("Array {2, 4, 2} contains two group of same size where mul5 is in one group and mul3 is in another group: " + split53(new int[] {2, 4, 2}));
    }

}
