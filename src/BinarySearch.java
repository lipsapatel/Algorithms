/**
 * Binary Search
 * The array has to be sorted array
 * Divide and Conquer
 * Recursive Approach
 *
 * Iterative approach
 *
 * Time Complexity: O(logn)
 */
public class BinarySearch {

    private static int binarySearchRecursive(int[] array, int k, int low, int high) {

        if (low > high) {
            return -1;
        } else {

            int mid = (low + high)/2;

            if (k == array[mid]) {
                return mid;
            } else if (k < array[mid]) {
                return binarySearchRecursive(array, k, low, mid - 1);
            } else {
                return binarySearchRecursive(array, k, mid + 1, high);
            }
        }
    }

    private static int binarySearchIterative(int[] array, int k) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {

            int mid = (low + high)/2;

            if (array[mid] == k) {
                return mid;
            } else if (array[mid] > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] array = {3, 4, 6, 7, 8, 19, 20, 22};
        System.out.println(binarySearchRecursive(array, 19, 0, array.length - 1));

        System.out.println(binarySearchIterative(array, 8));
    }
}
