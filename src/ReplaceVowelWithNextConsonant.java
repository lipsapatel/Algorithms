/**
 * Replace all vowels with next consonant in a given string.
 *
 * Given a string, write an algorithm to replace all the vowels with next consonant, and if last alphabet
 * is vowel, remove it
 *
 * Example:
 *
 * String input = "abcdefg"
 * Output = "bbcdffg"
 *
 * String input = "aaaabccceee"
 * Output = "bbbbbccc"
 *
 * String input = "aaa"
 * Output = ""
 *
 * Approach
 *
 * 1) Traverse the string from right to left one character at a time
 * 2) Maintain a variable, lastVisistedConsonant
 * 3) During traversal, when vowel is encountered, replace it with lastVisitedConsonant and add to result.
 * 4) During traversal, when consonant is encountered, update the lastVisitedConsonant and add to the result.
 */
public class ReplaceVowelWithNextConsonant {

    private static String replaceVowelWithNextConsonant(String input) {

        String result = "";
        char lastVisitedConsonant = '\0';

        for (int i = input.length() - 1; i >= 0; i--) {

            char x = input.charAt(i);

            if(isVowel(x)) {

                if (lastVisitedConsonant != '\0') {

                    result = String.valueOf(lastVisitedConsonant) + result;
                }
            } else {

                lastVisitedConsonant = x;
                result = String.valueOf(lastVisitedConsonant) + result;
            }
        }
        return result;
    }

    private static boolean isVowel(char x) {

        if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u' ||
                x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        String input = "abcdefg";
        System.out.println("Input: " + input + " Output: " + replaceVowelWithNextConsonant(input));

        input = "aaaabccceee";
        System.out.println("Input: " + input + " Output: " + replaceVowelWithNextConsonant(input));

        input = "aaaa";
        System.out.println("Input: " + input + " Output: " + replaceVowelWithNextConsonant(input));
    }
}
