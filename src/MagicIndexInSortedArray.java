/**
 * Find the magic index in sorted array such at A[i] = i
 *
 * Given a sorted array of distinct integers, find Magic Index or Fixed point in the array.
 *
 * Magic Index or Fixed Point:
 * Magic Index or Fixed Point in an array is an index i in the array such that A[i] = i
 *
 * int[] array = {-1, 0, 1, 2, 4, 10};
 * Magic Index or Fixed point is 4
 *
 * Naive Approach:
 *
 * 1) Do linear scan and find the magic index.
 * Time Complexity: O(n)
 *
 * Binary Search:
 * Time Complexity: O(logn)
 *
 * 1) mid = start + (end - start)/2
 * 2) if A[mid] == mid, then return mid
 * 3) if A[mid] > mid, then search left (A, start, mid - 1)
 * 4) if A[mid] < mid, then search right (A, mid + 1, end)
 */
public class MagicIndexInSortedArray {

    private static int findMagicIndexInSortedArray(int[] A, int start, int end) {

        if (start <= end) {

            int mid = start + (end - start)/2;

            if (mid == A[mid]) {
                return mid;
            }

            //Search left
            if (A[mid] > mid) {
                return findMagicIndexInSortedArray(A, start, mid - 1);
            } else { //Search right
                return findMagicIndexInSortedArray(A, mid + 1, end);
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] A = {-1, 0, 1, 2, 4, 10};

        System.out.println("The Magic Index or Fixed Point is " + findMagicIndexInSortedArray(A, 0, A.length - 1));
    }
}
