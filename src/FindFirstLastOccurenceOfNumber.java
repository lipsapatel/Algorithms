/**
 * Using binary search find the first occurence of a number in array
 *
 * For Example: 2, 4, 10, 10, 10, 18, 20
 * k = 10
 *
 * Number 10 found at index 2
 *
 * Time Complexity: O(logn)
 */
public class FindFirstLastOccurenceOfNumber {

    private static int findFirstOccurenceIterative(int[] array, int k) {

        int low = 0;
        int high = array.length - 1;
        int result = -1;

        while (low <= high) {

            int mid = low + (high - low)/2;

            if (k == array[mid]) {
                result = mid;
                high = mid - 1;
            } else if (k < array[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    private static int findLastOccurenceIterative(int[] array, int k) {

        int low = 0;
        int high = array.length - 1;
        int result = -1;

        while (low <= high) {

            int mid = low + (high - low)/2;

            if (k == array[mid]) {
                result = mid;
                low = mid + 1;
            } else if (k < array[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    private static int findFirstOccurenceRecursive(int[] array, int k, int low, int high, int result) {

        //Base Case
        if (low > high) {
            return result;
        } else {

            int mid = low + (high - low)/2;

            if (k == array[mid]) {
                result = mid;
                result = findFirstOccurenceRecursive(array, k, low, mid - 1, result);
            } else if (k < array[mid]) {
                result = findFirstOccurenceRecursive(array, k, low, mid - 1, result);
            } else {
                result = findFirstOccurenceRecursive(array, k, mid + 1, high, result);
            }
        }
        return result;
    }

    private static int findLastOccurenceRecursive(int[] array, int k, int low, int high, int result) {

        //Base Case
        if (low > high) {
            return result;
        } else {

            int mid = low + (high - low)/2;

            if (k == array[mid]) {
                result = mid;
                result = findLastOccurenceRecursive(array, k, mid + 1, high, result);
            } else if (k < array[mid]) {
                result = findLastOccurenceRecursive(array, k, low, mid - 1, result);
            } else {
                result = findLastOccurenceRecursive(array, k, mid + 1, high, result);
            }
        }
        return result;
    }

    //Find the count of an element in sorted list
    private static void countOfElement(int[] A, int n) {
        int first = findFirstOccurenceRecursive(A, n, 0, A.length - 1, -1);
        int last = findLastOccurenceRecursive(A, n, 0, A.length - 1, -1);
        int count = 0;

        if (first != -1 && last != -1) {
            count = last - first + 1; //TC = O(logn)
        }

        System.out.println("The count of element " + n + " is " + count);
    }

    public static void main(String[] args) {

        int[] array = {2, 4, 10, 10, 10, 18, 20};

        int result = findFirstOccurenceIterative(array, 10);

        if (result == -1) {
            System.out.println("10 not found in array");
        } else {
            System.out.println("First occurence of 10 found at index: " + result);
        }

        result = findLastOccurenceIterative(array, 10);

        if (result == -1) {
            System.out.println("10 not found in array");
        } else {
            System.out.println("Last occurence of 10 found at index: " + result);
        }

        result = findFirstOccurenceRecursive(array, 10, 0, array.length - 1, -1);

        if (result == -1) {
            System.out.println("10 not found in array");
        } else {
            System.out.println("First occurence of 10 found recursively at index: " + result);
        }

        result = findLastOccurenceRecursive(array, 10, 0, array.length - 1, -1);

        if (result == -1) {
            System.out.println("10 not found in array");
        } else {
            System.out.println("Last occurence of 10 found recursively at index: " + result);
        }

        int[] array1 = {1, 1, 3, 3, 5, 5, 5, 5, 9, 9, 11};
        countOfElement(array1, 5);
    }
}
