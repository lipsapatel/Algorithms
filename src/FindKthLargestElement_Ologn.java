import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Given an array of integers, find the kth largest element in array.
 *
 * int[] array = {1, 2, 10, 20, 40, 32, 44, 51, 6}
 * k = 4
 * Kth smallest element = 32
 *
 * Use Min Heap
 * Implement using Priority Queue
 * Insert all elements in the Priority Queue
 * Extract k max elements from Priority Queue
 * The last max (Kth) element extracted will be the Kth Largest element in the array.
 *
 * Time Complexity: O((n + k)*logk)
 */
public class FindKthLargestElement_Ologn {

    private static PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(Collections.reverseOrder());

    private static int findKthLargestElement(int[] array, int k) {

        for (int i = 0; i < array.length; i++) { //n
            priorityQueue.offer(array[i]); //logk
        }

        int kthLargestElement = -1;
        while (k > 0) { //k
            kthLargestElement = priorityQueue.poll(); //logk
            k--;
        }
        return kthLargestElement;
    }

    private static int[] topKPriorityQueue(int[] arr, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(k); //min heap

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        Integer[] array = set.toArray(new Integer[set.size()]);

        for (int i = 0; i < array.length; i++) {
            pq.offer(array[i]);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int j = 0;
        int[] result = new int[pq.size()];
        while (!pq.isEmpty()) {
            result[j] = pq.poll();
            j++;
        }
        return result;
    }

    private static int[] topK(int[] arr, int k) {

        //Don't want duplicates to add to set
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        //Convert set to array
        Integer[] array = set.toArray(new Integer[set.size()]);

        //Find k largest means, n-k+1 smallest
        int index = findKthLargestElementUsingQuickSelect(array, 0, array.length - 1, array.length - k + 1);


        int[] result = new int[array.length - index];
        int j = 0;
        for (int i = index; i < array.length; i++) {
            result[j] = array[i];
            j++;
        }
        return result;
    }


    private static int findKthLargestElementUsingQuickSelect(Integer[] array, int low, int high, int k) {
        //CHECKS
        //In your original method check if array is null or empty
        //k is from 1 to n

        //If k is smaller than number of elements in array
        if (k > 0 && k <= array.length) {
            int pi = partition(array, low, high);

            if (pi == k - 1) {
                //return index
                return pi;
            }

            if (pi > k - 1) {
                return findKthLargestElementUsingQuickSelect(array, low, pi - 1, k);
            } else {
                return findKthLargestElementUsingQuickSelect(array, pi + 1, high, k);
            }
        } else {
            return 0;
        }
    }

    private static int partition(Integer[] array, int low, int high) { //O(n)
        //Choosing pivot randomly also takes some time
        //Random random = new Random(System.currentTimeMillis());
        //int p = low + random.nextInt(high - low + 1);
        //swap(a, p, high);

        //Selecting pivot using median of three is faster and it balances out in case of sorted array so will not have n^2 complexity
        int middle = low + (high - low)/2;
        medianOfThree(array, low, middle, high);
        swap(array, middle, high);

        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        i++;
        swap(array, i, high);
        return i;
    }

    //Sort low, mid and high
    private static void medianOfThree(Integer[] a, int low, int middle, int high) {

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

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }



    public static void main(String[] args) {
        int[] array = {1, 2, 10, 20, 40, 32, 44, 51, 6};

        int[] array1 = {4,
                8,
                9,
                6,
                6,
                2,
                10,
                2,
                8,
                1,
                2};
        System.out.println("The 5th largest element using quick select is:" + Arrays.toString(topK(array1, 5))); //10,9,8,8,7,5,5,4,4
        System.out.println("The 5th largest element using priority queue is:" + Arrays.toString(topKPriorityQueue(array1, 5))); //10,9,8,8,7,5,5,4,4
        System.out.println("The 4th largest element is " + findKthLargestElement(array, 4));
    }
}
