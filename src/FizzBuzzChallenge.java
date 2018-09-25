/**
 * Fizz Buzz Challenge
 *
 * Write a program that prints the numbers from 1 to 100. But for multiples of three, print "Fizz" instead of the number
 * and for multiples of five print "Buzz". For numbers which are multiples of both three and five print "Fizz Buzz"
 *
 * Output:
 * 1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14,
 Fizz Buzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz,
 *
 * Approach
 *
 * 1) Iterate from 1 to 100
 * 2) If number is multiple of 3, print "Fizz"
 * 3) If number is multiple of 5, print "Buzz"
 * 4) If number is multiple of 3 and 5, print "Fizz Buzz"
 * 5) If none of the above is true, print the number itself.
 */
public class FizzBuzzChallenge {

    private static void fizzBuzzChallenge() {

        for (int i = 1; i <= 100; i++) {

            if (i % 15 == 0) {
                System.out.print("Fizz Buzz, ");
            } else if (i % 3 == 0) {
                System.out.print("Fizz, ");
            } else if (i % 5 == 0) {
                System.out.print("Buzz, ");
            } else {
                System.out.print(i + ", ");
            }
        }
    }

    public static void main(String[] args) {
        fizzBuzzChallenge();
    }
}
