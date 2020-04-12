/**
 *  Given a string S, find the number of different non-empty palindromic subsequences in S,
 *  and return that number modulo 10^9 + 7.
 *
 * A subsequence of a string S is obtained by deleting 0 or more characters from S.
 * A sequence is palindromic if it is equal to the sequence reversed.
 *
 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
 *
 * Example 1:
 *
 * Input:
 * S = 'bccb'
 * Output: 6
 * Explanation:
 * The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 * Note that 'bcb' is counted only once, even though it occurs twice.
 *
 * Example 2:
 *
 * Input:
 * S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * Output: 104860361
 * Explanation:
 * There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 *
 * Note:
 * The length of S will be in the range [1, 1000].
 * Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 *
 * Approach
 *
 * Initial Values : i= 0, j= n-1;
 *
 * CountPS(i,j)
 * // Every single character of a string is a palindrome
 * // subsequence
 * if i == j
 *    return 1 // palindrome of length 1
 *
 * // If first and last characters are same, then we
 * // consider it as palindrome subsequence and check
 * // for the rest subsequence (i+1, j), (i, j-1)
 * Else if (str[i] == str[j)]
 *    return   countPS(i+1, j) + countPS(i, j-1) + 1;
 *
 * else
 *    // check for rest sub-sequence and  remove common
 *    // palindromic subsequences as they are counted
 *    // twice when we do countPS(i+1, j) + countPS(i,j-1)
 *    return countPS(i+1, j) + countPS(i, j-1) - countPS(i+1, j-1)
 *
 *    resources/DistinctPalindromicSubsequences.jpg
 *    resources/DistinctPalindromicSubsequencesRecursion.jpg
 *
 * Recursion:
 * Time Complexity: O(3^(n - 2)) = O(3^n)
 * Space Complexity: O(n - 2) = O(n)
 *
 * DP:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 *
 */
public class DistinctPalindromicSubsequence {
    private static int moduloValue = 1000000007;

    private static int countPalindromicSubsequencesRecursion(String s) {
        return countPalindromicSubsequencesRecursionHelper(s, 0, s.length() - 1) % moduloValue;
    }

    private static int countPalindromicSubsequencesRecursionHelper(String s, int i, int j) {

        //Base Case
        if (i == j) { //Only one character left
            return 1;
        }

        //Two characters left
        if (i - j == -1 || i - j == 1) {
            if(s.charAt(i) == s.charAt(j)) { //aa = return 3
                return 2;
            } else {
                return 2; //return 2 if s[i] != s[j] for example: bc and return 3 if s[i] == s[j] if you are not counting distinct
            }
        }

        if(s.charAt(i) == s.charAt(j)) {
            return 1 + countPalindromicSubsequencesRecursionHelper(s, i + 1, j) + countPalindromicSubsequencesRecursionHelper(s, i, j - 1)
                    - countPalindromicSubsequencesRecursionHelper(s, i + 1, j - 1); //- is for duplicates
        } else {
            return countPalindromicSubsequencesRecursionHelper(s, i + 1, j) + countPalindromicSubsequencesRecursionHelper(s, i, j - 1)
                    - countPalindromicSubsequencesRecursionHelper(s, i + 1, j - 1);
        }
    }

    private static int countPalindromicSubsequencesDp(String s) {

        //Identify the dp table - two params changing i and j
        int[][] dp = new int[s.length()][s.length()];

        //Initialize the dp table
        //Two base cases
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp.length; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                }

                if(i - j == -1 || i - j == 1) {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 3;
                    } else {
                        dp[i][j] = 2;
                    }
                }
            }
        }

        //Traversal Direction Recursion i = 0 and j = n - 1
        //opposite of recursion
        for (int i = s.length() - 3; i >= 0; i--) {
            for(int j = i + 2; j < s.length(); j++) {

                //Populate dp table
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (1 + dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]) % 1000000007;
                } else {
                    dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]) % 1000000007;
                }
            }
        }
        return dp[0][dp.length - 1];
    }

    private static int noOfUniqueChar(String s) {
        char c = s.charAt(0);

        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != c) {
                return 2;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        String s = "bccb";
        System.out.println("Distinct palindromic subsequences using recursion: " + countPalindromicSubsequencesRecursion(s));

        s = "abcb";
        System.out.println("Distinct palindromic subsequences using recursion: " + countPalindromicSubsequencesRecursion(s));

        s = "bccb";
        System.out.println("Distinct palindromic subsequences using DP: " + countPalindromicSubsequencesDp(s));

        s = "abcb";
        System.out.println("Distinct palindromic subsequences using DP: " + countPalindromicSubsequencesDp(s));

        s = "aa";
        System.out.println("Distinct palindromic subsequences using DP: " + countPalindromicSubsequencesDp(s));

        s = "aaa";
        System.out.println("Distinct palindromic subsequences using DP: " + countPalindromicSubsequencesDp(s));
    }
}
