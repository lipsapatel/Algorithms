import java.util.Arrays;

/**
 * Given an array which contains even and odd integers, write an algorithm that separates
 * even and odd numbers.
 *
 * int[] array = {1, 2, 3, 4, 6, 8, 7, 12}
 * Output: [12, 2, 4, 6, 1, 3, 7]
 *
 * Use two indexes left and right
 * Put left index on first element and right index to the last element.
 * Increment left index if we encounter even element.
 * Decrement right index if we encounter odd element.
 * Swap left and right and increment left and decrement right
 * Do it till left < right
 *
 * Time Complexity: O(n)
 */
public class SeparateEvenAndOdd {

    private static int[] separateEvenAndOdd(int[] array) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;

        while (leftIndex < rightIndex) {
            if (array[leftIndex] % 2 == 0) {
                leftIndex++;
            } else if (array[rightIndex] % 2 != 0) {
                rightIndex--;
            } else {
                int temp = array[leftIndex];
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int [] array = {1,2,3,4,6,8,7,12};
        System.out.println("Rearranging arrays using left and right indexes");
        array = separateEvenAndOdd(array);
        System.out.println(Arrays.toString(array));
    }
}
