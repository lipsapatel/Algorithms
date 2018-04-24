import java.util.Arrays;

/**
 * Rearrange the array of Given range N, such that A[i] = i
 *
 * Given a array of length N, in which elements are in the range of 1 to N.
 * All elements may not be present in the array.
 *
 * If elements are not present, there will be -1 present in the array.
 *
 * Rearrange the array such that A[i] = i and if not present, display -1 at that place.
 *
 * resources/RearrangeTheArrayOfGivenRangeSuchThatAiEqualsi.png
 *
 * Approach:
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * 1) Navigate the array
 * 2) If A[i] != -1 and A[i] != i (If not vacate and correct position
 * then find the correct position for A[i])
 * 3) Then find the correct position for A[i]
 * 4) x = A[i]
 * 5) while A[x] != -1 && A[x] != x then (If the existing element is not at
 * correct position then place it at it's correct
 * position
 * 6) y = A[x]
 *    A[x] = x
 *    x= y
 * 7) Place y at it's correct position
 * A[x] = x
 *
 * 8) If A[i] != i means it's not been set to correct position then
 * A[i] = -1
 *
 */
public class RearrangeArraySuchThatAiEqualsi {

    private static int[] rearrangeArray(int[] A) {

        for (int i = 0; i < A.length; i++) {

            //If element is not vacate and correct position
            if (A[i] != -1 && A[i] != i) {

                int x = A[i];

                while(A[x] != -1 && A[x] != x) { //If the desired place is not vacate

                    int y = A[x]; //Store the value from desired place
                    A[x] = x; //Place x to it's correct position
                    x = y; //now y will become x and search the correct place for x
                }

                A[x] = x; //Place x to it's correct position
                if (A[i] != i) { //If while loop didn't set the correct value at A[i]
                    A[i] = -1;
                }
            }
        }
        return A;
    }

    public static void main(String[] args) {

        int A[] = { -1, -1, 6, 1, 9, 3, 2, -1, 4, -1 };
        System.out.println("Fixed Indexed Array " + Arrays.toString(rearrangeArray(A)));
        int B[] = { 19, 7, 0, 3, 18, 15, 12, 6, 1, 8, 11, 10, 9, 5, 13, 16, 2,
                14, 17, 4, };
        System.out.println("Fixed Indexed Array " + Arrays.toString(rearrangeArray(B)));
    }
}
