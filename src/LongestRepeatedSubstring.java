import Node.TrieNode;

import java.util.HashSet;
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
 * resources/MostRepeatedSubstring1.jpg
 * resources/MostRepeatedSubstring2.jpg
 *
 * Brute Force Approach
 *
 * 1) Find all substrings
 * 2) Add to set
 * 3) Check if any substring is repeated and keep track of longest.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 */
public class LongestRepeatedSubstring {

    private static TrieNode root;
    private static String lrs;

    private static String findLongestRepeatingSubstring(String str) {
        lrs = "";
        buildSuffixTrie(str);
        getLongestRepeatingSubstring(root, ""); //node and prefix
        return lrs;
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

    //Returns count
    private static int getLongestRepeatingSubstring(TrieNode current, String prefix) {
        int count = 0;

        //DFS
        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            if(child.getKey() == '$') {
                count = count + 1;
            } else {
                count = count + getLongestRepeatingSubstring(child.getValue(), prefix + child.getKey());
            }
        }
        if(count > 1 && prefix.length() > lrs.length()) { //I am repeating so check if I am longest
            lrs = prefix;
        }
        return count;
    }

    //TC = O(n^2)
    //SC = O(n^2) - This give out of memory error
    private static String getLongestRepeatedSubstringBruteForce(String s) {
        String lrs = "";
        HashSet<String> set = new HashSet<>();

        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 1; j <= s.length(); j++) {
                String subStr = s.substring(i, j);
                if(set.contains(subStr)) {
                    if(subStr.length() > lrs.length()) {
                        lrs = subStr;
                    }
                } else {
                    set.add(subStr);
                }
            }
        }
        return lrs;
    }

    public static void main(String[] args) {
        String str = "banana";
        System.out.println("The longest repeating substring is " + findLongestRepeatingSubstring(str));
        System.out.println("The longest repeating substring using brute force is " + getLongestRepeatedSubstringBruteForce(str));

        str = "AAAAAAAAAA";
        System.out.println("The longest repeating substring is " + findLongestRepeatingSubstring(str));
        System.out.println("The longest repeating substring using brute force is " + getLongestRepeatedSubstringBruteForce(str));

        str = "ABDCEF";
        System.out.println("The longest repeating substring is " + findLongestRepeatingSubstring(str));
        System.out.println("The longest repeating substring using brute force is " + getLongestRepeatedSubstringBruteForce(str));
    }
}
