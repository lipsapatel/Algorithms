/**
 * Java String subSequence Example.
 *
 * Java 1.4 introduced CharSequence interface and String implements this interface. This is the only reason for the
 * implementation of subSequence method in String class.
 *
 * Internally it invokes the String substring method.
 *
 * There is no benefit of using subSequence method, ideally you should always use String substring method.
 */
public class StringSubsequence {

    public static void main(String[] args) {

        String string = "www.lipsapatel.com";

        System.out.println("Last 4 char String: " + string.subSequence(string.length() - 4, string.length()));
        System.out.println("First 4 char String: " + string.subSequence(0, 4));
        System.out.println("Website name: " + string.subSequence(4, 14));

        System.out.println("substring == subSequence? " + (string.substring(4, 14) == string.subSequence(4, 14)));
        System.out.println("substring equals subSequence? " + (string.substring(4, 14).equals(string.subSequence(4, 14))));
    }
}
