package leetcode;

import java.util.Arrays;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 *
 */
public class BestTimeToBuyAndSellStockIV {

	public int maxProfit(int k, int[] prices) {
		if (prices.length == 0) {
			return 0;
		}

		if (k >= prices.length / 2) {
			int maxProfit = 0;
			for (int i = 1; i < prices.length; i++) {
				maxProfit += Math.max(0, prices[i] - prices[i - 1]);
			}
			return maxProfit;
		}

		int[] buy = new int[k + 1]; // profit after buying at k-th transaction
		int[] sell = new int[k + 1]; // profit at k-th transaction (sell)
		Arrays.fill(buy, Integer.MIN_VALUE);
		Arrays.fill(sell, 0);
		for (int price : prices) {
			for (int i = 1; i <= k; i++) {
				buy[i] = Math.max(buy[i], sell[i - 1] - price);
				sell[i] = Math.max(sell[i], buy[i] + price);
			}
		}

		return sell[k];
	}

	// DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
	public int maxProfit2(int k, int[] prices) {
		int len = prices.length;
		if (k >= prices.length / 2) {
			int maxProfit = 0;
			for (int i = 1; i < prices.length; i++) {
				maxProfit += Math.max(0, prices[i] - prices[i - 1]);
			}
			return maxProfit;
		}
		int[][] t = new int[k + 1][len];
		for (int i = 1; i <= k; i++) {
			int tmpMax = -prices[0];
			for (int j = 1; j < len; j++) {
				t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
				tmpMax = Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
				// tmpMax means the maximum profit of just doing at most i-1 transactions,
				// using at most first j-1 prices,
				// and buying the stock at price[j] - this is used for the next loop.
			}
		}
		return t[k][len - 1];
	}

	// More explanation for k transactions
	// dp[t][i]: maximum Profit using at most t transactions up to day i (including day i)
	// dp[t][i] = max(dp[t][i - 1], prices[i] - prices[j] + dp[t - 1][j]) for all j in range [0, i - 1]
	//          = max(dp[t][i - 1], prices[i] + max(dp[t - 1][j - 1] - prices[j])) for all j in range [0, i - 1]

	// Time limit exceeded
	public int maxProfit3(int k, int[] prices) {
		if (k == 0 || prices.length < 2)
			return 0;
		if (k > prices.length / 2)
			return noLimit(prices);

		// hold[i][j]: For at most i transactions, the max profit on jth day with a stock in hand.
		// unhold[i][j]: For at most i transactions, the max profit on jth day without a stock in
		// hand
		int[][] hold = new int[k + 1][prices.length];
		int[][] unhold = new int[k + 1][prices.length];
		for (int i = 1; i <= k; i++) {
			hold[i][0] = -prices[0];
			unhold[i][0] = 0;
			for (int j = 1; j < prices.length; j++) {
				hold[i][j] = Math.max(-prices[j] + unhold[i - 1][j], hold[i][j - 1]); // Buy or not
				                                                                      // buy
				unhold[i][j] = Math.max(prices[j] + hold[i][j - 1], unhold[i][j - 1]); // Sell or
				                                                                       // not sell
			}
		}
		return unhold[k][prices.length - 1];
	}

	private int noLimit(int[] prices) { // Solution from Best Time to Buy and Sell Stock II
		int max = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i + 1] > prices[i])
				max += prices[i + 1] - prices[i];
		}
		return max;
	}
}
