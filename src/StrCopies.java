/**
 * Given a string and a non-empty substring sub, compute recursively
 * if at least n copies of sub appear in the string somewhere, possibly with overlapping. N will be non-negative.


 strCopies("catcowcat", "cat", 2) → true
 strCopies("catcowcat", "cow", 2) → false
 strCopies("catcowcat", "cow", 1) → true
 */
public class StrCopies {

    private static boolean strCopies(String str, String sub, int n) {
        if (str.length() < sub.length()) {
            return n == 0;
        } else {
            if ((str.substring(0, sub.length())).equals(sub)) {
                return strCopies(str.substring(1), sub, n - 1);
            } else {
                return strCopies(str.substring(1), sub, n);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("String catcowcat contains cat 2 times: " + strCopies("catcowcat", "cat", 2));
        System.out.println("String catcowcat contains cow 2 times: " + strCopies("catcowcat", "cow", 2));
        System.out.println("String catcowcat contains cow 1 times: " + strCopies("catcowcat", "cow", 1));
    }
}
