import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

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
 *
 * IK Problem Statement
 *
 * Nuts and Bolts!


 Problem Statement:


 A disorganized carpenter has a mixed pile of bolts and nuts and would like to find the corresponding pairs of bolts and nuts.
 Each nut matches exactly one bolt and vice versa. By trying to match a bolt and a nut, the carpenter can see which one is bigger,
 but she cannot compare two bolts or two nuts directly. Can you help the carpenter match the nuts and bolts quickly?


 In other words: You are given a collection of N nuts of different size and N corresponding bolts. You can choose
 to compare any nut & any bolt to determine whether the nut is larger than the bolt, smaller than the bolt or matches the bolt exactly.
 However, there is no way to compare two nuts together or two bolts together. (i.e. you cannot sort all nuts or sort all bolts).
 Write an algorithm to match each bolt to its matching nut.


 Note that:

 No two nuts are of same size. Similarly, no two bolts are of same size.
 A given nut uniquely matches a bolt. i.e. There are no extra unmatched nuts or extra bolts. Every nut has one and only one matching
 bolt and vice-versa.


 Input Format:


 You will be given two integer arrays, NUTS[] and BOLTS[] of size N.


 Output Format:


 Return a string array res, of size N, with an entry for each pair of Nut and its corresponding Bolt separated by a single space.


 Format: “Nut Bolt”


 Order of the output does not matter.


 Input/Output Format For The Custom Input:


 Input Format:


 The first line of input should contain an integer N, denoting no. of nuts. In next N lines, ith line should contain an
 integer NUTS[i], denoting size of ith nut.

 Next line should contain an integer N, denoting no. of bolts. In next N lines, ith line should contain an
 integer BOLTS[i], denoting size of ith bolt.


 If N = 4, NUTS = [4, 32, 5, 7] and BOLTS = [32, 7, 5, 4], then input should be:


 4

 4

 32

 5

 7

 4

 32

 7

 5

 4


 Output Format:


 There will be N lines of output, where ith line contains a string res[i], denoting value at index i of res.

 Here, res is the result array returned by solution function.


 For input N = 4, NUTS = [4, 32, 5, 7] and BOLTS = [32, 7, 5, 4], output will be:


 4 4

 32 32

 5 5

 7 7


 Constraints:

 1 <= N <= 10^5
 1 <= NUTS <= 10^9
 1 <= BOLTS <= 10^9


 Sample Test Case:


 Sample Input:


 NUTS = [4, 32, 5, 7]

 BOLTS = [32, 7, 5, 4]


 Sample Output:


 4 4

 32 32

 5 5

 7 7
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

    //Sort low, mid and high
    private static void medianOfThree(int[] a, int low, int middle, int high) {

        if (a[middle] < a[low]) {
            swap(a, low, middle);
        }
        if (a[high] < a[low]) {
            swap(a, low, high);
        }

        if (a[high] < a[middle]) {
            swap(a, middle, high);
        }
    }

    private static void matchNutsAndBoltsUsingQuickSort(int[] nuts, int[] bolts, int low, int high) {

        if (low < high) {

            int middle = low + (high - low)/2;
            medianOfThree(bolts, low, middle, high);
            swap(bolts, middle, high);

            //You can use random - upper bound exclusive, 0 inclusive
            //int pivot = partition(nuts, low, high, bolts[new Random().nextInt(high - low + 1) + low]);

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

        String[] res = new String[nuts2.length];

        for (int i = 0; i < nuts2.length; i++) {
            res[i] = nuts[i] + " " + bolts[i];
            System.out.println(res[i]);
        }



        System.out.println("Matched nuts and bolts using Quick sort are: ");
        System.out.println(Arrays.toString(nuts2));
        System.out.println(Arrays.toString(bolts2));
    }
}
