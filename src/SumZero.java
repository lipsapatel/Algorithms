import java.util.Arrays;
import java.util.HashMap;

/**
 * Sum Zero
 *
 * Given an array of integers, find any non-empty subarray whose elements
 * sum up to zero.
 *
 * Example One
 * Input: [5, 1, 2, -3, 7, -4]
 * Output: [1, 3]
 * Sum of [1, 2, -3] subarray is zero. It starts at index 1 and ends at index 3
 * of the given array, so [1, 3] is a correct answer. [3, 5] is another correct answer.
 *
 * Example Two
 * Input: [1, 2, 3, 5, -9]
 * Output: [-1]
 *
 * There is no non-empty subarray with sum zero.
 *
 * Constraints:
 *
 *     1 <= n <= 5*10^5
 *     -10^9 <= arr[i] <= 10^9, (i = 0,1,...,(n-1))
 *
 * Output Format:
 * There are two cases here:
 * 1. If a valid sum zero subarray exists in arr, then there will be two lines for output. First line will have an integer res[0] and second line will have an integer res[1], denoting starting index and ending index of required subarray (0 based indexing, both inclusive).
 * 2. Otherwise if there is no valid sum zero subarray, there will be only one line for
 * output,having an integer -1.
 *
 * Brute Force Approach
 *
 * 1) Iterate over all possible subarrays starting at index i and ending at index j.
 * 2) Find the sum of all elements in it. If it's 0, then return [i, j]
 * 3) If no subarray found, return [-1]
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Optimal Solution
 *
 * 1) If there exists subarray with zero sum, then prefixSum + subarray sum = prefix sum
 * 2) We maintain map of sum and indexes.
 * 3) When we get sum, which is already present in the map, then we found subarray
 * with zero sum.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * resources/SumZero1.jpg
 * resources/SumZero2.jpg
 */
public class SumZero {

    private static int[] sumZero(int[] a) {
        int[] result = new int[2];
        long sum;

        for(int i = 0; i < a.length; i++) {
            sum = 0;
            for(int j = i; j < a.length; j++) {
                sum += a[j];

                if(sum == 0) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return new int[]{-1};
    }

    private static int[] sumZeroOptimal(int[] a) {
        int[] result = new int[2];

        //Map holds sum and their indexes
        HashMap<Long, Integer> map = new HashMap<>();

        //To check whether prefix sum itself = 0
        map.put(0l, -1);

        long sum = 0;
        for(int i = 0; i < a.length; i++) {

            sum += a[i];

            //If we found the sum in hashmap, it means we got subarray whose
            //sum is zero
            if(map.containsKey(sum)) {
                result[0] = map.get(sum) + 1;
                result[1] = i;
                return result;
            } else {
                map.put(sum, i);
            }
        }
        return new int[]{-1};
    }

    public static void main(String[] args) {
        int[] a = {5, 1, 2, -3, 7, -4};
        System.out.println(Arrays.toString(sumZero(a)));

        System.out.println(Arrays.toString(sumZeroOptimal(a)));
    }
}
