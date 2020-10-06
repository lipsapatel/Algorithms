/**
 * Given an array and target, find 4 elements that sum to the target.
 * Assume that the given array is sorted.
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(1)
 */
public class FourSum {

    private static boolean fourSum(int[] a, int k) {
        for(int i = 0; i < a.length; i++) {
            if(threeSum(a, i + 1, k - a[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean threeSum(int[] a, int start, int k) {
        for(int i = start; i < a.length; i++) {
            if(twoSum(a, i + 1, k - a[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean twoSum(int[] a, int start, int k) {
        int i = start;
        int j = a.length - 1;

        while(i < j) {
            int sum = a[i] + a[j];

            if(sum < k) {
                i++;
            } else if (sum > k) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 5, 8, 11, 15, 20, 25, 30};
        int k = 63;

        System.out.println("Four Sum " + fourSum(a, k));
    }
}
