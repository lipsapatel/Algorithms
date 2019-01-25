/**
 *
 Given a string, return recursively a "cleaned" string where adjacent chars that are the same have been reduced to a single char.
 So "yyzzza" yields "yza".


 stringClean("yyzzza") → "yza"
 stringClean("abbbcdd") → "abcd"
 stringClean("Hello") → "Helo"
 */
public class StringClean {

    private static String stringClean(String str) {
        return stringCleanHelper(str, "");
    }

    private static String stringCleanHelper(String str, String result) {
        //Base Case
        if (str.isEmpty()) {
            return result;
        } else { //Recursive Case
            if (!result.isEmpty() && result.charAt(result.length() - 1) == str.charAt(0)) {
                return stringCleanHelper(str.substring(1), result);
            } else {
                return stringCleanHelper(str.substring(1), result + str.charAt(0));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("String yyzzza after cleaning is: " + stringClean("yyzzza"));
        System.out.println("String abbbcdd after cleaning is: " + stringClean("abbbcdd"));
        System.out.println("String Hello after cleaning is: " + stringClean("Hello"));
    }
}
