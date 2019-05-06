/**
 * Find Factorial of a given Number
 *
 * Given a number, write a program to find factorial of that number
 *
 * What is Factorial of Number?
 *
 * In mathematics, the factorial of a non negative integer n denoted by n! is the product of all positive integers
 * less than or equal to n.
 * The value of 0! is 1, according to the convention for an empty product
 *
 * N! = n * (n - 1) * (n - 2) * ...2 * 1
 *
 * Example:
 *
 * 5! = 5 x 4 x 3 x 2x 1 = 120
 7! = 7 x 6 x 5 x 4 x 3 x 2x 1 = 5040

 Approach:

 1) If number is 0, return 1
 2) Make a recursive all to get the result of number - 1
 3) Multiply number with the result of number - 1
 */
public class FindFactorial {

    private static int findFactorial(int n) {

        if (n == 0) {
            return 1;
        }

        return n * findFactorial(n - 1);
    }

    public static void main(String[] args) {
        int n = 23;
        int factorial = findFactorial(n);

        System.out.println("Factorial of a number: " + n + " is: " + factorial);
    }
}
