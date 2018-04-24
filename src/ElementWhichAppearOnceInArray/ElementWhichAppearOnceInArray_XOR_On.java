package ElementWhichAppearOnceInArray;

/**
 * Given an array of integers, all elements appear twice but only one element appears
 * once.
 * Write an algorithm to find that element
 *
 * int[] array = {1, 5, 6, 2, 1, 6, 4, 3, 2, 5, 3}
 * Output = 4
 *
 * A XOR A = 0
 * So if you XOR all elements in an array then all duplicate elements will become
 * 0 and the one which is left is appearing once.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ElementWhichAppearOnceInArray_XOR_On {

    private static void findElementWhichAppearOnce(int[] array) {

        if(array.length == 0) {
            System.out.println("Array is empty");
            return;
        }

        int XOR = array[0];

        for (int i = 1; i < array.length; i++) {
            XOR = XOR ^ array[i];
        }

        System.out.println("The element which appear once is " + XOR);
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 6, 2, 1, 6, 4, 3, 2, 5, 3};

        findElementWhichAppearOnce(array);
    }
}
