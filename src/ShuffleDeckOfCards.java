import java.util.Arrays;
import java.util.Random;

/**
 * Given an array which contains numbers from 1 to 52.
 * Each number in array represent deck of card.
 * 1 --> A Spades
 * 2 --> 2 Spades
 * 3 --> 3 Spades
 *
 * Write a shuffle function which takes array and return the shuffle output.
 *
 * What is a valid shuffle?
 * A valid shuffle is one where probability of every output must be same.
 * 1/52!
 *
 * Total number of outputs = 52! permutations.
 */
public class ShuffleDeckOfCards {

    private static int[] shuffle(int[] a) {

        for(int x = 0; x < a.length; x++) {
            Random random = new Random();
            int max = a.length;

            //This is for picking random number between x and max(exclusive).
            //nextInt take the bound and pick random number between 0(inclusive) to
            // bound(exclusive)
            int i = random.nextInt(max - x) + x;

            swap(x, i, a);
        }
        return a;
    }

    private static void swap(int x, int y, int[] a) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    /**
     * Why you wouldn't use this?
     *
     * @param a
     * @return
     */
    private static int[] shuffleB(int[] a) {
        for(int x = 0; x < a.length; x++) {
            Random random = new Random();

            int i = random.nextInt(a.length); //0 to 52(exclusive)
            swap(x, i, a);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = new int[52];

        for(int i = 0; i < 52; i++) {
            a[i] = i + 1;
        }

        System.out.println("After shuffle " + Arrays.toString(shuffle(a)));

        for(int i = 0; i < 52; i++) {
            a[i] = i + 1;
        }

        System.out.println("After shuffle " + Arrays.toString(shuffleB(a)));
    }
}
