package IK.Graphs.Homework1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Shortest String Transformation Using A Dictionary
 * You are given a dictionary called words and two string arguments called
 * start and stop. All given strings have equal length.
 *
 * Transform string start to string stop one character per step using words
 * from the dictionary.
 * For example, "abc" → "abd" is a valid transformation step because only one character is changed ("c" → "d"),
 * but "abc" → "axy" is not a valid one because two characters are changed ("b" → "x" and "c" → "y").
 *
 * You need to find the shortest possible sequence of strings (two or more) such that:
 *
 * First string is start.
 * Last string is stop.
 * Every string (except the first one) differs from the previous one by exactly one character.
 * Every string (except, possibly, first and last ones) are in the dictionary of words.
 * Example One
 * {
 * "words": ["cat", "hat", "bad", "had"],
 * "start": "bat",
 * "stop": "had"
 * }
 * Output:
 *
 * ["bat", "bad", "had"]
 * OR
 *
 * ["bat", "hat", "had"]
 * In "bat", change "t" → "d" to get "bad".
 * In "bad", change "b" → "h"to get "had".
 *
 * OR
 *
 * In "bat", change "b" → "h" to get "hat".
 * In "hat", change "t" → "d" to get "had".
 *
 * Example Two
 * {
 * "words": [],
 * "start": bbb,
 * "stop": bbc
 * }
 * Output:
 *
 * ["bbb", "bbc"]
 * In "bbb", the last character to "b" and get "bbc".
 *
 * Example Three
 * {
 * "words": [],
 * "start": "zzzzzz",
 * "stop": "zzzzzz"
 * }
 * Output:
 *
 * ["-1"]
 * No sequence of strings exists that would satisfy all requirements. For example, ["zzzzzz", "zzzzzz"] does not satisfy requirement #3.
 * In such situations, ["-1"] is the correct answer.
 *
 * Notes
 * If two or more such sequences exist, return any.
 * If no such sequence is there to be found, ["-1"] (a sequence of one string, "-1") is the correct answer.
 * Constraints:
 *
 * All input strings consist of lowercase English letters.
 * 0 <= total number of characters in all dictionary words combined <= 105
 * Strings in words are not in any particular order.
 * There may be duplicates in words.
 *
 * Approach
 *
 * 1) Do BFS with visited, distance, parent
 * 2) getNeighbors will return all the neighbors by replacing each character in word with 25 letters.
 * 3) TC for getNeighbors. Length of word = m. Total words in dictionary = n
 * Total possible neighbors = 25m for one word and 25m * n for n words.
 * Each word is converted to string and checked in dictionary which takes time m so TC = 25 * m * m * n
 * = 25m^2 *n
 *
 * SC: O(m^2) for storing 25m words of length m
 *
 * 4) getNeighbors will return all the words from dictionary which one char difference.
 * TC = to check difference = O(m * n) for one word, there are n words so O(m * n^2)
 *
 * SC: O(n * m)
 *
 * 5) Comparing 25m * mn with  mn * n
 * 6) So if n <= 25m then use second getNeighbor method else use first neighbor method.
 * 7) Do DFS on parent to return the list
 * 8) Reverse the list before returning.
 *
 * TC: O(mn * min(25m, n))
 * SC: O(mn)
 */
public class ShortestStringTransformationUsingDictionary {

    private static ArrayList<String> string_transformation(ArrayList<String> words, String start, String stop) {

        int n = words.size();
        int m = start.length();

        if(n <= 25 * m) {
            return bfsWithGetNeighbor2(words, start, stop);
        } else {
            return bfsWithGetNeighbor1(words, start, stop);
        }
    }

    private static ArrayList<String> bfsWithGetNeighbor2(ArrayList<String> words, String start, String end) {
        HashMap<String, String> parent = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();
        HashSet<String> dict = new HashSet<>();
        HashMap<String, Integer> distance = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        for(String word: words) {
            dict.add(word);
        }

        //We need to add end word to dict
        dict.add(end);

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while(!queue.isEmpty()) {
            String v = queue.remove();

            for(String w: getNeighbor2(v, dict)) {
                if(w.equals(end)) {
                    parent.put(w, v);
                    distance.put(w, distance.get(v) + 1);

                    dfs(parent, result, end, start);
                    Collections.reverse(result);
                    return result;
                } else {
                    if (dict.contains(w)) {
                        if (!visited.contains(w)) {
                            visited.add(w);
                            queue.add(w);
                            parent.put(w, v);
                            distance.put(w, distance.get(v) + 1);
                        }
                    }
                }
            }
        }
        result.add("-1");
        return result;
    }

    private static ArrayList<String> getNeighbor2(String word, HashSet<String> dict) {

        int noOfDifferentCharacters;
        ArrayList<String> result = new ArrayList<>();

        for(String s: dict) {
            noOfDifferentCharacters = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) != word.charAt(i)) {
                    noOfDifferentCharacters++;
                }
            }

            if(noOfDifferentCharacters == 1) {
                result.add(s);
            }
        }
        return result;
    }

    private static ArrayList<String> bfsWithGetNeighbor1(ArrayList<String> words, String start, String end) {

        HashMap<String, String> parent = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();
        HashSet<String> dict = new HashSet<>();
        HashMap<String, Integer> distance = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        for(String word: words) {
            dict.add(word);
        }

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while(!queue.isEmpty()) {
            String v = queue.remove();

            //The start and end can be same so we need to check for end before checking for visited.
            for(String w: getNeighbor1(v)) {
                if(w.equals(end)) {
                    parent.put(w, v);
                    distance.put(w, distance.get(v) + 1);

                    dfs(parent, result, end, start);
                    Collections.reverse(result);
                    return result;
                } else {
                    if (dict.contains(w)) {
                        if (!visited.contains(w)) {
                            visited.add(w);
                            queue.add(w);
                            parent.put(w, v);
                            distance.put(w, distance.get(v) + 1);
                        }
                    }
                }
            }
        }

        result.add("-1");
        return result;
    }

    private static void dfs(HashMap<String, String> parent, List<String> result, String end, String start) {
        result.add(end);

        //If start and end are same it goes in cycle
        if(parent.containsKey(end)) {
            String next = parent.get(end);
            if(!next.equals(start)) {
                dfs(parent, result, next, start);
            } else {
                result.add(start);
            }
        }
    }

    private static List<String> getNeighbor1(String v) {
        List<String> result = new ArrayList<>();
        char[] charString = v.toCharArray();

        for(int i = 0; i < charString.length; i++) {
            char oldChar = charString[i];

            for(char c = 'a'; c <= 'z'; c++) {
                if(c != oldChar) {
                    charString[i] = c;
                    String word = new String(charString);
                    result.add(word);
                }
            }
            charString[i] = oldChar;
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("cccw");
        words.add("accc");
        words.add( "accw");

        String start = "cccc";
        String end = "cccc";

        System.out.println(string_transformation(words, start, end));

        words = new ArrayList<>();
        start = "bbb";
        end = "bbc";

        System.out.println(string_transformation(words, start, end));
    }
}
