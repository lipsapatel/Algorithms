import java.util.Arrays;
import java.util.Random;

/**
 * Assumption: Given rand() which generates random number in O(1) time
 * Start with the last element and swap with any element from 0 to till that element
 * Do this till i > 0 Don't do for the first element
 * Time Complexity: O(n)
 */
public class ShuffleArray {

    private static int[] shuffle(int[] array, int n) {

        Random r = new Random();

        for (int i = n-1; i > 0; i--) {

            //Pick random index from 0 to i
            int j = r.nextInt(i);

            //Swap array[i] with array[j]
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

        int[] shuffledArray = shuffle(array, array.length);

        System.out.println("The randomize array after shuffle is " + Arrays.toString(shuffledArray));
    }
}
