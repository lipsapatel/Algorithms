/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.

 Example 2:

 Input: "cbbd"
 Output: "bb"

 Brute Force Approach
 1) Find all substrings
 2) Check if it's palindrome or not

 Time Complexity: O(n^3)

Approach

 1) Expand around the center.
 2) Palindromic substring can be of even length or odd length
 3) while expanding, find the palindromic substring even length and odd length

 Time Complexity: O(n^2)
 Space Complexity: O(1)

 Linear approach is manacher's algorithm. O(n)

 **********************************************
 Suffix Trie Linear Approach

 1) In this approach you construct combined suffix trie for String S and
 it's Reverse String R
 2) The logic is, longest common substring from this tree will be the
 longest palindrome in String S
 3) But if the String S also contains the substring and it's reverse,
 then that will be returned as longest common substring even though it's not palindrome.
 4) To handle this case, we maintain the forward and reverse indexes and if they are same then
 that common substring is also palindrome

 LPS in S is same as LCS in S and R given that LCS in R and S must be from same position in S.

 Time Complexity: Linear - Construct Suffix trie using Ukonnen's Algorithm and find LCS
 Good to mention this in interview.

 resources/LongestPalindromicSubstringUsingSuffixTrie1.jpg
 resources/LongestPalindromicSubstringUsingSuffixTrie2.jpg
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

            //Consider each index as the center of odd and even palindrome.
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
