import java.util.ArrayList;
import java.util.List;

/**
 * Find if any anagram of string is in dictionary.
 * For a given string, find all the permutations of string
 * If any of the permutation is in given dictionary, return true.
 */
public class FindAnagramInDictionary {

    private static boolean findAnagram(String soFar, String rest, List<String> dict) {

        if (rest.isEmpty()) {

            if (dict.contains(soFar)) {
                return true;
            }
        }

        for (int i = 0; i < rest.length(); i++) {
            String next = soFar + rest.charAt(i);
            String remaining = rest.substring(0, i) + rest.substring(i + 1);

            if (findAnagram(next, remaining, dict)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        String input = "ABC";

        List<String> anagramDictionary = new ArrayList<>();
        anagramDictionary.add("BCA");
        anagramDictionary.add("XYZ");

        if (findAnagram("", input, anagramDictionary)) {
            System.out.println("Found anagram in dictionary for input " + input);
        } else {
            System.out.println("Anagram not found in dictionary for input " + input);
        }
    }
}
