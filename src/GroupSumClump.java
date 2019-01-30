/**
 * Given an array of ints, is it possible to choose a group of some of the ints,
 * such that the group sums to the given target, with this additional constraint:
 * if there are numbers in the array that are adjacent and the identical value,
 * they must either all be chosen, or none of them chosen.
 * For example, with the array {1, 2, 2, 2, 5, 2}, either all three 2's in the middle must be chosen or not,
 * all as a group. (one loop can be used to find the extent of the identical values).


 groupSumClump(0, [2, 4, 8], 10) → true
 groupSumClump(0, [1, 2, 4, 8, 1], 14) → true
 groupSumClump(0, [2, 4, 4, 8], 14) → false
 */
public class GroupSumClump {

    private static boolean groupSumClump(int start, int[] nums, int target) {
        //Base Case
        if (start > nums.length - 1) {
            return target == 0;
        }

        //Include all
        int count = 1;
        int totalNum = nums[start];

        //Calculate all adjacent value
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                count++;
                totalNum = totalNum + nums[i];
            } else {
                break;
            }
        }

        if (groupSumClump(start + count, nums, target - totalNum)) {
            return true;
        }

        //Exclude all
        if (groupSumClump(start + count, nums, target)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("The array {2, 4, 8}/10 sums to target with adjacent and identical value included: " + groupSumClump(0, new int[]{2, 4, 8}, 10));
        System.out.println("The array {1, 2, 4, 8, 1}/14 sums to target with adjacent and identical value included: " + groupSumClump(0, new int[]{1, 2, 4, 8, 1}, 14));
        System.out.println("The array {2, 4, 4, 8}/14 sums to target with adjacent and identical value included: " + groupSumClump(0, new int[]{2, 4, 4, 8}, 14));

    }
}
