import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * Input: s = "helloword"
 * t = "lloo"
 * Output: "llowo"
 *
 * Note:
 *
 * If there is no such window in S that covers all characters in T,
 * return the empty string "".
 * If there is such window, you are guaranteed that there will
 * always be only one unique minimum window in S.
 *
 * Approach:
 *
 * 1) Create map of all unique characters and their count in t - O(t)
 * 2) Keep map for currentWindow and int formed to keep track of unique
 * characters in currentWindow that matched to mapT O(s) in worst case
 * 3) Keep incrementing the right window until formed == mapTSize
 * 4) Try squeezing the left pointer until we have formed == mapTSize
 * 5) Record minimum window length, left pointer and right pointer
 * 6) Repeat step 3, to increase right window.
 *
 * Time Complexity: O(s + t)
 * Space Complexity: O(s + t)
 *
 * resources/MinimumWindowSubstring1.jpg
 * resources/MinimumWindowSubstring2.jpg
 *
 * Find the smallest substring which contains all characters from string
 * or set
 *
 * Sliding window
 * Controlling set
 *
 * Reason for using map.get(c).intValue()
 * Now when you compare two Integer object using a == operator,
 * Java doesn't compare them by value, but it does reference comparison.
 * What this means is even if the two integer has the same value, == can return
 * false because they are two different objects in the heap.
 * That's why you should use equals() method to compare objects.
 *
 * or use intValue() - their int value.
 *
 * *********************************************************************
 * Brute Force Approach
 *
 * 1) Find all the substrings of string s - O(n^2)
 * 2) For each substring, check if it contains same number of unique characters as t.
 *    O(n)
 * 3) Keep track of minLength and minString, which contains same number of unique
 * characters as t.
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(s + t) //mapT and substringMap
 *
 * *****************************************************************
 * Find minimum substring which contains all characters from set t
 * Controlling Set
 *
 * s = "helloworld"
 * t = {'l', 'w', 'o'}
 * Output: worl
 *
 * Note/Constraint: t contains no duplicate character
 *
 * Brute Force Approach
 * 1) Generate all substrings of string s - O(s^2)
 * 2) Check if substring contains all characters from set - O(s)
 * 3) For each character in substring, remove character from set.
 * 4) If set becomes empty, update minLength and minSubstring
 *
 * Time Complexity: O(s^3)
 * Space Complexity: O(s + t)
 *
 * The above mentioned brute force can be optimized to O(s^2)
 * hello, hellow If you set is empty at hello, then all later substring will be greater
 * starting from h.
 *
 * Time Complexity: O(s^2)
 * Space Complexity: O(t)
 *
 * resources/MinSubstringBruteForce1.jpg
 * resources/MinSubstringBruteForce2.jpg
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {

        //Base Case
        if(s.length() == 0 || t.length() == 0) {
            return "";
        }

        //Create Map to keep count of all unique characters in t
        HashMap<Character, Integer> mapT = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            int count = mapT.getOrDefault(t.charAt(i), 0);
            mapT.put(t.charAt(i), count + 1);
        }

        int mapTSize = mapT.size();

        //Left and right pointer
        int left = 0;
        int right = 0;

        //formed to keep track of how many unique character in t are
        //present in currentWindow
        int formed = 0;

        //Map of unique characters and their count in current window
        HashMap<Character, Integer> currentWindow = new HashMap<>();

        //Minimum Window length, left and right
        int[] min = {-1, 0, 0};

        //Expand right window
        while(right < s.length()) {
            char c = s.charAt(right);

            //Add to current window
            int count = currentWindow.getOrDefault(c, 0);
            currentWindow.put(c, count + 1);

            //If frequency matches to one in mapT, then increment formed
            if(mapT.containsKey(c) && currentWindow.get(c).intValue() == mapT.get(c).intValue()) {
                formed++;
            }

            //Squeeze the left window
            while(left <= right && formed == mapTSize) {

                //Record minimum window length, left and right
                if(min[0] == -1 || right - left + 1 < min[0]) {
                    min[0] = right - left + 1;
                    min[1] = left;
                    min[2] = right;
                }

                c = s.charAt(left);

                //Remove from current window
                currentWindow.put(c, currentWindow.get(c) - 1);

                if(mapT.containsKey(c) && currentWindow.get(c).intValue() < mapT.get(c).intValue()) {
                    formed--;
                }

                //Move/squeeze left pointer
                left++;
            }

            //Keep expanding right window
            right++;
        }
        return min[0] == -1 ? "": s.substring(min[1], min[2] + 1);
    }

    public static String minWindowBruteForce(String s, String t) {

        //Create map of all unique characters in t and their count
        HashMap<Character, Integer> mapT = new HashMap<>();

        int minLength = -1;
        String minString = "";

        for(int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = mapT.getOrDefault(c, 0);
            mapT.put(c, count + 1);
        }

        int mapTSize = mapT.size();

        //Create all substrings of string s
        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);

                //Check if substring contains all characters with same
                //count as in t.
                HashMap<Character, Integer> substringMap = new HashMap<>();

                //formed represent number of unique character matched in t
                int formed = 0;

                for(int k = 0; k < substring.length(); k++) {
                    char c = substring.charAt(k);
                    int count = substringMap.getOrDefault(c, 0);
                    substringMap.put(c, count + 1);

                    if(mapT.containsKey(c) && mapT.get(c).intValue() ==
                                        substringMap.get(c).intValue()) {
                        formed++;
                    }
                    //Update the minLength and minString
                    if(formed == mapTSize) {
                        if(minLength == -1 || minLength > substring.length()) {
                            minLength = substring.length();
                            minString = substring;
                        }
                    }
                }
            }
        }
        return minString;
    }

    //Time Complexity: O(s^3)
    //Space Complexity: O(s + t)
    public static String minSubstringControllingSet(String s, HashSet<Character> t) {

        //Base Case
        if(s.length() == 0 || t.size() == 0) {
            return "";
        }

        int minLength = Integer.MAX_VALUE;
        String minSubstring = "";

        //Generate all substrings
        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 1; j <= s.length(); j++) {

                String substring = s.substring(i, j);
                HashSet<Character> set = new HashSet<>(t);

                for (int k = 0; k < substring.length(); k++) {
                    set.remove(substring.charAt(k));

                    if(set.size() == 0) {
                        //Update min
                        if(substring.length() <= minLength) {
                            minLength = substring.length();
                            minSubstring = substring;
                        }
                        break; //No need to remove further
                    }
                }
            }
        }
        return minSubstring;
    }

    //Time Complexity: O(s^2)
    //Space Complexity: O(t)
    public static String minSubstringControllingSetOptimized(String s, HashSet<Character> t) {

        //Base Case
        if(s.length() == 0 || t.size() == 0) {
            return "";
        }

        int minLength = Integer.MAX_VALUE;
        String minSubstring = "";

        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> tSet = new HashSet<>(t);
            for (int j = i; j < s.length(); j++) {

                tSet.remove(s.charAt(j));

                if(tSet.size() == 0) {
                    String substring = s.substring(i, j + 1);
                    if(minLength >= substring.length()) {
                        minLength = substring.length();
                        minSubstring = substring;
                    }
                    break; //No need to continue j loop because you cannot get min than this
                }
            }
        }
        return minSubstring;
    }

    public static void main(String[] args) {
        String s = "helloworld";
        String t = "lloo";
        //String t = "lrw";

        System.out.println("Minimum window substring: " +minWindow(s, t));
        System.out.println("Minimum window substring brute force " +minWindowBruteForce(s, t));

        HashSet<Character> tSet = new HashSet<>();
        tSet.add('l');
        tSet.add('r');
        tSet.add('w');

        System.out.println("Minimum substring using controlling brute force O(s^3) "
                + minSubstringControllingSet(s, tSet));

        System.out.println("Minimum substring using controlling set brute force O(s^2) "
                + minSubstringControllingSetOptimized(s, tSet));
    }
}
