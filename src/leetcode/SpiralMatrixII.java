package leetcode;

/**
 * Given an integer n,
 * generate a square matrix filled with elements from 1 to n^2 in spiral order.
 * 
 * <pre>
 * For example,
 * Given n = 3,
 * You should return the following matrix:
 * 
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * </pre>
 */
public class SpiralMatrixII {
	public int[][] generateMatrix(int n) {
		int[][] res = new int[n][n];
		if (n == 0)
			return res;
		int num = 0;
		int layer = n / 2;
		for (int i = 0; i < layer; ++i) {
			int j;
			for (j = i; j < n - 1 - i; ++j)
				res[i][j] = ++num;
			for (j = i; j < n - 1 - i; ++j)
				res[j][n - 1 - i] = ++num;
			for (j = n - 1 - i; j > i; --j)
				res[n - 1 - i][j] = ++num;
			for (j = n - 1 - i; j > i; --j)
				res[j][i] = ++num;
		}
		if (n % 2 == 1)
			res[n / 2][n / 2] = ++num;
		return res;
	}
}
