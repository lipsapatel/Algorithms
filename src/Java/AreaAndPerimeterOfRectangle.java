package Java;

/**
 * Find the area and perimeter of rectangle
 *
 * A rectangle is a quadrilateral with four right angles.
 *
 * Say rectangle is given with side's length A and B.
 *
 * Area of Rectangle = A * B
 * Perimeter of rectangle = 2 (A + B)
 *
 * resources/AreaAndPerimeterOfRectangle.png
 */
public class AreaAndPerimeterOfRectangle {

    private static void area(double a, double b) {

        double area = a * b;

        System.out.println("Area of Rectangle: " + area);
    }

    private static void perimeter(double a, double b) {

        double perimeter = 2 * (a + b);

        System.out.println("Perimeter of Rectangle: " + perimeter);
    }

    public static void main(String[] args) {

        double a = 3;
        double b = 4;

        area(a, b);
        perimeter(a, b);
    }
}
