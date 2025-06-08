package IK.DynamicProgramming.Class;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * Recursive Approach:
 *
 * 1) There are 4 operations that can be done
 * Insert a character - 1 + f(i - 1, j)
 * Delete a character - 1 + f(i, j - 1)
 * Replace a character if it doesn't matches - 1 + f(i - 1, j - 1)
 * Character matches - 0 + f(i - 1, j - 1)
 *
 * 2) Base Case
 *
 * If both strings are empty
 *  i < 0 && j < 0
 *  return 0
 *
 *  If i < 0
 *  return j + 1
 *
 *  If j < 0
 *  return i + 1
 *
 *  3) return 1 + Math.min(1 + f(i - 1, j), 1 + f(i, j - 1), x + f(i - 1, j - 1))
 *
 *  TC: O(3^n)
 *  SC: O(m + n)
 *
 *  DP Approach
 *
 *  1) Create 2D int array of size m + 1 and n + 1
 *  2) Fill in the base case
 *  3) If word1 is empty string, return j dp[0][j] = j
 *  4) If word2 is empty string, return i dp[i][0] = i
 *  5) Iterate row by row
 *  6) x = 0 if word1[i - 1] == word2[j - 1] else x = 1
 *  7) dp[i][j] = Math.min(1 + dp[i - 1][j], 1 + dp[i][j - 1], x + dp[i - 1][j - 1])
 *  8) return dp[m][n]
 *
 *  The words index for DP are from 1 to n or m
 *  0 means empty word.
 *
 *  TC: O(mn)
 *  SC: O(mn)
 */
public class EditDistance {

    private static int editDistance(String word1, String word2) {
        int m = word1.length() - 1;
        int n = word2.length() - 1;

        return editDistanceHelper(word1, word2, m, n);
    }

    private static int editDistanceHelper(String word1, String word2, int m, int n) {

        //The words index are from 0 to n - 1 or m - 1

        //Base Case
        if(m < 0 && n < 0) {
            return 0;
        }

        if(m < 0) {
            return n + 1; //Index 1 means 2 characters left
        }

        if(n < 0) {
            return m + 1; //Index 1 means 2 characters left No of chars
        }

        int x = 0;
        if(word1.charAt(m) != word2.charAt(n)) {
            x = 1;
        }

        return Math.min(1 + editDistanceHelper(word1, word2, m - 1, n), Math.min(1 + editDistanceHelper(word1, word2, m , n - 1),
                                                                                    x + editDistanceHelper(word1, word2, m - 1, n - 1)));
    }

    private static int editDistanceDP(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {

                if(i == 0) {
                    dp[i][j] = j;

                } else if(j == 0) {
                    dp[i][j] = i;

                } else {
                    int x = 1;

                    if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        x = 0;
                    }

                    dp[i][j] = Math.min(1 + dp[i - 1][j], Math.min(1 + dp[i][j - 1], x + dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";

        System.out.println("Recursive Edit distance " + editDistance(word1, word2));
        System.out.println("DP Edit Distance " + editDistance(word1, word2));
    }
}
