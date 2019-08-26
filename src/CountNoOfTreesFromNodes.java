import java.util.Arrays;

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
 * Space Complexity: O(n)  - Recursion Call stack space
 *
 * resources/CountNoOfTreeFromNodes.png
 * resources/CountNoOfTreesFromNodes1.png
 *
 * Recursion with Memorization Approach
 *
 * 1) Store the result in 1-D array
 *
 * This reduces the time complexity to O(n^2)
 * Space Complexity: O(n) - 1-D array
 *
 * Iterative solution
 *
 * TC: O(n^2)
 * SC: O(n)
 *
 * ******************************************************************************************************************************
 * How Many Binary Search Trees With n Nodes?

 Problem Statement:
 Write a function that will return the number of binary search trees that can be constructed with n nodes.
 There may be other iterative solutions, but for the purpose of this exercise, please use recursive solution.
 The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution. After that, you can write a DP solution if you want.

 Input/Output Format For The Function:
 Input Format:
 There is only one argument denoting integer n.

 Output Format:
 Return number of binary search trees that can be constructed, with n nodes.

 Input/Output Format For The Custom Input:
 Input Format:
 The first and only line of input should contain an integer n.
 If n = 3, then input should be:
 3

 Output Format:
 There will be one line, containing an integer res, denoting the result returned by solution function.
 For input n = 3, output will be:
 5

 Constraints:
 1 <= n <= 16

 Sample Test Cases:
 Sample Test Case 1:
 Sample Input 1:
 1

 Sample Output 1:
 1
 Explanation 1:
 1) root (node val = 1)

Sample Test Case 2:
 Sample Input 2:
 2

 Sample Output 2:
 2
 Explanation 2:
 1) root (node val = 2), root->left (node val = 1)
 2) root (node val = 1), root->right (node val = 2)

 Sample Test Case 3:
 Sample Input 3:
 3

 Sample Output 3:
 5

 Explanation 3:
 1) root (node val = 3), root->left (node val = 2), root->left->left (node val = 1)
 2) root (node val = 3), root->left (node val = 1), root->left->right (node val = 2)
 3) root (node val = 1), root->right (node val = 2), root->right->right (node val = 3)
 4) root (node val = 1), root->right (node val = 3), root->right->left (node val = 2)
 5) root (node val = 2), root->left (node val = 1), root->right (node val = 3)

 If you keep doing this, it will form a series called Catalan numbers. One can simply lookup the formula for Catalan numbers and write code for it. But that's not how we want to do this. We want to do this by understanding the underlying recursion. The recursion is based on tree-topology only, as you can see by examples above, contents of the nodes of the tree do not matter.

 **************************************************************************************************************************
 * Solution
 *
 * We have provided 4 solutions:
 1) Catalan Number Solution: catalan_number_solution.cpp (If you simply provide this solution in interview, without explaining the intuition, then it will not be accepted.)
 2) Recursive Solution : brute_force_solution.cpp (In this problem, we only want to practice recursion so constraints are intentionally kept low to allow this solution to pass all the test cases.)
 3) Recursive Solution With Memorization : other_solution.cpp (Much much faster solution that the above recursive solution.)
 4) Iterative Solution : optimal_solution.cpp (Logically same solution as the above recursive solution with memorization. But faster by some constant, because recursion is removed.)

 First look at the Recursive Solution then Recursive Solution With Memorization and then Iterative Solution.
 We expect you to implement recursive solution with memorization at least once.

 For Recursive Solution (brute_force_solution.cpp):
 Time Complexity:
 O(Catalan number(n)).
 This is a loose bound, tight bound is very complex to derive and explain.

 Auxiliary Space Used:
 O(n).

 Due to spaced used by function call stack, during recursive function calls.

 Space Complexity:
 O(n).
 As auxiliary space used is O(n) and input is O(1), giving O(n) + O(1) -> O(n).
 In recursive function you will note that function how_many_BSTs is called to calculate same values too many times!
 So this recalculation can be avoided by using memorization techniques. (We can use an array to store the result once it is calculated and afterwards reuse it!)
 In Recursive Solution With Memorization, a few lines of code addition will improve time complexity from O(Catalan number(n))) to O(n ^ 2) and that is too big difference!

 For n = 35, catalan number(35) = 3116285494907301262, while 35^2 = 1225. Now you see the difference of memorization!!
 Once you use memorization in your recursive implementation, time complexity, auxiliary space used and space complexity of Iterative Solution and Recursive Solution With Memorization will become same as mentioned below.

 Time Complexity:
 O(n ^ 2).

 Auxiliary Space Used:
 O(n).
 As we are using array to store the calculated results.
 Space Complexity:
 O(n).
 As auxiliary space used is O(n) and input is O(1), giving O(n) + O(1) -> O(n).

 resources/CatalanNumber.png
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
                int rightWays = countBinaryTrees(n - i - 1); //subtracting 1 is for root

                count += leftWays * rightWays;
            }
            return count;
        }
    }

    private static int countBinaryTreesRecursionWithMemorization(int n) {

        //Initialize array with -1
        int[] treeCount = new int[n + 1];
        Arrays.fill(treeCount, -1);

        return countBinaryTreesRecursion(n, treeCount);

    }

    private static int countBinaryTreesRecursion(int n, int[] treeCount) {

        //Base Case
        if (n <= 1) {
            return 1;
        }

        //If we have already calculated the solution then return that
        if (treeCount[n] != -1) {
            return treeCount[n];
        }

        //Recursive Case
        int count = 0;

        //Consider one node as root and try different options for left and right
        for (int i = 0; i < n; i++) {

            int leftWays = countBinaryTreesRecursion(i, treeCount);
            int rightWays = countBinaryTreesRecursion(n - i - 1, treeCount);

            count += leftWays * rightWays;
        }
        treeCount[n] = count;
        return count;
    }

    //TC = O(n2) SC = O(n)
    private static int countBinaryTreesIteratively(int n) {

        //1-D Array to store the result
        int[] treeCount = new int[n + 1];

        //Base Case
        treeCount[0] = 1;

        for (int currSize = 1; currSize <= n; currSize++) {

            //For current size try different left and right options
            for (int i = 0; i < currSize; i++) {

                int leftWays = treeCount[i];
                int rightWays = treeCount[currSize - i - 1];

                treeCount[currSize] += leftWays * rightWays;
            }
        }
        return treeCount[n];
    }

    public static void main(String[] args) {

        System.out.println("The number of binary trees: " + countBinaryTrees(4));
        System.out.println("The number of binary trees with recursion and memorization: " + countBinaryTreesRecursionWithMemorization(4));
        System.out.println("The number of binary trees iteratively and memorization: " + countBinaryTreesIteratively(4));
    }
}
