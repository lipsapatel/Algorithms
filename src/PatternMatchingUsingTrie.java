import Node.TrieNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Given a list of words in dictionary and pattern,
 * return all the words that matches the pattern.
 *
 * Pattern contains wild card.
 * Where * = 0 or more characters
 *
 * Example:
 * Dictionary = cata, cara, dog, cat
 *
 * Pattern: c*ata
 * Output: cata
 *
 * Pattern: c*a
 * Output: cata, cara
 *
 * Pattern: ca*
 * Output: cata, cara, cat
 *
 *
 * Approach:
 *
 * 1) Build trie
 * 2) Two cases for *
 *    a) Zero match in which case you do pattern + 1, string
 *    b) More than zero match in which case you do pattern, string + 1
 * 3) If it's not * do regular search
 *
 * Time Complexity:
 * Build Trie: O(nk) if we have n words of k length
 * Search: O(mk) where m = number of words that matched the pattern
 * Worst case Time Complexity when pattern is *
 * so you traverse whole trie which O(nkp) where n = total number of words
 * k = length of word and p = length of pattern
 *
 * Space Complexity: O(nk)
 */
public class PatternMatchingUsingTrie {

    public static TrieNode root = new TrieNode();

    public static void insert(String word) {
        TrieNode current = root;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            TrieNode node = current.children.get(ch);

            if(node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    private static List<String> search(String pattern) {
        List<String> result = new ArrayList<>();
        List<Character> word = new ArrayList<>();

        TrieNode current = root;

        search(pattern, 0, current, result, word);
        return result;
    }

    private static void search(String pattern, int i, TrieNode current,
                               List<String> result, List<Character> word) {

        //Base Case - If pattern done
        if(i == pattern.length()) {
            if(current.endOfWord) {
                StringBuilder sb = new StringBuilder();
                for(Character c: word) {
                    sb.append(c);
                }
                result.add(sb.toString());
            }
            return;
        }

        char c = pattern.charAt(i);

        if(c == '*') {
            //Case 1: More than zero character
            for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
                word.add(child.getKey());
                search(pattern, i, child.getValue(), result, word);
                word.remove(word.size() - 1);
            }

            //Case 2: Zero character
            search(pattern, i + 1, current, result, word);
        } else {
            //regular character search
            TrieNode node = current.children.get(c);

            if(node == null) {
                return;
            }
            word.add(c);
            current = node;
            search(pattern, i + 1, current, result, word);
            word.remove(word.size() - 1);
        }
    }

    public static void main(String[] args) {
        insert("cata");
        insert("cara");
        insert("dog");
        insert("cat");

        List<String> result = search("c*ata");

        System.out.println("Pattern c*ata");
        for(String word: result) {
            System.out.println(word);
        }

        List<String> result1 = search("c*a");

        System.out.println("Pattern c*a");
        for(String word: result1) {
            System.out.println(word);
        }

        List<String> result2 = search("ca*");

        System.out.println("Pattern ca*");
        for(String word: result2) {
            System.out.println(word);
        }
    }
}
