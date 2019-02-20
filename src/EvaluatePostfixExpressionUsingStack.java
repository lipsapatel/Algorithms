import java.util.Stack;

/**
 * Evaluate postfix expression using stack
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * 23*54*+9- = 17
 *
 * ASCII for char '0' = 48
 */
public class EvaluatePostfixExpressionUsingStack {

    private static int evaluatePostfixExpressionUsingStack(String exp) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {

            //Continue if delimiter
            if (exp.charAt(i) == ' ' || exp.charAt(i) == ',') {
                continue;
            } else if (isOperator(exp.charAt(i))) { //perform operation

                int op2 = stack.pop();
                int op1 = stack.pop();
                int result = performOperation(exp.charAt(i), op1, op2);
                stack.push(result);

            } else if(isNumericDigit(exp.charAt(i))) { //push to stack
                int operand = 0;

                //For number more than one digit
                while (i < exp.length() && isNumericDigit(exp.charAt(i))) {
                    operand = (operand * 10) + Character.getNumericValue(exp.charAt(i));
                    i++;
                }
                //Decrement i because it's incremented in while
                i--;
                stack.push(operand);
            }
        }
        return stack.peek();
    }

    private static boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
        return false;
    }

    private static boolean isNumericDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    private static int performOperation(char operation, int op1, int op2) {
        if (operation == '+') {
            return op1 + op2;
        } else if (operation == '-') {
            return op1 - op2;
        } else if (operation == '*') {
            return op1 * op2;
        } else if (operation == '/') {
            return op1/op2;
        } else {
            System.out.println("Unexpected error");
            return -1;
        }
    }

    public static void main(String[] args) {
        String exp = "2 3 * 5 4 * + 9 -";
        System.out.println(evaluatePostfixExpressionUsingStack(exp));
    }
}
