/**
 * Given a string, compute recursively a new string where all the 'x' chars have been removed.


 noX("xaxb") → "ab"
 noX("abc") → "abc"
 noX("xx") → ""
 */
public class NoX {

    private static String noX(String str) {
        return noXHelper(str, "");
    }

    private static String noXHelper(String str, String result) {
        //Base Case
        if (str.isEmpty()) {
            return result;
        } else { //Recursive case
            if (str.charAt(0) == 'x') {
                return noXHelper(str.substring(1), result);
            } else {
                return noXHelper(str.substring(1), result + str.charAt(0));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("String after removing x is: " + noX("xaxb"));
        System.out.println("String after removing x is: " + noX("abc"));
        System.out.println("String after removing x is: " + noX("xx"));
    }
}
