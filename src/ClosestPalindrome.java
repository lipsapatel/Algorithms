/**Given an integer n, find the closest integer (not including itself),
 * which is a palindrome.

 The 'closest' is defined as absolute difference minimized between two integers.

        Example 1:

        Input: "123"
        Output: "121"

        Note:

        The input n is a positive integer represented by string,
        whose length will not exceed 18.
        If there is a tie, return the smaller one as answer.

 Approach:

 Case 1:

 To understand this method, let's start with a simple illustration.
 Assume that the number given to us is "abcxy".
 One way to convert this number into a palindrome is to replicate one half of the
 string to the other half.

 If we try replicating the second half to the first half,
 the new palindrome obtained will be "yxcxy" which lies at
 an absolute of ∣10000(a−y)+1000(b−x)| from the original number.
 But, if we replicate the first half to the second half of the string,
 we obtain "abcba", which lies at an absolute
 difference of ∣10(x−b)+(y−a)∣. Trying to change c additionally in
 either case would incur an additional value of atleast 100 in the absolute difference.

 From the above illustration, we can conclude that if replication
 is used to generate the palindromic number,
 we should always replicate the first half to the second half. In this implementation, we've stored such a number in
 a at a difference of diff1 from n.

 Case 2: 10 to 9
 If 0 is in the middle, change to 9, decrement by 1

 Case 3: 9 to 10
 If 9 is in the middle, change to 0, increment by 1

 Out of these three palindromes, we can choose the one with a
 minimum difference from nnn. Further, in case of a tie,
 we need to return the smallest palindrome obtained.
 For resolving this tie's conflict,
 we can observe that a tie is possible only if one number is larger
 than nnn and another is lesser than n.

 Further, we know that the number bbb is obtained by decreasing nnn.
 Thus, in case of conflict between bbb and
 any other number, we need to choose bbb. Similarly, ccc is obtained by increasing nnn. Thus, in case of a tie between c
 and any other number, we need to choose the number other than c.

 Time complexity : O(n). Scanning, insertion, deletion,, mirroring takes O(n),
 where n is the length of the string.
 Space complexity : O(n). Temporary variables are used to store the strings.

 resource/ClosestPalindrome.jpg
 **/
public class ClosestPalindrome {

    private static String findClosestPalindrome(String n) {

        if(n.equals("1")) {
            return "0";
        }

        //Case 1: Mirror
        //2134 = 2112
        //21345 = 21312
        String a = getMirror(n);
        long diff1 = Long.MAX_VALUE;

        //|n - a|
        diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));

        //It cannot be the number itself
        if(diff1 == 0) {
            diff1 = Long.MAX_VALUE;
        }

        //Case 2: Middle number is 0 so replace 10 with 9, decrement by 1 and remove 1
        StringBuilder s = new StringBuilder(n);

        //Replace 0 with 9
        int mid = (s.length() - 1)/2;
        while(mid >= 0 && s.charAt(mid) == '0') {
            s.replace(mid, mid + 1, "9");
            mid--;
        }

        if(mid == 0 && s.charAt(mid) == '1') { //Remove 1
            s.delete(0, 1);
            int newMid = (s.length() - 1)/2;
            s.replace(newMid, newMid + 1, "9");
        } else { //decrement by 1
            s.replace(mid, mid + 1, "" + (char)(s.charAt(mid) - 1));
        }
        String b = getMirror(s.toString());

        //|n - b|
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));

        //Case 3: Middle number is 9, replace 9 with 0, increment by 1 and add 1
        s = new StringBuilder(n);

        //Replace 9 with 0
        mid = (s.length() - 1)/2;
        while(mid >= 0 && s.charAt(mid) == '9') {
            s.replace(mid, mid + 1, "0");
            mid--;
        }

        if(mid < 0) { //Add 1
            s.insert(0, "1");
        } else { //increment by 1
            s.replace(mid, mid + 1, "" + (char)(s.charAt(mid) + 1));
        }

        String c = getMirror(s.toString());

        //|n - c|
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

        //In case of tie take the min and min is case 2 which is diff 2
        if (diff2 <= diff1 && diff2 <= diff3) {
            return b;
        }
        if(diff1 <= diff2 && diff1 <= diff3) {
            return a;
        } else {
            return c;
        }
    }

    public static void main(String[] args) {
        String n = "20007";
        System.out.println("The closest palindrome is: " + findClosestPalindrome(n));
    }

    private static String getMirror(String s) {
        int mid = s.length()/2;
        String x = s.substring(0, mid);

        //If string is odd, append the mid char
        String midChar = "";
        if(s.length() % 2 == 1) {
            midChar = Character.toString(s.charAt(mid));
        }

        return x + midChar + new StringBuilder(x).reverse();
    }
}
