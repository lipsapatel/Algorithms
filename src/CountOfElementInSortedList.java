/**
 * Find the count of element in sorted list
 *
 * Find the first occurrence of element using binary search
 * Find the last occurrence of element using binary search
 *
 * Then find the count
 *
 * Time Complexity: O(logn)
 */
public class CountOfElementInSortedList {

    private static int binarySearchFindFirstLastOccurenceIterative(int[] array, int n, boolean searchFirst) {

        int low = 0;
        int high = array.length - 1;
        int result = -1;

        while (low <= high) {

            int mid = low + (high - low)/2;

            if (array[mid] == n) {
                result = mid;
                if (searchFirst) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (n < array[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    private static int binarySearchFindFirstLastOccurenceRecursive(int[] array, int n, boolean searchFirst, int low, int high, int result) {

        if (low > high) {
            return result;
        } else {
            int mid = low + (high - low)/2;

            if (array[mid] == n) {
                result = mid;
                if (searchFirst) {
                    result = binarySearchFindFirstLastOccurenceRecursive(array, n, searchFirst, low, mid - 1, result);
                } else {
                    result = binarySearchFindFirstLastOccurenceRecursive(array, n, searchFirst, mid + 1, high, result);
                }
            } else if (n < array[mid]) {
                result = binarySearchFindFirstLastOccurenceRecursive(array, n, searchFirst, low, mid - 1, result);
            } else {
                result = binarySearchFindFirstLastOccurenceRecursive(array, n, searchFirst, mid + 1, high, result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 3, 3, 5, 5, 5, 5, 5, 9, 11};

        int first = binarySearchFindFirstLastOccurenceIterative(array, 5, true);
        int last = binarySearchFindFirstLastOccurenceIterative(array, 5, false);
        int count = 0;

        if (first != -1 && last != -1) {
            count = last - first + 1;
        }

        System.out.println("The count of element in sorted array using binary search iterative is: " + count);

        int first1 = binarySearchFindFirstLastOccurenceRecursive(array, 5, true, 0, array.length - 1, -1);
        int last1 = binarySearchFindFirstLastOccurenceRecursive(array, 5, false, 0, array.length - 1, -1);
        int count1 = 0;

        if (first1 != -1 && last1 != -1) {
            count1 = last1 - first1 + 1;
        }

        System.out.println("The count of element in sorted array using binary search recursive is: " + count1);
    }
}
