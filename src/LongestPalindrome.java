import java.util.HashSet;

/**
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes
 * that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * Approach:
 *
 * 1) You can form even length palindrome or odd length palindrome.
 * 2) If you have all even characters you can form the even length palindrome.
 * 3) But if you have any odd length character you can form odd length palindrome by adding 1 to it
 * 4) Take set, add and remove character for pair.
 * 5) Only the odd characters will be left in the set. s- odd char = will give you the length.
 * 6) Add one for one unique char
 * 7) If nothing is left in the set return s.length.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) //left over odd char
 */
public class LongestPalindrome {

    private static int longestPalindrome(String s) {
        HashSet<Character> set = new HashSet<>();

        for(int i = 0; i < s.length(); i++) {
            if(set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
            } else {
                set.add(s.charAt(i));
            }
        }

        //If set is empty, that means return whole string length
        if(set.isEmpty()) {
            return s.length();
        } else {
            return s.length() - set.size() + 1; //Remove the odd char, add 1 for odd length palindrome
        }
    }

    public static void main(String[] args) {
        String s = "aaaaabbb";
        System.out.println("The longest palindrome that can be formed is " + longestPalindrome(s));
    }
}
