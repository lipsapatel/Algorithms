/**
 * Print all sub sequences of a given string
 *
 * Given a string, write an algorithm to print all the possible sub sequences.
 *
 * Example:
 *
 * String input = "abc";
 *
 * Output: Possible sub sequences
 * {Empty Set}
 * {a}
 * {b}
 * {c}
 * {a, b}
 * {b, c}
 * {a, c}
 * {a, b, c}
 *
 * Approach:
 *
 * 1) The approach will be similar to Generate all strings of n bits
 * 2) If we consider n = 3 (string length) then all possible combinations are
 * [0,0,0], [0, 1, 0], [0, 1, 1], [1, 0, 1], [1, 1, 1], [1, 0, 0], [1, 1, 0], [0, 0, 1]
 *
 * 3) So from the above combinations where ever bit is set to 1, place an string character
 * from index at the position and where ever bit is set to 0, ignore the string character.
 *
 * 4) Time Complexity: O(2^n)
 *
 */
public class PrintAllSubsequenceOfString {

    private static void printAllSubsequenceOfString(String inputString) {

        int[] temp = new int[inputString.length()];
        int index = 0;
        printAllSubsequenceRecursion(inputString, index, temp);
    }

    private static void printAllSubsequenceRecursion(String inputString, int index, int[] temp) {

        if (index == inputString.length()) {
            printSubsequence(inputString, temp);
            return;
        }

        //set the current index bit to 1 and then 0, there are 2 options
        temp[index] = 1;
        printAllSubsequenceRecursion(inputString, index + 1, temp);

        //Set it to 0 and solve it again recursively
        temp[index] = 0;
        printAllSubsequenceRecursion(inputString, index + 1, temp);
    }

    private static void printSubsequence(String input, int[] temp) {

        String result = "";

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 1) {
                result = result + input.charAt(i) + " ";
            }
        }

        if (result.equals("")) {
            result = "{Empty Set}";
        }

        System.out.println(result);
    }

    public static void main(String[] args) {

        String input = "abc";
        printAllSubsequenceOfString(input);
    }
}
