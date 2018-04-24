import java.util.HashMap;

/**
 * Find the last non repeating charater in a given String
 * String input = "algorithm tutorial" --> "u"
 * String input = "aabbccdd" --> No repeating character found
 */
public class LastNonRepeatingCharacter {

    private static Character findNonRepeatingCharacter(String input) {

        //Remove any spaces in string
        String inputString = input.replaceAll(" ", "");

        //Store the character and count in HashMap
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<Character, Integer>();

        //Iterate String
        for (int i = 0; i < inputString.length(); i++) {
            Character character = inputString.charAt(i);

            if (characterIntegerHashMap.containsKey(character)) {
                characterIntegerHashMap.put(character, characterIntegerHashMap.get(character) + 1);
            } else {
                characterIntegerHashMap.put(character, 1);
            }
        }

        //Iterate String from right to left and find character with count 1
        for (int i = inputString.length() - 1; i >= 0; i--) {
            Character character = inputString.charAt(i);

            if (characterIntegerHashMap.get(character) == 1) {
                return character;
            }
        }
        return null;
    }

    public static void main(String args[]) {
        String input = "algorithm tutorial";
        //String input = "aabbccdd";

        Character result = findNonRepeatingCharacter(input);

        if (result == null) {
            System.out.println("No non repeating character found");
        } else {
            System.out.println("The last non repeating character is " + result);
        }

    }
}
