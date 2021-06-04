import java.util.Arrays;

/**
 * The selection sort algorithm sorts an array by repeatedly finding the
 * minimum element (considering ascending order)
 * from unsorted part and putting it at the beginning
 *
 * arr[] = 64 25 12 22 11

 // Find the minimum element in arr[0...4]
 // and place it at beginning
 11 25 12 22 64

 // Find the minimum element in arr[1...4]
 // and place it at beginning of arr[1...4]
 11 12 25 22 64

 // Find the minimum element in arr[2...4]
 // and place it at beginning of arr[2...4]
 11 12 22 25 64

 // Find the minimum element in arr[3...4]
 // and place it at beginning of arr[3...4]
 11 12 22 25 64

 Time Complexity: O(n2) as there are two nested loops.

 Space Complexity: O(1)
 The good thing about selection sort is it never makes more than O(n)
 swaps and can be useful when memory write is a costly operation.

 In Place : Yes, it does not require extra space.
 */
public class SelectionSort {

    private static void selectionSort(int[] array) {
        //Select first element
        //Find min from rest of element
        //If found swap

        for (int i = 0; i < array.length - 1; i++) {
            //Select
            int minIndex = i;

            //Find
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            //Swap
            swap(array, minIndex, i);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11};
        System.out.println("Original Array");
        System.out.println(Arrays.toString(array));

        selectionSort(array);
        System.out.println("After sorting");
        System.out.println(Arrays.toString(array));
    }
}
