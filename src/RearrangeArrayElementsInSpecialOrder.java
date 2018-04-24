import java.util.Arrays;

/**
 * Divide and Conquer - Rearrange array elements in special order
 *
 * Given an array of integers of size 2n, write an algorithm to arrange them such that first n
 * elements and last n elements are setup in alternative manner.
 *
 * For Example: n = 3 and 2n = {x1, x2, x3, y1, y2, y3}
 * Output: {x1, y1, x2, y2, x3, y3}
 *
 * Example:
 *
 * int[] array = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10}
 * n = 5
 * Output: {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
 *
 * Approaches:
 *
 * Brute Force Approach:
 *
 * One by one shift all the elements from second half of the array to their correct position
 * in the left half of the array.
 *
 * Time Complexity: O(n2)
 *
 * resources/RearrangeArrayElementsInSpecialOrderBruteForce.png
 *
 * Divide and Conquer Approach:
 *
 * 1) This solution will only only if total number of elements is 2^i so the total elements are
 * either 2, 4, 8, 16...
 * 2) Total length is 2n, take n elements around middle element.
 * 3) Swap n/2 elements on the left side from the middle element with
 * n/2 elements on the right side from the middle element.
 * 4) Now divide array into 2 parts, first n elements and last n elements
 * 5) Repeat step 2 and 3 recursively
 *
 * Time Complexity: O(nlogn)
 *
 * resources/RearrangeArrayDivideAndConquer.png
 */
public class RearrangeArrayElementsInSpecialOrder {

    private static void rearrangeArrayBruteForce(int[] array, int n) {

        int start = 0;
        int end = array.length - 1;
        int mid = start + (end - start)/2;

        while (start < mid && mid < end) {

            int left_index = start + 1;
            int right_index = mid + 1;

            while (left_index < right_index) {

                swap(array, right_index, right_index - 1); //shift one right
                right_index--;
            }

            start = start + 2;
            mid = mid + 1;
        }

        for (int i = 0; i < 2*n; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private static void swap(int[] array, int m, int n) {

        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }

    private static void rearrangeArrayUsingDivideAndConquer(int[] array, int start, int end) {

        if (start >= end) {
            return;
        }

        int mid = start + (end - start)/2;
        int x = 1 + (start + mid)/2;
        int y = mid + 1;

        for (int i = x, j = y; i <= mid; i++, j++) {
            swap(array, i, j);
        }

        rearrangeArrayUsingDivideAndConquer(array, start, mid);
        rearrangeArrayUsingDivideAndConquer(array, mid + 1, end);
    }

    public static void main(String[] args) {

        int[] array = {1,3,5,7,9,2,4,6,8,10};

        System.out.println("Rearrange Array using Brute Force: ");
        rearrangeArrayBruteForce(array, 5);

        System.out.println();

        int[] array1 = {1,3,5,7,2,4,6,8};
        rearrangeArrayUsingDivideAndConquer(array1, 0, array1.length - 1);
        System.out.println("Rearrange Array using Divide and Conquer: ");
        System.out.println(Arrays.toString(array1));
    }
}
