package ElementWhichAppearsMaximumOfTimes;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, write an algorithm to find element which occurs
 * maximum number of times
 *
 * int[] array = {4, 1, 5, 2, 1, 5, 9, 8, 6, 5, 3, 2, 4, 7}
 * Output: Element occurring maximum number of times - 5
 *          maximum count = 3
 *
 * int[] array = {2, 2, 1, 1}
 * Output: Element occurring maximum number of times - 2
 *          maximum count = 2
 *
 * Create hashmap
 * Store the element and it's count
 *
 * Iterate hashmap and return element with maximum count
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ElementMaximumTimes_Hashmap_On {

    private static void elementAppearingMaximumTimes(int[] array) {

        if (array.length < 1) {
            System.out.println("Array is empty");
            return;
        }

        Map<Integer, Integer> integerHashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < array.length; i++) {

            if (integerHashMap.containsKey(array[i])) {
                integerHashMap.put(array[i], integerHashMap.get(array[i]) + 1);
            } else {
                integerHashMap.put(array[i], 1);
            }
        }

        int maximumElement = 0;
        int maximumCount = 0;

        for(Map.Entry entry: integerHashMap.entrySet()) {

            if ((Integer)entry.getValue() > maximumCount) {
                maximumCount = (Integer)entry.getValue();
                maximumElement = (Integer)entry.getKey();
            }
        }

        System.out.println("The element occurring maximum number of times is " + maximumElement +
         " and it's count is " + maximumCount);
    }

    public static void main(String[] args) {

        int[] array = {4, 1, 5, 2, 1, 5, 9, 8, 6, 5, 3, 2, 4, 7};

        elementAppearingMaximumTimes(array);

        int[] array1 = {2, 2, 1, 1};

        elementAppearingMaximumTimes(array1);

        int[] array2 = {};

        elementAppearingMaximumTimes(array2);
    }
}
