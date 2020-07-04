import Node.TrieNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * resources/PatternSearchingUsingSuffixTrie1.jpg
 * resources/PatternSearchingUsingSuffixTrie2.jpg
 * resources/PatternSearchingUsingSuffixTrie3.jpg
 * resources/PatternSearchingUsingSuffixTrie4.jpg
 * resources/PatternSearchingUsingSuffixTrie5.jpg
 * resources/PatternSearchingUsingSuffixTrie6.jpg
 * resources/PatternSearchingUsingSuffixTrie7.jpg
 * resources/PatternSearchingUsingSuffixTrie8.jpg
 *
 * All substrings are prefix of suffixes.
 * So if you create suffix trie with all the suffixes, then search
 * the pattern in suffix trie.
 * Also maintain indexes in TrieNode so that we can return the index
 * where pattern is found in txt.
 *
 * Time Complexity:
 * Initial construction time for suffix tree = O(n^2)
 * Search pattern: O(m)
 *
 * Space Complexity: O(nk) where there are n suffixes of length k
 *
 */
public class PatternSearchingUsingSuffixTrie {

    private static TrieNode root;

    private static List<Integer> search(String txt, String pattern) {
        buildSuffixTree(txt);
        return searchPattern(pattern);
    }

    private static void buildSuffixTree(String txt) {
        root = new TrieNode();

        //All suffixes
        for(int i = 0; i < txt.length(); i++) {
            insert(txt.substring(i), i);
        }
    }

    //index = starting index of the word in txt.
    private static void insert(String word, int index) {
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
        TrieNode dollarNode = new TrieNode();
        dollarNode.startIdx = index;
        current.children.put('$', dollarNode);
    }

    private static List<Integer> searchPattern(String pattern) {
        List<Integer> result = new ArrayList<>();

        TrieNode current = root;
        for(int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);

            TrieNode node = current.children.get(ch);
            if(node == null) {
                return result;
            }
            current = node;
        }

        //Collect all the indexes from '$' node using DFS
        collectDollar(current, result);
        return result;
    }

    //DFS
    private static void collectDollar(TrieNode current, List<Integer> result) {
        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {

            if(child.getKey() == '$') {
                result.add(child.getValue().startIdx);
            } else {
                collectDollar(child.getValue(), result);
            }
        }
    }

    public static void main(String[] args) {
        String txt = "banana";
        String pattern = "na";

        List<Integer> result = search(txt, pattern);

        System.out.println("Pattern found at index");
        for(int index: result) {
            System.out.print(index + " ");
        }
    }
}
