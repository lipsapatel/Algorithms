import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 * A city's skyline is the outer contour of the silhouette formed by all
 * the buildings in that city when viewed from a distance. Now suppose you are
 * given the locations and height of all the buildings as shown on a cityscape
 * photo (Figure A), write a program to output the skyline formed by these
 * buildings collectively (Figure B).
 *
 * Buildings Skyline Contour
 * The geometric information of each building is represented by a triplet of
 * integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and
 * right edge of the ith building, respectively, and Hi is its height.
 * It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
 * You may assume all buildings are perfect rectangles grounded on an absolutely
 * flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
 * A key point is the left endpoint of a horizontal line segment. Note that
 * the last key point, where the rightmost building ends, is merely used to
 * mark the termination of the skyline, and always has zero height.
 * Also, the ground in between any two adjacent buildings should be considered
 * part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:
 * [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 * The number of buildings in any input list is guaranteed to be in the range
 * [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the
 * output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...]
 * is not acceptable; the three lines of height 5 should be merged into
 * one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 *
 * resources/SkylineProblem1.jpg
 * resources/SkylineProblem2.jpg
 * resources/SkylineProblem3.jpg
 * resources/SkylineProblem4.jpg
 *
 * Approach - Line Sweep along x axis
 *
 * 1) Sort all the given points along x axis, if two points x axis are equal,
 * then sort using below 3 cases:
 *
 *    i) If both are start, sort descending order of height
 *    ii) If both are end, sort ascending order of height
 *    iii) If one is start and other is end, start should come before end
 *
 * 2) Scan all the points from left to right on number line
 * 3) Add height and count to priority queue for start
 * 4) Decrement count and remove for end
 * 5) If currentMaxHeight changes, add x and currentMaxHeight to final result.
 *
 * Time Complexity: Sort(nlogn) and Add/Remove from priority queue(logn) = O(nlogn)
 *
 * We are using sorted tree map instead of priority queue because the
 * remove in priority queue is n instead of logn
 * Tree map's remove is logn
 *
 * Space Complexity: O(n)
 */
public class SkylineProblem {

    public static class BuildingPoint {
        int x;
        boolean isStart;
        int height;

        public BuildingPoint(int x1, boolean s, int h) {
            x = x1;
            isStart = s;
            height = h;
        }
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {

        List<BuildingPoint> buildingPointList = new ArrayList();

        //Add all buildings to buildingPointList
        for(int[] building: buildings) {
            BuildingPoint start = new BuildingPoint(building[0], true, building[2]);
            BuildingPoint end = new BuildingPoint(building[1], false, building[2]);
            buildingPointList.add(start);
            buildingPointList.add(end);
        }

        //Sort by x and 3 cases if x is equal
        buildingPointList.sort(new Comparator<BuildingPoint>() {

                    @Override
                    public int compare(BuildingPoint p1, BuildingPoint p2) {

                        if (p1.x != p2.x) {
                            return p1.x - p2.x;
                        } else { //p1.x == p2.x

                            //Case 1: Both are start, descending order of height
                            if (p1.isStart && p2.isStart) {
                                return p2.height - p1.height;

                            } else if (!p1.isStart && !p2.isStart) {
                                //Case 2: Both are end, ascending order of height
                                return p1.height - p2.height;

                            } else if (p1.isStart && !p2.isStart) {
                                //Case 3: One is start and other is end
                                //Start comes before end
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    }
                });

        //Sorted by key - height
        TreeMap<Integer, Integer> queue = new TreeMap<>();

        //height and count
        queue.put(0, 1);

        int prevMaxHeight = 0;

        List<List<Integer>> result = new ArrayList<>();

        //Scan along the x axis
        for(BuildingPoint buildingPoint: buildingPointList) {

            int height = buildingPoint.height;

            //Add to queue
            if(buildingPoint.isStart) {
                //Add height
                queue.put(height, queue.getOrDefault(height, 0) + 1);
            } else {
                //end so remove from queue
                int value = queue.get(height) - 1;
                if(value == 0) {
                    queue.remove(height);
                } else {
                    queue.put(height, value);
                }
            }

            int currentMaxHeight = queue.lastKey();

            if(prevMaxHeight != currentMaxHeight) {
                //Add to result
                ArrayList<Integer> point = new ArrayList<>();
                point.add(buildingPoint.x);
                point.add(currentMaxHeight);
                result.add(point);
                prevMaxHeight = currentMaxHeight;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] buildings = {{1, 3, 4}, {3, 4, 4}, {2, 6, 2}, {8, 11, 4}, {7, 9, 3},
                {10, 11, 2}};

        List<List<Integer>> points = getSkyline(buildings);

        points.forEach(point -> {
            System.out.println(point.get(0) + " " + point.get(1));
        });
    }
}
