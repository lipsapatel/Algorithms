import java.util.Stack;

/**
 * Valid Expression
 * You have to check whether a given string is a valid mathematical expression
 * or not. A string is considered valid if it contains matching opening and
 * closing parenthesis as well as valid mathematical operations.
 * The string contains the following set of parentheses ‘(‘, ’)’, ’[’, ’]’, ’{’, ’}’
 * , numbers from 0 to 9 and following operators ‘+’, ’-’ and ‘*’.
 * An expression containing only parentheses is considered valid if it contains
 * correct opening and closing parentheses. Example: “{()}” is considered valid.
 *
 * Input: {(1+2)*3}+4
 * Output: True
 *
 * We can clearly see that the above expression is a valid mathematical expression,
 * as well as the parentheses, are valid.
 *
 * Input: ((1+2)*3*)
 * Output: False
 *
 * Here the parentheses are valid but the mathematical expression is not valid.
 * There is an operator ‘*’ without any operand after it.
 *
 * Constraints:
 *
 *     1 <= length(expression) <= 100000
 *     Characters in expression string can be from ‘+’, ‘-’, ‘*’ and [0-9].
 *******************************************************************************
 *
 * Approach:
 *
 * 1) Take 2 stacks one for operands/digit and another for brackets and operators.
 * 2) If we get digits push to stack1.
 * 3) If we get operator or opening bracket, push to stack2.
 * 4) If we get closing bracket, remove from stack2 until stack2 is empty
 * or we find matching opening bracket in which case break.
 * 5) For any operator, make sure size of stack1 is > 1 and pop one.
 * In case we are not able to perform any such operation return false.
 * 6) At the end keep popping from stack2, if it's not operator return.
 * 7) For operator, make sure stack1 has atleast 2 digits and pop one.
 * 8) At the end, stack1 size should be 0 or 1 and stack2 should be empty.
 * 9) If it's not the above case return false else return true.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) - 2 stacks
 *
 * resources/ValidExpression1.jpg
 * resources/ValidExpression2.jpg
 * resources/ValidExpression3.jpg
 *
 */
public class ValidExpression {

    public static boolean isValid(String expression) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        boolean gotDigit = false; //This is for two continuous digit, there
        //has to be operator after digit 23+ or 3(2)+

        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if(isDigit(c)) {
                stack1.push(Character.getNumericValue(c));
                if(gotDigit) {
                    return false;
                } else {
                    gotDigit = true;
                }
            } else if(isOperator(c)) {
                stack2.push(c);
                gotDigit = false; //got operator after digit, reset it.
            } else if(isOpenBracket(c)) {
                stack2.push(c);
            } else if(isCloseBracket(c)) {
                //Pop from stack2 till you get matching open bracket
                //For everything else, make sure stack1 has atleast 2 operand
                //Pop one operand from stack1

                boolean valid = false; //This is for when it comes out of
                //While loop.
                //Two cases when it comes out of while loop
                //1) When found matching open bracket in which case this should be true
                //2) When stack2 is empty in which case it should be false

                while(!stack2.isEmpty()) {
                    char popChar = stack2.pop();

                    if(popChar == getCorrespondingOpenBracket(c)) {
                        valid = true;
                        break;
                    } else {
                        if(stack1.size() < 2) {
                            return false;
                        } else {
                            stack1.pop();
                        }
                    }
                }

                //When stack2 goes empty it comes out of while loop
                //or when encounter open bracket, it comes out of while loop
                if(!valid) {
                    return false;
                }
            } else { //Invalid char
                return false;
            }
        }

        while(!stack2.isEmpty()) {
            char popChar = stack2.pop();

            if(isOperator(popChar)) {
                if(stack1.size() < 2) {
                    return false;
                } else {
                    stack1.pop();
                }
            } else { //Not operator
                return false;
            }
        }

        if(stack1.size() > 1 || !stack2.isEmpty()) {
            return false;
        }
        return true;
    }

    private static char getCorrespondingOpenBracket(char c) {
        if(c == ')') {
            return '(';
        } else if (c == '}') {
            return '{';
        } else {
            return '[';
        }
    }

    private static boolean isCloseBracket(char c) {
        if(c == ')' || c == ']' || c == '}') {
            return true;
        }
        return false;
    }

    private static boolean isOpenBracket(char c) {
        if(c == '(' || c == '{' || c == '[') {
            return true;
        }
        return false;
    }

    private static boolean isDigit(char c) {
        if(c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    private static boolean isOperator(char c) {
        if(c == '+' || c == '-' || c == '*') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String expression = "{(1+2)*3}+4";
        System.out.println(isValid(expression));

        expression = "((1+2)*3*)";
        System.out.println(isValid(expression));

        expression = "(){}";
        System.out.println(isValid(expression));

        expression = "23+";
        System.out.println(isValid(expression));

        expression = "3(2)+";
        System.out.println(isValid(expression));
    }
}
