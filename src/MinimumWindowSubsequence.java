/**
 * Given strings S and T, find the minimum (contiguous) substring W of S,
 * so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T,
 * return the empty string "". If there are multiple such minimum-length windows,
 * return the one with the left-most starting index.
 *
 * Example 1:
 *
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window
 * must occur in order.
 *
 * Note:
 *
 *     All the strings in the input will only contain lowercase letters.
 *     The length of S will be in the range [1, 20000].
 *     The length of T will be in the range [1, 100].
 *
 * Example 2:
 *
 * S = "abcdbede"
 * T = "bde"
 * Output: bede
 *
 * Time Complexity: O(mn)
 * Space Complexity: O(1)
 *
 * Approach: Two pointer
 *
 * 1) Move forward and find the last character of T in S.
 * 2) Move backward and find the first character of T in S.
 * 3) Update the min, if subsequence is smaller
 * 4) Repeat the process(Step 1 to 3) until S is done.
 *
 * resources/MinimumWindowSubsequence1.jpg
 * resources/MinimumWindowSubsequence2.jpg
 * resources/MinimumWindowSubsequence3.jpg
 * resources/MinimumWindowSubsequence4.jpg
 * resources/MinimumWindowSubsequence5.jpg
 */
public class MinimumWindowSubsequence {

    private static String minWindowSubsequence(String S, String T) {
        if(S.length() == 0 || T.length() == 0) {
            return "";
        }

        int minLength = Integer.MAX_VALUE;
        String minSubsequence = "";
        int right = 0;

        while(right < S.length()) {
            int tidx = 0;

            //Move Forward
            while(right < S.length()) {
                if(S.charAt(right) == T.charAt(tidx)) {
                    tidx++;
                }

                if(tidx == T.length()) {
                    break; //Don't increment right because there's nothing to compare
                }
                right++;
            }

            if(right == S.length()) {
                break; //Subsequence not found
            }

            //Go backward
            int left = right;
            tidx = T.length() - 1;
            while(left >= 0) {

                if(S.charAt(left) == T.charAt(tidx)) {
                    tidx--;
                }

                if(tidx < 0) {
                    break; //Don't increment left because there's nothing to compare
                }
                left--;
            }

            //Record the minimum length and minimum subsequence
            if(right - left + 1 < minLength) {
                minLength = right - left + 1;
                minSubsequence = S.substring(left, right + 1);
            }

            //Repeat the process
            right = left + 1;
        }
        return minSubsequence;
    }

    public static void main(String[] args) {
        String S = "abcdbede";
        String T = "bde";

        System.out.println("Minimum window subsequence " + minWindowSubsequence(S, T));
    }
}
