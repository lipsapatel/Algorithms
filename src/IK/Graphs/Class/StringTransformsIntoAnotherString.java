package IK.Graphs.Class;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 * Example 2:
 *
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 *
 *
 * Constraints:
 *
 * 1 <= str1.length == str2.length <= 104
 * str1 and str2 contain only lowercase English letters.
 *
 * Approach
 * 1) If str1 and str2 are same then return true
 * 2) If the length of str1 and str2 is not same then return false
 * 3) One character cannot map to more than one character which means the outdegree = 1
 * 4) If there is cycle in graph, which means to break the cycle we need vertex with outdegree = 0
 * 5) Create Map and at any time we get more mapping then return false.
 * 6) If str2 contains all 26 character, we cannot break the cycle so return false
 *
 *  For example: s1 = abcdefghijklmnopqrstuvwxyz
 *  s2 = zyxwvutsrqponmlkjihgfedcba
 *  Return false
 *
 *  s1 = abcdefghijklmnopqrstuvwxyz = 26 char
 *  s2 = bcdefghijklmnopqrstuvwxyzz = 25 unique char
 *
 *  TC: O(n) where n = length of string
 *  SC: O(n) - for map and hashset
 */
public class StringTransformsIntoAnotherString {

    private static boolean convertString(String s1, String s2) {

        if(s1.length() != s2.length()) {
            return false;
        }

        if(s1.equals(s2)) {
            return true;
        }

        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> s2Set = new HashSet<>();

        for(int i = 0; i < s1.length(); i++) {

            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            s2Set.add(c2);

            if(map.containsKey(c1)) {
                if(map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }
        return s2Set.size() < 26;
    }

    public static void main(String[] args) {
        String s1 = "abcdefghijklmnopqrstuvwxyz";
        String s2 = "zyxwvutsrqponmlkjihgfedcba";

        System.out.println("Can transform: " + convertString(s1, s2));

        s2 = "zyxwvutsrqponmlkjihgfedcaa";

        System.out.println("Can transform: " + convertString(s1, s2));
    }
}
