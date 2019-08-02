/**
 * Check whether the given string is palindrome using recursion
 *
 * Approach:
 *
 * 1) Call recursive function with string, left and right
 * 2) If left >= right return true
 * 3) If string[left] != string[right] return false
 * 4) Make recursive call string(left + 1, right - 1)
 *
 * Time Complexity: O(n/2) = O(n)
 * Space Complexity: O(n/2) = O(n)
 */
public class CheckPalindromeRecursively {

    private static boolean isPalindrome(String input, int left, int right) {

        if (left >= right) {
            return true;
        }

        if (input.charAt(left) != input.charAt(right)) {
            return false;
        }

        return isPalindrome(input, left + 1, right - 1);

    }

    public static void main(String[] args) {

        String input = "racecar";

        System.out.println("The string is palindrome: " + isPalindrome(input, 0, input.length() - 1));
    }
}
