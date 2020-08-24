import java.util.HashMap;
import java.util.HashSet;

/**
 * Longest Substring With Exactly Two Distinct Characters
 * Given a string s of length n, find the length of the longest substring ss,
 * that contains exactly two distinct characters.
 *
 * There will be t test cases.
 *
 * Example One
 * Input:
 * 2
 * eceba
 * abcdef
 * Output:
 * 3
 * 2
 * In the first case, 'ece' is the largest substring with exactly 2 distinct characters.
 * In the second case, 'ab' is the largest substring with exactly 2 distinct characters. Also, 'bc', 'cd', 'de', 'ef' can be considered as substring with exactly 2 distinct characters.
 *
 * Example Two
 * Input:
 * 3
 * ababababa
 * e
 * baabcbab
 *
 * Output:
 * 9
 * 0
 * 4
 *
 * In the first case, the whole string 'ababababa' is the largest substring
 * with exactly 2 distinct characters.
 * In the second case, there is no substring with exactly 2 distinct characters.
 * In the third case, 'baab' is the largest substring with exactly 2
 * distinct characters.
 *
 * Notes
 * Input Parameters: There is only one argument s, denoting the input string.
 * Output: Return an integer len, denoting length of ss.
 * (If there are no such substrings, then return 0)
 * Constraints:
 * •	1 <= t <= 80
 * •	1 <= n <= 10^5
 * •	s may contain upper case alphabets, lower case alphabets and numerical values.
 *
 * Brute Force Solution
 *
 * A naive approach would be to iterate over all possible substrings ss of
 * input string s, check if it is a valid (i.e. contains exactly two distinct
 * characters) substring or not. Maximum value of length out of all valid
 * substrings is the required output.
 *
 * While iterating over all substrings, we will iterate in such a manner that
 * first all the substrings which starts from 0th index are covered,
 * then the ones which starts from 1st index are covered, then ones from 2nd index,
 * 3rd index and so on. And, while iterating over all substrings starting at ith
 * index, we will break iteration while we hit on the third distinct character
 * found at index j>i.
 *
 * Time Complexity:
 * O(n*n) where n is the length of string s.
 * In the worst case, where we have only two distinct characters in the whole string,
 * all substrings are valid. So, using this approach, code will iterate over
 * all n^2 substrings.
 *
 * Auxiliary Space Used:
 * O(1).
 *
 * Two Pointers and Sliding Window approach
 * Similar to minimum window substring
 *
 * Expand right pointer and squeeze left pointer
 *
 * Approach:
 *
 * 1) Keep expanding right pointer until size is > 2.
 * 2) If it becomes == 2 record max
 * 3) If it becomes > 2 the squeeze from left
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) The size of hashmap will be 3 at max which is constant
 *
 */
public class LongestSubstringWithTwoDistinctCharacters {

    private static int getLongestSubstringWith2DistinctCharactersBruteForce(String s) {
        int maxLength = 0;
        String maxSubstring = "";

        HashSet<Character> set = new HashSet<>();

        for(int i = 0; i < s.length(); i++) {
            set.clear();
            for(int j = 0; j < s.length(); j++) {

                set.add(s.charAt(j));

                if(set.size() > 2) {
                    break;
                }

                if(set.size() == 2) {
                    if(j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        maxSubstring = s.substring(i, j + 1);
                    }
                }
            }
        }
        System.out.println(maxSubstring);
        return maxLength;
    }

    private static int getLongestSubstringWith2DistinctCharacters(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        int maxLength = 0;
        String maxString = "";

        while(right < s.length()) {

            char c = s.charAt(right);

            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);

            //Squeeze from left
            while(map.size() > 2) {
                char leftChar = s.charAt(left);

                map.put(leftChar, map.get(leftChar) - 1);
                if(map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            if(map.size() == 2) {
                if(right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    maxString = s.substring(left, right + 1);
                }
            }
            right++;
        }
        System.out.println(maxString);
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "eceba";
        System.out.println("Brute Force: " + getLongestSubstringWith2DistinctCharactersBruteForce(s));
        System.out.println("Two Pointers: " + getLongestSubstringWith2DistinctCharacters(s));

        s = "abcdefg";
        System.out.println("Brute Force: " + getLongestSubstringWith2DistinctCharactersBruteForce(s));
        System.out.println("Two Pointers: " + getLongestSubstringWith2DistinctCharacters(s));
    }
}
