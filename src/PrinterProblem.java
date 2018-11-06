import java.util.HashMap;

/**
 * Dynamic Programming - Printer Problem
 *
 * Given a printer which can perform only 2 operations:
 *
 * 1) Printer can print consecutive identical characters in one go.
 * 2) It can replace consecutive characters by consecutive identical characters at any position
 *
 * You are given string input with some characters.
 * Write an algorithm to find the minimum number of printer operations required to print the input string.
 *
 * Example:
 *
 * Input: aabbcc
 * Output: 3
 *
 * (operation 1: print 'aa', operation 2: print 'bb', operation 3: print 'cc')
 *
 * Input: aabbccaa
 * Output: 3
 * (operation 1: print 'aaaaaaaa', operation 2: replace 'aa' with 'bb' in index 2 and 3, operation 3: replace 'aa' with 'cc' in index 4 and 5)
 *
 * Approach:
 *
 * This problem is similar to "Remove Boxes" problem. We will try to club identical characters so that printing them all together
 * will minimize the total number of operations.
 *
 * Take the example input: "aba".
 * If we take out 'b' from the input string, the remaning string will be 'aa' which can be printed in one operation.
 * One thing is left is 'b' which can be printed by replace operation.
 * So when taking 'b' out, print a instead and later replace with 'b'
 *
 * Recursion
 *
 * 1) Will try every option and choose the best one
 * 2) Start from index 0, pick the continuous identical characters possible, add 1 to the total operations (either print or replace
 * operation) and solve rest of the string using recursion.
 * 3) Leave the option chosen in step 2 and follow the same approach taken in step 2 starting from index where step 2 has ended.
 * Solve the remaining string recursively
 * 4) Each option will produce a result, choose a minimum among those.
 *
 * resources/PrinterProblem.png
 *
 * Dynamic Programming
 *
 * If we look closely the diagram above we are solving many sub problems recursively.
 * Here we will use top down approach of dynamic programming.
 * We will use HashMap to store the sub problems results and whenever we make a recursive call, first check if the sub problem
 * is already solved, if yes then use it.
 */
public class PrinterProblem {

    private static int print(String input) {

        int operations = input.length();

        //Base case
        if (input == null || input.length() == 0) {
            return 0;
        }

        if (input.length() == 1) {
            return 1;
        }

        int start_index = 0;
        int end_index = 0;

        while(start_index < input.length()) {

            char c = input.charAt(start_index);

            while (end_index < input.length() && c == input.charAt(end_index)) {
                end_index++;
            }

            //If end_index has reached to the end of the string means recursive call is required only for 0 to start_index
            if (end_index >= input.length()) {
                operations = Math.min(operations, print(input.substring(0, start_index)) + 1);
            } else {

                //else recursive call to the rest of the string left
                operations = Math.min(operations, print(input.substring(0, start_index) + input.substring(end_index, input.length())) + 1);
            }

            //Put the start_index at the end_index for the next iteration
            start_index = end_index;
        }
        return operations;
    }

    private static HashMap<String, Integer> map = new HashMap<>();

    public static int printDp(String input) {

        int operations = input.length();

        //Base case
        if (input == null || input.length() == 0) {
            return 0;
        }

        //Check if we have already solved the problem
        if (map.containsKey(input)) {
            return map.get(input);
        }

        int size = input.length();

        if (size == 1) {
            return 1;
        }

        int start_index = 0;
        int end_index = 0;

        while(start_index < size) {

            char c = input.charAt(start_index);
            while(end_index < size && c == input.charAt(end_index)) {
                end_index++;
            }

            //If end_index has reached to the end of the string means recursive call is required only for the 0 to start_index
            if (end_index >= size) {
                operations = Math.min(operations, print(input.substring(0, start_index)));
            } else {

                //else recursive call to the rest of the string left
                operations = Math.min(operations, print(input.substring(0, start_index) + input.substring(end_index, size)) + 1);
            }
            //put the start_index at the current end_index for the next iteration
            start_index = end_index;
        }

        //Store the result for future
        map.put(input, operations);
        return operations;
    }
    public static void main(String[] args) {

        String input = "aabbccaa";
        System.out.println("Minimum Operations: " + print(input));
        System.out.println("Minimum Operations Using DP:" + print(input));
    }
}
