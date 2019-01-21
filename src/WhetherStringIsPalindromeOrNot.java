/**
 * Find whether the given string is Palindrome or not.
 *
 * Input: String
 * Output: true or false on whether string is palindrome or not
 *
 * Approach:
 *
 * 1) Use recursive approach
 * 2) Compare the first and last character. If they are not same then return false
 * 3) If they are same, remove the first and last character and make recursive call.
 *
 * Time complexity: O(n)
 *
 * Example:
 *
 * Jain niaJ => compare ‘J’ with ‘J’ =>returns true
 *
 * ain nia => compare ‘a’ with ‘a’ =>returns true
 *
 * in ni => compare ‘i’ with ‘i’ =>returns true
 *
 * n n => compare ‘n’ with ‘n’ =>returns true
 *
 * string length <2 => returns true
 *
 * substring(int beginIndex, int endIndex) - returns substring beginIndex inclusive and
 * endIndex exclusive.
 */
public class WhetherStringIsPalindromeOrNot {

    private static Boolean isStringPalindrome(String inputString) {

        if (inputString == null) {
            return false;
        }

        inputString = inputString.toLowerCase();

        if (inputString.length() < 2) {
            return true;
        }

        if (inputString.charAt(0) == inputString.charAt(inputString.length() - 1)) {
            return isStringPalindrome(inputString.substring(1, inputString.length() - 1));
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        String inputString = "LipsaPatel";
        String S1 = "Sumit";
        String S2 = "Sumus";
        String S3 = "ABCDEFGHGFEDCBA";
        String S4 = "Jain niaJ";
        String S5 = "JasasddfwfcsJ";
        String S6 = "liaail";
        String S7 = "";

        System.out.println("The given string " + inputString + " is Palindrome " + isStringPalindrome(inputString));
        System.out.println("The given string " + S1 + " is Palindrome " + isStringPalindrome(S1));
        System.out.println("The given string " + S2 + " is Palindrome " + isStringPalindrome(S2));
        System.out.println("The given string " + S3 + " is Palindrome " + isStringPalindrome(S3));
        System.out.println("The given string " + S4 + " is Palindrome " + isStringPalindrome(S4));
        System.out.println("The given string " + S5 + " is Palindrome " + isStringPalindrome(S5));
        System.out.println("The given string " + S6 + " is Palindrome " + isStringPalindrome(S6));
        System.out.println("The given string " + S7 + " is Palindrome " + isStringPalindrome(S7));

    }
}
