import java.util.Arrays;

/**
 * Three stones are on a number line at positions a, b, and c.

 Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints.  Formally, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.

 The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

 When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]



 Example 1:

 Input: a = 1, b = 2, c = 5
 Output: [1,2]
 Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.
 Example 2:

 Input: a = 4, b = 3, c = 2
 Output: [0,0]
 Explanation: We cannot make any moves.
 Example 3:

 Input: a = 3, b = 5, c = 1
 Output: [1,2]
 Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.


 Note:

 1 <= a <= 100
 1 <= b <= 100
 1 <= c <= 100
 a != b, b != c, c != a

 For the minimum: We can always do it in at most 2 moves, by moving one stone next to another,
 then the third stone next to the other two. When can we do it in 1 move? 0 moves?
 For the maximum: Every move, the maximum position minus the minimum position must decrease by at least 1.
 */
public class MovingStonesUntilConsecutive {

    public static int[] moveStones(int a, int b, int c) {

        int[] result = new int[2];

        int sum = a + b + c;
        int l = Math.min(a, Math.min(b, c));
        int r = Math.max(a, Math.max(b, c));
        int m = sum - r - l;

        int gap1 = m - l - 1;
        int gap2 = r - m - 1;
        int gmin = Math.min(gap1, gap2);

        int max = gap1 + gap2;

        int min;
        if (gap1 == 0 && gap2 == 0) { //All are consecutive no min move
            min = 0;
        } else if (gmin <= 1) {
            min = 1;
        } else {
            min = 2;
        }

        result[0] = min;
        result[1] = max;

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Maximum and minimum moves are: " + Arrays.toString(moveStones(1, 2, 5)));
        System.out.println("Maximum and minimum moves are: " + Arrays.toString(moveStones(4, 3, 2)));
        System.out.println("Maximum and minimum moves are: " + Arrays.toString(moveStones(3, 5, 1)));
    }
}
