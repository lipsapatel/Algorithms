package Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieNode {
    public HashMap<Character, TrieNode> children;
    public boolean endOfWord;
    public int startIdx; //This is for finding the indexes of pattern
    //in txt using suffix trie.

    public int weight; //Prefix and Suffix search

    public int times = 0; //Search autocomplete system

    public ArrayList<Integer> indexes; //IndicesOfWordsInText

    public TrieNode() {
        children = new HashMap<>();
        endOfWord = false;
        startIdx = -1;
        weight = -1;
        indexes = new ArrayList<>();
    }
}
