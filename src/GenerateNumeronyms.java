import java.util.ArrayList;
import java.util.List;

/**
 * Generate Numeronyms
 * Given a string word of length n, generate all possible numeronyms.
 *
 * What is a Numeronym?
 * A numeronym is a word where a number is used to form an abbreviation.
 * For a given string word, a numeronym is a string with few or more contiguous
 * letters between the first letter and last letter of word replaced with a number
 * representing the count of letters omitted. Only one set of contiguous letters are
 * replaced by a number.
 *
 * e.g. “L10n” is called a numeronym of the word “Localization”, where 10
 * stands for the count of letters between the first letter 'L' and the last letter 'n'
 * in the word.
 *
 * Example
 * Input: “nailed”
 * Output: ["n4d", "na3d", "n3ed", "n2led", "na2ed", "nai2d"]
 * “n4d” is an abbreviation of given string “nailed” where “aile” string letters are omitted and replaced by count of letters, i.e. 4.
 * “na3d” is an abbreviation of given string “nailed” where “ile” string letters are omitted and replaced by count of letters, i.e. 3.
 * And so on.
 *
 * Constraints:
 * •	String will be composed of characters [a-z], [A-Z], [0-9] only.
 * •	1 <= n <= 120 where n is length of given string word.
 *
 * Approach
 *
 * 1) For any given string str, length of omitted characters l can be 2 <= l <= n-2
 * where n is length of string as we can’t omit first and last characters and we
 * need to find a numeronym in which at least 2 contiguous letters were omitted.
 *
 * 2) So for any given length l, we iterate over all possible positions from
 * where omission of characters can start, find a string of length l from that
 * position and replace that with l (number of omitted characters).
 *
 * Time Complexity:
 * O(n^3) where n is length of given string str.
 * As iteration will be in three loops, first over possible lengths
 * then over possible first characters of omitted characters and then to find
 * store newly created numeronym.
 *
 * Space Complexity:
 * Auxiliary Space Used:
 * O(n^3) where n is length of given string str.
 * Maximum number of possible numeronyms generated can be O(n^2) and
 * length of each will be O(n) hence it takes O(n^3) to store output.
 *
 */
public class GenerateNumeronyms {

    private static String[] numeronyms(String word) {
        List<String> numeronyms = new ArrayList<>();

        int n = word.length();

        for(int len = 2; len <= n - 2; len++) {
            for(int i = 1; i <= n - 1 - len; i++) {
                numeronyms.add(word.substring(0, i) + len + word.substring(i + len, n));
            }
        }

        int size = numeronyms.size();
        String[] result = new String[size];

        for(int i = 0; i < size; i++) {
            result[i] = numeronyms.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        String[] result = numeronyms("nailed");

        for(String word: result) {
            System.out.println(word);
        }
    }
}
