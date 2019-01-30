import java.util.Arrays;

/**
 * Divide and Conquer Algorithm
 *
 * 1) Picks last element as pivot
 * 2) Places pivot to it's correct position such that all the elements
 * to it's left are smaller and to it's right are greater
 * 3) This returns the index of pivot element
 * 4) Recursively does the quicksort on left of pivot
 * 5) Recursively does the quicksort on right of pivot
 *
 * Worst Case Time Complexity: O(n2)
 * faster in practice because its inner loop can be efficiently implemented in most architectures and in most real-world data.
 *  Merge sort is better when data is huge and stored in external storage.
 *
 * Best Case and Average Case Time Complexity: O(nlogn)
 */
public class QuickSort {

    //Place the pivot at it's right position and return it's index
    //Smaller elements placed to left of pivot
    //Larger elements to it's right
    private static int partition(int[] array, int low, int high) {

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            //If jth element is smaller than pivot
            //1) increment i and swap jth and ith element

            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        //Increment i and swap pivot with ith
        i++;
        swap(array, i, high);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void quickSort(int[] array, int low, int high) {

        if (low < high) {
            //Get the pivot position
            int pi = partition(array, low, high);

            //Recursively sort the left half of pivot
            quickSort(array, low, pi - 1);
            //Recursively sort the right half of pivot
            quickSort(array, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] array = {10, 80, 30, 90, 40, 50, 70};
        System.out.println("Original Array: " + Arrays.toString(array));

        quickSort(array, 0, array.length - 1);
        System.out.println("After quick sort: " + Arrays.toString(array));
    }
}
