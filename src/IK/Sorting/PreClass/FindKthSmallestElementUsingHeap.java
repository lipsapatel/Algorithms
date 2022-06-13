package IK.Sorting.PreClass;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given an array of integers, find the kth smallest element in array.
 *
 * int[] array = {1, 2, 10, 20, 40, 32, 44, 51, 6}
 * k = 4
 * Kth smallest element = 10
 *
 * Approach:
 *
 * 1) Heap approach is used when we have stream of data coming and we need to keep finding Kth smallest element at any point
 * 2) Implement using max Priority Queue - default is min
 * 3) Insert all elements in the Priority Queue
 * 4) If max element in priority queue > than incoming element
 * 5) Then extract max and insert incoming min element.
 * 6) At last we will have first K smallest elements in priority queue.
 * 7) The max element in priority queue at end will be kth smallest element.
 *
 * This requires extra O(K) space and time complexity is not better than quick select.
 * If you build in place heap then its better to use quick select.
 * If you are given that the array cannot be modified then use extra O(k) space to build heap.
 *
 * Time Complexity:O(nlogk)
 * Space Complexity: O(k)
 *
 */
public class FindKthSmallestElementUsingHeap {

    //Space: O(k) extra space.
    private static int findKthSmallestElement(int[] array, int k) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Collections.reverseOrder()); //max priority queue

        for (int i = 0; i < array.length; i++) { //n

            priorityQueue.offer(array[i]); //logk
            if (priorityQueue.size() > k) {
                priorityQueue.poll(); //logk
            }
        }

        return priorityQueue.peek();
    }

    //Min and Max heap complexity is O(n-k + klog(n - k)) where k = n - k
    /**
     * Build Heap TC = O(k)
     * Extract Min = klog(n-k)
     * Total TC = O((n-k) + klog(n-k))
     * SC = O(1)
     * @param a - array
     * @param k - kth smallest element
     * @return - kth smallest element
     */
    private static int findKthSmallestElementUsingMinHeap(int[] a, int k) {

        //Build the min heap of size k
        buildHeapMin(a, k); //O(k)

        for (int i = k; i < a.length; i++) { //O(n - k) where  k = n- k ; k
            if (a[i] > a[0]) { //keeping k larger elements
                a[0] = a[i];
                heapifyMin(a, k, 0); //O(logk)
            }

        }
        return a[0]; //return min - extract min or delete min
    }

    private static void heapifyMin(int[] array, int size, int i) {

        int smallestElementPosition = i;
        int leftChildPosition = 2 * i + 1;
        int rightChildPosition = 2 * i + 2;

        if (leftChildPosition < size && array[leftChildPosition] < array[smallestElementPosition]) {
            smallestElementPosition = leftChildPosition;
        }

        if (rightChildPosition < size && array[rightChildPosition] < array[smallestElementPosition]) {
            smallestElementPosition = rightChildPosition;
        }

        if (smallestElementPosition != i) {
            swap(array, i, smallestElementPosition);
            heapifyMin(array, size, smallestElementPosition);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //Time Complexity: O(k + (n - k)logk)
    //SC: O(1)
    //If k = n, then just build the heap and get the  max, TC = O(n)
    //Build max heap of for k elements, replace the larger element with smaller and then heapify for size k
    private static int findKthSmallestElementUsingMaxHeap(int[] a, int k) {

        //build max heap for k elements - O(K)
        buildHeapMax(a, k);

        for (int i = k; i < a.length; i++) { //O(n - k)
            if (a[i] < a[0]) { //keeping k smaller elements
                a[0] = a[i];
                heapifyMax(a, k, 0); //O(logk)
            }

        }
        return a[0]; //return max - extract max or delete max
    }

    private static void buildHeapMax(int[] a, int k) {
        int size = k;

        for (int i = size/2 - 1; i >= 0; i--) {
            heapifyMax(a, size, i);
        }
    }

    private static void buildHeapMin(int[] a, int k) {
        int size = k;

        for (int i = size/2 - 1; i >= 0; i--) {
            heapifyMin(a, size, i);
        }
    }

    private static void heapifyMax(int[] a, int size, int i) {
        int largestElementPosition = i;
        int leftChildPosition = 2 * i + 1;
        int rightChildPosition = 2 * i + 2;

        if (leftChildPosition < size && a[leftChildPosition] > a[largestElementPosition]) {
            largestElementPosition = leftChildPosition;
        }

        if (rightChildPosition < size && a[rightChildPosition] > a[largestElementPosition]) {
            largestElementPosition = rightChildPosition;
        }

        if (largestElementPosition != i) {
            swap(a, i, largestElementPosition);
            heapifyMax(a, size, largestElementPosition);
        }
    }

    public static void main(String[] args) {

        int[] array = {1, 6, 9, 2, 4, 3, 8};
        System.out.println("The 4th smallest element is " + findKthSmallestElement(array, 4));
        array = new int[]{1, 6, 2, 9, 4, 3, 8};
        System.out.println("The 4th smallest element using min-heap is " + findKthSmallestElementUsingMinHeap(array, array.length - 4 + 1));
        array = new int[]{1, 6, 2, 9, 4, 3, 8};
        System.out.println("The 4th smallest element using max-heap is " + findKthSmallestElementUsingMaxHeap(array, 4));

        int[] array1 = {1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element is " + findKthSmallestElement(array1, 4));
        array1 = new int[]{1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element using min-heap is " + findKthSmallestElementUsingMinHeap(array1, array1.length - 4 + 1));
        array1 = new int[]{1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element using max-heap is " + findKthSmallestElementUsingMaxHeap(array1, 4));

        //if (k <= array.length/2)
        // build max heap of size k
        //else
        //build min heap of size n - k + 1
        //For example you have 1000000
        //and k = 99999 then you don't want to build heap of almost the size of array
        //So instead you can build min heap of size n - k + 1 and find the 2st largest element.
    }
}
