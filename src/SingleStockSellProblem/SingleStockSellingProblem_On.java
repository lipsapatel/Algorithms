package SingleStockSellProblem;

/**
 * Given an array which represents the cost of stock on each day.
 * You are allowed to buy and sell the stock only once.
 * Write an algorithm to maximize the profit in the single buy and sell.
 *
 * int[] prices = {200, 500, 1000, 700, 30, 400, 900, 400, 50}
 * Output: Maximum profit = 870
 * BuyDateIndex = 4
 * SellDateIndex = 6
 *
 * Use one loop.
 * Initialize profit, buyDateIndex and sellDateIndex to zero
 * Take min = 0
 * if prices[i] < prices[min]
 * then update min = i
 * calculate currentProfit = prices[i] - prices[min]
 * If currentProfit > profit
 * the update profit and buyDateIndex and sellDateIndex
 *
 * Time Complexity: O(n)
 */
public class SingleStockSellingProblem_On {

    private static void findMaxProfit(int[] prices) {

        int min = 0;
        int profit = 0;
        int buyDateIndex = 0;
        int sellDateIndex = 0;

        for(int i = 0; i < prices.length; i++) {

            if (prices[i] < prices[min]) {
                min = i;
            }

            int currentProfit = prices[i] - prices[min];

            if (currentProfit > profit) {
                profit = currentProfit;
                buyDateIndex = min;
                sellDateIndex = i;
            }
        }

        System.out.println("Maximum profit is " + profit + " Buy Date Index is "
                + buyDateIndex + " Sell Date Index " + sellDateIndex);
    }

    private static void findMaxProfitSameAsMaxDifference(int[] prices) {

        int profit = -1;
        int max_so_far_index = prices.length - 1;
        int max_so_far = prices[prices.length - 1];

        int minIndex = prices.length - 1;
        int maxIndex = prices.length - 1;

        for (int i = prices.length - 2; i >= 0; i--) {

            if (prices[i] > max_so_far) {
                max_so_far = prices[i];
                max_so_far_index = i;
            } else {
                if (max_so_far - prices[i] > profit) {
                    profit = max_so_far - prices[i];
                    maxIndex = max_so_far_index;
                    minIndex = i;
                }
            }
        }

        System.out.println("Maximum profit/Maximum Difference is " + profit + " Buy Date Index is "
                + minIndex + " Sell Date Index " + maxIndex);
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.

     Design an algorithm to find the maximum profit. You may complete as many transactions as you like
     (i.e., buy one and sell one share of the stock multiple times).

     Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     TC = O(n)
     SC = O(1)
     */
    private static int findMaxProfitMultipleTransaction(int[] prices) {
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {200, 500, 1000, 700, 30, 400, 900, 400, 50};
        findMaxProfit(prices);
        findMaxProfitSameAsMaxDifference(prices);
        System.out.println("Multiple transactions max profit: " + findMaxProfitMultipleTransaction(prices));
    }
}
