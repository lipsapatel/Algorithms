/**
 * Find the smallest prime palindrome greater than or equal to N. *
 * Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
 * * For example, 2,3,5,7,11 and 13 are primes.
 * * Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
 *
 * For example, 12321 is a palindrome.
 *
 * Example 1:
 *
 * Input: 6
 * Output: 7
 * Example 2:
 *
 * Input: 8
 * Output: 11
 * Example 3:
 *
 * Input: 13
 * Output: 101
 *
 * Note:
 *
 * 1 <= N <= 10^8
 * The answer is guaranteed to exist and be less than 2 * 10^8.
 *
 * The Naive approach is to loop from N + 1 until we found next smallest prime palindrome greater than or equal to N.
 *
 * Efficient Approach:
 * Lets say P = R is a the next smallest prime-palindrome greater than or equal to N.
 * Now since R is a palindrome, the first half of the digits of R can be used to determine R up-to two possibilities.
 * Let k be the first half of the digits in R. For eg. if k = 123, then R = 12321 or R = 123321.
 *
 * Thus we iterate through each k upto 105 and create the associated palindrome R, and check whether R is a prime or not.
 * Also we will handle the odd and even palindromes separately, and break when we fount our result.
 *
 * Time Complexity: O(n Math.sqrt(n))
 * Space Complexity: O(logn) - the space used by string and string builder.
 */
public class PrimePalindrome {

    //Time Complexity: O(n sqrt(n)) here n will be the upper limit
    private static int primePalindrome(int n) {
        //Since n is between 1 to 10^8
        //We can use number 1 to 10^4 to generate all possible palindromes
        //For all generated palindromes, check if it's greater than equal to n and prime

        //If n <= 7; return the prime number greater than equal to n
        //The prime number less than 7 are 2, 3, 5, 7
        //The reason for this is for n = 1, we will generate 1 and 11 as palindrome
        //so for n = 6 it will return 11 instead of 7

        int[] prime = {2, 3, 5, 7};

        if(n <= 7) {
            for(int i = 0; i < prime.length; i++) {
                if(prime[i] >= n) {
                    return prime[i];
                }
            }
        }

        int limit = 100000;

        for(int i = 1; i <= limit; i++) {

            //Generate odd length palindrome
            String s = Integer.toString(i);

            String rs = s.substring(0, s.length() - 1);
            StringBuilder reverseString = new StringBuilder(rs).reverse();
            String odd = s + reverseString;
            int palindrome = Integer.parseInt(odd);

            if(palindrome >= n && isPrime(palindrome)) {
                return palindrome;
            }

            //Generate even length palindrome
            reverseString = new StringBuilder(s).reverse();
            String even = s + reverseString;
            palindrome = Integer.parseInt(even);

            if(palindrome >= n && isPrime(palindrome)) {
                return palindrome;
            }
        }
        return -1;
    }

    //Time Complexity: O(sqrt(n))
    private static boolean isPrime(int n) {
        if(n < 2) {
            return false;
        }

        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println("The prime palindrome greater than equal to n " + primePalindrome(n));

        n = 8;
        System.out.println("The prime palindrome greater than equal to n " + primePalindrome(n));

        n = 13;
        System.out.println("The prime palindrome greater than equal to n " + primePalindrome(n));

        n = 9989900;
        System.out.println("The prime palindrome greater than equal to n " + primePalindrome(n));
    }
}
