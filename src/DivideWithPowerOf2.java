/**
 * Divide with power of 2 without using pow() or / operator
 *
 * Given a number n and k, calculate n/k^2 without using pow() or / operator
 *
 * Example:
 *
 * N = 48
 * k = 4
 *
 * N/k^2 = 3
 *
 * Approach: Bit Manipulation
 *
 * 1) Right shift the number N by k
 * 2) N = 48
 * 3) Bit representation: 0 1 1 0 0 0 0
 * 4) Right shift by k = 4
 * 5) 0 1 1 which is the representation of 3
 */
public class DivideWithPowerOf2 {

    private static void divideWithPowerOf2(int n, int k) {

        System.out.print("Divide number " + n + " by " + k + "^2 is: ");
        System.out.println(n >> k);
    }

    public static void main(String[] args) {
        int n = 48;
        int k = 4;
        divideWithPowerOf2(n, k);
    }
}
