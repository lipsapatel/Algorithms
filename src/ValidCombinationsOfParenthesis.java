/**
 * Given n, generate all valid parenthesis of length 2n.
 *
 * n =2
 * Output:
 * ()()
 * (())
 *
 * Recursion is the key
 * Set openParenthesis = n and closeParenthesis = n
 *
 * Select openParenthesis and add it to the result string and reduce it's count by one
 * and make recursive call
 *
 * Select closeParenthesis and add it to the result string and reduce it's count by one
 * and make recursive call
 *
 * To print all valid parenthesis make sure the count of closeParenthesis is not less
 * than openParenthesis because if that's true then more closeParenthesis is printed than
 * openParenthesis so you need to return
 *
 * If count for openParenthesis and closeParenthesis becomes zero, then print
 *
 * images/ValidCombinationsOfParenthesis.png
 *
 */
public class ValidCombinationsOfParenthesis {

    private static void printValidCombinationsOfParenthesis(int openParenthesis,
                                                            int closeParenthesis, String result) {

        //All openParenthesis and closeParenthesis are in result string
        //So print the result string
        if (openParenthesis == 0 && closeParenthesis == 0) {
            System.out.println(result);
        }

        //This means more closeParenthesis are printed than openParenthesis so return
        if (openParenthesis > closeParenthesis) {
            return;
        }

        if (openParenthesis > 0) {
            printValidCombinationsOfParenthesis(openParenthesis - 1, closeParenthesis,
                    result + "(");
        }

        if (closeParenthesis > 0) {
            printValidCombinationsOfParenthesis(openParenthesis, closeParenthesis - 1,
                    result + ")");
        }
    }

    private static void printValidCombinationsOfParenthesis(int n) {
        printValidCombinationsOfParenthesis(n, n, "");
    }

    public static void main(String[] args) {
        System.out.println("The valid combinations for n = 1");
        printValidCombinationsOfParenthesis(1);

        System.out.println("The valid combinations for n = 2");
        printValidCombinationsOfParenthesis(2);

        System.out.println("The valid combinations for n = 3");
        printValidCombinationsOfParenthesis(3);
    }
}
