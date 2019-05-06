/**
 * A boomerang is a set of 3 points that are all distinct and not in a straight line.

 Given a list of three points in the plane, return whether these points are a boomerang.



 Example 1:

 Input: [[1,1],[2,3],[3,2]]
 Output: true
 Example 2:

 Input: [[1,1],[2,2],[3,3]]
 Output: false


 Note:

 points.length == 3
 points[i].length == 2
 0 <= points[i][j] <= 100

 Given the coordinates of the three vertices of any triangle, the area of the triangle is given by:
 area	=
 1/2|(x1(y2−y3)+x2(y3−y1)+x3(y1−y2))|

 where Ax and Ay are the x and y coordinates of the point A etc..

 If the area is zero
 If the area comes out to be zero, it means the three points are collinear.
 They lie in a straight line and do not form a triangle. You can drag the points above to create this condition.

 */
public class ValidBoomerang {

    public static boolean isBoomerang(int[][] p) {

        double area = Math.abs(p[0][0] * (p[1][1] - p[2][1]) +
                               p[1][0] * (p[2][1] - p[0][1]) +
                               p[2][0] * (p[0][1] - p[1][1]))/2;

        if (area == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        int[][] points = {{1, 1}, {2, 3}, {3, 2}};

        System.out.println("Valid Boomerang: " + isBoomerang(points));

        int[][] points1 = {{1, 1}, {2, 2}, {3, 3}};

        System.out.println("Valid Boomerang: " + isBoomerang(points1));
    }
}
