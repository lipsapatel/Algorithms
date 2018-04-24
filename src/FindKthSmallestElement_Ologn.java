import java.util.PriorityQueue;

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
 * Time Complexity: O(logn)
 */
public class FindKthSmallestElement_Ologn {

    private static int findKthSmallestElement(int[] array, int k) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();

        for (int i = 0; i < array.length; i++) {
            priorityQueue.offer(array[i]);
        }

        int kthSmallestElement = -1;
        while(k > 0) {
            kthSmallestElement = priorityQueue.poll();
            k--;
        }

        return kthSmallestElement;
    }

    public static void main(String[] args) {

        int[] array = {1, 6, 2, 9, 4, 3, 8};
        System.out.println("The 4th smallest element is " + findKthSmallestElement(array, 4));

        int[] array1 = {1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element is " + findKthSmallestElement(array1, 4));
    }
}
