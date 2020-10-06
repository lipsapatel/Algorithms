import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Design a data structure that supports the following operations in Θ(1) time.
 *
 * void insert(x): Inserts an item x to the data structure if not already present.
 * void remove(x): Removes an item x from the data structure if present.
 * boolean search(x): Searches an item x in the data structure.
 * int getRandom(): Returns a random element from current set of elements
 *
 * We can use hashing to support first 3 operations in Θ(1) time.
 * How to do the 4th operation? The idea is to use a resizable array (ArrayList in Java,
 * vector in C) together with hashing. Resizable arrays support insert in Θ(1)
 * amortized time complexity.
 *
 * To implement getRandom(), we can simply pick a random number from 0 to size-1
 * (size is the number of current elements) and return the element at that index.
 * The hash map stores array values as keys and array indexes as values.
 *
 * Following are detailed operations.
 *
 * insert(x)
 * 1) Check if x is already present by doing a hash map lookup.
 * 2) If not present, then insert it at the end of the array.
 * 3) Add in the hash table also, x is added as key and last array index as the index.
 *
 * remove(x)
 * 1) Check if x is present by doing a hash map lookup.
 * 2) If present, then find its index and remove it from a hash map.
 * 3) Swap the last element with this element in an array and remove the last element.
 * Swapping is done because the last element can be removed in O(1) time.
 * 4) Update index of the last element in a hash map.
 *
 * getRandom()
 * 1) Generate a random number from 0 to last index.
 * 2) Return the array element at the randomly generated index.
 *
 * search(x)
 * Do a lookup for x in hash map.
 *
 * Time Complexity of all operations is O(1)
 *
 * resources/DesignDataStructure1.jpg
 * resources/DesignDataStructure2.jpg
 *
 */
public class DesignDataStructure {

    private static HashMap<Integer, Integer> map = new HashMap<>();
    private static ArrayList<Integer> array = new ArrayList<>();

    private static void insert(int x) {
        if(map.containsKey(x)) {
            return;
        }
        int index = array.size();
        map.put(x, index);

        array.add(x);
    }

    private static void remove(int x) {
        if(!map.containsKey(x)) {
            return;
        }

        int index = map.get(x);
        map.remove(x);

        int last = array.size() - 1;
        int lastElement = array.get(last);

        //This is O(1) operation
        Collections.swap(array, last, index);

        array.remove(last);
        map.put(lastElement, index);
    }

    private static int getRandom() {
        Random random = new Random();
        int index = random.nextInt(array.size());

        return array.get(index);
    }

    private static boolean isPresent(int x) {
        return map.containsKey(x);
    }

    public static void main(String[] args) {
        insert(10);
        insert(20);
        insert(30);
        insert(40);

        System.out.println("30 present " + isPresent(30));

        remove(20);
        insert(50);

        System.out.println("50 present " + isPresent(50));

        System.out.println("Random number " + getRandom());
    }
}

