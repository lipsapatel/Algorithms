import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

 (Here, the distance between two points on a plane is the Euclidean distance.)

 You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



 Example 1:

 Input: points = [[1,3],[-2,2]], K = 1
 Output: [[-2,2]]
 Explanation:
 The distance between (1, 3) and the origin is sqrt(10).
 The distance between (-2, 2) and the origin is sqrt(8).
 Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 Example 2:

 Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 Output: [[3,3],[-2,4]]
 (The answer [[-2,4],[3,3]] would also be accepted.)


 Note:

 1 <= K <= points.length <= 10000
 -10000 < points[i][0] < 10000
 -10000 < points[i][1] < 10000

 Max Priority Queue: Sorted order

 Time Complexity: O(nlogk)
 Space Complexity: O(K)
 Create priority queue of size k and extract the max till we have k elements left

 Quick Select: This will have k closest points but not in sorted order

 Time Complexity: O(n)
 Space Complexity: O(1) //O(n) //for distance

 Partition till pivot becomes k, all k closet element will be together.

 If you need to sort it, do a quicksort on k elements which will be O(klogk)

 */
class KthClosestPoint {

    public static int[][] kClosestUsingMaxPriorityQueue(int[][] points, int K) {

        //Math.sqrt((x2 - x1)^2 + (y2 - y1)^2)
        PriorityQueue<int[]> pq = new PriorityQueue<>(K, new Comparator<int[]>() { //max
            @Override
            public int compare(int[] a, int[] b) {
                return (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]);
            }
        });

        for (int[] point : points) { //n
            pq.offer(point); //logk

            if (pq.size() > K) {
                pq.poll(); //logk
            }
        }

        int[][] rst = new int[K][2];
        for (int i = 0; i < K; i++) {
            int[] point = pq.poll();
            rst[i][0] = point[0];
            rst[i][1] = point[1];
        }
        return rst;
    }

    public static int[][] kClosestUsingQuickSelect(int[][] points, int k) {

        //(distances, index)
        int[][] distance = new int[points.length][2];

        for (int i = 0; i < points.length; i++) {

            distance[i][0] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            distance[i][1] = i; //index
        }

        quickSelect(distance, 0, distance.length - 1, k); //O(n)

        //Return the first k closest element
        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            result[i][0] = points[distance[i][1]][0];
            result[i][1] = points[distance[i][1]][1];
        }
        return result;
    }

    public static void quickSelect(int[][] distance, int low, int high, int k) {

        if (low >= high) {
            return;
        }

        int pi = partition(distance, low, high);

        if (pi == k - 1) {
            return;
        } else if (pi < k) {
            quickSelect(distance, pi + 1, high, k);
        } else {
            quickSelect(distance, low, pi - 1, k);
        }
    }

    public static int partition(int[][] distance, int low, int high) {

        int middle = (low + high)/2;
        medianOfThree(distance, low, middle, high);
        swap(distance, middle, high);

        int pivot = distance[high][0];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (distance[j][0] <= pivot) {
                i++;
                swap(distance, i, j);
            }
        }

        i++;
        swap(distance, i, high);
        return i;
    }

    public static void medianOfThree(int[][] distance, int low, int middle, int high) {

        if (distance[middle][0] < distance[low][0]) {
            swap(distance, low, middle);
        }

        if (distance[high][0] < distance[low][0]) {
            swap(distance, low, high);
        }

        if (distance[high][0] < distance[middle][0]) {
            swap(distance, middle, high);
        }
    }

    public static void swap(int[][] distance, int i, int j) {

        int temp = distance[i][0];
        int temp1 = distance[i][1];

        distance[i][0] = distance[j][0];
        distance[i][1] = distance[j][1];

        distance[j][0] = temp;
        distance[j][1] = temp1;
    }

    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};

        int[][] result = kClosestUsingMaxPriorityQueue(points, 2);

        System.out.println("K closest elements using priority queue");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + ", " + result[i][1]);
        }

        int[][] result1 = kClosestUsingQuickSelect(points, 2);

        System.out.println("K closest elements using quick select");
        for (int i = 0; i < result1.length; i++) {
            System.out.println(result1[i][0] + ", " + result1[i][1]);
        }
    }
}
