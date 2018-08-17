package Java;

/**
 * Find circumference of circle
 *
 * Area of circumference = 2nr
 *
 * Where n = 3.14 and r = radius of circle
 *
 * Example:
 *
 * r = 4.0;
 * Area = 2nr = 2 * 3.14 * 4.0 = 25.12
 */
public class CircumferenceOfCircle {

    private static void findCircumference(double radius) {

        double pi = 3.14;
        double circumference = 2 * pi * radius;

        System.out.println("Area of circumference with radius = " + radius + " is: " + circumference);
    }

    public static void main(String[] args) {

        findCircumference(4);
    }
}
