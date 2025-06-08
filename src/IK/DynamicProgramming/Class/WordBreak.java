package IK.DynamicProgramming.Class;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a
 * space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 *
 * Recursive Approach
 *
 * 1) Convert dictionary to hashSet for O(1) search
 * 2) Base Case: When n == 0, no string left so return true
 * 3) f(word, n) Recursive function
 * 4) Iterate from len = 1 to n
 * 5) Word = substring(n - len, n)
 * 6) If the word is present in dictionary
 * 7) boolean wordBreak = f(word, n - len)
 * 8) If wordBreak is true, return true
 * 9) Return false outside
 *
 * Example: (leetcode, 8)
 * len = 1 to 8
 * word = substring(8 - 1, 8) = e
 *        substring(8 - 2, 8) = de
 *        .
 *        .
 *        .
 *        substring(8 - 4, 8) = code here word is present in dictionary so make recursive call(word, 4)
 *        .
 *        .
 *        substring(8 - 8, 8) = leetcode
 *
 * TC: O(2^n) At every point we are making decision whether to break the word or not
 * SC: O(n)
 *
 * DP Approach
 *
 * 1) Create boolean table of size n + 1
 * 2) Base Case: dp[0] = true
 * 3) Iterate from i = 1 to n
 * 4) Iterate from len = 1 to i
 * 5) word = s.substring(i - len, i)
 * 6) If dict contains word, then dp[i] = dp[i - len]
 * 7) Return dp[n]
 *
 * TC: O(n^2)
 * SC: O(n)
 */
public class WordBreak {

    private static boolean wordBreak(String s, List<String> wordDict) {

        HashSet<String> wordDictHashSet = new HashSet<>(wordDict);

        int n = s.length();

        return wordBreakRecursive(s, n, wordDictHashSet);
    }

    private static boolean wordBreakRecursive(String s, int n, HashSet<String> wordDictHashSet) {

        //Base Case: If n == 0, no string left so return true
        if(n == 0) {
            return true;
        }

        for(int len = 1; len <= n; len++) {
            String word = s.substring(n - len, n);

            if(wordDictHashSet.contains(word)) {

                boolean wordbreak = wordBreakRecursive(s, n - len, wordDictHashSet);

                if(wordbreak) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean wordBreakDP(String s, List<String> wordDict) {
        HashSet<String> wordDictHashSet = new HashSet<>(wordDict);

        int n = s.length();

        boolean[] dp = new boolean[n + 1];

        //Base Case
        dp[0] = true;

        for(int i = 1; i <= n; i++) {
            for(int len = 1; len <= i; len++) {

                String word = s.substring(i - len, i);

                if(wordDictHashSet.contains(word) && dp[i - len]) {
                    dp[i] = true;
                    //break; //Once it becomes true, I can break out here because it's not going to change
                    //However this is not going to change TC
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        System.out.println("Word Break " + s + " :" + wordBreak(s, wordDict));
        System.out.println("Word Break " + s + " :" + wordBreakDP(s, wordDict));
    }
}
