import Node.TrieNode;

import java.util.Map;

/**
 * Most Repeated Substring
 * Example: "banana"
 * Output: "a" repeated 3 times
 * "na" length > 1 repeated 2 times
 *
 * Approach
 *
 * 1) Construct suffix trie for all the suffixes.
 * 2) Root ask all it's children to collect dollars.
 * 3) Pass the prefix, the string till that prefix is repeated if dollar count > 1
 * 4) Dollar count represent how many times it's repeated.
 * 5) Keep the track of maximum repeated count and maximum repeated substring using
 * global variables.
 *
 * Time Complexity: O(n^2) - Construction time and collecting dollars
 * Space Complexity: O(n^2)
 *
 * resources/MostRepeatedSubstring1.jpg
 * resources/MostRepeatedSubstring2.jpg
 *
 * Least repeated substring will be the child with min dollar greater than 1.
 */
public class MostRepeatedSubstring {

    private static TrieNode root;
    private static String mrs;
    private static int mrc; //Anything greater than 1 is repeated.

    private static String mostRepeatedSubstring(String str) {
        mrs = "";
        mrc = 1;
        buildSuffixTrie(str);
        getMostRepeatedSubstring(root, ""); //node and prefix
        return mrs;
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

    private static int getMostRepeatedSubstring(TrieNode current, String prefix) {

        //Get the count of all children. Collect dollars for all children
        //The child with maximum dollars is the most repeating
        int count = 0; //if least repeating substring Integer.MAX_VALUE;

        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {

            if (child.getKey() == '$') {
                count = count + 1;
            } else {
                count = count + getMostRepeatedSubstring(child.getValue(), prefix + child.getKey());
            }
        }

        if (count > mrc && prefix.length() > 1) {
                mrc = count;
                mrs = prefix;
        }

        return count;
    }

    public static void main(String[] args) {
        String str = "banana";
        System.out.println("The most repeating substring of length greater than 1 is " + mostRepeatedSubstring(str));

        str = "geeksgeeks";
        System.out.println("The most repeating substring is of length greater than 1 is " + mostRepeatedSubstring(str));

        str = "mississippiss";
        System.out.println("The most repeating substring is of length greater than 1 is " + mostRepeatedSubstring(str));

    }
}
