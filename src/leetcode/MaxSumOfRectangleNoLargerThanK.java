package leetcode;

import java.util.TreeSet;

/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the
 * matrix such that its sum is no larger than k.
 * 
 * Example:
 * 
 * <pre>
 * Given matrix = [
 *   [1,  0, 1],
 *   [0, -2, 3]
 * ]
 * k = 2
 * </pre>
 * 
 * The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no
 * larger than k (k = 2).
 * 
 * Note:
 * 
 * The rectangle inside the matrix must have an area > 0.
 * 
 * What if the number of rows is much larger than the number of columns?
 * 
 * @author Administrator
 *
 */
public class MaxSumOfRectangleNoLargerThanK {

	// Naive solution. O(n ^ 4).
	public int maxSumSubmatrix(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int rows = matrix.length, cols = matrix[0].length;
		int[][] areas = new int[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int area = matrix[r][c];
				if (r - 1 >= 0)
					area += areas[r - 1][c];
				if (c - 1 >= 0)
					area += areas[r][c - 1];
				if (r - 1 >= 0 && c - 1 >= 0)
					area -= areas[r - 1][c - 1];
				areas[r][c] = area;
			}
		}
		int max = Integer.MIN_VALUE;
		for (int r1 = 0; r1 < rows; r1++) {
			for (int c1 = 0; c1 < cols; c1++) {
				for (int r2 = r1; r2 < rows; r2++) {
					for (int c2 = c1; c2 < cols; c2++) {
						int area = areas[r2][c2];
						if (r1 - 1 >= 0)
							area -= areas[r1 - 1][c2];
						if (c1 - 1 >= 0)
							area -= areas[r2][c1 - 1];
						if (r1 - 1 >= 0 && c1 - 1 >= 0)
							area += areas[r1 - 1][c1 - 1];
						if (area <= k)
							max = Math.max(max, area);
					}
				}
			}
		}
		return max;
	}

	// O(n ^ 3 log n).
	public int maxSumSubmatrix2(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int rows = matrix.length, cols = matrix[0].length;
		int[][] areas = new int[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int area = matrix[r][c];
				if (r - 1 >= 0)
					area += areas[r - 1][c];
				if (c - 1 >= 0)
					area += areas[r][c - 1];
				if (r - 1 >= 0 && c - 1 >= 0)
					area -= areas[r - 1][c - 1];
				areas[r][c] = area;
			}
		}
		int max = Integer.MIN_VALUE;
		for (int r1 = 0; r1 < rows; r1++) {
			for (int r2 = r1; r2 < rows; r2++) {
				TreeSet<Integer> tree = new TreeSet<>();
				tree.add(0); // padding
				for (int c = 0; c < cols; c++) {
					int area = areas[r2][c];
					if (r1 - 1 >= 0)
						area -= areas[r1 - 1][c];
					Integer ceiling = tree.ceiling(area - k);
					if (ceiling != null)
						max = Math.max(max, area - ceiling);
					tree.add(area);
				}
			}
		}
		return max;
	}

	/**
	 * first consider the situation matrix is 1D
	 * 
	 * we can save every sum of 0~i(0<=i<len) and binary search previous sum to find possible result
	 * for every index, time complexity is O(NlogN).
	 * 
	 * O(n ^ 2 * m * log m )
	 */
	public int maxSumSubmatrix3(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int global = Integer.MIN_VALUE;
		int[] temp;

		for (int leftCol = 0; leftCol < col; leftCol++) {
			temp = new int[row];
			// sum from leftCol to rightCol for each row
			for (int rightCol = leftCol; rightCol < col; rightCol++) {
				int sum = 0;
				TreeSet<Integer> set = new TreeSet<>();
				set.add(0);
				for (int i = 0; i < row; i++) {
					temp[i] += matrix[i][rightCol];
					sum += temp[i];
					// TreeSet binary search previous sum
					Integer small = set.ceiling(sum - k);
					if (small != null) {
						global = Math.max(global, sum - small);
					}
					set.add(sum);
				}
			}
		}
		return global;
	}
}
