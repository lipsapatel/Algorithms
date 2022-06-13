package IK.Sorting.Class;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Given an array of integers, find the kth largest element in array.
 *
 * int[] array = {1, 2, 10, 20, 40, 32, 44, 51, 6}
 * k = 4
 * Kth smallest element = 32
 *
 * Min Heap
 * 1) Create min heap of size k
 * 2) Offer elements to min heap
 * 3) If size > k, poll
 * 4) Return peek/poll min
 *
 * Time Complexity: O(nlogk)
 * Space Complexity: O(k)
 *
 * Quick Select
 * 1) Find n - k + 1 smallest
 * 2) Create partition
 * 3) If pivot == k - 1 return a[pivot]
 * 3) If k - 1 < pivot recurse left (low, pivot - 1)
 * 4) else recurse right (pivot + 1, high)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class FindKthLargestElementUsingHeapAndQuickSelect {

    private static int findKthLargestElementUsingMinHeap(int[] a, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);

        for(int i = 0; i < a.length; i++) {
            pq.offer(a[i]);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    private static int findKthLargestElementUsingQuickSelect(int[] a, int k) {
        int k1 = a.length - k + 1;

        return quickSelect(a, k1, 0, a.length - 1);
    }

    private static int quickSelect(int[] a, int k, int low, int high) {
        if(k > 0 && k <= a.length && low < high) {

            int pi = partition(a, low, high);

            if(k - 1 == pi) {
                return a[pi];
            } else if (k - 1 < pi) {
                return quickSelect(a, k, low, pi - 1);
            } else {
                return quickSelect(a, k, pi + 1, high);
            }
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private static int partition(int[] a, int low, int high) {
        int pi = new Random().nextInt(high - low + 1) + 1;
        swap(a, high, pi);

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

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }



    public static void main(String[] args) {
        int[] array = {1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th largest element using min heap is " + findKthLargestElementUsingMinHeap(array, 4));
        System.out.println("The 4th largest element using quick select is " + findKthLargestElementUsingQuickSelect(array, 4));
    }
}
