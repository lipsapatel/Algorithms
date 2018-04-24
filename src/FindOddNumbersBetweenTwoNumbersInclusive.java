import java.util.ArrayList;
import java.util.Arrays;

/**
 * Find odd numbers between two given numbers inclusive
 * given number : 2, 5
 * output: 3,5
 * output is an array
 */
public class FindOddNumbersBetweenTwoNumbersInclusive {

    private static Integer[] findOddNumbers(int number1, int number2) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = number1; i <= number2; i++) {
            if (i%2 == 1) {
                result.add(i);
            }
        }
        return result.toArray(new Integer[result.size()]);
    }
    public static void main(String[] args) {
        int number1 = 2;
        int number2 = 5;

        System.out.println("Odd number between " + number1 + " and " + number2 + " are " + Arrays.toString(findOddNumbers(number1, number2)));
    }
}
