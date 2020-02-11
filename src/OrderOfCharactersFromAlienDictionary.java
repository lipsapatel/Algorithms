import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Find Order Of Characters From Alien Dictionary
 Given a sorted dictionary of an alien language, you have to find the order of characters in that language.
 (This is a popular interview problem.)
 Generally, dictionary does not contain duplicate values, but for the sake of this problem, assume that dictionary might have duplicate values.
 (Sometimes interviewer tricks the question, to see, how you will handle it.)

 Input Format:
 There is only one argument, array of strings words, which denotes sorted dictionary of an alien language.

 Output Format:
 Return a string ordered, denoting order of characters in the alien language.
 Length of the output string will be the number of different characters present in input dictionary.

 Input Format:
 If n = 5 and words = ["baa", "abcd", "abca", "cab", "cad"], then input should be:
 5
 baa
 abcd
 abca
 cab
 cad

 Output Format:
 For input n = 5 and words = ["baa", "abcd", "abca", "cab", "cad"], output will be:
 bdac

 Constraints:

 1 <= total number of characters in dictionary (not words) <= 10^5
 Character in any word will be lower case alphabet letter.
 Input will be such that it is possible to determine the order uniquely.

 Sample Test Cases:
 Sample Input 1:
 words = ["baa", "abcd", "abca", "cab", "cad"]

 Sample Output 1:
 "bdac"

 Sample Test Case 2:
 words = ["caa", "aaa", "aab"]

 Sample Output 2:
 "cab"

 Notes:
 Here input is given such that it is possible to determine order uniquely.
 But in interview you should clarify these things with interviewer.
 Like if we are given words = ["z" "bc"] then we can only conclude that 'z' will come before 'b', but nothing about the order of 'c'!

 ******************************************************************************************************
 Solution:

 Simple but non-optimal solution is:
 Collect all unique characters. Generate all permutations of those characters. Validate each permutation against the given dictionary.

 Now let's think about optimal solution:
 If you're familiar with topological sort and what it means, however, you'll see that this is a simple application of topological sort.
 Quick recap - Topological sort is ordering the vertices in a directed graph such that vertex A appears before vertex B for all
 directed edges A->B. One way to look at it is that you're given a graph of dependencies and you want to order the vertices
 such that no dependencies are broken when going from left to right.

 So, what is the graph in this question anyway?
 Since we are given the words sorted lexicographically, we know which character precedes what other characters.

 How do we determine these relationships between characters in practice?
 We know a few things about the dictionary ordering.
 In a dictionary, between two adjacent words, one of the following is true:
 1. there is at least one character different. Eg. "abcd" and "abde" in English.
 2. one word is shorter than other. Eg. "abc" and "abcd" in English.

 In case 1, we know from the dictionary's property that the character at mismatch index in the left word appears before its
 counter part in right word in the alphabet.

 In case 2, there is no meaningful information with respect to the alphabet
 It is also necessarily true between two adjacent words, that the characters after the first mismatch do not convey any
 relationship between the characters

 We can compare two adjacent words and try to find a mismatch. First mismatch denotes that, letter in first word will come before
 letter in second word in that alien language. Let's take one example to understand this:

 words =
 [
 "c",
 "aaaa",
 "aaaa",
 "aabc"
 ]

 Then we compare:
 1) "c" and "aaaa", here first mismatch is between 'c' and 'a' hence we are sure that 'c' will come before 'a'.
 2) "aaaa" and "aaaa", here there is no mismatch so we can not conclude anything!
 3) "aaaa" and "aabc", here first mismatch is between 'a' and 'b' hence we are sure that 'a' will come before 'b'.
 Also note that we should only consider first mismatch. So from second mismatch concluding that 'a' will come before 'c' is wrong!

 Now total information we have collected is:

 1) 'c' comes before 'a'
 2) 'a' comes before 'b'
 Combining them we can figure out the order of characters is 'cab' in the given alien language.

 Here we can use directed graph to combine the information collected by comparing words. Add an directed edge between
 first mismatched characters! Our directed graph will be directed acyclic graph! Now on DAG we can use topological sort
 to get the order of characters!

 Time complexity:
 In the solution one word will be compared maximum two times. With 1) previous word and 2) next word.
 So comparing words and finding edges will take O(2 * total number of characters) = O(total number of characters).

 Also an edge is added when a mismatch is found. Maximum number of mismatch will be <= number of words.
 So in our directed graph |V| is O(number of different characters) and |E| is O(number of words).
 We know that topological sort takes O(V + E) time, so that is O(number of different characters + number of words).

 So our overall time complexity will be O(total number of characters + number of different characters + number of words) =
 O(total number of characters).

 Space complexity:
 Input is O(total number of characters) and graph we will build will be O(number of different characters + number of words).
 So space complexity is also O(total number of characters).

 **********************************************************************************************************************

 Approach:

 1) Vertices in graph will be all unique characters.
 2) Create Graph by comparing two words to determine directed edge.
 3) Do DFS and topological sort

 TC = O(V + E) where V = no of different characters E = no of words Comparison = total number of characters
 O(total number of characters)

 SC = O(V + E) = O(no of different characters + no of words) = O(total number of characters)
 */
public class OrderOfCharactersFromAlienDictionary {

    private static String findOrder(String[] words) {

        //Build graph
        HashMap<Character, HashSet<Character>> graph = createGraph(words);

        //DFS and topological sort
        HashSet<Character> visited = new HashSet<>();
        Stack<Character> stack = new Stack<>();

        for(Character vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                dfs(vertex, visited, stack, graph);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private static void dfs(Character s, HashSet<Character> visited, Stack<Character> stack, HashMap<Character, HashSet<Character>> graph) {
        visited.add(s);

        HashSet<Character> neighbor = graph.get(s);

        if(neighbor != null) {
            for(Character av: neighbor) {
                if(!visited.contains(av)) {
                    dfs(av, visited, stack, graph);
                }
            }
        }
        stack.add(s);
    }

    private static HashMap<Character, HashSet<Character>> createGraph(String[] words) {
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();

        //Add all the vertices for all unique characters
        for(String word: words) {
            for(int i = 0; i < word.length(); i++) {
                graph.put(word.charAt(i), new HashSet<>());
            }
        }

        //Add all the directed edges by comparing two words
        for (int i = 0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];

            int minLength = Math.min(firstWord.length(), secondWord.length());
            for (int j = 0; j < minLength; j++) {
                if(firstWord.charAt(j) != secondWord.charAt(j)) {
                    //Add edge
                    HashSet<Character> neighbor = graph.get(firstWord.charAt(j));
                    neighbor.add(secondWord.charAt(j));
                    graph.put(firstWord.charAt(j), neighbor);
                    break;
                }
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        String[] input = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println("The order of characters in Alien Dictionary are: " + findOrder(input));
    }
}
