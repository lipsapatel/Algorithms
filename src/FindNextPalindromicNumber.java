/**
 * Find The Next Palindromic Number
 * Given an integer n, find the smallest palindromic number that’s greater than n.
 *
 * Example One
 * Input: 5
 * Output: 6
 *
 * 6 is a palindromic number, and it is greater than 5.
 * There is no palindromic number lesser than 6 and greater than 5.
 *
 * Example Two
 * Input: 10
 * Output: 11
 *
 * Constraints:
 *     0 <= n <= 2147483647
 *
 * Approach:
 *
 * There are three ways to convert given number to palindrome.
 *
 * 1) Replicate first half - in this case the difference is less.
 * 2) Replicate second half
 * 3) Change the middle
 *
 * Now to find closest palindrome there are 3 cases:
 * Case 1:
 *  1) Replicate the first half - mirror
 *
 * Case 2:
 * 2) If middle = 0, then replace 0 with 9
 * 3) After replacement, if middle is pointing to first element and if it's 1
 * then remove 1 and change newMid to 9
 * 4) Or decrement mid by 1
 *
 * Find mirror
 *
 * This case will give number less than n so we can skip this case.
 *
 * Case 3:
 * 5) If middle = 9, then replace 9 with 0
 * 6) After replacement, if middle < 0, then add 1
 * 7) else increment by 1
 *
 * Find mirror
 *
 * If answer of case 1 and case 3 > n, then choose the one with minimum difference.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class FindNextPalindromicNumber {

    public static long nextPalindrome(int n) {
        String num = String.valueOf(n);

        //Case 1: Replicate first half - mirror
        String a = getMirror(num);

        long p1 = Long.parseLong(a);

        long diff1 = Long.MAX_VALUE;

        if(p1 > n) {
            diff1 = p1 - n;
        }

        //Case 2: Replace 9 with 0
        StringBuilder s = new StringBuilder(num);
        int mid = (s.length() - 1)/2;

        while(mid >= 0 && s.charAt(mid) == '9') {
            s.replace(mid, mid + 1, "0");
            mid--;
        }

        if(mid < 0) { //Add 1
            s.insert(0, "1");
        } else { //Increment by 1
            s.replace(mid, mid + 1, "" + (char)(s.charAt(mid) + 1));
        }

        String b = getMirror(s.toString());

        long diff2 = Long.MAX_VALUE;
        long p2 = Long.parseLong(b);

        if(p2 > n) {
            diff2 = p2 - n;
        }

        if(diff1 < diff2) {
            return p1;
        } else {
            return p2;
        }
    }

    public static String getMirror(String num) {
        int mid = num.length()/2;
        String x = num.substring(0, mid);

        String midChar = "";
        if(num.length() % 2 == 1) {
            midChar = Character.toString(num.charAt(mid));
        }

        return x + midChar + new StringBuilder(x).reverse();
    }

    public static void main(String[] args) {
        System.out.println("Next Palindrome number " + nextPalindrome(10));
        System.out.println("Next Palindrome number " + nextPalindrome(5));
        System.out.println("Next Palindrome number " + nextPalindrome(19999));
        System.out.println("Next Palindrome number " + nextPalindrome(10000));
        System.out.println("Next Palindrome number " + nextPalindrome(50000));
        System.out.println("Next Palindrome number " + nextPalindrome(10005));
    }
}
