/**
 * Find area of triangle using coordinates
 *
 * Given three vertices coordinates or (x, y) coordinates, write a program to find the area of triangle
 *
 * Example
 *
 * resources/AreaOfTriangeUsingCoordinates.png
 *
 * Approach:
 *
 * 1) Given the coordinates of the three vertices of triangle
 * Area = |x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2)/2|
 */
public class AreaOfTriangleUsingCoordinates {

    private static void areaOfTriangleUsingCoordinates(double[] x, double[] y) {

        double area = Math.abs(x[0] * (y[1] - y[2]) +
                               x[1] * (y[2] - y[0]) +
                               x[2] * (y[0] - y[1])) /2;

        System.out.println("Area of triangle using coordinates is: " + area);
    }

    public static void main(String[] args) {

        double[] x_coordinates = {1, 2, 5};
        double[] y_coordinates = {0, 6, 10};

        areaOfTriangleUsingCoordinates(x_coordinates, y_coordinates);
    }
}
