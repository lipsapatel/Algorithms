/**
 * Cut The Rope
 * Given a rope with length n, find the maximum value maxProduct, that can be achieved for product len[0] * len[1] * ... * len[m - 1],
 * where len[] is the array of lengths obtained by cutting the given rope into m parts.
 *
 * Note that
 *   there should be atleast one cut, i.e. m >= 2.
 *     All m parts obtained after cut should have non-zero integer valued lengths.
 *
 * For input n = 5, output will be:
 * 6
 *
 * Constraints:
 *     2 <= n <= 111
 *     We have to cut at least once. (2 <= m).
 *     Length of the rope, as well as the length of each part are in positive integer value. (i.e. can't do partial cuts.)
 *
 *  Sample Input:
 * 4
 *
 * Sample Output:
 * 4
 *
 * Explanation:
 * For n = 4, there are two cuts possible: 1 + 3 and 2 + 2.
 * We'll pick 2 + 2, because their product 2 * 2 = 4 is greater than product of the first one 1 * 3 = 3.
 * (So our m = 2, n[0] = 2 and n[1] = 2 and product is n[0] * n[1] = 4.)
 *
 * resources/CutRopeMaxProductRecursion.jpg
 * resources/CutRopeMaxProductDp.jpg
 * resources/CutRopeMaxProductRecursion1.jpg
 * resources/CutRopeMaxProductTrickyApproach.jpg
 *
 * Recursion Solution
 *
 * Time Complexity: O(n^n) O(n!)
 * Space Complexity: O(n)
 *
 * Dp Solution:
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * Tricky Approach from observation
 *
 * For i >= 5, there's always one cut of length = 3
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Another tricky Approach
 *
 * 1) If the rope is multiple of 3, cut into 3 equal pieces.
 * 2) If rope is multiple of 3 + 1, cut one piece of len 4 and remaining of len 3
 * 3) If rope is multiple of 3 + 2, cut one piece of length 2 and remaining of len 3
 *
 * Power function:
 * Time complexity: O(logb) where b = n/3
 * Space complexity: O(logb) where b = n/3
 */
public class CutRopeMaxProduct {

    private static long maxProductFromCutPiecesRecursion(int n) {

        //Base Case
        if(n == 1) {
            return 1;
        }

        //At least one cut is required.
        //For all possible cuts
        long maxProduct = 1;
        for(int i = 1; i < n; i++) {
            //You don't have to do cut for 4, for example f(5) = max(1 * max (f(4), 4), 2 * max(f(3), 3),
            // 3 * max(f(2), 2), 4 * max(f(1), 1))
            maxProduct = Math.max(maxProduct, i * Math.max(maxProductFromCutPiecesRecursion(n - i), n - i));
        }
        return maxProduct;
    }

    private static long maxProductFromCutPiecesDp(int n) {
        //Identify the dp table
        //Recursion - one param changing n
        long[] dp = new long[n + 1];

        //Initialize the dp table
        //Base case n == 0 return 1
        dp[1] = 1;

        //Traversal direction. Recursion n to 0
        // 1 to n
        for(int j = 2; j <= n; j++) {

            //Populate dp table
            long maxProduct = 1;
            //For all possible cuts
            for(int i = 1; i < j; i++) {
                maxProduct = Math.max(maxProduct, i * Math.max(dp[j - i], j - i));
            }

            dp[j] = maxProduct;
        }
        return dp[n];
    }

    private static long maxProductFromCutPiecesTrickyApproach(int n) {

        long[] maxProduct = new long[n + 1];

        //Base Cases
        maxProduct[2] = 1;

        if(n >= 3) {
            maxProduct[3] = 2;
        }

        if(n >= 4) {
            maxProduct[4] = 4;
        }

        //There will be always one cut of length = 3
        for(int i = 5; i <= n; i++) {
            maxProduct[i] = Math.max(i - 3, maxProduct[i - 3]) * 3;
        }
        return maxProduct[n];
    }

    private static long maxProductFromCutPiecesAnotherTrickyApproach(int n) {

        if(n <= 3) {
            return n - 1;
        }

        if(n % 3 == 0) { //Cut all pieces of length 3
            return power(3, n/3);
        }

        if(n % 3 == 1) { //Cut one piece of length 4 and all other pieces of length 3
            return power(3, (n - 4)/3) * 4;
        }

        //Cut one piece of length 2 and all other pieces of length 3
        return power(3, (n - 2)/3) * 2;
    }

    //k ^n
    //Time Complexity: O(logn)
    //Space Complexity: O(logn)
    private static long power(int k, int n) {
        if(n == 0) {
            return 1;
        }

        long halfResult = power(k, n/2);

        if(n % 2 == 0) { //even
            return halfResult * halfResult;
        } else { //odd
            return halfResult * halfResult * k;
        }
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println("Max Product for n = 3 Recursive " + maxProductFromCutPiecesRecursion(n));
        System.out.println("Max Product for n = 3 Dp " + maxProductFromCutPiecesDp(n));
        System.out.println("Max Product for n = 3 Tricky approach " + maxProductFromCutPiecesTrickyApproach(n));
        System.out.println("Max Product for n = 3 Another Tricky approach " + maxProductFromCutPiecesAnotherTrickyApproach(n));

        n = 4;
        System.out.println("Max Product for n = 4 Recursive " + maxProductFromCutPiecesRecursion(n));
        System.out.println("Max Product for n = 4 Dp " + maxProductFromCutPiecesDp(n));
        System.out.println("Max Product for n = 4 Tricky approach " + maxProductFromCutPiecesTrickyApproach(n));
        System.out.println("Max Product for n = 4 Another Tricky approach " + maxProductFromCutPiecesAnotherTrickyApproach(n));

        n = 5;
        System.out.println("Max Product for n = 5 Recursive " + maxProductFromCutPiecesRecursion(n));
        System.out.println("Max Product for n = 5 Dp " + maxProductFromCutPiecesDp(n));
        System.out.println("Max Product for n = 5 Tricky approach " + maxProductFromCutPiecesTrickyApproach(n));
        System.out.println("Max Product for n = 5 Another Tricky approach " + maxProductFromCutPiecesAnotherTrickyApproach(n));

        n = 8;
        System.out.println("Max Product for n = 8 Recursive " + maxProductFromCutPiecesRecursion(n));
        System.out.println("Max Product for n = 8 Dp " + maxProductFromCutPiecesDp(n));
        System.out.println("Max Product for n = 8 Tricky approach " + maxProductFromCutPiecesTrickyApproach(n));
        System.out.println("Max Product for n = 8 Another Tricky approach " + maxProductFromCutPiecesAnotherTrickyApproach(n));
    }
}
