package IK.Sorting;

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
 * Worst Case Time Complexity: O(n2) - If the array is already sorted and first or the last index is chosen as pivot
 * faster in practice because its inner loop can be efficiently implemented in most architectures and in most real-world data.
 *  Merge sort is better when data is huge and stored in external storage.
 *
 * Best Case and Average Case Time Complexity: O(nlogn)
 *
 * Space Complexity: Avg/Best case O(logn) because of stack frames from recursive call
 * Worst case: O(n)
 * In place sort
 *
 * What is Stability?
 *
 * Stability preserves the order in case of duplicate keys.
 *
 * Consider partition on this array
 * 4a, 2, 3, 4b, 1
 *
 * pivot = 1
 *
 * 1, 2, 3, 4b, 4a
 * So quick sort is not stable algorithm
 *
 * Work at level 1 is n
 * Work at level 2 is n/2 + n/2 = n
 *
 * n + n + n = n (1 + 1 + 1) = work at each level * height of tree
 * n (logn)
 * for n = 8
 * n (log 8) = 3n
 *
 */
public class QuickSort {

    //Place the pivot at it's right position and return it's index
    //Smaller elements placed to left of pivot
    //Larger elements to it's right
    //is partition stable - No
    //Time Complexity of Partition: n
    private static int partition(int[] array, int low, int high) {

        //Lomuto's partition
        //Swap array[pi] with array[end]; pivot = array[end]
        int pivot = array[high]; //fixed index not good idea, random pivot is overkill, median of 3- 5 values.
        int i = low - 1; //This is less than equal to field, because all elements before i including i itself is less than equal to pivot. All elements to right of i is greater than pivot

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
        swap(array, i, high); //place pivot at it's correct position
        return i;
    }

    //Brute force approach
    //Scan the array and find the count of elements less than equal to pivot
    //Then place pivot at that position in new array
    //scan array with two pointers and add elements to out array
    //Best and Average case TC and SC
    //Time Complexity: O(nlogn)
    //Space Complexity: O(nlogn)
    //Brute force approach is stable but requires n more space
    private static int partitionBruteForce(int[] array, int low, int high) {
        int pivot = array[high];

        int leCount = 0;

        for (int i = 0; i < array.length - 1; i++) { //O(n)
            if (array[i] <= pivot) {
                leCount++;
            }
        }

        int[] outArray = new int[array.length];

        outArray[leCount] = pivot;

        int p1 = 0;
        int p2 = leCount + 1;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] <= pivot) {
                outArray[p1] = array[i];
                p1++;
            } else {
                outArray[p2] = array[i];
                p2++;
            }
        }
        return leCount;
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
