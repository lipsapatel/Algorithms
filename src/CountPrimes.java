/**
 * Count the number of prime numbers less than a non-negative number, n.

 Example:

 Input: 10
 Output: 4
 Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

 TC = n*sqrt(n)

 The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n.
 But don't let that name scare you, I promise that the concept is surprisingly simple.


 Sieve of Eratosthenes: algorithm steps for primes below 121. "Sieve of Eratosthenes Animation" by SKopp is licensed under CC BY 2.0.

 We start off with a table of n numbers. Let's look at the first number, 2. We know all multiples of 2 must not be primes,
 so we mark them off as non-primes. Then we look at the next number, 3. Similarly, all multiples of 3
 such as 3 × 2 = 6, 3 × 3 = 9, ... must not be primes, so we mark them off as well.
 Now we look at the next number, 4, which was already marked off. What does this tell you? Should you mark off all multiples of 4 as well?

 4 is not a prime because it is divisible by 2, which means all multiples of 4 must also be divisible by 2
 and were already marked off. So we can skip 4 immediately and go to the next number, 5.
 Now, all multiples of 5 such as 5 × 2 = 10, 5 × 3 = 15, 5 × 4 = 20, 5 × 5 = 25, ... can be marked off.
 There is a slight optimization here, we do not need to start from 5 × 2 = 10. Where should we start marking off?

 In fact, we can mark off multiples of 5 starting at 5 × 5 = 25, because 5 × 2 = 10 was already marked off by
 multiple of 2, similarly 5 × 3 = 15 was already marked off by multiple of 3.
 Therefore, if the current number is p, we can always mark off multiples of p starting at p2, then in
 increments of p: p2 + p, p2 + 2p, ... Now what should be the terminating loop condition?

 It is easy to say that the terminating loop condition is p < n, which is certainly correct but not efficient. Do you still remember Hint #3?

 Yes, the terminating loop condition can be p < √n, as all non-primes ≥ √n must have already been marked off.
 When the loop terminates, all the numbers in the table that are non-marked are prime.

 The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n).
 For the more mathematically inclined readers, you can read more about its algorithm complexity on Wikipedia.

 public int countPrimes(int n) {
 boolean[] isPrime = new boolean[n];
 for (int i = 2; i < n; i++) {
 isPrime[i] = true;
 }
 // Loop's ending condition is i * i < n instead of i < sqrt(n)
 // to avoid repeatedly calling an expensive function sqrt().
 for (int i = 2; i * i < n; i++) {
 if (!isPrime[i]) continue;
 for (int j = i * i; j < n; j += i) {
 isPrime[j] = false;
 }
 }
 int count = 0;
 for (int i = 2; i < n; i++) {
 if (isPrime[i]) count++;
 }
 return count;
 }

 TC = O(nloglogn)
 */
public class CountPrimes {

    private static boolean isPrime(int n) {

        for (int i = 2; i <= Math.sqrt(n); i++) { //sqrt(n) n^0.5n //to have it little bit faster avoid expensive function sqrt and use i * i <= n
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int countPrimes(int n) {
        int count = 0;

        for (int i = 2; i < n; i++) { //n
            if(isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private static int countPrimesSieveEratosthenes(int n) {

        boolean[] isPrime = new boolean[n];

        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        //Loop's ending condition is i * i <= n instead of i <= Math.sqrt(n)
        //to avoid repeatedly calling an expensive function sqrt()
        for (int i = 2; i * i <= n; i++) {

            if (!isPrime[i]) {
                continue;
            }

            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("The total count of prime numbers are: " + countPrimes(n));

        System.out.println("The total count of prime numbers using sieve eratosthenes: " + countPrimesSieveEratosthenes(n));
    }
}
