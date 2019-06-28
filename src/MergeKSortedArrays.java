import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge_K_sorted_arrays


 Problem Statement:


 This is a popular facebook problem.

 Given K sorted arrays arr, of size N each, merge them into a new array res, such that res is a sorted array.

 Assume N is very large compared to K. N may not even be known. The arrays could be just sorted streams, for instance, timestamp streams.


 All arrays might be sorted in increasing manner or decreasing manner. Sort all of them in the manner they appear in input.


 Note:

 Repeats are allowed.
 Negative numbers and zeros are allowed.
 Assume all arrays are sorted in the same order. Preserve that sort order in output.
 It is possible to find out the sort order from at least one of the arrays.


 Input/Output Format For The Function:


 Input Format:


 There is only one argument: 2D Integer array arr.

 Here, arr[i][j] denotes value at index j of ith input array, 0-based indexing. So, arr is K * N size array.


 Output Format:


 Return an integer array res, containing all elements from all individual input arrays combined.


 Input/Output Format For The Custom Input:


 Input Format:


 The first line of input should contain an integer K. The second line should contain an integer N, denoting size of each input array.

 In next K lines, ith line should contain N space separated integers, denoting content of ith array of K input arrays, where jth element in this ith line is nothing but arr[i][j], i.e. value at index j of ith array, 0-based indexing.


 If K = 3, N = 4 and arr = [

 [1, 3, 5, 7],

 [2, 4, 6, 8],

 [0, 9, 10, 11]

 ], then input should be:


 3

 4

 1 3 5 7

 2 4 6 8

 0 9 10 11


 Output Format:


 There will be (N*K) lines of output, where ith line contains an integer res[i], denoting value at index i of res.

 Here, res is the result array returned by solution function.


 For input K = 3, N = 4 and arr = [

 [1, 3, 5, 7],

 [2, 4, 6, 8],

 [0, 9, 10, 11]

 ], output will be:


 0

 1

 2

 3

 4

 5

 6

 7

 8

 9

 10

 11


 Constraints:

 1 <= N <= 500
 1 <= K <= 500
 -10^6 <= arr[i][j] <= 10^6, for all valid i,j


 Sample Test Case:


 Sample Input:


 K = 3, N =  4

 arr[][] = { {1, 3, 5, 7},

 {2, 4, 6, 8},

 {0, 9, 10, 11}} ;


 Sample Output:


 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
 */
public class MergeKSortedArrays {

    public static int[] mergeArrays(int[][] arr) {

        class HeapNode implements Comparable<HeapNode> {
            int value;
            int listIndex;
            int elementIndex;

            @Override
            public int compareTo(HeapNode node) {
                return this.value - node.value;
            }

            public HeapNode(int val, int li, int ei) {
                value = val;
                listIndex = li;
                elementIndex = ei;
            }
        }

        //Find the sorting order
        int m = arr.length;
        int n = arr[0].length;

        PriorityQueue<HeapNode> heap;

        if (arr[0][0] < arr[0][n - 1]) {
            //Ascending
            heap = new PriorityQueue<>(m);
        } else {
            //Descending
            heap = new PriorityQueue<>(m, Collections.reverseOrder());
        }

        //Create initial heap of size m with first element from all m array
        for (int i = 0; i < arr.length; i++) {
            HeapNode node = new HeapNode(arr[i][0], i, 0);

            heap.offer(node);
        }

        int[] result = new int[m * n];
        int index = 0;

        while (!heap.isEmpty()) {

            HeapNode removedNode = heap.poll();
            result[index] = removedNode.value;
            index++;

            int listIndex = removedNode.listIndex;
            int elementIndex = removedNode.elementIndex;

            if (elementIndex + 1 < arr[listIndex].length) {

                heap.offer(new HeapNode(arr[listIndex][elementIndex + 1], listIndex, elementIndex + 1));
            }
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /*
    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6


     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){

            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });

        //Insert all m nodes
        int m = lists.length;

        for (int i = 0; i < m; i++) {
            if (lists[i] != null) {
                minHeap.offer(lists[i]);
            }
        }

        ListNode head = new ListNode(0);
        ListNode current = head;

        while(!minHeap.isEmpty()) {
            current.next = minHeap.poll();
            current = current.next;

            if (current.next != null) {
                minHeap.offer(current.next);
            }
        }
        return head.next;

    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3, 5, 7}, {2, 4, 6, 8}, {0, 9, 10, 11}};

        int[] result = mergeArrays(arr);

        System.out.println(Arrays.toString(result));
    }
}
