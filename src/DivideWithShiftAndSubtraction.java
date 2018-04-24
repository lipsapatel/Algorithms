/**
 * Divide two integers without using division, multiplication or mod operator.
 * If it is overflow, return MAX_INT
 *
 * Time Complexity: O(logn)
 */
public class DivideWithShiftAndSubtraction {

  private static int divideWithShiftAndSubtraction(int dividend, int divisor) {

      if (divisor == 0) {
          return Integer.MAX_VALUE;
      }

      if (dividend == Integer.MIN_VALUE && divisor == -1) {
          return Integer.MAX_VALUE;
      }

      //Get positive values
      long absDividend = Math.abs((long)dividend);
      long absDivisor = Math.abs((long)divisor);

      int result = 0;

      while(absDividend >= absDivisor) {

          //Calculate the number of left shift
          //At every left shift the divisor is double
          int numShift = 0;

          while(absDividend >= (absDivisor << numShift)) {
              numShift++;
          }

          result = result + (1 << (numShift - 1));

          //Dividend minus largest shifted divisor
          absDividend = absDividend - (absDivisor << (numShift - 1));

      }

      //Both are positive or both are negative
      if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
          return result;
      } else {
          return -result;
      }
  }

    public static void main(String[] args) {

        System.out.println(divideWithShiftAndSubtraction(-2147483648, -1));
        System.out.println(divideWithShiftAndSubtraction(12,0));
        System.out.println(divideWithShiftAndSubtraction(12,2));

    }
}
