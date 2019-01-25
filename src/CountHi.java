/**
 * Given a string, compute recursively (no loops) the number of times lowercase "hi" appears in the string.


 countHi("xxhixx") → 1
 countHi("xhixhix") → 2
 countHi("hi") → 1
 */
public class CountHi {

    private static int countHi(String str) {
        //Base Case
        if (str.isEmpty() || str.length() == 1) {
            return 0;
        } else { //Recursive Case
            if (str.charAt(0) == 'h' && str.charAt(1) == 'i') {
                return 1 + countHi(str.substring(2));
            } else {
                return countHi(str.substring(1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Number of hi in string xxhixx are: " + countHi("xxhixx"));
        System.out.println("Number of hi in string xhixhix are: " + countHi("xhixhix"));
        System.out.println("Number of hi in string hi are: " + countHi("hi"));
    }
}
