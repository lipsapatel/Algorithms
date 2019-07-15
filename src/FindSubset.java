import java.util.Arrays;

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
 * resources/FindSubset.png
 * resources/FindSubsetUsingArray.png
 *
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

        //Base Case
        if (i == array.length) {
            if (j == k) {
                for (int x = 0; x < j; x++) {
                    System.out.print(subsetSoFar[x]);
                }
                System.out.println();
            }
        } else { //Recursive Case

            //Don't include A
            findSubsetOfCertainSize(array, i + 1, subsetSoFar, j, k);

            //Include A
            subsetSoFar[j] = array[i];
            findSubsetOfCertainSize(array, i + 1, subsetSoFar, j + 1, k);
        }
    }

    public static void main(String[] args) {
        String input = "AB";

        findSubsets("", input);

        char[] array = {'a', 'b', 'c'};
        findSubsetsArray(array, 0, new char[array.length], 0);

        System.out.println("Print of certain size: ");
        findSubsetOfCertainSize(array, 0, new char[array.length], 0, 2);
    }
}
