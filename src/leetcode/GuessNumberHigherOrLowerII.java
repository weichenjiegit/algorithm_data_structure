package leetcode;

/**
 * We are playing the Guess Game. The game is as follows:
 * 
 * I pick a number from 1 to n. You have to guess which number I picked.
 * 
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * 
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game
 * when you guess the number I picked.
 */
public class GuessNumberHigherOrLowerII {

	/**
	 * Given any n, we make a guess k. Then we break the interval [1,n] into [1,k - 1] and [k +
	 * 1,n]. The min of worst case cost can be calculated recursively as
	 * 
	 * cost[1,n] = k + max{cost[1,k - 1] + cost[k+1,n]}
	 */
	public int getMoneyAmount(int n) {
		int[][] dp = new int[n + 2][n + 2];
		for (int len = 1; len < n; len++) {
			for (int from = 1, to = from + len; to <= n; from++, to++) {
				dp[from][to] = Integer.MAX_VALUE;
				for (int k = from; k <= to; k++)
					dp[from][to] = Math.min(dp[from][to], k + Math.max(dp[from][k - 1], dp[k + 1][to]));
			}
		}
		return dp[1][n];
	}

	public int getMoneyAmount2(int n) {
		// all intervals are inclusive
		// add 1 to the length just to make the index the same as numbers used
		int[][] dp = new int[n + 1][n + 1];
		// iterate the lengths of the intervals since the calculations of longer intervals rely on
		// shorter ones
		for (int l = 2; l <= n; l++) {
			// iterate all the intervals with length l, the start of which is i. Hence the interval
			// will be [i, i + (l - 1)]
			for (int i = 1; i <= n - (l - 1); i++) {
				dp[i][i + (l - 1)] = Integer.MAX_VALUE;
				// iterate all the first guesses g
				for (int g = i; g <= i + (l - 1); g++) {
					int costForThisGuess;
					// if g is the last integer, g + 1 does not exist.
					// else dp[i, i + (l - 1)]: g (first guess) + max{the cost of left part [i, g
					// - 1], the cost of right part [g + 1, i + (l - 1)]}
					if (g == n) {
						costForThisGuess = dp[i][g - 1] + g;
					} else {
						costForThisGuess = g + Math.max(dp[i][g - 1], dp[g + 1][i + (l - 1)]);
					}
					dp[i][i + (l - 1)] = Math.min(dp[i][i + (l - 1)], costForThisGuess);
				}
			}
		}
		return dp[1][n];
	}
}
