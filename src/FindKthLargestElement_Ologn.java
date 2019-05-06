import java.util.Collections;
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
 * Time Complexity: O((n + k)*logn)
 */
public class FindKthLargestElement_Ologn {

    private static PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(Collections.reverseOrder());

    private static int findKthLargestElement(int[] array, int k) {

        for (int i = 0; i < array.length; i++) { //n
            priorityQueue.offer(array[i]); //logn
        }

        int kthLargestElement = -1;
        while (k > 0) { //k
            kthLargestElement = priorityQueue.poll(); //logn
            k--;
        }
        return kthLargestElement;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 10, 20, 40, 32, 44, 51, 6};

        System.out.println("The 4th largest element is " + findKthLargestElement(array, 4));
    }
}
