import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process: Starting with any positive integer,
 replace the number by the sum of the squares of its digits, and repeat the process
 until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 Those numbers for which this process ends in 1 are happy numbers.

 Example:

 Input: 19
 Output: true
 Explanation:
 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1

 When the number is looping endlessly, it will get the same sum twice

 for example: 12
 1^2 + 2^2 = 5
 5^2 = 25
 2^2 + 5 ^2 = 29
 4 + 81 = 85
 64 + 25 = 89
 64 + 81 = 145
 1 + 16 + 25 = 42
 16 + 4 = 20
 4 = 4
 16 = 16
 1 + 36 = 37
 9 + 49 = 58
 25 + 64 = 89
 64 + 81 = 145
 1 + 16 + 25 = 42
 16 + 4 = 20
 4 = 4
 16 = 16

 The numbers started to get repeated after 89

 For Example: 33
 9 + 9 = 18
 1 + 64 = 65
 36 + 25 = 61
 36 + 1 = 37
 9 + 49= 58
 25 + 64 = 89
 64 + 81 = 145
 1 + 16 + 25 = 42
 16 + 4 = 20
 4 = 4
 16 = 16
 1 + 36 = 37
 9 + 49 = 58
 25 + 64 = 89

 The numbers started to get repeated after 37

 */
public class HappyNumber {

    private static boolean isHappy(int n) {

        if (n == 1 || n == 7) { //Base Case, 7 can make 1 so those are happy numbers
            return true;
        } else { //recursive case

            if (n < 10) {
                return false; //Not happy number
            }

            int squareSum = 0;
            while (n > 0) { //Read each digit
                squareSum += Math.pow(n % 10, 2);
                n /= 10;
            }

            //Recursive call
            return isHappy(squareSum);
        }
    }

    public static void main(String[] args) {

        System.out.println("Is number 12 happy: " + isHappy(12));
        System.out.println("Is number 19 happy: " + isHappy(19));
        System.out.println("Is number 33 happy: " + isHappy(33));
        System.out.println("Is number 25 happy: " + isHappy(25));
    }
}
