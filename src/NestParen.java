/**
 * Given a string, return true if it is a nesting of zero or more pairs of parenthesis,
 * like "(())" or "((()))". Suggestion: check the first and last chars, and then recur on what's inside them.


 nestParen("(())") → true
 nestParen("((()))") → true
 nestParen("(((x))") → false
 */
public class NestParen {

    private static boolean nestParen(String str) {
        //Base Case
        if (str.isEmpty()) {
            return true;
        } else { //Recursive Case
            if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') {
                return nestParen(str.substring(1, str.length() - 1));
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("String (()) contains nested parenthesis: " + nestParen("(())"));
        System.out.println("String ((())) contains nested parenthesis: " + nestParen("((()))"));
        System.out.println("String (((x)) contains nested parenthesis: " + nestParen("(((x))"));
    }
}
