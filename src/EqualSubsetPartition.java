import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Equal Sum Subset Partition
 * Given an array s of n integers. Your task is to partition the given set s into two non-empty subsets, say s1 and s2 such
 * that sum of all elements in s1 is equal to the sum of all elements in set s2. If it is not possible to partition the array s
 * then returns a blank array else return a boolean array of size n where i (0<=i<n) element is true if it is part of s1 and
 * false if it is part of s2.
 *
 * Any valid answer will be accepted.
 *
 * Input Format:
 * If n = 3 and s = [1, 0, -1], then custom input format will be:
 * 3
 * 1
 * 0
 * -1
 *
 * Output Format:
 * 2
 * 1
 *-1
 *
 * 1
 * 0
 *
 * Constraints:
 *     1 <= n <= 100
 *     -100 <= elements in s <= 100
 *
 *  n = 6
 * s = [10, -3, 7, 2, 1, 3]
 *
 * Sample Output:
 * [True, True, False, False, False, True]
 *
 * Explanation:
 * There are multiple possible partitions to satisfy the equal subset sum condition.
 *
 *     s1 = [ 10 , -3 , 3 ] and s2 = [ 7 , 2 , 1 ] (Sample output)
 *     s1 = [ 7 , 2 , 1 ] and s2 = [ 10 , -3 , 3 ]
 *
 *     s1 = [10] and s2 = [-3, 3, 7, 2, 1]
 *     s1 = [-3, 3, 7, 2, 1] and s2 = [10]
 *
 *     s1 = [10, -3, 2, 1] and s2 = [7, 3]
 *     s1 = [7, 3] and s2 = [10, -3, 2, 1]
 *
 * In all the above partitions, the sum of s1 is 10 and the sum of s2 is also 10.
 *
 *  The only twist here is it contains negatives as well as positives so our target sum can be 0 when we start.
 *  For that we keep extra variable length to make sure length is not 0 and also not equal to size of s in which case one subset is empty
 *  and other has all the elements.
 *
 *  Dp Approach:
 *
 *  1) Since there are two params changing i and sum  and sum can be -ve or + ve
 *  2) So using array of map
 *  3) The range for the sum will be add all negatives and add all positives.
 *  4) For generating the result, back track the dp table and fill all included element with true.
 *  5) For this you have to compare the current i and targetSum with excluded (i + 1 and targetSum)
 *  if that's false means that element is not excluded and it should be included.
 *
 *  Time Complexity: O(n * Range of Sum)
 *  Space Complexity: O(n * Range of Sum)
 *
 *  resources/EqualSubsetPartitionRecursion.jpg
 *  resources/EqualSubsetPartitionDp.jpg
 *
 *  *********************************************************************************************
 *  Solution
 *
 * 1) brute_force_solution:
 * This partition problem simply reduces to find a subset of the given array such that the sum of the
 * given array is equal to sum/2, where the sum is the total sum of elements of the given array.
 * Also, if the value of the sum is odd then we cannot partition it into two equal subsets.
 * So, in case the value of the sum is odd we simply return an empty array.
 *
 * Now, In this approach, we iterate on all possible combinations of subsets of the given array and
 * check if the current subset sums to sum/2. If we are able to find once such subset then we declare
 * this subset as s1 and rest of other remaining elements of the subset as s2 and
 * return the denomination array as specified in the output section on the problem statement.
 *
 * Time Complexity:
 * O(n*2^n) where n is the number of elements in the given input array.
 * As we are iterating on all possible subsets i.e. 2^n subsets for an array of size n.
 * Hence, we are doing O(2^n) iterations and then for each subset, we are computing its sum.
 * To do this we need to iterate on each element of the subset that takes O(n) time of each individual subset.
 * Hence, the total time complexity becomes O(2^n) * O(n) ~ O(n*2^n).
 *
 * Auxiliary Space Used:
 * O(n) where n is the number of elements in the given input array.
 * To generate all partitions we are recursively backtracking on all indexes of the array.
 * So, at any point of time the recursion stack will occupy max O(n) stack size.
 *
 * Space Complexity:
 * O(n) where n is the number of elements in the given input array.
 *
 * Auxiliary space + the Input Space i.e. O(n) + O(n) →  O(n).
 *
 * 2) optimal_solution:
 * Description:
 * As discussed in the brute_force approach we have simply reduced this problem to a subset sum problem
 * such that given an array s and we need to first check if a subset exists with the subset sum of sum/2.
 * If it exists then we need to separate that subset from the rest of elements of the array.
 * We will be using dynamic programming to solve this problem.
 * Our first aim will be to check if a subset with sum sum/2 exists or not.
 * To do so, we will be maintaining a 2D DP state as following :
 *
 * Boolean state(idx, sum).
 *
 * Here, state(idx, sum) tell us if it is possible to get a subset sum of the sum
 * provided the elements from idx to n - 1 of the given array.
 *
 * Now, our state transition will look like below:
 * state(idx, sum) = state(idx+1, sum) | state(idx + 1, sum – s[idx])
 *
 * So, using the above state transition we will populate all our DP states.
 * Now, we simply check the value of state(0, sum/2) (assumed 0-based array index).
 * If it is true then it is possible to partition the given array and if it is false then once
 * again we return an empty array.
 *
 * Now, to get the partition we start a top-down lookup on our DP states.
 * We start from the state(i, sum/2). If this state is true and state(i + 1, sum/2) is false this
 * means s[i] contributed in the subset sum and if it is false we go to state(i + 1, sum/2 - s[i])
 * to identify our contributors of the subset sum of sum/2. We repeat this reverse DP transition
 * until the point we reach the last index of the array or till the point,
 * the required sum becomes 0. While doing these reverse DP transitions we also mark the
 * contributed elements as s1 subset elements and rest of the array as s2 elements.
 *
 * Because the elements in our array can also be negative and hence we use a hash-based container
 * like unordered_map in to overcome this problem of negative indexing.
 *
 * Time Complexity:
 * O(n*range_sum) since this is a pseudo-polynomial time problem where n is the number of elements
 * in the given input array and range_sum is the absolute difference between the maximum sum and
 * the minimum sum possible in the given input array s.
 *
 * As we are visiting all the DP states i.e. n*range_sum, hence we will be doing n*range_sum iterations
 * and for each state, we are doing O(1) amount of work and also because of memorization each state
 * is being visited once. Hence, the total time complexity of this solution is O(n*range_sum).
 *
 * Auxiliary Space Used:
 * O(n*range_sum) where n is the number of elements in the given input array and range_sum is the
 * absolute difference between the maximum sum and the minimum sum possible in the given input array s.
 *
 * Since we are using an auxiliary container of size n*range_sum to store the DP states.
 * So, the auxiliary space complexity is O(n*range_sum).
 *
 * Space Complexity:
 * O(n*range_sum) where n is the number of elements in the given input array and
 * range_sum is the absolute difference between the maximum sum and the minimum sum
 * possible in the given input array s.
 *
 * Auxiliary space + the Input space i.e. O(n*range_sum) + O(n) →  O(n*range_sum).
 */
public class EqualSubsetPartition {

    private static List<Boolean> equalSubsetSumPartition(List<Integer> s) {

        int sum = computeSum(s);

        if(sum % 2 != 0) {
            return new ArrayList<Boolean>();
        }

        boolean[] result = new boolean[s.size()];

        if(equalSubsetSumPartitionRecursion(s, 0, 0, result, sum/2)) { //If we are able to find partition s then return result

            List<Boolean> resultList = new ArrayList<>();
            for(int i = 0; i < result.length; i++) {
                resultList.add(result[i]);
            }
            return resultList;

        } else {
            return new ArrayList<Boolean>();
        }
    }

    private static boolean equalSubsetSumPartitionRecursion(List<Integer> s, int i, int len, boolean[] result, int targetSum) {
        //Base Case
        if(targetSum == 0 && len != 0 && len != s.size()) {
            return true;
        }

        //No more elements left
        if(i == s.size()) {
            return false;
        }

        boolean include = equalSubsetSumPartitionRecursion(s, i + 1, len + 1, result, targetSum - s.get(i));
        if(include) {
            result[i] = true;
        }

        //Include or exclude
        return include || equalSubsetSumPartitionRecursion(s, i + 1, len, result, targetSum);
    }

    private static int computeSum(List<Integer> s) {
        int sum = 0;

        for(int element: s) {
            sum = sum + element;
        }
        return sum;
    }

    private static List<Boolean> equalSubsetSumPartitionDp(List<Integer> s) {
        //Calculate the -ve and +ve range
        int negativeSum = 0;
        int positiveSum = 0;

        for(int val: s) {
            if(val < 0) {
                negativeSum = negativeSum + val;
            } else {
                positiveSum = positiveSum + val;
            }
        }

        List<Boolean> result = new ArrayList<>();
        int n = s.size();

        if((negativeSum + positiveSum) % 2 != 0) {
            return result;
        }

        //Identify the dp table. In recursion two params changing, i and sum
        //i range from 0 to n and sum range from -ve range to +ve range
        Map<Integer, Boolean> dp[] = new HashMap[n];

        for(int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();

            for(int sum = negativeSum; sum <= positiveSum; sum++) {
                dp[i].put(sum, false);
            }
        }

        //Initialize the dp table. Base case: It's always possible to generate the sum of s[n - 1] using
        //dp[n - 1].get(s[n- 1]) so set that to true
        dp[n - 1].put(s.get(n - 1), true);

        //Traversal direction i = n - 2 to 0
        // sum = -ve to +ve range
        for(int i = n - 2; i >= 0; i--) {
            for(int sum = negativeSum; sum <= positiveSum; sum++) {

                //Exclude
                dp[i].put(sum, dp[i + 1].get(sum));

                //If sum == s.get(i) then just taking ith value is sufficient
                if(sum == s.get(i)) {
                    dp[i].put(sum, true);
                } else if (sum - s.get(i) <= positiveSum && sum - s.get(i) >= negativeSum) { //>= 0
                    //Exclude || Include
                    dp[i].put(sum, dp[i].get(sum) || dp[i + 1].get(sum - s.get(i)));
                }
            }
        }

        int targetSum = (positiveSum + negativeSum)/2;

        //Recursion started at i = 0
        if (dp[0].get(targetSum) == false) {
            return result; //Partition not possible
        }

        //Set it to all false
        for (int i = 0; i < n; i++) {
            result.add(false);
        }

        int count = 0;
        int i = 0;

        while(i <= n - 1) {
            if(i != n - 1) {
                //Include if false
                if(dp[i].get(targetSum) && dp[i + 1].get(targetSum) == false) {
                    count++;
                    result.set(i, true);
                    targetSum = targetSum - s.get(i);
                    if(targetSum == 0) { //rest all will be excluded
                        break;
                    }
                }
            } else { //last element - If we reached here then sum is still not 0, include the last element
                count++;
                result.set(i, true);
            }
            i++;
        }

        // if all elements are included in S1
        // All elements will be in S1 if total_sum = 0
        // case when s = [-2,2]
        // partition is not possible in this case
        if(count == 0 || count == n) {
            return new ArrayList<Boolean>();
        } else {
            return result;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(1);
        s.add(0);
        s.add(-1);

        System.out.println("Equal subset partition " + equalSubsetSumPartition(s));
        System.out.println("Equal subset partition using dp " + equalSubsetSumPartitionDp(s));

        s = new ArrayList<>();
        s.add(10);
        s.add(-3);
        s.add(7);
        s.add(2);
        s.add(1);
        s.add(3);

        System.out.println("Equal subset partition " + equalSubsetSumPartition(s));
        System.out.println("Equal subset partition using Dp" + equalSubsetSumPartitionDp(s));

        s = new ArrayList<>();
        s.add(1);
        s.add(-1);

        System.out.println("Equal subset partition using Recursion " + equalSubsetSumPartition(s));
        System.out.println("Equal subset partition using Dp " + equalSubsetSumPartitionDp(s));

        s = new ArrayList<>();
        s.add(1);
        s.add(1);

        System.out.println("Equal subset partition using Recursion " + equalSubsetSumPartition(s));
        System.out.println("Equal subset partition using Dp " + equalSubsetSumPartitionDp(s));

        s = new ArrayList<>();
        s.add(0);
        s.add(0);

        System.out.println("Equal subset partition using Recursion " + equalSubsetSumPartition(s));
        System.out.println("Equal subset partition using Dp " + equalSubsetSumPartitionDp(s));
    }
}
