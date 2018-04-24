package Separate0And1InArray;

import java.util.Arrays;

/**
 * Iterate the array and count number of 0's = count
 * In array, put 0 from indexes 0 to count - 1
 * In rest of the array, put 1
 *
 * Time Complexity: O(n)
 */
public class Separate0And1UsingCount {

    private static int[] separate0And1 (int[] array) {

        int count = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                count++;
            }
        }

        for (int i = 0; i < array.length; i++) {

            if (count > 0) {
                array[i] = 0;
                count --;
            } else {
                array[i] = 1;
            }
        }

        return array;
    }

    public static void main(String[] args) {
        int [] arrA = {1,0,1,0,1,1,0,0,0,0,1};
        System.out.println("Rearranging arrays using counting..");
        arrA = separate0And1(arrA);
        System.out.println(Arrays.toString(arrA));
    }
}
