import java.util.Stack;

/**
 * Build the lowest number by removing k digits from number
 *
 * String num = "4325043"
 * int k = 3
 * Output: 2043
 *
 * Approach: Brute Force
 *
 * 1) Choose and unchoose
 * Exponential time complexity
 *
 * kCn - Choose k from n
 *
 * Time limit exceeded
 *
 * 2) Another technical issue is to compare the values of two digit strings.
 * We could convert digit string to the numerical value. This method
 * does not scale. For unsigned 32-bit integer, the maximum value it can
 * hold is a number with 10 digits (2 ^32 = 4, 294, 967, 295)
 * 3) String num could be hundred of digits.
 *
 * Time Complexity: O(2^n)
 *
 * Greedy Approach with Stack
 *
 * 1) A = 1axxxx, B = 1bxxx
 * if a > b, then A > B.
 *
 * 2) Given a sequence of digits [D1 D2 D3...Dn] if the digit D2 is less than it's
 * left neighbor D1 then we should remove the left neighbor D1 in order to
 * obtain minimum result.
 *
 * 3) Monotonic Increasing Sequence: Each digit is bigger than its previous digit.
 * In this scenario we simply remove the pending large digits again greedily.
 *
 * For example:
 * num = "1234567" k = 3
 * Output = "1234"
 *
 * Approach Implementation
 *
 * 1) Take stack
 * 2) For each digit, if digit is less than the top of the stack i.e., left
 * neighbor of the digit, then pop from the stack.
 * 3) Push digit to the stack
 * 4) Also decrement k for each pop operation
 * 5) Keep popping from the stack until it's empty or k = 0
 *
 * For example:
 * String num = "1234567" k = 3
 * Nothing has been removed, so pop last 3
 *
 * 6) Also remove leading zeros
 * 7) If all digits are removed, then return zero instead of empty string.
 *
 * Build maximum number by removing k digits, the condition to pop reverse
 * We need to build monotonic decreasing sequence
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * resources/BuildLowestNumber1.jpg
 * resources/BuildLowestNumber2.jpg
 * resources/BuildLowestNumber3.jpg
 * resources/BuildLowestNumber4.jpg
 *
 * *************************************************************
 * Sliding Window Approach - Don't use it TC is O(nk)
 *
 * If k = 0 there's nothing to remove so return the whole number as a result
 * If k >= length the result would be empty; nothing to append
 *
 * find the lowest number from first k+1 digits
 * Append it to the result
 *
 * make a recursive call from index of lowest number + 1 to length of string and
 * Update k
 * k = k - (minIdx - startIdx) //Removed this many numbers
 *
 * Time Complexity: O(nk)
 * Space Complexity: O(n) Stack space = n - k recursive calls
 */
public class BuildLowestNumber {

    private static String removeKDigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            while(!stack.isEmpty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }

            stack.push(c);
        }

        //Remove remaining digits from tail
        for(int i = 0; i < k; i++) {
            stack.pop();
        }

        //Strip leading zeros
        StringBuilder result = new StringBuilder();
        boolean leadingZeros = true;
        for(Character digit: stack) {

            if(leadingZeros && digit == '0') {
                continue;
            }
            leadingZeros = false;
            result.append(digit);
        }

        //If result is empty return 0
        if(result.length() == 0) {
            return "0";
        }
        return result.toString();
    }

    private static String buildLowestNumber(String num, int k) {

        StringBuilder result = new StringBuilder();

        String output = buildLowestNumberHelper(num, k, result, 0);

        result = new StringBuilder();
        boolean leadingZeros = true;

        for(int i = 0; i < output.length(); i++) {
            char c = output.charAt(i);

            if(leadingZeros && c == '0') {
                continue;
            }
            leadingZeros = false;
            result.append(c);
        }

        if(result.length() == 0) {
            return "0";
        }
        return result.toString();
    }

    private static String buildLowestNumberHelper(String num, int k, StringBuilder
            result, int startIdx) {
        //Base Cases
        if (k == 0) { //Nothing to remove
            result.append(num.substring(startIdx));
            return result.toString();
        }

        int remLength = num.length() - startIdx;

        if (remLength <= k) { //Remove everything
            return result.toString();
        }

        //Find the smallest number among first (k+1) number
        int minIdx = startIdx;
        for (int i = startIdx; i <= startIdx + k; i++) {
            if (num.charAt(i) < num.charAt(minIdx)) {
                minIdx = i;
            }
        }

        result.append(num.charAt(minIdx));

        return buildLowestNumberHelper(num, k - (minIdx - startIdx), result, minIdx + 1);
    }

    public static void main(String[] args) {
        String num = "1234567";
        int k = 3;

        System.out.println("After removing k digits " + removeKDigits(num, k));

        num = "12345264";
        k = 4;

        System.out.println("After removing k digits " + removeKDigits(num, k));
    }
}


