import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * resources/CatalanNumber.png
 *
 * *********************************************************************************************
 *
 * Find All Well Formed Brackets
 Problem Statement:
 Given a positive integer n, find ALL well formed round brackets string of length 2*n.
 The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution. After that, you can write a DP solution if you want.

 Input/Output Format For The Function:
 Input Format:
 There is only one argument denoting integer n.

 Output Format:
 Return array of strings res, containing all possible well formed round brackets string. (Length of each string will be 2*n).

 You need not to worry about the order of strings in res.
 E.g. For n = 2, ["(())", "()()"] or ["()()", "(())"], both will be accepted.

 Input/Output Format For The Custom Input:
 Input Format:
 There should be one line for input, containing an integer n.

 If n = 3, then input should be:
 3

 Output Format:
 Let’s denote the size of res is m, where res is the resultant array of string returned by the solution function.
 Then, there will be m lines of output, where ith line contains res[i], denoting string at index i of res.

 For input n = 3, output will be:
 ((()))
 (()())
 (())()
 ()(())
 ()()()

 Constraints:
 1 <= n <= 13
 Only use round brackets. '(' and ')'.

 Sample Test Case:
 Sample Input:
 3

 Sample Output:
 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]

 (This is one of the possible outputs. Array containing these five string in any order, will be accepted.)
 *******************************************************************************************************
 *
 * There are many possible solutions for this problem.
 Have a look at the solution provided by us.

 TC = O(2 ^ (2n))
 SC = O(2n) - Just call stack space

 Here We are eliminating when it goes invalid we are not exploring that path
Time Complexity:
 O(2n * catalan number(n)).

 Auxiliary Space Used:
 O(2n * catalan number(n)). //To store output

 Space Complexity:
 O(2n * catalan number(n)).
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

    //TC = O(2 ^ n) = (degree) ^ height loose upper bound
    //SC = O(n) Recursive call stack but we could also have O(2^n) possible outcomes in worst case
    private static String[] find_all_well_formed_brackets(int n) {

        List<String> result = new ArrayList<String>();

        findAllWellFormedBrackets(n, 0, 0, result, new StringBuilder());
        return result.toArray(new String[0]);
    }

    private static void findAllWellFormedBrackets(int n, int open, int close, List<String> result, StringBuilder soFar) {

        //Base Case
        if (open == n && close == n) {
            result.add(soFar.toString());
            return;
        }

        //If open or close is done - Guard condition pruning the recursion tree
        if (open > n || close > n) {
            return;
        }

        //If close > open - Guard condition pruning the recursion tree
        if (close > open) {
            return;
        }

        //(
        findAllWellFormedBrackets(n, open + 1, close, result, soFar.append('('));
        soFar.deleteCharAt(soFar.length() - 1);

        //)
        findAllWellFormedBrackets(n, open, close + 1, result, soFar.append(')'));
        soFar.deleteCharAt(soFar.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println("The valid combinations for n = 1");
        printValidCombinationsOfParenthesis(1);

        System.out.println("The valid combinations for n = 2");
        printValidCombinationsOfParenthesis(2);

        System.out.println("The valid combinations for n = 3");
        printValidCombinationsOfParenthesis(3);

        System.out.println("The valid combination for n = 3 ");
        System.out.println(Arrays.toString(find_all_well_formed_brackets(3)));

        System.out.println("The valid combination for n = 4");
        System.out.println(Arrays.toString(find_all_well_formed_brackets(4)));
    }
}
