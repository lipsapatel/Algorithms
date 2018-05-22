import java.util.Arrays;
import java.util.Stack;

/**
 * The Stock Span Problem
 *
 * The stock span problem is a financial problem where we have a series of n daily
 * price quotes for a stock.
 *
 * The span si of the stock price on a given day i is defined as the maximum number of consecutive
 * days just before the given day, for which the price of the stock on the current day
 * is less than or equal to it's price on the given day.
 *
 * resources/StockSpanProblem.png
 *
 * Approach:
 *
 * 1) Traverse the input array. For every element being visited, traverse elements on left
 * of it and increment the span value of it while element on left is smaller or equal.
 *
 * Time Complexity: O(n2)
 *
 * Span of Stock Price = No of days stock price <= current price
 *
 * Span of stock using Stack
 *
 * Time Complexity: O(n)
 *
 * h(i) = Preceding day when stock price was greater than day i
 * s(i) = i - h(i)
 *
 */
public class StockSpanProblem {

    private static void calculateStockSpan(int[] price, int n, int[] s) {

        //The Span value is 1 for the first day
        s[0] = 1;

        //Calculate the span value for the remaining days by linearly checking previous days
        for (int i = 1; i < n; i++) {

            //Initialize the span value
            s[i] = 1;

            //Traverse left and check if the prices is less than equal to ith price
            for (int j = i - 1; (j >= 0 && (price[i] >= price[j])); j--) {

                s[i]++;
            }
        }
    }

    private static void calculateStockSpanUsingStack(int[] price, int n, int[] s) {

        //Create stack
        Stack<Integer> stack = new Stack<>();

        //Push the index of first element
        stack.push(0);

        //Span value of first element is always 1
        s[0] = 1;

        //Calculate span for the rest of the elements
        for (int i = 1; i < n; i++) {

            //Pop element from stack while stack is not empty and top of stack is smaller
            //than price[i]
            while(!stack.empty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            //If stack becomes empty then price[i] is greater than all elements to the
            //left of it else price[i] > than the elements after the top of stack
            s[i] = (stack.empty()) ? i + 1 : (i - stack.peek());

            //Push the element to stack
            stack.push(i);
        }
    }

    public static void main(String[] args) {

        int[] price = {10, 4, 5, 90, 120, 80};

        int n = price.length;
        int[] s = new int[n];

        calculateStockSpan(price, n, s);
        System.out.println("Stock Span: " + Arrays.toString(s));

        s = new int[n];
        calculateStockSpanUsingStack(price, n, s);
        System.out.println("Stock Span using stack " + Arrays.toString(s));
    }
}
