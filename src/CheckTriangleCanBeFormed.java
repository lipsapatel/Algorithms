/**
 * Write Java program to find if Triangle can be formed using given 3 sides.
 *
 * Given 3 side lengths, write a program to find out if using these 3 sides, a triangle can be formed.
 *
 * Example:
 * Triangle can be formed using side 2.5, 3.5, 5.0
 * Triangle cannot be formed using side 1.0, 3.0, 5.0
 * Sum of 1.0 and 3.0 is not  > 5.0
 *
 * Approach:
 * 1) If sum of length of any two sides is strictly greater than the length of third side,
 * then triangle can be constructed else we cannot construct a triangle.
 * 2) Say sides lengths are a, b, c then to form a triangle, a+b > c, b+c > a and c+a > b
 * If any of the above condition is not true, triangle cannot be formed.
 *
 */
public class CheckTriangleCanBeFormed {

    private static void checkTriangleCanBeFormed(double a, double b, double c) {

        //Check all the combinations
        if (check(a, b, c) && check(b, c, a) && check(c, a, b)) {

            System.out.println("Triangle can be formed using the side " + a + ", " + b + ", " + c);
        } else {
            System.out.println("Triangle cannot be formed using side " + a + ", " + b + ", " + c);
        }
    }

    private static boolean check(double a, double b, double c) {

        //Check if sum of a and b is greater than c
        if ((a + b) > c) {
            return true;
        }

        System.out.println("Sum of " + a + " and " + b + " is not > " + c);
        return false;
    }

    public static void main(String[] args) {

        double a = 2.5;
        double b = 3.5;
        double c = 5;

        checkTriangleCanBeFormed(a, b, c);

        a = 1;
        b = 3;
        c = 5;

        checkTriangleCanBeFormed(a, b, c);
    }
}
