/**
 * Given a string and a non-empty substring sub,
 * compute recursively the largest substring which starts and ends with sub and return its length.


 strDist("catcowcat", "cat") → 9
 strDist("catcowcat", "cow") → 3
 strDist("cccatcowcatxx", "cat") → 9
 */
public class StrDist {

    private static int strDist(String str, String sub) {
        return strDistHelper(str, sub, -1, -1, 0);
    }

    private static int strDistHelper(String str, String sub, int startIndex, int endIndex, int index) {
        //Base Case
        if (index > str.length() - sub.length()) {
            if (startIndex >= 0) {
                return endIndex - startIndex;
            } else {
                return 0;
            }
        } else { //Recursive Case
            if ((str.substring(index, index + sub.length())).equals(sub)) {
                if (startIndex < 0) {
                    startIndex = index;
                }
                endIndex = index + sub.length();
            }
            return strDistHelper(str, sub, startIndex, endIndex, index + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("The length of largest substring which starts and ends with sub in string catcowcat/cat is " + strDist("catcowcat", "cat"));
        System.out.println("The length of largest substring which starts and ends with sub in string catcowcat/cow is " + strDist("catcowcat", "cow"));
        System.out.println("The length of largest substring which starts and ends with sub in string cccatcowcatxx/cat is " + strDist("cccatcowcatxx", "cat"));
    }
}
