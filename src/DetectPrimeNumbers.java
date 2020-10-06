/**
 * Detect Prime Numbers
 * Given an array of integers, check each number if it’s prime.

 * Example
 * Input: [6, 2, 5, 8]
 * Output: “0110”
 *
 * 6 is not a prime number. (6 = 2 * 3)
 * 2 is a prime number.
 * 5 is a prime number.
 * 8 is not a prime number. (8 = 2 * 4)
 *
 * Output: Return a string of the length equal to the size
 * of the input array. For each number in the array the
 * string has to have either “0” (not prime) or “1” (prime) character.
 *
 * Constraints:
 *     1 <= any input array element <= 4 * 10^6
 *     1 <= number of array elements <= 3 * 10^5
 *
 * Approach - Brute Force Approach
 *
 * 1) For any number A, check if it divides from any number between 2 to A - 1.
 * 2) If it does divides, then it's not prime
 *
 * Time Complexity: O(n * maxA)
 * Space Complexity: O(1)
 *
 * Approach - Suboptimal Approach - sqrt(n)
 *
 * 1) If x is non-prime, then we can write
 *    x = a * b where a > 1 and b > 1
 *
 *    If a > sqrt(x) and b > sqrt(x)
 *
 *    then a * b > sqrt(x) * sqrt(x)
 *    a * b > x
 *    which contradicts a * b = x
 *
 * 2) At least one of a or b should be <= sqrt(x)
 * 3) Loop from 2 to sqrt(x)
 *
 * Time Complexity: (n * sqrt(maxA))
 * Space Complexity: O(1)
 *
 * Approach - Sieve of Eratosthenes
 *
 * 1) Mark all multiple of 2 as non-prime or visited.
 * 2) Now 3 is prime, so mark all multiples of 3 as non-prime or visited.
 * 3) Now 4 is already marked as visited or non-prime by 2. So no need to
 * mark multiples of 4 since they are marked by 2.
 * 4) For 5, we mark 10, 15, 20, 25, ... as non-prime but 10, 15, 20 are already
 * marked by 2 and 3.
 * 5) So instead of starting marking multiples as non-prime from x + x, we start
 * at x * x.
 * 6) The terminating loop condition is sqrt(n) because all non-prime > sqrt(n)
 * will already been marked.
 *
 * Time Complexity: O(n * log(logn))
 * where n = MAX_A
 *
 * Space Complexity: O(MAX_A)
 *
 * The solution with time complexity O(n * log(logn)) where n = MAX_A
 * is better solution than solution with time complexity O(n * sqrt(n)).
 *
 * But it also depends on the situration. We should also consider space.
 * Solution with TC = O(n * sqrt(n)) require O(1) space but the other
 * need O(MAX_A) space.
 * So when space is important than time, we should opt for slower one.
 *
 * If given a single integer and need to find if it's prime or not,
 * we should use solution with TC = O(sqrt(n))
 *
 * Sieve of Eratosthenes is helpful when
 *
 * 1) Many numbers need to be checked and pre-processing can help.
 * 2) If those numbers are limited to a given range - so we can estimate how
 * much space the algorithm would use.
 *
 * In this problem, we are given range for numbers. But when given stream
 * of random numbers and nothing is specified about range of a[i], then we
 * can use caching.
 *
 * Caching will be useful when some numbers are going to repeat, though
 * in real life that is likely to happen.
 *
 * We maintain hashmap with number and whether it's prime or not.
 *
 * 1 false
 * 2 true
 * 3 true
 * 4 false
 * ...
 *
 * For each number, we check the presence in our hashMap. If it is
 * present, we are done.
 *
 * If it is not present, then we use O(sqrt(n)) method to check if it is
 * prime or not and add to hashMap.
 *
 * To further improve auxiliary space, we can check even numbers
 * without adding them to the hashMap.
 *
 * resources/DetectPrimeNumbers1.jpg
 * resources/DetectPrimeNumbers.jpg
 * resources/DetectPrimes2.jpg
 * resources/DetectPrimes3.jpg
 * resources/DetectPrimes4.jpg
 * resources/DetectPrimes5.jpg
 * resources/DetectPrimes6.jpg
 */
public class DetectPrimeNumbers {

    public static String detectPrimes(int[] a) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < a.length; i++) {
            if(isPrimeSqrt(a[i])) {
                result.append("1");
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    //O(n)
    public static boolean isPrime(int n) {
        if(n == 1) {
            return false;
        }

        for(int i = 2; i < n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //O(sqrt(n))
    public static boolean isPrimeSqrt(int n) {
        if(n == 1) {
            return false;
        }

        //To avoid calling expensive sqrt operation repeatedly
        //for(int i = 2; i <= Math.sqrt(n); i++) {
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //TC = O(n * log(logn)) where n = maxA
    //SC = O(maxA)
    public static String detectPrimesSieveOfEratosthenes(int[] a) {
        StringBuilder result = new StringBuilder();

        int max = a[0];

        //Find max
        for (int value : a) {
            if (value > max) {
                max = value;
            }
        }

        boolean[] isPrime = new boolean[max + 1]; //since array index starts at 0

        applySieveOfEratosthenes(isPrime, max);

        for(int value: a) {
            if(isPrime[value]) {
                result.append("1");
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    public static void applySieveOfEratosthenes(boolean[] isPrime, int n) {
        for(int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        isPrime[1] = false;

        for(int i = 2; i * i <= n; i++) {

            if(!isPrime[i]) {
                continue;
            }

            for(int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {6, 2, 5, 8};
        System.out.println(detectPrimes(a));

        System.out.println(detectPrimesSieveOfEratosthenes(a));
    }
}
