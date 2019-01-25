/**
 *
 Given a string, compute recursively the number of times lowercase "hi" appears in the string,
 however do not count "hi" that have an 'x' immedately before them.


 countHi2("ahixhi") → 1
 countHi2("ahibhi") → 2
 countHi2("xhixhi") → 0
 */
public class CountHi2 {

    private static int countHi2(String str) {
        return countHi2Helper(str, ' ');
    }

    private static int countHi2Helper(String str, char prev) {
        //Base Case
        if (str.length() <= 1) {
            return 0;
        } else { //Recursive Case
            if (str.charAt(0) == 'h' && str.charAt(1) == 'i' && prev != 'x') {
                return 1 + countHi2Helper(str.substring(2), str.charAt(1));
            } else {
                return countHi2Helper(str.substring(1), str.charAt(0));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Lowercase hi appears in string ahixhi " + countHi2("ahixhi") + " times");
        System.out.println("Lowercase hi appears in string ahibhi " + countHi2("ahibhi") + " times");
        System.out.println("Lowercase hi appears in string xhixhi " + countHi2("xhixhi") + " times");
    }
}
