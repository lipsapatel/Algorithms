import java.util.Arrays;

/**
 * Dynamic Programming - Longest Increasing Subsequence
 *
 * The longest increasing subsequence (LIS) problem is to find the length of the longest
 * subsequence in a given array such that all the elements in the subsequence are sorted
 * in increasing order.
 *
 * or
 *
 * Given an array A[1, 2...n], calculate B[1, 2...m] with B[i] < B[i + 1] where i = 1, 2, 3...m
 * such that m is the maximum.
 *
 * Construct LIS which is the longest increasing sequence length till ith location
 *
 * Example:
 *
 * int[] A = {1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2}
 *
 * Length of LIS = 7
 * LIS is {1, 12, 23, 52, 61, 69, 70}
 *
 * LIS(i) = 1 + max(LIS(j = 1 to i -1)) where A[j] < A[i]
 *
 * resources/LongestIncreasingSubsequence.png
 *
 */
public class LongestIncreasingSubsequence_DP {

    private static void findLongestIncreasingSubsequence(int[] array) {

        int[] LIS = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int max = -1;

            for (int j = 0; j < array.length; j++) {

                if (array[j] < array[i]) {

                    if (max == -1 || max < LIS[j] + 1) {
                        max = LIS[j] + 1; //Should be max of previous lis
                    }
                }
            }

            if (max == -1) { //means no smaller element found
                max = 1;
            }

            LIS[i] = max;
        }

        //Find the max in LIS
        int result = -1;
        int index = -1;

        for (int i = 0; i < LIS.length; i++) {
            if (LIS[i] > result) {
                result = LIS[i];
                index = i;
            }
        }

        //Print the result
        //Start moving backward from the end
        String increasingSubsequence = array[index] + " ";
        int res = result - 1;
        for (int i = index - 1; i >= 0; i--) {
            if (LIS[i] == res) {

                increasingSubsequence = array[i] + " " + increasingSubsequence;
                res--;
            }
        }

        System.out.println("Longest Increasing Subsequence Length: " + result);
        System.out.println("Longest Increasing subsequence path " + increasingSubsequence);
    }

    public static void main(String[] args) {
        int[] A = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };
        findLongestIncreasingSubsequence(A);
    }
}
