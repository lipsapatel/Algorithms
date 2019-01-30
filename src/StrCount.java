/**
 * Given a string and a non-empty substring sub, compute recursively the number of times that sub appears in the string,
 * without the sub strings overlapping.


 strCount("catcowcat", "cat") → 2
 strCount("catcowcat", "cow") → 1
 strCount("catcowcat", "dog") → 0
 */
public class StrCount {

    private static int strCount(String str, String sub) {
        //Base Case
        if (str.length() < sub.length()) {
            return 0;
        } else { //Recursive Case
            if ((str.substring(0, sub.length())).equals(sub)) {
                return 1 + strCount(str.substring(sub.length()), sub);
            } else {
                return strCount(str.substring(1), sub);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("The substring appears " + strCount("catcowcat", "cat") + " times in string catcowcat/cat");
        System.out.println("The substring appears " + strCount("catcowcat", "cow") + " times in string catcowcat/cow");
        System.out.println("The substring appears " + strCount("catcowcat", "dog") + " times in string catcowcat/dog");
    }
}
