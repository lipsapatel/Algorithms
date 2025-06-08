package IK.DynamicProgramming.Class;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a dictionary of words and a string, find the number of ways the string can be
 * broken down into the dictionary words
 *
 * For Example:
 * String s = "catsandog"
 * wordDict = {"cats", "dog", "sand", "and", "cat", "an", "og"}
 *
 * Output: The given string s can be broken down into
 * cats an dog
 * cat sand og
 * cats and og
 *
 *  3 ways
 *
 *  Recursive Approach
 *
 *  1) Convert wordDict into HashSet so that look up costs O(1) time.
 *  2) f(s, n, wordDictSet)
 *  3) Base Case: If n == 0, string is empty and it can be broken down return 1
 *  4) wordBreakWays = 0
 *  5) Iterate for len =  1 to n
 *  6) word = s.substring(n - len, n)
 *  7) If word is present in wordDictSet then
 *  8) wordBreakWays += f(s, n - len, wordDictSet)
 *  9) return wordBreakWays
 *
 *  TC: O(2^n) At every index we are deciding whether to break or not
 *  SC: O(n)
 *
 *  DP Approach
 *
 *  1) Create int dp array of size n + 1
 *  2) Base Case: dp[0] = 1
 *  3) Iterate i = 1 to n
 *  4) Iterate len = 1 to i
 *  5) word = s.substring(i - len, i)
 *  6) If wordDictSet.contains(word)
 *  7) dp[i] += dp[i - len]
 *  8) Return dp[n]
 *
 *  TC: O(n^2)
 *  SC: O(n)
 */
public class WordBreakCount {

    private static int wordBreakCountRecursive(String s, List<String> wordDict) {

        HashSet<String> wordDictSet = new HashSet<>(wordDict);

        int n = s.length();

        return wordBreakCountRecursiveHelper(s, n, wordDictSet);
    }

    private static int wordBreakCountRecursiveHelper(String s, int n, HashSet<String> wordDictSet) {

        //Base Case
        if(n == 0) { //No string left so return 1
            return 1;
        }

        int wordBreakWays = 0;

        for(int len = 1; len <= n; len++) {

            String word = s.substring(n - len, n);

            if(wordDictSet.contains(word)) {
                wordBreakWays += wordBreakCountRecursiveHelper(s, n - len, wordDictSet);
            }
        }
        return wordBreakWays;
    }

    private static int wordBreakCountDP(String s, List<String> wordDict) {
        HashSet<String> wordDictSet = new HashSet<>(wordDict);

        int n = s.length();

        int[] dp = new int[n + 1];

        //Base Case
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {

            for(int len = 1; len <= i; len++) {

                String word = s.substring(i - len, i);

                if(wordDictSet.contains(word)) {
                    dp[i] += dp[i - len];
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = new ArrayList<>();

        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("og");
        wordDict.add("and");
        wordDict.add("an");
        wordDict.add("cat");

        System.out.println("Recursive wordBreak count " +wordBreakCountRecursive(s, wordDict));

        System.out.println("DP wordBreak count " + wordBreakCountDP(s, wordDict));
    }
}
