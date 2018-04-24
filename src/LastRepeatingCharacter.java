import java.util.HashMap;

/**
 * Find the last repeating character in the given string
 * String input = "horizon tutorials";
 * String output = "i";
 * String input = "algorithms";
 * String output = "No repeating character found.";
 */
public class LastRepeatingCharacter {

    private static Character lastRepeatingCharacter(String input) {
        Character result = null;

        if (input == null) {
            return result;
        } else {
            //Remove all spaces
            String inputAfterRemovingSpaces = input.replaceAll(" ", "");

            //Store the character and their corresponding count in map
            HashMap<Character, Integer> characterIntegerHashMap = new HashMap<Character, Integer>();

            for (int i = inputAfterRemovingSpaces.length()-1; i >= 0; i--) {
                Character character = inputAfterRemovingSpaces.charAt(i);

                if (characterIntegerHashMap.containsKey(character)) {
                    characterIntegerHashMap.put(character, characterIntegerHashMap.get(character)+1);
                } else {
                    characterIntegerHashMap.put(character, 1);
                }
            }

            for (int i = inputAfterRemovingSpaces.length()-1; i >= 0; i--) {
                Character character = inputAfterRemovingSpaces.charAt(i);

                if (characterIntegerHashMap.get(character) > 1) {
                    return character;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "horizon tutorials";
        String input1 = "algorithms";
        String input2 = null;

        Character output = lastRepeatingCharacter(input);

        if (output == null) {
            System.out.println("No last repeating character found.");
        } else {
            System.out.println("The last repeating character is " +output);
        }

        Character output1 = lastRepeatingCharacter(input1);

        if (output1 == null) {
            System.out.println("No last repeating character found.");
        } else {
            System.out.println("The last repeating character is " +output1);
        }

        Character output2 = lastRepeatingCharacter(input2);

        if (output2 == null) {
            System.out.println("No last repeating character found.");
        } else {
            System.out.println("The last repeating character is " +output2);
        }



    }
}
