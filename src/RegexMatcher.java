/**
 * Regex Matcher
 *
 * Given a text string containing characters only from lowercase alphabetic characters
 * and a pattern string containing characters only from lowercase alphabetic
 * characters and two other special characters '.' and '*'.
 *
 * Your task is to implement a pattern matching algorithm that returns true if
 * pattern is matched with text otherwise returns false. The matching must be
 * exact, not partial.
 *
 * Explanation of the special characters:
 * 1) '.' - Matches a single character from lowercase alphabetic characters.
 * 2) '*' - Matches zero or more preceding character. It is guaranteed that '*'
 * will have one preceding character which can be any lowercase alphabetic
 * character or special character '.'. But '*' will never be the preceding character
 * of '*'. (That means "**" will never occur in the pattern string.)
 *
 * '.' = "a", "b", "c", ... , "z".
 * a* = "a","aa","aaa","aaaa",... or empty string("").
 * ab* = "a", "ab", "abb", "abbb", "abbbb",...
 *
 * Example 1
 * Input: text = "abbbc" and pattern = "ab*c"
 * Output: true
 *
 * Given pattern string can match:
 * "ac", "abc", "abbc", "abbbc", "abbbbc", ...
 *
 * Example 2
 * Input: text = "abcdefg" and pattern = "a.c.*.*gg*"
 * Output: true
 *
 * ".*" in pattern can match  "", ".", "..", "...", "....", ...
 * "g*" in pattern can match "", "g", "gg", "ggg", "gggg", ...
 *
 * Now, consider:
 *     '.' at position 2 as 'b',
 *     '.*'  at position {4,5} as "...",
 *     '.*' at position {6,7} as "" and
 *     [g*] at position {8,9} as "".
 *
 * So, "a.c.*gg*" = "abc...g" where we can write "..." as "def". Hence, both matches.
 *
 * Example 3
 * Input:
 * text = "abc" and pattern = ".ab*.."
 *
 * Output: false
 *
 * If we take 'b*' as "" then also, length of the pattern will be 4 (".a..").
 * But, given text's length is only 3. Hence, they can not match.
 *
 * Recursive Approach
 *
 * 1) If the next char is * then you match
 *    zero char
 *    more than zero char
 * 2) Else match one char at a time
 *
 * Time Complexity = O(2 ^ (m + n)) where m = length of string and n = length of pattern
 * Space Complexity = O(m + n)
 *
 * Constraints:
 *     0 <= text length, pattern length <= 1000
 *     Text string containing characters only from lowercase alphabetic characters.
 *     Pattern string containing characters only from lowercase alphabetic characters
 *     and two other special characters '.' and '*'
 *     In pattern string, It is guaranteed that '*' will have one preceding
 *     character which can be any lowercase alphabetic character or
 *     special character '.'. But '*' will never be the preceding character of '*'.
 *
 *     resources/RegexMatcher1.jpg
 *     resources/RegexMatcher2.jpg
 *
 *     DP Approach:
 *
 *     1) Identify the dp table
 *     2 changing parameters, and return type is boolean
 *     so 2d boolean table
 *     boolean[][] dp = new boolean[p.length() + 1][s.length() + 1]
 *
 *     2) Initialize dp table
 *     Last row where pi == p.length(), all false except when si == s.length() then make
 *     it true
 *
 *     3) Traversal direction - opposite of recursion
 *     for(int si = s.length(); si >= 0; si--)
 *      for(int pi = p.length() - 1; pi >= 0; pi--)
 *
 *     4) Populate dp table
 *
 *     Return dp[0][0]
 *
 *     Time Complexity: O(mn)
 *     Space Complexity: O(mn)
 */
public class RegexMatcher {

    private static boolean patternMatcher(String s, String p) {
        return patternMatcherRecursive(s, 0, p, 0);
    }

    private static boolean patternMatcherDP(String s, String p) {
        //Identify dp table
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

        //Initialize dp table
        for (int i = 0; i < s.length(); i++) {
            dp[p.length()][i] = false;
        }
        dp[p.length()][s.length()] = true;

        //Traversal direction
        for (int si = s.length(); si >= 0; si--) {
            for(int pi = p.length() - 1; pi >= 0; pi--) {

                //Populate dp table
                if (si == s.length()) {
                    if(pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
                        dp[pi][si] = dp[pi + 2][si]; //skip
                    } else {
                        dp[pi][si] = false;
                    }
                } else {

                    char nextChar = pi + 1 < p.length() ? p.charAt(pi + 1) : ' ';

                    if (nextChar == '*') {
                        if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') {
                            //skip and consume
                            dp[pi][si] = dp[pi + 2][si] || dp[pi][si + 1];
                        } else {
                            //skip
                            dp[pi][si] = dp[pi + 2][si];
                        }

                    } else if(s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') {
                        //Match one char
                        dp[pi][si] = dp[pi + 1][si + 1];
                    } else { //Char don't match
                        dp[pi][si] = false;
                    }
                }
            }
        }

        return dp[0][0];
    }

    private static boolean patternMatcherRecursive(String s, int si, String p, int pi) {

        //Base Case - If pattern is done
        if(pi == p.length()) {
            return si == s.length();
        }

        //If string is done and pattern is not
        if(si == s.length()) {
            if(pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
                //zero match
                return patternMatcherRecursive(s, si, p, pi + 2);
            } else {
                return false;
            }
        }

        char nextChar = pi + 1 < p.length() ? p.charAt(pi + 1) : ' ';

        if(nextChar == '*') {

            if(s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') {
                //Match zero and match more than zero
                return patternMatcherRecursive(s, si, p, pi + 2) ||
                        patternMatcherRecursive(s, si + 1, p, pi);
            } else {
                //Don't match char to skip - match zero
                return patternMatcherRecursive(s, si, p, pi + 2);
            }
        }

        if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') {
            //Match one char at time
            return patternMatcherRecursive(s, si + 1, p, pi + 1);
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "abbbc";
        String p = "ab*c";

        System.out.println("Pattern matches String: " + patternMatcherRecursive(s, 0, p, 0));
        System.out.println("Pattern matches String DP: " + patternMatcherDP(s, p));

        s = "abcdefg";
        p = "a.c.*.*gg*";

        System.out.println("Pattern matches String: " + patternMatcherRecursive(s, 0, p, 0));
        System.out.println("Pattern matches String DP: " + patternMatcherDP(s, p));

        s = "abc";
        p = ".ab*..";

        System.out.println("Pattern matches String: " + patternMatcherRecursive(s, 0, p, 0));
        System.out.println("Pattern matches String DP: " + patternMatcherDP(s, p));
    }
}
