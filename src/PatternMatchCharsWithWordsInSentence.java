import java.util.HashMap;
import java.util.Map;

/**
 * Part 1 of problem: It's not recursive
 * Given sentence with space separated words
 * and given pattern of characters
 *
 * Sentence: red blue green blue
 * Pattern: abcb
 *
 * Part 2: where space is not given
 *
 * Example
 *
 * redbluegreenblue - String
 * abcb - Pattern
 * true
 *
 * String: abcabdabbabd
 * Pattern: abcb
 * true
 *
 * String: bluee
 * Pattern: abb
 * true
 *
 * String: blued
 * Pattern: abb
 * false
 *
 * Time Complexity: n! because branching factor decreases at each level by 1
 *
 */
public class PatternMatchCharsWithWordsInSentence {

    private static boolean patternMatches(String pattern, int pi, String str, int si, Map<Character, String> map) {

        //If pattern and string both are empty, return true
        if (pi == pattern.length() && si == str.length()) {
            return true;
        }

        //If pattern is empty and string is not return false
        if (pi == pattern.length() && si != str.length()) {
            return false;
        }

        //If string is empty and pattern is not return false
        if (si == str.length() && pi != pattern.length()) {
            return false;
        }

        //For all possible string length 1 or more
        for (int i = si; i < str.length(); i++) {

            //If map contains the key then the value and string has to match
            if (map.containsKey(pattern.charAt(pi))) {

                if (map.get(pattern.charAt(pi)).equals(str.substring(si, i + 1))) {

                    if (patternMatches(pattern, pi + 1, str, i + 1, map)) {
                        return true;
                    }
                }
            } else {

                //If map doesn't contain mapping, add one and do recursive call
                map.put(pattern.charAt(pi), str.substring(si, i + 1));

                if (patternMatches(pattern, pi + 1, str, i + 1, map)) {
                    return true;
                }

                //remove entry from map while going back up
                map.remove(pattern.charAt(pi));
            }
        }
        return false;
    }

    public static void main(String[] args) {

        String str2 = "bluee";
        String pattern2 = "abb";

        System.out.println("Pattern matches: " + patternMatches(pattern2, 0, str2, 0, new HashMap<Character, String>()));

        String str = "redbluegreenblue";
        String pattern = "abcb";

        System.out.println("Pattern matches: " + patternMatches(pattern, 0, str, 0, new HashMap<Character, String>()));

        String str1 = "abcabdabbabd";
        String pattern1 = "abcb";

        System.out.println("Pattern matches: " + patternMatches(pattern1, 0, str1, 0, new HashMap<Character, String>()));

        String str3 = "blued";
        String pattern3 = "abb";

        System.out.println("Pattern matches: " + patternMatches(pattern3, 0, str3, 0, new HashMap<Character, String>()));

        String str4 = "redbluebluee";
        String pattern4 = "abb";

        System.out.println("Pattern matches: " + patternMatches(pattern4, 0, str4, 0, new HashMap<Character, String>()));

    }
}
