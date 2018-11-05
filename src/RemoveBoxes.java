import java.util.HashMap;

/**
 * Dynamic programming - Remove Boxes problem.
 *
 * Given a number of boxes with different colors represented by different positive numbers.
 * You need to remove all the boxes in several rounds, each time you can choose continuous boxes with the
 * same color (means with the same numbers, composed of k boxes, k >= 1), remove them and get k*k points.
 *
 * Write an algorithm to get the maximum pints by removing all the boxes.
 *
 * Example:
 *
 * 1233211
 *
 * Remove 33 - Profit = 2*2 = 4
 * Remove 22 - Profit = 2*2 + 4 = 8
 * Remove 111 - Profit = 3*3 + 8 = 17
 *
 * We will try to club identical numbers so that when we remove those together, will get the maximum profit since if
 * we are removing x boxes then profit will be x*x
 *
 * Approach:
 *
 * Recursion:
 *
 * Time complexity: 2^n
 *
 * 1) Will try every option and choose the best one
 * 2) Start from index 0, pick the maximum identical numbers possible, add the profit and solve rest of the string
 *    using recursion
 * 3) Leave the option chosen in the step 2 and follow the same approach taken in step 2.
 *    Solve the remaining string
 * 4) Pick the maximum among result step 2 and step 3.
 *
 * resources/RemoveBoxes.png
 *
 * Dynamic Programming:
 *
 * If we look closely the diagram above we are solving many sub problems recursively.
 * Here we will use Top-Down approach of dynamic programming.
 * We will use HashMap to store the sub problems results and whenever we make a recursive call, first check if the
 * sub problem is already solved, if yes then use it.
 */
public class RemoveBoxes {

    private static int removeBox(String input) {
        int profit = 0;

        if (input == null || input.length() == 0) {
            return 0;
        }

        if (input.length() == 1) {
            return 1;
        }

        int start_index = 0;
        int end_index = 0;

        while (start_index < input.length()) {

            char c = input.charAt(start_index);
            int count = 0;

            //Consecutive same numbers
            while(end_index < input.length() && c == input.charAt(end_index)) {
                end_index++;
                count++;
            }

            //We have choice to select that chunk or not
            if (end_index >= input.length()) {
                profit = Math.max(profit, count*count);
            } else  {
                profit = Math.max(profit, removeBox(input.substring(0, start_index) + input.substring(end_index, input.length())) + count * count);
            }
            start_index = end_index;
        }
        return profit;
    }

    private static HashMap<String, Integer> map = new HashMap<>();

    private static int removeBoxDynamicProgramming(String input) {

        int profit = 0;

        if (input == null || input.length() == 0) {
            return 0;
        }

        if (map.containsKey(input)) {
            return map.get(input);
        }

        if (input.length() == 1) {
            return 1;
        }

        int start_index = 0;
        int end_index = 0;

        while(start_index < input.length()) {

            char c = input.charAt(start_index);
            int count = 0;

            while(end_index < input.length() && c == input.charAt(end_index)) {
                end_index++;
                count++;
            }

            //We have choice to select that chunk or not
            if (end_index >= input.length()) {
                profit = Math.max(profit, count * count);
            } else {
                profit = Math.max(profit, removeBoxDynamicProgramming(input.substring(0, start_index) + input.substring(end_index, input.length())) + count * count);
            }

            start_index = end_index;
        }
        map.put(input, profit);
        return profit;
    }

    public static void main(String[] args) {

        String input = "123321";
        System.out.println("Max profit: " + removeBox(input));
        System.out.println("Max profit using DP: " + removeBoxDynamicProgramming(input));
    }
}
