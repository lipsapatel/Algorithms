/**
 * Given a string, compute recursively a new string where all the adjacent chars are now separated by a "*".


 allStar("hello") → "h*e*l*l*o"
 allStar("abc") → "a*b*c"
 allStar("ab") → "a*b"

 */
public class AllStar {

    private static String allStar(String str) {
        return allStarHelper(str, "");
    }

    private static String allStarHelper(String str, String result) {
        //Base Case
        if (str.length() <= 1) {
            return result + str;
        } else { //Recursive Case
            return allStarHelper(str.substring(1), result + str.charAt(0) + "*");
        }
    }

    public static void main(String[] args) {
        System.out.println("String hello after separating all adjacent chars with *: " + allStar("hello"));
        System.out.println("String abc after separating all adjacent chars with *: " + allStar("abc"));
        System.out.println("String ab after separating all adjacent chars with *: " + allStar("ab"));
        System.out.println("String  after separating all adjacent chars with *: " + allStar(""));
    }
}
