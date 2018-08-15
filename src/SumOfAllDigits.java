/**
 * Get the sum of digits in a number till it become a single digit.
 *
 * Given a number, write a program to get the sum of digits in a number till it become a single digit
 *
 * Example:
 *
 * N = 999 = 9 + 9 + 9 = 27 = 2 + 7 = 9
 * N = 789 = 7 + 8 + 9 = 24 = 2 + 4 = 6
 *
 * Approach:
 *
 * Recursive Approach
 *
 * 1. Find the sum of all digits
 * 2. Make a recursive call with sum calculated in step 1
 * 3. If number is less than 10, return number
 *
 * Another Approach:
 *
 * 1. If number is 0, return 0
 * 2. Find the remainder of number with 9 (number % 9)
 * 3. If remainder is 0, return 9 else return remainder
 *
 */
public class SumOfAllDigits {

    private static int findSumOfAllDigitsRecursive(int number) {

        if (number < 10) {
            return number;
        }

        int sum = 0;

        while(number > 0) {

            sum += number % 10;
            number = number/10;

        }

        return findSumOfAllDigitsRecursive(sum);
    }

    private static int findSumOfAllDigitsAnotherApproach(int number) {

        if (number == 0) {
            return number;
        }

        int remainder = number % 9;

        if (remainder == 0) {
            return 9;
        } else {
            return remainder;
        }
    }

    public static void main(String[] args) {

        int number = 12345;
        int result = findSumOfAllDigitsRecursive(number);
        System.out.println("Sum of digits in a number " + number + " till it become a singe digit: " + result);

        number = 999;
        result = findSumOfAllDigitsRecursive(number);
        System.out.println("Sum of digits in a number " + number + " till it become a single digit: " + result);

        number = 111;
        result = findSumOfAllDigitsRecursive(number);
        System.out.println("Sum of digits in a number " + number + " till it become a single digit: " + result);

        number = 12345;
        result = findSumOfAllDigitsAnotherApproach(number);
        System.out.println("Sum of digits in a number " + number + " till it become a singe digit: " + result);

        number = 999;
        result = findSumOfAllDigitsAnotherApproach(number);
        System.out.println("Sum of digits in a number " + number + " till it become a single digit: " + result);

        number = 111;
        result = findSumOfAllDigitsAnotherApproach(number);
        System.out.println("Sum of digits in a number " + number + " till it become a single digit: " + result);
    }
}
