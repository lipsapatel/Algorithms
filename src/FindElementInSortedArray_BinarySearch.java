/**
 * Write an algorithm to find an element in sorted array.
 *
 * Input: int[] sortedArray and int key
 * Output: Return true if element found else return false.
 *
 * Approach:
 *
 * 1) If mid == key, return true
 * 2) If mid < key, search right
 * 3) If mid > key, search left
 *
 * Time Complexity: O(logn) since we are eliminating half of the array at every comparison.
 *
 * resources/FindElementInSortedArray_BinarySearch.png
 *
 */
public class FindElementInSortedArray_BinarySearch {

    private static boolean findElementInSortedArray(int[] array, int low, int high, int number) {

        if (low > high) {
            return false;
        }

        int mid = (low + high)/2;

        if (array[mid] == number) {
            return true;
        } else if (array[mid] < number) {
            //Search right
            return findElementInSortedArray(array, mid + 1, high, number);
        } else {
            return findElementInSortedArray(array, low, mid - 1, number);
        }
    }

    public static void main(String[] args) {
        int [] a = {2,5,8,10,14,44,77,78,99};
        int number = 99;
        System.out.println("The "+ number + " present in array a ??? :" + findElementInSortedArray(a, 0, a.length-1, number));

        number = 76;
        System.out.println("The "+ number + " present in array a ??? :" + findElementInSortedArray(a, 0, a.length-1, number));

    }
}
