/**
 * Given a non-negative integer N, find the largest number that is less
 * than or equal to N with monotone increasing digits.
 *
 * (Recall that an integer has monotone increasing digits
 * if and only if each pair of adjacent digits x and y satisfy x <= y.)
 *
 * Example 1:
 *
 * Input: N = 10
 * Output: 9
 *
 * Example 2:
 *
 * Input: N = 1234
 * Output: 1234
 *
 * Example 3:
 *
 * Input: N = 332
 * Output: 299
 *
 * Note: N is an integer in the range [0, 10^9].
 *
 * resources/MonotoneIncreasingDigits1.jpg
 * resources/MonotoneIncreasingDigits2.jpg
 * resources/MonotoneIncreasingDigits3.jpg
 *
 * Approach
 *
 * 1) We could always have a candidate answer of d99999....
 * 2) Find the first cliff where s[i - 1] > s[i]
 * 3) While the cliff exists, decrement the digit at i - 1 and move back i
 * 4) Finally make rest of digit 9s.
 * 5) Return
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class MonotoneIncreasingDigits {

    public static int monotoneIncreasingDigits(int N) {
        char[] s = (String.valueOf(N)).toCharArray();

        //Find cliff
        int i = 1;
        while (i < s.length && s[i] >= s[i - 1]) {
            i++;
        }

        //Decrement while cliff exists
        while(i > 0 && i < s.length && s[i - 1] > s[i]) {
            s[i - 1]--;
            i--;
        }

        //Make rest of the digit 9
        for(int j = i + 1; j < s.length; j++) {
            s[j] = '9';
        }
        return Integer.valueOf(String.valueOf(s));
    }

    public static void main(String[] args) {
        int N = 10;
        System.out.println("Largest number <= N with monotone increasing digits "
                            + monotoneIncreasingDigits(N));

        N = 1234;
        System.out.println("Largest number <= N with monotone increasing digits "
                            + monotoneIncreasingDigits(N));

        N = 322;
        System.out.println("Largest number <= N with monotone increasing digits "
                + monotoneIncreasingDigits(N));

        N = 221;
        System.out.println("Largest number <= N with monotone increasing digits "
                + monotoneIncreasingDigits(N));

        N = 333222;
        System.out.println("Largest number <= N with monotone increasing digits "
                + monotoneIncreasingDigits(N));

        N = 120;
        System.out.println("Largest number <= N with monotone increasing digits "
                + monotoneIncreasingDigits(N));

        N = 331;
        System.out.println("Largest number <= N with monotone increasing digits "
                + monotoneIncreasingDigits(N));

    }
}
