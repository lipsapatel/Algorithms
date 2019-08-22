import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

 Example:

 Input: "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 Note:

 Although the above answer is in lexicographical order, your answer could be in any order you want.

 1)	Given map of character and String
 0 = “ “
 1 = “”
 2 = abc
 3 = def
 4 = ghi
 5 = jkl
 6 = mno
 7 = pqrs
 8 = tuv
 9 = wxyz
 And 7 digit number
 Generate all possible string combination

 If length of mapping characters = m
 and Length of digits = n

 Time Complexity = O(m^n)
 Space Complexity = O(n)

 */
public class LetterCombinationOfPhoneNumber {

    private static List<String> letterCombinations(String digits) {

        List<String> list = new ArrayList<>();

        if (digits.isEmpty()) {
            return list;
        }

        Map<Character, String> map = new HashMap<>();
        map.put('0', " ");
        map.put('1', "");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        letterCombinationsHelper(digits, 0, list, new char[digits.length()], map);
        return list;
    }

    private static void letterCombinationsHelper(String digits, int index, List<String> list, char[] result, Map<Character, String> map) {

        //Base Case
        if (index == digits.length()) {
            list.add(new String(result));
        } else { //Recursive Case

            String values = map.get(digits.charAt(index));

            if (!values.isEmpty()) { //For 1 because the value that maps to 1 is empty
                for (int i = 0; i < values.length(); i++) { //Options

                    result[index] = values.charAt(i); //Set value

                    letterCombinationsHelper(digits, index + 1, list, result, map); //Recursive call

                }
            } else {
                letterCombinationsHelper(digits, index + 1, list, result, map);
            }
        }
    }

    public static void main(String[] args) {
        String digit = "02";

        System.out.println("The different string combinations are: " + letterCombinations(digit).toString());
    }
}
