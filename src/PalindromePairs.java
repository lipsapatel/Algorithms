import Node.TrieNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in
 * the given list, so that the concatenation of the two words,
 * i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * Example 2:
 *
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 *
 * Brute Force:
 *
 * 1) Two nested loops
 * 2) For each pair, create combined word and check if it's palindrome.
 * 3) Edge Case: when i == j continue the loop because i and j must be distinct
 *
 * Time Complexity: O(n^2 * k)
 * where n = number of words
 * k = length of longest word
 *
 * There will be n(n - 1) pairs
 *
 * Space Complexity: O(n^2)
 * For storing the pairs. Worst case when all pairs are palindrome.
 *
 * Using Trie:
 *
 * Approach:
 *
 * 1) Build regular trie
 * 2) Check the reverse word in regular trie
 * 3) If at any point you find endOfWord in trie, check if remaining is palindrome
 *    in reverse word.
 * 4) If it does, the add to set
 *
 * 5) Build reverse trie
 * 6) Repeat step 2 to 4
 *
 * Time Complexity: O(nk) where n are the number of words and
 *                              k = max length of word
 * Build regular trie = O(nk)
 * Build reverse trie = O(nk)
 * Search in regular trie = O(nk)
 * Search in reverse trie = O(nk)
 *
 * Space Complexity = O(nk)
 *
 * resources/PalindromePairs1.jpg
 * resources/PalindromePairs2.jpg
 * resources/PalindromePairs3.jpg
 * resources/PalindromePairs4.jpg
 * resources/PalindromePairs5.jpg
 */
public class PalindromePairs {

    private static List<List<Integer>> palindromePairsBruteForce(String[] words) {
        List<List<Integer>> pairs = new ArrayList<>();

        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words.length; j++) {

                //Edge Case: i == j
                if(i == j) {
                    continue;
                }

                String combinedWord = words[i] + words[j];
                if(isPalindrome(combinedWord)) {
                    pairs.add(Arrays.asList(i, j));
                }
            }
        }
        return pairs;
    }

    private static boolean isPalindrome(String word) {
        if(word.isEmpty()) {
            return true;
        }

        for(int i = 0, j = word.length() - 1; i < j; i++, j--) {
            if(word.charAt(i) != word.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private static TrieNode root;

    private static List<List<Integer>> palindromePairs(String[] words) {
        String[] reverseWords = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            String reverse = new StringBuilder(words[i]).reverse().toString();
            reverseWords[i] = reverse;
        }

        //Build regular trie
        buildTrie(words);

        HashSet<List<Integer>> set = new HashSet<>();

        //Search palindrome pairs
        search(set, reverseWords, true);

        //Build reverse trie
        buildTrie(reverseWords);

        //Search palindrome pairs
        search(set, words, false);

        return new ArrayList<>(set);
    }

    private static void buildTrie(String[] words) {
        root = new TrieNode();

        for(int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }
    }

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

        current.endOfWord = true; //Only do for nodes which are not root
        current.startIdx = index; //Adding the word index when it ends
    }

    private static boolean remainingIsPalindrome(String word, int index) {
        int i = index;
        int j = word.length() - 1;

        while(i < j) {
            if(word.charAt(i) != word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static void search(HashSet<List<Integer>> set,
                               String[] words, boolean reverseSearch) {

        for(int i = 0; i < words.length; i++) {
            TrieNode current = root;
            String word = words[i];

            //Search
            for(int j = 0; j < word.length(); j++) {

                if(current.endOfWord && remainingIsPalindrome(word, j) &&
                    current.startIdx != i) {
                    if(reverseSearch) {
                        set.add(Arrays.asList(current.startIdx, i));
                    } else {
                        set.add(Arrays.asList(i, current.startIdx));
                    }
                }

                char ch = word.charAt(j);
                current = current.children.get(ch);

                if(current == null) {
                    break; //Not found pair
                }
            }

            //This is for last node for example BAN - NAB
            if(current != null && current.endOfWord &&
                    current.startIdx != i) {
                if(reverseSearch) {
                    set.add(Arrays.asList(current.startIdx, i));
                } else {
                    set.add(Arrays.asList(i, current.startIdx));
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"A", "B", "BAN", "BANANA", "BAT", "LOLCAT", "MANA",
                          "NAB", "NANA", "NOON", "ON", "TA", "TAC"};

        List<List<Integer>> pairs = palindromePairsBruteForce(words);

        System.out.println("Brute Force");
        for(List<Integer> pair: pairs) {
            System.out.println(pair.get(0) + "-->" + pair.get(1));
        }

        pairs = palindromePairs(words);

        System.out.println("Using Trie");
        for(List<Integer> pair: pairs) {
            System.out.println(pair.get(0) + "-->" + pair.get(1));
        }

        String[] words1 = {"a", "abc", "aba", ""};
        pairs = palindromePairs(words1);

        System.out.println("Using Trie");
        for(List<Integer> pair: pairs) {
            System.out.println(pair.get(0) + "-->" + pair.get(1));
        }
    }
}
