/**
 * Reverse String Using Recursion
 * Given a string, write a recursive program to reverse it
 *
 * Example:
 *
 * Original String: tutorial horizon
 Reversed String: noziroh lairotut

 Approach

 1) Take first character out of the string, add it to the end of the result of the remaining string
 2) Make a recursive call to remaining string.
 */
public class ReverseStringUsingRecursion {

    private static String reverseStringUsingRecursion(String input) {

        if (input.isEmpty()) {
            return input;
        }

        return reverseStringUsingRecursion(input.substring(1)) + input.charAt(0);
    }

    public static void main(String[] args) {

        String input = "tutorial horizon";
        String reversedString = reverseStringUsingRecursion(input);

        System.out.println("Original String: " + input);
        System.out.println("Reversed String: " + reversedString);
    }
}
