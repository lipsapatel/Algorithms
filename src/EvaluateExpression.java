/**
 * Evaluate math expression represented as String
 *
 * Assumptions:
 *
 * 1) Single digit integer
 * 2) No bogus string
 * 3) It will have single digit integer, parenthesis and + and * operators
 * 4) Expression will be in parenthesis
 *
 * evaluateExpression("7") = 7
 * evaluateExpression("(2+2)") = 4
 * evaluateExpression("(1+(2*4))") = 9
 * evaluateExpression("((1+3)+((1+2)*5))") = 19
 */
public class EvaluateExpression {
    private static int index = 0;

    private static int evaluateExpression(String exp) {
        //Base Case
        if (Character.isDigit(exp.charAt(index))) {
            return Character.getNumericValue(exp.charAt(index++));
        } else { //if(exp.charAt(index) == '(') not needed since there will be no bogus string
            //Considering "(2+2)"
            //Assuming there's no bogus string
            index++; //skip '('

            int op1 = evaluateExpression(exp);
            char op = exp.charAt(index++);
            int op2 = evaluateExpression(exp);

            index++; //skip ')'

            if (op == '+') {
                return op1 + op2;
            } else {
                return op1 * op2;
            }
        }
    }

    private static void resetIndex() {
        index = 0;
    }

    public static void main(String[] args) {
        System.out.println("7" + " = " + evaluateExpression("7"));
        resetIndex();
        System.out.println("(2+2)" + " = " + evaluateExpression("(2+2)"));
        resetIndex();
        System.out.println("(1+(2*4))" + " = " + evaluateExpression("(1+(2*4))"));
        resetIndex();
        System.out.println("((1+3)+((1+2)*5))" + " = " + evaluateExpression("((1+3)+((1+2)*5))"));
    }
}
