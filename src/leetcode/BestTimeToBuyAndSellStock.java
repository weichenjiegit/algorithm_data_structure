package leetcode;

/**
 * Say you have an array
 * for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction
 * (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 */
public class BestTimeToBuyAndSellStock {
	public int maxProfit(int[] prices) {
		if (prices.length == 0)
			return 0;
		int profit = 0;
		int minValue = prices[0];
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minValue)
				minValue = prices[i];
			else if (prices[i] - minValue > profit)
				profit = prices[i] - minValue;
		}
		return profit;
	}
}
