import java.util.Arrays;

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

    public static void main(String[] args) {

        int[] price = {10, 4, 5, 90, 120, 80};

        int n = price.length;
        int[] s = new int[n];

        calculateStockSpan(price, n, s);
        System.out.println("Stock Span: " + Arrays.toString(s));
    }
}
