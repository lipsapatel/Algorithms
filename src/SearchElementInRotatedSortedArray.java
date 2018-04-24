/**
 * Search an element in Rotated Sorted array.
 *
 * Rotated Sorted Array: A sorted array is rotated around some pivot element.
 * Rotated after 6
 *
 * 10, 11, 12, 1, 2, 3, 4, 5, 6
 *
 * Approach:
 *
 * Naive Approach: Do the linear scan and find the element
 * Time Complexity: O(n)
 *
 * Better Approach:
 *
 * 1) Binary Search
 * 2) Check if array[mid] > array[low]
 * 3) If that true then the array is increasing in the first half
 * 4) Quickly search the element in first half
 * 5) If element is less than mid and greater than equal to low then high = mid -1
 * 6) else low = mid + 1 (the element is in the second half)
 *
 * 7) else search in second hald
 * 8) If element is greater than mid and less than equal to high, then low = mid + 1
 * 9) else high = mid - 1 (the element is in the first half)
 *
 * Time Complexity: O(logn)
 */
public class SearchElementInRotatedSortedArray {

    private static int searchElementInRotatedSortedArray(int[] array, int element) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {

            int mid = (low + high)/2;

            if (array[mid] == element) {
                return mid;
            }

            //array is increasing in the first half, quickly search in the first half
            if (array[mid] >= array[low]) {

                if (array[mid] > element && array[low] <= element) {
                    //Element is in the first half
                    high = mid - 1;
                } else {
                    //The element is in the second half
                    low = mid + 1;
                }
            } else {

                //Check if the element is in the second half

                if (array[mid] < element && element <= array[high]) {

                    //Element is in the second half
                    low = mid + 1;
                } else {
                    //The element is in the first half
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = { 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8 };

        System.out.println("Index of element 5 is " + searchElementInRotatedSortedArray(a, 5));
    }
}
