package ElementWhichAppearOnceInArray;

/**
 * Given an array of integers, all elements appear twice but only one element appears
 * once.
 * Write an algorithm to find that element
 *
 * int[] array = {1, 5, 6, 2, 1, 6, 4, 3, 2, 5, 3}
 * Output = 4
 *
 * Use two loops and compare each element with other element and find the one
 * which is non repeated.
 * Time Complexity: O(n2)
 * Space Complexity: O(1)
 */
public class ElementWhichAppearOnceInArray_NestedLoop_On2 {

    private static void findElementWhichAppearOnce(int[] array) {

        boolean[] isVisited = new boolean[array.length];

        for (int i = 0; i < array.length; i++) {

            if (!isVisited[i]) {

                boolean foundDuplicate = false;

                for (int j = i + 1; j < array.length; j++) {

                    if (array[i] == array[j]) {
                        isVisited[j] = true;
                        foundDuplicate = true;
                        break;
                    }
                }

                if (!foundDuplicate) {
                    System.out.println("Found the element which appears only once " + array[i]);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 6, 2, 1, 6, 4, 3, 2, 5, 3};

        findElementWhichAppearOnce(array);
    }
}
