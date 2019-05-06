import java.util.Arrays;

/**
 * Heap Sort - Java Implementation
 *
 * What is Heap Sort?
 *
 * 1) Heap Sort is a comparison based sorting algorithm
 * 2) Heap Sort is considered as improved selection sort, it divides the input into sorted and unsorted region.
 * 3) The improvement from selection sort is to use Heap Data Structure instead of using linear search algorithm to reduce time complexity
 *
 * What is Binary Heap?
 *
 * Heap is a tree based data structure which satisfies two properties
 *
 * Shape Property:
 * Heap data structure is always a complete binary tree. The complete binary tree is a binary tree which is completely filled
 * (means all the nodes has 2 children) except the last level which not be completely full.
 *
 * Heap Property: All nodes are either greater than equal to (Max-Heap) or less than equal to (Min-Heap) than each of it's child nodes.
 * This is called heap property.
 *
 * How Heap Sort Algorithm work?
 *
 * Sorting in ascending order:
 *
 * 1) Create a max heap from the given input array
 * 2) Extract the root (it will be the maximum value in array) and replace it with the last element in the array.
 * 3) Again Heapify the root (create max heap on reduced heap)
 * 4) Repeat step 2 and 3 until entire array is sorted.
 *
 * Sorting in descending order:
 *
 * 1) Create min Heap from the given input array
 * 2) Extract root ( it will the minimum value in array) and replace it with the last element in the array
 * 3) Heapify the root of the heap with reduce size
 * 4) Repeat the steps 2 and 3 till entire array is sorted
 *
 * Time Complexity: O(nlogn) in all cases
 * Space Complexity: O(1)
 * In place algorithm
 * Heap sort algorithm has limited uses because quick sort and mergesort are better in practice.
 * Heap sort is not cache friendly.
 * Heap sort is not stable.
 */
public class HeapSort {

    private static void heapSort(int[] array) {

        //Given array is a heap
        int size = array.length;

        //Transforming it to max heap
        // Build max heap - O(n)
        for (int i = size/2 - 1; i >= 0; i--) {

            heapify(array, size, i);
        }

        //Extract the max and move it to the last location and call heapify (create max heap) on reduced heap - logn
        for (int i = size - 1; i >= 0; i--) { //n

            //Swap the first and last element
            swap(array, 0, i);

            //Call heapify on the reduced heap (create max heap out of reduced heap)
            heapify(array, i, 0); //logn
        }
    }

    //Heapify - max heap with node i
    private static void heapify(int[] array, int heapSize, int i) {

        int largestElementPosition = i;
        int leftChildPosition = 2 * i + 1;
        int rightChildPosition = 2 * i + 2;

        //If left child is larger than root
        if (leftChildPosition < heapSize && array[leftChildPosition] > array[largestElementPosition]) {
            largestElementPosition = leftChildPosition;
        }

        //If right child is larger than largest element
        if (rightChildPosition < heapSize && array[rightChildPosition] > array[largestElementPosition]) {
            largestElementPosition = rightChildPosition;
        }

        if (largestElementPosition != i) {

            //Swap both of them
            swap(array, i, largestElementPosition);

            //Make Recursive call to heapify for the subtree
            heapify(array, heapSize, largestElementPosition);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {

        int[] array = {9, 10, 5, 3, 1, 2, 6};

        System.out.println("Original array is: " + Arrays.toString(array));
        heapSort(array);

        System.out.println("Sorted array is (Heap Sort): " + Arrays.toString(array));
    }
}
