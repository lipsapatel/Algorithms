package IK.DynamicProgramming.Class;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict,
 * add spaces in s to construct a sentence where each word is a
 * valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused
 * multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 *
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple",
 * "pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 * Recursive Approach
 * 1) f(s, n, wordDict, sentenceList, "")
 * 2) Base Case: If n == 0, nothing left in string so add sentence.trim()
 * to list
 * 3) and return
 * 4) Iterate from len = 1 to n
 * 5) word = s.substring(n - len, n)
 * 6) If wordDictSet contains word
 * 7) f(s, n - len, wordDictSet, sentenceList, word + " " + sentence)
 * 8) Return sentenceList
 *
 * TC: O(2^n) At each index we are deciding whether to break or not
 * SC: O(n)
 *
 * DP Approach
 *
 * 1) Convert wordDict to wordDictSet for O(1) time lookup
 * 2) Create List<List<String>> dp table
 * 3) Fill the dp[0] with "" string
 * 4) Iterate from i = 1 to n
 * 5) Iterate from len = 1 to i
 * 6) word = s.substring(i - len, i)
 * 7) If word is present in dictionary
 * 8) dp.get(i - len) this will return list
 * 9) Append word to this list and add to dp[i]
 * 10) Return dp[n]
 *
 * TC: O(n^2)
 * SC: O(n^2)
 */
public class WordBreakIIReturnAllSentence {

    private static List<String> wordBreakRecursive(String s, List<String> wordDict) {

        int n = s.length();

        List<String> sentenceList = new ArrayList<>();

        HashSet<String> wordDictSet = new HashSet<>(wordDict);

        wordBreakRecursiveHelper(s, n, wordDictSet, sentenceList, "");

        return sentenceList;
    }

    private static void wordBreakRecursiveHelper(String s, int n, HashSet<String> wordDictSet,
                                                 List<String> sentenceList, String sentence) {

        //Base Case
        if(n == 0) {
            //Nothing left in String s
            sentenceList.add(sentence.trim());
            return;
        }

        for(int len = 1; len <= n; len++) {

            String word = s.substring(n - len, n);

            if(wordDictSet.contains(word)) {
                wordBreakRecursiveHelper(s, n - len, wordDictSet, sentenceList,
                        word + " " + sentence);
            }
        }
    }

    private static List<String> wordBreakDp(String s, List<String> wordDict) {
        HashSet<String> wordDictSet = new HashSet<>(wordDict);

        int n = s.length();

        List<List<String>> dp = new ArrayList<>(n + 1);

        for(int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }

        List<String> empty = new ArrayList<>();
        empty.add("");

        dp.set(0, empty);

        for(int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();

            for(int len = 1; len <= i; len++) {

                String word = s.substring(i - len , i);

                if(wordDictSet.contains(word)) {

                    List<String> sentenceList = dp.get(i - len);

                    for(String sen: sentenceList) {
                        String sentence = sen + " " + word;
                        list.add(sentence.trim());
                    }
                }
            }
            dp.set(i, list);
        }

        return dp.get(n);
    }

    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<>();

        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("sand");
        wordDict.add("dog");
        wordDict.add("and");

        String s = "catsanddog";

        System.out.println(wordBreakRecursive(s, wordDict));
        System.out.println(wordBreakDp(s, wordDict));
    }
}
