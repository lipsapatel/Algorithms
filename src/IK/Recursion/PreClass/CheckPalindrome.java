package IK.Recursion.PreClass;

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
 *
 * Check whether given string is palindrome iteratively
 *
 * 1) If first and last characters match, then low++ and high--
 * 2) If they don't match then return false
 * 2) Repeat step 1 and 2 while low < high
 * 3) Outside while return true
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class CheckPalindrome {

    private static boolean isPalindrome(String input, int left, int right) {

        if (left >= right) { //Base Case
            return true;
        } else { //Recursive Case

            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }

            return isPalindrome(input, left + 1, right - 1);
        }

    }

    private static boolean isPalindromeIteratively(String input, int low, int high) {

        while(low < high) {
            if(input.charAt(low) != input.charAt(high)) {
                return false;
            } else {
                low++;
                high--;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String input = "racecar";

        System.out.println("The string is palindrome: " + isPalindrome(input, 0, input.length() - 1));
        System.out.println("The string is palindrome Iteratively : " + isPalindromeIteratively(input, 0, input.length() - 1));
    }
}
