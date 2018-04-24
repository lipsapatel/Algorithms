/**
 * Given an array, find the maximum j - i such that array[j] > array[i]
 *
 * int[] array = {12, 3, 1, 5, 6, 4, 10, 9, 8, 0};
 * Output: Max (j - i) where j > i and array[j] > array[i] is 7
 *
 * 3 and 8
 *
 * Approach:
 *
 * 1) Create two auxiliary array lMin and rMax
 * 2) lMin[0] = array[0]
 * 3) Traverse the main array and fill lMin with mimimum value found so far
 * 4) rMax[array.length - 1] = array[array.length - 1]
 * 5) Traverse the main array backwards and fill the rMax with maximum value found so far
 * 6) initialize distanceSoFar = 0
 * 7) While i  and j < array.length
 * 8) Check if lMin[i] < rMax[j]
 * 9) if j - i is greater than distanceSoFar then update distanceSoFar
 * 10) j++
 * 11) else i++
 * 12) return the distanceSoFar
 *
 * Time Complexity: O(n)
 *
 */
public class FindMaximumj_iSuchThatArrayjGreaterThanArrayi {

    private static int findMaximumDistanceOrDifference(int[] array) {

        int[] lMin = new int[array.length];
        int[] rMax = new int[array.length];

        int leftMinValue = array[0];
        int rightMaxValue = array[array.length - 1];

        //Traverse the main array and fill lMin with the minimum value found so far
        for (int i = 0; i < array.length; i++) {
            if (leftMinValue > array[i]) {
                leftMinValue = array[i];
            }
            lMin[i] = leftMinValue;
        }

        //Traverse the main array backwards and fill the rMax with the maximum value found so far
        for (int i = array.length - 1; i >= 0; i--) {
            if (rightMaxValue < array[i]) {
                rightMaxValue = array[i];
            }
            rMax[i] = rightMaxValue;
        }

        int distanceSoFar = -1;

        int x = 0, y = 0;

        while (x < array.length && y < array.length) {

            if (lMin[x] < rMax[y]) {

                if (y - x > distanceSoFar) {
                    distanceSoFar = y - x;
                }
                y++;
            } else {
                x++;
            }
        }
        return distanceSoFar;
    }

    public static void main(String[] args) {
        int[] array = {12, 3, 1, 5, 6, 4, 10, 9, 8, 0};

        int x = findMaximumDistanceOrDifference(array);

        System.out.println("Max(j-i) where j>i and A[j]>A[i] is : " + x);
    }
}
