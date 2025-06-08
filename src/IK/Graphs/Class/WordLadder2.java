package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 500
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 * The sum of all shortest transformation sequences does not exceed 105.
 *
 * Approach
 * 1) BFS gives shortest path. By maintaining parent list and visited we get only one shortest path.
 * 2) To get all shortest path, we need to not mark node as visited until all nodes at that level are processed.
 * 3) Also maintain map of string and list for multiple paths - parentlist
 * 4) Set stores the nodes at each level
 * 5) we copy all nodes at that level to visited and level
 * 7) Do DFS to get all the paths from parentlist
 * 8) Get Neighbors will return all neighbor for word by replacing each char with a to z
 *
 * TC: O(M^2 * N) Because get Neighbor is 26M^2 because of converting char array to string
 * SC: O(MN) - parent list, visited
 */
public class WordLadder2 {

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        //If end word is not present in word list, no sequence possible
        if(!wordList.contains(endWord)) {
            return new ArrayList<>();
        }

        HashSet<String> visited = new HashSet<>();
        HashMap<String, HashSet<String>> parentList = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        HashSet<String> level = new HashSet<>();

        level.add(beginWord);
        visited.add(beginWord);
        boolean isTherePath = false;

        HashSet<String> visitedByLevel = new HashSet<>();

        while(!level.isEmpty()) {

            visited.addAll(visitedByLevel);
            visitedByLevel = new HashSet<>();

            for(String node: level) {

                for (String w : getNeighbors(node)) {

                    if (!wordList.contains(w)) {
                        continue;
                    }

                    if (!visited.contains(w)) {
                        visitedByLevel.add(w);

                        parentList.putIfAbsent(w, new HashSet<>());
                        parentList.get(w).add(node);

                        if (w.equals(endWord)) {
                            isTherePath = true;
                        }
                    }
                }
            }
            level = visitedByLevel;
        }
        //Don't do if there is no path
        if(isTherePath) {
            dfs(parentList, result, endWord, new ArrayList<>());
        }
        return result;
    }

    private static void dfs(HashMap<String, HashSet<String>> parentList, List<List<String>> result, String endWord, List<String> path) {

        path.add(endWord);

        if(parentList.containsKey(endWord)) {
            for(String word: parentList.get(endWord)) {
                dfs(parentList, result, word, path);
            }
        } else {
            List<String> list = new ArrayList<>(path);
            Collections.reverse(list);
            result.add(list);
        }

        path.remove(path.size() - 1);

    }
    private static List<String> getNeighbors(String word) {
        List<String> result = new ArrayList<>();

        char[] s = word.toCharArray();

        for(int i = 0; i < s.length; i++) {
            char c = s[i];

            for(char ch = 'a'; ch <= 'z'; ch++) {
                if(c != ch) {
                    s[i] = ch;
                    String str = new String(s);
                    result.add(str);
                }
            }
            s[i] = c;
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        String beginWord = "hit";
        String endWord = "cog";

        System.out.println("All shortest paths " +findLadders(beginWord, endWord, wordList));

        List<String> wordList1 = new ArrayList<>();
        wordList1.add("ted");
        wordList1.add("tex");
        wordList1.add("red");
        wordList1.add("tax");
        wordList1.add("tad");
        wordList1.add("den");
        wordList1.add("rex");
        wordList1.add("pee");

        String beginWord1 = "red";
        String endWord1 = "tax";

        System.out.println("All shortest paths " +findLadders(beginWord1, endWord1, wordList1));
    }
}
