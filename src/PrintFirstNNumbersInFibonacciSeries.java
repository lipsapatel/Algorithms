/**
 * Print first n numbers in fibonacci series
 *
 * Write a program to print first n numbers in fibonacci series
 *
 * IK.Recursion.PreClass.Fibonacci number: First two IK.Recursion.PreClass.Fibonacci numbers are defined as 0 and 1 and every number after the first
 * tow is the sum of the two preceding ones.
 *
 * IK.Recursion.PreClass.Fibonacci Sequence: 0 1 1 2 3 5 8 13 21 34 ...
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Using Mathematical formula - TC = O(1) SC = O(1)
 *
 * Fn = {[(√5 + 1)/2] ^ n} / √5
 */
public class PrintFirstNNumbersInFibonacciSeries {

    private static void printFirstNNumbers(int n) {

        int one = 0;
        int two = 1;

        System.out.println("First " + n + " elements in fibonacci series are: ");

        for (int i = 0; i < n; i++) {
            System.out.print(one + " ");
            int temp = one + two;
            one = two;
            two = temp;
        }
    }

    private static int fibonacci(int n) {
        int a = 0;
        int b = 1;
        int c;

        if (n == 0) {
            return a;
        }
        if (n == 1) {
            return b;
        }

        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    private static int fibonacciUsingFormula(int n) {
        double phi = (1 + Math.sqrt(5))/2;
        return (int)Math.round(Math.pow(phi, n)/Math.sqrt(5));
    }

    public static void main(String[] args) {
        int n = 10;
        printFirstNNumbers(n);
        System.out.println();
        System.out.println(fibonacci(9));

        System.out.println("Fibonnaci for 10 using formula: " + fibonacciUsingFormula(9));
    }
}
