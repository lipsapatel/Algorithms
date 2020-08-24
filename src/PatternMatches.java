/**
 * Find if the pattern matches to the string
 * ? - empty or one char/more than one
 * . - one char
 *
 * For example:
 *
 * a?b -> ab, aab
 *
 * Time Complexity: O(2 ^ (m + n)) where m is the str length and n is the pattern length
 * Because once pattern is over, and string is not then we return false and don't continue.
 *
 * Approach:
 *
 * 1) If both pattern and string are empty, return true
 * 2) If pattern is empty and string is not, return false
 * 3) If string is empty, then pattern has to be ? all
 * 4) If both the char in string and pattern matches or char in patter is ., pi + 1 and si + 1
 * 5) If char in pattern is ?, then exclude for which you do pi + 1, si Or
 * 6) Include for which you do pi + 1, si + 1
 * 7) return false otherwise, if both char don't match
 *
 * * - Matches zero or more characters
 *
 * n!is worst than 2^n
 *
 * resources/PatternMatches.png
 * resources/PatternMatchesExecutionTree.png
 *
 * Pattern ** and String ab
 *
 *                                                        (0, 0)
 *                                             Exclude/t                       C&S\t
 *                                            (1, 0)                          (0, 1)
 *                                   Exclude/f     C&S\t               Exclude/t     C&S\t
 *                                     (2, 0)       (1, 1)               (1, 1)      (0,2)
 *                                  return false  Exclude/f    t\C&S   Exclude/f  C&S\t
 *                                                (2, 1)      (1,2)     (2, 1)      (1, 2)
 *                                                return false return true

 DP Approach

 Time Complexity = O(mn) where m = length of pattern and n = length of string
 Space Complexity = O(mn)

 1) Identify the DP table
    2D boolean table because 2 changing parameters
    boolean[][] dp = new int[p.length() + 1][s.length() + 1]

 2) Initialize the DP table
    Base case when pattern is done - pi == p.length()
    last row should be false except when s is empty

 3) Traversal direction
    for(int si = s.length(); si >= 0; si--) {
        for(int pi = p.length() - 1; pi >= 0; pi--) {

 4) Populate dp table
 5) Return dp[0][0]

 resources/PatternMatchesDP1.jpg
 resources/PatternMatchesDP2.jpg
 */
public class PatternMatches {

    private static boolean patternMatches(String pattern, int pi, String str, int si) {

        //Base Cases
        //If both are empty, return true
        if (pi == pattern.length()) {
            return si == str.length();
        }

        //If string is empty but pattern is not, then everything in pattern has to be ?
        if (si == str.length()) {

            for (int i = pi; i < pattern.length(); i++) {
                if (pattern.charAt(i) != '?') {
                    return false;
                }
            }
            return true;
        }

        //If both char are same or char in patter is . then move forward
        if (pattern.charAt(pi) == str.charAt(si) || pattern.charAt(pi) == '.') {
            return patternMatches(pattern, pi + 1, str, si + 1);
        } else if (pattern.charAt(pi) == '?') { //If it's ? then exclude or include
            //return patternMatches(pattern, pi + 1, str, si) ||
             //patternMatches(pattern, pi + 1, str, si + 1);
            //Matches zero or one character

            return patternMatches(pattern, pi + 1, str, si) ||
                    patternMatches(pattern, pi, str, si + 1);
            //Matches zero or more characters. This will match in the skip part; This is consume and stay in the
            // pattern where ever you are.
        }

        //when both char doesn't match
        return false;

    }

    private static boolean patternMatches(String p, String s) {
        //Identify the DP table
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

        //Initialize DP table - Base Case
        for(int i = 0; i < s.length(); i++) {
            dp[p.length()][i] = false;
        }

        dp[p.length()][s.length()] = true;

        //Traversal direction
        for(int si = s.length(); si >= 0; si--) {
            for(int pi = p.length() - 1; pi >= 0; pi--) {

                if(si == s.length()) {
                    boolean allCharWildCard = true;
                    for (int i = pi; i < p.length(); i++) {
                        if(p.charAt(i) != '?') {
                            allCharWildCard = false;
                            break;
                        }
                    }

                    dp[pi][si] = allCharWildCard;

                } else if(p.charAt(pi) == s.charAt(si)) {
                    dp[pi][si] = dp[pi + 1][si + 1];
                } else if(p.charAt(pi) == '?') {
                    dp[pi][si] = dp[pi + 1][si] || dp[pi][si + 1];
                } else {
                    dp[pi][si] = false;
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        String pattern = "a?b";
        String input = "ab";
        String input1 = "acb";

        System.out.println("Pattern matches: " + patternMatches(pattern, 0, input, 0));
        System.out.println("Pattern matches: " + patternMatches(pattern, 0, input1, 0));

        System.out.println("Pattern matches DP: " + patternMatches(pattern, input));
        System.out.println("Pattern matches DP: " + patternMatches(pattern, input1));

        String pattern1 = "a???b";
        String input2 = "acb";
        System.out.println("Pattern matches: " + patternMatches(pattern1, 0, input2, 0));

        System.out.println("Pattern matches DP: " + patternMatches(pattern1, input2));

        String pattern2 = "a?b?";
        String input3 = "aab";
        System.out.println("Pattern matches: " + patternMatches(pattern2, 0, input3, 0));

        System.out.println("Pattern matches DP: " + patternMatches(pattern2, input3));

        String input4 = "aabcd";
        System.out.println("aabcd and a?b? Pattern matches: " + patternMatches(pattern2, 0, input4, 0));

        System.out.println("Pattern matches DP: " + patternMatches(pattern2, input4));

        String pattern3 = "a?c";
        String input5 = "aacdc";
        System.out.println("Pattern matches: " + patternMatches(pattern3, 0, input5, 0));

        System.out.println("Pattern matches DP: " + patternMatches(pattern3, input5));

        String pattern4 = "a?b";
        String input6 = "aaaaa";
        System.out.println("Pattern matches: " + patternMatches(pattern4, 0, input6, 0));

        System.out.println("Pattern matches DP: " + patternMatches(pattern4, input6));

        String pattern5 = "ab?";
        String input7 = "ab";
        System.out.println("Pattern matches: " + patternMatches(pattern5, 0, input7, 0));

        System.out.println("Pattern matches DP: " + patternMatches(pattern5, input7));
    }
}
