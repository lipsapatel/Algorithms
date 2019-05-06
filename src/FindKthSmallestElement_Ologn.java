import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Given an array of integers, find the kth smallest element in array.
 *
 * int[] array = {1, 2, 10, 20, 40, 32, 44, 51, 6}
 * k = 4
 * Kth smallest element = 10
 *
 * Use Min Heap
 * Implement using Priority Queue
 * Insert all elements in the Priority Queue
 * Extract k min elements from Priority Queue
 * The last min (Kth) element extracted will be the Kth Smallest element in the array.
 *
 * Time Complexity:O(nlogk)
 *
 */
public class FindKthSmallestElement_Ologn {

    //Space: O(k) extra space.
    private static int findKthSmallestElement(int[] array, int k) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(k, Collections.reverseOrder()); //max priority queue

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

    //The average time complexity is O(n)
    //Worst Case time complexity is O(n^2)
    //n + n/2 + n/4 + ..1 = n(1+ 1/2...) = n (1/i < 2) = Work at each level is not same
    //Not to do complete quicksort, do it till pivot == kth element
    //Make recursive call to either left or right sides of pivot based on the position of pivot
    private static int findKthSmallestElementUsingQuickSelect(int[] a, int low, int high, int k) {

        //CHECKS
        //In your original method check if array is null or empty
        //k is from 1 to n

        //If k is smaller than number of elements in array
        if (k > 0 && k <= a.length) {

            int pi = partition(a, low, high);

            if (pi == k - 1) {
                return a[pi];
            }
            if (pi > k - 1) {
                return findKthSmallestElementUsingQuickSelect(a, low, pi - 1, k);
            } else {
                return findKthSmallestElementUsingQuickSelect(a, pi + 1, high, k);
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int partition(int[] a, int low, int high) { //O(n)
        //Choosing pivot randomly also takes some time
        //Random random = new Random(System.currentTimeMillis());
        //int p = low + random.nextInt(high - low + 1);
        //swap(a, p, high);

        //Selecting pivot using median of three is faster and it balances out in case of sorted array so will not have n^2 complexity
        int middle = (low + high)/2;
        medianOfThree(a, low, middle, high);
        swap(a, middle, high);

        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        i++;
        swap(a, i, high);
        return i;
    }

    //Sort low, mid and high
    private static void medianOfThree(int[] a, int low, int middle, int high) {

        if (a[middle] < a[low]) {
            swap(a, low, middle);
        }
        if (a[high] < a[low]) {
            swap(a, low, high);
        }

        if (a[high] < a[middle]) {
            swap(a, middle, high);
        }
    }

    public static void main(String[] args) {

        int[] array = {1, 6, 2, 9, 4, 3, 8};
        System.out.println("The 4th smallest element is " + findKthSmallestElement(array, 4));
        array = new int[]{1, 6, 2, 9, 4, 3, 8};
        System.out.println("The 4th smallest element using min-heap is " + findKthSmallestElementUsingMinHeap(array, array.length - 4 + 1));
        array = new int[]{1, 6, 2, 9, 4, 3, 8};
        System.out.println("The 4th smallest element using max-heap is " + findKthSmallestElementUsingMaxHeap(array, 4));
        array = new int[]{1, 6, 2, 9, 4, 3, 8};
        System.out.println("The 4th smallest element using quick-select is " + findKthSmallestElementUsingQuickSelect(array, 0, array.length - 1, 4));

        int[] array1 = {1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element is " + findKthSmallestElement(array1, 4));
        array1 = new int[]{1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element using min-heap is " + findKthSmallestElementUsingMinHeap(array1, array1.length - 4 + 1));
        array1 = new int[]{1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element using max-heap is " + findKthSmallestElementUsingMaxHeap(array1, 4));
        array1 = new int[]{1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element using quick-select is " + findKthSmallestElementUsingQuickSelect(array1, 0, array1.length - 1, 4));

        //if (k <= array.length/2)
        // build max heap of size k
        //else
        //build min heap of size n - k
        //For example you have 1000000
        //and k = 99999 then you don't want to build heap of almost the size of array
        //So instead you can build min heap of size n - k and find the 1st largest element.
    }
}
