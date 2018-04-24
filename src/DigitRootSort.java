import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Digit root of some positive integer is defined as the sum of all of its digits.

 You are given an array of integers. Sort it in such a way that if a comes before b
 then the digit root of a is less than or equal to the digit root of b.
 If two numbers have the same digit root, the smaller one (in the regular sense)
 should come first. For example 4 and 13 have the same digit root, however 4 < 13
 thus 4 comes before 13 in any digitRoot sorting where both are present.

 Example

 For a = [13, 20, 7, 4], the output should be [20, 4, 13, 7].

 [time limit] 3000ms (java)
 [input] array.integer a

 Array of positive integers.

 [output] array.integer

 */
public class DigitRootSort {

    // 65785412
    //6+5 =11%9 = 2+7 = 9%9 = 0+8 = 8 %9 = 8+5 = 13 %9 = 4+4 = 8%9 = 8+1 = 9%9 = 0+2=2%9 = 2
    private static int findDigitRoot(String number) {

        //If number == 0 return 0
        if (number.equals("0")) {
            return 0;
        }

        //Numbers 1 -8 when modulo with 9 will return number itself.
        int answer = 0;
        for (int i = 0; i < number.length(); i++) {
            //Convert character to integer value
            int number1 = (int)(Character.getNumericValue(number.charAt(i)));
            //Add to answer and do modulo 9
            answer = (answer + number1) % 9;
        }

        if (answer == 0) {
            return 9;
        } else {
            return answer % 9;
        }
    }

    //If sum is greater then 9 then keep adding else return
    private static int findDigitRoot(Integer number) {

        if (number <= 9) {
            return number;
        } else {
            //Convert int to number
            String numberString = String.valueOf(number);

            int answer = 0;
            for (int i = 0; i < numberString.length(); i++) {
                answer = answer + Character.getNumericValue(numberString.charAt(i));
            }

            return findDigitRoot(answer);
        }

    }

    private static Integer[] digitRootSort(int[] a) {

        //Convert it to Integer array for Arrays.sort method
        Integer[] array = new Integer[a.length];

        for (int i = 0; i < a.length; i++) {
            array[i] = a[i];
        }

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                if (findDigitRoot(String.valueOf(o1)) == findDigitRoot(String.valueOf(o2))) {
                    return o1 - o2;
                } else {
                    return findDigitRoot(String.valueOf(o1)) - findDigitRoot(String.valueOf(o2));
                }

            }
        });

        return array;

    }

    public static void main(String[] args) {
        int[] a = {100, 22, 4, 11, 31, 103};

        Integer[] resultArray = digitRootSort(a);

        System.out.println(Arrays.toString(resultArray));

        int[] b = {13, 20, 7, 4};

        Integer[] resultArray1 = digitRootSort(b);

        System.out.println(Arrays.toString(resultArray1));

        int[] c = {14, 47, 39, 74, 21, 9, 4, 15};

        Integer[] resultArray2 = digitRootSort(c);

        System.out.println(Arrays.toString(resultArray2));
    }
}
