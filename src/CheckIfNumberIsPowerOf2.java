/**
 * Given a number, write a program to find if number is power of two.
 *
 * Example:
 *
 * N = 5;
 * Output: false
 *
 * N = 8;
 * Output = true
 *
 * N = 512
 * Output = true
 *
 * Approach:
 *
 * This problem can be solved in multiple ways
 *
 * 1) Check the remainder
 * 2) Convert number to bits - Power of 2 has only one bit set, count the set bits
 *
 * Remainder Approach:
 *
 * 1) Keep dividing the number by 2 until n = 1, during this iteration if anytime remainder (n % 2) is non zero  then number
 * is not power of 2 else the number is power of 2
 *
 * Bit Manipulation
 *
 * 1) Every number which is a power of 2 has only one bit set (4 = 100, 8 = 1000, 16 = 10000)
 * 2) Convert the given number into binary and count the number of set bits, if count > 1 then number is not power of 2
 * else the number is power of 2
 */
public class CheckIfNumberIsPowerOf2 {

    private static void checkIfNumberIsPowerOf2Remainder(int n) {

        boolean isPowerOf2 = true;
        int number = n;

        while (n > 1) {
            if (n % 2 != 0) {
                isPowerOf2 = false;
            }
            n = n/2;
        }

        if (isPowerOf2) {
            System.out.println("Given number " + number + " is power of 2");
        } else {
            System.out.println("Given number " + number + " is not power of 2");
        }
    }

    private static void checkIfNumberIsPowerOf2CountSetBits(int n) {
        int countBits = countSetBits(n);

        if (countBits == 1) {
            System.out.println("Given number " + n + " is power of 2");
        } else {
            System.out.println("Given number " + n + " is not power of 2");
        }
    }

    private static int countSetBits(int number) {
        int count = 0;

        while(number > 0) {

            //Check the last bit of number
            //If last bit is 1, increment count
            count = count + (number & 1);

            //right shift the number
            number = number >> 1;
        }
        return count;
    }

    public static void main(String[] args) {

        int n = 6;
        checkIfNumberIsPowerOf2Remainder(n);
        checkIfNumberIsPowerOf2CountSetBits(n);

        n = 8;
        checkIfNumberIsPowerOf2Remainder(n);
        checkIfNumberIsPowerOf2CountSetBits(n);

        n = 24;
        checkIfNumberIsPowerOf2Remainder(n);
        checkIfNumberIsPowerOf2CountSetBits(n);

        n = 512;
        checkIfNumberIsPowerOf2Remainder(n);
        checkIfNumberIsPowerOf2CountSetBits(n);
    }
}
