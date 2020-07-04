import Node.TrieNode;

import java.util.Map;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 *
 * search(word) can search a literal word or a regular expression
 * string containing only letters a-z or ..
 * A . means it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 *
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 * Approach
 *
 * 1) Use Tries data structure
 * 2) Add word means insert into trie
 *    Time Complexity: O(k) where k = length of word
 * 3) Search word in trie
 *    Time Complexity: O(k) where k = length of word
 * 4) For dot, search all children
 *
 * Add and search one word
 * Time Complexity: O(k)
 * Space Complexity: O(k)
 *
 * resources/WordDictionaryAddSearchWord1.jpg
 * resources/WordDictionaryAddSearchWord2.jpg
 */
public class WordDictionaryAddSearchWord {

    public TrieNode root;

    public WordDictionaryAddSearchWord() {
        root = new TrieNode();
    }

    public void addWord(String word) {
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

    public boolean search(String word) {
        return search(word, 0, root);
    }

    public boolean search(String word, int index, TrieNode current) {
        //Base Case
        if(index == word.length()) {
            return current.endOfWord;
        }

        char ch = word.charAt(index);

        if(ch == '.') {
            //Search all children
            for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
                if(search(word, index + 1, child.getValue())) {
                    return true;
                }
            }
        } else {
            TrieNode node = current.children.get(ch);

            if(node != null) {
                return search(word, index + 1, node);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionaryAddSearchWord wordDictionaryAddSearchWord = new WordDictionaryAddSearchWord();

        wordDictionaryAddSearchWord.addWord("bad");
        wordDictionaryAddSearchWord.addWord("dad");
        wordDictionaryAddSearchWord.addWord("mad");
        System.out.println(wordDictionaryAddSearchWord.search("pad"));
        System.out.println(wordDictionaryAddSearchWord.search("bad"));
        System.out.println(wordDictionaryAddSearchWord.search(".ad"));
        System.out.println(wordDictionaryAddSearchWord.search("b.."));
    }
}
