package DecreasingPointInArray;

/**
 * Array which is first increasing then decreasing. Find the element in array from which
 * it starts decreasing.
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
 * Linear Search Approach
 * Traverse the array and track the element from where array starts decreasing
 * Time Complexity : O(n)
 */
public class DecreasingPointInArray_linearSearch_O_n {

    private static void findDecreasingPointInArray(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i-1] > array[i]) {
                System.out.println("First element from where elements starts decreasing is " + array[i-1]);
                return;
            }
        }

        //If you have reached here there is no decreasing point, return the last element
        // For Example: {1, 2, 4 }
        System.out.println("Array does not have a decreasing point, Maximum element is " + array[array.length - 1]);
    }

    public static void main(String[] args) {
        //int[] array = {1,2,4,6,11,15,19,20,21,31,41,51,55,46,35,24,18,14,13,12,11,4,2,1};
        //int[] array = {1, 2, 4};
        int[] array = {4, 2, 1};
        findDecreasingPointInArray(array);
    }
}
