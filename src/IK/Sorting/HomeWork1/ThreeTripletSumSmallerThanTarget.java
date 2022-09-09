package IK.Sorting.HomeWork1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 3 Sum Smaller
 * Given a list of numbers, count the number of triplets having a sum less than a given target.
 *
 * Example One
 * {
 * "target": 4,
 * "numbers": [5, 0, -1, 3, 2]
 * }
 * Output:
 *
 * 2
 * {numbers[1], numbers[2], numbers[3]} and {numbers[1], numbers[2], numbers[4]} are the triplets having sum less than 4.
 *
 * Example Two
 * {
 * "target": 7,
 * "numbers": [2, 2, 2, 1]
 * }
 * Output:
 *
 * 4
 * {numbers[0], numbers[1], numbers[2]}, {numbers[0], numbers[1], numbers[3]}, {numbers[0], numbers[2], numbers[3]} and {numbers[1], numbers[2], numbers[3]} are the triplets having sum less than 7.
 *
 * Notes
 * The original array's indexes identify a triplet. Therefore, any two triplets will differ if they are denoted by a different triplet of indexes, even if the values present at those indexes are the same. Please observe Example Two for more details on this.
 *
 * Constraints:
 *
 * 3 <= size of the input list <= 103
 * -105 <= any element of the input list <= 105
 * -109 <= target number <= 109
 *
 * **********************************************************************************************************
 * Approach:
 *
 * First sort array
 * 1) One outer loop to choose one item.
 * 2) Two pointer approach to choose other two.
 * 3) low = i + 1 and high = a.length - 1
 * 4) while low < high
 * 5) If sum < target, than all elements between low and high can be chosen as third element. so result += high - low, low++
 * 6) If sum > target then decrement high
   7) Return result

 TC: O(n^2)
 SC: O(1)
 */
public class ThreeTripletSumSmallerThanTarget {

    private static Integer tripletsSumLessThanTarget(ArrayList<Integer> a, Integer target) {

        Collections.sort(a);
        int result = 0;

        for(int i = 0; i < a.size(); i++) {
            int low = i + 1;
            int high = a.size() - 1;

            while(low < high) {
                int sum = a.get(i) + a.get(low) + a.get(high);

                if(sum < target) {
                    //Then all values between low and high can become 3 item
                    result += high - low;
                    low++;
                } else {
                    high--; //because sum > target
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        Collections.addAll(a, 2, 2, 2, 1);

        System.out.println("The number of triplets whose sum is less than target are " + tripletsSumLessThanTarget(a, 7));
    }
}
