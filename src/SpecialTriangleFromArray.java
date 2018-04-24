import java.util.Arrays;

/**
 * Construct a special triangle from a given array.
 *
 * Given an array of integers, such that first level will print all the elements in the array and from
 * then the number of elements at each level will be one less than the previous level and element
 * at each level will be the sum of consecutive elements in the previous level. Print it in a
 * reverse level.
 *
 * 48
 * 20 28
 * 8  12 16
 * 3  5  7  9
 * 1  2  3  4 5
 *
 * resources/SpecialTriangleFromArray.png
 *
 * 1) Recursion is the key
 * 2) At each iteration create new array where each element is the sum of consecutive numbers
 * from array that is passed as parameter.
 * 3) Recursive call with newly created array
 * 4) While backtracking, Print the array for printing in reverse order.
 */
public class SpecialTriangleFromArray {

    private static void specialTriangleFromArray(int[] array) {

        if (array.length >= 1) {

            int[] temp = new int[array.length - 1];

            for (int i = 0; i < array.length - 1; i++) {

                int x = array[i] + array[i+1];
                temp[i] = x;
            }

            //Changing the print statements, print triangle in up-side down
            //System.out.println(Arrays.toString(array));
            specialTriangleFromArray(temp);
            System.out.println(Arrays.toString(array));
        }
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5};
        specialTriangleFromArray(array);
    }
}
