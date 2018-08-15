/**
 * Given a number N, write a program to print first N prime numbers
 *
 * What is prime number?
 *
 * A prime number (or a prime) is a natural number greater than 1 which is either divisible by 1 or by number itself.
 *
 * Example:
 *
 * N = 5
 * 2, 3, 5, 7, 11
 *
 * N = 10
 * 2, 3, 5, 7, 11, 13, 17, 19, 23, 29
 *
 * Approach:
 *
 * 1) Start from number 2 to till we find N prime numbers
 * 2) Check for each number if it is a prime number, if yes then increment the prime count
 *
 */
public class PrintFirstNPrimeNumbers {

    private static void printFirstNPrimeNumbers(int N) {

        int number = 2;
        int primeCount = 0;

        while (primeCount < N) {

            if (isPrimeBestSolution(number)) {

                System.out.print(number + " ");
                primeCount++;
            }

            number++;
        }
    }

    private static boolean isPrimeBestSolution(int number) {

        for (int i = 2; i <= Math.sqrt(number); i++) {

            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int N = 5;
        printFirstNPrimeNumbers(N);
        System.out.println();

        N = 10;
        printFirstNPrimeNumbers(N);
        System.out.println();

        N = 20;
        printFirstNPrimeNumbers(N);
        System.out.println();
    }
}
