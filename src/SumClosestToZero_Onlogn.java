import java.util.Arrays;

/**
 * Given an array of positive and negative integers, find two elements whose sum is closest
 * to zero.
 *
 * int[] array = {1, 4, -5, -7, -2, 7}
 * Output: Sum close to zero is 0
 *
 * int[] array = {8, 6, -5, -2}
 * Output: Sum close to zero is 1
 *
 * int[] array = {-6, 5, 2}
 * Output: Sum close to zero is -1
 *
 * int[] array = {-5, -5, -8}
 * Output: Sum close to zero is -10
 *
 * int[] array = {1, 4, -5, 3, -2, 10, -6, 20};
 * Output: Sum close to zero is 1
 *
 * Time Complexity: O(nlogn)
 */
public class SumClosestToZero_Onlogn {

    private static int sumClosestToZero(int[] array) {

        Arrays.sort(array);

        int i = 0;
        int j = array.length - 1;

        int leftIndex = 0;
        int rightIndex = array.length - 1;

        int minimumSum = Integer.MAX_VALUE;
        int sum;

        if (array.length < 2) { //For one element return that element
            return array[0];
        }

        while(i < j) {

            sum = array[i] + array[j];

            if (Math.abs(sum) < Math.abs(minimumSum)) {
                minimumSum = sum;
                leftIndex = i;
                rightIndex = j;
            }

            if (sum < 0) {
                i++;
            } else {
                j--;
            }
        }

        System.out.println("The two elements whose sum is mimimum is " + array[leftIndex] + ", " + array[rightIndex]);
        return minimumSum;
    }

    public static void main(String[] args) {

        int[] array = {1, 4, -5, -7, -2, 7};
        System.out.println("Sum close to zero " + sumClosestToZero(array));

        int[] array1 = {8, 6, -5, -2};
        System.out.println("Sum close to zero " + sumClosestToZero(array1));

        int[] array2 = {-6, 5, 2};
        System.out.println("Sum close to zero " + sumClosestToZero(array2));

        int[] array3 = {1};
        System.out.println("Sum close to zero " + sumClosestToZero(array3));

        int[] array4 = {1, 2};
        System.out.println("Sum close to zero " + sumClosestToZero(array4));

        int[] array5 = {-5, -5, -8};
        System.out.println("Sum close to zero " + sumClosestToZero(array5));

        int[] array6 = {1, 4, -5, 3, -2, 10, -6, 20};
        System.out.println("Sum close to zero " + sumClosestToZero(array6));

    }
}
