/**
 * Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums
 * to the given target with these additional constraints: all multiples of 5 in the array must be
 * included in the group. If the value immediately following a multiple of 5 is 1, it must not be chosen. (No loops needed.)


 groupSum5(0, [2, 5, 10, 4], 19) → true
 groupSum5(0, [2, 5, 10, 4], 17) → true
 groupSum5(0, [2, 5, 10, 4], 12) → false
 */
public class GroupSum5 {

    private static boolean groupSum5(int start, int[] nums, int target) {

        //Base Case
        if (start > nums.length - 1) {
            return target == 0;
        }

        //Include only if it's not number 1 followed by multiple of 5
        if (!(start > 0 && nums[start] == 1 && nums[start - 1] % 5 == 0)) {
            if (groupSum5(start + 1, nums, target - nums[start])) {
                return true;
            }
        }

        //Exclude only if it's not multiple of 5
        if (nums[start] % 5 != 0) {
            if (groupSum5(start + 1, nums, target)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {2, 5, 10, 4};
        int[] array1 = {2, 5, 4, 10};
        int[] array2 = {3, 5, 1};
        System.out.println("Group sum which includes all 5 for {2, 5, 10, 4}/19: " +groupSum5(0, array, 19));
        System.out.println("Group sum which includes all 5 for {2, 5, 10, 4}/17: " +groupSum5(0, array, 17));
        System.out.println("Group sum which includes all 5 for {2, 5, 10, 4}/12: " +groupSum5(0, array, 12));
        System.out.println("Group sum which includes all 5 for {2, 5, 4, 10}/12: " +groupSum5(0, array1, 12));
        System.out.println("Group sum which includes all 5 for {3, 5, 1}/5: " +groupSum5(0, array2, 5));
    }
}
