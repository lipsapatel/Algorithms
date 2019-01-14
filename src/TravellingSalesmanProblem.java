import java.util.Arrays;

/**
 * Travelling Salesman problem
 * Given a set of cities and distance between every pair of cities, the problem is to find the shortest possible route
 * that visits every city exactly once and returns to the starting point.
 *
 * Permutation of cities
 * Time Complexity: O(n!)
 *
 * c[] = {0, 1, 2, 3}
 * d[][] = {{0, 10, 15, 20}
 *          {10, 0, 35, 25},
 *          {15, 35, 0, 30},
 *          {20, 25, 30, 0}}
 *
 * The path from 0 - 1 - 3- 2 - 0 = 10 + 25 + 30 + 15 = 80
 */
public class TravellingSalesmanProblem {

    private static int travellingSalesman(int[] c, int[][] d, int left, int minDistance) {

        //Base Case
        if (left == c.length) {

            int distance = 0;
            for (int i = 0; i < c.length; i++) {

                if (i != c.length - 1) {
                    distance += d[c[i]][c[i + 1]];
                } else {
                    distance += d[c[i]][c[0]];
                }
            }

            if (distance < minDistance) {
                System.out.println(Arrays.toString(c));
                minDistance = distance;
            }
            return minDistance;
        }

        for (int i = left; i < c.length; i++) {
            swap(i, left, c);
            minDistance = travellingSalesman(c, d, left + 1, minDistance); //Keep increasing left for different position
            swap(i, left, c); //This is for backtrack
        }
        return minDistance;
    }

    private static void swap(int i, int left, int[] A) {
        int temp = A[i];
        A[i] = A[left];
        A[left] = temp;
    }

    public static void main(String[] args) {
        int[] c = {0, 1, 2, 3};
        int[][] d = {{0, 10, 15, 20},
                     {10, 0, 35, 25},
                     {15, 35, 0, 30},
                     {20, 25, 30, 0}};

        System.out.println(travellingSalesman(c, d, 0, Integer.MAX_VALUE));
    }

}
