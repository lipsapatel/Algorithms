/**
 * The array has to be sorted array
 * Divide and Conquer
 * Recursive Approach
 */
public class BinarySearch {

    private static int binarySearch(int[] array, int k, int low, int high) {

        if (low > high) {
            return -1;
        } else {

            int mid = (low + high)/2;

            if (k == array[mid]) {
                return mid;
            } else if (k < array[mid]) {
                return binarySearch(array, k, low, mid - 1);
            } else {
                return binarySearch(array, k, mid + 1, high);
            }
        }
    }

    public static void main(String[] args) {

        int[] array = {3, 4, 6, 7, 8, 19, 20, 22};
        System.out.println(binarySearch(array, 19, 0, array.length - 1));
    }
}
