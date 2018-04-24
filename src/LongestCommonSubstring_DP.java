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
 * Check all the substrings from the first string with the second string.
 * And keep the track of maximum.
 * Time Complexity: O(n2 *m) - O(n2) for substring and O(m) for checking all substring in second
 * string.
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
 */
public class LongestCommonSubstring_DP {

    private static int findLengthOfLongestCommonSubstring(char[] A, char[] B) {

        int[][] LCS = new int[A.length + 1][B.length + 1];

        //If A is null then LCS = 0
        for (int i = 0; i <= B.length; i++) {
            LCS[0][i] = 0;
        }

        //If B is null then LCS = 0
        for (int i = 0; i <= A.length; i++) {
            LCS[i][0] = 0;
        }

        //Fill the rest of the matrix
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {

                if (A[i - 1] == B[j - 1]) {
                    LCS[i][j] = 1 + LCS[i - 1][j - 1];
                } else {
                    LCS[i][j] = 0;
                }
            }
        }

        //Find the maximum in the matrix
        int result = -1;
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= B.length; j++) {

                if (result < LCS[i][j]) {
                    result = LCS[i][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String A = "tutorialhorizon";
        String B = "dynamictutorialProgramming";
        System.out.println("LCS length : " + findLengthOfLongestCommonSubstring(A.toCharArray(), B.toCharArray()));
    }
}
