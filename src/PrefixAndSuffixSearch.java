import Node.TrieNode;

/**
 * Given many words, words[i] has weight i.
 *
 * Design a class WordFilter that supports one function,
 * WordFilter.f(String prefix, String suffix). It will return the
 * word with given prefix and suffix with maximum weight.
 * If no word exists, return -1.
 *
 * Examples:
 *
 * Input:
 * WordFilter(["apple"])
 * WordFilter.f("a", "e") // returns 0
 * WordFilter.f("b", "") // returns -1
 *
 * Input:
 * WordFilter(["apple", "banana", "grapes", "aple", "gres"])
 * WordFilter.f("ap", "le") //returns 3
 * WordFilter.f("gr", "es") //returns 4
 *
 * Note:
 *
 * words has length in range [1, 15000].
 * For each test case, up to words.length queries WordFilter.f may be made.
 * words[i] has length in range [1, 10].
 * prefix, suffix have lengths in range [0, 10].
 * words[i] and prefix, suffix queries consist of lowercase letters only.
 *
 * resources/PrefixAndSuffixSearch1.jpg
 * resources/PrefixAndSuffixSearch2.jpg
 *
 * Approach
 *
 * 1) For all suffixes of word insert suffix + "#" + word.
 * 2) Since suffix can be of length 0 also, so the suffixes of word "apple"
 * is "apple", "pple", "ple", "le", "e", ""
 * 3) Maintain max weight.
 * 4) Search trie for suffix#prefix
 *
 * Time Complexity:
 *
 * Max length of word = k, so there will be k suffixes
 *
 * Insert/Construct trie: O(k^2) for one word.
 * For N words O(N * K^2)
 *
 * Search = O(K)
 * Q search queries = O(QK)
 *
 * Space Complexity: O(N * K^2) size of trie.
 */
public class PrefixAndSuffixSearch {

    public TrieNode root;

    public PrefixAndSuffixSearch(String[] words) {

        root = new TrieNode();

        //Iterate words - Index is the weight
        for(int w = 0; w < words.length; w++) { //O(N)
            String word = words[w];

            //All suffixes including ""
            for(int i = 0; i <= word.length(); i++) { //O(k)
                String suffix = word.substring(i, word.length());
                String finalSearchString = suffix + "#" + word;
                insert(finalSearchString, w); //O(k)
            }
        }
    }

    public void insert(String s, int weight) {
        TrieNode current = root;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            TrieNode node = current.children.get(ch);

            if(node == null) {
                node = new TrieNode();
                node.weight = weight;
                current.children.put(ch, node);
            }

            node.weight = Math.max(node.weight, weight);
            current = node;
        }
        current.endOfWord = true;
    }

    public int filter(String prefix, String suffix) {
        return search(suffix + "#" + prefix);
    }

    public int search(String word) {
        TrieNode current = root;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            TrieNode node = current.children.get(ch);

            if(node == null) { //not found
                return -1;
            }
            current = node;
        }
        return current.weight;
    }

    public static void main(String[] args) {
        String[] words = {"apple", "banana", "grapes", "aple"};
        PrefixAndSuffixSearch prefixAndSuffixSearch = new
                PrefixAndSuffixSearch(words);

        System.out.println("Max weight which match prefix and suffix " +
                prefixAndSuffixSearch.filter("ap", "le"));
    }
}
