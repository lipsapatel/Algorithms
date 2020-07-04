import java.util.Arrays;

/**
 * In java strings are immutable - that cannot be changed.
 *
 * Approach:
 *
 * 1) Convert string to char array.
 * 2) Reverse char array by swapping first and last char.
 * 3) Convert char array to string and return
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ReverseString {

    private static String reverseString(String a) {
        char[] input = a.toCharArray();
        int left = 0;
        int right = input.length - 1;

        while(left < right) {
            char temp = input[left];
            input[left] = input[right];
            input[right] = temp;
            left++;
            right--;
        }

        return String.valueOf(input);
    }

    public static void main(String[] args) {
        String a = "abcd";
        System.out.println(reverseString(a));
    }
}
