/**
 * Find if the pattern matches to the string
 * ? - empty or one char
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

 */
public class PatternMatches {

    private static boolean patternMatches(String pattern, int pi, String str, int si) {

        //Base Cases
        //If both are empty, return true
        if (pi == pattern.length() && si == str.length()) {
            return true;
        }

        //If pattern is empty but string is not, return false
        if (pi == pattern.length() && si != str.length()) {
            return false;
        }

        //If string is empty but pattern is not, then everything in pattern has to be ?
        if (si == str.length()) {

            for (int i = pi; i < pattern.length(); i++) {
                if (pattern.charAt(pi) != '?') {
                    return false;
                }
            }
            return true;
        }

        //If both char are same or char in patter is . then move forward
        if (pattern.charAt(pi) == str.charAt(si) || pattern.charAt(pi) == '.') {
            return patternMatches(pattern, pi + 1, str, si + 1);
        } else if (pattern.charAt(pi) == '?') { //If it's ? then exclude or include
            //return patternMatches(pattern, pi + 1, str, si) || patternMatches(pattern, pi + 1, str, si + 1);
            //return patternMatches(pattern, pi + 1, str, si) || matchAsterik(pattern, pi, str, si);
            return patternMatches(pattern, pi + 1, str, si) || patternMatches(pattern, pi, str, si + 1); //This will match in the skip part; This is consume and stay in the pattern where ever you are.
        }

        //when both char doesn't match
        return false;

    }

    //Include si to str.length() - 1 char for *
    private static boolean matchAsterik(String pattern, int pi, String str, int si) {

        for (int i = si; i < str.length(); i++) {
            if (patternMatches(pattern, pi + 1, str, i + 1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String pattern = "a?b";
        String input = "ab";
        String input1 = "acb";

        System.out.println("Pattern matches: " + patternMatches(pattern, 0, input, 0));
        System.out.println("Pattern matches: " + patternMatches(pattern, 0, input1, 0));

        String pattern1 = "a???b";
        String input2 = "acb";
        System.out.println("Pattern matches: " + patternMatches(pattern1, 0, input2, 0));

        String pattern2 = "a?b?";
        String input3 = "aab";
        System.out.println("Pattern matches: " + patternMatches(pattern2, 0, input3, 0));

        String input4 = "aabcd";
        System.out.println("Pattern matches: " + patternMatches(pattern2, 0, input4, 0));

        String pattern3 = "a?c";
        String input5 = "aacdc";
        System.out.println("Pattern matches: " + patternMatches(pattern3, 0, input5, 0));

        String pattern4 = "a*b";
        String input6 = "aaaaa";
        System.out.println("Pattern matches: " + patternMatches(pattern4, 0, input6, 0));
    }
}
