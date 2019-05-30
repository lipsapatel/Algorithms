/**
 * /*
 Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.
 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

/*
Thoughts:
For word A and B.
At one time, A can only be most close to two possible B's from left or right.
For the current A, left-B is known and right-B is unkown, but will encounter in the future.
Therefore, we always only have to keep the two index: indexA, indexB updated and always try to calculate the latest amount the two.
This is quite Greedy.
TC: O(n)
*/
public class ShortestWordDistance {

    private static int shortestDistance(String[] words, String word1, String word2) {

        int word1Index = -1;
        int word2Index = -1;
        int distance = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                word1Index = i;
            } else if (words[i].equals(word2)) {
                word2Index = i;
            }

            if (word1Index >= 0 && word2Index >= 0) {
                distance = Math.min(distance, Math.abs(word1Index - word2Index));
            }
        }
        return distance;
    }

    public static void main(String[] args) {

        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "makes";
        String word2 = "coding";

        System.out.println("Shortest Distance: " + shortestDistance(words, word1, word2));
    }
}
