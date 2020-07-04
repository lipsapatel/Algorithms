import Node.TrieNode;

import java.util.*;

/**
 * Design a search autocomplete system for a search engine.
 * Users may input a sentence (at least one word and end with a
 * special character '#'). For each character they type except '#',
 * you need to return the top 3 historical hot sentences that have
 * prefix the same as the part of sentence already typed.
 *
 * Here are the specific rules:
 *
 *     The hot degree for a sentence is defined as the number of times
 *     a user typed the exactly same sentence before.
 *
 *     The returned top 3 hot sentences should be sorted by hot degree
 *     (The first is the hottest one). If several sentences have the
 *     same degree of hot, you need to use ASCII-code order (smaller one
 *     appears first).
 *
 *     If less than 3 hot sentences exist, then just return as many
 *     as you can.
 *     When the input is a special character, it means the sentence ends,
 *     and in this case, you need to return an empty list.
 *
 * Your job is to implement the following functions:
 *
 * The constructor function:
 *
 * AutocompleteSystem(String[] sentences, int[] times): This is the
 * constructor. The input is historical data. Sentences is a string
 * array consists of previously typed sentences. Times is the
 * corresponding times a sentence has been typed. Your system should
 * record these historical data.
 *
 * Now, the user wants to input a new sentence. The following function
 * will provide the next character the user types:
 *
 * List<String> input(char c): The input c is the next character
 * typed by the user. The character will only be lower-case letters
 * ('a' to 'z'), blank space (' ') or a special character ('#').
 * Also, the previously typed sentence should be recorded in your system.
 * The output will be the top 3 historical hot sentences that have
 * prefix the same as the part of sentence already typed.
 *
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman",
 * "i love leetcode"], [5,3,2,2])
 *
 * The system have already tracked down the following sentences and
 * their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 *
 * Now, the user begins another search:
 *
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i".
 * Among them, "ironman" and "i love leetcode" have same hot degree.
 * Since ' ' has ASCII code 32 and 'r' has ASCII code 114,
 * "i love leetcode" should be in front of "ironman".
 * Also we only need to output top 3 hot sentences, so "ironman"
 * will be ignored.
 *
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 *
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 *
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved
 * as a historical sentence in system. And the following input will
 * be counted as a new search.
 *
 * Note:
 *
 *     The input sentence will always start with a letter and end with '#',
 *     and only one blank space will exist between two words.
 *
 *     The number of complete sentences that to be searched won't exceed 100.
 *     The length of each sentence including those in the historical data
 *     won't exceed 100.
 *
 *     resources/DesignSearchAutocompleteSystem1.jpg
 *     resources/DesignSearchAutocompleteSystem2.jpg
 *     resources/DesignSearchAutocompleteSystem3.jpg
 *     resources/DesignSearchAutocompleteSystem4.jpg
 *     resources/DesignSearchAutocompleteSystem5.jpg
 *     resources/DesignSearchAutocompleteSystem6.jpg
 *
 *     Brute Force Approach
 *
 *     1) Create hashmap of sentences and times
 *     2) Find the key in HashMap whose initial characters matches
 *        currSentence.
 *     3) Add to list - sentence and times
 *     4) Sort the list and return first three values from list
 *
 *     Time Complexity:
 *
 *     O(k * l) - Create hash value of sentence of length k
 *                l = no of sentences.
 *
 *     O(n) - Iterate list of sentences in map
 *     O(mlogm) - Sort the list of length m
 *
 *     O(k*l + n + mlogm)
 *
 *     Trie Approach
 *
 *     1) Create trie of all sentences.
 *     2) Add times to leaf node.
 *     3) Search the prefix or currSentence
 *     4) Do DFS to find all the matching sentences.
 *     5) Add all the sentences and times to priority queue
 *     6) And return top 3
 *
 *     Time Complexity:
 *
 *     O(k * l) - Create trie l = no of sentence
 *                Each sentence is of length k
 *
 *     O(p + q + mlogm) p = length of prefix or currSentence
 *                      q = remaning length to form sentence
 *                      mlogm sort m = length of priority queue
 */
public class DesignSearchAutocompleteSystem {

    public StringBuilder prefix;
    public TrieNode root;

    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = new StringBuilder();

        //Add to trie
        for(int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    public void insert(String s, int times) {
        TrieNode current = root;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            TrieNode node = current.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
        current.times = current.times + times;
    }

    public List<String> input(char c) {

        if(c == '#') {
            insert(prefix.toString(), 1);
            prefix = new StringBuilder();
            return Collections.EMPTY_LIST;
        } else {
            prefix.append(c);

            //Search for that prefix
            TrieNode current = root;
            for(int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);

                TrieNode node = current.children.get(ch);

                if(node == null) {
                    return Collections.EMPTY_LIST;
                }
                current = node;
            }

            //Do DFS to find all matching sentences
            PriorityQueue<PqNode> pq = new PriorityQueue(new Comparator<PqNode>() {

                @Override
                public int compare(PqNode n1, PqNode n2) {
                    if(n1.times == n2.times) {
                        return n1.sentence.compareTo(n2.sentence);
                    } else {
                        return n2.times - n1.times;
                    }
                }
            });

            //DFS
            getMatchingSentences(prefix.toString(), current, pq);

            //Return top 3
            List<String> result = new ArrayList<>();
            int i = 0;

            while(i < 3 && !pq.isEmpty()) {
                result.add(pq.poll().sentence);
                i++;
            }
            return result;
        }
    }

    //DFS
    public void getMatchingSentences(String prefix, TrieNode current,
                                     PriorityQueue<PqNode> pq) {

        //Base Case
        if(current.endOfWord) {
            pq.offer(new PqNode(prefix, current.times));
            //Since we are find all the matching sentences,
            //continue find
        }

        for(Map.Entry<Character, TrieNode> child: current.children.entrySet()) {
            getMatchingSentences(prefix + child.getKey(), child.getValue(), pq);
        }
    }

    public class PqNode {
        public String sentence;
        public int times;

        public PqNode(String s, int t) {
            sentence = s;
            times = t;
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};

        DesignSearchAutocompleteSystem designSearchAutocompleteSystem =
                new DesignSearchAutocompleteSystem(sentences, times);

        List<String> result = designSearchAutocompleteSystem.input('i');
        System.out.println("i result");
        for(String r: result) {
            System.out.println(r);
        }

        result = designSearchAutocompleteSystem.input(' ');
        System.out.println("i  result");
        for(String r: result) {
            System.out.println(r);
        }

        /**
         * ["AutocompleteSystem","input","input","input","input","input","input","input","input","input","input","input","input","input","input"]
         * [[["abc","abbc","a"],[3,3,3]],["b"],["c"],["#"],["b"],["c"],["#"],["a"],["b"],["c"],["#"],["a"],["b"],["c"],["#"]]
         */

        result = designSearchAutocompleteSystem.input('a');
        System.out.println("i a result");
        for(String r: result) {
            System.out.println(r);
        }

        result = designSearchAutocompleteSystem.input('#');
        System.out.println("i a# result");
        for(String r: result) {
            System.out.println(r);
        }
    }
}
