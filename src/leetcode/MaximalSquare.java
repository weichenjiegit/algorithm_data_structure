package leetcode;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and
 * return its area.
 * 
 * For example, given the following matrix:
 * 
 * <pre>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * </pre>
 * 
 * Return 4.
 */
public class MaximalSquare {
	// Top, Left, and Top Left decides the size of the square. If all of them are same, then the
	// size of square increases by 1. If they're not same, then set to the minimal square size,
	// which is 1.
	public int maximalSquare(char[][] a) {
		if (a.length == 0)
			return 0;
		int m = a.length, n = a[0].length, result = 0;
		int[][] b = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a[i - 1][j - 1] == '1') {
					b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
					result = Math.max(b[i][j], result);
				}
			}
		}
		return result * result;
	}

	// Only use an array to store the prevois row.
	public int maximalSquare2(char[][] matrix) {
		int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
		int[] dp = new int[cols + 1];
		int maxsqlen = 0, prev = 0;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				int temp = dp[j];
				if (matrix[i - 1][j - 1] == '1') {
					dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
					maxsqlen = Math.max(maxsqlen, dp[j]);
				} else {
					dp[j] = 0;
				}
				prev = temp;
			}
		}
		return maxsqlen * maxsqlen;
	}
}
