package Separate0And1InArray;

import java.util.Arrays;

/**
 * Use two indexes, left and right
 * Left index = array[0]
 * right index = array.length - 1
 * if left == 0  increment it
 * if right == 1 decrement it
 * else swap it, left = 0, right = 1, left ++, right --
 * Do it till left < right
 * Time Complexity: O(n)
 */
public class Separate0And1UsingSwap {

    private static int[] separate0And1UsingSwap(int[] array) {

        int leftIndex = 0;
        int rightIndex = array.length - 1;

        while (leftIndex < rightIndex) {

            if (array[leftIndex] == 0) {
                leftIndex++;
            } else if (array[rightIndex] == 1) {
                rightIndex--;
            } else {
                array[leftIndex] = 0;
                array[rightIndex] = 1;

                leftIndex++;
                rightIndex--;
            }
        }

        return array;
    }

    public static void main(String[] args) {
        int [] arrA = {1,0,1,0,1,1,0,1};
        System.out.println("Rearranging arrays using left and right indexes");
        arrA = separate0And1UsingSwap(arrA);
        System.out.println(Arrays.toString(arrA));
    }
}
