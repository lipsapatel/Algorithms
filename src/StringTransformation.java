import java.util.*;

/**
 * String Transformation Using Given Dictionary Of Words

 Problem Statement:
 You are given a dictionary of words named words, and two strings named start and stop. All given strings have
 equal length. Dictionary words are not in any particular order, there may be duplicates, too.
 You need to transform string start to string stop using given dictionary words. In each transformation,
 you can only change one character of the current string. e.g. "abc" -> "abd" is a valid transformation,
 because only one character 'c' is changed to 'd', but, "abc" -> "axy" is not a valid transformation,
 because two characters are changed, character 'b' is changed to 'x' and character 'c' is changed to 'y'.

 In other words, you need to find out the least amount of transformations between two words start and stop,
 given a specific set of allowed transformations words. In other words, you need to find the shortest possible
 sequence of strings (two or more strings) such that:

 First string is start.
 Last string is stop.
 Every  string (except the first one) differs from the previous one by exactly one character.
 Every string (except, possibly, first and last ones) are in the dictionary of words.
 i.e. output = [start, <strings from the given dictionary>, stop] and len(output) >= 2.

 If two or more such sequences exist, any one of them is a correct answer.
 If no such sequence is there to be found, [“-1”] (a sequence of one string, “-1”) is the correct answer.

 Constraints:
 All input strings consist of lowercase Latin characters only.
 0 <=  total number of characters in all dictionary words combined <= 10^5.

 Input/Output Format For The Function:
 Input Format:
 There are three arguments:
 Array of strings words,
 String start,
 String stop.

 Output Format:
 Function must return an array of strings of length >= 2, where the first string is start and the last string is stop,
 if the transformation is possible. Else return an array of strings containing only one string "-1", i.e. return ["-1"].

 Sample Test Cases:
 Sample Test Case 1:
Sample Input 1:
 words = ["cat", "hat", "bad", "had"]
 start = "bat"
 stop = "had"

 Sample Output 1:
 ["bat", "bad", "had"]
 or
 ["bat", "hat", "had"]

 Explanation 1:
 From "bat" change character 't' to 'd', so new string will be "bad".
 From "bad" change character 'b' to 'h', so new string will be "had".
 or
 From "bat" change character 'b' to 'h', so new string will be "hat".
 From "hat" change character 't' to 'd', so new string will be "had".

 Sample Test Case 2:
 Sample Input 2:
 words = []
 start = bbb
 stop = bbc

 Sample Output 2:
 ["bbb", "bbc"]

 Explanation 2:
 From "bbb" change the last character 'b' to 'c', so new string will be "bbc".

 Sample Test Case 3:
 Sample Input 3:
 words = []
 start = "zzzzzz"
 stop = "zzzzzz"

 Sample Output 3:
 [-1]

 Explanation 3:
 Function must return an array of strings of length >= 2, where the first string is start and the last string is stop,
 if the transformation is possible. Else return an array of strings containing only one string "-1", i.e. return ["-1"].

 Here, the words dictionary is empty and ["zzzzzz", "zzzzzz"] is not a valid transformation hence return ["-1"].

 Sample Test Case 4:
 Sample Input 4:
 words = ["cccw", "accc", "accw"]
 start = "cccc"
 stop = "cccc"

 Sample Output 4:
 ["cccc", "cccw", "cccc"]
 Or:
 ["cccc", "accc", "cccc"]

 Explanation 4:
 All the given conditions are met.

 Approach
 *
 * 1) The problem is to find the shortest path from start to end and return that path
 * 2) The path can be build using backRefs
 * 3) The shortest path can be found using bfs
 * 4) We need to create adjacencylist or graph
 * 5) Creating graph will take O(NM) time where N = no of words and M = word length
 * 6) getNeighbors will return union of all the list excluding the word itself
 *
 * Time Complexity = O(NM) where n = no of words and m = length of each word
 * Space Complexity = O(NM)
 *
 * This solution runs out of memory (Java heap space) if the word length (m) is too big.
 */
public class StringTransformation {

    private static String[] stringTransformation(String[] words, String start, String end) {
        HashSet<String> wordsSet = new HashSet<>();

        //Add start and end if in case they are not already present
        wordsSet.add(start);
        wordsSet.add(end);

        for(String word: words) {
            wordsSet.add(word);
        }

        //nm create graph + nm if differ by one char, directly go to end = 2nm
        HashMap<String, HashSet<String>> graph = createGraph(wordsSet);
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String, String> backRefs = new HashMap<>();
        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            String poppedNode = queue.remove();

            //This is for 4th input
            //If there's only one character difference with the end word then you got the shortest path
            //cccc -> accc -> cccc
            if(oneCharDifference(poppedNode, end)) { //mn
                return buildPath(backRefs, poppedNode, end);
            }

            HashSet<String> neighbors = getNeighbors(poppedNode, graph);

            if(neighbors != null) {
                for(String av: neighbors) {
                    if(!visited.contains(av)) {
                        queue.add(av);
                        visited.add(av);
                        backRefs.put(av, poppedNode);
                        //if(av.equalsIgnoreCase(end)) {
                        //    return buildPath(backRefs, end, false, end);
                        //}
                    }
                }
            }
        }
        return new String[]{"-1"};
    }

    private static boolean oneCharDifference(String current, String end) {
        int difference = 0;

        for(int i = 0; i < current.length(); i++) {
            if(current.charAt(i) != end.charAt(i)) {
                difference++;

                if(difference > 1) {
                    return false;
                }
            }
        }
        return difference == 1;
    }

    private static String[] buildPath(HashMap<String, String> backRefs, String end, String actualEnd) {
        List<String> result = new ArrayList<>();

        result.add(0, end);
        String current = end;

        while(backRefs.get(current) != null) {
            current = backRefs.get(current);
            result.add(0, current);
        }

        result.add(result.size(), actualEnd);
        return result.toArray(new String[0]);
    }

    private static HashSet<String> getNeighbors(String word, HashMap<String, HashSet<String>> graph) {

        HashSet<String> result = new HashSet<>();

        for(int i = 0; i < word.length(); i++) {
            String key = word.substring(0, i) + '*' + word.substring(i + 1, word.length());
            HashSet<String> set = graph.get(key);
            for(String s: set) {
                if(!s.equals(word)) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    private static HashMap<String, HashSet<String>> createGraph(HashSet<String> wordsSet) {
        HashMap<String, HashSet<String>> graph = new HashMap<>();

        for(String word: wordsSet) {
            for(int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + '*' + word.substring(i + 1, word.length());

                if(graph.containsKey(key)) {
                    HashSet<String> neighbor = graph.get(key);
                    neighbor.add(word);
                    graph.put(key, neighbor);
                } else {
                    HashSet<String> neighbor = new HashSet<>();
                    neighbor.add(word);
                    graph.put(key, neighbor);
                }
            }
        }
        return graph;
    }

    /******************************************************************************************************************
     * Consider this method as a following up question depending on the list of words length or each word length.
     * The above solution might have possibility of running out of heap memory space.
     *
     * This problem can be solved using BFS.
     From current string, when we want to update neighbor strings (strings having different character at exactly one position),
     there are two methods possible:

     1) Visit every string in words array and check.
     There are n strings in words array and each having length m. So, for one string to find neighbor strings,
     time taken will be O(n * m). And we will find neighbors for O(n) strings, hence time complexity of this solution
     will be O(n ^ 2 * m). Now when value of n is high, this solution will timeout.

     2) For current string we will generate all possible strings having different character at exactly one position,
     and we will update strings that are in words array i.e. they are neighbors.
     We can use hashmap to check if any string is in words array or not in O(m) time, instead of O(n * m) time using simple array search.

     Now there can be O(26 * m) different strings having different character at exactly one position.
     And for each string we will spend O(m) time to check if it is in words array or not.
     We will find neighbors for O(n) strings, hence time complexity of this solution will be
     O(n * m^2 * 26). Now when string length is high, this solution will timeout.

     So, we can combine both methods in one solution to bring down time complexity to O((n * m * min((n, 26 * m)).
     When (n <= 26 * m) use first method and when (n > 26 * m) use second method!

     Have a look at the solution provided by us, it contains necessary comments to understand the solution.

     Time Complexity:
     O(n * m * min(n, 26 * m)).

     Auxiliary Space Used:
     O(n * m).

     Space Complexity:
     O(n * m).

     As input is O(n * m) and auxiliary space used is also O(n * m). So, O(n * m) + O(n * m) -> O(n * m).
     */

    private static String[] stringTransformationFollowUp(String[] words, String start, String end) {
        HashSet<String> wordsSet = new HashSet<>();
        wordsSet.add(start);
        wordsSet.add(end);

        //We can do contains operation in O(1)
        for(String s: words) {
            wordsSet.add(s);
        }

        //Choose n^2 * m or n * m^2 * 26
        int nWords = words.length;
        boolean use1 = false;

        if(nWords <= start.length() * 26) {
            use1 = true;
        } else {
            use1 = false;
        }

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String, String> backRefs = new HashMap<>();
        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            String poppedNode = queue.remove();

            if(oneCharDifference(poppedNode, end)) {
                return buildPath(backRefs, poppedNode, end);
            }

            List<String> neighbors;
            if(use1) {
                neighbors = getNeighbors1(wordsSet, poppedNode);
            } else {
                neighbors = getNeighbors2(wordsSet, poppedNode);
            }

            for(String av: neighbors) {
                if(!visited.contains(av)) {
                    queue.add(av);
                    visited.add(av);
                    backRefs.put(av, poppedNode);
                }
            }
        }
        return new String[]{"-1"};
    }

    private static List<String> getNeighbors1(HashSet<String> wordsSet, String word) {

        List<String> neighbors = new ArrayList<>();

        for(String s : wordsSet) {
            if(s.equals(word)) {
                continue;
            }
            if(oneCharDifference(s, word)) {
                neighbors.add(s);
            }
        }
        return neighbors;
    }

    private static List<String> getNeighbors2(HashSet<String> wordsSet, String word) {

        List<String> neighbors = new ArrayList<>();
        char[] wordArray = word.toCharArray();

        for(int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];

            //Replace with 26 characters
            for(int j = 0; i < 26; j++) {
                char newChar = (char)('a' + j);

                if(newChar != c) {
                    wordArray[i] = newChar;

                    String neighbor = new String(wordArray);
                    if(wordsSet.contains(neighbor)) {
                        neighbors.add(neighbor);
                    }
                }
            }
            wordArray[i] = c;
        }
        return neighbors;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "hat", "bad", "had"};
        String start = "bat";
        String end = "had";

        String[] result = stringTransformation(words, start, end);
        System.out.println("The shortest path to string transformation is ");
        for(String s: result) {
            System.out.print(s + " ");
        }

        System.out.println();
        String[] result11 = stringTransformationFollowUp(words, start, end);
        System.out.println("The shortest path to string transformation is ");
        for(String s: result11) {
            System.out.print(s + " ");
        }

        System.out.println();
        String[] words1 = {};
        start = "bbb";
        end = "bbc";

        String[] result1 = stringTransformation(words1, start, end);
        System.out.println("The shortest path to string transformation is ");
        for(String s: result1) {
            System.out.print(s + " ");
        }

        System.out.println();
        String[] result12 = stringTransformationFollowUp(words1, start, end);
        System.out.println("The shortest path to string transformation is ");
        for(String s: result12) {
            System.out.print(s + " ");
        }

        System.out.println();
        String[] words2 = {};
        start = "zzzzzz";
        end = "zzzzzz";

        String[] result2 = stringTransformation(words2, start, end);
        System.out.println("The shortest path to string transformation is ");
        for(String s: result2) {
            System.out.print(s + " ");
        }

        System.out.println();
        String[] result22 = stringTransformationFollowUp(words2, start, end);
        System.out.println("The shortest path to string transformation is ");
        for(String s: result22) {
            System.out.print(s + " ");
        }

        System.out.println();
        String[] words3 = {"cccw", "accc", "accw"};
        start = "cccc";
        end = "cccc";

        String[] result3 = stringTransformation(words3, start, end);
        System.out.println("The shortest path to string transformation is ");
        for(String s: result3) {
            System.out.print(s + " ");
        }

        System.out.println();
        String[] result33 = stringTransformationFollowUp(words3, start, end);
        System.out.println("The shortest path to string transformation is ");
        for(String s: result33) {
            System.out.print(s + " ");
        }
    }
}
