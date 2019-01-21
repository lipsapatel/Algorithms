/**
 * Print binary representation of int using recursion
 *
 * 43 = 101011
 *  43%2 = 1
 *  43/2 = 21
 *
 *  21 % 2 = 1
 *  21/2 = 10
 *
 *  10%2 = 0
 *  10/2 = 5
 *
 *  5%2 = 1
 *  5/2 = 2
 *
 *  2%2 = 0
 *  2/2 = 1
 *
 *  1 % 2 = 1
 *  1/2 = 0
 */
public class BinaryRepresentationOfInt {

    private static void printBinaryRepresentationOfInt(int n) {

        if (n < 0) {
            System.out.print("-");
            printBinaryRepresentationOfInt(-n);
        } else if (n <= 1) {
            System.out.print(n);
        } else {
            int lastDigit = n % 2;
            printBinaryRepresentationOfInt(n/2);
            System.out.print(lastDigit);
        }
    }

    public static void main(String[] args) {
        printBinaryRepresentationOfInt(43);
        System.out.println();
        printBinaryRepresentationOfInt(-43);
    }
}
