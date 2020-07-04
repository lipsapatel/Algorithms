import Node.TrieNode;

import java.util.Map;

/**
 * Count the number of words with given prefix using Trie
 * Prerequisite: Trie
 * Given a list of string str[] and a prefix string pre.
 * The task is to count the number of words in list of string
 * with given prefix using trie.
 *
 * Examples:
 *
 * Input: str = [ “apk”, “app”, “apple”, “arp”, “array” ], pre = “ap”
 * Output: 3
 *
 * The words in str having prefix “ap” are apk, app and apple.
 * So, the count is 3
 *
 * Input: str = [ “gee”, “geek”, “geezer”, “geeksforgeeks”, “geekiness”, “geekgod” ],
 * pre = “geek”
 * Output: 4
 *
 * Approach:
 *
 * 1) Build trie for all the given words.
 * 2) Traverse trie for the prefix
 * 3) Collect dollars by doing DFS O(n)
 * 4) Return the count
 *
 * Time Complexity: O(nk + n) where there are n words of length k
 * Space Complexity: O(nk)
 *
 * resources/CountNumberOfWordsWithGivenPrefixUsingTrie1.jpg
 */
public class CountNumberOfWordsWithGivenPrefixUsingTrie {

    private static TrieNode root;

    private static int countWordsWithPrefix(String[] words, String prefix) {
        buildTrie(words);
        return countWords(prefix);
    }

    //Time Complexity: O(nk)
    private static void buildTrie(String[] words) {
        root = new TrieNode();

        for(int i = 0; i < words.length; i++) {
            insert(words[i]);
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
        //Add dollar
        current.children.put('$', new TrieNode());
        current.endOfWord = true; //not needed for this problem
    }

    private static int countWords(String prefix) {
        TrieNode current = root;

        for(int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);

            TrieNode node = current.children.get(ch);

            if(node == null) {
                return 0; //No words found with given prefix
            }
            current = node;
        }
        return countUsingDFS(current); //Collect dollars
    }

    private static int countUsingDFS(TrieNode current) {
        int count = 0;

        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            if(child.getKey() == '$') {
                count = count + 1;
            } else {
                count = count + countUsingDFS(child.getValue());
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] words = {"apk", "app", "apple", "arp", "array"};
        String prefix = "ap";

        System.out.println("The number of words with prefix ap are " +
                countWordsWithPrefix(words, prefix));
    }
}
