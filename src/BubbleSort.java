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
 * resources/BubbleSort.png
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
 */
public class BubbleSort {

    private static void bubbleSort(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }

        System.out.println("Original Array: " + Arrays.toString(array));

        int size = array.length;

        for (int i = 0; i < size - 1; i++) {

            for (int j = 0; j < size - 1 - i; j++) {

                //Check the adjacent elements
                if (array[j] > array[j + 1]) {

                    //Swap the elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    private static void bubbleSortOptimized(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }

        System.out.println("Original Array: " + Arrays.toString(array));
        boolean isSwapped;

        int size = array.length;

        for (int i = 0; i < size - 1; i++) {

            isSwapped = false;

            for (int j = 0; j < size - 1 - i; j++) {

                //Check the adjacent elements
                if (array[j] > array[j + 1]) {

                    //Swap the elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSwapped = true;
                }
            }

            //Check if any swapping occured in last iteration
            //If no then break the loop, array is sorted at this point
            if (!isSwapped) {
                break;
            }
        }

        System.out.println("Sorted Array: " + Arrays.toString(array));

    }

    public static void main(String[] args) {

        int[] array = {5, 1, 9, 3, 2, 10};
        bubbleSort(array);

        int [] arrA = {5, 1, 9, 3, 2, 10};
        bubbleSortOptimized(arrA);
        System.out.println("------------------------------");
        arrA = new int []{1, 2, 4, 6, 8, 10};
        bubbleSortOptimized(arrA);
    }
}
