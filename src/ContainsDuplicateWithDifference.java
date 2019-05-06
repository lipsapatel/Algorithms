import java.util.HashMap;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that
 * nums[i] = nums[j] and the absolute difference between i and j is at most k.

 Example 1:

 Input: nums = [1,2,3,1], k = 3
 Output: true
 Example 2:

 Input: nums = [1,0,1,1], k = 1
 Output: true
 Example 3:

 Input: nums = [1,2,3,1,2,3], k = 2
 Output: false

 TC = O(n)
 SC = O(n)
 */
public class ContainsDuplicateWithDifference {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int x = 0; x < nums.length; x++) {

            if (map.containsKey(nums[x])) {

                int i = map.get(nums[x]);

                if (Math.abs(i - x) <= k) {
                    return true;
                } else {
                    map.put(nums[x], x);
                }
            } else {
                map.put(nums[x], x);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;

        System.out.println("The array contains duplicates with k difference: " + containsNearbyDuplicate(nums, k));

        nums = new int[]{1, 0, 1, 1};
        k = 1;

        System.out.println("The array contains duplicates with k difference: " + containsNearbyDuplicate(nums, k));

        nums = new int[]{99, 99};
        k = 2;

        System.out.println("The array contains duplicates with k difference: " + containsNearbyDuplicate(nums, k));

    }
}
