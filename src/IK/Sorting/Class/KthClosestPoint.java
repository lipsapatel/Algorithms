package IK.Sorting.Class;

import java.util.*;

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
1) Calculate distance
2) Create Max priority queue of size k
3) Poll if size of priority queue is greater than k
4) Priority queue will have k closest points
5) Create result array

 Time Complexity: O(nlogk)
 Space Complexity: O(K)
 Create priority queue of size k and extract the max till we have k elements left

 Quick Select: This will have k closest points but not in sorted order
 It can be sorted using O(KlogK)

 1) Calculate distance between two points
 2) Do quick select
 3) Partition the array
 4) if pi == k - 1, return
 5) If k - 1 < pi, then recurse (low, pi - 1)
 6) Else recurse (pi + 1, high)
 7) Return result

 Time Complexity: O(n + k)
 Space Complexity: O(1) //O(n + k) //for distance and result

Partition till pivot becomes k, all k closet element will be together.

 If you need to sort it, do a quicksort on k elements which will be O(klogk)

 Distance between two points (x1, y1) and (x2, y2) = sqrt((x1-x2)^2 + (y1-y2)^2)
 Multiplication result is long
 Math.sqrt result is double.
 *********************************************************************************************
 * We have provided three solutions and all the solutions contain necessary comments to understand the approach used:

 1) brute force solution.java
 Description:
 We compute the distance of each and every point present in array n_points from point p. After this, we sort the points present in array n_points according to their distances
 from point p in ascending order. Now we simply take the first k points from the array and append them to the result.
 Time Complexity (assuming that input arguments are already given and excluding time used in declaration of output):
 O(n*log(n)) where n is the size of array n_points.
 As we are computing the distance of each and every point present in array n_points from point p this will take O(n) and sorting them takes O(n*log(n)) time.

 Time Complexity:
 O(n*log(n)) where n is the size of array n_points.
 As time complexity assuming that input arguments are already given and excluding time used in declaration of output is O(n*log(n)),
 to read input it will take O(n) and to initialise output array it will take O(k) hence total complexity will be O(n*log(n)) + O(n) + O(k) → O(n*log(n)).

 Auxiliary Space Used:
 O(n) where n is the size of array n_points.
 As we create an auxiliary array to store the distances of all the points present in array n_points from point p. It will take extra space equal to the number of points
 hence it will be O(n).

 Space Complexity:
 O(n+k) where n is the size of array n_points and k is the number of points nearest to point p, to be returned as output.
 For storing input it would take O(n), as we are storing the points in array n_points and auxiliary space used is O(n) and O(k) to store output,
 hence total complexity will be O(n) + O(n) + O(k) → O(n+k).

 2) suboptimal_solution.java
 Description:
 We compute the distance of each and every point present in array n_points from point p. Along with this, we create a max heap and add the point whose distance
 we computed into the max heap. After each insertion, we check if the size of the max heap is less than or equal to k. If it is greater than k, we poll the maximum value out of it. Thus after insertion of all the points present in array n_points, and polling as per the condition mentioned, we will be left with k points having minimum distance.

 Time Complexity (assuming that input arguments are already given and excluding time used in declaration of output):
 O(n*log(k)) where n is the size of the array n_points and k is the number of points nearest to point p, to be returned as output.
 As we compute the distance of each point present in array n_points from point p and also maintain a max heap of size k, the complexity is O(n*log(k)).

Time Complexity:
 O(n*log(k)) where n is the size of the array n_points and k is the number of points nearest to point p, to be returned as output.
 As time complexity assuming that input arguments are already given and excluding time used in declaration of output is O(n*log(k)), to read input it will take O(n) and to initialise output array it will take O(k) hence total complexity will be O(n*log(k)) + O(n) + O(k) → O(n*log(k)).

 Auxiliary Space Used:
 O(k) where k is the number of points nearest to point p, to be returned as output.
 As we maintain a max heap of size k to store the k elements having the minimum distance from point P. It will take O(k) of extra space.

 Space Complexity:
 O(n+k) where n is the size of the array n_points and k is the number of points nearest to point p, to be returned as output.
 For storing input it would take O(n), as we are storing the points in array n_points and auxiliary space used is O(k) and O(k) to store output,
 hence total complexity will be O(n) + O(k) + O(k) → O(n+k).

 3) optimal_solution.java
 Description:
 We compute the distance of each and every point present in array n_points from point p. Now we need to select k points having the minimum distance. For this, we use the quickselect algorithm which is a subpart of the quicksort algorithm. We randomly shuffle the array to reduce the average case running time of quicksort algorithm. We choose a pivot and split the array by the pivot. Now, if the position of pivot decides which array should we split. Note that here we do not need to sort each and every subarray, we will only sort the subarrays which we require. This part is commented well in the code “optimal_solution.java”. Please refer to it in case of any query.
 NOTE: To learn more about the quickselect algorithm, please go through the following link: https://www.geeksforgeeks.org/quickselect-algorithm/

 Time Complexity (assuming that input arguments are already given and excluding time used in declaration of output):
 O(n) where n is the size of the array n_points.
 We compute the distance of each point present of array n_points in O(n). On average, the number of operations required for sorting (only selective sub arrays and skipping the ones not required) turns out to be n + n/2 + n/4 + ... ~ 2*n = O(n).

 Time Complexity:
 O(n+k) where n is the size of the array n_points and k is the number of points nearest to point p, to be returned as output.
 As time complexity assuming that input arguments are already given and excluding time used in declaration of output is O(n),
 to read input it will take O(n) and to initialise output array  it will take O(k) hence total complexity will be O(n) + O(n) + O(k) → O(n+k).

 Auxiliary Space Used:
 O(n) where n is the size of the array n_points.
 As we create an array to store the distance of each point present in array n_points from point p. It will take extra space of O(n).

 Space Complexity:
 O(n+k) where n is the size of the array n_points and k is the number of points nearest to point p, to be returned as output.
 For storing input it would take O(n), as we are storing the points in array n_points and auxiliary space used is O(n) and O(k) to store output, hence total complexity will be O(n) + O(n) + O(k) → O(n+k).
 */
class KthClosestPoint {

    //TC - O(nlogk) SC - O(n + k)
    //The K closest point will be in sorted order.
    public static int[][] kClosestUsingMaxPriorityQueue(int[][] points, int K) {

        //Math.sqrt((x2 - x1)^2 + (y2 - y1)^2)
/*        PriorityQueue<int[]> pq = new PriorityQueue<>(K, new Comparator<int[]>() { //max
            @Override
            public int compare(int[] a, int[] b) {
                return (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]);  //descending, returns negative when a is greater than b.
            }
        });*/

/*        PriorityQueue<int[]> pq = new PriorityQueue<>(K, (a, b) -> { //max
            return (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]);  //descending, returns negative when a is greater than b.
        });*/

        //Origin point from which the distance needs to be calculated
        int px = 0;
        int py = 0;

        int[][] distance = new int[points.length][2];

        for(int i = 0; i < points.length; i++) {
            distance[i][0] = ((px - points[i][0]) * (px - points[i][0])) + ((py - points[i][1]) * (py - points[i][1])); //calculated distance
            distance[i][1] = i; //index
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(K, (a, b) -> {
            return b[0] - a[0]; //max priority queue - descending, returns negative when a is greater than b
        });

        for (int[] d : distance) { //n
            pq.offer(d); //logk

            if (pq.size() > K) {
                pq.poll(); //logk
            }
        }

        int[][] rst = new int[K][2];
        for (int i = 0; i < K; i++) {
            int[] p = pq.poll();
            rst[i][0] = points[p[1]][0];
            rst[i][1] = points[p[1]][1];
        }
        return rst;
    }

    //TC = O(n + k)
    //SC = O(n + k)
    public static int[][] kClosestUsingQuickSelect(int px, int py, int[][] points, int k) {

        //(distances, index)
        int[][] distance = new int[points.length][2];

        for (int i = 0; i < points.length; i++) {

            distance[i][0] = (px - points[i][0]) * (px - points[i][0]) + (py - points[i][1]) * (py - points[i][1]);
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
        } else if (pi < k - 1) {
            quickSelect(distance, pi + 1, high, k);
        } else {
            quickSelect(distance, low, pi - 1, k);
        }
    }

    public static int partition(int[][] distance, int low, int high) {

        int middle = low + (high - low)/2;
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
//**********************************************************************************************************************************************
    public static class Point  {
        double distance;
        int index;

        public Point(double d, int i) {
            distance = d;
            index = i;
        }

    }

    //This is the right way - We need to type cast to long and take Math.sqrt
    public static List<List<Integer>> kClosestUsingQuickSelectList(int px, int py, List<List<Integer>> points, int k) {

        int size = points.size();

        //(distances, index)
        Point[] point = new Point[size];

        for (int i = 0; i < size; i++) {

            int x1 = points.get(i).get(0);
            int y1 = points.get(i).get(1);
            point[i] = new Point(Math.sqrt((long) (px - x1) * (px - x1) + (long) (py - y1) * (py - y1)), i);
        }

        quickSelectUsingPoint(point, 0, point.length - 1, k); //O(n)

        //Return the first k closest element
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int i = 0; i < k; i++) {
            result.add(points.get(point[i].index));
        }
        return result;
    }

    public static void quickSelectUsingPoint(Point[] points, int low, int high, int k) {

        if (low >= high) {
            return;
        }

        int pi = partitionUsingPoint(points, low, high);

        if (pi == k - 1) {
            return;
        } else if (pi < k) {
            quickSelectUsingPoint(points, pi + 1, high, k);
        } else {
            quickSelectUsingPoint(points, low, pi - 1, k);
        }
    }


    public static int partitionUsingPoint(Point[] points, int low, int high) {

        Random random = new Random();
        int p = low + random.nextInt(high - low + 1);
        //int middle = (low + high)/2;
        //medianOfThreeUsingPoint(points, low, middle, high);
        swapUsingPoint(points, p, high);

        double pivot = points[high].distance;
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (points[j].distance <= pivot) {
                i++;
                swapUsingPoint(points, i, j);
            }
        }

        i++;
        swapUsingPoint(points, i, high);
        return i;
    }

    public static void medianOfThreeUsingPoint(Point[] points, int low, int middle, int high) {

        if (points[middle].distance < points[low].distance) {
            swapUsingPoint(points, low, middle);
        }

        if (points[high].distance < points[low].distance) {
            swapUsingPoint(points, low, high);
        }

        if (points[high].distance < points[middle].distance) {
            swapUsingPoint(points, middle, high);
        }
    }

    public static void swapUsingPoint(Point[] points, int i, int j) {

        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
/************************************************************************************************************/
//Right way using priority queue


public static class Point1 {
    double d;
    int index;

    public Point1(double dist, int i) {
        d = dist;
        index = i;
    }
}
    public static List<List<Integer>> nearest_neighbours(int px, int py, int k, List<List<Integer>> npoints) {

        Point1[] point = new Point1[npoints.size()];

        for(int i = 0; i < npoints.size(); i++) {
            int x1 = npoints.get(i).get(0);
            int y1 = npoints.get(i).get(1);

            double d = Math.sqrt(((long)(px - x1) * (px - x1)) + ((long) (py - y1) * (py - y1)));

            point[i] = new Point1(d, i);
        }

        PriorityQueue<Point1> pq = new PriorityQueue<>(k, (a, b) -> {
            if(b.d > a.d) {
                return 1;

            } else if(b.d < a.d) {
                return -1;

            } else {
                return 0;

            }});

        for(int i = 0; i < point.length; i++) {
            pq.offer(point[i]);

            if(pq.size() > k) {
                pq.poll();
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            Point1 p = pq.poll();

            result.add(npoints.get(p.index));
        }
        return result;

    }
//***************************************************************************************************************************************

    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};

        int[][] result = kClosestUsingMaxPriorityQueue(points, 2);

        System.out.println("K closest elements using priority queue");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + ", " + result[i][1]);
        }

        int[][] result1 = kClosestUsingQuickSelect(0, 0, points, 2);

        System.out.println("K closest elements using quick select");
        for (int i = 0; i < result1.length; i++) {
            System.out.println(result1[i][0] + ", " + result1[i][1]);
        }

        List<Integer> p = new ArrayList<>();
        p.add(3);
        p.add(4);

        List<List<Integer>> pointsList = new ArrayList<>();
        pointsList.add(p);

        p = new ArrayList<>();
        p.add(5);
        p.add(-1);

        pointsList.add(p);

        p = new ArrayList<>();
        p.add(-2);
        p.add(4);

        pointsList.add(p);

        List<List<Integer>> result2 = kClosestUsingQuickSelectList(0, 0, pointsList, 2);

        System.out.println("Using quickselect and list as input");
        for(List<Integer> point: result2) {
            System.out.println(point.get(0) + "," + point.get(1));
        }
    }
}
