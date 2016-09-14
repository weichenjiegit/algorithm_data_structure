package leetcode;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper
 * left corner (row1, col1) and lower right corner (row2, col2).
 * 
 * Example:
 * 
 * <pre>
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 * 
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * 
 * Note:
 * 
 *     You may assume that the matrix does not change.
 *     There are many calls to sumRegion function.
 *     You may assume that row1 ≤ row2 and col1 ≤ col2.
 * </pre>
 */
public class RangeSumQuery2DImmutable {
	public class NumMatrix {
		int[][] sum;

		public NumMatrix(int[][] matrix) {
			if (matrix.length == 0 || matrix[0].length == 0)
				return;
			sum = new int[matrix.length + 1][matrix[0].length + 1];
			for (int i = 0; i < matrix.length; i++) {
				int tmp = 0;
				for (int j = 0; j < matrix[0].length; j++) {
					tmp += matrix[i][j];
					sum[i + 1][j + 1] = sum[i][j + 1] + tmp;
				}
			}
		}

		public int sumRegion(int row1, int col1, int row2, int col2) {
			if (sum.length == 0)
				return 0;
			return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
		}
	}

	// Clean.
	public class NumMatrix2 {
		private int[][] dp;

		public NumMatrix2(int[][] matrix) {
			if (matrix.length == 0 || matrix[0].length == 0)
				return;
			dp = new int[matrix.length + 1][matrix[0].length + 1];
			for (int r = 0; r < matrix.length; r++) {
				for (int c = 0; c < matrix[0].length; c++) {
					dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
				}
			}
		}

		public int sumRegion(int row1, int col1, int row2, int col2) {
			return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
		}
	}
}
