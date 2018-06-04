/**
 * Searching in an unsorted array.
 *
 * Input: Array of Integers
 * k = integer element to be searched
 *
 * Output: an index j such that A[j] = k
 *
 * Time Complexity: O(n)
 */
public class SearchInUnsortedArray {

    private static int searchElementInUnsortedArray(int[] array, int k) {

        for (int i=0; i < array.length; i++) {
            if (array[i] == k) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] array = {3, 4, 6, 7, 8, 19, 20, 22};
        System.out.println("The index of element is: " + searchElementInUnsortedArray(array, 19));
    }
}
