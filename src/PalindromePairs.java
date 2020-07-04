import Node.TrieNode;

import java.util.ArrayList;
import java.util.Arrays;
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
 * There are 3 cases
 *
 * 1) Case 1: Same length, reverse of each other
 *            Example: cat - tac
 *
 * 2) Case 2: when first word length is greater than second word
 *            Example: Banana - nab
 *            In trie it should match the end of word
 *            Remaining string should be palindrome - ana
 *
 * 3) Case 3: When second word length is greater than first word.
 *            Example: Tac - lolcat
 *            In trie, remaining should be palindrome - lol
 *
 * Approach:
 *
 * 1) Reverse each word and build trie.
 * 2) Trie will store endOfWord and list of indexes which include index at the end and
 *    at every node after which the remaining is palindrome.
 * 3) While adding trie node, check if remaining string is palindrome. If it is
 *    palindrome, add the word index to palindromePrefixRemaining.
 *
 *  Find Palindrome pairs.
 *
 * 1) If all characters of word are matched, Case 1 and 3
 *     Then create pair of index and palindromePrefixRemaining.
 * 2) If all characters matched till the end of the word, Case 3
 *    then check if remaining string is palindrome.
 *    If it is palindrome, the create pair of index and palindromePrefixRemaining
 *    Banana - nab
 *
 * Time Complexity:
 *
 * O(n * k^2)
 *
 * Building Trie and find pairs: O(n * k)
 * Has palindrome remaining : O(k^2)
 *
 * Space Complexity: O(n^2 * k)
 * If there are n^2 pairs, then each node will have upto n indexes
 * Each word will form pair with all words.
 *
 * O(n * k) - Trie space
 *
 * resources/PalindromePairs1.jpg
 * resources/PalindromePairs2.jpg
 * resources/PalindromePairs3.jpg
 * resources/PalindromePairs4.jpg
 * resources/PalindromePairs5.jpg
 * resources/PalindromePairs6.jpg
 * resources/PalindromePairs7.jpg
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
        //Build Trie of all reverse words
        buildTrie(words);

        List<List<Integer>> pairs = new ArrayList<>();
        findPalindromePairs(pairs, words);
        return pairs;
    }

    private static void buildTrie(String[] words) {
        root = new TrieNode();

        for(int i = 0; i < words.length; i++) {
            String word = new StringBuilder(words[i]).reverse().toString();
            insert(word, i);
        }
    }

    private static void insert(String word, int index) {
        TrieNode current = root;

        for(int i = 0; i < word.length(); i++) {

            //Check if the remaining is palindrome then store index.
            if(remainingIsPalindrome(word, i)) {
                current.palindromePrefixRemaining.add(index);
            }

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
        current.palindromePrefixRemaining.add(index);//Adding word index to last node
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

    private static void findPalindromePairs(List<List<Integer>> pairs, String[] words) {
        for(int i = 0; i < words.length; i++) {
            TrieNode current = root;
            String word = words[i];

            //Search
            for(int j = 0; j < word.length(); j++) {

                //Case 3: Length of word > nodes
                //banana - nab - forms palindrome with word ending here
                if(current.endOfWord && remainingIsPalindrome(word, j)) {
                    if(current.startIdx != i) {
                        pairs.add(Arrays.asList(i, current.startIdx));
                    }
                }

                char ch = word.charAt(j);
                current = current.children.get(ch);

                if(current == null) {
                    break; //Not found pair
                }
            }

            //Case 1 (cat and tac) and Case 2 (tac - lolcat)
            if(current != null) {
                for(int index: current.palindromePrefixRemaining) {
                    if(index != i) {
                        pairs.add(Arrays.asList(i, index));
                    }
                }
            }
            //For current == null, go to next word
        }
    }

    public static void main(String[] args) {
        String[] words = {"A", "B", "BAN", "BANANA", "BAT", "LOLCAT", "MANA",
                          "POPCAT", "CAT", "NAB", "NANA", "NOON", "ON", "TA", "TAC"};

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
