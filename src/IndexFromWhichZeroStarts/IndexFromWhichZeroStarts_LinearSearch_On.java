package IndexFromWhichZeroStarts;

/**
 * Given an array of Integer in which 1 is followed by 0.
 * Find the starting index of 0.
 *
 * Linear Search - Iterate the array and return the index of 0
 * Time Complexity: O(n)
 *
 * int[] a = {1,1,1,1,0,0}
 * Index = 4
 *
 * int[] a = {1,1,1}
 * No element found
 *
 * int[] a = {0,0,0}
 * Index = 0
 */
public class IndexFromWhichZeroStarts_LinearSearch_On {

    private static void indexFromWhichZeroStarts(int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                System.out.println("First Index from where zero starts is " + i);
                return;
            }
        }

        System.out.println("Array does not have zero");
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 1, 0};

        indexFromWhichZeroStarts(array);

        int[] array1 = {0, 0, 0};

        indexFromWhichZeroStarts(array1);

        int[] array2 = {1, 1};

        indexFromWhichZeroStarts(array2);
    }
}
