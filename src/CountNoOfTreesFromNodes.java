/**
 * Given number of nodes, count how many different binary tree can be constructed.
 *
 * Approach
 *
 * 1) If n = 0, number of trees = 1
 * 2) If n = 1, number of trees = 1
 * 3) Take one as root, then try out different options from 0 to n - 1 at left and right
 * 4) Multiply the left and right ways and add that to the count
 *
 * Time complexity : Degree = 2n and height = n = O(2n ^ n)
 * n keeps decreasing so it will be approx n!
 *
 * resources/CountNoOfTreeFromNodes.png
 * resources/CountNoOfTreesFromNodes1.png
 */
public class CountNoOfTreesFromNodes {

    private static int countBinaryTrees(int n) {

        //Base Case
        if (n <= 1) {
            return 1;
        } else {

            int count = 0;

            //consider one node as root and try different options for left and right
            for (int i = 0; i < n; i++) {

                int leftWays = countBinaryTrees(i);
                int rightWays = countBinaryTrees(n - i - 1); //1 is for root

                count += leftWays * rightWays;
            }
            return count;
        }
    }

    public static void main(String[] args) {

        System.out.println("The number of binary trees: " + countBinaryTrees(4));
    }
}
