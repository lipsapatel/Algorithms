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

    public static void main(String[] args) {
        int[] prices = {200, 500, 1000, 700, 30, 400, 900, 400, 50};
        findMaxProfit(prices);
    }
}
