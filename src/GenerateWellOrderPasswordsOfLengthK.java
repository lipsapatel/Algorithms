/**
 * Generate well ordered passwords of given length k.
 * Well-ordered means the digits should be in increasing order in every generated password.
 *
 * Example:
 * K = 7
 *
 * 1234567 1234568 1234569 1234578 1234579 1234589 1234678 1234679 1234689 1234789 1235678
 * 1235679 1235689 1235789 1236789 1245678 1245679 1245689 1245789 1246789 1256789 1345678
 * 1345679 1345689
 * 1345789 1346789 1356789 1456789 2345678 2345679 2345689 2345789 2346789 2356789 2456789
 * 3456789
 *
 * Approach:
 *
 * 1) Loop through all the digits - 1 to 9
 * 2) x will start at 0 and will be i with every recursive call
 * 3) With every recursive call, multiply result * 10 + i
 * This will append i to the result.
 * 4) k--;
 * 5) When k == 0 then print the result.
 */
public class GenerateWellOrderPasswordsOfLengthK {

    private static void generateWellOrderedPasswordsOfLengthK(int result, int x, int k) {

        if (k == 0) {
            System.out.print(result + " ");
        } else {
            for (int i = (x + 1); i < 10; i++) {

                generateWellOrderedPasswordsOfLengthK(10 * result + i, i, k - 1);
            }
        }
    }

    public static void main(String[] args) {
        generateWellOrderedPasswordsOfLengthK(0, 0, 7);
    }
}
