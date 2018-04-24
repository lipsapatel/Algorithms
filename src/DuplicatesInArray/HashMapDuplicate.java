package DuplicatesInArray;

import java.util.HashMap;

/**
 * Iterate through the array and store the element and it's count in Hashmap
 * If for any element count is greater than 1 then it is duplicate.
 *
 * int[] a = {4, 6, 2, 1, 2, 5};
 * Output: Array has duplicates: 2
 *
 * int[] a = {1, 6, 5, 2, 3, 3, 2};
 * Output: Array has duplicates: 2, 3
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class HashMapDuplicate {

    private static void hasDuplicate(int[] arrayA) {
        HashMap<Integer, Integer> arrayHashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < arrayA.length; i++) {
            if (arrayHashMap.containsKey(arrayA[i])) {
                arrayHashMap.put(arrayA[i], arrayHashMap.get(arrayA[i]) + 1);
                //If it comes here that means array has duplicates
                System.out.println("Array has duplicates " + arrayA[i]);
            } else {
                arrayHashMap.put(arrayA[i], 1);
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
