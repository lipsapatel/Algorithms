package IK.Graphs.Class;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Example 2:
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 * Example 3:
 *
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 *
 * Approach
 *
 * 1) Construct directed graph
 * 2) Do DFS with outer loop, cycle detection and topological sort
 * 3) Compare two adjacent words and determine the edge
 * 4) If first word followed by second word and second word is prefix of the
 * first word then there is not valid alphabet because prefix always comes before
 * 5) Compare until we find mismatch and till minLength
 * 6) Return the topological order
 *
 * TC: O(N) where N = number of words given, Number of vertices = N and Number of edges = N
 * SC: (N) Where N = number of words, vertices and edges
 */
public class AlienDictionary {

    private static HashMap<Character, HashSet<Character>> graph;
    private static int time;

    private static void initializeGraph() {
        graph = new HashMap<>();
        time = 0;
    }

    private static void addEdge(Character start, Character end) {
        graph.get(start).add(end);
    }

    public static String alienOrder(String[] words) {

        initializeGraph();

        //Add all unique characters to graph
        for(String word: words) {
            for(int i =0; i < word.length(); i++) {
                graph.putIfAbsent(word.charAt(i), new HashSet<>());
            }
        }

        for(int i = 0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];

            //If second word is prefix of first word then wrong order
            if(firstWord.length() > secondWord.length() && firstWord.startsWith(secondWord)) {
                return "";
            }

            int minLength = Math.min(firstWord.length(), secondWord.length());
            for(int j = 0; j < minLength; j++) {
                if(firstWord.charAt(j) != secondWord.charAt(j)) {
                    addEdge(firstWord.charAt(j), secondWord.charAt(j));
                    break;
                }
            }
        }

        //Do DFS, detect cycle and topological sort
        HashSet<Character> visited = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Integer> depTime = new HashMap<>();

        for(Character v: graph.keySet()) {
            if(!visited.contains(v)) {
                if(dfs(v, visited, stack, depTime)) {
                    //Cycle
                    return "";
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private static boolean dfs(Character start, HashSet<Character> visited, Stack<Character> stack, HashMap<Character, Integer> depTime) {
        visited.add(start);

        for(Character v: graph.get(start)) {
            if(!visited.contains(v)) {
                if(dfs(v, visited, stack, depTime)) {
                    return true;
                }
            } else {
                if(!depTime.containsKey(v)) {
                    return true;
                }
            }
        }

        stack.add(start);
        depTime.put(start, time++);
        return false;
    }

    public static void main(String[] args) {
        String[] words = {"wrt","wrf","er","ett","rftt"};

        System.out.println("Topological order " +alienOrder(words));
    }
}
