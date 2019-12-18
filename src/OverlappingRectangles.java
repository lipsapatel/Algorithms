import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Describe an algorithm that takes an unsorted array of axis-aligned rectangles and
 * returns any pair of rectangles that overlaps, if there is such a pair.
 * Axis-aligned means that all the rectangle sides are either parallel or perpendicular to
 * the x- and y-axis. You can assume that each rectangle object has two variables in it: the x-y
 * coordinates of the upper-left corner and the bottom-right corner.
 *
 * Time Complexity
 * Sort: nlogn
 * insert: nlogn
 * delete: nlogn
 * search: Rlogn - NRlogn
 *
 * This is assuming that the interval tree
 is balanced.
 If it's not balanced then it's n for insert, delete and search.

 * Now lets see the detailed algorithm of the original problem:
 1) Sort all left and right rectangle edges, according to their X value, into list L.
 2) Create a new empty range search tree T, for Y ordering of rectangle tops/bottoms
 3) Create a new empty result set RS of unique rectangle pairs
 4) Traverse L in ascending order. For all V in L:
 If V.isRightEdge()
 T.remove(V.rectangle.top)
 T.remove(V.rectangle.bottom)
 else
 For all U in T.getRange(V.rectangle.top, V.rectangle.bottom) find overlapping intervals
 RS.add(<V.rectangle, U.rectangle>)
 T.add(V.rectangle.top)
 T.add(V.rectangle.bottom)
 5) return RS

 src/OverlappingRectangles.jpg
 */
public class OverlappingRectangles {

    private static IntervalNodeRect root = null;

    public static class Rectangle {

        public int x1;
        public int x2;
        public int y1;
        public int y2;
        public String id;
        public boolean isLeft;

        public Rectangle(int x1, int x2, int y1, int y2, String id, boolean isLeft) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.id = id;
            this.isLeft = isLeft;
        }
    }

    private static class IntervalNodeRect {

        public int low;
        public int high;
        public int max;
        public Rectangle rect;
        public IntervalNodeRect left;
        public IntervalNodeRect right;

        public IntervalNodeRect(int low, int high, Rectangle r) {
            this.low = low;
            this.high = high;
            this.max = high;
            this.rect = r;
            this.left = null;
            this.right = null;
        }
    }

    private static List<Pair<Rectangle, Rectangle>> findOverlappingRectangles(List<Rectangle> list) {

       //Sort by x
        PriorityQueue<Rectangle> pq = new PriorityQueue<>(list.size(), new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return o1.x1 - o2.x1;
            }
        });

        List<Pair<Rectangle, Rectangle>> resultSet = new ArrayList<>();

        for (Rectangle rectangle: list) {
            pq.add(rectangle);
        }

        while(!pq.isEmpty()) {

            Rectangle rect = pq.poll();

            if (!rect.isLeft) {
                root = delete(root, rect.y1);
            } else {

                List<IntervalNodeRect> overlappedInterval = new ArrayList<>();
                searchOverlappingInterval(overlappedInterval, root, rect.y2, rect.y1);

                for (IntervalNodeRect overlapInterval: overlappedInterval) {
                    Pair<Rectangle, Rectangle> pair = new Pair(overlapInterval.rect, rect);
                    resultSet.add(pair);
                }
                root = insert(root, rect, rect.y2, rect.y1);
            }
        }
        return resultSet;
    }

    private static IntervalNodeRect insert(IntervalNodeRect root, Rectangle rect, int low, int high) {

        //Base Case
        if (root == null) {
            return new IntervalNodeRect(low, high, rect);
        }

        //Recursive Case
        if (low < root.low) { //left
            root.left = insert(root.left, rect, low, high);
        } else { //right
            root.right = insert(root.right, rect, low, high);
        }

        //Update max
        if (root.max < high) {
            root.max = high;
        }

        return root;
    }

    private static void searchOverlappingInterval(List<IntervalNodeRect> overlappedInterval, IntervalNodeRect root, int low, int high) {

        //Base Case
        if (root == null) {
            return;
        }

        //return if it's overlapping
        if (low <= root.high && high >= root.low) {
            overlappedInterval.add(root);
        }

        //If left subtree is null, go right
        //or if max endpoint in left subtree is less than low, then go to right
        if (root.left == null || root.left.max < low) {
            searchOverlappingInterval(overlappedInterval, root.right, low, high);
        } else { //go left
            searchOverlappingInterval(overlappedInterval, root.left, low, high);
        }
    }


    private static IntervalNodeRect delete(IntervalNodeRect root, int val) {

        //Base Case
        if (root == null) {
            return root;
        }

        if (val < root.low) {
            root.left = delete(root.left, val);
        } else if (val > root.low) {
            root.right = delete(root.right, val);
        } else { //Matches

            //One child or no child
            if (root.left == null || root.right == null) {

                IntervalNodeRect kid = null;

                if (root.left != null) {
                    kid = root.left;
                }

                if (root.right != null) {
                    kid = root.right;
                }

                //No child
                if (kid == null) {
                    root = null;
                } else {
                    root = kid;
                }
            } else { //Two child

                //Find inorder successor
                IntervalNodeRect minNode = computeMin(root.right);

                //Swap the low, high and max of the current with minNode
                root.low = minNode.low;
                root.high = minNode.high;
                root.max = minNode.max;
                root.rect = minNode.rect;

                root.right = delete(root.right, minNode.low);
            }
        }
        root = updateRootMax(root);
        return root;
    }

    private static IntervalNodeRect updateRootMax(IntervalNodeRect root) {

        if (root != null) {

            //Set root to the max val of itself, left and right
            root.max = root.high;
            if (root.left != null && root.left.max > root.max) {
                root.max = root.left.max;
            }

            if (root.right != null && root.right.max > root.max) {
                root.max = root.right.max;
            }
        }
        return root;
    }

    private static IntervalNodeRect computeMin(IntervalNodeRect root) {
        IntervalNodeRect current = root;

        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    public static void main(String[] args) {
        List<Rectangle> list = new ArrayList<>();

        Rectangle rectangle = new Rectangle(1, 4, 4, 1, "a", true);
        Rectangle rectangle1 = new Rectangle(4, 1,1, 4, "a", false);
        Rectangle rectangle2 = new Rectangle(3, 6, 5, 3, "b", true);
        Rectangle rectangle3 = new Rectangle(6, 3, 3, 5, "b", false);
        Rectangle rectangle4 = new Rectangle(5, 7, 4, 1, "c", true);
        Rectangle rectangle5 = new Rectangle(7, 5, 1, 4, "c", false);
        Rectangle rectangle6 = new Rectangle(8, 9, 3, 1, "d", true);
        Rectangle rectangle7 = new Rectangle(9, 8, 1, 3, "d", false);

        list.add(rectangle);
        list.add(rectangle1);
        list.add(rectangle2);
        list.add(rectangle3);
        list.add(rectangle4);
        list.add(rectangle5);
        list.add(rectangle6);
        list.add(rectangle7);

        List<Pair<Rectangle, Rectangle>> resultSet = findOverlappingRectangles(list);

        System.out.println("The overlapping rectangles are");
        for (Pair<Rectangle, Rectangle> pair: resultSet) {
            Rectangle r1 = pair.getKey();
            Rectangle r2 = pair.getValue();

            System.out.println("List of Overlapping Rectangles");
            System.out.println("Rectangle1: (" + r1.x1 + " , " + r1.y1 + ")  (" + r1.x2 + " , " + r1.y2 + " )");
            System.out.println("Rectangle2: (" + r2.x1 + " , " + r2.y1 + ")  (" + r2.x2 + " , " + r2.y2 + " )");
        }
    }
}
