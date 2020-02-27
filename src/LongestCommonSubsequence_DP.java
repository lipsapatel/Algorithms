/**
 * Given two string subsequences, write an algorithm to find the length of longest common
 * subsequence present in both of them.
 *
 * What is Longest Common Subsequence?
 * A longest common subsequence is the sequence that appears in the same relative order,
 * but not necessarily contiguous in both string.
 *
 * Example:
 *
 * String A = "acbaed"
 * String B = "abcadf"
 *
 * Longest Common Subsequence: Length: 4 "acad"
 *
 * Approach:
 *
 * Recursion:
 *
 * Start by comparing the string in reverse order, one character at a time.
 *
 * Now we have two cases:
 *
 * 1) Both characters are same
 * Add 1 to the result and make recursive call after removing last character from both the string.
 *
 * 2) If both characters are not same
 * Remove last character from string1 and make recursive call
 * Remove last character from string2 and make recursive call
 * Return max from both of them
 *
 * Example:
 *
 * StringA = "ABCD"
 * StringB = "AEBD"
 *
 * LCS("ABCD","AEBD") = 1 + LCS("ABC", "AEB")
 *
 * StringA = "ABCDE"
 * StringB = "AEBDF"
 *
 * LCS("ABCDE", "AEBDF") = Max(LCS("ABCDE", "AEBD"), LCS("ABCD", "AEBDF"))
 *
 * Recursion
 * Time Complexity: O(2 ^ (max(m,n)))
 * Space Complexity: O(max(m, n))
 *
 * DP Approach
 *
 * resources/LongestCommonSubsequenceDP.jpg
 *
 * 1) Identify the DP table (type, size) - changing params
 * 2) Initialize the DP table - base conditions
 * 3) Traversal direction - opp of recursion
 * 4) Populate the DP table - recursion code
 *
 * Time Complexity = O(mn)
 * Space Complexity = O(mn)
 */
public class LongestCommonSubsequence_DP {

    private static int LCSRecursion(String s1, String s2, int i, int j) {

        //Base Case
        if(i == s1.length() || j == s2.length()) {
            return 0;
        }

        //If both chars are same, 1 + increment i and j
        if(s1.charAt(i) == s2.charAt(j)) {
            return 1 + LCSRecursion(s1, s2, i + 1, j + 1);
        } else {
            //Not same so take max of lcs after advancing i once and then j once.
            return Math.max(LCSRecursion(s1, s2, i + 1, j), LCSRecursion(s1, s2, i, j + 1));
        }
    }

    private static int LCSDP(String s1, String s2) {
        //Identify the DP table
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        //Initialize the DP table - Base Case
        //Initialize the last row and last col with 0
        for(int i = 0; i <= s2.length(); i++) {
            dp[s1.length()][i] = 0;
        }

        for(int i = 0; i <= s1.length(); i++) {
            dp[i][s2.length()] = 0;
        }

        //Traversal direction
        for(int i = s1.length() - 1; i >= 0; i--) {
            for(int j = s2.length() - 1; j >= 0; j--) {

                //Populate dp table - recursion logic
                if(s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        //Print LCS
        StringBuilder result = new StringBuilder();
        printLCS(dp, s1, s2, 0, 0, result);
        System.out.println(result.toString());

        //return the position where it started the recursion
        return dp[0][0];
    }

    private static void printLCS(int[][] dp, String s1, String s2, int i, int j, StringBuilder result) {

        //Base Case
        if (i == s1.length() || j == s2.length()) {
            return;
        }

        if(s1.charAt(i) == s2.charAt(j)) {
            result.append(s1.charAt(i));
            printLCS(dp, s1, s2, i + 1, j + 1, result);
        } else {
            if(dp[i + 1][j] > dp[i][j + 1]) {
                i = i + 1;
                j = j;
            } else {
                i = i;
                j = j + 1;
            }
            printLCS(dp, s1, s2, i, j, result);
        }
    }

    public static void main(String[] args) {

        String A = "ACB";
        String B = "ABC";

        System.out.println("The length of longest common subsequence using recursion is " +
                            LCSRecursion(A, B, 0, 0));

        System.out.println("The length of longest common subsequence using DP is " +
                            LCSDP(A, B));

        String s1 = "abracadabra";
        String s2 = "bradcar";

        System.out.println("The length of longest common subsequence using DP is " + LCSDP(s1, s2));
    }
}
