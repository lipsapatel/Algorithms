import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * We are given a list of (axis-aligned) rectangles.
 * Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the
 * coordinates of the bottom-left corner, and (x2, y2) are the coordinates
 * of the top-right corner of the ith rectangle.
 *
 * Find the total area covered by all rectangles in the plane.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * Output: 6
 * Explanation: As illustrated in the picture.
 *
 * Example 2:
 *
 * Input: [[0,0,1000000000,1000000000]]
 * Output: 49
 * Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
 *
 * Note:
 *
 *     1 <= rectangles.length <= 200
 *     rectanges[i].length = 4
 *     0 <= rectangles[i][j] <= 10^9
 *     The total area covered by all rectangles will never exceed 2^63 - 1
 *     and thus will fit in a 64-bit signed integer.
 *
 *     Approach:
 *
 *     1) Imagine we pass a horizontal line from bottom to top
 *     2) We have some active intervals on this horizontal line.
 *     3) For a rectangle = [1, 0, 3, 1]
 *     First update is to add [1, 3] to active set at y = 0
 *     Second update is to remove [1, 3] at y = 1
 *
 *     4) Create two events for each rectangle.
 *     5) Sort them by y-axis
 *     6) We can use priority queue TC = O(nlogn)
 *     7) Process all items from priority queue.
 *     8) Add and remove for start and end respectively.
 *     9) After processing all items at y, merge interval and find area
 *     10) Query() the total horizontal length of our active interval.
 *
 *     11) Add all (x1, s2) in sorted order
 *     12) Use TreeMap
 *         Add and remove = O(nlogn) for n items
 *
 *     13) Query() - merge Intervals = O(n)
 *
 *     Time Complexity = O(n^2 * logn)
 *     Space Complexity: O(n)
 *
 *  You can use segment tree.
 *  Build time is O(n)
 *  Query time is O(logn)
 *
 *  Total time complexity for Segment tree - O(nlogn)
 *  Space Complexity: O(n)
 *
 *  resources/RectangleArea1.jpg
 *  resources/RectangleArea2.jpg
 *  resources/RectangleArea3.jpg
 *  resources/RectangleArea4.jpg
 */
public class RectangleAreaII {

    public static class PqNode {
        int[] interval;
        int y;
        boolean isStart;

        public PqNode(int[] i, int y1, boolean s) {
            interval = i;
            y = y1;
            isStart = s;
        }
    }

    public static int rectangleArea(int[][] rectangles) {

        //Create priority queue sorted by y-axis
        PriorityQueue<PqNode> pq = new PriorityQueue<>(new Comparator<PqNode>() {
            @Override
            public int compare(PqNode n1, PqNode n2) {
                return n1.y - n2.y;
            }
        });

        //Using lambda
        PriorityQueue<PqNode> pq1 = new PriorityQueue<>((n1, n2) -> n1.y - n2.y);

        //Add to priority queue
        for(int[] rectangle: rectangles) {

            PqNode start = new PqNode(new int[]{rectangle[0], rectangle[2]},
                    rectangle[1], true);

            PqNode end = new PqNode(new int[]{rectangle[0], rectangle[2]},
                    rectangle[3], false);

            pq.add(start);
            pq.add(end);
        }

        //Create active interval list at each y
        //Sorted by first x-axis
        TreeMap<int[], Integer> activeMap = new TreeMap<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] i1, int[] i2) {
                if(i1[0] == i2[0]) {
                    return i1[1] - i2[1]; //Don't allow duplicates. Duplicate keys
                    //are not possible
                } else {
                    return i1[0] - i2[0];
                }
            }
        });

        //Lambda
        TreeMap<int[], Integer> activeMap1 = new TreeMap<>((i1, i2) -> i1[0] - i2[0]);

        //Add and remove active interval in activeMap
        //Merge Intervals and calculate area
        long area = 0;

        int y = 0;
        if(!pq.isEmpty()) {
            y = pq.peek().y;
        }
        LinkedList<int[]> mergedIntervals = new LinkedList<>();
        while(!pq.isEmpty()) {
            int prevY = y;
            y = pq.peek().y;

            if(y > prevY) {
                //Update area
                for(int[] interval: mergedIntervals) {
                    area = area + (long)(interval[1] - interval[0]) * (y - prevY);
                }
                //For area you need to cover all active interval difference and
                //multiply with vertical height
                // This is cover all y - prevY squares
                //for area - look at second example and last example
                // to understand this.

            }

            while(!pq.isEmpty() && pq.peek().y == y) {

                PqNode pqNode = pq.poll();
                int[] interval = pqNode.interval;

                if(pqNode.isStart) {
                    //Add
                    activeMap.put(interval,
                            activeMap.getOrDefault(interval, 0) + 1);
                } else {
                    //Remove
                    int value = activeMap.get(interval) - 1;
                    if(value == 0) {
                        activeMap.remove(interval);
                    } else {
                        activeMap.put(interval, value);
                    }
                }
            }

            //Merge Intervals
            mergedIntervals = new LinkedList<>();
            for(int[] interval: activeMap.keySet()) {

                if(mergedIntervals.isEmpty() ||
                        mergedIntervals.getLast()[1] < interval[0]) {
                    //Don't overlap
                    //This is required because when you add interval you are
                    //adding reference then when you change it also changes in
                    //activeMap
                    mergedIntervals.add(new int[]{interval[0], interval[1]});
                } else {
                    //Overlap
                    mergedIntervals.getLast()[1] = Math.max(interval[1],
                            mergedIntervals.getLast()[1]);
                }
            }


        }
        return (int)(area % 1000000007);
    }

    public static void main(String[] args) {
        int[][] rectangles = {{0, 0, 2, 2}, {1, 0, 2, 3}, {1, 0, 3, 1}};

        System.out.println(rectangleArea(rectangles));

        int[][] rectangles1 = {{0,0,1000000000,1000000000}};

        System.out.println(rectangleArea(rectangles1));

        int[][] rectangles2 = {{2, 1, 5, 4}};

        System.out.println(rectangleArea(rectangles2));

        int[][] rectangles3 = {{0,0,3,3},{2,0,5,3},{1,1,4,4}};

        System.out.println(rectangleArea(rectangles3));

    }

}
