package IK.Sorting.HomeWork1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Top K
 * You are given an array of integers, arr, of size n, which is analogous to a continuous stream of integers input.
 * Your task is to find K largest elements from a given stream of numbers.
 *
 * By definition, we don't know the size of the input stream. Hence, produce K largest elements seen so far, at any given time.
 * For repeated numbers, return them only once.
 *
 * If there are less than K distinct elements in arr, return all of them.
 *
 *     Don't rely on size of input array arr.
 *     Feel free to use built-in functions if you need a specific data-structure.
 *
 *
 * Example One
 * Input: arr = [1, 5, 4, 4, 2]; K = 2
 * Output: [4, 5]
 *
 * Example Two
 * Input: arr = [1, 5, 1, 5, 1]; K = 3
 *
 * Output: [5, 1]
 *
 * Notes
 * Input Parameters: There is only one argument: Integer array arr.
 * Output: Return an integer array res, containing K largest elements. If there are less than K unique elements, return all of them.
 * Order of elements in res does not matter.
 *
 * Constraints:
 *
 *     1 <= n <= 10^5
 *     1 <= K <= 10^5
 *     arr may contain duplicate numbers.
 *     arr may or may not be sorted
 *
 * Custom Input
 * Input Format: The first line of input should contain an integer n, denoting size of input array arr.
 * In next n lines, ith line should contain an integer arr[i], denoting a value at index i of arr.
 *
 * In the next line, there should be an integer, denoting value of K.
 * If n = 5, arr = [1, 5, 4, 4, 2] and K = 2, then input should be:
 *
 * 5
 *
 * 1
 *
 * 5
 *
 * 4
 *
 * 4
 *
 * 2
 *
 * 2
 *
 * Output Format: Let’s denote size of res as m, where res is the array of integers returned by solution function.
 *
 * Then, there will be m lines of output, where ith line contains an integer res[i], denoting value at index i of res.
 *
 * For input n = 5, arr = [1, 5, 4, 4, 2] and K = 2, output will be:
 *
 * 4
 *
 * 5
 *
 * Heap Approach
 * 1) Create min heap of size k
 * 2) Add all elements to set to remove duplicates
 * 3) Add elements to heap.
 * 4) If heap size > k, remove from heap
 * 5) Return all elements from heap
 *
 * Time Complexity: (nlogk)
 * Space Complexity: O(k)
 *
 * Quick Select Approach
 * 1) Add elements to set to remove duplicates
 * 2) Convert set to array
 * 3) Create partition
 * 4) Find n - k + 1 smallest element
 * 5) If pi == k - 1 return pi
 * 6) If k - 1 < pi, recurse left (low, pi - 1)
 * 7) If k - 1 > pi, recurse right (pi + 1, right)
 * 8) Return all elements from pi to end of array
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * There will be O(k) space for result array in both approach
 */
public class TopkLargest {


    private static int[] topKHeap(int[] arr, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(k); //min heap

        //Remove duplicates
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        for(int val: set) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }

        int index = 0;
        int[] result = new int[k];
        while(!pq.isEmpty()) {
            result[index] = pq.poll();
            index++;
        }
        return result;
    }

    private static int[] topKQuickSelect(int[] arr, int k) {

        //Don't want duplicates so add to set to remove duplicates
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        //Convert set to array
        Integer[] array = set.toArray(new Integer[set.size()]);

        //Find k largest means, n-k+1 smallest
        //After removing duplicates, array elements can be less so k1 = n - k + 1 could go less than 1
        int kthIndex = findKthLargestElementUsingQuickSelect(array, 0, array.length - 1, array.length - k + 1);

        int[] result = new int[array.length - kthIndex];
        int index = 0;
        for (int i = kthIndex; i < array.length; i++) {
            result[index] = array[i];
            index++;
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
            return 0; // If k is less than 1 then return all array elements so print array from 0 index position
        }
    }

    private static int partition(Integer[] array, int low, int high) { //O(n)
        //Choosing pivot randomly also takes some time
        //Random random = new Random(System.currentTimeMillis());
        int p = low + new Random().nextInt(high - low + 1);
        swap(array, p, high);

        //Selecting pivot using median of three is faster and it balances out in case of sorted array so will not have n^2 complexity
        //int middle = low + (high - low)/2;
        //medianOfThree(array, low, middle, high);
        //swap(array, middle, high);

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
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

    private static void swap(Integer[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {

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
        System.out.println("The 5 largest elements using quick select is:" + Arrays.toString(topKQuickSelect(array1, 9)));
        System.out.println("The 5 largest elements using priority queue/Heap is:" + Arrays.toString(topKHeap(array1, 5))); //1, 2, 4, 6, 8, 9, 10
    }
}
