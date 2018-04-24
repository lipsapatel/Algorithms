/**
 * Number of bits to be flipped to convert one number to another number
 *
 * x = 10 - 01010
 * y = 20 - 10100
 * Turn on bits 1 and 3
 * Turn off bits 2 and 4
 *
 * XOR Table
 * 0 0 - 0
 * 1 0 - 1
 * 0 1 - 1
 * 1 1 - 0
 * The XOR of two numbers will set the bit if either of bit is set not both
 * Calculate z = x XOR y
 * And count the number of set bits in z which will be final answer
 */
public class BitsToBeFlipped {

    private static int numberOfBitsToBeFlipped(int x, int y) {
        int z = x ^ y;

        int count = 0;
        while (z != 0) {
            z = z & (z-1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {

        int x = 10;
        int y = 20;
        System.out.println("Number of bit needs to be flipped to convert "
                + x + " to " + y + " are: " + numberOfBitsToBeFlipped(x,y));
    }
}
