/**
 * Check if given number is Prime.
 *
 * Given a number, write a program to check if the number is prime or not.
 *
 * A number is called a prime number when number is divisible by 1 or by number itself.
 * 1 is neither prime or composite number.
 *
 * Naive Solution:
 *
 * 1) Iterate through 2 to n - 1
 * 2) Check if the given number is divisible by any number between 2 to n - 1.
 * 3) If yes then the number is not prime
 * 4) Else the number is prime
 * 5) Time Complexity: O(N)
 *
 * Better Solution:
 *
 * 1) If you look closely, we don't have to check all the numbers from 2 to n - 1
 * 2) If we check numbers from 2 to n/2, we get our answer since any number greater than n/2 cannot divide n.
 * 3) Time Complexity: O(N/2) ~ O(N)
 *
 * Best Solution:
 *
 * 1) sqrt(N)
 * 2) Let's say number n is not a prime number, that means there are two numbers which products to n
 *    p * q = n
 * 3) Means in (p, q) one of the two number will be greater than equal to sqrt(n) and one of them is less than equal to sqrt(n)
 *
 * Example:
 *
 * N = 64, sqrt(N) = 8
 *
 * p*q = 64
 *
 * 2 * 32 = 64
 * 4 * 16 = 64
 * 8 * 8 = 64
 * 16 * 4 = 64
 * 32 * 2 = 64
 *
 * So observe closely unique pairs
 * (2, 32)
 * (4, 16)
 * (8, 8)
 *
 * After that p and q exchange values. So it makes sense to validate from 2 to 8 after that it will be repetitive.
 *
 * Time Complexity: O(sqrt(N))
 *
 */
public class CheckIfNumberIsPrime {

    private static void isPrimeNaiveApproach(int number) {

        System.out.println("O(N) solution: ");

        boolean isPrime = true;

        for (int i = 2; i < number; i++) {

            if (number % i == 0) {

                System.out.println("Number " + number + " is not a prime number");
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            System.out.println("Number " + number + " is a prime number");
        }
    }

    private static void isPrimeBetterApproach(int number) {

        System.out.println("O(N/2) Solution: ");

        boolean isPrime = true;

        for (int i = 2; i <= number/2; i++) {

            if (number % i == 0) {

                System.out.println("Number " + number + " is not a prime number");
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            System.out.println("Number " + number + " is a prime number");
        }
    }

    public static void isPrimeBestMethod(int number) {

        System.out.println("O(sqrt(N)) Solution: ");

        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(number); i++) {

            if (number % i == 0) {

                System.out.println("Number " + number + " is not a prime number");
                isPrime = false;
                break;
            }
        }

        if (isPrime) {

            System.out.println("Number " + number + " is a prime number");
        }
    }

    public static void main(String[] args) {

        int number = 13;
        isPrimeNaiveApproach(number);
        isPrimeBetterApproach(number);
        isPrimeBestMethod(number);

        number = 15;
        isPrimeNaiveApproach(number);
        isPrimeBetterApproach(number);
        isPrimeBestMethod(number);
    }
}
