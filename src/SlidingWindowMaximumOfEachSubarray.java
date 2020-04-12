import java.util.Deque;
import java.util.LinkedList;

/**
 * Maximum In Sliding Window
 * Given an array of integers arr (of size n) and an integer w, find maximum number in
 * all subarrays of arr of length w.
 *
 * Imagine that n is very large and a sliding window of a smaller size w
 * is moving through arr from left to right. We need to find the maximum in
 * every position of the sliding window.
 *
 * Input:
 * arr = [1, 3, -1, -3, 5, 3, 6, 7]
 * w = 3
 *
 * Output:
 * [3, 3, 5, 5, 6, 7]
 *
 * Size of arr is 8 and so the size of the output array is n-w+1 = 8-3+1 = 6.
 * Here are all the 6 positions of the sliding window and the corresponding maximum values:
 * 1) [1 3 -1] -3 5 3 6 7. Maximum is 3.
 * 2) 1 [3 -1 -3] 5 3 6 7. Maximum is 3.
 * 3) 1 3 [-1 -3 5] 3 6 7. Maximum is 5.
 * 4) 1 3 -1 [-3 5 3] 6 7. Maximum is 5.
 * 5) 1 3 -1 -3 [5 3 6] 7. Maximum is 6.
 * 6) 1 3 -1 -3 5 [3 6 7]. Maximum is 7.
 *
 * Constraints:
 *     1 <= n <= 10^5
 *     -2 * 10^9 <= arr[i] <= 2 * 10^9
 *     1 <= w <= n
 *
 * Track maximum of each subarray of size k
 *
 * Given an array and integer k, write an algorithm to find maximum element in each subarray
 * of size k.
 *
 * Example:
 *
 * int[] array = {1, 2, 3, 2, 4, 1, 5, 6, 1}
 * int k = 3
 *
 * Output: 3, 3, 4, 4, 5, 6, 6
 *
 * Subarrays -
 *
 * [1, 2, 3] = max = 3
 * [2, 3, 2] = max = 3
 * [3, 2, 4] = max = 4
 * [2, 4, 1] = max = 4
 * [4, 1, 5] = max = 5
 * [1, 5, 6] = max = 6
 * [5, 6, 1] = max = 6
 *
 * Approach:
 *
 * Two Nested Loops:
 *
 * 1) Have 2 Nested loops
 * 2) Have outer loop iterate through the array
 * 3) Inner loop iterate the k elements and keeps track of maximum element.
 *
 * Time Complexity: O(nk)
 * where n = size of array
 * k = size of subarray
 *
 * Approach: Using Deque
 *
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 *
 * 1) Use double ended queue
 * 2) Store first k elements in queue
 * 3) Remove the smaller element starting from last and add the element at the last
 *
 * 4) For the rest of the element, remove the element which are out the sliding window starting
 * from first
 * 5) Remove the element which are smaller starting from last and add the element at the last.
 * 6) Print the first element of deque.
 * 8) Deque will always have data for max k elements. Initially create deque with first k elements
 * and then slide the window by one element at a time and discard the data which falls outside
 * the window.
 *
 * resources/SlidingWindowMaximum.jpg
 *
 * Example:
 *
 * int [] nums = { 11,12, 13, 12, 14, 11, 10, 9};
 K = 3
 Create deque for first k elements
 int [] nums = { 11,12, 13, 12, 14, 11, 10, 9};
 Deque:		Output:
 __________________________________
 int [] nums = { 11, 12, 13, 12, 14, 11, 10, 9};
 NOTE : we are adding the array index, not element
 Deque: 11		Output:
 __________________________________
 int [] nums = { 11, 12, 13, 12, 14, 11, 10, 9};
 12>11, remove 11 from deque and add 12’s index at the end
 Deque: 12		Output:
 __________________________________
 int [] nums = { 11, 12, 13, 12, 14, 11, 10, 9};
 13>12, remove 12 from deque and add 13’s index at the end
 Deque: 13		Output: 13  (First index in deque)
 At this point our first window with k elements is prepared. Now we will start discarding
 the elements from the deque which falls outside the window.
 __________________________________
 int [] nums = { 11, 12, 13, 12, 14, 11, 10, 9};
 2. 12<13, add 12’s index at the end
 Deque: 13, 12              Output: 13 13
 __________________________________
 int [] nums = { 11, 12, 13, 12, 14, 11, 10, 9};
  2. 14>12, remove 12 and 14>13, remove 13. Add 14 at the end
 Deque: 14 		Output: 13 13 14
 __________________________________
 int [] nums = { 11, 12, 13, 12, 14, 11, 10, 9};
  2. 11< 14, Add 11 at the end
 Deque: 14 11 		Output: 13 13 14 14
 __________________________________
 int [] nums = { 11, 12, 13, 12, 14, 11, 10, 9};
  2. 10< 11, Add 10 at the end
 Deque: 14 11 10		Output: 13 13 14 14 14
 __________________________________
 int [] nums = { 11, 12, 13, 12, 14, 11, 10, 9};
 1. 14 will be removed from deque.
 2. Deque: 11 10
 3. 9< 10, Add 9 at the end
 Deque: 11 10 9		Output: 13 13 14 14 14 11
 *
 */
public class SlidingWindowMaximumOfEachSubarray {

    private static void slidingWindowFindMaxInEachSubarray(int[] array, int k) {

        for (int i = 0; i <= array.length - k; i++) {
            int max = array[i];

            for (int j = 1; j < k; j++) {

                max = Math.max(max, array[i + j]);
            }

            System.out.print(max + " ");
        }
    }

    private static int[] slidingWindowDequeFindMaxInEachSubarray(int[] array, int k) {

        int[] result = new int[array.length - k + 1];
        int index = 0;

        //Create Deque
        Deque<Integer> deque = new LinkedList<>();

        //For first k elements
        for (int i = 0; i < k; i++) {

            //While deque is not empty and last element is smaller, remove it
            while(!deque.isEmpty() && deque.peekLast() <= array[i]) {
                deque.removeLast();
            }

            deque.addLast(array[i]);
        }

        System.out.print(deque.peekFirst() + " ");
        result[index] = deque.peekFirst();
        index++;

        //For rest of the elements
        for (int i = k; i < array.length; i++) {

            //If deque is not empty and first index is out of window, remove it
            if (!deque.isEmpty() && deque.peekFirst() == array[i - k]) {
                deque.removeFirst();
            }

            //While deque is not empty and the last element is smaller, remove it
            while (!deque.isEmpty() && deque.peekLast() < array[i]) {
                deque.removeLast();
            }

            deque.addLast(array[i]);

            //Print the first element of deque, which will be the max in subarray
            System.out.print(deque.peekFirst() + " ");
            result[index] = deque.peekFirst();
            index++;
        }

        return result;
    }

    public static void main(String[] args) {

        int [] array = {1, 2, 3, 2, 4, 1, 5, 6, 1};
        int k = 3;
        slidingWindowFindMaxInEachSubarray(array, k);

        System.out.println();
        slidingWindowDequeFindMaxInEachSubarray(array, k);

        System.out.println();
        int[] array1 = {-7,-8,7,5,7,1,6,0};
        k = 4;
        slidingWindowDequeFindMaxInEachSubarray(array1, k);
    }
}
