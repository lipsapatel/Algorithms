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

    public static void main(String[] args) {
        String input = "AB";

        findSubsets("", input);
    }
}
