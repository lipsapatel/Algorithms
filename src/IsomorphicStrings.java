/**
 * Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 Example 1:

 Input: s = "egg", t = "add"
 Output: true
 Example 2:

 Input: s = "foo", t = "bar"
 Output: false
 Example 3:

 Input: s = "paper", t = "title"
 Output: true
 Note:
 You may assume both s and t have the same length.

 TC: O(n)
 */
public class IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        //256 in ASCII covers all
        int[] m1 = new int[256];
        int[] m2 = new int[256];

        for (int i = 0; i < s.length(); i++) {

            if (m1[s.charAt(i)] != m2[t.charAt(i)]) {
                return false;
            }

            m1[s.charAt(i)] = m2[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "egg";
        String t = "add";

        System.out.println("The string " + s + " and " + t + " is isomorphic: " + isIsomorphic(s, t));
    }
}
