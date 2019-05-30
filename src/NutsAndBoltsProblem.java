import java.util.Arrays;
import java.util.HashMap;

/**
 * Nuts and Bolts Problem (Lock and key problem)
 *
 * Given 'n' Nuts and 'n' Bolts  of different sizes. There is one-to-one mapping between nuts and
 * bolts. Write an algorithm to find all matches between nuts and bolts.
 *
 * Note: This problem can also be framed as - Given 'n' keys and 'n' locks. There is one-to-one
 * mapping between keys and locks, means each lock has a specific key and can be unlocked using
 * that key only.
 * Write an algorithm to find all matches between keys and locks.
 *
 * You are allowed to compare only nuts with bolts (or keys with locks), nuts with nuts
 * and bolts with bolts comparisons are not allowed.
 *
 * Example:
 *
 * char[] nuts = {'$', '%', '&', '*', 'x'};
 * char[] bolts = {'%', 'x', '*', '$', '&'};
 *
 * Output:
 *
 * Matched nuts and bolts are:
 * [$, %, &, *, x]
 * [$, %, &, *, x]
 *
 * Approach:
 *
 * Brute Force
 *
 * 1) Compare each nut (or key) will all the bolts (or locks).
 * 2) If there is a match, perform swap for bolts (or locks)
 *
 * Time Complexity: O(n^2)
 *
 *
 * Using HashMap:
 *
 * 1) Insert all the nuts as key and it's position as value into HashMap.
 * 2) Iterate through all bolts and check if the nuts is present in hashmap and if present
 * then place it at the right place.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Using QuickSort:
 *
 * 1) Pick last element as pivot in bolts
 * 2) Partition nuts based on that pivot
 * 3) Partition bolts based on nuts[pivot]
 * 4) Recur left and right
 *
 * Time Complexity: 2n(logn)
 * Space Complexity: O(1)
 */
public class NutsAndBoltsProblem {

    private static void matchNutsAndBoltsUsingBruteForce(char[] nuts, char[] bolts) {

        for (int i = 0; i < nuts.length; i++) {

            char nut = nuts[i];

            for (int j = 0; j < bolts.length; j++) {

                if (nut == bolts[j]) {
                    swap(bolts, i, j);
                    break;
                }
            }
        }

        System.out.println("Matched nuts and bolts using Brute Force are: ");
        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));
    }

    private static void swap(char[] bolts, int i, int j) {

        char temp = bolts[i];
        bolts[i] = bolts[j];
        bolts[j] = temp;
    }

    private static void matchNutsAndBoltsUsingHashMap(char[] nuts, char[] bolts) {

        //Create a HashMap for nuts
        //nut as key and it's position as value
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < nuts.length; i++) {
            map.put(nuts[i], i);
        }

        //for each bolt, check for the nut in map
        for (int i = 0; i < bolts.length; i++) {

            char bolt = bolts[i];

            //check if bolt present in map
            if (map.containsKey(bolt)) {

                //Put it at right position
                nuts[i] = bolts[i];
            } else {
                System.out.println("For bolt " + bolt + " no nut is present.");
                return;
            }
        }

        System.out.println("Matched nuts and bolts using HashMap are: ");
        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));
    }

    private static void matchNutsAndBoltsUsingQuickSort(int[] nuts, int[] bolts, int low, int high) {

        if (low < high) {

            //Choose last character from bolts as pivot and partition nuts based on that
            int pivot = partition(nuts, low, high, bolts[high]);

            //Partition bolts using nuts[pivot]
            partition(bolts, low, high, nuts[pivot]);

            //Recur
            matchNutsAndBoltsUsingQuickSort(nuts, bolts, low, pivot - 1); //Left
            matchNutsAndBoltsUsingQuickSort(nuts, bolts, pivot + 1, high); //right
        }
    }

    private static int partition(int[] array, int low, int high, int pivot) {

        int i = low - 1;
        boolean placePivotOnce = false; //If there are duplicate pivot place only on pivot to position high

        for (int j = low; j < high; j++) {

            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            } else if (array[j] == pivot && !placePivotOnce) { //Pivot can be some where in middle, if found place it at the end
                swap(array, j, high);
                j--;
                placePivotOnce = true;
            }
        }

        i++;
        swap(array, i, high);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {

        char[] nuts = {'$', '%', '&', 'x', '@'};
        char[] bolts = {'%', '@', 'x', '$', '&'};

        matchNutsAndBoltsUsingBruteForce(nuts, bolts);

        char[] nuts1 = {'$', '%', '&', 'x', '@'};
        char[] bolts1 = {'%', '@', 'x', '$', '&'};

        matchNutsAndBoltsUsingHashMap(nuts1, bolts1);

        //Sort both
        int[] nuts2 = {2, 1, 2, 5};
        int[] bolts2 = {5, 2, 1, 2};

        matchNutsAndBoltsUsingQuickSort(nuts2, bolts2, 0, nuts2.length - 1);

        System.out.println("Matched nuts and bolts using Quick sort are: ");
        System.out.println(Arrays.toString(nuts2));
        System.out.println(Arrays.toString(bolts2));
    }
}
