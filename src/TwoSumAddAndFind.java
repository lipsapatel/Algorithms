import java.util.HashMap;

/**
 * Two Sum III - Data structure design
 Design and implement a TwoSum class. It should support the following operations: add and find.

 add - Add the number to an internal data structure.
 find - Find if there exists any pair of numbers which sum is equal to the value.

 Example
 Example 1:

 add(1); add(3); add(5);
 find(4) // return true
 find(7) // return false
 */
public class TwoSumAddAndFind {

    static HashMap<Integer, Integer> map = new HashMap<>();

    /**
     * @param number: An integer
     * @return: nothing
     */
    public static void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, map.get(number) + 1);
        } else {
            map.put(number, 1);
        }
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public static boolean find(int value) {
        for (Integer i: map.keySet()) {
            int target = value - i;

            if (map.containsKey(target)) {
                if (i == target && map.get(target) < 2) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        add(1);
        add(3);
        add(5);

        System.out.println("Found: " + find(4));
    }
}
