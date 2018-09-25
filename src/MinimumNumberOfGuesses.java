/**
 * Minimum number of guesses needed to find specific number
 *
 * Given the numbers 1 to 1000, what is the minimum number of guesses needed to find a specific number if you are given the hint
 * "higher" or "lower" for each guesses you make
 *
 * Naive Approach: Linear Search
 *
 * Start guessing from 1 and then 2 and 3..till we do not find the answer
 *
 * Time Complexity: O(N) N= total numbers, as per our problem it is 1000
 *
 * Better Approach: Binary Search
 *
 * Start from N/2 and keep discarding half elements after each guess based on the hint.
 *
 * Example:
 *
 * N = 1 to 1024, specific no = 378

 1st guess = 512, hint = lower, new N =1 to 512, discard numbers 513 to 1024.
 2nd guess = 256, hint = higher, new N =257 to 512, discard numbers 1 to 256
 3rd guess = 385, hint = lower, new N = 257 to 384, discard numbers 385 to 512
 4th guess = 320, hint = higher, new N = 321 to 384, discard numbers 257 to 320
 5th guess = 352, hint = higher, new N = 353 to 384, discard numbers 321 to 352
 6th guess = 368, hint = higher, new N = 369 to 384, discard numbers 353 to 368
 7th guess = 376, hint= higher, new N = 377 to 384, discard numbers 369 to 376
 8th guess = 380, hint= lower, new N = 377 to 380, discard numbers 381 to 384
 9th guess = 378 MATCH found

 Total no of guesses = 9

 Time Complexity: O(logn)
 */
public class MinimumNumberOfGuesses {

    private static void findMinimumNumberOfGuesses(int N, int x) {

        int guess = findMinimumNumberOfGuessesUtil(1, N, x);
        System.out.println("No of guesses needed for N: " + N + " and x: " + x + " are: " + guess);
    }

    private static int findMinimumNumberOfGuessesUtil(int start, int end, int x) {

        if (! (x >= start && x <= end)) {

            //x is not in range
            return -1;
        }

        int guessCount = 0;
        int guess = start + (end - start)/2;
        guessCount++;

        while(guess != x) {

            if (guess > x) {
                end = guess;
            } else {
                start = guess;
            }
            guess = start + (end - start)/2;
            guessCount++;
        }
        return guessCount;
    }

    public static void main(String[] args) {
        int N = 1024;
        int x = 378;

        findMinimumNumberOfGuesses(N, x);
    }
}
