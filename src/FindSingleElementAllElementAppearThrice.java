import java.util.Arrays;

/**
 * All Elements appears thrice and one element appears once. Find that element in O(n) time and O(1) space.
 *
 * Give an array of integers in which all elements appear thrice but one which appear only once.
 * Write an algorithm to find that element.
 *
 * int[] a = {6, 5, 3, 2, 4, 2, 5, 6, 3, 3, 6, 5, 2}
 * Output: 4
 *
 * Approach:
 *
 * Nested Loops
 *
 * Use nested loops and compare each element with all other elements and return element
 * which appears only once.
 *
 * Time Complexity: O(n2)
 *
 * Use HashMap
 *
 * 1) Store each element as key and its count as value in hash map
 * 2) Iterate through map and return the element that appear once.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Better Approach
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * 1) Doing XOR will not help here since all the elements appear odd number of times
 * 2) Sum all the bits in the same positions for all the elements and take modulus with 3
 * 3) If remainder is 0, means that bit is set by elements appearing thrice.
 * 4) If remainder is not 0, it means that bit is set in element appearing once for sure.
 * If may be set or unset in elements appearing thrice.
 * 5) Repeat step 2, 3, 4 for all the elements for all the positions, we will get the element
 * appearing once.
 *
 * Say arr[] = {6, 6, 6, 3}

 1 1 0
 1 1 0
 1 1 0
 0 1 1
 __________+
 3 4 1

 Now modulus with 3

 3mod3  4mod3 1mod3 => 0 1 1 => 3 element appearing once.
 */
public class FindSingleElementAllElementAppearThrice {

    private static void findElementAppearingOnce(int[] a) {

        int singleElement = 0;

        //For 32 bit number, calculate sum for all the position
        for (int i = 0; i < 32; i++) {
            int y = 1 << i;

            int tempSum = 0;

            for (int j = 0; j < a.length; j++) {

                if ((y & a[j]) >= 1) { //Means that bit is set, add to sum
                    tempSum = tempSum + 1;
                }
            }

            //Check if not multiple of 3, then set that bit in singleElement
            if ((tempSum % 3) == 1) {
                singleElement = singleElement | y;
            }
        }
        System.out.println("Element appearing once is: " + singleElement);
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 5, 6, 1, 4, 6, 1, 4, 6};
        System.out.println(Arrays.toString(a));
        findElementAppearingOnce(a);
    }
}
