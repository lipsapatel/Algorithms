import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Word Break Count
 * You are given a list of strings named dictionary that contains dictionaryCount distinct words and another string txt.
 * Your task is to count the possible number of the word-breaks of the txt string in such a way that all the word occur
 * in a continuous manner in the original txt string and all these words exist in our dictionary set dictionary.
 *
 * In short, you have to split the string txt using ‘ ‘(single white space delimiter) in such a way that every segment
 * (word) exists in our dictionary.
 *
 * The same word from the dictionary can be used multiple times when splitting the given string.
 *
 * Example: Suppose our Dictionary is {“to”, “do”, “todo”}
 * and txt is “totodo” then it can also be segmented as
 * “to to do”. Here the word “to” from the dictionary is being used twice.
 *
 * Output Format:
 * Return an integer denoting all different possible word-break arrangements for the txt string.
 * This integer could be large so output it modulo 10^9 + 7.
 *
 * If dictionaryCount = 2 , dictionary = [“hello” , “world”] and
 * txt = “helloworld” then custom input format will be:
 *
 * 2
 *
 * hello
 * world
 *
 * For the above-provided custom input, output there is only one way to partition the txt string ( “hello world”), so the output will be:
 * 1
 *
 * Constraints:
 *     1 <= dictionaryCount <= 200000
 *     1 <= length(txt) <= 2000
 *     1<= length of words in dictionary <= 100
 *     All the characters in txt and words in dictionary are lower case English alphabets.

 * Sample Test Case:
 * 7
 * kick
 * start
 * kickstart
 * is
 * awe
 * some
 * awesome
 *
 * kickstartisawesome
 *
 * Sample Output:
 * 4
 *
 * Explanation:
 * There are only 4 possible segmentations possible for the given txt and 4 % 1000000007 = 4. All four of which are mentioned below:

 *     kick start is awe some
 *     kick start is awesome
 *     kickstart is awe some
 *     kickstart is awesome
 *
 * Consider first word-break arrangement: “kick start is awe some”
 * Here all the words: kick, start, is, awe and some exist in our dictionary and these words are continuous substrings
 * of the txt string “kickstartisawesome”.
 *
 * Similarly, other three word-breaks satisfy the same conditions and hence are valid word-breaks of the given string
 *
 *  * Recursion:
 *  *
 *  * 1) Cut the txt at different position
 *  * 2) If that cut is present is dictionary, repeat the cutting for the rest of the string
 *  *
 *  * Time Complexity: L = length of txt, n = number of words in dictionary, Len = len of words in dictionary
 *  * O(2^L * L * n + n * Len)
 *  *
 *  * 2^L arrangements
 *  * L*n for contains
 *  * n * Len - populate dictionary set
 *  *
 *  * Space Complexity: O(n * Len + 2^L * L)
 *  *
 *  * 2^L = arrangements - each arrangement is of size L
 *  * n * Len - dictionary set
 *  *
 *  * DP:
 *  *
 *  * 1) dp[i] means the different splits for substring starting at index i and ending in end
 *  *
 *  * Time Complexity: O(L^2 * L + n * len)
 *  * where L = generate substring
 *  * n * len for dictionary set
 *  *
 *  * Space Complexity: O(L + n * len)
 *  * where L = DP
 *  * n * len = dictionary set
 *
 *  resources/WordBreakCount.jpg
 */
public class WordBreakCount {

    private static int wordBreakCountRecursion(List<String> dictionary, String txt) {

        //Create set for contains operation
        HashSet<String> dictSet = new HashSet<>(dictionary);

        return wordBreakCountRecursionHelper(dictSet, txt, 0) % 1000000007;
    }

    private static int wordBreakCountRecursionHelper(HashSet<String> dictSet, String txt, int i) {

        //Base Case
        if(i == txt.length()) {
            return 1;
        }

        //All possible cuts
        int count = 0;
        for(int k = i; k < txt.length(); k++) {

            String subString = txt.substring(i, k + 1);

            //If present in dictionary
            if(dictSet.contains(subString)) {
                count = count + wordBreakCountRecursionHelper(dictSet, txt, k + 1);
            }
        }
        return count;
    }

    private static int wordBreakCountDp(List<String> dictionary, String txt) {

        //Add to set for contains operation
        HashSet<String> dictSet = new HashSet<>(dictionary);

        //Identify the dp table - 1 changing param i
        int[] dp = new int[txt.length() + 1];

        //Initialize the dp table
        dp[txt.length()] = 1; //Base case

        //Traversal Direction - Recursion (i = 0 to n)
        for(int i = txt.length() - 1; i >= 0; i--) {
            //Populate the dp table
            int count = 0;

            //All possible cuts
            for(int k = i; k < txt.length(); k++) {
                String subString = txt.substring(i, k + 1);

                //If present in dictionary
                if(dictSet.contains(subString)) {
                    count = (count + dp[k + 1]) % 1000000007;
                }
            }
            dp[i] = count;
        }

        return dp[0];
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

        System.out.println("The number of different combination of word break possible using recursion " + wordBreakCountRecursion(dictionary, txt));
        System.out.println("The number of different combination of word break possible using DP " + wordBreakCountDp(dictionary, txt));
    }
}
