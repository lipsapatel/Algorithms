/**
 * Given a string, compute recursively (no loops) a new string where all appearances of "pi" have been replaced by "3.14".


 changePi("xpix") → "x3.14x"
 changePi("pipi") → "3.143.14"
 changePi("pip") → "3.14p"
 */
public class ChangePi {

    private static String changePi(String str) {
        //Helper function
        return changePiHelper(str, "");
    }

    private static String changePiHelper(String str, String result) {
        //Base Case
        if (str.isEmpty()) {
            return result;
        } else { //Recursive Case
            if (str.length() > 1 && str.charAt(0) == 'p' && str.charAt(1) == 'i') {
                return changePiHelper(str.substring(2), result + "3.14");
            } else {
                return changePiHelper(str.substring(1), result + str.charAt(0));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("String xpix after replacing pi is: " + changePi("xpix"));
        System.out.println("String pipi after replacing pi is: " + changePi("pipi"));
        System.out.println("String pip after replacing pi is: " + changePi("pip"));
    }
}
