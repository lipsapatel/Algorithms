package IK.DynamicProgramming.Class;

/**
 *Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 *
 * Recursive Approach
 *
 * 1) Recursive Function (word1, n, word2, m)
 * 2) Compare the nth character in word1 and word2
 * 3) If both match, then add 1 + f(word1, n - 1, word2, m - 1)
 * 4) If both don't match, return Max (f(word1, n - 1, word2, m), f(word1, n, word2, m - 1))
 * Delete char from word1 or delete char from word2
 * 5) Base Case: If n < 0 or m < 0, then return 0 One of the string is done
 *
 * TC: O(2^(n + m))
 * SC: O(n + m)
 *
 * Exponential Time Complexity
 *
 * DP Approach
 *
 * 1) Create int 2D DP array of size (n + 1) x (m + 1)
 * 2) Base Case: If n == 0, fill in 0
 * If m == 0, then fill in 0
 * 3) If word1.charAt(i - 1) == word2.charAt(j - 1), dp[i][j] = 1 + dp[i - 1][j - 1]
 * 4) else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
 * 5) Return dp[n][m]
 *
 * TC: O(nm)
 * SC: O(nm)
 *
 */
public class LongestCommonSubsequence {

    private static int longestCommonSubsequence(String word1, String word2) {

        int n = word1.length() - 1;
        int m = word2.length() - 1;

        return longestCommonSubsequenceHelper(word1, n, word2, m);
    }

    private static int longestCommonSubsequenceHelper(String word1, int n, String word2, int m) {

        //Base Case
        if(n < 0 || m < 0) {
            return 0; //Either one of the string is empty
        }

        if(word1.charAt(n) == word2.charAt(m)) {
            return 1 + longestCommonSubsequenceHelper(word1, n - 1, word2, m - 1);
        } else {
            //Delete one char from word1 and then delete one char from word2 and take max
            return Math.max(longestCommonSubsequenceHelper(word1, n - 1, word2, m), longestCommonSubsequenceHelper(word1, n, word2, m - 1));
        }
    }

    private static int longestCommonSubsequenceDP(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {

                if(i == 0 || j == 0) { //Word1 or word2 done
                    dp[i][j] = 0;
                } else if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); //Delete char from word1 and then delete char from word2
                }
            }
        }
        return dp[n][m];
    }
    public static void main(String[] args) {
        String word1 = "abcdef";
        String word2 = "ace";

        System.out.println("Longest Common Subsequence Recursion " +longestCommonSubsequence(word1, word2));
        System.out.println("Longest Common Subsequence DP: " + longestCommonSubsequenceDP(word1, word2));
    }
}
