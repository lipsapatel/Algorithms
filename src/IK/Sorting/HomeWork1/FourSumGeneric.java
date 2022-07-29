package IK.Sorting.HomeWork1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Given array, find the 4 numbers whose sum = target.
 *
 * Approach:
 * 1) The generic approach which combines recursion and two pointers can be done for 4 sum, 5 sum, 6 sum and so on.
 * 2) First sort the array
 * 3) Define recursive function (a, target, start, k)
 * 4) There are two base cases
 * 5) Base Case 1: If start + k > a.size() or a.get(start) * k > target or a.get(a.size() - 1) < target then return empty result arraylist
 * 6) Base Case 2: If k == 2, then call twoSum approach
 * 7) Recursive Case: Iterate from start to a.size() - 1
 * 8) First check for duplicates
 * 9) Make recursive call (a, target - a.get(i), i + 1, k - 1)
 * 10) Iterate the array list returned and append a.get(i)
 * 11) Add that to result arraylist
 * 12) Return result arraylist
 *
 * TC: O(n^k - 1)  = O(n^3) for four sum
 * SC: O(k) + O(n^4) where O(k) is recursive call stack space and n^4 possible quadruples
 */

public class FourSumGeneric {

    private static ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> a, Integer target) {
        //Sort Array
        Collections.sort(a);

        return fourSumGeneric(a, target, 0, 4);
    }

    private static ArrayList<ArrayList<Integer>> fourSumGeneric(ArrayList<Integer> a, int target, int start, int k) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        //Base Case 1
        if(start + k > a.size() || a.get(start) * k > target || a.get(a.size() - 1) * k < target) {
            return result;
        }

        //Base Case 2
        if(k == 2) {
            return twoSum(a, target, start);
        }

        //Recursive Case
        for(int i = start; i < a.size(); i++) {

            //Duplicates
            if(i > start && a.get(i).equals(a.get(i - 1))) {
                continue;
            }

            ArrayList<ArrayList<Integer>> subResult = fourSumGeneric(a, target - a.get(i), i + 1, k - 1);

            for(ArrayList<Integer> list: subResult) {
                list.add(a.get(i));
                result.add(list);
            }
        }
        return result;
    }

    private static ArrayList<ArrayList<Integer>> twoSum(ArrayList<Integer> a, int target, int start) {
        int low = start;
        int high = a.size() - 1;

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        while(low < high) {

            int sum = a.get(low) + a.get(high);

            if(target == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(a.get(low));
                list.add(a.get(high));
                result.add(list);

                low++;
                high--;
            } else if(sum > target) {
                high--;
            } else {
                low++;
            }

            //Duplicates
            if(low > start) {
                while(low <= high && a.get(low).equals(a.get(low - 1))) {
                    low++;
                }
            }

            if(high < a.size() - 1) {
                while(low <= high && a.get(high).equals(a.get(high + 1))) {
                    high--;
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
