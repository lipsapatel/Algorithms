package ElementWhichAppearOnceInArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, all elements appear twice but only one element appears
 * once.
 * Write an algorithm to find that element
 *
 * int[] array = {1, 5, 6, 2, 1, 6, 4, 3, 2, 5, 3}
 * Output = 4
 *
 * Use Hashmap to store the count of each element.
 * Iterate the hashmap and find the element whose count is one.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ElementWhichAppearOnceInArray_HashMap_On {

    private static void findElementWhichAppearOnce(int[] array) {

        HashMap<Integer, Integer> integerHashMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {

            if (integerHashMap.containsKey(array[i])) {
                integerHashMap.put(array[i], integerHashMap.get(array[i]) + 1);
            } else {
                integerHashMap.put(array[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry: integerHashMap.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println("Found the element which appears only once " + entry.getKey());
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 6, 2, 1, 6, 4, 3, 2, 5, 3};

        findElementWhichAppearOnce(array);
    }
}
