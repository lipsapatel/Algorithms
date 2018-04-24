/**
 * Print all sub arrays of a given array
 *
 * Given an array write an algorithm to print all the possible sub arrays.
 *
 * Example:
 *
 * int[] array = {1, 2, 3}
 *
 * Possible Subarrays -
 *
 * {1}
 * {2}
 * {3}
 * {1, 2}
 * {2, 3}
 * {1, 2, 3}
 *
 * Approach:
 *
 * Using three nested loops
 * Time Complexity: O(n3)
 *
 * 1) Use nested loops
 * 2) The outer loop will decide the starting point.
 * 3) First inner loop will decide the group size. Starting from 1 and goes up to the array size.
 * 4) Most inner loop will create the subarrays and print it.
 *
 * Using Append and two nested loops
 * Time Complexity: O(n2)
 *
 * 1) Use two nested loops.
 * 2) The outer loop will decide the starting point.
 * 3) Append that starting point to string
 * 4) First inner loop will decide the group size. Starting from 1 and goes up to the array size.
 * 5) Print the string
 * 6) Then keep appending.
 *
 */
public class PrintAllSubArraysOfGivenArray {

    private static void printAllSubarraysUsingNestedLoop(int[] array) {

        int length = array.length;

        for (int startPoint = 0; startPoint < length; startPoint++) {

            for (int groupSize = startPoint + 1; groupSize <= length; groupSize++) {

                for (int j = startPoint; j < groupSize; j++) {

                    System.out.print(array[j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static void printAllSubarraysUsingAppend(int[] array) {

        int length = array.length;

        System.out.println("Using two Nested Loops and Append");

        for (int startPoint = 0; startPoint < length; startPoint++) {

            StringBuilder outputString = new StringBuilder();
            outputString.append(array[startPoint] + " ");

            for (int groupSize = startPoint + 1; groupSize <= length; groupSize++) {

                System.out.println(outputString);

                if (groupSize < length) {
                    outputString.append(array[groupSize] + " ");
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4};

        printAllSubarraysUsingNestedLoop(array);
        printAllSubarraysUsingAppend(array);
    }
}
