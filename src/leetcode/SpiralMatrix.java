package leetcode;

/**
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 * 
 * <pre>
 * For example,
 * Given the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * </pre>
 * You should return [1,2,3,6,9,8,7,4,5]. 
 */
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		int m = matrix.length;
		if (m == 0)
			return res;
		int n = matrix[0].length;
		if (n == 0)
			return res;
		int min = m < n ? m : n;
		int layer = min / 2;
		for (int i = 0; i < layer; i++) {
			for (int j = i; j < n - 1 - i; j++)
				res.add(matrix[i][j]);
			for (int j = i; j < m - 1 - i; j++)
				res.add(matrix[i][j]);
			for (int j = n - 1 - i; j > i; j--)
				res.add(matrix[i][j]);
			for (int j = m - 1 - i; j > i; j--)
				res.add(matrix[i][j]);
		}
		if (min % 2 == 1) {
			if (m > n)
				for (int i = layer; i <= m - 1 - layer; i++)
					res.add(matrix[i][n / 2]);
			else
				for (int i = layer; i <= n - 1 - layer; i++)
					res.add(matrix[m / 2][i]);
		}
		return res;
	}

	public List<Integer> spiralOrder2(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		if (matrix.length == 0)
			return res;
		int beginX = 0, endX = matrix[0].length - 1;
		int beginY = 0, endY = matrix.length - 1;
		while (true) {
			// From left to right
			for (int i = beginX; i <= endX; ++i)
				res.add(matrix[beginY][i]);
			if (++beginY > endY)
				break;
			// From top down
			for (int i = beginY; i <= endY; ++i)
				res.add(matrix[i][endX]);
			if (beginX > --endX)
				break;
			// From right to left
			for (int i = endX; i >= beginX; --i)
				res.add(matrix[endY][i]);
			if (beginY > --endY)
				break;
			// From bottom up
			for (int i = endY; i >= beginY; --i)
				res.add(matrix[i][beginX]);
			if (++beginX > endX)
				break;
		}
		return res;
	}
}
