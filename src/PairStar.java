/**
 * Given a string, compute recursively a new string where identical chars that are adjacent in the original
 * string are separated from each other by a "*".


 pairStar("hello") → "hel*lo"
 pairStar("xxyy") → "x*xy*y"
 pairStar("aaaa") → "a*a*a*a"
 */
public class PairStar {

    private static String pairStar(String str) {
        return pairStarHelper(str, "");
    }

    private static String pairStarHelper(String str, String result) {
        //Base Case
        if (str.isEmpty()) {
            return result;
        } else { //Recursive Case
            if (!result.isEmpty() && result.charAt(result.length() - 1) == str.charAt(0)) { //Adjacent
                return pairStarHelper(str.substring(1), result + "*" + str.charAt(0));
            } else {
                return pairStarHelper(str.substring(1), result + str.charAt(0));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("String hello after separating identical chars with * is: " + pairStar("hello"));
        System.out.println("String xxyy after separating identical chars with * is: " + pairStar("xxyy"));
        System.out.println("String aaaa after separating identical chars with * is: " + pairStar("aaaa"));
        System.out.println("String  after separating identical chars with * is: " + pairStar(""));
    }
}
