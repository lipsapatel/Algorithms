/**
 * Given an integer array, print all subsets such that sum is less than 20.
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */
public class FindAllSubsetWhoseSumIsLessThanTargetSum {

    private static void findSubsets(int[] array, int i, int[] subsetSoFar, int j, int targetSum) {

        //Base Case
        if (i == array.length) {
            int sum = 0;
            String subset = "";

            for (int k = 0; k < j; k++) {
                sum += subsetSoFar[k];
                subset = subset + " " + subsetSoFar[k];
            }

            if (sum < targetSum) {
                System.out.println(subset);
            }
        } else { //Recursive case

            //Don't include A
            findSubsets(array, i + 1, subsetSoFar, j, targetSum);

            //Include A
            subsetSoFar[j] = array[i];
            findSubsets(array, i + 1, subsetSoFar, j + 1, targetSum);
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 6, 7, 9};
        findSubsets(array, 0, new int[array.length], 0, 20);
    }
}
