/**
 *
 Given a string, compute recursively (no loops) a new string where all the lowercase 'x' chars have been changed to 'y' chars.


 changeXY("codex") → "codey"
 changeXY("xxhixx") → "yyhiyy"
 changeXY("xhixhix") → "yhiyhiy"
 */
public class ChangeXY {

    private static String changeXY(String str) {

        return changeXYHelper(str, "");
    }

    private static String changeXYHelper(String str, String result) {
        //Base Case
        if (str.isEmpty()) {
            return result;
        } else { //Recursive Case
            if (str.charAt(0) == 'x') {
                return changeXYHelper(str.substring(1), result + "y");
            } else {
                return changeXYHelper(str.substring(1), result + str.charAt(0));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("String after replacing x with y in string codex is: " + changeXY("codex"));
        System.out.println("String after replacing x with y in string xxhixx is: " + changeXY("xxhixx"));
        System.out.println("String after replacing x with y in string xhixhix is: " + changeXY("xhixhix"));
    }
}
