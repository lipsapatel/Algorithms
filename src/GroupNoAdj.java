/**
 * Given an array of ints, is it possible to choose a group of some of the ints, such that
 * the group sums to the given target with this additional constraint:
 * If a value in the array is chosen to be in the group, the value immediately following it in the array must not be chosen. (No loops needed.)


 groupNoAdj(0, [2, 5, 10, 4], 12) → true
 groupNoAdj(0, [2, 5, 10, 4], 14) → false
 groupNoAdj(0, [2, 5, 10, 4], 7) → false
 */
public class GroupNoAdj {

    private static boolean groupNoAdj(int start, int[] nums, int target) {
        //Base case
        if (start > nums.length - 1) {
            return target == 0;
        }

        //Include, after skipping one
        if (groupNoAdj(start + 2, nums, target - nums[start])) {
            return true;
        }

        //Exclude
        if (groupNoAdj(start + 1, nums, target)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {2, 5, 10, 4};

        System.out.println("Group sum which include adjacent no for {2, 5, 10, 4}/12: " + groupNoAdj(0, array, 12));
        System.out.println("Group sum which include adjacent no for {2, 5, 10, 4}/14: " + groupNoAdj(0, array, 14));
        System.out.println("Group sum which include adjacent no for {2, 5, 10, 4}/7: " + groupNoAdj(0, array, 7));
    }
}
