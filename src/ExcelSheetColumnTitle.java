/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

 For example:

 1 -> A
 2 -> B
 3 -> C
 ...
 26 -> Z
 27 -> AA
 28 -> AB
 ...
 Example 1:

 Input: 1
 Output: "A"
 Example 2:

 Input: 28
 Output: "AB"
 Example 3:

 Input: 701
 Output: "ZY"
 */
public class ExcelSheetColumnTitle {

    private static String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        //'A' = 65
        //n-- for A, so for B = 2
        //which is 'A' + 1
        //n = 1
        //n % 26 = 1
        //B
        //Do this until n is greater than 0
        while(n>0){
            n--;
            result.insert(0, (char)('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int n = 1005;
        System.out.println(n + ": " + convertToTitle(n));
    }
}
