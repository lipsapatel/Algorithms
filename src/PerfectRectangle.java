import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Given N axis-aligned rectangles where N > 0,
 * determine if they all together form an exact cover of a rectangular region.
 *
 * Each rectangle is represented as a bottom-left point and a top-right point.
 * For example, a unit square is represented as [1,1,2,2].
 * (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 *
 * Example 1:
 *
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [3,2,4,4],
 *   [1,3,2,4],
 *   [2,3,3,4]
 * ]
 *
 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
 *
 * Example 2:
 *
 * rectangles = [
 *   [1,1,2,3],
 *   [1,3,2,4],
 *   [3,1,4,2],
 *   [3,2,4,4]
 * ]
 *
 * Return false. Because there is a gap between the two rectangular regions.
 *
 * Example 3:
 *
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [1,3,2,4],
 *   [3,2,4,4]
 * ]
 *
 * Return false. Because there is a gap in the top center.
 *
 * Example 4:
 *
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [1,3,2,4],
 *   [2,2,4,4]
 * ]
 *
 * Return false. Because two of the rectangles overlap with each other.
 *
 * resources/PerfectRectangle1.jpg
 * resources/PerfectRectangle2.jpg
 * resources/PerfectRectangle3.jpg
 * resources/PerfectRectangle4.jpg
 * resources/PerfectRectangle5.jpg
 * resources/PerfectRectangle6.jpg
 * resources/PerfectRectangle7.jpg
 * resources/PerfectRectangle8.jpg
 *
 * Approach: Sweep Line Algorithm
 *
 * 1) Sort by x-axis. If both x values are equal then sort by x-axis of start
 *  (bottom-left)
 * 2) Use priority queue for this.
 * 3) Insert in tree set for start and check for intersection of y-axis.
 * If intersect return false
 * 4) Remove from tree set for end
 * 5) Check y-axis range for each x-axis, return false if it doesn't matches.
 * 6) Y-axis range will be 0 when priority queue is empty.
 *
 * Time Complexity: O(nlogn)
 * Space Complexity: O(n)
 */
public class PerfectRectangle {

    public static class PqNode {
        int x;
        boolean isStart;
        int[] rectangle;

        public PqNode(int x1, boolean s, int[] r) {
            x = x1;
            isStart = s;
            rectangle = r;
        }
    }

    public static boolean isRectangleCover(int[][] rectangles) {

        PriorityQueue<PqNode> pq = new PriorityQueue<>(new Comparator<PqNode>() {

            @Override
            public int compare(PqNode n1, PqNode n2) {
                if(n1.x != n2.x) {
                    return n1.x - n2.x;
                } else {
                    return n1.rectangle[0] - n2.rectangle[0];
                }
            }
        });

        //Lambda Expression
        /*PriorityQueue<PqNode> pq = new PriorityQueue<>((n1, n2) -> {
                if(n1.x != n2.x) {
                    return n1.x - n2.x;
                } else {
                    return n1.rectangle[0] - n2.rectangle[0];
                }
            }
        );*/

        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

        for(int[] rectangle: rectangles) {
            PqNode start = new PqNode(rectangle[0], true, rectangle);
            PqNode end = new PqNode(rectangle[2], false, rectangle);
            pq.add(start);
            pq.add(end);

            if(rectangle[1] < yMin) {
                yMin = rectangle[1];
            }

            if(rectangle[3] > yMax) {
                yMax = rectangle[3];
            }
        }

        TreeSet<int[]> set = new TreeSet(new Comparator<int[]>() {

            @Override
            public int compare(int[] r1, int[] r2) {
                if(r1[3] <= r2[1]) {
                    return -1;
                } else if(r2[3] <= r1[1]) {
                    return 1;
                } else { //Some where in between means intersect
                    return 0;
                }
            }
        });

        int yRange = 0;
        while(!pq.isEmpty()) {
            int time = pq.peek().x; //lowest x

            while(!pq.isEmpty() && time == pq.peek().x) {
                PqNode n = pq.poll();

                if(n.isStart) {
                    //Add to treeset
                    if(!set.add(n.rectangle)) {
                        return false; //intersect
                    }
                    yRange += n.rectangle[3] - n.rectangle[1];
                } else {
                    //Remove from treeset
                    set.remove(n.rectangle);
                    yRange -= n.rectangle[3] - n.rectangle[1];
                }
            }

            //The yRange should match at each x-axis to form rectangle
            //When done (pq is empty) yRange will be 0
            if(!pq.isEmpty() && yRange != yMax - yMin) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] rectangles = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4},
                {1, 3, 2, 4}, {2, 3, 3, 4}};
        System.out.println(isRectangleCover(rectangles));
    }
}
