import Node.TrieNode;
import javafx.util.Pair;

import java.util.Map;

/**
 * Given a text string, find Longest Repeated Substring in the text.
 * If there are more than one Longest Repeated Substrings, get any one of them.
 *
 * Longest Repeated Substring in GEEKSFORGEEKS is: GEEKS
 * Longest Repeated Substring in AAAAAAAAAA is: AAAAAAAAA
 * Longest Repeated Substring in ABCDEFG is: No repeated substring
 * Longest Repeated Substring in ABABABA is: ABABA
 * Longest Repeated Substring in ATCGATCGA is: ATCGA
 * Longest Repeated Substring in banana is: ana
 * Longest Repeated Substring in abcpqrabpqpq is: ab (pq is another LRS here)
 *
 * Approach:
 *
 * 1) Construct suffix trie for all the suffixes.
 * 2) More than one dollar means repeating. I am repeating if I have
 * more than one dollar.
 *
 * Time Complexity: O(n^2) = n^2 construction time and n^2 for finding deepest repeating
 * node because there are n^2 nodes.
 * Space Complexity: O(n^2)
 *
 * resources/LongestRepeatedSubstring.jpg
 */
public class LongestRepeatedSubstring {

    private static TrieNode root;

    private static String findLongestRepeatingSubstring(String str) {
        buildSuffixTrie(str);
        return getLongestRepeatingSubstring(root, '*').getValue();
    }

    private static void buildSuffixTrie(String str) {
        root = new TrieNode();

        //All suffixes
        for(int i = 0; i < str.length(); i++) {
            insert(str.substring(i));
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
        //Insert dollar node
        current.children.put('$', new TrieNode());
    }

    //Returns count and longest repeated substring
    private static Pair<Integer, String> getLongestRepeatingSubstring(TrieNode current,
                                                                      char ch) {
        int count = 0;
        String longestRepeatingString = "";

        //DFS
        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            if(child.getKey() == '$') {
                count = count + 1;
            } else {
                Pair<Integer, String> pair = getLongestRepeatingSubstring(child.getValue(), child.getKey());
                count = count + pair.getKey();

                if(longestRepeatingString.length() < pair.getValue().length()) {
                    longestRepeatingString = pair.getValue();
                }
            }
        }
        if(count > 1 && ch != '*') { //I am repeating, so append my char, nothing to append for root
            longestRepeatingString = ch + longestRepeatingString;
        }
        return new Pair<>(count, longestRepeatingString);
    }

    public static void main(String[] args) {
        String str = "banana";
        System.out.println("The longest repeating substring is " + findLongestRepeatingSubstring(str));

        str = "AAAAAAAAAA";
        System.out.println("The longest repeating substring is " + findLongestRepeatingSubstring(str));

        str = "ABDCEF";
        System.out.println("The longest repeating substring is " + findLongestRepeatingSubstring(str));
    }
}
