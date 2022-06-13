package IK.Sorting.PreClass;

import java.util.Arrays;

/**
 * Hoare Algorithm
 *
 * Scheme - <, =, ?, >
 *
 * Lomuto's Scheme
 * <= > ? pivot
 *
 * Lomuto performs relatively poorly when there is high percentage of duplicate values where as Hoare performs just fine in that situation.
 *
 * Lomotu – pi = 5 skew every time swap O(n2) while hoare performs in O(nlogn)
 5, 5, 5, 2, 5, 5, 1, 5, 5

 5
 /
 5, 5,5,2, 5,5,1,5
 /
 5
 /
 5,5,5,2,5,5,1

 Hoare’s Partition Scheme works by initializing two indexes that start at two ends, the two indexes move toward each other until an inversion is
 (A smaller value on left side and greater value on right side) found. When an inversion is found, two values are swapped and process is repeated.

 Lomuto performs more swaps than Hoare

 Hoare's Algorithm performance is degraded to O(n2) if the array is already sorted.

 Nore that in this scheme, the pivot's final location is not necessarily at the index that was returned.
 And the next two segment recurs on (low, pi) and (pi + 1, high) as opposed to pi - 1 in lomuto's.

 Hoare’s scheme is more efficient than Lomuto’s partition scheme because it does three times fewer swaps on average, and
 it creates efficient partitions even when all values are equal.

 Hoarse Partitioning is not stable sorting algorithm.

 Time Complexity: O(nlogn)
 */
public class HoareAlgorithm {

    public static int hoarePartition(int[] a, int low, int high) {

        //Hoare's Partition
        int pivot = a[low];

        int i = low - 1;
        int j = high + 1;

        while(true) {

            //Increment i while a[i] < pivot
            do {
                i++;
            } while (a[i] < pivot);

            //Decrement j while a[j] > pivot
            do {
                j--;
            } while (a[j] > pivot);

            //If they cross return
            if (i >= j) {
                return j;
            }

            swap(a, i, j);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pi = hoarePartition(a, low, high);

            //Here it is pi while in lomuto's it was pi - 1
            quickSort(a, low, pi);
            quickSort(a, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        //int[] a = {5, 5, 2, 5, 5, 1, 5, 5};
        //int[] a = {4, 2, 5, 5, 1, 5, 5, 5};
        //int[] a = {1, 2, 1, 2, 0, 0, 2, 1, 2, 0, 2, 1, 0, 1, 2, 2, 1, 1,1, 0, 0, 0, 1, 2, 0, 1, 2, 1};

        int[] a = {2, 8, 7, 1, 3, 5, 6};
        long s = System.nanoTime();
        quickSort(a, 0, a.length - 1);
        System.out.println(System.nanoTime() - s);

        System.out.println(Arrays.toString(a));
    }
}
