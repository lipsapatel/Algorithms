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
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ReverseStringWithoutReversingWord {

    private static String reverseStringWithoutReversingWord(String s) {
        int i = s.length() - 1;
        int start = i + 1;
        int end = i + 1;
        String result = "";

        //Scan from right to left
        while(i >= 0) {

            //Found space, so add word to result
            if(s.charAt(i) == ' ') {
                start = i + 1;
                while(start != end) {
                    result += s.charAt(start);
                    start++;
                }
                result += ' ';

                //Update end
                end = i;
            }
            i--; //Decrement till found space.
        }

        //For last word
        start = 0;
        while(start != end) {
            result += s.charAt(start);
            start++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "My dog ran";
        System.out.println(reverseStringWithoutReversingWord(s));
    }
}
