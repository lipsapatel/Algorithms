import java.util.HashSet;

/**
 * Given a string and dictionary of words, find out if the input string can be broken into space
 * separated sequence of one or more dictionary words.
 *
 * Example:
 * dictionary = ["I", "am", "Lipsa", "This", "is", "dog"];
 * String = "IamLipsa";
 *
 * Output: String can be broken - true
 *
 * String = "Thisisadog";
 *
 * Output: String can be broken - false
 *
 * Approach: Backtracking
 *
 * 1) Take blank string and add one character at time
 * 2) Check if dictionary contains word
 * 3) If yes then add that word to the answer string and make a recursive call
 * 4) If any of the recursive call return false, then backtrack and remove word from answer string
 *
 * resources/WordBreakProblem.png
 *
 * We are solving many sub-problems recursively
 *
 * Dynamic Programming
 *
 * 1) Top-Down approach
 * 2) Before solving for any string check if we have already solved it
 * 3) Another HashSet to store the result of already solved string
 * 4) Whenever any recursive call returns false, store it in hashset
 *
 */
public class WordBreakProblem {

    private static void wordBreakProblem_UsingRecursion(String s, HashSet<String> dictionary) {
        if (isWorkBreak(s, dictionary, "")) {

        } else {
            System.out.println("Can't break the string");
        }

    }

    private static boolean isWorkBreak(String s, HashSet<String> dictionary, String answer) {

        if (s.length() == 0) {
            System.out.println(answer);
            return true;
        } else {
            int index = 0;
            String word = "";

            while(index < s.length()) {

                //One character at a time
                word = word + s.charAt(index);

                //If dictionary contains word
                if (dictionary.contains(word)) {

                    //add word to the answer and make recursive call
                    if (isWorkBreak(s.substring(index+1), dictionary, answer + word + " ")) {
                        return true;
                    } else {
                        index++;
                    }
                } else {
                    index++;
                }
            }
            return false;
        }
    }

    private static void wordBreakProblem_UsingDynamicProgramming(String s, HashSet<String> dictionary) {

        HashSet<String> memory = new HashSet<String>();

        if (isWordBreak(s, dictionary, "", memory)) {

        } else {
            System.out.println("Can't break the string");
        }
    }

    private static boolean isWordBreak(String s, HashSet<String> dictionary, String answer,
                                       HashSet<String> memory) {

        if (s.length() == 0) {
            System.out.println(answer);
            return true;
        } else if (memory.contains(s)) {
            return false;
        } else {

            int index = 0;
            String word = "";

            while(index < s.length()) {

                //One character at a time
                word = word + s.charAt(index);

                //Check the dictionary
                if (dictionary.contains(word)) {

                    //Make recursive call
                    if (isWordBreak(s.substring(index + 1), dictionary, answer + word + " ", memory)) {
                        return true;
                    } else {
                        index++;
                    }
                } else {
                    index++;
                }
            }
            memory.add(s);
            return false;
        }
    }

    public static void main(String[] args) {

        String s = "IamLipsa";
        String s1 = "Thisisadog";
        HashSet<String> dictionary = new HashSet<String>();
        dictionary.add("I");
        dictionary.add("am");
        dictionary.add("Lipsa");
        dictionary.add("This");
        dictionary.add("is");
        dictionary.add("dog");

        wordBreakProblem_UsingRecursion(s, dictionary);
        wordBreakProblem_UsingRecursion(s1, dictionary);

        wordBreakProblem_UsingDynamicProgramming(s, dictionary);
        wordBreakProblem_UsingDynamicProgramming(s1, dictionary);
    }
}
