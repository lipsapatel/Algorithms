/**
 * Convert Number to base 3 String Representation
 *
 * Given a number convert it to base 3 representation
 *
 * Example
 * N = 35
 * Base 3 representation: 27 9 3 1 (1022)
 *
 * N = 50
 * Base 3 representation: 1212
 *
 * Approach
 *
 * Till the number is greater than 0, keep dividing by 3 and append remainder to the result (Append it at the beginning of the result)
 *
 * N = 35, result =””
 N = N/3 => 35/3 => 11 remainder =2 So
 N = 11, result =”2”
 N = N/3 => 11/3 => 3 remainder =2 So
 N = 3, result =”22”
 N = N/3 => 3/3 => 1 remainder =0 So
 N = 1, result =”022”
 N = N/3 => 1/3 => 0 remainder =1 So
 N = 0, result =”1022”
 */
public class ConvertToBase3 {

    private static String convertToBase3(int N) {

        String result = "";
        while(N > 0) {

            int rem = N % 3;
            N = N/3;
            result = rem + result;
        }
        return result;
    }

    public static void main(String[] args) {
        int N = 35;
        System.out.println("Base 3 representation of " + N + ": " + convertToBase3(N));

        N = 50;
        System.out.println("Base 3 representation of " + N + ": " + convertToBase3(N));
    }
}
