import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Word Break
 *
 * You are given a dictionary set dictionary that contains dictionaryCount distinct words and another string txt.
 * Your task is to segment the txt string in such a way that all the segments occur in a continuous manner in the
 * original txt string and all these segments (or words) exists in our dictionary set dictionary.
 * In short you have to split the string txt using ‘ ‘(single white space delimiter) in such a way that
 * every segment(or word exists in our dictionary.

 * Same word from the dictionary can be used multiple times when splitting the given string.
 * Example: Suppose our Dictionary is {“to”, “do”, “todo”}
 * and txt is “totodo” then it can also be segmented as
 *
 * “to to do”. Here the word “to” from the dictionary is being used twice.

 * Output Format:
 * Return array of strings containing all different possible segmentation arrangements for the txt string.
 *
 * Constraints:
 * 1 <= dictionaryCount <= 500
 * 1 <= |txt| <= 19
 * 1<= lengths of words in dictionary <= 19
 * All the characters in txt and words in dictionary are lower case English alphabets.

 * Sample Test Case:
 * 7
 * kick
 * start
 * kickstart
 * is
 * awe
 * some
 * awesome
 * txt = kickstartisawesome
 *
 * Sample Output:
 * kick start is awe some
 * kick start is awesome
 * kickstart is awe some
 * kickstart is awesome
 *
 * Explanation:
 * There are only 4 possible segmentations possible for the given txt. All of which are mentioned above.
 * Consider first segmented string: “kick start is awe some”
 * Here all the words: kick, start, is, awe and some exists in our dictionary and these words are continuous substrings
 * of the txt string “kickstartisawesome”.
 * Similarly, other three segmentations satisfy the same conditions and hence are valid segmentations of the given string.
 *
 * Recursion:
 *
 * 1) Cut the txt at different position
 * 2) If that cut is present is dictionary, repeat the cutting for the rest of the string
 *
 * Time Complexity: L = length of txt, n = number of words in dictionary, Len = len of words in dictionary
 * O(2^L * L * n + n * Len)
 *
 * 2^L arrangements
 * L*n for contains
 * n * Len - populate dictionary set
 *
 * Space Complexity: O(n * Len + 2^L * L)
 *
 * 2^L = arrangements - each arrangement is of size L
 * n * Len - dictionary set
 *
 * DP:
 *
 * 1) dp[i] means the different splits for substring starting at index i and ending in end
 *
 * Time Complexity: O(L^2 * L + n * len)
 * where L = generate substring
 * n * len for dictionary set
 *
 * Space Complexity: O(L^2 + n * len)
 * where L^2 = DP
 * n * len = dictionary set
 *
 * resources/WorkBreakRecursion.jpg
 * resources/WordBreakDP.jpg
 *
 */
public class WordBreakProblem {

    private static List<String> wordBreak(List<String> dictionary, String txt) {
        //Create set for dictionary contains operation
        HashSet<String> dictSet = new HashSet<>();

        for(String word: dictionary) {
            dictSet.add(word);
        }

        List<String> result = new ArrayList<>();
        wordBreakRecursion(dictSet, txt, result, 0, "");
        return result;
    }

    private static void wordBreakRecursion(HashSet<String> dictSet, String txt, List<String> result, int i, String soFar) {

        //Base Case
        if(i == txt.length()) {
            result.add(soFar);
            return;
        }

        //For all possible cuts
        for(int k = i; k < txt.length(); k++) {
            String substr = txt.substring(i, k + 1);

            if(dictSet.contains(substr)) {
                if(soFar.isEmpty()) {
                    wordBreakRecursion(dictSet, txt, result, k + 1, substr);
                } else {
                    wordBreakRecursion(dictSet, txt, result,k + 1, soFar + " " + substr);
                }
            }
        }
    }

    private static List<String> wordBreakDp(List<String> dictionary, String txt) {

        //Create dictionary set for contains operation
        HashSet<String> dict = new HashSet<>();

        for(String word: dictionary) {
            dict.add(word);
        }

        //Identify dp table
        //2 changing params i and soFar
        List<String>[] dp = new ArrayList[txt.length() + 1];

        //Initialize dp table
        //Base case
        for(int i = 0; i <= txt.length(); i++) {
            dp[i] = new ArrayList<>();
        }
        dp[txt.length()].add("");

        //Traversal direction
        //i = 0 to txt.length in recursion
        //txt.length() - 1 to 0
        for(int i = txt.length() - 1; i >= 0; i--) {
            //Populate dp table

            //For all posssible cuts
            for(int k = i; k < txt.length(); k++) {
                String substr = txt.substring(i, k + 1);

                if(dict.contains(substr)) {

                    for(String s: dp[k + 1]) {
                        if(s.isEmpty()) {
                            dp[i].add(substr);
                        } else {
                            dp[i].add(substr + " " + s);
                        }

                    }
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[0]; //Recursion started from i = 0
    }

    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("kick");
        dictionary.add("start");
        dictionary.add("kickstart");
        dictionary.add("is");
        dictionary.add("awe");
        dictionary.add("some");
        dictionary.add("awesome");

        String txt = "kickstartisawesome";

        List<String> result = wordBreak(dictionary, txt);

        System.out.println("The possible word break are ");
        for(String word: result) {
            System.out.println(word);
        }

        List<String> result1 = wordBreakDp(dictionary, txt);

        System.out.println("The possible word break are ");
        for(String word: result) {
            System.out.println(word);
        }
    }

}
