import java.util.HashMap;

/**
 * First repeating character
 * String input = "horizon tutorials";
 * String output = "o";
 * String input = "algorithms"
 * String output = "No repeating character found"
 */
public class FirstRepeatingCharacter {

    private static Character getFirstRepeatingCharacter(String input) {

        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<Character, Integer>();

        //Remove whitespaces
        String inputAfterRemovingSpaces = input.replaceAll(" ","");

        //Iterate the string and create hashmap
        for (int i = 0; i < inputAfterRemovingSpaces.length(); i++) {
            Character character = inputAfterRemovingSpaces.charAt(i);

            if (characterIntegerHashMap.containsKey(character)) {
                characterIntegerHashMap.put(character, characterIntegerHashMap.get(character)+1);
            } else {
                characterIntegerHashMap.put(character,1);
            }
        }

        //Iterate the string and find character with count > 1
        for (int i = 0; i < inputAfterRemovingSpaces.length(); i++) {
            Character character = inputAfterRemovingSpaces.charAt(i);

            if (characterIntegerHashMap.get(character) > 1) {
                return character;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String input = "horizon tutorials";

        Character character = getFirstRepeatingCharacter(input);
        if (character == null) {
            System.out.println("No first repeating character found.");
        } else {
            System.out.println("The first repeating character is " + character);
        }

        String input1 = "algorithms";

        Character character1 = getFirstRepeatingCharacter(input1);
        if (character1 == null) {
            System.out.println("No first repeating character found.");
        } else {
            System.out.println("The first repeating character is " +character1);
        }

    }
}
