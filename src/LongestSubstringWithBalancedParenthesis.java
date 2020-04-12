import java.util.Stack;

/**
 * Longest Substring With Balanced Parentheses
 * Given a string brackets that may only contain characters '(' and ')',
 * find the length of the longest substring that has balanced parentheses.
 *
 * A string is defined as having balanced parentheses if and only if it has
 * an equal number of '(' and ')' and its every prefix has at least as many '('s as ')'s.
 *
 * Return the length, not the substring.
 *
 * Example One
 * Input: "((((())(((()"
 * Output: 4
 * Because "(())" is the longest substring with balanced parentheses.
 *
 * Example Two
 * Input: "()()()"
 * Output: 6
 * The entire string "()()()" has parentheses balanced.
 *
 * Constraints:
 *     1 <= length of brackets <= 10^5
 *
 * ***********************************************************************
 *
 * Approach 1: Using stack
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * 1) The valid string starts from 0 index, so push -1 to stack
 * 2) For each opening bracket, push the index to the stack
 * 3) For closing bracket, pop from stack
 * 4) If stack becomes empty after popping then push the current index of closing
 * bracket to stack. The new valid string starts after this char
 * 5) Else calculate the length of valid string and update the max.
 *
 * **************************************************************************
 *
 * Approach 2: Count the number of opening and closing brackets
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * 1) Scan the string from left to right.
 * 2) Count the number of opening and closing brackets.
 * 3) If open == close, then update the max
 * 4) If close > open, reset open and close = 0
 *
 * 5) Scan the string from right to left.
 * 6) Count the number of opening and closing brackets.
 * 7) If open == close, then update the max
 * 8) If open > close, reset open and close = 0
 *
 * resources/LongestSubstringWithBalancedParenthesisStack.jpg
 * resources/LongestSubstringWithBalancedParenthesisCount.jpg
 *
 */
public class LongestSubstringWithBalancedParenthesis {

    private static int longestSubstringWithBalancedParenthesisStack(String brackets) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); //The valid string starts at index 0
        int maxLength = 0;

        for(int i = 0; i < brackets.length(); i++) {
            if(brackets.charAt(i) == '(') {
                stack.push(i);
            } else { //')'
                stack.pop();

                if(stack.isEmpty()) {
                    stack.push(i); //The new valid string starts after this char
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }

    private static int longestSubstringWithBalancedParenthesisCount(String brackets) {
        int maxLength = 0;
        int open = 0;
        int close = 0;

        //Left to right
        for(int i = 0; i < brackets.length(); i++) {
            if(brackets.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if(open == close) {
                maxLength = Math.max(maxLength, 2 * open);
            } else if(close > open) {
                open = close = 0;
            }
        }

        //Right to left
        open = close = 0;
        for(int i = brackets.length() - 1; i >= 0; i--) {
            if(brackets.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if(open == close) {
                maxLength = Math.max(maxLength, 2 * open);
            } else if (open > close) {
                open = close = 0;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String brackets = "((((())(((()";
        System.out.println("The longest substring with balanced parenthesis using stack " +
                longestSubstringWithBalancedParenthesisStack(brackets));
        System.out.println("The longest substring with balanced parenthesis using count " +
                longestSubstringWithBalancedParenthesisCount(brackets));

        brackets = "()()()";
        System.out.println("The longest substring with balanced parenthesis using stack " +
                longestSubstringWithBalancedParenthesisStack(brackets));
        System.out.println("The longest substring with balanced parenthesis using count " +
                longestSubstringWithBalancedParenthesisCount(brackets));
    }
}
