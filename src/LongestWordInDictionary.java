import Node.TrieNode;

import java.util.HashSet;
import java.util.Map;

/**
 Given a list of strings words representing an English Dictionary,
 find the longest word in words that can be built one character at a time by
 other words in words. If there is more than one possible answer, return the
 longest word with the smallest lexicographical order.
 If there is no answer, return the empty string.

 Example 1:

 Input:
 words = ["w","wo","wor","worl", "world"]
 Output: "world"
 Explanation:
 The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".

 Example 2:

 Input:
 words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 Output: "apple"
 Explanation:
 Both "apply" and "apple" can be built from other words in the dictionary.
 However, "apple" is lexicographically smaller than "apply".

 Note:
 All the strings in the input will only contain lowercase letters.
 The length of words will be in the range [1, 1000].
 The length of words[i] will be in the range [1, 30].

 Approach #1: Brute Force [Accepted]

 1) Add all words in set.
 2) Iterate all words and for each word check if all prefixes of that word
 exists in set.
 3) If it does then update the longest word.

 Time Complexity: O(n * k^2) where n = number of words
 k = length of each word

 Space Complexity: O(nk)

 Approach #2: Trie + Depth-First Search [Accepted]

 Intuition

 As prefixes of strings are involved, this is usually a natural fit for a trie
 (a prefix tree.)

 Algorithm

 Put every word in a trie, then depth-first-search from the start of the trie,
 only searching nodes that ended a word. Every node found
 (except the root, which is a special case) then represents a word with all it's
 prefixes present. We take the best such word.

 1) Build trie for all words.
 2) Do DFS and all should be the word on it's way down. Keep updating lw.

 Time Complexity: Construction time = O(nk)
 Search longest word = O(nk)

 Space Complexity: O(nk)
 */
public class LongestWordInDictionary {

    private static String longestWordBruteForce(String[] words) {
        HashSet<String> set = new HashSet<>();
        String lw = "";

        for(String word: words) {
            set.add(word);
        }

        for(String word: words) {

            if(word.length() > lw.length() || word.length() == lw.length() && word.compareTo(lw) < 0) {
                boolean prefixExists = true;

                //Check if all prefix exists in set
                for (int k = 1; k < word.length(); k++) {
                    if (!set.contains(word.substring(0, k))) {
                        prefixExists = false;
                        break;
                    }
                }
                if (prefixExists) {
                    lw = word;
                }
            }
        }
        return lw;
    }

    private static TrieNode root;
    private static String lw;

    private static String longestWord(String[] words) {
        lw = "";
        buildTrie(words);
        getLongestWord(root, "");
        return lw;
    }

    private static void buildTrie(String[] words) {
        root = new TrieNode();
        for(String word: words) {
            insert(word);
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
        current.endOfWord = true;
    }

    private static void getLongestWord(TrieNode current, String prefix) {
        if(current.endOfWord) {
            if(prefix.length() > lw.length() ||
                    (prefix.length() == lw.length() && prefix.compareTo(lw) < 0)) {
                lw = prefix;
            }
        } else if(!current.endOfWord && current != root) {
            return;
        }

        //DFS
        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            getLongestWord(child.getValue(), prefix + child.getKey());
        }
    }

    public static void main(String[] args) {
        String[] words = {"a", "banana", "app", "apple", "appl", "ap", "apply"};
        System.out.println(longestWordBruteForce(words));
        System.out.println(longestWord(words));
    }
}
