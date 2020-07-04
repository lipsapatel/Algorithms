import java.util.Arrays;

/**
 * Given a string S and a character C, return an array of integers
 * representing the shortest distance from the character C in the string
 * to every other character.
 *
 * Example 1:
 *
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 * s = "geeksforgeeks"
 * c = 'e'
 *
 * output = [1, 0, 0, 1, 2, 3, 3, 2, 1, 0, 0, 1, 2]
 *
 * Approach
 *
 * 1) Find distance from prev character c while going from left to right and right
 * to left
 * 2) Left to right
 *    prev = Integer.MIN_VALUE
 *    i - prev
 * 3) Right to left
 *    prev = Integer.MAX_VALUE
 *    prev - i
 * 4) Take minimum of two answer
 *
 * For doing /2
 * Integer.MIN_VALUE is -2147483648, but the highest value a 32 bit integer can
 * contain is +2147483647. Attempting to represent +2147483648 in a 32 bit int
 * will effectively "roll over" to -2147483648. This is because, when using signed
 * integers, the two's complement binary representations of +2147483648 and
 * -2147483648 are identical. This is not a problem, however, as +2147483648 is
 * considered out of range.
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ShortestDistanceToCharacter {

    public static int[] shortestDistanceToChar(String s, char c) {
        int[] ans = new int[s.length()];

        int prev = Integer.MIN_VALUE/2; //avoid overflow
        //Left to right
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c) {
                prev = i;
            }
            ans[i] = i - prev;
        }

        //Right to left
        prev = Integer.MAX_VALUE/2;
        for(int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) == c) {
                prev = i;
            }
            ans[i] = Math.min(ans[i], prev - i);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "loveleetcode";
        char c = 'e';

        System.out.println(Arrays.toString(shortestDistanceToChar(s, c)));

        s = "geeksforgeeks";

        System.out.println(Arrays.toString(shortestDistanceToChar(s, c)));
    }
}
