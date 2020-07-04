/**
 * Given a string s, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.

 Example 1:

 Input: "aacecaaa"
 Output: "aaacecaaa"
 Example 2:

 Input: "abcd"
 Output: "dcbabcd"

 Approach:

 1) Find the longest prefix substring.
 2) Reverse the string after that and append to original string.

 TC = O(n^2)

 Time limit exceeded for this brute force approach.

 Knutt Morris Pratt Approach - KMP
 https://www.youtube.com/watch?v=c4akpqTwE5g

 Approach

 1) s + rev
 2) Find the suffix which is the longest prefix using KMP
 3) Remove the longest suffix from rev
 4) Append remaining String + s

 Time Complexity: O(n) - to construct lps table
 Space Complexity: O(n) - lps table

 For KMP table

 1) Update j - prefix boundary
 2) If chars don't match, move to last prefix boundary
 3) If chars match, update lps[i] to j + 1

 The "#" in the middle is required, since without the #, the 2 strings could mix with each other, producing wrong answer.
 For example, take the string "aaaa". Had we not inserted "#" in the middle, the new string would be "aaaaaaaa"
 and the largest prefix size would be 7 corresponding to "aaaaaaa" which would be obviously wrong.
 Hence, a delimiter is required at the middle.

 resources/ShortestPalindrome.jpg
 */
public class ShortestPalindrome {

    private static String shortestPalindrome(String s) {

        //Find the longest palindrome prefix. Reverse the string after that and append to original string
        for(int i = s.length() - 1; i >= 0; i--) {
            StringBuilder remainingString = new StringBuilder();
            if(isPalindrome(s, 0, i)) {
                if(i < s.length() - 1) {
                    remainingString.append(s.substring(i + 1, s.length()));
                } else {
                    //Nothing left - looks like the whole string is palindrome
                    remainingString.append("");
                }
                return remainingString.reverse() + s;
            }
        }
        return s;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static String shortestPalindromeKMP(String s) {
        if(s.isEmpty()) {
            return s;
        }

        String rev = new StringBuilder(s).reverse().toString();
        String combine = s + '#' + rev;

        //KMP table
        int[] lps = new int[combine.length()];

        //Build KMP Table
        //i = suffix boundary
        //j = prefix boundary
        for(int i = 1; i < combine.length(); i++) {

            //Update prefix boundary
            int j = lps[i - 1];

            //Move to the last prefix boundary match
            while(j > 0 && combine.charAt(i) != combine.charAt(j)) {
                j = lps[j - 1];
            }

            //If prefix boundary matches suffix boundary, then increase the prefix length
            if(combine.charAt(i) == combine.charAt(j)) {
                lps[i] = j + 1;
            }
        }
        return rev.substring(0, s.length() - lps[combine.length() - 1]) + s;
    }

    public static void main(String[] args) {
        String input = "aaaaa";
        String input1 = "";
        String input2 = "abcd";
        String input3 = "aabba";
        System.out.println(shortestPalindrome(input));

        System.out.println(shortestPalindromeKMP(input3));
    }
}
