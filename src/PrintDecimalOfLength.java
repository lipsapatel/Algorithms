/**
 * Write a recursive function printDecimal that accepts an integer number of digits and prints all base 10
 * numbers that have exactly that many digits in ascending order one per line
 *
 * Try all the options/choices
 */
public class PrintDecimalOfLength {

    private static void printDecimalOfLength(int digit, String soFar) {

        //Base Case
        if (digit == 0) {
            System.out.println(soFar);
        } else {
            //Recursive Case
            //Try all options/choices
            for (int i = 0; i < 10; i++) {
                printDecimalOfLength(digit - 1, soFar+Integer.toString(i));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Print decimal of length 2");
        printDecimalOfLength(2, "");
        System.out.println("Print decimal of length 3");
        printDecimalOfLength(3, "");
    }
}
