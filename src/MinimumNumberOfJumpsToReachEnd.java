import java.util.HashMap;

/**
 * Dynamic programming - Minimum jumps to reach to end
 *
 * Given an array of non negative integers, start from the first element and reach the last
 * by jumping. The jump length can be at most the value at the current position in the array.
 * Optimum result is when you reach the goal in minimum number of jumps
 *
 * Example:
 *
 * Given array A = {2,3,1,1,4}
 possible ways to reach the end (index list)
 i) 0,2,3,4 (jump 2 to index 2, then jump 1 to index 3 then 1 to index 4)
 ii) 0,1,4 (jump 1 to index 1, then jump 3 to index 4)

 Since second solution has only 2 jumps it is the optimum result.

 Approach:

 Recursion

 1) Start from index 0. Try out each option.
 2) If the value at current index is k, then try out all the jumps from 1 to k.
 3) Each time you jump to new index, follow the step 2 recursively.
 4) Base cases are:

        1) Check if your start index and end index are same then no further jumps are
            required. Return 0
        2) If the value on the start index is 0, then we can pass through that index,
            return Integer.MAX_VALUE
        3) Calculate the remaining length in the array which is yet to be travelled.
            If remaining length is less than the value at the present index then
            we do not need further recursion, we can reach to the destination in one
            jump so return 1.

 Dynamic Programming

 If we look closely we are solving many sub problems recursively. Here we will use
 Top-Down approach of dynamic programming. We will use HashMap to store the sub
 problems results and whenever we make recursive call, first check if the sub problem
 is already solved, if yes then use it.

 */
public class MinimumNumberOfJumpsToReachEnd {

    private static int findJumpsUsingRecursion(int[] a, int start) {

        //If reached to the end, we are done
        if (start == a.length - 1) {
            return 0;
        }

        int size = a.length;
        int remainingLength = size - start;
        if (remainingLength <= a[start]) {
            return 1; //means no further recursion is required
        }

        if (a[start] == 0) {
            System.out.println("Cannot move further");
            return Integer.MAX_VALUE;
        }

        int jumps = Integer.MAX_VALUE;

        for (int i = 1; i <= a[start]; i++) {
            int temp = findJumpsUsingRecursion(a, start + i);

            if (temp != Integer.MAX_VALUE) {
                //Check if the path is not blocked.
                jumps = Math.min(jumps, temp + 1);
            } else {
                //ignore cannot pass through 0
            }
        }
        return jumps;
    }

    private static HashMap<Integer, Integer> map = new HashMap<>();

    private static int findJumpsUsingDP(int[] a, int start) {

        //If we reached to the end, we are done
        if (start == a.length - 1) {
            return 0;
        }

        if (map.containsKey(start)) {
            return map.get(start);
        }

        int size = a.length;
        int remainingLength = size - start;
        if (remainingLength <= a[start]) {
            //no further recursion is required
            return 1;
        }

        if (a[start] == 0) {
            //Cannot move further
            return Integer.MAX_VALUE;
        }

        int jumps = Integer.MAX_VALUE;
        for (int i = 1; i <= a[start]; i++) {
            int temp = findJumpsUsingDP(a, start + i);

            if (temp != Integer.MAX_VALUE) {
                //Check if we are not blocked
                jumps = Math.min(jumps, temp + 1);
            } else {
                //igonore cannot pass through 0
            }
        }
        map.put(start, jumps);
        return jumps;
    }
    public static void main(String[] args) {

        int[] a = {1, 3, 4, 8, 9};
        System.out.println("Minimum jumps required: " + findJumpsUsingRecursion(a, 0));

        int[] a1 = {1, 3, 5, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 4};
        System.out.println("Minimum jumps required using DP: " + findJumpsUsingDP(a1, 0));
    }
}
