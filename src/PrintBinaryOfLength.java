/**
 * Print the Binary of length of given digit
 *
 * For example:
 *
 * digits = 2
 * 00
 * 01
 * 10
 * 11
 *
 * Starting with 0 first and then starting with 1 first
 * That's the pattern.
 *
 * Base case:
 *
 * If (digits == 1) {
 *     sout(0);
 *     sout(1);
 * } else {
 *     recursive case
 *     starting with 0
 *     then starting with 1
 * }
 *
 * You need to pass prefix in order to keep appending
 */
public class PrintBinaryOfLength {

    private static void printBinaryOfLength(int digits, String prefix) {

        if (digits == 0) {
            //Base Case
            System.out.println(prefix);
        } else {
            //Recursive Case
            printBinaryOfLength(digits - 1, prefix+"0");
            printBinaryOfLength(digits - 1, prefix+"1");
        }
    }

    public static void main(String[] args) {
        System.out.println("Print binary of length 2");
        printBinaryOfLength(2, "");
        System.out.println("Print binary of length 3");
        printBinaryOfLength(3, "");
        System.out.println("Print binary of length 4");
        printBinaryOfLength(4, "");
    }
}
