import java.util.HashMap;
import java.util.Map;

/**
 * Print maximum occuring character in a string
 *
 * Given a string, write an algorithm to find the character in string which occurs maximum number of times.
 * If more than one character has maximum and same count then print all of them
 *
 * Example
 *
 * Input- tutorial horizon
 Character: o has occurred max times:  3
 ----------------------
 Input- abcabcdefabcab
 Character: a has occurred max times:  4
 Character: b has occurred max times:  4

 Approach:

 1) Use HashMap - Character as key and count as value
 2) For every character in string, if character is present in hashmap then increments it's count in hashmap
 else store the character and its count as 1
 3) Keep track of max count so far in a separate variable
 4) At the end iterate through hashmap and print all the characters which has max count.

 Time Complexity: O(n)
 */
public class MaximumOccuringCharacter {

    private static void findMaximumOccuringCharacter(String input) {

        int maxCount = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);

            if (map.containsKey(c)) {
                int count = map.get(c);

                count++;
                map.put(c, count);

                if (maxCount < count) {
                    maxCount = count;
                }
            } else {
                map.put(c, 1);
            }
        }

        for (Map.Entry<Character, Integer> element: map.entrySet()) {

            if (element.getValue() == maxCount) {
                System.out.println("Character: " + element.getKey() + " has occurred max times: " + maxCount);
            }
        }
    }

    public static void main(String[] args) {
        String input = "tutorial horizon";
        System.out.println("Input: " + input);
        findMaximumOccuringCharacter(input);

        String input1 = "abcabcdefabcab";
        System.out.println("Input: " + input);
        findMaximumOccuringCharacter(input1);
    }
}
