import java.util.Arrays;

/**
 * Reverse an Array without using built-in function.
 * int[] a = {1, 2, 3, 4, 5};
 * Output = {5, 4, 3, 2, 1};
 *
 * Swap the first and last element
 *
 * Recursive function called with first and last element
 *
 * Iteration - while loop
 */
public class ReverseArrayWithoutBuiltInFunction {

    static int[] array;

    private static void reverseArrayByIteration() {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            start++;
            end--;
        }
    }

    private static void reverseArrayByRecursion(int start, int end) {

        if (start <= end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            start++;
            end--;

            reverseArrayByRecursion(start, end);
        }
    }

    public static void main(String[] args) {
        array = new int[]{1, 2, 3, 4, 5};

        System.out.println("Original Array " + Arrays.toString(array));

        reverseArrayByIteration();

        System.out.println("Array after reversing by Iteration " + Arrays.toString(array));

        reverseArrayByRecursion(0, array.length - 1);

        System.out.println("Array after reversiong by Recursion " + Arrays.toString(array));
    }
}
