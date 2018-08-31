/**
 * Print Numbers from 1 to N without using loop
 *
 * Given a number N, write an program to print from number 1 to N without using loop
 *
 * Example:
 *
 * N = 20;
 * Output: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
 *
 * Approach:
 *
 * Use Recursion
 *
 * 1) Make recursive call with N - 1
 * 2) In tail recursion, print the number
 */
public class PrintNumbersWithoutLoop {

    private static void printNumbersWithoutLoop(int number) {

        if (number <= 0) {
            return;
        }

        printNumbersWithoutLoop(number - 1);

        System.out.print(number + " ");
    }

    public static void main(String[] args) {

        int n = 20;
        printNumbersWithoutLoop(n);
    }
}
