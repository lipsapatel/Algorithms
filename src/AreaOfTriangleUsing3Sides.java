/**
 * Find the Area of a triangle given three sides - Heron's Formula
 *
 * Given three sides of triangle, write a program to find the area of triangle
 *
 * Heron's Formula for finding area of triangle:
 *
 * 1) First find the semi parameter of a triangle using formula s = (a + b + c)/2, where a, b, c are three sides of triangle
 * and s will be the semi parameter
 * 2) Then use the below formula to get the area of triangle
 * Area = Math.sqrt(s (s - a) (s - b) (s - c))
 *
 * resources/AreaOfTriangleUsing3Sides.png
 */
public class AreaOfTriangleUsing3Sides {

    private static void areaOfTriangleUsing3Sides(double a, double b, double c) {

        //Get the Semi parameter
        double s = (a + b + c)/2;

        //find the area of triangle
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

        System.out.println("Area of triangle: " + area + " sq unit");
    }

    public static void main(String[] args) {

        double a = 3.5;
        double b = 4.5;
        double c = 6.0;

        areaOfTriangleUsing3Sides(a, b, c);
    }
}
