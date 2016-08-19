package leetcode;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing all ones and return its area.
 */
public class MaximalRectangle {
	/*
	 * Solution 1
	 * Using Largest Rectangle in Histogram, O(n^2).
	 * Great solution... Have to try multiple times to pass judge large.
	 */
	public int maximalRectangle(char[][] matrix) {
		int row = matrix.length;
		if (row == 0)
			return 0;
		int col = matrix[0].length;
		if (col == 0)
			return 0;
		int[] ar = new int[col];
		int max = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == '1')
					ar[j]++;
				else
					ar[j] = 0;
			}
			// for ar of every row, run largestRectangleArea once
			max = Math.max(max, largestRectangleArea(ar));
		}
		return max;
	}

	// O(n) using one stack
	public int largestRectangleArea(int[] height) {
		int area = 0;
		java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			if (stack.empty() || height[stack.peek()] < height[i]) {
				stack.push(i);
			} else {
				int start = stack.pop();
				int width = stack.empty() ? i : i - stack.peek() - 1;
				area = Math.max(area, height[start] * width);
				i--;
			}
		}
		while (!stack.empty()) {
			int start = stack.pop();
			int width = stack.empty() ? height.length : height.length
					- stack.peek() - 1;
			area = Math.max(area, height[start] * width);
		}
		return area;
	}

	/*
	 * Solution 2: N(n^3)
	 */
	public int maximalRectangle2(char[][] matrix) {
		int row = matrix.length;
		if (row == 0)
			return 0;
		int col = matrix[0].length;
		if (col == 0)
			return 0;
		int[][] dp = new int[row][col];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == '1')
					dp[i][j] = j > 0 ? dp[i][j - 1] + 1 : 1;
			}
		int max = 0;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == '1') {
					int k = i;
					int min = dp[i][j];
					while (k >= 0 && matrix[k][j] == '1') {
						min = Math.min(min, dp[k][j]);
						max = Math.max(max, min * (i - k + 1));
						k--;
					}
				}
			}
		return max;
	}
}
