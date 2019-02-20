import java.util.Stack;

/**
 * Convert infix expression to postfix expression
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Take one stack which stores operators, brackets
 * Only higher precedence operators will be pushed to stack
 * We are assuming that operand will be single character
 */
public class ConvertInfixToPostfix {

    private static String convertInfixToPostfix(String exp) {

        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < exp.length(); i++) {

            //If delimiter, continue
            if (exp.charAt(i) == ' ' || exp.charAt(i) == ',') {

            } else if (isOperator(exp.charAt(i))) { //Operator, push to stack if higher precedence else pop

                while (!stack.isEmpty() && stack.peek() != '(' && hasHigherPrecedence(stack.peek(), exp.charAt(i))) {
                    postfix = postfix.append(stack.peek());
                    stack.pop();
                }
                stack.push(exp.charAt(i));
            } else if (exp.charAt(i) == '(') { //If it's opening bracket, push to stack
                stack.push(exp.charAt(i));
            } else if (isOperand(exp.charAt(i))) { //If operand, append to string
                postfix = postfix.append(exp.charAt(i));
            } else if (exp.charAt(i) == ')') { //If it's closing bracket, pop till opening bracket
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix = postfix.append(stack.peek());
                    stack.pop();
                }
                stack.pop(); //for '('
            }
        }

        while(!stack.isEmpty()) {
            postfix = postfix.append(stack.peek());
            stack.pop();
        }
        return postfix.toString();
    }

    private static boolean isOperand(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

    private static boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '$') {
            return true;
        }
        return false;
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        int op1Weight = getOperatorWeight(op1);
        int op2Weight = getOperatorWeight(op2);

        //If operators, have equal precedence, return true for left associative
        //return false if right associative
        if (op1Weight == op2Weight) {
            if (isRightAssociative(op1)) {
                return false;
            }
            return true;
        }

        return op1Weight > op2Weight;
    }

    private static int getOperatorWeight(char op) {
        int weight = -1;

        switch (op) {
            case '+':
            case '-':
                weight = 1;
                break;
            case '*':
            case '/':
                weight = 2;
                break;
            case '$':
                weight = 3;
                break;
        }
        return weight;
    }

    private static boolean isRightAssociative(char op) {
        if (op == '$') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String infixExpression = "((A+B)*C-D)*E";

        System.out.println("Postfix Expression: " + convertInfixToPostfix(infixExpression));
    }
}
