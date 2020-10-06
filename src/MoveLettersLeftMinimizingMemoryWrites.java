/**
 * Move All Letters To Left Side With Minimizing Memory Writes
 * You are given a string s, which may contain alphabet letters ('a' - 'z' or
 * 'A' - 'Z') as well as numerical characters ('0' - '9'), in random order.
 * Numerical characters are garbage characters and we don't care about them.
 * Inside the same string, you have to make all alphabet letters appear on the left
 * side though in the same order they appeared originally.
 * Suppose in our architecture, memory write is a very expensive operation,
 * so we have to minimize the number of writes. As digits are garbage, we need not
 * to move them on the right side. Here we can save some memory writes!
 *
 * Example One
 * Input: “1x”
 * Output: “xx”

 * “xx” is the only correct answer. “x1”, for example, is a wrong answer since
 * reaching that string would have required two memory writes while reaching “xx”
 * requires one.
 *
 * Example Two
 * Input: "0a193zbr"
 * Output: "azbr3zbr"
 *
 * In the given string letters are a, z, b and r. We can move all four letters to
 * the left side with 4 write operations and get the string "azbr3zbr".
 * Reaching any other string would have taken more than four writes, so "azbr3zbr"
 * is the only correct answer.
 *
 * Constraints:
 *
 *     1 <= length of s <= 10^5
 * An in-place linear solution is expected.
 * For languages that have immutable strings, convert the input string into a
 * character array and work in-place on that array. Convert it back to the string
 * before returning. Ignore the extra linear space used in that conversion, as long
 * as you're only using constant space after conversion to a character array.
 *
 * Approach
 *
 * 1) Take two pointers
 *    Left pointer - position where char needs to be written
 *    Right pointer - write char and skip the digit
 * 2) Skip writing if right pointer == left pointer
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * resources/MoveLettersLeftMinimizingMemoryWrites1.jpg
 * resources/MoveLettersLeftMinimizingMemoryWrites2.jpg
 * resources/MoveLettersLeftMinimizingMemoryWrites3.jpg
 */
public class MoveLettersLeftMinimizingMemoryWrites {

    private static String moveLettersToLeftMinimizingWrites(String s) {
        int left = 0;
        int right = 0;

        char[] sArray = s.toCharArray();

        while(right < s.length()) {
            if(!isDigit(sArray[right])) {
                if(left != right) {
                    sArray[left] = sArray[right];
                }
                left++;
            }
            right++;
        }
        return String.valueOf(sArray);
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        String s = "1x";
        System.out.println("Move letters to left " + moveLettersToLeftMinimizingWrites(s));

        s = "0a193zbr";
        System.out.println("Move letters to left " + moveLettersToLeftMinimizingWrites(s));

        s = "1a2b";
        System.out.println("Move letters to left " + moveLettersToLeftMinimizingWrites(s));

        s = "a1b";
        System.out.println("Move letters to left " + moveLettersToLeftMinimizingWrites(s));

        s = "1a2b";
        System.out.println("Move letters to left " + moveLettersToLeftMinimizingWrites(s));
    }
}
