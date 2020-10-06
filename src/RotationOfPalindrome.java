/**
 * Is It A Rotation Of A Palindrome?
 * Given a string, check if it is a rotation of some palindromic string or not.
 *
 * Example
 * Input: “aab”
 * Output: 1
 * “aab” is a rotation of the palindromic string “aba”.
 * Rotating “aba” to the right gives “aab”.
 *
 * Constraints:
 *
 *     1 <= length of the input string <= 4000
 *     ‘a’ <= any character in the string <= ‘z’
 *
 * Approach
 *
 * 1) Find all rotation of string and check if it's palindrome or not.
 * 2) Return 1 if it's palindrome or else return 0
 * 3) There are n rotations and it takes O(n) time to check if
 * string is palindrome or not.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * resources/RotationOfPalindrome1.jpg
 * resources/RotationOfPalindrome2.jpg
 *
 * Optimal Approach:
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Worth mentioning in an interview
 *
 * 1) Concatenate given string with itself.
 * 2) Find the longest palindromic substring using Manacher's algorithm or
 * Suffix Trie approach.
 * 3) If given string is odd then longest palindromic substring found
 * should be odd.
 * 4) If given string is even then longest palindromic substring found
 * should be even.
 *
 * Refer to Longest Palindromic Substring problem.
 */
public class RotationOfPalindrome {

    public static int isRotationOfPalindrome(String s) {
        if(isPalindrome(s)) {
            return 1;
        }

        for(int i = 0; i < s.length(); i++) {
            String s1 = s.substring(i + 1);
            String s2 = s.substring(0, i + 1);

            if(isPalindrome(s1 + s2)) {
                return 1;
            }
        }
        return 0;
    }

    public static boolean isPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;

        while (lo < hi) {
            if(s.charAt(lo) == s.charAt(hi)) {
                lo++;
                hi--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isRotationOfPalindrome("aab"));
        System.out.println(isRotationOfPalindrome("baabc"));
    }
}
