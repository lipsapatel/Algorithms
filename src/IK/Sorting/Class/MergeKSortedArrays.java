package IK.Sorting.Class;

import java.util.Arrays;
import java.util.Collections;
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

In next K lines, ith line should contain N space separated integers, denoting content of ith array of K input arrays, where jth element in this ith line is nothing
 but arr[i][j], i.e. value at index j of ith array, 0-based indexing.

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

 Approach:

 1) Create min Heap of size k.
 2) Create HeapNode with value, listIndex and elementIndex
 3) Add first element from all list.
 4) Poll minimum
 5) Offer element for that listIndex and elementIndex + 1

 Time complexity: O(K + NKlog(K)) - where K is time to build heap and O(logK) is time to delete from heap
 Space Complexity: O(K) for heap and Output result = O(NK)

 Merge K sorted linked list and return a single sorted linked list
 1) Create Min Heap of size K
 2) Add first List Node from array of linked list
 3) Poll minimum
 4) Add to result linked list
 5) Offer the next node in linked list

 Time Complexity: O(K + NK(logK)) where O(K) = time to build heap and O(logK) - delete from heap
 Space Complexity: O(K) for heap and O(NK) for result
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

        PriorityQueue<HeapNode> heap = new PriorityQueue<>(m);

        boolean isIncreasing = false;
        boolean isDecreasing = false;

        //Need to do this for all k arrays because if they have duplicates then its neither increasing or decreasing
        //we need to reach a point where one element is less than or greater than other to determine if its increasing or decreasing.
        for (int i = 0; i < m; i++) {
            if(arr[i][0] < arr[i][n - 1]) {
                isIncreasing = true;
            } else if(arr[i][0] > arr[i][n - 1]) {
                isDecreasing = true;
            }
        }

        if(isIncreasing) { //Ascending
            heap = new PriorityQueue<>(m);
        }
        if(isDecreasing){ //Descending
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

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (n1, n2) ->  n1.val - n2.val);

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
            ListNode node = minHeap.poll();
            current.next = node;
            current = node;

            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        return head.next;

    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3, 5, 7}, {2, 4, 6, 8}, {0, 9, 10, 11}};

        int[] result = mergeArrays(arr);

        System.out.println(Arrays.toString(result));

        int[][] a = {{9, 9, 9, 9}, {9, 9, 9, 9}, {9, 9, 9, 10}, {9, 9, 9, 9}};
        int[] r = mergeArrays(a);
        System.out.println(Arrays.toString(r));

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = head;
        listNodes[1] = new ListNode(3);
        listNodes[2] = new ListNode(5);

        ListNode curr = mergeKLists(listNodes);
        while(curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}
