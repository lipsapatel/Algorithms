import java.sql.SQLOutput;

/**
 * Run Length Encoder
 * Compress a string of alphabetic characters with the basic encoding
 * where you simply count the number of repeated characters.
 *
 * Example One
 * Input: AAAAA
 * Output: 5A
 *
 * Character “A” is repeated 5 times consecutively.
 *
 * Example Two
 * Input: ABaaaBCC
 * Output: AB3aB2C
 *
 * Character “a” is repeated 3 times consecutively, character “C” is repeated 2
 * times consecutively.
 *
 * Constraints:
 *
 *     Input string consists of alphabetic characters only
 *     1 <= length of the string <= 10^5
 *
 * Approach
 *
 * 1) Count the number of characters and add to result string if count > 1
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class RunLengthEncoder {

    public static String RLE(String s) {
        StringBuilder result = new StringBuilder();

        char prev = s.charAt(0);
        int count = 1;

        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == prev) {
                count++;
            } else {
                if (count > 1) {
                    result.append(count);
                    count = 1;
                }
                result.append(prev);
                prev = s.charAt(i);
            }
        }

        //For last count
        if(count > 1) {
            result.append(count);
        }
        result.append(prev);

        return result.toString();
    }

    public static void main(String[] args) {
        String s = "AAAAA";
        System.out.println("Run length Encoder " + RLE(s));

        String s1 = "ABaaaBCC";
        System.out.println("Run length Encoder " + RLE(s1));
    }
}
