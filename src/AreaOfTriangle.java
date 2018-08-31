/**
 * Find the area of triangle using base and height
 *
 * Given base and height of a triangle, calculate triangle area
 *
 * Area of Triangle = (base * height)/2;
 *
 * resources/AreaOfTriangle.png
 */
public class AreaOfTriangle {

    private static void areaOfTriangle(double base, double height) {

        double area = (base * height)/2;

        System.out.println("Area of Triangle is: " + area);
    }

    public static void main(String[] args) {

        double base = 4;
        double height = 5;

        areaOfTriangle(4, 5);
    }
}
