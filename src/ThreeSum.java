import java.util.HashSet;

/**
 * ThreeSum: Given an array and integer k, find 3 elements in array that sum to k
 * Assume that the given array is sorted.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 */
public class ThreeSum {

    private static boolean threeSum(int[] a, int k) {
        for(int i = 0; i < a.length; i++) {
            if(twoSum(a, i + 1, k - a[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean twoSumSet(int[] a, int start, int k) {
        HashSet<Integer> set = new HashSet<>();

        for(int i = start; i < a.length; i++) {
            if(set.contains(k - a[i])) {
                return true;
            }
            set.add(a[i]);
        }
        return false;
    }

    private static boolean twoSum(int[] a, int start, int k) {
        int i = start;
        int j = a.length - 1;

        while(i < j) {
            int sum = a[i] + a[j];

            if(sum > k) {
                j--;
            } else if(sum < k) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 5, 7, 9, 12, 15, 20, 21};
        int k = 40;

        System.out.println("Three sum possible " + threeSum(a, k));
    }

}
