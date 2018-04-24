package ThreeElementsSumZero;

import java.util.HashSet;

/**
 * Given an array of integer find 3 elements that sum to zero
 * a + b + c = 0
 *
 * int[] a = {3, -1, -4, -5, -7, 9, 10}
 * Elements are -4, -5 and 9
 *
 * Use two loops
 * a = x[i]
 * b = x[j]
 * c = -(a+b)
 *
 * if set contains c then we found three elements whose sum is 0
 * else add to set b
 *
 * Time Complexity: - O(n2)
 */
public class HashSetOn2 {

    private static void findThreeElementsSumZero(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int a = array[i];
            HashSet<Integer> set = new HashSet<Integer>();

            for (int j = i + 1; j < array.length; j++) {
                int b = array[j];

                int c = -(a+b);

                if (set.contains(c)) {
                    System.out.println("Found 3 elements whose sum is 0. Elements are " + a + "," + b + "," + c);
                    return;
                } else {
                    set.add(b);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {3, -1, -4, -5, -7, 9, 10};

        findThreeElementsSumZero(array);
    }
}
