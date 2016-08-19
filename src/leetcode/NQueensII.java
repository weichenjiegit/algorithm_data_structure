package leetcode;

/**
 * Follow up for N-Queens problem. Now, instead outputting board configurations, return the total
 * number of distinct solutions.
 */
public class NQueensII {
	private int result = 0;

	public int totalNQueens(int n) {
		boolean[] column = new boolean[n];
		boolean[] dia45 = new boolean[2 * n - 1];
		boolean[] dia135 = new boolean[2 * n - 1];
		helper(0, n, column, dia45, dia135);
		return result;
	}

	private void helper(int row, int n, boolean[] column, boolean[] dia45, boolean[] dia135) {
		if (row == n) {
			result++;
			return;
		}
		for (int col = 0; col < n; col++) {
			int id45 = col - row + n - 1;
			int id135 = col + row;
			if (column[col] || dia45[id45] || dia135[id135])
				continue;
			column[col] = dia45[id45] = dia135[id135] = true;
			helper(row + 1, n, column, dia45, dia135);
			column[col] = dia45[id45] = dia135[id135] = false;
		}
	}
}
