package DuplicatesInArray;

import java.util.Arrays;

/**
 * Sort the array. This will bring all duplicates together.
 * Now check the element with it's adjacent element, if they are same then there is a
 * duplicate.
 *
 * int[] a = {4, 6, 2, 1, 2, 5};
 * Output: Array has duplicates: 2
 *
 * int[] a = {1, 6, 5, 2, 3, 3, 2};
 * Output: Array has duplicates: 2, 3
 *
 * Time Complexity: O(nlogn)
 * Space Complexity: O(n)
 * Using merge sort
 */
public class SortArrayDuplicate {

    private static void hasDuplicate(int[] arrayA) {
        Arrays.sort(arrayA);

        for (int i = 0; i < arrayA.length - 1; i++) {
            if (arrayA[i] == arrayA[i+1]) {
                System.out.println("Array has duplicates: " + arrayA[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arrayA = {4, 6, 2, 1, 2, 5};

        long startTime = System.currentTimeMillis();
        hasDuplicate(arrayA);
        long endTime = System.currentTimeMillis();

        System.out.println("Total time of execution for arrayA " + (endTime - startTime));

        int[] arrayB = {1, 6, 5, 2, 3, 3, 2};

        long startTime1 = System.currentTimeMillis();
        hasDuplicate(arrayB);
        long endTime1 = System.currentTimeMillis();

        System.out.println("Total time of execution for arrayB " + (endTime1 - startTime1));
    }
}
