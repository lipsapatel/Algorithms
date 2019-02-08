/**
 * Find an element in circular sorted array
 *
 * 12, 14, 18, 21, 3, 6, 8, 9
 *
 * 1) Case 1: A[mid] = x
 * 2) Case 2: Check if the right sequence is sorted
 *    A[mid] <= A[high] and A[mid] < x <= A[high]
 *    low = mid + 1;
 *
 *    search left
 *    high = mid -1;
 *
 * 3) Case 3: Check if the left sequence is sorted
 *            A[mid] >= A[low] && A[low] <= x < A[mid]
 *            high = mid - 1;
 *
 *    search right
 *    low = mid + 1;
 *
 *  Time Complexity: O(logn)
 *
 *  No duplicates
 */
public class SearchElementInCircularSortedArray {

    private static int findElementInCircularSortedArrayIterative(int[] array, int x) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {

            int mid = low + (high - low)/2;

            //Case 1: If mid is the element
            if (array[mid] == x) {
                return mid;
            }

            //Case 2: Check if the right sequence is sorted and element is between it
            if (array[mid] <= array[high]) {
                if (array[mid] < x && x <= array[high]) {
                    //Search right
                    low = mid + 1;
                } else {
                    //Search left
                    high = mid - 1;
                }
            }

            //Case 3: Check if the left sequence is sorted and element is between it
            if (array[mid] >= array[low]) {
                if (x >= array[low] && x < array[mid]) {
                    //Search left
                    high = mid - 1;
                } else {
                    //Search right
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    private static int findElementInCircularSortedArrayRecursive(int[] array, int x, int low, int high, int result) {

        //Base Case
        if (low > high) {
            return result;
        } else {
            int mid = low + (high - low)/2;

            //Case 1: If the element is found
            if (array[mid] == x) {
                result = mid;
                return result;
            } else if (array[mid] <= array[high]) { //Case 2: If right is sorted and element is between it
                if (array[mid] < x && x <= array[high]) {
                    //Search right
                    result = findElementInCircularSortedArrayRecursive(array, x, mid + 1, high, result);
                } else {
                    //Search left
                    result = findElementInCircularSortedArrayRecursive(array, x, low, mid - 1, result);
                }
            } else if (array[mid] >= array[low]) { //If left is sorted, and element is between it
                if (x >= array[low] && x < array[mid]) {
                    //Search left
                    result = findElementInCircularSortedArrayRecursive(array, x, low, mid - 1, result);
                } else {
                    //Search right
                    result = findElementInCircularSortedArrayRecursive(array, x, mid + 1, high, result);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {12, 14, 18, 21, 3, 6, 8, 9};

        int result = findElementInCircularSortedArrayIterative(array, 8);

        if (result == -1) {
            System.out.println("Element is not found");
        } else {
            System.out.println("Element found at index: " + result);
        }

        int result1 = findElementInCircularSortedArrayRecursive(array, 14, 0, array.length - 1, -1);

        if (result1 == -1) {
            System.out.println("Element is not found");
        } else {
            System.out.println("Element found at index using recursion : " + result1);
        }
    }
}
