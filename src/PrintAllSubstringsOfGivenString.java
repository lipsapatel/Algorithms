/**
 * Print all substrings of a given string.
 *
 * Example:
 *
 * Input: "abcd"
 * Output: Possible sub strings
 *
 * a
 * ab
 * abc
 * abcd
 * b
 * bc
 * bcd
 * c
 * cd
 * d
 *
 * Approach:
 *
 * Time Complexity: O(n3)
 *
 * Three nested loops
 *
 * 1) Use nested loops
 * 2) Outer nested loop will decide the starting point
 * 3) First Inner nested loop - Will decide the group size. Starting from 1 and goes up to character array size(4)
 * 4) Second Inner nested loop - Create substring from starting point to groupSize and print it
 *
 * Time Complexity: O(n2)
 *
 * Two nested loops
 *
 * 1) Use two nested loops
 * 2) Outer nested loop will decide the starting point
 * 3) Inner nested loop will decide the group size
 * 4) Use StringBuilder and append to the front
 */
public class PrintAllSubstringsOfGivenString {

    //Time Complexity: O(n3)
    private static void printSubstring(String input) {

        int length = input.length();

        for (int startPoint = 0; startPoint < length; startPoint++) {

            //Group Size
            for (int groupSize = startPoint + 1; groupSize <= length; groupSize++) {

                //Group Size = 1, 2, 3, 4

                for (int j = startPoint; j < groupSize; j++) {
                    System.out.print(input.charAt(j) + " ");
                }

                System.out.println();
            }
        }
    }

    //Time Complexity: O(n2)
    private static void printSubstringUsingAppend(String input) {

        int length = input.length();

        System.out.println("Using two nested loops");

        for (int startPoint = 0; startPoint < length; startPoint++) {

            StringBuilder outputString = new StringBuilder();
            outputString.append(input.charAt(startPoint));

            for (int groupSize = startPoint + 1; groupSize <= length; groupSize++) {

                //Keep printing
                System.out.println(outputString);

                if (groupSize < length) {
                    outputString.append(input.charAt(groupSize));
                }
            }
        }
    }

    public static void main(String[] args) {
        String input = "abcde";

        printSubstring(input);
        System.out.println();
        printSubstringUsingAppend(input);
    }
}
