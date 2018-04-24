import java.util.HashMap;

/**
 * Find the first non-repeating character
 * String input = "tutorial horizon";
 * String output = "u";
 * String input = "aabbccadd"
 * String output = "No non-repeating character found."
 */
public class FirstNonRepeatingCharacter {

    private static Character getFirstNonRepeatingCharacter(String input) {

        String inputAfterRemovingSpaces = input.replaceAll(" ", "");

        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<Character, Integer>();

        //Iterate the String and count the number of characters
        for (int i = 0; i < inputAfterRemovingSpaces.length(); i++) {
            Character character = inputAfterRemovingSpaces.charAt(i);

            if (characterIntegerHashMap.containsKey(character)) {
                characterIntegerHashMap.put(character, characterIntegerHashMap.get(character) + 1);
            } else {
                characterIntegerHashMap.put(character, 1);
            }
        }

        //Iterate the String from left to right and return character with count 1
        for (int i = 0; i < inputAfterRemovingSpaces.length(); i++) {
            Character character = inputAfterRemovingSpaces.charAt(i);

            if (characterIntegerHashMap.get(character) == 1) {
                return character;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String input = "tutorial horizon";
        String input1 = "aabbccadd";

        Character firstNonRepeatingCharacter = getFirstNonRepeatingCharacter(input);
        if (firstNonRepeatingCharacter == null) {
            System.out.println("No first non-repeating character found.");
        } else {
            System.out.println("The first non-repeating character is " + firstNonRepeatingCharacter);
        }

        Character firstNonRepeatingCharacter1 = getFirstNonRepeatingCharacter(input1);
        if (firstNonRepeatingCharacter1 == null) {
            System.out.println("No first non-repeating character found.");
        } else {
            System.out.println("The first non-repeating character is " + firstNonRepeatingCharacter1);
        }
    }
}
