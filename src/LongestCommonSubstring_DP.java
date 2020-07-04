/**
 * Given two string sequences, write an algorithm to find the length of longest substring
 * present in both of them.
 *
 * Longest Common Substring: A longest substring is the sequence that appears in the same order
 * and necessarily contiguous in both string.
 *
 * Example:
 *
 * String A = "ABCXYZAY"
 * String B = "XYZABCB"
 * Output: The Longest Common Substring length is 4 ("XYZA")
 *
 * Naive Approach:
 *
 * 1) Generate all substrings of s1
 * The total number of substrings = n(n+1)/2 - O(n^2)
 * 2) For every substring, check if it exists in second string
 * Pattern Searching KMP O(s + m)
 * Rabin Karp = O(s + m)
 * 3) If it exists the keep the track of maximum.
 *
 * Time Complexity: O(n2 *m) - O(n2) for substring and O(m) for checking all substring in second
 * string.
 *
 * Recursive Solution
 * 1) There are two cases for recursion.
 * 2) Case 1: When last character matches, recursively match remaining characters
 * if(s1.charAt(m - 1) == s2.charAt(n - 1)) {
 *     lcsCount1 = lcs(m - 1, n - 1, lcsCount + 1)
 * }
 *
 * 3) Case 2: When last character does not match
 * Two recursive call by skipping one character from both the string.
 * Reset lcsCount
 *
 * lcsCount2 = lcs(m, n - 1, 0)
 * lcsCount3 = lcs(m - 1, n, 0)
 *
 * lcsCount = Max(lcsCount1, lcsScount2, lcsCount3)
 *
 * 4) Base Case
 * if(m <= 0 || n <= 0)
 *     return lcsCount
 *
 * Time Complexity: O(3^(m + n))
 * Space Complexity: O(m + n)
 *
 * Better Solution:
 * Dynamic Programming
 *
 * 1) We will solve this problem in bottom-up approach.
 * 2) Create matrix of size m*n and store the solutions of substring for use later.
 * 3) Best Cases: If any of string is null, then LCS = 0
 * 4) Check if ith character in String A is equal to jth character in String B
 * 5) Case: If both characters are same then LCS[i][j] = 1 + LCS[i - 1][j - 1]
 * 6) Case: If both characters are not same, LCS[i][j] = 0
 * 7) At the end traverse the matrix and find the maximum element in it.
 * 8) This will be the length of longest common substring.
 *
 * If the string is really long, better to use suffix trie and collect dollars and hash.
 * Time Complexity: O(m + n)
 *
 * Dp cell: Longest substring ending at given point
 *
 * Time Complexity: O(mn)
 * Space Complexity: O(mn)
 *
 * resources/LongestCommonSubstringNaive.jpg
 * resources/LongestCommonSubstringRecursion.jpg
 * resources/LongestCommonSubstring3D.jpg
 * resources/LongestCommonSubstringDP.jpg
 */
public class LongestCommonSubstring_DP {

    private static int findLCSRecursive(String s1, String s2, int lcsCount, int m, int n) {

        //Base Case
        if(m <= 0 || n <= 0) {
            return lcsCount;
        }

        //Case 1: Characters match
        int lcsCount1 = lcsCount;
        if(s1.charAt(m - 1) == s2.charAt(n - 1)) {
            lcsCount1 = findLCSRecursive(s1, s2, 1 + lcsCount, m - 1, n - 1);
        }

        //Case 2: Don't match - This has to be done in both the cases
        int lcsCount2 = findLCSRecursive(s1, s2, 0, m - 1, n);
        int lcsCount3 = findLCSRecursive(s1, s2, 0, m, n - 1); //Reset lcsCount

        return Math.max(lcsCount1, Math.max(lcsCount2, lcsCount3));
    }

    private static String findLCSDP(String s1, String s2) {
        String lcs = "";

        if(s1.isEmpty() || s2.isEmpty()) {
            return lcs;
        }

        int[][] dp = new int[s1.length()][s2.length()];

        for(int i = 0; i < s1.length(); i++) {
            for(int j = 0; j < s2.length(); j++) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    //First row or First col
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    }

                    if(dp[i][j] > lcs.length()) {
                        lcs = s1.substring(i - dp[i][j] + 1, i + 1);
                    }
                }
            }
        }
        return lcs;
    }

    public static void main(String[] args) {
        String s1 = "xabxa";
        String s2 = "babxba";

        System.out.println("Longest common substring using recursion " +
                findLCSRecursive(s1, s2, 0, s1.length(), s2.length()));

        System.out.println("Longest common substring using dp " + findLCSDP(s1, s2));
    }
}
