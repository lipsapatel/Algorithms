/**
 * Find local minima in given array
 *
 * An element is called local minima if it's less than both of it's neighbour
 * if neighbour exists
 *
 * int[] array = {11, 4, 2, 5, 11, 13, 5};
 * Output: Local minima is 2
 *
 * int[] array = {1, 2, 3, 4};
 * Output: Local minima is 1
 *
 * int[] array = {3};
 * Output: Local minima is 3
 *
 * int[] array = {6, 4};
 * Output: Local minima is 4
 *
 * Binary search approach
 *
 * Check if mid element is less than left and right neighbour
 * To make sure if neighbour exists also add condition if mid = 0 as or condition
 * with left check
 * mid = size - 1 as or check with right check
 *
 * If mid is greater than zero and element is greater than left then search in left half
 *
 * If mid is less than size - 1 and element is greater than right then search in right half
 *
 * Time Complexity: O(logn)
 */
public class LocalMinima_BinarySearch {

    private static int findLocalMinima(int[] array, int start, int end) {

        //Find mid element
        int mid = start + (end - start)/2;

        int size = array.length;

        //Check if mid element is less than left and right neighbour
        //Also check if left and right neighbour exist
        if ((mid == 0 || array[mid] < array[mid - 1]) &&
                (mid == size - 1 || array[mid] < array[mid + 1])) {

            return array[mid];

        } else if (mid > 0 && array[mid] > array[mid - 1]) { //If mid element is greater than
            //left element than search in left

            return findLocalMinima(array, start, mid);
        } else { //If mid element is greater than right element than search right
            //If mid < size - 1 && array[mid] > array[mid+1]

            return findLocalMinima(array, mid + 1, end);
        }
    }

    public static void main(String[] args) {

        int [] arrA = {11, 4, 2, 5, 11, 13, 5};
        System.out.println("Local Minima is: " + findLocalMinima(arrA,0,arrA.length));

        int[] array = {1, 2, 3, 4};
        System.out.println("Local Minima is: " + findLocalMinima(array,0,array.length));

        int[] arrayA = {3};
        System.out.println("Local Minima is: " + findLocalMinima(arrayA,0,arrayA.length));

        int[] arrayB = {6, 4};
        System.out.println("Local Minima is: " + findLocalMinima(arrayB,0,arrayB.length));
    }
}

