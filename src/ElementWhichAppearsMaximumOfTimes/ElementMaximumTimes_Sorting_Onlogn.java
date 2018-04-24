package ElementWhichAppearsMaximumOfTimes;

import java.util.Arrays;

/**
 * Given an array of integers, write an algorithm to find element which occurs
 * maximum number of times
 *
 * int[] array = {4, 1, 5, 2, 1, 5, 9, 8, 6, 5, 3, 2, 4, 7}
 * Output: Element occurring maximum number of times - 5
 *          maximum count = 3
 *
 * int[] array = {2, 2, 1, 1}
 * Output: Element occurring maximum number of times - 2
 *          maximum count = 2
 *
 * Sort an array
 * This will bring all duplicates together
 * Count the element
 * Maintain the maximum element and count
 *
 * Time Complexity: O(nlogn)
 * Space Complexity: O(n)
 */
public class ElementMaximumTimes_Sorting_Onlogn {

    private static void elementAppearingMaximumTimes(int[] array) {

        if (array.length < 1) {
            System.out.println("Array is empty");
            return;
        }
        Arrays.sort(array);

        int count = 1;
        int element = array[0];

        int maximumElement = 0;
        int maximumCount = 0;

        for (int i = 1; i < array.length; i++) {

            if (element == array[i]) {
                count++;
            } else {

                if (count > maximumCount) {
                    maximumCount = count;
                    maximumElement = element;
                }

                //Reset the element and the count
                element = array[i];
                count = 1;
            }
        }

        System.out.println("The element appearing maximum number of times is " + maximumElement +
                    " and it's count is " + maximumCount);
    }

    public static void main(String[] args) {
        int[] array = {4, 1, 5, 2, 1, 5, 9, 8, 6, 5, 3, 2, 4, 7};

        elementAppearingMaximumTimes(array);

        int[] array1 = {2, 2, 1, 1};

        elementAppearingMaximumTimes(array1);

        int[] array2 = {};

        elementAppearingMaximumTimes(array2);
    }
}
