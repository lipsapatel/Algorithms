package IK.Sorting.PreClass;

import java.util.Arrays;

/**
 * Counting sort is a sorting algorithm which sorts the integers given in specific range.
 * Counting sort is a linear time algorithm assuming that k is some constant order of n and not n^2.
 *
 * Approach:
 *
 * 1) We are assuming the range of integers is length of array. For array length of 5, the range is 5
 * 2) It can have all the elements from 0 to 5.
 * 3) Create count array which contains count of elements.
 * 4) Iterate the count array and while count of element is > 0, add that element to array that many times.
 * 5) Decrement the count
 *
 * Time Complexity: O(n + k) when the elements are in the range from 0 to k
 * Space Complexity O(k)
 * Stable: No But can be made stable by storing elements in FIFO order.
 *
 */
public class CountingSort {

    private static void countingSort(int[] a) {
        int[] c = new int[a.length + 1];

        for (int i = 0; i < a.length; i++) {
            c[a[i]] = c[a[i]] + 1;
        }

        int j = 0;
        for (int i = 0; i < c.length; i++) {
            int count = c[i];

            while (count > 0) {
                a[j] = i;
                j++;
                count--;
            }
        }
    }

    public static void main(String[] args) {
        int input[] = { 2, 0, 4, 5, 7, 1, 1, 8, 9, 10, 11, 14, 15, 3, 2, 4 };

        System.out.println("Orginal Array " + Arrays.toString(input));

        countingSort(input);
        System.out.println("Sorted array " + Arrays.toString(input));
    }
}
