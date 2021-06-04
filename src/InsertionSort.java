import java.util.Arrays;

/**
 * Insertion Sort is a simple sorting algorithm that works the way we
 * sort playing cards in our hands.
 *
 * resources/InsertionSort.png
 *
 * Time Complexity: O(n2) - Quadratic time.
 * Space Complexity: O(1)
 *
 * Best Case: Elements are already sorted. O(n) - Linear time
 * Worst Case: Elements are sorted in decreasing order - O(n2) - Quadratic time
 * Average Case: j/2 so O(n2) - Quadratic time.
 *
 * In place sorting
 *
 * Little more better than selection sort and bubble sort
 *
 * Insertion sort is a stable algorithm and good for smaller inputs
 */
public class InsertionSort {

    private static void insertionSort (int[] array) {

        int size = array.length;

        for (int i = 1; i < size; i++) {

            int key = array[i];
            int j = i - 1;

            //Move elements of array[0...i - 1] that are greater than key to one position
            //ahead of their current position
            while (j >= 0 && array[j] > key) {

                array[j + 1] = array[j];
                j = j - 1;
            }

            //put key to correct position
            array[j + 1] = key;
        }

        System.out.println("Array after insertion sort: " + Arrays.toString(array));
    }

    public static void main(String[] args) {

        int[] array = {12, 11, 13, 5, 6};

        insertionSort(array);
    }
}
