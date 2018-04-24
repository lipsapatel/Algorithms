package ThreeElementsSumZero;

import java.util.Arrays;

/**
 * Sort the array
 * Loop through the array
 * Take first element = a
 * Find 2 elements whose sum = -a
 *
 * Time Complexity: O(n2)
 */
public class SortingOn2 {

    private static void findThreeElementsSumToZero(int[] array) {

        Arrays.sort(array);

        for (int i = 0; i < array.length; i++) {
            int a = array[i];

            int sum = -a;

            int start = i + 1;
            int end = array.length - 1;

            while(start < end) {
                int tempSum = array[start] + array[end];

                if (tempSum == sum) {
                    System.out.println("The three elements that sum to zero are " + a +
                    "," + array[start] + "," + array[end]);
                    return;
                } else if (tempSum < sum) {
                    start++;
                } else if (tempSum > sum) {
                    end--;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {3, -1, -4, -5, -7, 9, 10};

        findThreeElementsSumToZero(array);
    }
}
