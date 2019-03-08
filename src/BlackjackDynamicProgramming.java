import java.util.Arrays;
import java.util.Random;

/**
 * Deck of 52 cards
 * Already know what cards are in the deck
 * 2 cards dealer
 * 2 cards player
 *
 * hit - $1, $0, $-1
 * i = Starting a new round, Already played i cards in previous round
 *
 * Helper method
 * round_outcome(i, h) --> returns (cp, money)
 * Takes i and number of hits
 * Returns the number of cards played and money won
 *
 * CP = 4 + 2h
 *
 * (5, 1) --> (6, 1)
 *
 * Max money we can win by playing optimally
 */
public class BlackjackDynamicProgramming {

    private static int[] won = {-1, 0, 1};

    private static void getMaxMoneyWonBlackjackDP() {

        int choices[] = new int[53];
        choices[0] = 0;

        int[] dp = new int[200];

        for (int i = 51; i >= 0; i--) { //Topological sort

            for (int h = 0; h < 52 - i; h++) {

                int[] o = round_outcome(i, h);
                int moneyMade = o[1] + dp[i + o[0]];
                choices[h + 1] = moneyMade;
            }
            dp[i] = getMax(choices);
        }

        System.out.println("The max earned at each ith starting position: " + Arrays.toString(dp));
    }

    private static int getMax(int[] choices) {
        int max = 0;
        for (int i = 0; i < 53; i++) {
            if (choices[i] > max) {
                max = choices[i];
            }
        }
        return max;
    }

    private static int[] round_outcome(int i, int h) {
        int cardsPlayed = 4 + 2 * h;

        int moneyWon = won[new Random().nextInt(won.length)];

        return new int[]{cardsPlayed, moneyWon};
    }

    public static void main(String[] args) {
        getMaxMoneyWonBlackjackDP();
    }
}
