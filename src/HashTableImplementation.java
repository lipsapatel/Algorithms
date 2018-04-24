import java.util.Hashtable;

/**
 * To implement hashtable.
 *
 * Input: A set of keys and value pairs
 *
 * Approach:
 *
 * 1) Create hashtable
 *  Hashtable<Integer, String> hashtable = new HashTable<Integer, String>();
 * 2) Insert values into hashtable
 *    hashtable.put(key, value);
 * 3) Get value from hashtable
 *    get(key)
 *
 * The search time for any element is O(1) since it uses key to find the element.
 * Drawback: It takes extra space.
 *
 */
public class HashTableImplementation {

    private static String[] employeeNames = {"Lipsa", "Kushal", "Yashvi"};

    private static Hashtable<Integer, String> hashtable = new Hashtable<Integer, String>();

    private static void insertValues() {

        for (int i = 0; i < employeeNames.length; i++) {
            hashtable.put(i + 1, employeeNames[i]);
        }
    }

    private static String getValue(int key) {
        return hashtable.get(key);
    }

    public static void main(String[] args) {

        insertValues();
        System.out.println("All values inserted");
        System.out.println("Employee with ID 1 is "+ getValue(1));
        System.out.println("Employee with ID 3 is "+ getValue(3));
    }
}
