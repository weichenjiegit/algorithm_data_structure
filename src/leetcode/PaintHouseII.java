package leetcode;

/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of
 * painting each house with a certain color is different. You have to paint all the houses such that
 * no two adjacent houses have the same color.
 * 
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For
 * example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of
 * painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 * 
 * Note: All costs are positive integers.
 * 
 * Follow up: Could you solve it in O(nk) runtime?
 */
public class PaintHouseII {
	// dp[currentHouse][currentColor] = (currentColor == prevMinColor? prevSecondMin: prevMin) +
	// costs[currentHouse][currentColor]
	public int minCostII(int[][] costs) {
		if (costs == null || costs.length == 0 || costs[0].length == 0)
			return 0;

		int n = costs.length, k = costs[0].length;
		if (k == 1)
			return (n == 1 ? costs[0][0] : -1);

		int prevMin = 0, prevMinIndex = -1, prevSecMin = 0;
		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE, minIndex = -1, secMin = Integer.MAX_VALUE;
			for (int j = 0; j < k; j++) {
				int val = costs[i][j] + (j == prevMinIndex ? prevSecMin : prevMin);
				if (val < min) { // when val < min,
					secMin = min;
					min = val;
					minIndex = j;
				} else if (val < secMin) { // min <= val < secMin
					secMin = val;
				}
			}
			prevMin = min;
			prevMinIndex = minIndex;
			prevSecMin = secMin;
		}
		return prevMin;
	}
}
