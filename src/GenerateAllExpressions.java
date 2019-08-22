import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generate All Possible Expressions That Evaluate To The Given Target Value

 Problem Statement:
 You are given a string s of length n, containing only numerical characters ('0' - '9'). You are also given a non-negative number target.
 You have to put between each pair of numerical characters, one of ("", "*", "+") operators such that the expression you get will evaluate to the target value.
 You have to return ALL possible strings(expressions) that evaluate to target value.
 Putting "" (an empty string) operator between two numerical characters means, that the they are joined (e.g. 1""2 = 12). Also the join can be extended further (e.g. 1""2""3 = 123).

 Precedence of the operators matters. In higher to lower precedence:
 Join ("") > Multiplication ("*") > Addition ("+")

 The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution. After that, you can write a DP solution if you want.

 Input/Output Format For The Function:
 Input Format:
 There are two arguments.
 1) String s.
 2) Long integer target.

 Output Format:
 Return array of strings res, containing ALL possible strings that evaluate to the target value.
 You need not to worry about the order of strings in your output array. Like for s = "22" and target = 4, arrays ["2+2", "2*2"] and ["2*2", "2+2"] both will be accepted.
 Any string in the returned array should not contain any spaces. In the above example string "2+2" is expected, other strings containing any space like " 2+2", "2 + 2", "2 +2" etc. will give wrong answer.

 Input/Output Format For The Custom Input:
 Input Format:
 The first line of input should contain a string s, denoting input string. The second line should contain an integer target, denoting the target value as explained in problem statement section.
 If s = “222” and target = 24, then input should be:
 222
 24

Output Format:
 Let’s denote the size of res as m, where res is the resultant array of strings returned by solution function.
 Then, there will be m lines of output, where ith line contains a string res[i], denoting value at index i of res.
 For input s = “222” and target = 24, output will be:
 2+22
 22+2

 Constraints:
 1 <= n <= 13
 s contains only numerical characters ('0' - '9').
 0 <= target < 10^13

 Sample Test Cases:
 Sample Input:
 s = "222"
 target = 24

 Sample Output:
 ["22+2", "2+22"]

 Explanation:
 1) 22 + 2 = 24 (Here, we put "" operator between the first two characters and then put "+" operator between the last two characters.)
 2) 2 + 22 = 24 (Here, we put "+" operator between the first two characters and then put "" operator between the last two characters.)

 Notes:
 Suggested time in interview: 40 minutes.
 The “Suggested Time” is the time expected to complete this question during a real-life interview, not now in homework i.e. For the first attempt of a given homework problem, the focus should be to understand what the problem is asking, what approach you are using, coding it, as well as identifying any gaps that you can discuss during a TC session. Take your time, but limit yourself to 2 one hour sessions for most problems.
 Have a look at the solution provided by us, it contains detailed comments.

 Time Complexity:
 O((3^(n - 1)) * n).

 To solve the problem we just have to use brute force.
 Generate all possible expressions and evaluate them.
 Store the expressions that evaluates to the target.
 Now, first let's find how many different expressions possible to generate by putting either of 3 operators in between each pair of characters.
 We have 3 operators to put in n - 1 places (we have n characters in the given string hence n - 1 places between them).
 So simply it is 3^(n - 1). It means for given string of length n, we will have 3^(n - 1) different expressions to check.

 What will be the length of expressions?
 If we only put "" (join) operator then length of expression will be minimum and that will be n.
 Else if we put one of "+" or "*" operators at each of the n - 1 places, then length of the string will be maximum and that is 2 * n - 1.

 So in general we can write that length of any expression will be O(n).
 So, we have 3^(n - 1) different expressions with O(n) length. So, time complexity will be O((3^(n - 1)) * n).

 Auxiliary Space Used:
 O((3^(n - 1)) * n).
 In worst case all the generated expressions will evaluate to the given target.
 Try:

 s = "0000000000000"
 target = 0

 So in our answer we will store all 3^(n - 1) expressions of length O(n).

 Space Complexity:
 O((3^(n - 1)) * n).

 Auxiliary space used is O((3^(n - 1)) * n) and input size is O(n) hence O((3^(n - 1)) * n) + O(n) -> O((3^(n - 1)) * n).

 resources/generateAllExpressions.png
 */
public class GenerateAllExpressions {

    private static String[] generate_all_expressions(String s, long target) {

        List<String> result = new ArrayList<>();

        if (s.isEmpty()) {
            return result.toArray(new String[0]);
        }

        generateExpressionsHelper(s, 0, target, "", 0, 0, result);
        return result.toArray(new String[0]);
    }

    //This function calculates expresssion while in recursion so complexity is 3^n vs 3^n(n) in case of evaluating expression afterwards
    private static void generateExpressionsHelper(String s, int startIndex, long target, String expression, long valueSoFar, long valueAfterRightMostAdd, List<String> result) {

        //Base Case
        if (startIndex == s.length()) {

            if (valueSoFar == target) {
                result.add(expression);
            }
        } else { //Recursive Case

            //we will concat only, if starting pos is 0 b/c we can't start expression with + or *
            //example s:2345 ==> 2, 23, 234, 2345
            //s:2345 startIdx=1 ==> currStr = 3, 34, 345

            for (int i = startIndex; i < s.length(); i++) {

                String currString = s.substring(startIndex, i + 1);
                Long currNum = Long.parseLong(currString);

                //First time, expression don't start with + or *
                if (startIndex == 0) { //*

                    generateExpressionsHelper(s, i + 1, target, currString, currNum, currNum, result);
                } else {

                    //+
                    generateExpressionsHelper(s, i + 1, target, expression + "+" + currString, valueSoFar + currNum, currNum, result);

                    //*; 2 + 3 * 5 (5, 3) = (5 - 3) + (3 * 5) = 17
                    generateExpressionsHelper(s, i + 1, target, expression + "*" + currString, (valueSoFar - valueAfterRightMostAdd) + (valueAfterRightMostAdd * currNum), (valueAfterRightMostAdd * currNum), result);
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(generate_all_expressions("113", 14)));
    }

}
