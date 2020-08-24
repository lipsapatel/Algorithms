import java.util.Arrays;

/**
 * Reverse string without reversing the word.
 * Given string "My dog ran"
 *
 * Output: ran dog my
 *
 * Approach
 * 1) Scan the string from right to left
 * 2) When you encounter space, scan the string starting from character
 * after space till it reaches end and append to result.
 * 3) Append space to result after the word.
 * 4) For last word, start at 0 and end at end and append all characters to result
 *
 * Reverse The Ordering Of Words In A String
 * Given a string s containing a set of words, transform it such that the words
 * appear in the reverse order. Words in s are separated by one or more spaces.

 * Example One
 * Input: “I will do it.”
 * Output: “it. do will I”

 * Example Two
 * Input: "   word1 word2 " (Note: there are 3 spaces in the beginning,
 * 2 spaces between the words and 1 space at the end.)
 * Output: " word2  word1 " (Note: there is 1 space in the beginning,
 * 2 spaces between the words and 3 spaces at the end.)
 *
 * Example Three
 * Input: "word1, word2;"
 * Output: "word2; word1,"
 *
 * Constraints:
 *
 *     1 <= length of s <= 10^5
 *     s contains only lowercase and uppercase alphabetical characters,
 *     spaces and punctuation marks ".,?!':;-" (quotes not included).
 *
 * Punctuation marks are considered a part of the word.
 * Usage of built-in string functions is NOT allowed.
 * An in-place linear solution is expected.
 * For languages that have immutable strings, convert the input string into a
 * character array and work in-place on that array.
 * Convert it back to the string before returning. Ignore the extra linear
 * space used in that conversion, as long as you're only using constant space
 * after conversion to character array.
 *
 * Trivia: This is a very old interview question. Google used it as one of their
 * qualifier questions in Google CodeJam in the past, too.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ReverseStringWithoutReversingWord {

    private static String reverseStringWithoutReversingWord(String s) {
        int i = s.length() - 1;
        int start = i + 1;
        int end = i + 1;
        StringBuilder result = new StringBuilder();

        //Scan from right to left
        while(i >= 0) {

            //Found space, so add word to result
            if(s.charAt(i) == ' ') {
                start = i + 1;

                result.append(s.substring(start, end) + ' ');

                //Update end
                end = i;
            }
            i--; //Decrement till found space.
        }

        //For last word
        if(end != 0) {
            start = 0;
            result.append(s, start, end);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "My dog ran.";
        System.out.println(reverseStringWithoutReversingWord(s));

        s = "   word1  word2 ";
        System.out.println(reverseStringWithoutReversingWord(s));

        s = "word1, word2;";
        System.out.println(reverseStringWithoutReversingWord(s));
    }
}
