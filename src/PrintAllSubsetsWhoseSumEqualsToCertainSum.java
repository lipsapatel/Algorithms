/**
 * Given an array of positive integers, print only those subsets that have a certain sum.
 * (If the values can be negative, this is a simple change in the base case,
 * where you print the said subset only if it has the right sum)﻿﻿﻿﻿﻿

 Pause and write code for this extension. Possible code structure in next section.

 Time Complexity: O(2 ^ n) * n
 Space Complexity: O(n)
 */
public class PrintAllSubsetsWhoseSumEqualsToCertainSum {

    private static void findSubsets(int[] array, int i, int[] subsetSoFar, int j, int sumSoFar, int sum) {

        //Base Case
        if (i == array.length) {
            if(sum == sumSoFar) {
                //Print
                for (int x = 0; x < j; x++) {
                    System.out.print(subsetSoFar[x] + " ");
                }
                System.out.println();
            }
        } else { //Recursive Case

            //Don't include A
            findSubsets(array, i + 1, subsetSoFar, j, sumSoFar, sum);

            //Include A
            subsetSoFar[j] = array[i];
            findSubsets(array, i + 1, subsetSoFar, j + 1, sumSoFar + array[i], sum);
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 4, 6, 7};
        findSubsets(array, 0, new int[array.length], 0, 0, 13);
    }
}
