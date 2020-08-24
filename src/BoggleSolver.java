import Node.TrieNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Boggle Solver
 *
 *  Problem Statement:
 * You are given a dictionary set dictionary that contains dictionaryCount distinct words
 * and a matrix mat of size n*m.
 *
 * Your task is to find all possible words that can be formed by a sequence
 * of adjacent characters in the matrix mat.
 *
 * Note that we can move to any of 8 adjacent characters, but a word should
 * not have multiple instances of the same cell of the matrix.
 *
 * Note: Same dictionary word can be found in the matrix multiple times.
 * We only need to check the existence of the dictionary word in the matrix.
 * Hence, for multiple existences for the same word only add it once in the list
 * of all found words.
 *
 * Input Format:
 * If dictionary = ["hat”, “world” ] and mat [ “aaa”, “hat”, “ccc”],
 * then the corresponding custom input will be:
 *
 * 2
 * hat
 * world
 *
 * 3
 * 3
 * aaa
 * hat
 * ccc
 *
 * Output Format:
 * Print all the dictionary words found in the matrix mat in a separate line.
 * For the above-provided custom input only one word is found and hence the
 * custom output looks like:
 *
 * hat
 *
 * Constraints:
 *
 *     1 <= dictionaryCount <= 1000
 *     1<= n*m <= 100000
 *     1<= length(words in dictionary) <= 100
 *     All the characters in mat and words in dictionary are lower case English alphabets.
 *
 * Sample Test Case:
 * dictionary = [ “bst” , “heap” , “tree”]
 * mat = [ “bsh”  , ”tee” , ”arh” ]
 *
 * Sample Output:
 * Function returns the list result = [ “bst” , “tree” ]
 *
 * Explanation:
 *
 * The input matrix is represented below:
 * bsh
 * tee
 * arh
 *
 * Assume here top left-most corner is at (0,0) and bottom right-most corner is (2,2).
 *
 * (0,0) -> (0,1) -> (1,0)
 *
 * Presence of “tree” is marked bold in the below representation:
 *
 * (1,0) -> (2,1) -> (1,1) -> (1,2)
 *
 * Brute Force Solution
 *
 * 1) Insert all words from dictionary into HashSet for constant lookup time
 * 2) Iterate over all cells of matrix and assume each cell as the first character of word.
 * 3) Visit all it's neighbors and build word. There are 8 neighbors in total.
 *    One is already visited so 7 neighbors.
 * 4) Keep visited matrix.
 * 5) For each word do lookup in HashSet. If found, add to list of found words.
 * Remove from HashSet.
 *
 * Time Complexity: O(c + (m * n * 7 ^ max))
 *
 * Where m * n for each cell and for each cell you visit all neighbors 7 ^ max
 * You only word of max length
 *
 * c is the number of words in dictionary added to HashSet.
 *
 * Space Complexity: O(c * max + m * n)
 *                     HashMap   visited
 *
 * resources/BoggleSolverBruteForce1.jpg
 * resources/BoggleSolverBruteForce2.jpg
 *
 * Optimal Approach
 *
 * 1) Store all dictionary words in trie.
 * Fast lookups and also suggests the next character to look in the matrix for a given prefix.
 * 2) Use DFS traversal on mat
 * 3) We will use same approach as brute force solution.
 *    We will iterate over all cells of matrix and for each cell we will do a DFS
 *    traversal on matrix but this time we will be using our trie to guide so as we
 *    only land on valid neighbors.
 * 4) Everytime we find a word, we remove it from trie.
 *
 * Time Complexity: O(c * max + m * n * 7 ^ max)
 *                    Trie       Find word
 *
 * Space Complexity: O(c * max + m * n)
 *                     Trie      visited
 *
 * resources/BoggleSolverOptimal1.jpg
 * resources/BoggleSolverOptimal2.jpg
 * resources/BoggleSolverOptimal3.jpg
 *
 */
public class BoggleSolver {

    private static List<String> boggleSolverBruteForce(List<String> dictionary,
                                                       List<String> mat) {
        //Insert all the words from dictionary into HashSet
        HashSet<String> set = new HashSet<>(dictionary);

        //Find the max length word in dictionary
        int maxLen = 0;

        for(String word: dictionary) {
            maxLen = Math.max(maxLen, word.length());
        }

        List<String> result = new ArrayList<>();

        int r = mat.size();
        int c = mat.get(0).length();

        boolean[][] visited = new boolean[r][c];

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                char ch = mat.get(i).charAt(j);
                solveForPosition(1, maxLen, i, j, mat, set, visited, result, "" + ch);
            }
        }
        return result;
    }

    private static void solveForPosition(int len, int maxLen, int r, int c,
                                         List<String> mat, HashSet<String> set,
                                         boolean[][] visited, List<String> result,
                                         String word) {

        if(len > maxLen) {
            return;
        }

        visited[r][c] = true;

        //If set contains then add to result and remove from set
        if(set.contains(word)) {
            result.add(word);
            set.remove(word);
        }

        //Iterate on all 8 directions
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                if(valid(r + i, c + j, mat) && !visited[r + i][c + j]) {
                    solveForPosition(len + 1, maxLen, r + i, c + j, mat,
                            set, visited, result, word + mat.get(r + i).charAt(c + j));
                }
            }
        }
        visited[r][c] = false;
    }

    private static boolean valid(int x, int y, List<String> mat) {
        int r = mat.size();
        int c = mat.get(0).length();

        if(x < 0 || x >= r || y < 0 || y >= c) {
            return false;
        }
        return true;
    }

    /*****************************************************************************/

    public static TrieNode root = new TrieNode();

    public static List<String> boggleSolver(List<String> dictionary, List<String> mat) {
        List<String> result = new ArrayList<>();

        if(mat.size() == 0) {
            return result;
        }

        for(String word: dictionary) {
            insertTrie(word);
        }

        int r = mat.size();
        int c = mat.get(0).length();

        boolean[][] visited = new boolean[r][c];

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                char ch = mat.get(i).charAt(j);

                if(root.children.containsKey(ch)) {
                    solve(mat, i, j, root.children.get(ch), ""+ch, result, visited);
                }
            }
        }
        return result;
    }

    public static void solve(List<String> mat, int x, int y, TrieNode current, String word,
                             List<String> result, boolean[][] visited) {

        if(current.endOfWord) {
            result.add(word);
            delete(word);
        }

        visited[x][y] = true;

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {

                int r = x + i;
                int c = y + j;

                if(valid(r, c, mat) && !visited[r][c] && current.children.
                        containsKey(mat.get(r).charAt(c))) {
                    solve(mat, r, c, current.children.get(mat.get(r).charAt(c)),
                            word + mat.get(r).charAt(c), result, visited);
                }
            }
        }
        visited[x][y] = false;
    }

    public static void insertTrie(String word) {
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
        current.endOfWord = true;
    }

    public static void delete(String word) {
        delete(word, root, 0);
    }

    public static boolean delete(String word, TrieNode current, int index) {

        if(index == word.length()) {
            if(!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            return current.children.size() == 0;
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        if(node == null) {
            return false;
        }

        boolean shouldDeleteNode = delete(word, node, index + 1);

        if(shouldDeleteNode) {
            current.children.remove(ch);
            return current.children.size() == 0 && !current.endOfWord;
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> dict = new ArrayList<>();
        dict.add("bst");
        dict.add("abs");
        dict.add("tab");

        List<String> mat = new ArrayList<>();
        mat.add("ast");
        mat.add("bxr");

        List<String> result = boggleSolverBruteForce(dict, mat);
        System.out.println(result.toString());

        System.out.println("Trie Optimal approach");
        System.out.println(boggleSolver(dict, mat).toString());
    }
}
