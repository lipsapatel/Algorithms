/**
 * Given an array of ints, is it possible to choose a group of some of the ints,
 * beginning at the start index, such that the group sums to the given target?
 * However, with the additional constraint that all 6's must be chosen. (No loops needed.)


 groupSum6(0, [5, 6, 2], 8) → true
 groupSum6(0, [5, 6, 2], 9) → false
 groupSum6(0, [5, 6, 2], 7) → false
 */
public class GroupSum6 {

    private static boolean groupSum6(int start, int[] nums, int target) {

        //Base Case
        if (start > nums.length - 1) {
            return target == 0;
        }

        //Include number
        if (groupSum6(start + 1, nums, target - nums[start])) {
            return true;
        };

        //Exclude no only if it's not 6
        if (nums[start] != 6) {
            if (groupSum6(start + 1, nums, target)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] array = {5, 6, 2};

        System.out.println("Group sum which include 6 for {5, 6, 2}/8: " + groupSum6(0, array, 8));
        System.out.println("Group sum which include 6 for {5, 6, 2}/7: " + groupSum6(0, array, 7));
        System.out.println("Group sum which include 6 for {5, 6, 2}/9: " + groupSum6(0, array, 9));
    }
}
