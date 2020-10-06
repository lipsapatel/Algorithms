import java.util.Arrays;

/**
 * Alternating Positives and Negatives
 * Given an array of positive and negative integers, rearrange the elements
 * so that the positive and negative numbers alternate. Order of the positive
 * elements should be preserved, same with the negative ones.
 *
 * Consider zero a positive number for this exercise.
 *
 * Start output with a positive integer if one exists in the input.
 *
 * Number of positive and negative integers may not be equal and
 * extra positives or negatives have to appear at the end of the output.
 *
 * Example
 *
 * Input: [2 3 -4 -9 -1 -7 1 -5 -6]
 *
 * Output: [2 -4 3 -9 1 -1 -7 -5 -6]
 *
 * The order of positives in the input: 2, 3, 1.
 *
 * The order of negatives in the input: -4, -9, -1, -7, -5, -6.
 *
 * We start with the first positive number, alternate until we run out of
 * (in this case) positives, and dump the remaining negatives at the end of the output.
 *
 * Constraints:
 *
 *     1 <= n <= 500000
 *     -2 * 10^9 <= array[i] <= 2 * 10^9
 *
 * Approach:
 *
 * 1) We keep two pointers
 *      p = 0;
 *      n = 0;
 * 2) We merge numbers in new array similar to merge sort.
 * 3) Move forward positive pointer until you find next positive element.
 * 4) Move forward negative pointer until you find next negative element.
 * 5) All positive elements are at even index 0, 2, 4, ....
 * 6) All negative elements are at odd index 1, 3, 5,...
 * 7) Once the value of positive pointer or negative pointer becomes n, then add
 *    remaining negative or positive elements.
 *
 * Time Complexity: O(n)
 * Space Complexity: (n)
 *
 * resources/AlternatingPositivesAndNegatives1.jpg
 * resources/AlternatingPositivesAndNegatives2.jpg
 * resources/AlternatingPositivesAndNegatives3.jpg
 */
public class AlternatingPositivesAndNegatives {

    private static int[] alternatingPositivesAndNegatives(int[] a) {
        int[] result = new int[a.length];

        int p = 0;
        int n = 0;

        for(int i = 0; i < a.length; i++) {

            //Increment p till it points to next positive element
            while(p < a.length && a[p] < 0) {
                p++;
            }

            //Increment n till it points to next negative element
            while(n < a.length && a[n] >= 0) {
                n++;
            }

            //Append all remaining negative elements
            if(p == a.length) {
                result[i] = a[n];
                n++;
            } else if (n == a.length) { //Append all remaining positive elements
                result[i] = a[p];
                p++;
            } else {
                //Even position - positive element
                if(i % 2 == 0) {
                    result[i] = a[p];
                    p++;
                } else { //Odd position - negative element
                    result[i] = a[n];
                    n++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {5, 0, 1, -3, 4, -6, -8, 3, 2, -9};

        System.out.println(Arrays.toString(alternatingPositivesAndNegatives(a)));
    }

}
