/**
 * Given a string, compute recursively (no loops) the number of lowercase 'x' chars in the string.


 countX("xxhixx") → 4
 countX("xhixhix") → 3
 countX("hi") → 0
 */
public class CountX {

    private static int countX(String str) {
        //Base Case
        if (str.isEmpty()) {
            return 0;
        } else { //Recursive Case
            if (str.charAt(0) == 'x') {
                return 1 + countX(str.substring(1));
            } else {
                return countX(str.substring(1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("The number of lowercase x in string - xxhixx is: " + countX("xxhixx"));
        System.out.println("The number of lowercase x in string - xhixhix is: " + countX("xhixhix"));
        System.out.println("The number of lowercase x in string - hi is: " + countX("hi"));
    }
}
