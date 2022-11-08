package IK.Graphs.Class;

import java.util.ArrayList;
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
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord,
 * or 0 if no such sequence exists.
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 *
 * Approach
 *
 * 1) The neighbors of begin words are replace each character with letter a to z
 * 2) Check if neighbors are present in the list
 * 3) Convert list to hashset
 * 4) Do BFS starting with begin word.
 * 5) Maintain distance array, if found target word return distance.
 * 6) If the end word is is not present in list return 0, this is not needed but good to have so that we don't we whole bfs to find that out.
 * 8) GetNeighbor method, convert word to char array and then replace char at each position with a to z
 * 9) Revert the replace and do for second position
 * 10) It converts to string and add to result
 *
 * TC: O(M^2 * N) where M = length of word getNeighbors - For word of length M do replacement for 26 char and then convert to String O(M * 26 * M) = O(M^2)
 * But we do this for N words in the list
 *
 * SC: O(M * N) - For each N word we are are storing O(26M) 26 words of length M, visited = O(MN) Queue = O(MN)
 */
public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> list = new HashSet<>(wordList);

        if(!list.contains(endWord)) {
            return 0;
        }

        return bfs(beginWord, endWord, list);

    }

    private static int bfs(String start, String end, HashSet<String> list) {
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> distance = new HashMap<>();

        queue.add(start);
        visited.add(start);
        distance.put(start, 1);

        while(!queue.isEmpty()) {
            String v = queue.remove();

            for(String w: getNeighbors(v)) {
                if(list.contains(w)) {
                    if(!visited.contains(w)) {
                        queue.add(w);
                        visited.add(w);
                        distance.put(w, distance.get(v) + 1);

                        if(w.equals(end)) {
                            return distance.get(w);
                        }
                    }
                }
            }
        }
        return 0;
    }

    private static List<String> getNeighbors(String v) {
        List<String> result = new ArrayList<>();

        char[] s = v.toCharArray();

        for(int i = 0; i < s.length; i++) {

            char temp = s[i];

            for(char c = 'a'; c <= 'z'; c++) {
                s[i] = c;
                String str = new String(s);
                result.add(str);
            }
            s[i] = temp;
        }
        return result;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";

        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");

        System.out.println("Number of words in transformation sequence is " + ladderLength(beginWord, endWord, list));

    }
}
