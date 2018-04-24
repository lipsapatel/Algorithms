/**
 * Swap two numbers without using extra variable
 * a = 3
 * b = 5
 *
 * a = a + b = 8
 * a = 8
 *
 * b = a - b = 8 - 5 = 3
 * b = 3
 *
 * a = a - b = 8 - 3 = 5
 * a = 5
 */
public class SwapTwoNumbersWithoutUsingExtraVariable {

    private static void swapTwoNumbers(int a, int b) {

        System.out.println("The two numbers before swapping: First Number: " + a + " Second Number: "
                            + b );

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("The two numbers after swapping: First Number: " + a + " Second Number: "
                            + b );
    }

    public static void main(String[] args) {
        swapTwoNumbers(3, 5);
        swapTwoNumbers(10, 10);
        swapTwoNumbers(0, 0);
    }
}
