import java.util.Stack;

/**
 * Evaluate prefix expression using stack
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * - + * 2 3 * 5 4 9 = 17
 */
public class EvaluatePrefixExpressionUsingStack {

    private static int evaluatePrefixExpressionUsingStack(String exp) {
        Stack<Integer> stack = new Stack<>();

        //Travel from right to left
        for (int i = exp.length() - 1; i >= 0; i--) {

            //Continue if delimiter
            if (exp.charAt(i) == ' ' || exp.charAt(i) == ',') {
            } else if (isOperator(exp.charAt(i))) { //Perform operation

                int op1 = stack.pop();
                int op2 = stack.pop();
                int result = performOperation(exp.charAt(i), op1, op2);
                stack.push(result);

            } else if (isNumericDigit(exp.charAt(i))) { //push to stack
                int operand = 0;
                int expo = 0;

                while (i >= 0 && isNumericDigit(exp.charAt(i))) {
                    operand = operand + (Character.getNumericValue(exp.charAt(i)) * (int)Math.pow(10,expo));
                    i--;
                    expo++;
                }

                //Increment i, because it's decremented in while
                i++;
                stack.push(operand);
            }
        }
        return stack.peek();
    }

    private static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c =='*' || c == '/');
    }

    private static boolean isNumericDigit(char c) {
        return(c >= '0' && c <= '9');
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
        String input = "- + * 2 3 * 5 4 9";
        System.out.println(evaluatePrefixExpressionUsingStack(input));
    }
}
