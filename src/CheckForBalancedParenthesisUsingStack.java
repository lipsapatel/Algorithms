import java.util.Stack;

/**
 * Check for balanced parenthesis using stack.
 *
 * Opening symbol, push to stack
 * Closing symbol, top of stack same type - pop
 * else return false
 *
 * ended with empty stack, return true
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 */
public class CheckForBalancedParenthesisUsingStack {

    private static boolean isBalancedParenthesis(String input) {

        if (input == null || input.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {

            //If opening bracket, add to stack
            if (input.charAt(i) == '(' || input.charAt(i) == '{' || input.charAt(i) == '[') {
                stack.push(input.charAt(i));
            }
            //If closing bracket, check for matching
            if (input.charAt(i) == ')' && (stack.isEmpty() || stack.peek() != '(')) {
                return false;
            } else if (input.charAt(i) == '}' && (stack.isEmpty() || stack.peek() != '{')) {
                return false;
            } else if (input.charAt(i) == ']' && (stack.isEmpty() || stack.peek() != '[')) {
                return false;
            } else if (input.charAt(i) == '}' || input.charAt(i) == ')' || input.charAt(i) == ')') {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String input = "(A+B)";
        System.out.println("Balanced parenthesis for input " + input + " : " + isBalancedParenthesis(input));

        input = "{(A+B)+(C+D)}";
        System.out.println("Balanced parenthesis for input " + input + " : " + isBalancedParenthesis(input));

        input = "{(x+y)*(z)";
        System.out.println("Balanced parenthesis for input " + input + " : " + isBalancedParenthesis(input));

        input = "[2+3]+(A)]";
        System.out.println("Balanced parenthesis for input " + input + " : " + isBalancedParenthesis(input));

        input = "{a+z)";
        System.out.println("Balanced parenthesis for input " + input + " : " + isBalancedParenthesis(input));
    }
}
