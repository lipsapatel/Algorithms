/**
 * Build the lowest number by removing n digits from number
 * If n = 0 there's nothing to remove so return the whole number as a result
 * If n >= length the result would be empty
 *
 * find the lowest number from first n+1 digits
 * Append it to the result
 *
 * make a recursive call from index of lowest number +1 to length of string and
 * n = n - that index
 *
 */
public class buildLowestNumber {

    private static void buildLowestNumber(String givenString, int number, StringBuilder result) {

        if (number == 0) {
            result.append(givenString);
            return;
        }

        int length = givenString.length();

        if (length <= number) {
            return;
        }

        //Find the smallest character among first (n+1) characters of str
        int minIndex = 0;
        for (int i = 1; i <=number; i++) {
            if (givenString.charAt(i) < givenString.charAt(minIndex)) {
                minIndex = i;
            }
        }

        result.append(String.valueOf(givenString.charAt(minIndex)));

        String newString = givenString.substring(minIndex+1, length);

        buildLowestNumber(newString, number-minIndex, result);

    }

    private static String buildLowestNumber(String string, int number) {
        StringBuilder result = new StringBuilder();
        buildLowestNumber(string, number, result);
        return result.toString();
    }

    public static void main(String[] args) {
        String string = "4325043";
        int number = 3;
        System.out.println("The Lowest number is " + buildLowestNumber(string, number));

        String string1 = "765028321";
        int number1 = 5;
        System.out.println("The Lowest number is " + buildLowestNumber(string1, number1));

        String string2 = "121198";
        int number2 = 2;
        System.out.println("The Lowest number is " + buildLowestNumber(string2, number2));
    }
}


