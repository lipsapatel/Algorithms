/**
 * Check if one string is rotation of another string
 *
 * Input String: lipsa patel
 * and psapatelli
 *
 * true
 *
 * Input String lipsa patel
 * and pspatellia
 *
 * false
 *
 * Approach:
 *
 * 1) stringAdd = s1 + s1;
 * Make a new string by appending first string to itself
 * 2) stringAdd.contains(s2);
 * Check if second string is substring of new string.
 *
 * Space Complexity: O(2n)
 * Time Complexity: O(2n * n) = where 2n is the length stringAdd and n is the length of s2 = O(n ^ 2)
 */
public class CheckIfOneStringIsRotationOfAnother {

    private static boolean checkIfOneStringIsRotationOfAnother(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        String stringAdd = s1 + s1;

        if (stringAdd.contains(s2)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        String s1 = "lipsapatel";
        String s2 = "psapatelli";

        System.out.println("The strings are rotation of each other: " + checkIfOneStringIsRotationOfAnother(s1, s2));

        String s3 = "pspatellia";

        System.out.println("The strings are rotation of each other: " + checkIfOneStringIsRotationOfAnother(s1, s3));
    }
}
