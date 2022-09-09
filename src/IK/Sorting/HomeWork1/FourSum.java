package IK.Sorting.HomeWork1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * We provided four solutions.
 * Throughout this editorial, we will refer to the input array as arr, its size as n and the target sum as target.
 *
 * brute_force_solution.cpp
 * A brute force solution that most of you might come up with initially is to run 4 nested loops and check the sum of each quadruple present in the array. If the quadruple sums up to target, then we will append it to the result.
 * But note that, in the case when duplicate elements are present in the array, this simple approach might also generate some duplicate quadruples. One way to deal with this is to maintain a set of quadruples and insert each quadruple in the sorted form in this set. Finally, we can simply extract the quadruples from this set, insert them into the result and return it. However, this would cost us both extra space and time.
 * In this solution, we will discuss an approach that would not require the use of a set to avoid duplications.
 *
 * We will initially sort the array so that all of the duplicate occurrences of any element come adjacent to each other. The reason for this will become more clear as we move ahead.
 * Next, we will run 4 nested loops where the purpose of each of the loop is described below:
 *
 * The outermost loop will fix an element arr[i] so that the 3 nested inner loops could find the triplets with sum equal to (target - arr[i]).
 * The second loop will fix an element arr[j] ahead of the index i so that the 2 nested inner loops could find the pairs with sum equal to (target - arr[i] - arr[j]).
 * The third loop will fix an element arr[k] ahead of the index j so that the next inner loop could find the numbers equal to (target - arr[i] - arr[j] - arr[k]).
 * The innermost loop will find an element arr[l] ahead of the index k such that (arr[i] + arr[j] + arr[k] + arr[l]) equals target.
 * Now, an observation here that will help us avoid the duplicate quadruples is that, if the outermost loop has already considered the possibility of arr[i] being the first element of any quadruple, then any duplicate occurrences of arr[i] need not be considered again for being the first element of any other quadruple. Let us understand this with an example.
 * Say, we have arr = [0, 0, 1, 2, 3, 4, 5] and target = 10.
 * Now, if we consider the possibility of arr[0] being the first element of a quadruple, then the inner three loops will get us all the unique triplets with sum equal to (10 - arr[0]) = 10.
 * All such unique triplets are: [1, 4, 5], [2, 3, 5].
 * Appending arr[0] = 0 at the beginning of all these triplets, we will get the quadruples having the first element equal to arr[0]. All such quadruples are: [0, 1, 4, 5], [0, 2, 3, 5].
 *
 * Now, say we are considering the possibility of arr[1] being the first element of a quadruple. We will first call the inner three loops to get us all of the unique triplets with sum equal to (10 - arr[1]) = 10.
 * Now, since arr[0] equals arr[1], the inner 3 loops will again return the following triplets: [1, 4, 5] and [2, 3, 5]. Now, if we will append arr[1] = 0 at the beginning of these triplets, it will get us the same quadruples as we got above.
 *
 * The above analysis leads us to a lesson that if an element arr[i] has already been considered for being the first element of a quadruple, then any duplicate occurrence of arr[i] should not be considered for being the first element of a quadruple.
 * Similarly, if an element arr[j] has already been considered for being the second element of a quadruple, then any duplicate occurrence of arr[j] should not be considered for being the second element of a quadruple.
 * Similarly, the same can be said for the third and fourth element of a quadruple.
 *
 * Time Complexity
 * O(n^4).
 *
 * We will run 4 nested loops on the input array of size n.
 *
 * Auxiliary Space Used
 * O(1).
 *
 * We used only a constant amount of extra space.
 *
 * Space Complexity
 * O(n^4).
 *
 * Space used for input: O(n).
 * Auxiliary space used: O(1).
 * Space used for output: O(n^4) (In the worst case, there can be O(n^4) number of output quadruples each of size 4).
 * So, total space complexity is: O(n^4).
 *
 * two_pointer_solution.cpp
 * Given a sorted array nums of size m, we can find all the unique pairs with sum equal to any number k in O(m) amount of time. The approach for the same is given below:
 *
 * Initialize the iterators low = (starting index of nums) and high = (ending index of nums).
 * Initiate a process that runs until the condition low < high is satisfied. The approach followed by the process is:
 * If nums[low] + nums[high] equals k: It means we have found a pair with the required sum. Therefore, we will store the pair, increment low and decrement high.
 * If nums[low] + nums[high] is greater than k: In this case, we are in search of a sum that is less than the current sum. To resolve this issue, we will decrement high. Since nums is sorted, decrementing high will give us a sum less than or equal to the current sum.
 * If nums[low] + nums[high] is less than k: In this case, we are in search of a sum that is greater than the current sum. To resolve this issue, we will increment low. Since nums is sorted, incrementing low will give us a sum greater than or equal to the current sum.
 * Next, we need to make sure that the duplicate pairs do not get inserted to our result. To do this, we will skip the duplicate occurrences of arr[low] and arr[high] (if any).
 * In the previous solution, the inner two loops were responsible to get us all the unique pairs with sum equal to (target - arr[k] - arr[l]) in O(n^2) amount of time.
 * In this solution, we will replace those two inner loops with the two-pointer based approach discussed above.
 *
 * Time Complexity
 * O(n3).
 *
 * We have two outer nested loops and then an O(n) time-taking two-pointer based approach. Therefore, the total time complexity is O(n^2 * n).
 * Since the output array will have size equal to O(n^4), printing or generating it will take O(n^4) amount of time. Apart from that, the time taken by the four_sum function will be O(n^3).
 *
 * Auxiliary Space Used
 * O(1).
 *
 * We used only a constant amount of extra space.
 *
 * Space Complexity
 * O(n^4).
 *
 * Space used for input: O(n).
 * Auxiliary space used: O(1).
 * Space used for output: O(n^4) (In the worst case, there can be O(n^4) number of output quadruples each of size 4).
 * So, total space complexity is: O(n^4).
 *
 * two_pointer_generic_k_sum_solution.cpp
 * If you are asked the “Four Sum” problem in a technical interview, it is very much possible that the interviewer asks you to extend your solution to “Five Sum”, “Six Sum” or a generic “K Sum” solution.
 * Here, we will discuss a generalized implementation of this problem.
 * We will follow a very similar approach that we followed in the two-pointer based approach above.
 * We will initially sort the array and call the function called k_sum with the value of k equal to 4 to get us all the unique sets of size 4 with sum of the values equal to the given target.
 * The approach that we will follow for the k_sum function is described below:
 *
 * This function will take the input array arr, the current_target, the starting index start of arr and the value of k as its inputs. The current_target will initially be equal to the given target value and the start will be equal to 0. The function will return all the distinct sets of size k such that they sum up to the current_target.
 *
 * We will then check for the following base cases:
 *
 * If start + k > n: It means that there are not enough elements present in the array to form a set of size k. Therefore, we will return the empty result.
 * If arr[start] * k > current_target or arr[n - 1] * k < current_target: It means that it is not possible to have a set of size k that sums up to the current_target (since the input array is sorted). Therefore, we will return the empty result in this case as well.
 * If k equals 2: In this case, we will follow a similar two-pointer based approach that we followed in the previous solution. For this, we will call a separate function called two_sum which will take the array arr, index start and the current_target as its input parameters and will return all the distinct pairs in arr[start ... n - 1] which sum up to the current_target.
 * If all of the above base-conditions fail, we will continue with the current function call. The approach for the same is described in the points below.
 *
 * Iterate i through the array arr starting from the index start and do the following:
 *
 * If the current element is the same as the one before it, we will skip it. This is to avoid the duplicate sets as seen in the previous solutions.
 * Recursively call k_sum with: current_target = current_target - arr[i], start = i + 1 and k = k - 1. This will bring us all of the unique sets of size k - 1 in arr[i + 1 ... n - 1] with sum equal to (current_target - arr[i]). Let us store this in sub_result.
 * Next, we will iterate through all of the arrays present in the sub_result and will append arr[i] at the end of each one of it.
 * Finally, all of the arrays present in the sub_result now have the sum equal to the current_target and have the size equal to k. Therefore, we will push all of these arrays in our final resultant array called result.
 * The termination of the above loops means that we have considered the possibility of each element present in the array for being the first element of the set of size k. Therefore, we will finally return the result.
 *
 * Time Complexity
 * O(n^k - 1) = O(n^3).
 *
 * We have k - 2 nested loops and finally, two_sum will take O(n) time.
 * Since the output array will have size equal to O(n^k) = O(n^4), printing it will take O(n^4) amount of time. However, the time taken by the four_sum function will be O(n^3).
 *
 * Auxiliary Space Used
 * O(n^k - 1) = O(n^3).
 *
 * Recursive stack size in the worst-case: O(k) = O(4) = O(1).
 * Size of the sub_result for the function call with k = 4: O(n^3).
 *
 * Space Complexity
 * O(n^4)
 *
 * Space used for input: O(n).
 * Auxiliary space used: O(n^3).
 * Space used for output: O(n^4) (In the worst case, there can be O(n^4) number of output quadruples each of size 4).
 * So, total space complexity is: O(n^4).
 *
 * ************************************************************************************************************************************
 *
 * There are 2 approaches to do 4 Sum
 * 1) Two Pointer approach with 2 for loop
 * 2) Generic Approach using recursion and two pointer approach
 *
 * Approach 1: Two Pointer approach with 2 for loop
 * 1) Sort Array
 * 2) There will be two outer for loops
 * 3) In each loop, check for duplicates
 * 4) Another loop for two pointer approach to find remaining two and check for duplicates
 *
 * TC:O(n^3)
 * SC: O(n^4) because In the worst case, there can be O(n^4) number of output quadruples each of size 4
 * There are n^4 combinations. For first blank there are n numbers to choose from, for second blank there are n - 1 number to choose from and so on
 * so its n * (n - 1) * (n - 2) * (n - 4)
 *
 */
public class FourSum {

    private static ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> a, Integer target) {

        Collections.sort(a);

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(int i = 0; i < a.size(); i++) {

            //Duplicates
            if(i > 0 && a.get(i).equals(a.get(i - 1))) {
                continue;
            }

            for(int j = i + 1; j < a.size(); j++) {

                //Duplicates
                if(j > i + 1 && a.get(j).equals(a.get(j - 1))) {
                    continue;
                }

                //Two Sum
                int targetSum = target - a.get(i) - a.get(j);
                int low = j + 1;
                int high = a.size() - 1;

                while(low < high) {

                    int tempSum = a.get(low) + a.get(high);

                    if(tempSum == targetSum) {

                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(a.get(i));
                        list.add(a.get(j));
                        list.add(a.get(low));
                        list.add(a.get(high));
                        result.add(list);

                        low++;
                        high--;

                    } else if(tempSum < targetSum) {
                        low++;
                    } else if(tempSum > targetSum) {
                        high--;
                    }

                    //Duplicates
                    if(low > j + 1) {
                        while(low <= high && a.get(low).equals(a.get(low - 1))) {
                            low++;
                        }
                    }

                    //Duplicates
                    if(high < a.size() - 1) {
                        while(low <= high && a.get(high).equals(a.get(high + 1))) {
                            high--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(-1);

        System.out.println(fourSum(list, 3).toString());
    }
}
