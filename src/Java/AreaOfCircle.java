package Java;

/**
 * Find the Area of a circle
 *
 * Area of circle = pi * radius * radius
 * where pi = 3.14 and R = radius of circle
 *
 * Example:
 *
 * Radius: 4.0
 * Area = pi * radius * radius
 *  = 3.14 * 4 * 4 = 50.24
 *
 */
public class AreaOfCircle {

    private static void areaOfCircle(double radius) {

        double pi = 3.14;
        double area = pi * radius * radius;
        System.out.println("Area of circle with radius =  " + radius + " is: " + area);
    }

    public static void main(String[] args) {

        double radius = 4;
        areaOfCircle(radius);
    }
}
