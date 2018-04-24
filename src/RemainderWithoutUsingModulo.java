/**
 * Given two number, dividend and divisor
 * Write an algorithm to find remainder if dividend is divided by divisor
 *
 * You are not allowed to use % or modulo operator
 *
 * Calculate number of left shift
 * At every left shift divisor is doubled.
 *
 * Return the dividend that remained
 *
 * Time Complexity: O(logn)
 */
public class RemainderWithoutUsingModulo {

    private static int remainderWithoutUsingModulo(int dividend, int divisor) {

        //If divisor is 0 then remainder is the dividend itself
        if (divisor == 0) {
            return dividend;
        }

        //The remainder will be zero
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return 0;
        }

        //Get positive values
        long absDividend = Math.abs((long)dividend);
        long absDivisor = Math.abs((long)divisor);

        while(absDividend >= absDivisor) {

            //Calculate number of left shift
            //At every left shift, divisor is doubled
            int numShift = 0;

            while (absDividend >= (absDivisor << numShift)) {
                numShift++;
            }

            //Dividend minus the largest shift divisor
            absDividend = absDividend - (absDivisor << (numShift - 1));
        }

        //Finally the absDividend will be the remainder
        //If dividend is negative the remainder will be negative else it will be positive
        if (dividend < 0) {
            return  -(int)absDividend;
        } else {
            return (int)absDividend;
        }
    }

    public static void main(String[] args) {

        System.out.println(remainderWithoutUsingModulo(-2147483648, -1)); //0
        System.out.println(remainderWithoutUsingModulo(12,0)); //12
        System.out.println(remainderWithoutUsingModulo(12,5)); //2
        System.out.println(remainderWithoutUsingModulo(10,4)); //2

    }

}
