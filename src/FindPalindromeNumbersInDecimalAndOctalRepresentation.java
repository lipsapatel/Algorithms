/**
 * Find numbers which are Palindrome in both their Octal and Decimal Representations.
 *
 * Given range of integers, find all the numbers which are Palindrome when represented in
 * decimal (base 10) and Octal (base 8)
 *
 * Example:
 *
 * 373 decimal and 565 Octal are palindrome.
 *
 * 1) Traverse through all the numbers in the given range
 * 2) Check if it is palindrome
 * 3) If yes then convert to Octal
 * 4) Check again if it is palindrome
 *
 * Time Complexity O(n)
 *
 */
public class FindPalindromeNumbersInDecimalAndOctalRepresentation {

    private static void findIfBothArePalindrome(int start, int end) {

        for (int i = start; i <= end; i++) {

            String decimal = String.valueOf(i);

            if (isPalindrome(decimal)) {

                String octal = decimalToOctal(i);

                if (isPalindrome(octal)) {
                    System.out.print(decimal + " ");
                }

            }
        }
    }

    private static String decimalToOctal(int N) {

        String octal = "";

        while (N > 0) {

            int remainder = N % 8;
            N = N/8;
            octal = octal + remainder;

        }

        return octal;
    }

    private static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println("The numbers between 1 to 2000 which are palindrome in both " +
                            "their decimal and octal representation are ");
        findIfBothArePalindrome(1, 2000);
    }
}
