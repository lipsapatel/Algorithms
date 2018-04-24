/**
 * If N is the number, the expression N & ~(N-1) will give the right most set bit
 *  N & ~N = 0
 *  N-1 = will subtract from the right most set bit and it will become 0
 *  Negate will make it 1
 *  And will make all bits zero except rightmost set bit
 *  Divide by Math.log(2) will return position - 1 so add 1.
 *
 *  Example: Number = 24
 *  number = 11000
 *  number -1 = 11000 - 00001 = 10111
 *  ~(number -1) = 01000
 *  number & (~(number - 1)) = 11000 & 01000 = 01000
 *  position = 4
 */
public class RightMostSetBit {

    private static void getRightMostSetBit(int number) {
        int rightMostSetBit = number & (~(number -1));
        double position = 1 + Math.log(rightMostSetBit)/Math.log(2);

        System.out.println("Right most set bit for " + number + " is " + Integer.toBinaryString(rightMostSetBit));
        System.out.println("Position: " + position);
    }

    public static void main(String[] args) {
        int number = 24;
        getRightMostSetBit(number);
    }

}