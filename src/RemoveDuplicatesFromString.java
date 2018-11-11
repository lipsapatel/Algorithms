import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Remove Duplicates from String
 *
 * Given a string, write an algorithm to remove the duplicate characters in that string.
 *
 * Example:
 * Input: tutorialhorizon
 * Output: tuorialhzn
 *
 * Approaches:
 *
 * There are multiple approaches to solve this problem
 *
 * 1) Sorting - (order changes, O(nlogn))
 * 2) HashSet - (order changes, o(n))
 * 3) Linked HashMap - (order not changed, O(n))
 * 4) Buffer Array - boolean ASCII array - (order not changes, O(n))
 *
 * Sorting
 *
 * Time Complexity: O(nlogn)
 *
 * 1) Convert string --> char Array
 * 2) Sort array
 * 3) Iterate and remove duplicates
 * 4) This will change the order of characters
 *
 * Hash Set
 *
 * Time Complexity: O(n)
 *
 * 1) Convert string --> char Array
 * 2) Store all the characters in the Set. This will remove duplicates
 * 3) Iterate through set and create string.
 * 4) This will change the order of characters.
 *
 * Linked HashSet
 *
 * Time Complexity: O(n)
 * Ordering maintained
 *
 * 1) Convert string --> char Array
 * 2) Store all characters in Linked HashSet which will remove duplicates
 * 3) Iterate through set and create result string.
 * 4) This approach will retain the order of characters
 *
 * Boolean ASCII Array - Buffer Array
 *
 * Time Complexity: O(n)
 * Order maintained
 *
 * 1) Convert string --> char Array
 * 2) Create boolean array of 256 (for all 0 - 255 ASCII characters)
 * 3) Iterate through the character array and set boolean array index to true. Ignore if that index is already true
 * 4) Create result string when you set boolean array to true.
 * 5) This will retain the order of characters.
 */
public class RemoveDuplicatesFromString {

    private static String removeDuplicatesUsingSort(String s) {
        char[] chars = s.toCharArray();

        Arrays.sort(chars);
        String result = new String();

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                result += chars[i];
            }
        }

        //add the first character
        if (chars[0] != chars[1]) {
            result = chars[0] + result;
        }
        return result;
    }

    private static String removeDuplicatesUsingHashSet(String s) {

        HashSet<Character> set = new HashSet<Character>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
        }

        String result = new String();

        for (char c: set) {
            result += c;
        }
        return result;
    }

    private static String removeDuplicatesUsingLinkedHashSet(String s) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
        }

        String result = new String();

        for (char c: set) {
            result += c;
        }
        return result;
    }

    private static String removeDuplicatesUsingBooleanASCIIArray(String s) {
        char[] chars = s.toCharArray();
        boolean[] asciiChrSet = new boolean[256];

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (asciiChrSet[chars[i]]) {
                continue;
            }
            asciiChrSet[chars[i]] = true;
            result.append(chars[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {

        String s = "tutorialhorizon";
        System.out.println(removeDuplicatesUsingSort(s));
        System.out.println(removeDuplicatesUsingHashSet(s));
        System.out.println(removeDuplicatesUsingLinkedHashSet(s));
        System.out.println(removeDuplicatesUsingBooleanASCIIArray(s));
    }
}
