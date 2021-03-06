import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Strings From Wild Card

 Problem Statement:
 You are given string s of length n, having m wildcard characters '?', where each wildcard character represent
 a single character. Write a program which returns list of all possible distinct strings that can be generated by replacing each wildcard characters in s with either '0' or '1'.
 Any string in returned list must not contain '?' character i.e. you have to replace all '?' with either '0' or '1'.

 The purpose of this problem is to learn recursion and not DP. So, you must write at least one recursive solution. After that, you can write a DP solution if you want.

 Input Format:
 There is only one argument s, denoting input string.

 Output Format:
 Return a result list of distinct strings (No fix order of strings in result list is required).

 Constraints:
 1 <= n <= 50, where n is length of s.
 0 <= m <= 17, where m is number of ‘?’ (wildcard characters) in s.

 Sample Test Cases:
 Sample Test Case 1:
 Sample Input 1:
 s = “1?10”

 Sample Output 1:
 result = ["1010", "1110"] or ["1110", "1010"].

 Explanation 1:
 ‘?’ at index 1 (0 based indexing) can be replaced with either '0' or '1'. So, generated two strings replacing '?' with ‘0’ and ‘1’.

 Sample Test Case 2:
 Sample Input 2:
 s = “1?0?”

 Sample Output 2:
 result = ["1000", "1001", "1100", "1101"] or any other list having same strings but in different order.
 Explanation 2:
 Input string have two '?' characters. Each one can be replaced with either '0' or '1'. So, total 2*2 strings are possible as ('?' at index 1, '?' at index 3) can be replaced with ('0','0'), ('0','1'), ('1', '0'), ('1', '1').

 *******************************************************************************************************************************************
 Approach

 2) optimal_solution.java
 Description:
 Better approach would be only to generate valid possible strings matching with s instead of generating all possible strings.
 For doing this we can replace every ‘?’ with ‘0’ and ‘1’. This approach would be a recursive approach. Because every time after replacing a single ‘?’ at index i ( 0 <= i <= n-1 where n is length of s ) we are trying to replace remaining ‘?’ in string having index j ( i < j < n-1 ).

Time Complexity:
 O(n*(2^m)) where n is length of s string and m is number of ‘?’ (wild card) characters in s string.
 As, two recursive calls will be made when s[i] == '?', and there are total m '?' and we are generating that possible string when we are reaching at the end of s to be added in final result list.
So, total time complexity will be O(n*(2^m)).

 Auxiliary Space Used:
 O(n) where n is length of given string.
 As at any time we are maintaining current possible string generated in the solution and length of that string can be n.

 Space Complexity:
 O(n*(2^m)) where n is length of s string and m is number of ‘?’ (wild card) characters in s string.
 To store input, it will take O(n) and result list will contains number of strings equal to 2^(number of ‘?’ characters in s i.e. m) so, space for result list will be O(n *(2^m)) and auxiliary space used is taking O(n).
 Total space complexity will be
 O(n*(2^m)) + O(n) → O(n*(2^m))

 */
public class PossibleStringsFromWildCard {

    private static String[] find_all_possibilities(String s) {

        List<String> result = new ArrayList<>();
        findAllPossibilities(s, 0, result, new char[s.length()]);
        return result.toArray(new String[0]);
    }

    private static void findAllPossibilities(String s, int i, List<String> result, char[] res) {

        //Base Case
        if (i == s.length()) {
            result.add(new String(res));
        } else { //Recursive Case

            if (s.charAt(i) == '?') {
                //0
                res[i] = '0';
                findAllPossibilities(s, i + 1, result, res);

                //1
                res[i] = '1';
                findAllPossibilities(s, i + 1, result, res);
            } else {
                res[i] = s.charAt(i);
                findAllPossibilities(s, i + 1, result, res);
            }
        }
    }

    public static void main(String[] args) {
        String s = "1?1?";
        System.out.println(Arrays.toString(find_all_possibilities(s)));
    }
}
