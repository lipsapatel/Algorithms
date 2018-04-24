package MajorityElementInArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Store the element and count in HashMap
 * Iterate the hashmap and check the count of each element
 * If count > n/2 then that element is the majority element
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class MajorityElementHashMap {

    private static void findMajorityElement(int[] array) {

        boolean found = false;
        HashMap<Integer, Integer> integerHashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < array.length; i++) {

            if (integerHashMap.containsKey(array[i])) {
                integerHashMap.put(array[i], integerHashMap.get(array[i]) + 1);
            } else {
                integerHashMap.put(array[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> element: integerHashMap.entrySet()) {
            if (element.getValue() > array.length/2) {
                System.out.println("Found the majority element " + element.getKey());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No majority element found");
        }
    }

    public static void main(String[] args) {
        int [] array = {1,3,5,5,5,5,4,1,5};
        findMajorityElement(array);
    }
}
