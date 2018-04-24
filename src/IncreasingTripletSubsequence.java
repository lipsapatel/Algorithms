/**
 * Find increasing triplet subsequence
 *
 * Given an integer array A = 1...n
 * Find instance of i, j, k where 0 < i < j < k <= n
 * and A[i] < A[j] < A[k]
 *
 * int array = {10, 9, 4, 3, 2, 1, 7, 3, 1, 11, 2, 6, 9};
 * Increasing triplets are
 * 1, 7, 11
 * 1, 3, 6
 * 1, 2, 9 and many more
 *
 * Approach:
 *
 * 1) Take two array lMin and rMax, the size of the array are same as original array
 * 2) lMinIndex = 0 and lMinIndexValue = array[0]
 * 3) rMaxIndex = array.length - 1 and rMaxIndexValue = array[array.length - 1]
 * 4) Fill the lMin array with the index which is minimum value so far.
 * 5) Fill the rMax array with the index which has maximum value so far traversing backward.
 * 6) Now traverse the array and find the element which satisfy below condition
 * array[lMin[i]] < array[i] && array[i] < array[rMax[i]]
 *
 * Time Complexity: O(n)
 */
public class IncreasingTripletSubsequence {

    private static void findIncreasingTripletSubsequence(int[] array) {

        int[] lMin = new int[array.length];
        int[] rMax = new int[array.length];

        int lMinIndex = 0;
        int lMinIndexValue = array[0];

        int rMaxIndex = array.length - 1;
        int rMaxIndexValue = array[array.length - 1];

        //Now fill the lMin array with the min index found so far
        for (int i = 0; i < array.length; i++) {

            if (array[i] < lMinIndexValue) {
                lMinIndexValue = array[i];
                lMinIndex = i;
            }
            lMin[i] = lMinIndex;
        }

        //Now fill the rMax array with the max index found so far
        for (int i = array.length - 1; i >= 0; i--) {

            if (array[i] > rMaxIndexValue) {
                rMaxIndex = i;
                rMaxIndexValue = array[i];
            }
            rMax[i] = rMaxIndex;
        }

        //Now traverse the array and find array[i] such that array[lMin[i]] < array[i] < array[rMax[i]]
        for (int i = 0; i < array.length; i++) {

            if (array[lMin[i]] < array[i] && array[i] < array[rMax[i]]) {
                System.out.println("Triplet " + array[lMin[i]] + "  " + array[i]
                        + "  " + array[rMax[i]]);
                return;
            }
        }
    }

    public static void main(String[] args) {
        int array[] = { 10, 9, 4, 3, 2, 1, 7, 3, 1, 11, 2, 6, 9 };

        findIncreasingTripletSubsequence(array);
    }
}
