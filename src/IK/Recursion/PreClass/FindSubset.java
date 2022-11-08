package IK.Recursion.PreClass;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Find subsets of given string.
 * For Example Subset of "AB"
 * ""
 * A
 * B
 * AB
 *
 * Make recursive call which includes A.
 * Make recursive call which does not include A
 *
 * Degree = 2 because we are making 2 decisions at each node
 * Height = n
 * Time Complexity: 2 ^ n
 * Space Complexity: O(n) Auxillary space
 *
 * Subset problems are inclusion/exclusion.
 *
 * resources/IK.Recursion.PreClass.FindSubset.png
 * resources/FindSubsetUsingArray.png
 *
 * ********************************************************************************************************
 *
 * Generate All Subsets Of A Set
 Problem Statement:
 Given a set (in form of string s containing only distinct lowercase letters ('a' - 'z')), you have to generate ALL possible subsets of it .

 Note that:
 empty set is always a subset of any set.
 whole set s should also be considered as its subset here.
 The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution. After that, you can write a DP solution if you want.

 Input/Output Format For The Function:
 Input Format:
 There is only one argument denoting string s.

 Output Format:
 Return array of strings res, containing ALL possible subsets of given string.
 You need not to worry about order of strings in your output array. E.g. s = "a", arrays ["", "a"] and ["a", ""]  both will be accepted.
 Order of characters in any subset must be same as in the input string. For s = "xy", array ["", "x", "y", "xy"] will be accepted, but ["", "x", "y", "yx"] will not be accepted.

 Input/Output Format For The Custom Input:
 Input Format:
 The first and only line of input should contain a string s, denoting the input string.
 If s = “xy”, then input should be:
 xy

 Output Format:
 Let’s denote the size of res as m, where res is the resultant array of strings returned by the solution function.
Then, there will be m lines of output, where ith line contains string at index i of res.

 For input s = “xy”, output will be:
 ----------- START of output -----------
 x
 y
 xy
 ----------- END of output ---------------
 (Note that the first line of output is an empty line, corresponding to empty set [“”].)
 Constraints:
 0 <= |s| <= 20
 s only contains distinct lowercase alphabetical letters ('a' - 'z').
 Sample Test Cases:
 Sample Input:
 "xy"
 Sample Output:
 ["", "x", "y", "xy"]

 Notes:
 Suggested time in interview: 20 minutes
 The “Suggested Time” is the time expected to complete this question during a real-life interview, not now in homework i.e.
 For the first attempt of a given homework problem, the focus should be to understand what the problem is asking, what approach you are using, coding it,
 as well as identifying any gaps that you can discuss during a TC session. Take your time, but limit yourself to 2 one hour sessions for most problems.
 **************************************************************************************************************
 *
 * We have provided 2 solutions:
 1) Recursive Solution : other_solution.cpp.
 2) Iterative Solution : optimal_solution.cpp.

 Have a look at both the solutions.
 Both solutions are valid, but recursive solution is slightly slower because of function calls and variable passing.
 Also you should observe that number of subsets will always be power of 2.
 If size of set is n, then number of subsets will always be 2^n.

 Time Complexity:
 O(2^n * n).
 As we will generate 2^n strings of length O(n).

 Auxiliary Space Used:
 O(2^n * n).
 As we will store 2^n strings of length O(n) in output array to be returned.
 Space Complexity:
 O(2^n * n).
 As auxiliary space used is O(2^n * n) and input is O(n) hence O(2^n * n) + O(n) -> O(2^n * n).
 */
public class FindSubset {

    private static void findSubsets(String soFar, String rest) {

        if (rest.isEmpty()) {
            System.out.println("Printing " + soFar);
        } else {

            //include A
            findSubsets(soFar + rest.charAt(0), rest.substring(1));

            //Does not include A
            findSubsets(soFar, rest.substring(1));
        }
    }

    //Time Complexity: O(2 ^ n) * n (for printing)
    //Space Complexity: O(n)
    //Build the subset as you go down
    //Top Down approach.
    //All recursions are top-down approach.
    //Best data structure to use is stack
    //The array implementation below for subsetSoFar is array implementation of stack
    private static void findSubsetsArray(char[] array, int i, char[] subsetSoFar, int j) {

        if (i == array.length) { //Base case
            for (int k = 0; k < j; k++) {
                System.out.print(subsetSoFar[k]); //Print them as you get it.
            }
            System.out.println();
        } else { //Recursive case

            //Don't include A
            findSubsetsArray(array, i + 1, subsetSoFar, j);

            //Include A
            subsetSoFar[j] = array[i];
            findSubsetsArray(array, i + 1, subsetSoFar, j + 1);
        }
    }

    //Print Subsets of certain size
    private static void findSubsetOfCertainSize(char[] array, int i, char[] subsetSoFar, int j, int k) {
       //We need to have this case first because we could have subset of size 2 which will have both the condition true and if we have
        //base case first then it will not print the partial solution because we will return.
        //This will backtrack early and prune the tree early
        //The TC: O(n*2^n)
        //SC: O(n)
        if (j == k) {
            for (int x = 0; x < j; x++) {
                System.out.print(subsetSoFar[x]);
            }
            System.out.println();
            return;
        }
        //Base Case
        if (i == array.length) {
            return;
        }

        //Recursive Case

        //Don't include A
        findSubsetOfCertainSize(array, i + 1, subsetSoFar, j, k);

        //Include A
        subsetSoFar[j] = array[i];
        findSubsetOfCertainSize(array, i + 1, subsetSoFar, j + 1, k);
    }

    private static String[] generate_all_subsets(String s) {

        List<String> result = new ArrayList<>();

        generateAllSubsets(s, "", result);

        return result.toArray(new String[0]);
    }

    private static void generateAllSubsets(String rest, String soFar, List<String> result) {

        //Base Case
        if (rest.isEmpty()) {
            result.add(soFar);
        } else { //Recursive Case

            //Not include
            generateAllSubsets(rest.substring(1), soFar, result);

            //Include
            generateAllSubsets(rest.substring(1), soFar + rest.charAt(0), result);
        }
    }

    /**
     * Iterative solution to generate all subsets
     * Faster than recursive solution because there's not call stack
     * TC: O(2^n)
     * SC: O(2^n)
     */
    private static String[] generate_all_subsetsIterative(String s) {

        List<String> result = new ArrayList<>();
        result.add("");

        /*
        Suppose s = "xyz".
        Now try to find pattern in following steps (think how any step is related to previous step!):
        - [""]
        - ["", "x"]
        - ["", "x", "y", "xy"]
        - ["", "x", "y", "xy", "z", "xz", "yz", "xyz"]
        Let me explain what we have done in last step.
        First take array from previous step, that is ["", "x", "y", "xy"], append 'z' to each string,
        that is ["z", "xz", "yz", "xyz"], now merge it with array in previous step!
        */
        for (int i = 0; i < s.length(); i++) {
            int oldArrayLength = result.size();
            for (int j = 0; j < oldArrayLength; j++) {

                result.add(result.get(j) + s.charAt(i));
            }
        }
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String input = "AB";

        findSubsets("", input);

        char[] array = {'a', 'b', 'c'};
        findSubsetsArray(array, 0, new char[array.length], 0);

        System.out.println("Print of certain size: ");
        findSubsetOfCertainSize(array, 0, new char[array.length], 0, 2);

        System.out.println("All subsets " + Arrays.toString(generate_all_subsets(input)));

        System.out.println("All subsets iterative " + Arrays.toString(generate_all_subsetsIterative(input)));
    }
}
