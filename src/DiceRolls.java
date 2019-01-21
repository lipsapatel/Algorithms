import java.util.ArrayList;

/**
 * Write a recursive function diceRolls that accepts an integer representing  number
 * of 6-sided dice to roll, and output all possible combinations of values that
 * could appear on the dice
 *
 * For example:
 *
 * diceRoll(2);
 *
 * {1, 1}
 * {1, 2}
 * {1, 3}
 * {1, 4}
 * {1, 5}
 * {1, 6}
 * {2, 1}
 * ....
 *
 *
 */
public class DiceRolls {

    private static void diceRoll(int dice, ArrayList<Integer> chosen) {

        //Base Case
        if (dice == 0) {
            System.out.println(chosen.toString());
        } else {
            //some dice left to roll
            //handle one die
            //for each value that die could have:
            for (int i = 1; i <= 6; i++) {

                //choose
                chosen.add(i);

                //Explore
                diceRoll(dice - 1, chosen);

                //unchoose
                chosen.remove(chosen.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        diceRoll(2, new ArrayList<Integer>());
        diceRoll(3, new ArrayList<Integer>());
    }
}
