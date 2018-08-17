/**
 * Remove vowels from string
 *
 * Given a string, remove all the vowels from the string.
 *
 * What are vowels?
 *
 * The letters A, E, I, O, U are called vowels.
 * The other letters in the alphabet are called consonants.
 *
 * Example:
 *
 * Input String: algorithms @ tutorial horizon
 * Updated String: lgrthms @ ttrl hrzn
 *
 * Approach
 *
 * 1) Iterate through all the characters, if you find the vowel, skip it else add it to the result
 *
 */
public class RemoveVowelsFromString {

    private static void removeVowelsFromString(String input) {

        StringBuffer result = new StringBuffer();

        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            char chr = chars[i];

            if (!isVowel(chr)) {

                result.append(chr);
            }
        }

        System.out.println("Updated String after removing vowels: " + result);
    }

    private static boolean isVowel(char chr) {

        String vowels = "AEIOUaeiou";

        if (vowels.contains(Character.toString(chr))) {

            return true;

        } else {

            return false;
        }
    }

    public static void main(String[] args) {

        String input = "algorithms @ tutorial horizon";
        System.out.println("Input String: " + input);

        removeVowelsFromString(input);
    }
}
