/**
 * Dynamic Programming - Coin in a line game problem
 *
 * In this game, which we will call the coins-in-a-line game, an even number, n coins of various denominations
 * from various countries are placed in a line.
 * Two players, who we will call Alice and Bob, take turns removing one of the coins from either end of the remaining
 * line of coins.
 * That is, when it is a player's turn, he or she removes the coin at the left or right end of the line of coins and
 * adds that coin to his or her collection. The player who removes a set of coins with larger total value than the other
 * player wins, where we assume that both Alice and Bob know the value of each coin.
 *
 * Example:
 *
 * coins[] = {6, 9, 1, 2, 16, 8}
 *
 * Trial 1:
 *
 * Players will pick the best option available for them
 *
 * Alice picks 8
 * coins[] = {6, 9, 1, 2, 16}
 *
 * Bob picks 16
 * coins[] = {6, 9, 1, 2}
 *
 * Alice picks 6
 * coins[] = {9, 1, 2}
 *
 * Bob picks 9
 * coins[] = {1, 2}
 *
 * Alice picks 2
 * coins[] = {1}
 *
 * Bob picks 1
 *
 * Alice = 8 + 6 + 2 = 16
 * Bob = 16 + 9 + 1 = 26
 *
 * Alice Lost
 *
 * So clearly picking up the best in each move is not good for Alice. What else Alice can do to win the game.
 *
 * Trail 2:
 *
 * Alice thinks about Bob's move
 *
 * Alice picks 6
 * coins[] = {9, 1, 2, 16, 8}
 *
 * Bob picks 9
 * coins[] = {1, 2, 16, 8}
 *
 * Alice picks 1
 * coins[] = {2, 16, 8}
 *
 * Bob picks 8
 * coins[] = {2, 16}
 *
 * Alice picks 16
 * coins[] = {2}
 *
 * Bob picks 2
 *
 * Alice = 6 + 1 + 16 = 23
 * Bob = 9 + 8 + 2 = 19
 * Alice won
 *
 * So this time Alice has won.
 *
 * Approach:
 *
 * We will solve the problem using Dynamic Programming in Bottom-up manner.
 * In the example above we have seen that in trial 1, Alice has lost and in trial 2 Alice has won.
 *
 * So the question is what Alice has done differently to win in second trial.
 * Since Alice and Bob, both are playing to win the game and both are equally clever then
 * "Alice has to think about Bob's move means options available for Bob once Alice is done with the move.
 * What Bob will pick (Bob is equally clever and tries to leave Alice with minimum values to be chosen from) and then what will
 * Alice chose"
 *
 * So if we can clearly see that each player is making the move by keeping in mind the two moves can be made in future
 * and pick the best of them.
 *
 * Suppose we have coins from Ci to Cj with values of Vi to Vj respectively
 *
 * MV(i, j) = Maximum value Alice can collect from ith coin to jth coin
 *
 * In every move Alice has 2 options -
 *
 * Either pick the ith coin (from starting)
 * or pick the jth coin (from end)
 *
 * Let's discuss both the options
 *
 * Alice picks the ith coin from starting
 *
 * Ci Ci+1 ... ... Cj-1 Cj
 *
 * If Alice picks ith coin Ci, then Bob will be left with 2 choices Ci+1 and Cj, since Bob is equally clever
 * than Bob will pick the coin which will leave the Alice with minimum value coins.
 *
 * So if Bob picks i+1th coin then again Alice has to pick from i+2th to jth coin
 * Similarly if Bob picks jth coin then Alice has to pick a coin from i+1th to j-1th coin
 *
 * So Alice collection will be
 * Vi + Min(MV(i+2, j), MV(i+1, j-1))
 *
 * Alice picks the jth coin
 *
 * If Alice picks jth coin, Cj then Bob will be left with 2 choices Ci and Cj-1, since Bob is equally clever,
 * Bob will pick the coin which will leave the Alice with minimum value coins.
 *
 * So if Bob picks ith coin then Alice has to pick from i+1th to j-1th coin.
 * Similarly if Bob picks j-1th coin then Alice has to pick a coin from ith to j-2th coin
 *
 * Alice collection will be
 *
 * Vj + Min(MV(i+1,j), MV(j,j-2))
 *
 * So now we need to decide whether Alice should pick ith coin or jth coin
 * Alice will pick the coin which ever gives more value considering 2 moves ahead
 *
 * MV(i,j) = Max(Vi + Min(MV(i+2,j),MV(i+1,j-1)), Vj + Min(MV(i+1,j-1),MV(i,j-2)))
 *
 * Let's see how the recursive equation will look like
 *
 * resources/CoinGameRecursiveEquation.png
 *
 */
public class CoinInLineGame {

    private static int coinInLineGame(int[] array) {

        int[][] MV = new int[array.length][array.length];

        for (int interval = 0; interval < array.length; interval++) {
            for(int i = 0, j = interval; j < array.length; i++, j++) {

                //Alice chooses i Bob chooses i+1
                //a = MV(i+2, j)

                //Alice chooses i, Bob chooses j or
                //Alice chooses j, Bob chooses i
                //b = MV(i+1, j-1)

                //Alice chooses j, Bob chooses j-1
                //c = MV(i, j-2)

                int a, b, c;

                if (i + 2 <= j) {
                    a = MV[i + 2][j];
                } else {
                    a = 0;
                }

                if (i + 1 <= j - 1) {
                    b = MV[i + 1][j - 1];
                } else {
                    b = 0;
                }

                if (i <= j - 2) {
                    c = MV[i][j - 2];
                } else {
                    c = 0;
                }

                MV[i][j] = Math.max(array[i] + Math.min(a, b),
                                     array[j] + Math.min(b, c));
            }
        }
        return MV[0][array.length - 1];
    }

    public static void main(String[] args) {

        int[] array = {6, 9, 1, 2, 16, 8};
        System.out.println("Max value pick up by player1(Alice): " + coinInLineGame(array));
    }
}
