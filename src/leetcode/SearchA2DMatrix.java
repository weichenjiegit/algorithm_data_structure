package leetcode;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the
 * following properties:
 * 
 * Integers in each row are sorted from left to right. The first integer of each row is greater than
 * the last integer of the previous row.
 * 
 * For example,
 * 
 * Consider the following matrix:
 * 
 * <pre>
 * [
 *  [1,   3,  5,  7],
 *  [10, 11, 16, 20],
 *  [23, 30, 34, 50]
 * ]
 * </pre>
 * 
 * Given target = 3, return true.
 */
public class SearchA2DMatrix {
	// Pay attention to Young tableau, this question is different.
	/**
	 * Treat the matrix as a sorted array.
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		int start = 0, rows = matrix.length, cols = matrix[0].length;
		int end = rows * cols - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (matrix[mid / cols][mid % cols] == target) {
				return true;
			}
			if (matrix[mid / cols][mid % cols] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return false;
	}

	public boolean searchMatrix2(int[][] matrix, int target) {
		int row = binarySearchRow(matrix, target);
		return binarySearchColumn(matrix[row], target);
	}

	private boolean binarySearchColumn(int[] array, int target) {
		int i = 0;
		int j = array.length - 1;
		int mid;
		while (i <= j) {
			mid = (j - i) / 2 + i;
			if (array[mid] == target)
				return true;
			else if (array[mid] < target)
				i = mid + 1;
			else
				j = mid - 1;
		}
		return false;
	}

	private int binarySearchRow(int[][] array, int target) {
		int i = 0;
		int j = array.length - 1;
		int mid = 0;
		while (i <= j) {
			mid = (j - i) / 2 + i;
			if (target < array[mid][0]) {
				j = mid - 1;
			} else if (target > array[mid][0]) {
				i = mid + 1;
			} else
				return mid;
		}
		return j >= 0 ? j : 0;
	}
}
