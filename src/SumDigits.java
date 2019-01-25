/**
 * Given a non-negative int n, return the sum of its digits recursively (no loops).
 * Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6), while divide (/) by 10 removes the rightmost digit (126 / 10 is 12).


 sumDigits(126) → 9
 sumDigits(49) → 13
 sumDigits(12) → 3
 */
public class SumDigits {

    private static int sumDigits(int n) {
        //Base Case
        if (n == 0) {
            return 0;
        } else { //Recursive Case
            return n % 10 + sumDigits(n / 10);
        }
    }

    public static void main(String[] args) {
        System.out.println("The sum of Digits for 126 is: " + sumDigits(126));
        System.out.println("The sum of Digits for 49 is: " + sumDigits(49));
        System.out.println("The sum of Digits for 12 is: " + sumDigits(12));
    }
}
