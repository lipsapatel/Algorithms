import java.util.*;

/**
 * SEND + MORE = MONEY
 * The goal here is to assign each letter a digit from 0 to 9 so that arthmetic works out
 * correctly. The rules are that all occurrences of a letter must be assigned the same digit
 * and no digit can be assigned to more than one letter.
 *
 * 1) First create a list of all characters that need assigning to pass to solve
 * 2) If all characters are assigned, then return true if puzzle is solved otherwise return
 * false
 * 3) Otherwise consider first unassigned character
 * 4) for
 *   make that choice and then recursively try to assign the rest of the characters
 *   If recursion is successful, return true
 *   If !successful, unmake assignment and try another digit.
 *
 *   If all digits have been tried and nothing worked, return false to trigger backtracking.
 */
public class CryptArthmetic {

    //Get unique characters
    private static String getLettersToAssign(String string1, String string2, String result) {

        Set<Character> charSet = new HashSet<>();

        for (int i = 0; i < string1.length(); i++) {
            charSet.add(string1.charAt(i));
        }

        for (int i = 0; i < string2.length(); i++) {
            charSet.add(string2.charAt(i));
        }

        for (int i = 0; i < result.length(); i++) {
            charSet.add(result.charAt(i));
        }

        String lettersToAssign = "";

        for (char c: charSet) {
            lettersToAssign = lettersToAssign + c;
        }
        return lettersToAssign;
    }

    private static boolean assignLetter(char c, int digit, Map<Character, Integer> map) {
        //Check for duplicates
        Set<Integer> valuesSet = new HashSet<Integer>(map.values());
        if (map.values().size() != valuesSet.size()) {
            return false; //Duplicate digits assigned
        } else {
            map.put(c, digit);
            return true;
        }
    }

    private static void unassignLetter(char c, int digit, Map<Character, Integer> map) {
        map.remove(c);
    }

    private static boolean puzzleSolved(String string1, String string2, String result, HashMap<Character, Integer> map) {
        String num1 = "";

        for (int i = 0; i < string1.length(); i++) {
            int digit = map.get(string1.charAt(i));
            num1 = num1 + Integer.toString(digit);
        }

        String num2 = "";

        for (int i = 0; i < string2.length(); i++) {
            int digit = map.get(string2.charAt(i));
            num2 = num2 + Integer.toString(digit);
        }

        String total = "";

        for (int i = 0; i < result.length(); i++) {
            int digit = map.get(result.charAt(i));
            total = total + Integer.toString(digit);
        }

        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        int r = Integer.parseInt(total);

        if (n1 + n2 == r) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean cryptArthmetic(String string1, String string2, String result, HashMap<Character, Integer> map, String lettersToAssign) {

        if (lettersToAssign.isEmpty()) { //Base Case
            return puzzleSolved(string1, string2, result, map);
        }

        for (int d = 0; d <= 9; d++) { //Options

            if (assignLetter(lettersToAssign.charAt(0), d, map)) {

                if (cryptArthmetic(string1, string2, result, map, lettersToAssign.substring(1))) {
                    return true;
                }

                unassignLetter(lettersToAssign.charAt(0), d, map);
            }
        }
        return false; //Backtracking since nothing worked
    }

    public static void main(String[] args) {

        String s1 = "send";
        String s2 = "more";
        String result = "money";

        HashMap<Character, Integer> map = new HashMap<>();

        cryptArthmetic(s1, s2, result, map, getLettersToAssign(s1, s2, result));

        for (Map.Entry<Character, Integer> entry: map.entrySet()) {

            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
