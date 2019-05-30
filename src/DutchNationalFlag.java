import java.util.Arrays;

/**
 * Dutch national flag sorting problem
 For this problem, your goal is to sort an array of 0, 1 and 2's but you must do this in place, in linear time
 and without any extra space (such as creating an extra array). This is called the Dutch national flag sorting problem.
 For example, if the input array is [2,0,0,1,2,1] then your program should output [0,0,1,1,2,2] and the algorithm should run in O(n) time.

 Algorithm

 The solution to this algorithm will require 3 pointers to iterate throughout the array, swapping the necessary elements.

 (1) Create a low pointer at the beginning of the array and a high pointer at the end of the array.
 (2) Create a mid pointer that starts at the beginning of the array and iterates through each element.
 (3) If the element at arr[mid] is a 2, then swap arr[mid] and arr[high] and decrease the high pointer by 1.
 (4) If the element at arr[mid] is a 0, then swap arr[mid] and arr[low] and increase the low and mid pointers by 1.
 (5) If the element at arr[mid] is a 1, don't swap anything and just increase the mid pointer by 1.

 This algorithm stops once the mid pointer passes the high pointer.

 Time Complexity: O(n)

 Hoare scheme - 3 way partition.

 */
public class DutchNationalFlag {

    public static void dutchNationalFlag(int[] a) {

        int low = 0;
        int mid = 0;
        int high = a.length - 1;

        while (mid <= high) {
            if (a[mid] == 0) {
                swap(a, mid++, low++);
            } else if (a[mid] == 1) {
                mid++;
            } else if (a[mid] == 2) {
                swap(a, mid, high--);
            }
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 1, 2, 0, 0, 2, 1, 2, 0, 2, 1, 0, 1, 2, 2, 1, 1,1, 0, 0, 0, 1, 2, 0, 1, 2, 1};

        long s = System.nanoTime();
        dutchNationalFlag(a);
        System.out.println(System.nanoTime() - s);
        System.out.println(Arrays.toString(a));
    }
}
