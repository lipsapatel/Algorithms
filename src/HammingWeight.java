import java.util.ArrayList;
import java.util.List;

/**
 * Hamming Weight
 *
 * Calculate Hamming weight of an array of integers.
 * Hamming weight of an integer is defined as the number of set bits in its
 * binary representation. Hamming weight of an array is a sum of hamming weights
 * of all numbers in it.
 *
 * Example
 * Input:[1, 2, 3]
 * Output: 4
 *
 * Binary representation of 1 is “1”; one set bit.
 * Binary representation of 2 is “10”; one set bit.
 * Binary representation of 3 is “11”; two set bits.
 * 1 + 1 + 2 = 4
 *
 * Constraints:
 *
 *     1 <= n <= 10^5
 *     0 <= s[i] < 2^32 where 0 <= i < n.
 *
 * Brute Force Approach
 *
 * 1) 32-bit integer.
 * 2) Iterate for 32 times and count the set bit for all 32-bit position
 * 3) Keep the count of set bits.
 *
 * n = 12
 *
 * Binary representation = 01100
 *                       & 00001
 *                       = 00000
 *
 *                       01100
 *                     & 00010
 *                     = 00000
 *
 *                     01100
 *                   & 00100
 *                   = 00100
 *                    count = 1
 *
 *                    keep doing this for all 32-bits
 *
 *  Time Complexity: O(n * 32)
 *  Space Complexity: O(1)
 *
 *  Optimal Solution
 *
 *  1) Integer size is 32 bits.
 *  2) Divide into 16 bits and lets call it A and B
 *  3) Total set bits = number of set bits in A and B
 *  4) sz = number of possible 16-bit integer = 2^16
 *  5) Precompute number of set bits for sz
 *
 *  dp[i] = dp[i >> 1] + (i & 1);
 *  right shift by 1      0th bit set or not
 *
 *  dp[5] = dp[2] + (5 & 1)
 *           1 + 1 = 2
 *  Right shifting an integer by 1 will result in an integer smaller than itself.
 *
 *  Now for int x
 *
 *  1101000101001001 0010101001001000
 *        B               A
 *  &
 *  0000000000000000 1111111111111111
 *
 *  =                0010101001001000
 *  dp[A] + dp[B]
 *
 *  Instead of dividing array into two parts - 16 bits each we can divide into 4 parts
 *  8 bit each
 *
 *  This will reduce the space complexity significantly, but will required 4 lookups
 *  hence double the time complexity.
 *
 *  Space time trade off
 *
 *  Time Complexity: O(n + sz)
 *  where n = number of elements in given array
 *  sz = number of possible 16-bit integers = 2 ^16
 *
 *  Precomputing dp state for all 16-bit integers take 2^16
 *
 *  To calculate set bits in integer x, we are performing 2 lookups
 *  so for n integers = 2n
 *
 *  Space Complexity: O(sz)
 *  where sz = 2^16 possible 16-bit integers.
 *
 *  bitmask = (1 << 16) - 1;
 *  This will give 1111111111111111
 *
 *  resources/HammingWeight1.jpg
 *  resources/HammingWeight2.jpg
 *  resources/HammingWeight3.jpg
 *  resources/HammingWeight4.jpg
 *  resources/HammingWeight5.jpg
 *  resources/HammingWeight6.jpg
 *
 */
public class HammingWeight {

    public static int calculateHammingWeight(List<Long> s) {
        int totalSetBits = 0;

        for(int i = 0; i < s.size(); i++) {
            for(int j = 0; j < 31; j++) {
                if((s.get(i) & 1L << j) != 0) {
                    totalSetBits++;
                }
            }
        }
        return totalSetBits;
    }

    private static int calculateHammingWeightOptimal(List<Long> s) {
        //Calculate the hamming weight of all 16-bit integer = 2^16
        int sz = (1 << 16); //2^16

        int[] dp = new int[sz];
        dp[0] = 0;

        for(int i = 1; i < sz; i++) {
            dp[i] = dp[i >> 1] + (i & 1); //last bit
        }

        int totalSetBits = 0;
        int bitMask = sz - 1; //1111111111111111

        for (int i = 0; i < s.size(); i++) {
            totalSetBits += dp[(int)(s.get(i) & bitMask)];
            s.set(i, s.get(i) >> 16);
            totalSetBits += dp[(int)(s.get(i) & bitMask)];
        }
        return totalSetBits;
    }

    public static void main(String[] args) {
        List<Long> s = new ArrayList<>();
        s.add(1L);
        s.add(2L);
        s.add(3L);

        System.out.println("The hamming weight " + calculateHammingWeight(s));
        System.out.println("The hamming weight using optimal " + calculateHammingWeightOptimal(s));
    }
}
