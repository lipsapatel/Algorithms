import Node.TrieNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Indices Of Words In Text String
 *
 * Given some text and a bunch of words, find where each of the words appear in the
 * text. Text may contain spaces, words may not.
 *
 * For every given word you need to return a list of (zero-based) indices of
 * where that word starts in the text. If a word isn’t found in the text,
 * return [-1] for that word.
 *
 * Example
 * Input:
 * text = “you are very very smart”
 * words = [“you”, “are”, “very”, “handsome”]
 * Output:
 * [ [0], [4], [8, 13], [-1] ]
 * “you” is found in the given text starting at the index 0.
 * “are” is found in the given text starting at the index 4.
 * “very” is found in the given text two times, starting at the indices 8 and 13.
 * “handsome” isn’t found in the given text.
 *
 * Constraints:
 * •	Text and words may contain a-z, A-Z, 0-9, “$”, “#”, “@”, “?”, “;”.
 * •	Text may contain spaces, too, but never two or more spaces consecutively. Spaces separate words in the text string.
 * •	Text won’t start or end with a space.
 * •	Indexing of characters in text is zero-based.
 * •	Words list will contain unique strings.
 * •	1 <= number of characters in text <= 1000000
 * •	1 <= number of words <= 100000
 * •	1 <= length of any word in words or in text <= 10
 *
 * Solution
 * We provided three solutions.
 * 1) brute_force_solution.java
 * We literally compare each word from words with every word from the text.
 * When the two are equal we take note of the start index of the word in the text.
 *
 * Time Complexity:
 * O(n*w*l).
 * Let n be the number of words in text,
 * w be the number of words and
 * l be the maximum length of a word.
 *
 * Processing each pair of words takes O(l) time as we compare them character by
 * character. We compare each of n words with every one of w words for the total
 * time complexity of O(n*w*l).
 *
 * Auxiliary Space Used:
 * O(n+w).
 * The list of lists that we return takes O(w+n) space.
 * Since words are unique, any given word from text can match at most one word from
 * words so the total number of indexes in the returned list of lists won’t exceed n
 * and we know that the outer list has exactly w lists, giving us a total of O(w + n).
 *
 * Space Complexity:
 * O((n+w)*l).
 * Adding up O(w*l) of words, O(n*l) of text and O(w+n) of the auxiliary space
 * we get O((n+w)*l).
 *
 * 2) optimal_solution1.java
 * In this solution we preprocess text and create its index, see “HashMap textIndex”
 * variable. By the time that’d done, each word from the text has the list of its
 * starting indices pre-compiled. All that’s left is to look up those lists of
 * indexes for every word from words.
 *
 * Time Complexity:
 * O((n+w)*l).
 * It takes O(I) time to calculate hashcode or to compare two strings up to l characters
 * long. Thus populating the hashmap with n words will take O(n*l),
 * making w searches in that hashmap will take O(w*l). Total time is the sum of those:
 * O(n*l) + O(w*l) = O((n+w)*l)
 *
 * Auxiliary Space Used:
 * O((n*l)+w).
 *
 * Hashmap which we pre-compute takes O(n*l) space.
 * The list of lists that we return takes O(w+n) space. Summing up the two
 * gives O(n*l) + O(w+n) = O((n*l)+w).
 *
 * Space Complexity:
 * O((n+w)*l).
 * Text input takes O(n*l) and words take O(w*l).
 * Adding up those two and the auxiliary space we get the total space complexity:
 * O(n*l) + O(w*l) + O((n*l)+w) = O((n+w)*l).
 *
 * 3) optimal_solution2.java
 * In this solution we use a trie (prefix tree),
 * see https://en.wikipedia.org/wiki/Trie. First we insert all words from the
 * text into the trie. Then we look up every word from words in the trie.
 *
 * Although this solution has the same worst case time and space complexity as
 * the hashmap based optimal_solution1.java, it will utilize less space when
 * many words share common prefixes.
 *
 * In the actual interview many interviewers will prefer to hear the trie
 * based solution to the hashmap based one.
 * For more hints on hashmap vs. trie based algorithms, see
 * https://stackoverflow.com/questions/245878/how-do-i-choose-between-a-hash-table-and-a-trie-prefix-tree
 *
 * Time Complexity:
 * O((n+w)*l).
 * Insert and search operations in the trie take O(l) time each.
 * The algorithm makes n insertions and w searches.
 *
 * Auxiliary Space Used:
 * O(n*l + w).
 * Trie takes O(n*l) space and the list of lists that we return takes O(w+n).
 * Adding that up we get O(n*l + w).
 *
 * Space Complexity:
 * O((n+w)*l).
 * Text input takes O(n*l) and words take O(w*l). Adding auxiliary space to that
 * we get the total space complexity: O(n*l) + O(w*l) + O(n*l + w) = O((n+w)*l).
 *
 * resources/IndicesOfWordsInText1.jpg
 * resources/IndicesOfWordsInText2.jpg
 * resources/IndicesOfWordsInText3.jpg
 */
public class IndicesOfWordsInText {

    //TC = O(n * w * l)
    //SC = O(n + w)
    public static ArrayList<ArrayList<Integer>> findWordsBruteForce(String text, List<String> words) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        String[] textWords = text.split(" ");

        for(String word: words) {
            int index = 0;
            ArrayList<Integer> indexes = new ArrayList<>();

            for(String s: textWords) {
                if(s.equals(word)) {
                    indexes.add(index);
                }
                index += s.length() + 1;
            }

            if(indexes.isEmpty()) {
                indexes.add(-1);
            }
            result.add(indexes);
        }
        return result;
    }

    //TC = O((n + w) * l)
    //SC = O((n * l) + w)
    private static ArrayList<ArrayList<Integer>> findWordsHashMap(String text, List<String> words) {

        String[] textWords = text.split(" ");
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

        int index = 0;
        for(String s: textWords) {
            ArrayList<Integer> indexes = map.get(s);
            if(indexes == null) {
                indexes = new ArrayList<>();
            }

            indexes.add(index);
            map.put(s, indexes);
            index += s.length() + 1;
        }

        for(String s: words) {
            ArrayList<Integer> indexes = map.get(s);

            if(indexes == null) {
                indexes = new ArrayList<>();
                indexes.add(-1);
            }
            result.add(indexes);
        }
        return result;
    }

    //TC = O((n + w) * l)
    //SC = O(n * l + w)
    public static TrieNode root;
    public static ArrayList<ArrayList<Integer>> findWordsTrie(String text, List<String> words) {
        buildTrie(text); //TC = O(n * l)

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(String word: words) {
            result.add(search(word)); //TC = O(w * l)
        }
        return result;
    }

    private static void buildTrie(String text) {
        root = new TrieNode();

        String[] textWords = text.split(" ");

        int index = 0;
        for(String s: textWords) {
            insert(s, index);
            index += s.length() + 1;
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
        current.indexes.add(index);
    }

    private static ArrayList<Integer> search(String word) {
        TrieNode current = root;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            TrieNode node = current.children.get(ch);
            if(node == null) {
                ArrayList<Integer> indexes = new ArrayList<>();
                indexes.add(-1);
                return indexes;
            }
            current = node;
        }

        if(current.indexes.isEmpty()) {
            ArrayList<Integer> indexes = new ArrayList<>();
            indexes.add(-1);
            return indexes;
        } else {
            return current.indexes;
        }
    }

    public static void main(String[] args) {
        String text = "you are very very smart";
        List<String> words = new ArrayList<>();
        words.add("you");
        words.add("are");
        words.add("very");
        words.add("handsome");

        System.out.println(findWordsBruteForce(text, words));
        System.out.println(findWordsHashMap(text, words));
        System.out.println(findWordsTrie(text, words));
    }

}
