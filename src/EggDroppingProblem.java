/**
 * Dynamic Programming - Egg Dropping Problem.
 *
 * Objective: There are n number of eggs and building which has k floors. Write an algorithm to find
 * the minimum number of drops required to know the floor from which if eggs is dropped, it will break.
 *
 * Note:
 *
 * 1) One trail is - dropping an egg once from the particular floor.
 * 2) If the egg does not break after dropping, will be used again.
 * 3) If egg breaks when dropped from some floor, then it will break if dropped from
 * any higher floor.
 * 4) If egg does not break when dropped from some floor, then it will not break if dropped
 * from any lower floor.
 *
 * Approach:
 *
 * N eggs, k floors
 *
 * Recursive Approach:
 *
 * Time Complexity: 2^k
 *
 * Try dropping egg from each floor from 1 to k and calculate the minimum number of dropping
 * needed in worst case
 *
 * Base Cases -
 *
 * Eggs = 1, Drops = floors Play safe and drop from floor 1, if egg does not break then drop from floor 2
 * and so on. so in worst case x(floor) times an egg needs to be dropped to find the solution.
 *
 * Floors = 0, Drops = 0 - No trials are required.
 *
 * Floors = 1, Drops = 1, 1 Trail is required.
 *
 * For rest of the case, if an egg is dropped from xth floor then there are only 2 outcomes
 * which are possible. Either egg will break or egg will not break.
 *
 * If Egg breaks - check the floors lower than x. So the problem is reduced to n - 1 eggs and
 * x - 1 floors
 *
 * If egg does not break - check the floors higher than x floors with all n eggs remaining.
 * So problem is reduced to n eggs and k - x floors.
 *
 * Recursive Equation–

 n eggs, k floors

 getDrops (n, k) – given n eggs and k floor building, minimum of drops to
 determine the floor from which egg break if dropped.

 getDrops (n, k) = 1 + Min(x = 1,2,….k) [(drops(n-1, k-1), drops(n, k-x)]

 If we look closely, we are solving many sub problems repeatedly

 Dynamic Programming: Bottoms up approach

 1) Solve it in bottom up manner, means start from the smallest sub problem possible
 (here it is 0 eggs and 0 floors) and solve it. Store the result in some temporary storage.
 2) Equation will be the same as recursion. Start solving from smallest sub problem
 and move towards the final problem. Use the temporary result being stored instead of solving
 sub problems again.

 Time Complexity: n x k
 or n^2

 resources/EggDroppingDynamicProgramming1.png
 resources/EggDroppingDynamicProgramming2.png
 */
public class EggDroppingProblem {

    private static int getMinimumDropsRecursive(int eggs, int floors) {

        //Base Case 1:
        //If floors = 0 then no drops are required or
        //floors = 1 then 1 drop is required.
        if (floors == 0 || floors == 1) {
            return floors;
        }

        //Base Case 2:
        //If only one egg is there, then drops = floors
        if (eggs == 1) {
            return floors;
        }

        int minimumDrops = Integer.MAX_VALUE;
        int tempResult;

        //Check dropping from all the floors 1 to floors k and pick the minimum among those.
        //For every drop, there are 2 scenarios -
        //a) Either egg will break
        //b) Egg will not break
        for (int i = 1; i <= floors; i++) {

            //For worst case pick the maximum among a and b
            tempResult = Math.max(getMinimumDropsRecursive(eggs - 1, i - 1),
                    getMinimumDropsRecursive(eggs, floors - i));

            minimumDrops = Math.min(tempResult, minimumDrops);
        }

        return minimumDrops + 1;
    }

    private static int getMinimumDropsDynamicProgramming(int eggs, int floors) {

        int[][] eggDrops = new int[eggs + 1][floors + 1];

        //Base Case 1:
        //If floors = 0, then drops = 0
        //If floors = 1, then drops = 1
        for (int i = 1; i <= eggs; i++) {
            eggDrops[i][0] = 0;
            eggDrops[i][1] = 1;
        }

        //Base Case 2:
        //If eggs = 1, then drops = floors
        for (int i = 1; i <= floors; i++) {
            eggDrops[1][i] = i;
        }

        for (int i = 2; i <= eggs; i++) {
            for (int j = 2; j <= floors; j++) {

                eggDrops[i][j] = Integer.MAX_VALUE;
                int tempResult;

                for (int k = 1; k <= j; k++) {

                    tempResult = 1 + Math.max(eggDrops[i - 1][k - 1], eggDrops[i][j - k]);
                    eggDrops[i][j] = Math.min(tempResult, eggDrops[i][j]);
                }
            }
        }

        //eggDrops[eggs][floors] will have the result: minimum number of drops required in worst case
        return eggDrops[eggs][floors];
    }

    public static void main(String[] args) {

        int eggs = 2;
        int floors = 10;

        System.out.println("(Recursion) Minimum number of drops required in worst case with eggs: "
                + eggs + " and floors:" + floors + " is: " + getMinimumDropsRecursive(eggs,floors));

        eggs = 2;
        floors = 100;

        System.out.printf("(DP) Minimum number of drops required in worst case with eggs: %s and " +
                "floors: %s is : %s",eggs, floors, getMinimumDropsDynamicProgramming(eggs,floors));
    }
}
