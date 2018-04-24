import java.util.Arrays;

/**
 * Input: An n-element array x of numbers
 * Output: An n-element array A of numbers such that A[i] = average of elements x[0]...x[i]
 *
 * Approach 1:
 *
 * Time Complexity: O(n2)
 *
 * 1) For each element we are summing the elements again and again
 *
 * We are repeating calculating the sum
 * array[0] + array[1]
 * array[0] + array[1] + array[2]
 *
 * Linear Time Complexity: O(n)
 *
 */
public class ArrayPrefixAverage {

    private static void prefixAverageQuadratic(int[] array) {

        int[] outputArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {

            int average = 0;

            for (int j = 0; j <= i; j++) {
                average = average + array[j];
            }

            outputArray[i] = average/(i + 1);
        }
        System.out.println("Array by prefix average is(Quadratic): " + Arrays.toString(outputArray));
    }

    private static void prefixAverageLinear(int[] array) {

        int[] outputArray = new int[array.length];
        int sum = 0;

        for (int i = 0; i < array.length; i++) {

            //Sum of prefixes
            sum = sum + array[i];

            outputArray[i] = sum/(i + 1);
        }

        System.out.println("Array by prefix average is(Linear): " + Arrays.toString(outputArray));
    }

    public static void main(String[] args) {

        int[] array = {3, 5, 8, 9, 7};

        prefixAverageQuadratic(array);
        prefixAverageLinear(array);
    }
}
