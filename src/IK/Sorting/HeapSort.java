package IK.Sorting;

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
 * Heap data structure is always a complete binary tree also called left complete binary tree. The complete binary tree is a binary tree which is completely filled
 * from left except the last level which not be completely full.
 *
 * Full binary tree means all nodes has has two children except the leaf nodes.
 *
 * resources/CompleteVsFullBinaryTree.jpg
 *
 * Heap Property: All nodes are either greater than equal to (Max-Heap) or less than equal to (Min-Heap) than each of it's child nodes.
 * This is called heap property.
 *
 * How Heap Sort Algorithm work?
 *
 * If heap array starts at 0, the parent = (i - 1)/2 and Left Child = 2 * position + 1 and Right child =  2 * position + 2
 *
 * 1) The sorting using heap sort is done in place
 * 2) Insert into which is create min heap structure.
 * 3) For that iterate the array and perform bubble up operation.
 * 4) To sort, swap the first min value with the last value in heap
 * 5) Then perform sink down operation
 * 6) When you swap it, reduce heap size by 1 and perform sinkdown on reduced heap size
 * 7) Do that till heap size becomes less than 0.
 *
 * Bubble up and sink down operations takes O(logn) time
 * So insert takes O(nlogn) time and sort takes O(nlogn) time.
 *
 * The resulting array will be in descending order so you can reverse the array to get it in ascending order.
 *
 * Time Complexity: O(nlogn) in all cases
 * Space Complexity: O(1)
 * Recursive call stack: O(logn)
 *
 * Priority queue is java implementation of heap data structure.
 *
 * Heap data structure is used for following operation
 * 1) Insert
 * 2) Extract min or max which equals to delete
 *
 * In place algorithm
 * Heap sort algorithm has limited uses because quick sort and mergesort are better in practice.
 * Heap sort is not cache friendly.
 * Heap sort is not stable.
 *
 * Time complexity and space complexity: Space complexity of merge sort is O(n) while that of heapsort is O(1) since it does in place.
 * If space is not an issue, merge sort is faster then heap sort and quick sort.
 * Quick sort runs faster than heap sort
 *
 * Is heap sort stable? no
 *
 * Heapsort has the benefit of having a worst running case of O(n*log(n)) so in cases where quicksort is likely
 * to be performing poorly (mostly sorted data sets generally) heapsort is much preferred.
 *
 * Merge sort on arrays has considerably better data cache performance, often outperforming heapsort on modern desktop computers
 * because merge sort frequently accesses contiguous memory locations (good locality of reference); heapsort references are spread throughout the heap.
 */
public class HeapSort {

/*    //This builds the heap in O(n) time instead of O(nlogn)
    private static void heapSort(int[] array) {

        //Given array is a heap
        int size = array.length;

        //Transforming it to max heap
        // Amortized Build max heap - O(n)
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
    }*/

    //This creates min heap
    private static void heapSort(int[] a) {

        //Insert
        for (int i = 0; i < a.length; i++) { //O(n)
            bubbleUp(a, i); //O(logn)
        }

        //Sort it by swapping the min with last heap element and reduces the size of heap with each swap
        //This will give array in descending order
        //After you swap the min element you need to perform sinkDown
        sort(a);

        //The array is in descending order
        reverse(a);

    }

    private static void bubbleUp(int[] a, int position) {

        //Parent = (i -1)/2
        while((position - 1) >= 0 && a[position] < a[(position - 1)/2]) {
            swap(a, position, (position - 1)/2);
            position = (position - 1)/2;
        }
    }

    private static void sort(int[] a) {

        int hs = a.length - 1;

        while(hs >= 0) { //O(n)
            swap(a, 0, hs);
            hs--;
            sinkDown(a, 0, hs); //O(logn)
        }
    }

    private static void sinkDown(int[] a, int position, int hs) {

        int smallestPosition = position;
        int left = 2 * position + 1;
        int right = 2 * position + 2;

        //Left
        if (left <= hs && a[left] < a[smallestPosition]) {
            smallestPosition = left;
        }

        //Right
        if(right <= hs && a[right] < a[smallestPosition]) {
            smallestPosition = right;
        }

        if(smallestPosition != position) {
            swap(a, smallestPosition, position);
            sinkDown(a, smallestPosition, hs);
        }
    }

    private static void reverse(int[] a) {
        for(int i = 0; i < a.length/2; i++) {
            swap(a, i, a.length - 1 - i);
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
