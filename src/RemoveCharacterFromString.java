/**
 * Write a method to remove given character from the String.
 *
 * We can use replaceAll method to replace all the occurrence of a String with another String.
 *
 */
public class RemoveCharacterFromString {

    private static String removeCharacter(String inputString, char character) {

        if (inputString == null) {
            return null;
        }

        return inputString.replaceAll(Character.toString(character), "");
    }

    public static void main(String[] args) {

        String inputString = "lipsapatel";

        System.out.println("After removing character a: " + removeCharacter(inputString, 'a'));
    }
}
