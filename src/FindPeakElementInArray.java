/**
 * It can also be called as increasing point in array.
 *
 * Peak Element: Peak element is the element which is greater than or equal to both of it's
 * neighbour.
 *
 * Input: int[] array;
 * Output: A peak element and it's index.
 *
 * Approach:
 *
 * 1) Do linear scan
 *
 * Time Complexity: O(n)
 *
 * Binary Search:
 *
 * 1) mid = start+end/2;
 * 2) If mid element == peek element then return
 * 3) If left element is greater than mid element, do a recursive search in left, 0, mid - 1
 * 4) If right element is greater than mid element, do a recursive search in right, mid + 1, end
 *
 *  Time Complexity: O(logn)
 *
 *  Notes:
 *
 *  1) If array has all same elements, then all are peek element.
 *  2) If array is in ascending order, then last element is the peek element.
 *  3) If array is in descending order, then the first element is the peek element.
 *  4) Every array has peek element.
 *  5) We are finding only one peek element.
 */
public class FindPeakElementInArray {

    private static int findPeekElementInArray(int[] array, int start, int end, int size) {

        int mid = (start + end)/2;

        //If mid = 0, we have reached the end of left and mid = size means we have reached the end
        //of right.
        if (mid == 0 || mid == size || (array[mid] >= array[mid - 1] && array[mid] >= array[mid + 1])) {
            return mid;
        } else if (mid > 0 && array[mid - 1] >= array[mid]) {
            return findPeekElementInArray(array, start, mid - 1, size);
        } else {
            return findPeekElementInArray(array, mid + 1, end, size);
        }
    }

    public static void main(String[] args) {

        int[] array = { 1,2,3,4,0,1,5,4,3,2,1};
        int peekElementIndex = findPeekElementInArray(array, 0, array.length - 1, array.length - 1);
        System.out.println("Peak Element is found at index [" + peekElementIndex +"] = "+ array[peekElementIndex]);

        int[] array1 = {1,2,3,4};
        int peekElementIndex1 = findPeekElementInArray(array1, 0, array1.length - 1, array1.length - 1);
        System.out.println("Peak Element is found at index [" + peekElementIndex1 +"] = "+ array1[peekElementIndex1]);

        int[] array2 = {4,3,2,1};
        int peekElementIndex2 = findPeekElementInArray(array2, 0, array2.length - 1, array2.length - 1);
        System.out.println("Peak Element is found at index [" + peekElementIndex2 +"] = "+ array2[peekElementIndex2]);

        int[] array3 = {4,4,4,4};
        int peekElementIndex3 = findPeekElementInArray(array3, 0, array3.length - 1, array3.length - 1);
        System.out.println("Peak Element is found at index [" + peekElementIndex3 +"] = "+ array3[peekElementIndex3]);

    }
}
