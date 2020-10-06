import java.util.Arrays;
import java.util.HashSet;

/**
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.

 The function twoSum should return indices of the two numbers such that
 they add up to the target, where index1 must be less than index2.

 Note:

 Your returned answers (both index1 and index2) are not zero-based.
 You may assume that each input would have exactly one solution and
 you may not use the same element twice.
 Example:

 Input: numbers = [2,7,11,15], target = 9
 Output: [1,2]
 Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.

 Time Complexity: O(n)
 */
public class TwoSum {

    private static int[] twoSum(int[] numbers, int target) {

        int len = numbers.length;

        int i = 0;
        int j = len - 1;

        while (i < j) {
            int sum = numbers[i] + numbers[j];

            if (sum > target) j--;
            else if (sum < target) i++;
            else return new int[] {i+1, j+1};
        }
        return new int[] {-1, -1}; // never comes here if sum exists
    }

    /**
     * If the array a is not sorted
     * TC: O(n)
     * SC: O(n)
     * @param a
     * @param k
     * @return boolean
     */
    private static boolean twoSumNotSorted(int[] a, int k) {
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < a.length; i++) {
            if(set.contains(k - a[i])) {
                return true;
            }
            set.add(a[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] numbers = {0, 0, 3, 4};
        int target = 0;
        System.out.println("The indexes are: " + Arrays.toString(twoSum(numbers, target)));

        int[] a = {-1, 4, 3, 7, 2, -5, 1};
        int k = 4;
        System.out.println("The two sum possible " + twoSumNotSorted(a, k));
    }
}
