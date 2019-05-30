import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 For example, the numbers "69", "88", and "818" are all strobogrammatic.

 There are only 5 numbers which when rotated might remain the same
 0, 1, 6, 8, 9
 6 becomes 9 and 9 becomes 6

 Input : "69"
 Output : true
 Example 2:

 Input : "68"
 Output : false

 .For example, the numbers "69", "88", and "818" are all mirror numbers.

 */
public class StrobogrammaticNumber {

    public static boolean isStrobogrammatic(String num) {

        if (num == null || num.length() == 0) {
            return true;
        }

        if (num.length() == 1) {

            return num.equals("0") || num.equals("1") || num.equals("8");
        }

        List<Character> mirrorNumber = new ArrayList<>(Arrays.asList('0', '1', '8', '6', '9'));
        char[] charArray = num.toCharArray();

        for (int i = 0; i < charArray.length; i++) {

            if (!mirrorNumber.contains(charArray[i])) {
                return false;
            }

            if (charArray[i] == '6') {
                charArray[i] = '9';
            } else if (charArray[i] == '9') {
                charArray[i] = '6';
            }
        }

        StringBuilder sb = new StringBuilder(String.valueOf(charArray));
        return sb.reverse().toString().equals(num);
    }

    public static void main(String[] args) {
        String num = "96";
        System.out.println("Is Strobogrammatic: " + num + " " + isStrobogrammatic(num));

        String num1 = "818";
        System.out.println("Is Strobogrammatic: " + num1 + " " +  isStrobogrammatic(num1));
    }
}
