import java.util.Arrays;

/**
 * Array Product
 * Given an array of numbers nums of size n, find an array of numbers products of size
 * n, such that products[i] is the product of all numbers nums[j], where j != i.
 *
 * Example One
 * Input:
 * 5
 * 1
 * 2
 * 3
 * 4
 * 5
 * Output:
 * 120
 * 60
 * 40
 * 30
 * 24
 *
 * Resultant Product array products =
 * [products[0], products[1], products[2], products[3], products[4]]
 * = [(nums[1]*nums[2]*nums[3]*nums[4]), (nums[0]*nums[2]*nums[3]*nums[4]),
 * (nums[0]*nums[1]*nums[3]*nums[4]), (nums[0]*nums[1]*nums[2]*nums[4]),
 * (nums[0]*nums[1]*nums[2]*nums[3])]
 * = [(2*3*4*5), (1*3*4*5), (1*2*4*5), (1*2*3*5), (1*2*3*4)]
 * = [120, 60, 40, 30, 24]
 *
 * Example Two
 * Input:
 * 3
 * 4
 * 9
 * 10
 *
 * Output:
 * 90
 * 40
 * 36
 *
 * Resultant Product array products = [products[0], products[1], products[2]]
 * = [(nums[1]*nums[2]), (nums[0]*nums[2]), (nums[0]*nums[1])]
 * = [(9*10), (4*10), (4*9)]
 *  = [90, 40, 36]
 *
 * Output: Return an array of numbers products, denoting the required
 * product array where products[i] is the (product of all numbers nums[j]) % (10^9 + 7),
 * where j != i.
 *
 * Constraints:
 * •	You can't use division anywhere in solution.
 * •	2 <= n <= 100000
 * •	-10^9 <= nums[i] <= 10^9, i = 0, 1, 2, … , n-1
 * •	products[i] >=0, i = 0, 1, 2, ... , n-1
 * •	You are allowed to use only constant extra space and the resultant product array will not be considered extra space.
 * Usage of resultant products array will not be considered as extra space used.
 * Without using division is the key constraint to remember.
 *
 * Approach
 *
 * Brute Force Approach
 * 1) Iterate all elements of array except i.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Optimal Approach
 *
 * 1) Scan the array from left to right, do the product of left elements
 * 2) Scan the array from right to left, do the product of right elements
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * resources/ArrayProduct1.jpg
 * resources/ArrayProduct2.jpg
 */
public class ArrayProduct {

    private static int mod = 1000000007;

    private static int[] getProductArray(int[] a) {
        int[] p = new int[a.length];

        //Left Product
        int leftP = 1;

        for(int i = 0; i < a.length; i++) {
            p[i] = leftP;

            if(a[i] < 0) { //The product needs to be positive so make it positive
                a[i] = (mod + a[i]) % mod;
            }

            leftP = (int) ((leftP * 1l * a[i]) % mod);
        }

        //Right Product
        int rightP = 1;

        for(int i = a.length - 1; i >= 0; i--) {
            p[i] = (int)((p[i] * 1l * rightP) % mod);

            rightP = (int) ((rightP * 1l * a[i]) % mod);
        }
        return p;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};

        System.out.println(Arrays.toString(getProductArray(a)));

        int[] a1 = {4, 9, 10};
        System.out.println(Arrays.toString(getProductArray(a1)));

        int[] a2 = {-1000000000, -1000000000};
        System.out.println(Arrays.toString(getProductArray(a2)));
    }
}
