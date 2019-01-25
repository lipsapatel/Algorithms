import java.util.ArrayList;

/**
 * Write a function travel that accepts an x/y position parameter and prints or displays
 * all ways to walk from (0,0) to that point by taking single steps North, East
 * or Northeast
 *
 * Example: travel(2, 1); might print
 *
 * E E N
 * E N E
 * E NE
 * N E E
 * NE E
 *
 * For extra fun try drawing the paths on a graphical window
 */
public class TravelToPoint {

    private static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void travelHelper(Point target, Point me, ArrayList<String> chosen) {

        if (target.x == me.x && target.y == me.y) {
            //Base Case: done
            System.out.println(chosen);
        } else if (me.x <= target.x && me.y <= target.y) {

            //Recursive case: Take one step towards target

            //Choose, explore, unchoose E
            Point e = new Point(me.x + 1, me.y);

            chosen.add("E ");
            travelHelper(target, e, chosen);
            chosen.remove(chosen.size() - 1);

            //Choose, explore, unchoose N
            Point n = new Point(me.x, me.y + 1);

            chosen.add("N ");
            travelHelper(target, n, chosen);
            chosen.remove(chosen.size() - 1);

            //Choose, explore, unchoose NE
            Point ne = new Point(me.x + 1, me.y + 1);

            chosen.add("NE ");
            travelHelper(target, ne, chosen);
            chosen.remove(chosen.size() - 1);
        }
    }

    public static void main(String[] args) {
        Point target = new Point(4, 3);

        Point origin = new Point(0, 0);

        travelHelper(target, origin, new ArrayList<String>());
    }
}
