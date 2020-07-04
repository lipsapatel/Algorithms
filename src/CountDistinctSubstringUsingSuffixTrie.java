import Node.TrieNode;

import java.util.Map;

/**
 * Count of distinct substrings of a string using Suffix Trie
 * Given a string of length n of lowercase alphabet characters,
 * we need to count total number of distinct substrings of this string.
 * Examples:
 *
 * Input  : str = “ababa”
 * Output : 10
 * Total number of distinct substring are 10, which are,
 * "", "a", "b", "ab", "ba", "aba", "bab", "abab", "baba"
 * and "ababa"
 *
 * The idea is create a Trie of all suffixes of given string. Once
 * the Trie is constricted, our answer is total number of nodes in the constructed Trie.
 *
 * Approach
 *
 * 1) Construct suffix trie of all the suffixes.
 * 2) Count the number of nodes.
 * 3) All prefixes of suffixes are substring so each node represent one substring
 *
 * Time Complexity: O(n^2 + n) where n^2 is construction time for suffix trie
 * and n = count number of nodes.
 *
 * Space Complexity: O(nk) where there are n words of k length.
 *
 * resources/CountDistinctSubstringUsingSuffixTrie1.jpg
 * resources/CountDistinctSubstringUsingSuffixTrie2.jpg
 */
public class CountDistinctSubstringUsingSuffixTrie {

    private static TrieNode root;

    private static int countDistinctSubstring(String str) {
        buildSuffixTrie(str);
        return countDistinctSubstring(root);
    }

    private static void buildSuffixTrie(String word) {
        root = new TrieNode();

        //All suffixes
        for(int i = 0; i < word.length(); i++) {
            insert(word.substring(i));
        }
    }

    private static void insert(String word) {
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
    }

    //DFS
    private static int countDistinctSubstring(TrieNode current) {

        int count = 0;

        //Base Case
        if(current == null) {
            return count;
        }

        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            count = count + countDistinctSubstring(child.getValue()); //Add count of all child nodes
        }

        return 1 + count; //Count the self  so + 1
    }

    public static void main(String[] args) {
        String str = "ababa";

        System.out.println("The number of distinct substrings are " +
                countDistinctSubstring(str));
    }
}
