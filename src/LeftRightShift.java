/**
 * Left Shift (<<) and Right Shift (>>) Operators
 *
 * What is Left Shift (<<) Operator?
 *
 * 1) a << b means left shift the bits of number a by b places.
 * 2) 3 << 2 means left shift the bits of number 3 by 2 places. Result = 12
 * 3) 011 is the bit representation of number 3
 * 4) Left Shift all the bits so => 0110 (number = 6)
 * 5) Left Shift all the bits so => 01100 (number = 12)
 * 6) Each time you left shift the number, it will be multiplied by 2. So if you shift a number by k then number
 * will be multiplied by k^2
 *
 * What is Right Shift (>>) Operator?
 *
 * 1) a >> b means right shift the bits of number a by b places.
 * 2) 12 >> 2 means right shift the bits of number 12 by 2 places. Result = 3
 * 3) 01100 is the bit representation of number 12
 * 4) Right shift all the bits so => 0110 (number = 6)
 * 5) Right shift all the bits so => 011 (number = 3)
 * 6) Each time you right shift the number, it will be divided by 2. So if you shift a number by k then number will be divided by k^2
 */
public class LeftRightShift {

    private static void LeftShift(int n) {

        int x = n << 1;

        System.out.println("n << 1, Left shift by 1 of n: " + n + " is: " + x);
    }

    private static void RightShift(int n) {

        int x = n >> 1;

        System.out.println("n >> 1, Right Shift by 1 of n: " + n + " is: " + x);
    }

    public static void main(String[] args) {

        int n = 10;
        LeftShift(n);
        RightShift(n);

        n = 62;
        LeftShift(n);
        RightShift(n);
    }
}
