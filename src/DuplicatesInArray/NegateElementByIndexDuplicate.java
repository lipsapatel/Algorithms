package DuplicatesInArray;

/**
 * This approach only works if the array has all positive elements
 * And the elements are in the range from 0 to n-1 where n is the size of array
 *
 * Iterate the array. Negate the ith element of array. If it's already negative then we
 * have duplicate
 * arrayA[arrayA[i]] = arrayA[arrayA[i]] * -1;
 *
 * If the element is 0, then update it with Integer.MIN_VALUE
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * int[] a = {4, 6, 2, 1, 2, 5, 0, 0};
 * Output: Array has duplicates: 2, 0
 *
 * int[] a = {1, 6, 5, 2, 3, 3, 2};
 * Output: Array has duplicates: 2, 3
 */
public class NegateElementByIndexDuplicate {

    private static void hasDuplicate(int[] arrayA) {
        for (int i = 0; i < arrayA.length; i++) {

            int index;
            if (arrayA[i] == Integer.MIN_VALUE) {
                index = 0;
            } else {
                index = Math.abs(arrayA[i]);
            }

            if (arrayA[index] < 0) {
                System.out.println("Array has duplicates " + index);
            } else if (arrayA[index] == 0) {
                arrayA[index] = Integer.MIN_VALUE;
            } else {
                arrayA[index] = arrayA[index] * -1;
            }
        }
    }

    public static void main(String[] args) {
        int[] arrayA = {4, 3, 2, 1, 2, 5, 0, 0};

        long startTime = System.currentTimeMillis();
        hasDuplicate(arrayA);
        long endTime = System.currentTimeMillis();

        System.out.println("Total execution time for arrayA " + (endTime - startTime));

        int[] arrayB = {1, 6, 5, 2, 3, 3, 2};
        long startTime1 = System.currentTimeMillis();
        hasDuplicate(arrayB);
        long endTime1 = System.currentTimeMillis();

        System.out.println("Total execution time for arrayB " + (endTime1 - startTime1));

        int[] arrayC = {1, 0, 2, 4, 3};
        hasDuplicate(arrayC);
    }
}
