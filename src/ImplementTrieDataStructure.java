import Node.TrieNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Trie Data Structure or Prefix tree:
 *
 * It is a tree data structure used for storing collection of strings.
 * Ideal data structure for storing dictionary.
 * It can be used to do prefix based search.
 * Sort the string lexograhically in the trie.
 *
 * If your character set is all upper case or lower case,
 * you can use array of length 26.
 *
 * There are two kinds of searches:
 *
 * 1) Prefix based search - check if the prefix exists or not
 * 2) String based search - check if the whole string exists or not.
 *
 * resources/ImplementTrieDataStructure1.jpg
 * resources/ImplementTrieDataStructure2.jpg
 *
 * Time Complexity
 * Let assume the length of word = k
 * Insert a word = O(k) for n words it's O(nk)
 * Search a word = O(k)
 * Delete a word = O(k)
 *
 * Space Complexity: O(nk) - where we have n words of length k.
 */
public class ImplementTrieDataStructure {

    private static final TrieNode root = new TrieNode();

    private static void insert(String word) {
        TrieNode current = root;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            TrieNode node = current.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node; //update the current node
        }
        current.endOfWord = true; //Word completed, this is the last letter of word
    }

    private static boolean search(String word) {
        TrieNode current = root;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);

            if(node == null) { //Not found
                return false;
            }
            current = node;
        }
        return current.endOfWord;
    }

    private static boolean delete(String word) {
        return delete(root, word, 0);
    }

    //Returns true if parent should delete the mapping
    private static boolean delete(TrieNode current, String word, int index) {
        if(index == word.length()) {
            //Reached the last letter of word

            if(!current.endOfWord) {
                return false; //Not found
            }
            current.endOfWord = false;

            return current.children.size() == 0;
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if(node == null) {
            return false; //Not found
        }

        boolean shouldDeleteNode = delete(node, word, index + 1);

        if(shouldDeleteNode) {
            //Delete node
            current.children.remove(ch);
            return current.children.size() == 0 && !current.endOfWord;
        }
        return false;
    }

    //Time Complexity: O(P + k(n-p)) where k = number of matches and n = length of word
    //p = length of prefix.
    //Search all words with given prefix
    private static List<String> prefixSearch(String prefix) {
        List<String> result = new ArrayList<>();
        List<Character> word = new ArrayList<>();

        TrieNode current = root;

        for(int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);

            TrieNode node = current.children.get(ch);
            if(node == null) {
                return result;
            }
            word.add(ch);
            current = node;
        }
        dfs(current, result, word);
        return result;
    }

    private static void dfs(TrieNode current, List<String> result, List<Character> word) {

        //Base Case
        if(current.endOfWord) {
            StringBuilder sb = new StringBuilder();
            for(Character c: word) {
                sb.append(c);
            }
            result.add(sb.toString());
        }

        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            char ch = child.getKey();
            word.add(ch);
            dfs(child.getValue(), result, word);
            //Backtrack
            word.remove(word.size() - 1);
        }
    }

    public static void main(String[] args) {
        insert("abc");
        insert("abcd");
        insert("abgl");
        insert("cdf");
        insert("lmn");

        System.out.println("Found abgl " + search("abgl"));
        System.out.println("Found cdf " + search("cdf"));

        System.out.println("Found abc " + search("abc"));
        System.out.println("Delete abc " + delete("abc"));
        System.out.println("Found abc " + search("abc"));

        System.out.println("Search abgl " + search("abgl"));
        System.out.println("Delete abgl " + delete("abgl"));
        System.out.println("Search abgl " + search("abgl"));

        insert("cat");
        insert("can");
        insert("cam");
        insert("could");
        insert("cap");

        List<String> result = prefixSearch("ca");
        for(String word: result) {
            System.out.println(word);
        }
    }
}
