import Node.TrieNode;

import java.util.Map;

/**
 * Most Repeated Substring
 * Example: "banana"
 * Output: "a"
 *
 * Approach
 *
 * 1) Construct suffix trie for all the suffixes.
 * 2) Root ask all it's children to collect dollars.
 * The child having maximum dollars is the most repeating substring.
 *
 * Time Complexity: O(n^2) - Construction time and collecting dollars
 * Space Complexity: O(n^2)
 *
 * resources/MostRepeatedSubstring.jpg
 *
 * Least repeated substring will be the child with min dollar.
 */
public class MostRepeatedSubstring {

    private static TrieNode root;

    private static String mostRepeatedSubstring(String str) {
        buildSuffixTrie(str);
        return getMostRepeatedSubstring();
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

    private static String getMostRepeatedSubstring() {
        TrieNode current = root;

        //Get the count of all children. Collect dollars for all children
        //The child with maximum dollars is the most repeating
        int count = 0; //if least repeating substring Integer.MAX_VALUE;
        String mostRepeatingSubstring = "";

        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {

            int dollar = collectDollars(child.getValue());

            if (count < dollar) { //Change > for least repeating substring
                count = dollar;
                mostRepeatingSubstring = child.getKey().toString();
            }
        }
        return mostRepeatingSubstring;
    }

    private static int collectDollars(TrieNode current) {
        int count = 0;

        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            if(child.getKey() == '$') {
                count = count + 1;
            } else {
                count = count + collectDollars(child.getValue());
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String str = "banana";
        System.out.println("The most repeating substring is " + mostRepeatedSubstring(str));

        str = "geeksgeeks";
        System.out.println("The most repeating substring is " + mostRepeatedSubstring(str));
    }
}
