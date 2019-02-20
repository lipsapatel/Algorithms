import java.util.Stack;

/**
 * Evaluate Infix Expression with brackets, +, -, *, /, $ and more than one digit number.
 *
 * Use 2 stack: one for operators and brackets, another for operand
 *
 * Time Complexity: O(n)
 * Space Complexity: O(2n)
 *
 * For Example: ((2+20)*2-3)*4 = 164
 */
public class EvaluateInfixExpression {

    private static int evaluateInfixExpression(String exp) {

        Stack<Character> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            //If it's a delimiter continue
            if (exp.charAt(i) == ' ' || exp.charAt(i) == ',') {
            } else if (isOperator(exp.charAt(i))) { //If Operator, push to stack if higher precedence else pop

                while(!stack1.isEmpty() && stack1.peek() != '(' && hasHigherPrecedence(stack1.peek(), exp.charAt(i))) {
                    int op2 = stack2.pop();
                    int op1 = stack2.pop();
                    int result = performOperation(stack1.pop(), op1, op2);
                    stack2.push(result);
                }

                //Push to stack if higher precedence
                stack1.push(exp.charAt(i));
            } else if (exp.charAt(i) == '(') { //If opening bracket push to stack1
                stack1.push(exp.charAt(i));
            } else if (isNumericDigit(exp.charAt(i))) { //If operand, push to stack2
                int operand = 0;

                while(i < exp.length() && isNumericDigit(exp.charAt(i))) {
                    operand = (operand * 10) + Character.getNumericValue(exp.charAt(i));
                    i++;
                }
                //Already incremented in while so decrement
                i--;
                stack2.push(operand);
            } else if (exp.charAt(i) == ')') { //If it's closing bracket, pop till opening bracket, perform operation and push result to stack2
                while(!stack1.isEmpty() && stack1.peek() != '(') {
                    char operator = stack1.pop();
                    int op2 = stack2.pop();
                    int op1 = stack2.pop();

                    int result = performOperation(operator, op1, op2);
                    stack2.push(result);
                }
                stack1.pop(); //'('
            }
        }
        while(!stack1.isEmpty()) {
            char operator = stack1.pop();
            int op2 = stack2.pop();
            int op1 = stack2.pop();

            int result = performOperation(operator, op2, op1);
            stack2.push(result);
        }
        return stack2.peek();
    }

    private static boolean isOperator(char c) {
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '$') {
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

        switch(op) {
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
            System.out.println("Unexpected Error");
            return -1;
        }
    }

    private static boolean isNumericDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String expression = "((2+20)*2-3)*4";
        System.out.println(evaluateInfixExpression(expression));
    }
}
