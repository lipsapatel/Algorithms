package IK.Sorting.PreClass;

import java.util.Arrays;

/**
 * Insertion Sort is a simple sorting algorithm that works the way we
 * sort playing cards in our hands.
 *
 * resources/IK.Sorting.Sorting1.Class.PreClass.InsertionSort.png
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
 *
 * Approach:
 *
 * 1) Assume that n - 1 elements are sorted, sort n - 1 elements in recursive call.
 * 2) Place nth element in its right position by scanning from right to left and right shifting elements
 * greater than n.
 * 3) There are two approach
 *    - iterative
 *    - recursive
 * 4) Time Complexity : O(n^2)
 *    Space Complexity: O(1)
 */
public class InsertionSort {

    private static void insertionSort (int[] a) {

        for (int i = 0; i < a.length; i++) {

            int n = a[i];
            int j = i - 1;

            //Move elements of array[0...i - 1] that are greater than key to one position
            //ahead of their current position
            while (j >= 0 && a[j] > n) {

                a[j + 1] = a[j]; //Right shift
                j = j - 1;
            }

            //put key or nth element to correct position
            a[j + 1] = n;
        }

        System.out.println("Array after insertion sort: " + Arrays.toString(a));
    }

    //Place nth element to its right place
    private static void recursiveInsertionSort(int[] a, int nthIndex) {

        //Base Case
        if (nthIndex == 0) {
            return;
        }

        recursiveInsertionSort(a, nthIndex - 1);

        int n = a[nthIndex];
        int j = nthIndex - 1;

        while (j >= 0 && a[j] > n) {
            a[j + 1] = a[j]; //Right Shift
            j--;
        }

        //Place nth element at it's right position
        a[j + 1] = n;
    }

    public static void main(String[] args) {

        int[] array = {12, 11, 13, 5, 6};
        insertionSort(array);

        int[] array1 = {12, 11, 13, 5, 6};
        recursiveInsertionSort(array1, array1.length - 1);
        System.out.println("After recursive insertion sort " + Arrays.toString(array1));
    }
}
