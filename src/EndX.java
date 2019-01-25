/**
 * Given a string, compute recursively a new string where all the lowercase 'x' chars have been moved to the end of the string.


 endX("xxre") → "rexx"
 endX("xxhixx") → "hixxxx"
 endX("xhixhix") → "hihixxx"
 */
public class EndX {

    private static String endX(String str) {
        return endXHelper(str, "", "");
    }

    private static String endXHelper(String str, String rest, String xStr) {
        //Base Case
        if (str.isEmpty()) {
            return rest + xStr;
        } else { //Recursive Case
            String rem = rest, strX = xStr;
            if (str.charAt(0) == 'x') {
                strX = strX + str.charAt(0);
            } else {
                rem = rem + str.charAt(0);
            }
            return endXHelper(str.substring(1), rem, strX);
        }
    }

    public static void main(String[] args) {
        System.out.println("String xxre after moving lowercase x at the end: " + endX("xxre"));
        System.out.println("String xxhixx after moving lowercase x at the end: " + endX("xxhixx"));
        System.out.println("String xhixhix after moving lowercase x at the end: " + endX("xhixhix"));
        System.out.println("String  after moving lowercase x at the end: " + endX(""));
    }
}
