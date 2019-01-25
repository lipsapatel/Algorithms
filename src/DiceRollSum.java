import java.util.ArrayList;

/**
 * Write a function diceSum similar to diceRoll, but it also accepts a desired
 * sum and prints only combinations that add up to exactly that sum.
 *
 * For example:
 *
 * diceSum(2,7)
 *
 * {1, 6}
 * {2, 5}
 * {3, 4}
 * {4, 3}
 * {5, 2}
 * {6, 1}
 *
 * diceSum(3, 7)
 *
 * {1, 1, 5}
 * {1, 2, 4}
 * ...
 */
public class DiceRollSum {

    private static void diceSumHelper (int dice, int desiredSum, int sumSoFar, ArrayList<Integer> chosen) { // int j){

        //Base Case
        if (dice == 0) {
            System.out.println(chosen.toString());
        } else {

            for (int i = 1; i <= 6; i++) { //i = j - This is to print all unique combinations
                //too small
                //too big
                if (sumSoFar + i + 1 * (dice - 1) <= desiredSum &&
                    sumSoFar + i + 6 * (dice - 1) >= desiredSum) {

                    chosen.add(i);
                    diceSumHelper(dice - 1, desiredSum, sumSoFar + i, chosen);// i);
                    chosen.remove(chosen.size() - 1); //Backtrack
                }
            }
        }
    }

    public static void main(String[] args) {
        diceSumHelper(2, 7, 0, new ArrayList<Integer>()); // 1);
        diceSumHelper(3, 7, 0, new ArrayList<Integer>()); //, 1);
    }
}
