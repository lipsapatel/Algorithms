/**
 * Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:

 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".

 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

 Note:
 The input string length won't exceed 1000.

 Approach

 1) Expand around the center.
 2) Consider both odd and even length palindromic substring from ith position
 3) Increment count for every character match

 TC = O(n^2)
 SC = O(1)
 */
public class CountPalindromicSubstrings {

    private static int count = 0;

    private static int countPalindromicSubstrings(String s) {

        //Base Case
        if(s == null || s.isEmpty()) {
            return 0;
        }

        for(int i = 0; i < s.length(); i++) {
            //Odd length palindrome at index i
            findPalindrome(s, i, i);

            //Even length palindrome at index i and i + 1
            findPalindrome(s, i, i + 1);
        }
        return count;
    }

    private static void findPalindrome(String s, int lo, int hi) {
        while(lo >= 0 && hi < s.length()) {
            if(s.charAt(lo) == s.charAt(hi)) {
                count++;
                lo--;
                hi++;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String input = "abc";

        System.out.println("The total number of palindromic substrings are " + countPalindromicSubstrings(input));

        String input1 = "aaa";
        System.out.println("The total number of palindromic substrings are " + countPalindromicSubstrings(input));
    }
}
