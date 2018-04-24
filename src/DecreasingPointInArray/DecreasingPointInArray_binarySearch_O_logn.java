package DecreasingPointInArray;

/**
 * Array which is first increasing then decreasing. Find the element in array from
 * which it starts decreasing.
 * Find the decreasing point in an array or Find the maximum element in that array
 *
 * int[] a = {1, 2, 4, 6, 11, 15, 19, 20, 21, 31, 41, 51, 55, 46, 35, 24, 18, 14, 13, 12,
 * 11, 4, 2, 1}
 * output = 55
 *
 * int[] a = {1, 2, 4} //No decreasing element so the last element will be the answer
 * output = 4
 *
 * int[] a = {4, 2, 1}
 * output = 4
 *
 * Binary Search Approach
 *
 * If array is null or empty, return -1
 *
 * If array has only one element, return that element
 *
 * If array has 2 elements, return maximum of 2
 *
 * if mid element is greater than right element and greater than left element return mid element
 *
 * if mid element is greater than left element but smaller than right element, then do recursive call with
 * mid+1, end
 *
 * if mid element is less than left element and greater than right element, then do recursive call with
 * start, mid
 *
 * Time Complexity: O(logn)
 */
public class DecreasingPointInArray_binarySearch_O_logn {

    private static int findDecreasingPointInArray(int[] array, int start, int end) {

        //If array is null or empty return -1
        if (array == null || array.length == 0) {
            return -1;
        }

        //If array has only one element return that element
        if (start == end) {
            return start;
        }

        //If array has two elements, return the maximum one
        if (end == start + 1) {
            if (array[start] < array[end]) {
                return end;
            } else {
                return start;
            }
        }

        //Now do the binary search
        int remainingElementsMid = (end - start)/2;
        int mid = start + remainingElementsMid;

        //If mid element is greater than left element and greater than right element then return mid element
        if (array[mid] > array[mid-1] && array[mid] > array[mid+1]) {
            return mid;
        } else if (array[mid] > array[mid-1] && array[mid] < array[mid+1]){ //If mid element is greater than left element
            //But smaller than right element then search in right
            return findDecreasingPointInArray(array, mid+1, end);
        } else { //If mid element is smaller than left element and greater than right element, then search in left
            return findDecreasingPointInArray(array, start, mid);
        }
    }

    public static void main(String[] args) {
        //int[] array = {1,2,4,6,11,15,19,20,21,31,41,51,55,46,35,24,18,14,13,12,11,4,2,1};
        //int[] array = {1, 2, 4};
        //int[] array = {4, 2, 1};
        int[] array = {};

        int index = findDecreasingPointInArray(array, 0, array.length - 1);

        if (index == -1) {
            System.out.println("No decreasing element found; Array is either empty or null");
        } else {
            System.out.println("First element from where elements starts decreasing: (index: " + index + " ) : " + array[index]);
        }
    }
}
