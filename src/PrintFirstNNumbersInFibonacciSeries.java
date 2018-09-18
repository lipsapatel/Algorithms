/**
 * Print first n numbers in fibonacci series
 *
 * Write a program to print first n numbers in fibonacci series
 *
 * Fibonacci number: First two Fibonacci numbers are defined as 0 and 1 and every number after the first
 * tow is the sum of the two preceding ones.
 *
 * Fibonacci Sequence: 0 1 1 2 3 5 8 13 21 34 ...
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

    public static void main(String[] args) {
        int n = 10;
        printFirstNNumbers(n);
    }
}
