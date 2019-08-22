/**
 * Write an algorithm to calculate power(k, n)
 * K^n
 *
 * This handles both the case where n is positive and negative.
 *
 * For example
 * k = 4 and n = 5
 * 4 ^5 = 4*4*4*4*4 = 1024
 *
 * k = 2 and n = 3
 * 2^3 = 2*2*2 = 8
 *
 * If we have negative power
 * 2^-4 = 1/16 = 0.0625
 *
 * Use recursion
 * Divide the problem into sub problem with size n/2 and solve it recursively
 * If n is even, then keep multiplying halfResult
 * If n is odd, then multiply finalResult by k
 * If n is less than zero instead of multiplying with k divide with k, so it will be 1/4 for
 * first iteration then 1/8 x 1/8
 *
 * Time Complexity: O(logn) or O(logb) - where n or b is the power/exponent
 * Space Complexity: O(logn)
 *
 * ************************************************************************************************
 * Power
 Problem Statement:
 The problem statement is straight forward. Given a base ‘a’ and an exponent ‘b’.
 Your task is to find a^b. The value could be large enough. So, calculate a^b % 1000000007.

Input/Output Format For The Function:
 Input Format:
 First parameter of the function denotes base a and the second parameter of the function denotes the exponent b.

 Output Format:
 The function returns an integer variable denoting the calculated value of a^b % 1000000007.

 Input/Output Format For The Custom Input:
 Input Format:
 First line of the input contains one single integer a, denoting the value of the base and the
 second line also contains one single integer denoting the value of the exponent.

 If a = 2 and b = 10, then custom input format will be:
 2
 10

 Output Format:
 Print one single line containing one integer denoting the calculated value of a^b % 1000000007.
 For the above provided custom input, output would be:
 1024

 Constraints:
 0 <= a <= 10^18
 0 <= b <= 10^18
 a and b together can’t be 0

 Sample Test Case:
 a = 2
 b = 10

 Sample Output:
 1024

 Explanation:
 For the above sample input:
 2^10 = 1024, and
 1024 % 1000000007 = 1024
 Hence, output is 1024.

 *****************************************************************************************************
 *
 * 2) other_solution:
 Description:
 In this approach we will try to solve this problem in a recursive manner. Let’s say we need to calculate a^b.
 Now, this can be reduced to a^b = a^(b/2) * a^(b/2) , if b is even and for the case when b is odd then
 we can say that a^b = a^(b/2) * a^(b/2) * a.
 Let’s say f(a,b) is the power function that denotes a^b. So, f(a,b) can be represented as:

 f(a , b) = {
    f(a , b/2) * f(a , b/2) , when b is even;
     f(a , b/2) * f(a , b/2) * a , when b is odd;
    1, when b is zero }

 We will use the above recursive relation to evaluate a^b.

 Time Complexity (assuming that input arguments are already given and excluding time used in declaration of output):
 O(log(b)), where b is the exponent.
 The recursive equation that we are using keeps reducing the exponent by half at each step till the exponent reduces to zero. Therefore, this takes O(log(b)) iterations to evaluate a^b.

 Time Complexity:
 O(log(b)), where b is the exponent.
 The recursive equation that we are using keeps reducing the exponent by half at each step till the exponent reduces to zero. Therefore, this takes O(log(b)) iterations to evaluate a^b.

 Auxiliary Space Used:
 O(log(b)), where b is the exponent.
 The recursive function calls consumes a recursive stack memory for each function call that is equal to O(log(b)).

 Space Complexity:
 O(log(b)), where b is the exponent.
 The total space complexity is equal to the auxiliary space plus the input and output space. Here the input and output space complexity is constant O(1) as we are only taking two integers as input and returning one integer as output. Hence, the total space complexity becomes O(log(b)) + O(1) ~ O(log(b)).

 2) optimal_solution:
 Description:
 In this approach we will use the same power doubling principal as used in the other_solution approach. In the above solution we used top down approach by spiting into half at each step. Here, in this approach we will solve it using bottom up approach by doubling at each step. Below is the mathematical illustration of the approach using an example :
 So, this will be our approach in solving this problem in bottom-up fashion. We will start with the least significant bit of the exponent when represented in binary and for each set bit we keep on multiplying that power of the base into our final result. Kindly, refer to the implementation for better understanding.

 Time Complexity (assuming that input arguments are already given and excluding time used in declaration of output):
 O(log(b)), where b is the exponent.
 To evaluate the power we are iterating over all set-bits of the exponent which takes O(b) iterations.

 Time Complexity:
 O(log(b)), where b is the exponent.
 To evaluate the power we are iterating over all set-bits of the exponent which takes O(b) iterations.

 Auxiliary Space Used:
 O(1)
 This is a bottom up iterative approach. Hence, no stack memory is consumed in recursive function calls as compared to the other_solution.

 Space Complexity:
 O(1)
 The total space complexity is equal to the auxiliary space plus the input and output space. Here the input and output space complexity is constant O(1) as we are only taking two integers as input and returning one integer as output. Hence, the total space complexity becomes O(1) + O(1) ~ O(1).
 */
public class CalculatePower_Ologn {

    private static int MOD = 1000000007;

    private static double calculatePower(int k, int n) {

        if (n==0) {
            return 1;
        }

        double halfResult = calculatePower(k, n/2);

        if (n%2 == 0) { //If n is even
            return halfResult * halfResult; //Second step 1/4 * 1/4 = 1/16
        } else if (n > 0) { //If n is odd
            return halfResult * halfResult * k;
        } else { //If n is negative
            return halfResult * halfResult/k; //1*1/4
        }
    }

    private static int calculate_power(long a, long b) {
        //This is using recursion

        //TC = O(logb)
        //SC = O(logb)

        //Base Case
        if (b == 0) {
            return 1;
        }

        a = a % MOD;

        long halfResult = calculate_power(a, b/2);

        //Even
        halfResult = halfResult * halfResult % MOD;

        //Odd
        if (b % 2 == 1) {
            halfResult = halfResult * a % MOD;
        }
        return (int)halfResult;
    }

    //TC = O(logb)
    //SC = O(1)
    private static int calculate_powerNonRecursive(long a, long b) {

        long result = 1;

        // stores a^(power of two)
        // initialized with a^(2^0)
        long power = a % MOD;

        //Iterate all bits of b
        while (b != 0) {

            //If current bit is set
            if (b % 2 == 1) {
                result = (result * power) % MOD;
            }

            // double the power of two
            // a^i * a^i = a^(2*i)
            power = power * power % MOD;

            //Move to next bit
            b = b/2;
        }
        return (int)result;
    }

    public static void main(String[] args) {
        System.out.println("4 power of -2 is " + calculatePower(4, -2));
        System.out.println("4 power of 3  is " + calculatePower(4, 3));
        System.out.println("4 power of 4 is " + calculatePower(4, 4));

        System.out.println("2 power 10 is: " + calculate_power(10000000, 10000000));
        System.out.println("2 power 11 is: " + calculate_powerNonRecursive(10000000, 10000000));
    }
}
