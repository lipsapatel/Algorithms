/**
 * Given a string, compute recursively (no loops) the number of "11" substrings in the string.
 * The "11" substrings should not overlap.


 count11("11abc11") → 2
 count11("abc11x11x11") → 3
 count11("111") → 1
 */
public class Count11 {

    private static int count11(String str) {
        //Base Case
        if(str.length() <= 1) {
            return 0;
        } else { //Recursive Case
            if (str.charAt(0) == '1' && str.charAt(1) == '1') {
                return 1 + count11(str.substring(2));
            } else {
                return count11(str.substring(1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("The total number of 11 in string 11abc11 are: " + count11("11abc11"));
        System.out.println("The total number of 11 in string abc11x11x11 are: " + count11("abc11x11x11"));
        System.out.println("The total number of 11 in string 111 are: " + count11("111"));
        System.out.println("The total number of 11 in string  are: " + count11(""));
    }
}
