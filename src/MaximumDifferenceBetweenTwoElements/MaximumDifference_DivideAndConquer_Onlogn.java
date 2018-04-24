package MaximumDifferenceBetweenTwoElements;

/**
 * Given an array A write an algorithm to find maximum difference between two elements
 * where larger element appears after smaller element
 * A[j] > A[i]
 * j > i
 * A[j] - A[i] is maximum
 *
 * int[] array = {2, 5, 1, 7, 3, 9, 5}
 * Output = 8
 *
 * int[] array = {22, 12, 2, 5, 19}
 * Output: 17
 *
 * Divide and Conquer:
 * 1) Divide input array into two parts, left half and right half
 * 2) Both the index i and j are in the left half
 * 3) Both the index i and j are in the right half
 * 4) i is in the left half and j is in the right half
 * 5) Final answer is maximum of all 3
 *
 * Time Complexity: O(nlogn)
 *
 */
public class MaximumDifference_DivideAndConquer_Onlogn {

    private static int maximumDifference(int[] array, int start, int end) {

        if (start >= end) {
            return -1;
        }

        int mid = start + (end - start)/2;

        int leftDifference = maximumDifference(array, start, mid);
        int rightDifference = maximumDifference(array, mid + 1, end);

        int leftMinimum = getLeftMinimum(array, start, mid);
        int rightMaximum = getRightMaximum(array, mid, end);

        int centerDifference = rightMaximum - leftMinimum;

        return Math.max(centerDifference, Math.max(leftDifference, rightDifference));
    }

    private static int getLeftMinimum(int[] array, int i, int j) {
        int min = array[i];

        for (int k = i+1; k <= j; k++) {
            if (array[k] < min) {
                min = array[k];
            }
        }

        return min;
    }

    private static int getRightMaximum(int[] array, int i, int j) {
        int max = array[i];

        for (int k = i+1; k <= j; k++) {
            if (array[k] > max) {
                max = array[k];
            }
        }

        return max;
    }

    public static void main(String[] args) {

        int[] array = {2, 5, 1, 7, 3, 9, 5};

        System.out.println("The maximum difference is " + maximumDifference(array, 0, array.length - 1));

        int[] array1 = {22, 12, 2, 5, 19};

        System.out.println("The maximum difference is " + maximumDifference(array1, 0, array1.length - 1));

        int[] array2 = {22};

        System.out.println("The maximum difference is " + maximumDifference(array2, 0, array2.length - 1));
    }
}
