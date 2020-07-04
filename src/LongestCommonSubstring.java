import Node.TrieNode;
import javafx.util.Pair;

import java.util.Map;

/**
 * Given two strings X and Y, find the Longest Common Substring of X and Y.
 * we will build generalized suffix tree for two strings X and Y
 * Lets take same example (X = xabxa, and Y = babxba)
 *
 * Approach
 * 1) Build generalized suffix trie for all the suffixes of x and y.
 * 2) Count the dollars and hash
 * 3) If there are more than 0 dollars and hash then I am common and I
 * will append myself to longest substring.
 *
 * Time Complexity: O(n^2 + m^2)
 * Construction time = O(n^2 + m^2)
 * Getting longest common substring - O(m^2 + n^2) because there are m^2 + n^2 nodes
 *
 * Space Complexity: O(n^2 + m^2)
 *
 * Now the construction time can be changed to linear O(n + m) by using Ukonnen's algorithm
 * By using compressed trie/Radix tree, the search time can be reduce to linear O(n + m)
 *
 * resources/LongestCommonSubstring1.jpg
 * resources/LongestCommonSubstring2.jpg
 */
public class LongestCommonSubstring {

    private static TrieNode root;

    private static String longestCommonSubstring(String s1, String s2) {
        buildSuffixTrie(s1, s2);
        return getLongestCommonSubstring(root, '*').getKey(); //No character to append
        // for root, so using dummy character to identify the root
    }

    private static void buildSuffixTrie(String s1, String s2) {
        root = new TrieNode();

        //All s1 suffixes
        for (int i = 0; i < s1.length(); i++) {
            insert(s1.substring(i), '$');
        }

        //All s2 suffixes
        for (int i = 0; i < s2.length(); i++) {
            insert(s2.substring(i), '#');
        }
    }

    private static void insert(String word, Character endChar) {
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
        //Add end char
        current.children.put(endChar, new TrieNode());
    }

    private static Pair<String, int[]> getLongestCommonSubstring(TrieNode current, Character ch) {
        int dollarCount = 0;
        int hashCount = 0;
        String longestCommonSubstring = "";

        //DFS
        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            if(child.getKey() == '$') {
                dollarCount = dollarCount + 1;
            } else if(child.getKey() == '#') {
                hashCount = hashCount + 1;
            } else {
                Pair<String, int[]> pair = getLongestCommonSubstring(child.getValue(), child.getKey());
                dollarCount = dollarCount + pair.getValue()[0];
                hashCount = hashCount + pair.getValue()[1];

                if(pair.getKey().length() > longestCommonSubstring.length()) {
                    longestCommonSubstring = pair.getKey();
                }
            }
        }

        //I am common if I have both dollar and hash and nothing to append for root
        if(dollarCount > 0 && hashCount > 0 && ch != '*') {
            longestCommonSubstring = ch + longestCommonSubstring;
        }
        return new Pair<>(longestCommonSubstring, new int[]{dollarCount, hashCount});
    }

    public static void main(String[] args) {
        String s1 = "ball";
        String s2 = "call";

        System.out.println("The longest common substring is " + longestCommonSubstring(s1, s2));

        s1 = "xabxa";
        s2 = "babxba";

        System.out.println("The longest common substring is " + longestCommonSubstring(s1, s2));
    }
}
