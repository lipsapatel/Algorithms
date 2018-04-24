package DuplicatesInArray;

/**
 * This method checks the duplicate in a given array by iterating and
 * checking against all elements of
 * array for duplication.
 *
 * int a[] = {4, 6, 2, 1, 2, 5};
 * Output: Array has duplicates: 2
 *
 * int a[] = {1, 6, 5, 2, 3, 3, 2};
 * Output: Array has duplicates: 2, 3
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
public class TwoLoopsDuplicate {

    private static void hasDuplicate(int[] arrayA) {

        for (int i = 0; i < arrayA.length; i++) {
            for (int j = i+1; j < arrayA.length; j++) {
                if (arrayA[i] == arrayA[j]) {
                    System.out.println("Array has duplicates: " + arrayA[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arrayA = {4, 6, 2, 1, 2, 5};

        Long startTime = System.currentTimeMillis();
        hasDuplicate(arrayA);
        Long endTime = System.currentTimeMillis();

        System.out.println("Total time of execution for arrayA " + (endTime - startTime));

        int[] arrayB = {1, 6, 5, 2, 3, 3, 2};

        Long startTime1 = System.currentTimeMillis();
        hasDuplicate(arrayB);
        Long endTime1 = System.currentTimeMillis();

        System.out.println("Total time of execution for arrayB " + (endTime1 - startTime1));
    }
}
