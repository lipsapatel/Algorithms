/**
 * Sort 3 integers without using if condition
 * you can only use Max() function
 *
 * Given 3 integers, sort them without using if condition
 *
 * Approach:
 *
 * 1) Find the maximum of a, b, c
 * 2) Multiply all by -1 and again find the maximum of a, b, c - This will give you the minimum element
 * 3) Mid element = (a + b + c) - (min + max);
 */
public class SortThreeIntegersUsingMaxFunction {

    private static void sortThreeIntegersUsingMaxFunction(int a, int b, int c) {

        int max, mid, min;

        max = Math.max(a, Math.max(b, c));
        min = - (Math.max(-a, Math.max(-b, -c)));
        mid = (a + b + c) - (min + max);

        System.out.println("Sorted order is " + min + " " + mid + " " + max);
    }

    public static void main(String[] args) {

        sortThreeIntegersUsingMaxFunction(4, 1, 9);
    }
}
