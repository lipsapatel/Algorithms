/**
 *
 Given a string that contains a single pair of parenthesis,
 compute recursively a new string made of only of the parenthesis and their contents, so "xyz(abc)123" yields "(abc)".


 parenBit("xyz(abc)123") → "(abc)"
 parenBit("x(hello)") → "(hello)"
 parenBit("(xy)1") → "(xy)"
 */
public class ParentBit {

    private static String parentBit(String str) {
        return parentBitHelper(str, str, 0, 0);
    }

    private static String parentBitHelper(String str, String origStr, int openIndex, int closeIndex) {

        //Base Case
        if (str.isEmpty()) {
            if (openIndex < closeIndex) {
                return origStr.substring(openIndex, closeIndex + 1);
            } else {
                return "";
            }
        } else { //Recursive Case
            if (str.charAt(0) == '(') {
                return parentBitHelper(str.substring(1), origStr, origStr.indexOf('('), closeIndex);
            } else if (str.charAt(0) == ')') {
                return parentBitHelper(str.substring(1), origStr, openIndex, origStr.indexOf(')'));
            } else {
                return parentBitHelper(str.substring(1), origStr, openIndex, closeIndex);
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("Parenthesis string for xyz(abc)123 is : " + parentBit("xyz(abc)123"));
        System.out.println("Parenthesis string for x(hello) is : " + parentBit("x(hello)"));
        System.out.println("Parenthesis string for (xy)1 is : " + parentBit("(xy)1"));
    }
}
