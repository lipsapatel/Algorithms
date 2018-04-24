/**
 * Print all sub sequence of a given array
 *
 * Given an array write an algorithm to print all the possible sub sequences.
 *
 * Example:
 *
 * int[] array = {1, 2, 3};
 * Output:
 *
 * Possible sub sequence
 * {Empty Set}
 * {1}
 * {2}
 * {3}
 * {1, 2}
 * {2, 3}
 * {1, 3}
 * {1, 2, 3}
 *
 * Approach:
 *
 * 1) If we consider n = 3, all the possible subsequence will be 2^n = 8
 * 2) All possible combinations are [0,0,0][1,0,0][0,1,0][1,1,0][0,0,1][1,0,1][0,1,1][1,1,1]
 * 3) Place an array element where ever bit is set to 1
 *
 * Time Complexity: O(2^n)
 */
public class PrintAllSubsequenceOfArray {

    private static void printAllSubsequenceOfArray(int[] inputArray) {

        int[] temp = new int[inputArray.length];
        int index = 0;
        printAllSubsequenceRecursion(inputArray, index, temp);
    }

    private static void printAllSubsequenceRecursion(int[] inputArray, int index, int[] temp) {

        if (index == inputArray.length) {
            printSubsequence(inputArray, temp);
            return;
        }

        //Set the current index bit to 1 and then 0, there are 2 options
        temp[index] = 1;
        printAllSubsequenceRecursion(inputArray, index + 1, temp);

        //Set it to 0 and solve it again recursively
        temp[index] = 0;
        printAllSubsequenceRecursion(inputArray, index + 1, temp);
    }

    private static void printSubsequence(int[] inputArray, int[] temp) {

        String result = "";

        for (int i = 0; i < temp.length; i++) {

            if (temp[i] == 1) {

                result = result + inputArray[i] + " ";
            }
        }

        if (result.equals("")) {
            result = "{Empty Set}";
        }

        System.out.println(result);
    }

    public static void main(String[] args) {

        int [] inputArray = {1, 2, 3};
        printAllSubsequenceOfArray(inputArray);

    }
}
