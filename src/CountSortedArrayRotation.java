/**
 * Count how many times sorted array is rotated in anticlock wise, right side
 *
 * 11, 12, 15, 18, 2, 5, 6, 8
 *
 * Rotated 4 times
 * return the index of min element
 *
 * Work only if there are no duplicates in array
 *
 * TC = O(logn)
 */
public class CountSortedArrayRotation {

    private static int countRotationIterative(int[] array) {

        int n = array.length;
        int low = 0;
        int high = array.length - 1;

        while(low <= high) {

            int mid = low + (high - low)/2;

            //Case 1 - if the array is already sorted
            if (array[low] < array[high]) {
                return low;
            }

            //Case 2 - If we find the min element
            int next = (mid + 1) % n; //We don't run past the array
            int prev = (mid - 1 + n) % n; //We don't go negative
            if (array[mid] <= array[next] && array[mid] <= array[prev]) { //check if we find the min element, left and right should be greater
                return mid;
            }

            //Case 3 = right sequence is sorted
            if (array[mid] <= array[high]) {
                //left
                high = mid - 1;
            }

            //Case 4 - left sequence is sorted
            if (array[mid] >= array[low]) {
                //right
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int countRotationRecursive(int[] array, int low, int high, int result, int n) {

        if (low > high) {
            return result;
        } else {
            //Case 1 - Array is already sorted
            if (array[low] < array[high]) {
                return low;
            }

            int mid = low + (high - low)/2;

            //Case 2 - if we found the min element
            int next = (mid + 1) % n; //We don't run past the array
            int prev = (mid - 1 + n) % n; //We don't go negative
            if (array[mid] <= array[next] && array[mid] <= array[prev]) {
                return mid;
            }

            //Case 3
            if (array[mid] <= array[high]) {
                //left
                result = countRotationRecursive(array, low, mid - 1, result, n);
            }

            //Case 4
            if (array[mid] >= array[low]) {
                //right
                result = countRotationRecursive(array, mid + 1, high, result, n);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {11, 12, 15, 18, 2, 5, 6, 8};
        int rotation = countRotationIterative(array);

        if (rotation == -1) {
            System.out.println("We have invalid scenario");
        }
        System.out.println("Array is rotated: " + rotation);

        rotation = countRotationRecursive(array, 0, array.length - 1, -1, array.length);
        if (rotation == -1) {
            System.out.println("We have an invalid scenario");
        }
        System.out.println("Array is rotated using recursion: " + rotation);
    }
}
