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

    /**
     * Return the count, that is the numbers of subsets with sum instead of printing them
     */
    static int count = 0;
    private static void findSubsets(int[] array, int i, int sumSoFar, int sum) {

        //Base Case
        if (i == array.length) {
            if (sum == sumSoFar) {
                count++;
            }
        } else { //Recursive Case

            //Don't Include A
            findSubsets(array, i + 1, sumSoFar, sum);

            //Include A
            findSubsets(array, i + 1, sumSoFar + array[i], sum);
        }
    }

    //Backtracking where it return 1 if sum = 0 or 0 if sum < 0
    //Return the number of subsets with given sum
    //If you have negative values, then just remove sum < 0
    private static int findSubsets(int[] array, int i, int sum) {

        //Base Case
        if (sum == 0) {
            return 1;
        } else if (sum < 0 && i == array.length) {
            return 0;
        } else {

            return findSubsets(array, i + 1, sum) + findSubsets(array, i + 1, sum - array[i]);
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 4, 6, 7};
        findSubsets(array, 0, new int[array.length], 0, 0, 13);

        int[] array1 = {1, 2, 3, 4, 5};
        findSubsets(array1, 0, 0, 5);
        System.out.println("The number of subsets with sum 5 is: " + count);

        System.out.println("The number of subsets with sum 5 is: " + findSubsets(array1, 0, 5));

        int[] array2 = {5, -3, -2, 4, 1, 7};

        System.out.println("The number of subsets with sum 5 is: " + findSubsets(array2, 0, 5));
    }
}
