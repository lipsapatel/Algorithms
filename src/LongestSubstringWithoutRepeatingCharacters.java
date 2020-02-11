import java.util.HashSet;

/**
 * Given a string, find the length of the longest substring without repeating characters.

 Example 1:

 Input: "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 Approach:

 Sliding Window Approach

 1) Keep all the unique substring in Set.
 2) Take 2 pointers: left pointer and right pointer
 3) Both point to 0 index initially
 4) Now if the character is not present in the set
        add to set
        increment the right pointer
        update maxLength
 5) If it's present in set, remove from set till duplicate is removed
        remove first element from set
        increment left pointer

 Time Complexity: O(n)
 Space Complexity: O(min(26, n)) for set
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {

        int maxLength = 0;
        int left = 0;
        int right = 0;

        HashSet<Character> set = new HashSet<>();

        while (left < s.length() && right < s.length()) {
            if(!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                if(right - left > maxLength) {
                    maxLength = right - left;
                }
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String input = " ";
        String input1 = "pkwwpez";
        System.out.println("Max length substring without repeating characters is: " + lengthOfLongestSubstring(input1));
    }

}
