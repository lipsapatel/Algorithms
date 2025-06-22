import java.util.ArrayList;
import java.util.HashMap;

/**
 * "Have the function StringExpression(str) read the str parameter being passed
 * which will contain the written out version of the numbers 0-9 and
 * the words ""minus"" or ""plus"" and convert the expression into an actual final number written out as well.
 * For example: if str is ""foursixminustwotwoplusonezero"" then this converts to ""46 - 22 + 10""
 * which evaluates to 34 and
 * your program should return the final string threefour.
 * If your final answer is negative it should include the word ""negative.""
 *
 */
public class CalculateExpression {

    public static void main(String[] args) {

        String str = "foursixminustwotwoplusonezero";

        //StringExpression
        String result = StringExpression(str);
        System.out.println(result);
    }

    private static String StringExpression(String str) {

        //Create number dictionary and operation dictionary
        HashMap<String, Integer> numberDictionary = new HashMap<>();

        numberDictionary.put("one", 1);
        numberDictionary.put("two", 2);
        numberDictionary.put("three", 3);
        numberDictionary.put("four", 4);
        numberDictionary.put("five", 5);
        numberDictionary.put("six", 6);
        numberDictionary.put("seven", 7);
        numberDictionary.put("eight", 8);
        numberDictionary.put("nine", 9);
        numberDictionary.put("zero", 0);

        HashMap<String, String> operationDictionary = new HashMap<>();
        operationDictionary.put("minus", "-");
        operationDictionary.put("plus", "+");

        StringBuilder word = new StringBuilder();

        StringBuilder expression = new StringBuilder(); //This holds "46 - 22 + 10"

        for(int i = 0; i < str.length(); i++) {

            word.append(str.charAt(i));

            //Convert toString here once
            String currentString = word.toString();

            //Check if word present in number dictionary
            if(numberDictionary.containsKey(word.toString())) {
                int number = numberDictionary.get(word.toString());

                expression.append(number);

                //Reset/clear StringBuilder. Use setLength(0) instead of creating new StringBuilder()
                //This will not create new objects in memory so it is memory efficient
                word.setLength(0);

            } else if(operationDictionary.containsKey(currentString)){

                String operation = operationDictionary.get(currentString);

                expression.append(operation);

                //Reset/clear StringBuilder
                word.setLength(0);
            }
        }

        return expression.toString();

    }
}
