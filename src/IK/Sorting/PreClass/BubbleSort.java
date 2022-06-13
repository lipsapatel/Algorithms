package IK.Sorting.PreClass;

import java.util.Arrays;

/**
 * Bubble Sort and Optimized Bubble Sort - Java Implementation
 *
 * Simplest sorting algorithm
 *
 * What is bubble sort?
 *
 * 1) Bubble sort is one of the simplest sorting algorithms.
 * 2) Bubble sort compares each pair of adjacent items and swaps them
 * if they are in the wrong order.
 * 3) The pass through the list is repeated until no swaps are needed,
 * that means array is sorted.
 * 4) As the name indicates, the lighter elements (smaller) bubble up to the top
 * 5) Bubble sort is also known as sinking sort
 * (heavy or bigger elements settles down at the bottom of the
 * list after each iteration)
 *
 * Time Complexity
 * Worst and Average Case:
 * O(n^2)
 *
 * Best Case:
 * O(n) when array is already sorted
 *
 * Space Complexity: O(1)
 *
 * In place sorting: yes
 *
 * resources/IK.Sorting.Sorting1.Class.PreClass.BubbleSort.png
 *
 * Pros and Cons of Bubble sort
 *
 * Pros: Bubble sort algorithm is considered as very simple sorting technique
 * since all you need to do is compare all adjacent elements
 * and swap them if they are in wrong order
 * Cons: Main drawback of bubble sort is it's time complexity which is O(n2)
 * since all the pairs are compared
 * even when the original array is sorted
 *
 * Optimized Bubble Sort
 *
 * 1) As seen in the program above, bubble sort compares all the pairs in the
 * array, even when original array is sorted
 * or partially sorted. This is where we can do some improvements
 * 2) During any iteration, if there are no swaps then we can claim that
 * our array is already sorted.
 *
 * //Outer loops for pass
 * //Inner loop swap the adjacent no
 *
 * Approach:
 * 1) Scan from right to left (inner loop)
 * 2) Compare two elements
 * 3) Bubble up smaller element by performing swap
 * 4) Do n iteration for scan (outer loop)
 *
 * TC: O(n^2)
 * SC: O(1)
 * Stable: yes
 *
 * Bubble sort performs more swap than Selection sort. So even though bubble sort is a correct algorithm, selection sort performs better than bubble sort.
 */
public class BubbleSort {

    private static void bubbleSortOptimized(int[] a) {

        for(int i = 0; i < a.length; i++) {

            boolean swap = false;
            for(int j = a.length - 1; j > i; j--) {

                if(a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                    swap = true;
                }
            }

            if(!swap) { //No swap performed so break
                break;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {

        int[] array = {5, 1, 9, 3, 2, 10};
        bubbleSortOptimized(array);

        System.out.println(Arrays.toString(array));
    }
}
