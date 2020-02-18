/**
 * L, R are in the range 1 to 10^18
 * Let assume that R is 10^18
 * If n * n = sp
 * if sp = 10^18 then n has to be Math.sqrt(10^18) = 10^9
 * Now you can scan all the n from 1 to 10^9
 * But that would be too much time
 * So if n is palindrome which is 10^9, it can be formed using any number which Math.sqrt(10^9) = 10^5
 * so iterate from 1 to 10^5 and for palindromes, multiply to form super palindromes.
 *  Check if super palindrome is actually a palindrome and it falls within range.
 *  No need to continue for loop if super palindromes goes outside of range R.
 *
 *  The TC of this approach is 10^5
 *
 *  Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.

 Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].

 Example 1:
 Input: L = "4", R = "1000"
 Output: 4
 Explanation: 4, 9, 121, and 484 are superpalindromes.
 Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.

 Note:

 1 <= len(L) <= 18
 1 <= len(R) <= 18
 L and R are strings representing integers in the range [1, 10^18).
 int(L) <= int(R)

 Approach:
 Lets say P = R^{2} is a super-palindrome.
 Now since R is a palindrome, the first half of the digits of R can be used to determine R up-to two possibilities.
 Let i be the first half of the digits in R. For eg. if i = 123, then R = 12321 or R = 123321.
 Thus we can iterate through these all these digits. Also each possibility can have either odd or even number of digits in R.
 Thus we iterate through each i upto 10^ 5 and create the associated palindrome R, and check whether R^2 is a palindrome.
 Also we will handle the odd and even palindromes separately, and break whenever out palindrome goes beyond R.
 Now since P <= 10^{18}, and R <= (10^{18})^{\frac{1}{2}} = 10^{9} and  R = i||i^{'} (on Concatenation),
 where i‘ is reverse of i (in both ways), so our LIMIT will not be greater than i <= 10^{5}.
 */
class SuperPalindromes {
    public static int superpalindromesInRange(String L, String R) {
        int count = 0;

        long start = Long.parseLong(L);
        long end = Long.parseLong(R);

        int limit = 100000;

        //Count the super palindromes generated from odd length palindrome
        for(int i = 1; i <= limit; i++) {
            String s = Integer.toString(i);
            String rs = s.substring(0, s.length() - 1); //Remove last character for odd length

            StringBuilder reverseString = new StringBuilder(rs).reverse();

            String odd = s + reverseString;
            long sp = Long.parseLong(odd) * Long.parseLong(odd);

            if(sp > end) {
                break; //break the for loop
            }

            if(sp >= start && isPalindrome(Long.toString(sp))) {
                count = count + 1;
            }
        }

        //Count the super palindromes generated from even length palindrome
        for(int i = 1; i <= limit; i++) {
            String s = Integer.toString(i);
            StringBuilder reverseString = new StringBuilder(s).reverse();

            String even = s + reverseString;
            long sp = Long.parseLong(even) * Long.parseLong(even);

            if(sp > end) {
                break; //break the for loop
            }

            if(sp >= start && isPalindrome(Long.toString(sp))) {
                count = count + 1;
            }
        }
        return count;
    }

    public static boolean isPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;

        while(lo < hi) {
            if(s.charAt(lo) == s.charAt(hi)) {
                lo++;
                hi--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(superpalindromesInRange("38455498359", "999999999999999999"));
        System.out.println(superpalindromesInRange("1","5"));
        System.out.println(superpalindromesInRange("398904669","13479046850"));
    }
}