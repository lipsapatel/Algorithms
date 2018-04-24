/**
 * Count set bits in Integer.
 * Count number of 1's in binary representation of an integer.
 *
 * Input: n= 6
 * Binary representation: 110
 * Number of set bits = 2
 *
 * Input n = 13
 * Binary representation: 1101
 * Number of set bits = 3
 *
 * Subtract 1 from number will toggle all bits till rightmost set bits including
 * right most set bits
 * Do bitwise AND with itself will unset rightmost set bit.
 * Do n & (n-1) in loop till it becomes 0
 * count the number of times loop executes will give number of set bits
 *
 * Time Complexity: O(logn)
 *
 */
public class CountSetBits {

    private static int countNumberOfSetBits(int n) {
        int count = 0;

        while (n != 0) {
            n = n & (n-1);
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println("The number of set bits in Integer 13 is " + countNumberOfSetBits(13));
    }
}
