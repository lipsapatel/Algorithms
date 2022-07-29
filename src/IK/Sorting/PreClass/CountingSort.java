package IK.Sorting.PreClass;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
 * *********************************************************************************
 *
 * Given array whose elements are in the range of -4 * 10^5 and 4 * 10^5.
 *
 * Approach
 * 1) Calculate range
 * 2) For calculating range, find min and max
 * 3) Range = max - min + 1; so if min = 5 and max = 7 then range is 7 - 5 + 1 = 3
 * 4) Create count array of size range
 * 5) Iterate the array and increment count for val - min
 * 6) Iterate count array
 * 7) Set array value to i + min and decrement count and increment j
 *
 * Time Complexity: O(n + k) where k = range of elements
 * Space Complexity: O(k)
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

    private static void countingSortRangeUnknown(List<Integer> a) {
        int min = Collections.min(a);
        int max = Collections.max(a);

        int range = max - min + 1;

        int[] c = new int[range];

        //Add count
        for(Integer value: a) {
            c[value - min]++;
        }

        //Iterate count array
        int j = 0;
        for(int i = 0; i < c.length; i++) {
            int count = c[i];

            while(count > 0) {
                a.set(j, i + min);
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

        ArrayList<Integer> a = new ArrayList<>();
        a.add(12);
        a.add(134);
        a.add(34);
        a.add(34);
        a.add(45);
        a.add(1244);

        countingSortRangeUnknown(a);

        System.out.println("Sorted array " + a.toString());
    }
}
