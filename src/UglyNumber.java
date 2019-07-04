/**
 * Write a program to check whether a given number is an ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

 Example 1:

 Input: 6
 Output: true
 Explanation: 6 = 2 × 3

 Example 2:

 Input: 8
 Output: true
 Explanation: 8 = 2 × 2 × 2

 Example 3:

 Input: 14
 Output: false
 Explanation: 14 is not ugly since it includes another prime factor 7.

 Note:

 1 is typically treated as an ugly number.
 Input is within the 32-bit signed integer range: [−231,  231 − 1].


 Time Complexity: O(n) + O(Math.sqrt(n))
 Space Complexity: O(1)
 */
public class UglyNumber {

    public static boolean isUgly(int num) {

        if (num < 1) {
            return false;
        }

        if (num == 1 || num == 2 || num == 3 || num == 5) {
            return true;
        }

        if (num % 2 == 0) { //This will eventually reduce to 2, 3 0r 5 if it reduce to some other number then it's not ugly
            return isUgly(num/2);
        } else if (num % 3 == 0) {
            return isUgly(num/3);
        } else if (num % 5 == 0) {
            return isUgly(num/5);
        } else {
            return false;
        }

    }


    public static void main(String[] args) {
        System.out.println("The given number is Ugly number: " + isUgly(6));
    }
}
