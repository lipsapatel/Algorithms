/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:

 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
 ...
 Example 1:

 Input: "A"
 Output: 1
 Example 2:

 Input: "AB"
 Output: 28
 Example 3:

 Input: "ZY"
 Output: 701
 */
public class ExcelSheetColumnNumber {

    private static int titleToNumber(String s) {

        char[] charArray = s.toCharArray();
        int length = charArray.length;
        int result = 0;

        for (int j = length - 1, i = 0; j >= 0 && i < length; i++, j--) {

            result += (int) ((charArray[j] - 'A' + 1) * Math.pow(26, i)); //To get the mapping value Z - A + 1 will give 26
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Column Number: " + titleToNumber("ZY"));
    }
}
