/**
 * Given a decimal number convert it into irreducible fraction.
 *
 * Irreducible Fraction: An irreducible fraction is fraction in which numerator
 * and denominator are integers that have no common divisor
 *
 * For example 7/20, 1/4, 1/2, 6/5
 *
 * Example: 0.35
 * Output = 35/100 = 7/20
 *
 * Example: 1.2
 * Output = 6/5
 *
 * Split using the decimal
 * Find the length after decimal
 * Calculate denominator = Math.pow(10, length)
 * Calculate numerator = double x * denominator = 0.35 * 100
 * Find the greatest common divisor between numerator and denominator
 * Irreducible fraction = numerator/gcd "/" denominator/gcd
 *
 * To find gcd of (n1, n2)
 *
 * If n2 = 0 then return n1
 * Make recursive call to gcd(n2, n1%n2)
 *
 * Best example is 5/20  = (20, 5 % 20 = 5)  = (5, 20 % 5 = 0) so return 5
 */
public class ConvertDecimalIntoIrreducibleFraction {

    private static void convertDecimalIntoIrreducibleFraction(double number) {

        //Convert double to string
        String numberString = Double.toString(number);

        //Now split it using "."
        String[] splitNumberString = numberString.split("\\.");

        //Find the length
        int afterDecimalLength = splitNumberString[1].length(); //2

        //Calculate denominator
        int denominator = (int) Math.pow(10, afterDecimalLength); //100

        //Calculate numerator
        int numerator = (int) (number * denominator); //0.35 * 100 = 35

        int gcd = getGcd(numerator, denominator); //5

        String irreducibleFraction = "" + numerator/gcd + "/" + denominator/gcd;

        System.out.println("The irreducible fraction for " + numberString + " is " + irreducibleFraction);
    }

    private static int getGcd(int n1, int n2) {

        if (n2 == 0) {
            return n1;
        }

        return getGcd(n2, n1 % n2);
    }

    public static void main(String[] args) {

        convertDecimalIntoIrreducibleFraction(0.35);
        convertDecimalIntoIrreducibleFraction(1.2);
    }
}
