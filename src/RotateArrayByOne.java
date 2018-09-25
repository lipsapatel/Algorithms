import java.util.Arrays;

/**
 * Rotate Array in circles
 *
 * Given an array of integers, write a program to rotate array in cyclic manner by one.
 *
 * Input:
 * int[] array = {1, 2, 3, 4, 5}
 *
 * Output:
 * {2, 3, 4, 5, 1}
 *
 * Approach:
 *
 * 1) Store the first element of array in temporary variable
 * 2) From 2nd element to last element, left shift all the elements by one position
 * 3) Put the temporary variable value at the last index of the array
 *
 * resources/RotateArrayByOne.png
 */
public class RotateArrayByOne {

    private static void rotateArrayByOne(int[] array) {

        System.out.println("Original Array: " + Arrays.toString(array));

        if (array == null || array.length == 0) {
            return;
        }

        int temp = array[0];

        for (int i = 1; i < array.length; i++) {
            array[i - 1] = array[i];
        }

        array[array.length - 1] = temp;
        System.out.println("Rotated Array: " + Arrays.toString(array));
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5};
        rotateArrayByOne(array);
        rotateArrayByOne(array);
    }
}
