/**
 * Given two strings, find out if they are equal or not without using built-in function
 * (without using equals() function in Java)
 *
 * Example:
 *
 * String x = "tutorial" String y = "tutorial" are equal = true
 * String x = "tutorial" String y = "tutorial " are equal = false
 * String x = "tutorial" String y = " " are equal = false
 *
 * Approach:
 *
 * 1) If any of the string is null, return false
 * 2) If length of both strings are not matching, return false
 * 3) Check if all the character of both strings are matching, if not return false
 * 4) If all of the steps above got executed without returning false return true
 */
public class CompareStringWithoutUsingBuiltinFunction {

    private static boolean compareString (String x, String y) {

        if (x == null || y == null) {
            return false;
        }

        //Compare lengths
        if (x.length() != y.length()) {

            return false;
        }

        //Compare all characters
        for (int i = 0; i < x.length(); i++) {

            if (x.charAt(i) != y.charAt(i)) {

                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        String x = "tutorial";
        String y = "tutorial";

        System.out.println("String x = " + x + " and String y = " + y + " are equal? - " + compareString(x, y));

        x = "tutorial";
        y = "tutorial ";

        System.out.println("String x = " + x + " and String y = " + y + " are equal? - " + compareString(x, y));

        x = "tutorial";
        y = " ";

        System.out.println("String x = " + x + " and String y = " + y + " are equal? - " + compareString(x, y));
    }
}
