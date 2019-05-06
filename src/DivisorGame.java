/**
 * Alice and Bob take turns playing a game, with Alice starting first
 *
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:

 Choosing any x with 0 < x < N and N % x == 0.
 Replacing the number N on the chalkboard with N - x.
 Also, if a player cannot make a move, they lose the game.

 Return True if and only if Alice wins the game, assuming both players play optimally.



 Example 1:

 Input: 2
 Output: true
 Explanation: Alice chooses 1, and Bob has no more moves.
 Example 2:

 Input: 3
 Output: false
 Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.


 Note:

 1 <= N <= 1000

 dp[i] indicates the result of the game for the given number i and for the player who started the game.
 Alice will try all the factors and choose the one which gives his opponent a losing value and she wins.

 Alice has to move in such a way that she wins and his opponent loses in next play

 dp[i] is your turn and dp[i - j] is opponent's turn, if dp[i - j] == false that means dp[i] can win.
 */
public class DivisorGame {

    private static boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 1];

        dp[0] = false;
        dp[1] = false;

        for (int i = 2; i <= n; i++) { //This constructs dp table

            for (int j = 1; j <= i/2; j++) { //Try all of the factors from 1 to i/2

                if (i % j == 0) { //divisor

                    //Make sure opponent loses in his next turn
                    if (dp[i - j] == false) {
                        dp[i] = true; //I win for dp[i]
                        break;
                    }
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("Alison wins if n = 2:  " + divisorGame(2));
        System.out.println("Alison wins if n = 3:  " + divisorGame(3));
        System.out.println("Alison wins if n = 4:  " + divisorGame(4));
    }
}
