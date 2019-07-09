/**
 * Print the subset whose sum is closest to 20
 *
 * Time Complexity: O(2^n) * n (for determining min diff)
 * Space Complexity: O(n)
 */
public class PrintSubsetClosestToValue {

    private static int minDiff = Integer.MAX_VALUE;
    private static String subset = "";

    private static void findSubsets(int[] array, int i, int[] subsetSoFar, int j, int targetValue) {

        //Base Case
        if (i == array.length) {
            int sum = 0;
            String intermediateSubset = "";

            for (int k = 0; k < j; k++) {
                sum += subsetSoFar[k];
                intermediateSubset = intermediateSubset + " " + subsetSoFar[k];
            }

            int diff = Math.abs(targetValue - sum);
            if (diff < minDiff) {
                minDiff = diff;
                subset = intermediateSubset;
            }
        } else { //Recursive Case

            //Don't include A
            findSubsets(array, i + 1, subsetSoFar, j, targetValue);

            //Include A
            subsetSoFar[j] = array[i];
            findSubsets(array, i + 1, subsetSoFar, j + 1, targetValue);
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 6, 9, 7};

        findSubsets(array, 0, new int[array.length], 0, 20);
        System.out.println("The subset whose sum is closest to 20 is: " + subset);
    }
}
