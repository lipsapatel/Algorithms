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
 * Use two nested loops,
 * Take one element at time
 * Use outer loop as buyDateIndex and compare it with every element in the inner loop
 * which will be considered as sellDateIndex.
 * Keep track of the maximumProfit
 *
 * Time Complexity: O(n2)
 */
public class SingleStockSellProblem_NestedLoop_On2 {

    private static void findMaxProfit(int[] prices) {

        int maximumProfit = -1;
        int buyDateIndex = prices[0];
        int sellDateIndex = prices[0];

        for (int i = 0; i < prices.length; i++) {

            for (int j = i; j < prices.length; j++) {

                if (prices[j] > prices[i] && (prices[j] - prices[i] > maximumProfit)) {
                    maximumProfit = prices[j] - prices[i];
                    buyDateIndex = i;
                    sellDateIndex = j;
                }
            }
        }

        System.out.println("Maximum profit is " + maximumProfit + " Buy Date Index is "
         + buyDateIndex + " Sell Date Index " + sellDateIndex);
    }

    public static void main(String[] args) {
        int[] prices = {200, 500, 1000, 700, 30, 400, 900, 400, 50};
        findMaxProfit(prices);
    }
}
