/**
 * Find the local minimum or maximum in O(1)
 *
 * Given an array such that every next element differs from previous
 * by +/- 1.
 * Find the local maximum or minimum in O(1) time
 *
 * Local minimum and local maximum should be non-edge elements of array.
 *
 * 1, 2, 3, 4, 5, 4, 3, 2, 1 - Local maximum = 5
 * 5, 4, 3, 2, 1, 2, 3, 4, 5 - Local minimum = 1
 * 1, 2, 3, 4, 5 - No Local minimum or maximum found
 * 5, 4, 3, 2, 1 - No Local minimum or maximum found
 *
 * Can be solved using binary search in O(logn) time.
 *
 * We assume that only one local minimum or maximum exists.
 *
 * For local maximum, array should be first increasing and then decreasing.
 * The last_element_should_be = first_element + (size - 1)
 *
 * local_maximum = last_element_should_be + last_element/2
 *
 * For local minimum, array should be first decreasing and then increasing.
 * The last_element_should_be = first_element - (size - 1)
 *
 * local_minimum = last_element_should_be + last_element/2
 *
 * Handle the edge cases
 *
 * If array is all increasing
 * then first_element + (size - 1) == last_element
 *
 * If array is all decreasing
 * then first_element - (size - 1) == last_element
 *
 */
public class LocalMimimumOrMaximumO1 {

    private static void findLocalMinimumOrMaximum(int[] array) {

        //If array is null or empty then no local minimum or maximum found
        if (array == null || array.length == 0) {
            System.out.println("No local minimum or maximum found");
            return;
        }

        int size = array.length;
        int first_element = array[0];
        int last_element = array[size - 1];

        //Handle edge cases
        if (first_element + (size - 1) == last_element || first_element - (size - 1) == last_element) {
            System.out.println("No local minimum or maximum found");
            return;
        }

        //If array is increasing then find local maximum
        if (first_element < array[1]) {
            int last_element_should_be = first_element + (size - 1);

            int local_maximum = (last_element_should_be + last_element)/2;

            System.out.println("The local maximum is " + local_maximum);
        } else {
            //If array is decreasing the find local minimum

            int last_element_should_be = first_element - (size - 1);

            int local_minimum = (last_element_should_be + last_element)/2;

            System.out.println("The local minimum is " + local_minimum);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        findLocalMinimumOrMaximum(array);

        int[] array1 = {5, 4, 3, 2, 1, 2, 3, 4, 5};
        findLocalMinimumOrMaximum(array1);

        int[] array2 = {1, 2, 3, 4, 5};
        findLocalMinimumOrMaximum(array2);

        int[] array3 = {5, 4, 3, 2, 1};
        findLocalMinimumOrMaximum(array3);

        int[] array4 = {-4, -5, -6, -5, -4, -3};
        findLocalMinimumOrMaximum(array4);
    }
}
