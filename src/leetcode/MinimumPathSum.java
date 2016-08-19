package leetcode;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right
 * which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		int m = grid.length;
		if (m == 0)
			return 0;
		int n = grid[0].length;
		if (n == 0)
			return 0;
		int[][] sum = new int[m][n];
		sum[0][0] = grid[0][0];
		for (int i = 1; i < m; i++) {
			sum[i][0] = sum[i - 1][0] + grid[i][0];
		}
		for (int j = 1; j < n; j++) {
			sum[0][j] = sum[0][j - 1] + grid[0][j];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (sum[i - 1][j] >= sum[i][j - 1])
					sum[i][j] = sum[i][j - 1] + grid[i][j];
				else
					sum[i][j] = sum[i - 1][j] + grid[i][j];
			}
		}
		return sum[m - 1][n - 1];
	}

	// Fewer variables
	public int minPathSum2(int[][] grid) {
		int m = grid.length;
		if (m == 0)
			return 0;
		int n = grid[0].length;
		if (n == 0)
			return 0;
		for (int i = 1; i < m; ++i)
			grid[i][0] += grid[i - 1][0];
		for (int i = 1; i < n; ++i)
			grid[0][i] += grid[0][i - 1];
		int a, b;
		for (int i = 1; i < m; ++i) {
			for (int j = 1; j < n; ++j) {
				a = grid[i][j] + grid[i - 1][j];
				b = grid[i][j] + grid[i][j - 1];
				grid[i][j] = a > b ? b : a;
			}
		}
		return grid[m - 1][n - 1];
	}
	
	public static void main(String[] args){
		int[][] grid = {{1, 2, 5}, {3, 2, 1}};
		MinimumPathSum test = new MinimumPathSum();
		test.minPathSum2(grid);
	}
}
