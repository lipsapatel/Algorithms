/**
 * There is a fence with n posts, each post can be painted with one of the k colors.

 You have to paint all the posts such that no more than two adjacent fence posts have the same color.

 Return the total number of ways you can paint the fence.

 Note:
 n and k are non-negative integers.

 Example:

 Input: n = 3, k = 2
 Output: 6
 Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

 post1  post2  post3
 -----      -----  -----  -----
 1         c1     c1     c2
 2         c1     c2     c1
 3         c1     c2     c2
 4         c2     c1     c1
 5         c2     c1     c2
 6         c2     c2     c1

Approach:

 1) For n = 0 post, there are 0 ways
 2) For n = 1 post, there are k ways
 3) For n = 2

 Case 1: Same: If we paint the first two post with the same color

 No of ways to paint with same color = k * 1

 Case 2: Diff: If we paint the first two posts with different color

 No of ways to paint with diff color = k * (k - 1)

 Total number of ways = same + diff

 4) For n = 3 and greater

 Case 1: Same: If we paint the third post with same color

 First two posts are different = diff * 1
 First two posts are same = no valid possibility, it will violate the condition

 Case 2: Diff: If we paint the third post with diff color

 First two posts are same = same * (k - 1)
 First two posts are diff = diff * (k - 1)

 diff = (same + diff) * (k - 1)

 Time Complexity: O(n)
 Space Complexity: O(1)

 Nice explanation: https://www.youtube.com/watch?time_continue=1&v=deh7UpSRaEY
 */
public class PaintFence {

    private static int noOfWaysToPaintFence(int n, int k) {

        //Base Cases
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return k;
        }

        //Case 1: Same
        int same = k * 1;

        //Case 2: Diff
        int diff = k * (k - 1);

        for (int i = 3; i <= n; i++) {

            int prevDiff = diff;

            //Case 1: Diff
            diff = (same + prevDiff) * (k - 1);

            //Case 1: Same
            same = prevDiff * 1;
        }
        return same + diff;
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 2;

        System.out.println("Number of Different ways: " + noOfWaysToPaintFence(n, k));
    }
}
