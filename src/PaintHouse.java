/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color,
 * and you need to cost the least. Return the minimum cost.

 The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the
 cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Example

 Example 1:

 Input: [[14,2,11],[11,14,5],[14,3,10]]
 Output: 10
 Explanation: blue green blue, 2 + 5 + 3 = 10

 Example 2:

 Input: [[1,2,3],[1,4,6]]
 Output: 3


 */
public class PaintHouse {

    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }

        int size = costs.length - 1;
        return Math.min(Math.min(costs[size][0], costs[size][1]), costs[size][2]);
    }

    public static void main(String[] args) {
        int[][] costs = {{14, 2, 11}, {11, 14, 5}, {14, 3, 10}};

        System.out.println("Minimum cost to paint houses is: " + minCost(costs));

        int[][] costs1 = {{1, 2, 3}, {1, 4, 6}};

        System.out.println("Minimum cost to paint houses is: " + minCost(costs1));
    }
}
