/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.

 Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

 Note: Each term of the sequence of integers will be represented as a string.



 Example 1:

 Input: 1
 Output: "1"
 Example 2:

 Input: 4
 Output: "1211"

 The following are the terms from n=1 to n=10 of the count-and-say sequence:
 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 6.     312211
 7.     13112221
 8.     1113213211
 9.     31131211131221
 10.     13211311123113112211
 To generate the nth term, just count and say the n-1th term.
 */
public class CountAndSay {

    public static String countAndSay(int n) {

        //Base Case
        if (n == 1) {
            return "1";
        } else { //recursive case
            String s = countAndSay(n - 1);
            StringBuilder result = new StringBuilder();

            int i = 0;
            while (i < s.length()) {
                int count = 1;
                while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                    count++;
                    i++;
                }
                result = result.append(Integer.toString(count)).append(s.charAt(i));
                i++;
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }
}
