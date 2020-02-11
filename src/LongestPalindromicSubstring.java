/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.

 Example 2:

 Input: "cbbd"
 Output: "bb"

Approach

 1) Expand around the center.
 2) Palindromic substring can be of even length or odd length
 3) while expanding, find the palindromic substring even length and odd length

 Time Complexity: O(n^2)
 Space Complexity: O(1)

 Linear approach is manacher's algorithm. O(n)

 */
public class LongestPalindromicSubstring {

    private static String findLongestPalindromicSubstring(String s) {

        if(s == null || s.isEmpty()) {
            return s;
        }

        int maxLength = 0;
        int startIndex = -1;
        int endIndex = -1;

        for(int i = 0; i < s.length(); i++) {

            int oddLength = findMaxLength(s, i, i);
            int evenLength = findMaxLength(s, i, i + 1);

            if(oddLength > maxLength) {
                maxLength = oddLength;
                startIndex = (i - (oddLength/2));
                endIndex = (i + (oddLength/2));
            }

            if(evenLength > maxLength) {
                maxLength = evenLength;
                startIndex = (i - (evenLength - 1)/2);
                endIndex = (i + (evenLength/2));
            }
        }
        return s.substring(startIndex, endIndex + 1);
    }

    private static int findMaxLength(String s, int lo, int hi) {
        while(lo >= 0 && hi < s.length()) {
            if(s.charAt(lo) == s.charAt(hi)) {
                lo--;
                hi++;
            } else {
                break;
            }
        }
        return hi - lo - 1;
    }

    public static void main(String[] args) {
        String s = "abxzuvwnwvupyba";
        System.out.println("The longest palindromic substring is " + findLongestPalindromicSubstring(s));
    }
}
