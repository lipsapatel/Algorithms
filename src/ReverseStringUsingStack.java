import java.util.Stack;

/**
 * Reverse the given String using Stack
 *
 * Given a string, write a java program to reverse the string using Stack
 *
 * Example:
 *
 * Input: "Lipsa Patel"
 * Output: "letap aspil"
 *
 * Approach:
 *
 * 1) Initialize a StringBuffer, this will be our output
 * 2) Initialize Stack
 * 3) Traverse through String, one character at a time and keep adding it to Stack
 * 4) While stack is not empty, keep popping out the characters from stack and add it to the output StringBuffer
 * 5) Print the output
 */
public class ReverseStringUsingStack {

    private static void reverseStringUsingStack(String input) {

        StringBuffer output = new StringBuffer();

        if (input == null || input.isEmpty()) {
            return;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {

            stack.add(input.charAt(i));
        }

        //Traverse through stack, pop the characters and add it to StringBuffer
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        System.out.println("Original String: " + input);
        System.out.println("Reverse String: " + output);
    }

    public static void main(String[] args) {

        String input = "Lipsa Patel";
        reverseStringUsingStack(input);
    }
}
