package IndexFromWhichZeroStarts;

/**
 * Given an array of Integer in which 1 is followed by 0.
 * Find the starting index of 0.
 *
 * Binary Search - Start with the middle element
 * If mid element is 0 and left element is 1, return the index
 * If mid element is 1 then search mid+1 to end
 * If mid element is 0 and left element is 0 then search start to mid
 * Also handle the base cases - If there is only one element check if it's 0
 * return that index else return -1
 * If there are two elements, if first element is 0 return that index,
 * if second element is 0 return that index else return -1
 *
 * Time Complexity: O(logn)
 *
 * int[] a = {1,1,1,1,0,0}
 * Index = 4
 *
 * int[] a = {1,1,1}
 * No element found
 *
 * int[] a = {0,0,0}
 * Index = 0
 */
public class IndexFromWhichZeroStarts_BinarySearch_Ologn {

    private static int findIndexFromWhichZeroStarts(int[] array, int start, int end) {

        //If array is null or empty return -1
        if (array == null || array.length == 0) {
            return -1;
        }

        //If there is only one element
        if (start == end) {
            //If that element is 0, return that index
            if (array[start] == 0) {
                return start;
            } else if (array[start] == 1) {
                return -1;
            }
        }

        //If there are only two elements
        if (end == start + 1) {
            if (array[start] == 0) {
                return start;
            } else if (array[end] == 0) {
                return end;
            } else {
                return -1;
            }
        }

        int mid = start + (end - start)/2;

        //If mid element is 0 and left element is 1 return that index
        if (array[mid] == 0 && array[mid-1] == 1) {
            return mid;
        } else if (array[mid] == 0 && array[mid-1] == 0) { //If mid element is 0 and left
            //element is 0 then search left
            return findIndexFromWhichZeroStarts(array, start, mid);
        } else { //search right if middle element is 1
            return findIndexFromWhichZeroStarts(array, mid+1, end);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 1, 0};

        System.out.println("First index from which 0 starts " +
                findIndexFromWhichZeroStarts(array, 0, array.length - 1));

        int[] array1 = {0, 0, 0};

        System.out.println("First index from which 0 starts " +
                findIndexFromWhichZeroStarts(array1, 0, array1.length - 1));

        int[] array2 = {1, 1};

        System.out.println("First index from which 0 starts " +
                findIndexFromWhichZeroStarts(array2, 0, array2.length - 1));
    }
}
