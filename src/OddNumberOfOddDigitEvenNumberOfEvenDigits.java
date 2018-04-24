import java.util.ArrayList;

/**
 * Created by lpatel on 9/5/2017.
 */
public class OddNumberOfOddDigitEvenNumberOfEvenDigits {

    public static void main(String[] args) {
        ArrayList<String> inputList = new ArrayList<String>();

        inputList.add("22333");
        inputList.add("22233");



        for (String givenNumber: inputList) {
            int evenNumberCount = 0;
            int oddNumberCount = 0;
            for (int i = 0 ; i < givenNumber.length(); i++) {
                int number = Character.getNumericValue(givenNumber.charAt(i));
                if (number % 2 == 0) {
                    evenNumberCount++;
                } else {
                    oddNumberCount++;
                }
            }

            if (evenNumberCount % 2 == 0 && oddNumberCount % 2 != 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }


    }
}
