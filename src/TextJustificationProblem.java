/**
 * Text Justification Problem
 *
 * Given a list of words and length L, format the words so that each line with have only L characters and fully justified
 * (left and right justified)
 *
 * Restrictions or Assumptions:
 *
 * 1) You are not allowed to split a word and put them in next line.
 * 2) Pad extra spaces ' ' if needed so that each line has L characters
 * 3) Extra Spaces between words should be distributed as evenly as possible.
 * 4) If even distribution is not possible then extra spaces will be assigned on the left most space (between two words)
 * 5) Each word has length less than L
 *
 * Example:
 *
 * String[] words = {"This", "is", "a", "text", "justification", "problem", "in", "tutorial", "horizon"};
 * int length = 20;
 *
 * Output:
 * This   is   a   text
 * justification
 * problem  in tutorial
 * horizon
 *
 * Greedy approach
 * Not very optimal
 *
 * Approach: Recursion
 *
 * 1) Maintain an index which tracks the number of words already justified.
 * 2) Take currentLength = 0 and remainingLength = maxWidth.
 * 3) Take result string for line
 * 4) While result.length < maxWidth && index < words.length
 * 5) If remainingLength is less than the length of word + 1 for space
 * 6) Add word to the result String with "@" in between and update remainingLength
 * 7) Else if remainingLength > 0 and there's no "@" in the result String, add all spaces at
 * the end
 * 8) Else find the splitted space by dividing remainingLength by no of words - 1
 * 9) Find the extraSpaceMod by taking modulos.
 * 9) LeftmostSpace = splittedSpace + extraSpaceMod
 * 10) Add splittedSpace between all other words.
 * 11) Make recursive call for rest of the words.
 */
public class TextJustificationProblem {

    private static String justifyText(String[] words, int maxWidth, int index) {

        //Index should be less than words length
        if (index < words.length) {

            String result = "";
            int currentLength = 0;
            int remainingLength = maxWidth;

            while(result.length() < maxWidth && index < words.length) {

                //Add words[index] to result with @
                if (remainingLength >= words[index].length() + 1) { //+1 is for space

                    if (result.equals("")) { //Add first word

                        result += words[index];
                        currentLength = words[index].length();

                    } else { //Add "@" and then word

                        result += "@" + words[index];
                        currentLength = words[index].length() + 1;

                    }

                    remainingLength -= currentLength;
                    index++;

                } else if (remainingLength > 0) { //Insert that many spaces

                    //If does not contains "@" then its only one word, add spaces at the end
                    if (!result.contains("@")) {

                        for (int i = 0; i < remainingLength; i++) {
                            result = result + " ";
                        }
                    } else {

                        //Split the result string using "@"
                        String[] array = result.split("@");

                        //Extra space mod
                        int extraSpaceMod = remainingLength % (array.length - 1);

                        //Splitted space
                        int splittedSpace = remainingLength / (array.length - 1);

                        //Add the spaces
                        String spaces = " "; //"@" is also one space.

                        for (int i = 0; i < splittedSpace; i++) {
                            spaces = spaces + " ";
                        }

                        String leftMostSpaces = spaces;
                        for (int i = 0; i < extraSpaceMod; i++) {
                            leftMostSpaces = leftMostSpaces + " ";
                        }

                        result = result.replaceFirst("@", leftMostSpaces);
                        result = result.replaceAll("@", spaces);
                    }
                }
            }

            result = result.replaceAll("@", " ");
            return  result + "\n" + justifyText(words, maxWidth, index);

        } else {
            return "";
        }

    }

    public static void main(String[] args) {
        String [] words = {"This", "is", "a", "text", "justification","problem","in","tutorial","horizon"};
        int maxWidth = 25;
        System.out.println(justifyText(words,maxWidth,0));
    }
}
