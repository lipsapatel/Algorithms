/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

 Example 1:
 Input: "aba"
 Output: True
 Example 2:
 Input: "abca"
 Output: True
 Explanation: You could delete the character 'c'.
 Note:
 The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

 Approach:

 1) Recursive Palindrome code.
 2) In case of mismatch, tried removing from left and right and check if it's a validPalindrome.
 3) Do that once.

 Time Complexity: O(N)
 Space Complexity: O(N) - recursive stack space
 */
public class ValidPalindromeRemoveAtmostOneChar {

    public static boolean validPalindrome(String s) {
        return validPalindromeHelper(s, 0, s.length() - 1, false);
    }

    public static boolean validPalindromeHelper(String s, int left, int right, boolean removedChar) {

        //Base Case
        if (left >= right) {
            return true;
        }

        if(s.charAt(left) != s.charAt(right)) {
            if(!removedChar) {
                removedChar = true;
                return validPalindromeHelper(s, left, right - 1, removedChar) || validPalindromeHelper(s, left + 1, right, removedChar);
            } else {
                return false;
            }
        }

        return validPalindromeHelper(s, left + 1, right - 1, removedChar);
    }

    public static void main(String[] args) {
        String input = "ebcbbececabbacecbbcbe";

        System.out.println(validPalindrome(input));
    }
}
