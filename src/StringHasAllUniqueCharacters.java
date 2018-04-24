import java.util.Arrays;

/**
 * Write an algorithm to find out whether the given string contains all the unique characters.
 *
 * Input: String
 * Output: True or false based on whether the string contains unique characters or not.
 *
 * Approach
 *
 * Method 1:
 *
 * When characters are not ASCII but could be alphabets or special characters.
 *
 * 1) Create boolean array of size 256, and have false at every index.
 * 2) Navigate input string one character at a time, example char a
 * 3) If array[a] is false, change it to true
 * 4) If array[a] is true, then return false.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Method 2:
 *
 * 1) Sort the array
 * 2) Do linear scan to find out whether string contain unique characters or not
 *
 * Time Complexity: O(nlogn)
 * Space Complexity: O(n)
 *
 *
 */
public class StringHasAllUniqueCharacters {

    private static boolean doesStringHasUniqueCharacterByBooleanArray(String inputString) {

        Boolean[] booleanArray = new Boolean[256];

        //Initialize it with all false
        for (int i = 0; i < 256; i++) {
            booleanArray[i] = false;
        }

        for (int i = 0; i < inputString.length(); i++) {

            Character character = inputString.charAt(i);

            if (booleanArray[character] == true) {
                //The string don't have all unique characters
                return false;
            } else {
                booleanArray[character] = true;
            }
        }

        //String has all unique characters
        return true;
    }

    private static boolean doesStringHasUniqueCharacterByArraySort(String inputString) {

        char[] characterArray = inputString.toCharArray();
        Arrays.sort(characterArray);

        for (int i = 1; i < characterArray.length; i++) {

            if (characterArray[i - 1] == characterArray[i]) {
                return false;
            }
        }
        //String has all unique characters
        return true;
    }

    public static void main(String[] args) {

        String inputString = "Lipsa_Patel";

        System.out.println("Method 1: Does string has all unique characters using boolean array " + doesStringHasUniqueCharacterByBooleanArray(inputString));
        System.out.println("Method 1: Does string has all unique characters using array sort " + doesStringHasUniqueCharacterByArraySort(inputString));

        System.out.println();
        String inputString1 = "Lipsa";

        System.out.println("Method 1: Does string has all unique characters using boolean array " + doesStringHasUniqueCharacterByBooleanArray(inputString1));
        System.out.println("Method 1: Does string has all unique characters using array sort " + doesStringHasUniqueCharacterByArraySort(inputString1));
    }
}
