package MajorityElementInArray;

import java.util.Arrays;

/**
 * Sort an array
 * Iterate the array and check if any element has count > n/2
 * Time Complexity: O(nlogn)
 * Space Complexity: O(1)
 */
public class MajorityElementArraySort {

    private static void findMajorityElement(int[] array) {
        Arrays.sort(array);
        boolean found = false;

        int x = array[0];
        int count = 1;

        for (int i = 1; i < array.length; i++) {
            if (x == array[i]) {
                count++;

                if (count > array.length/2) {
                    System.out.println("Found the majority element " + array[i]);
                    found = true;
                    break;
                }
            } else {
                x = array[i];
                count = 1;
            }
        }

        if (!found) {
            System.out.println("Majority element not found");
        }
    }

    public static void main(String[] args) {
        int [] array = {1,3,5,5,5,5,4,1,5};
        findMajorityElement(array);
    }
}
