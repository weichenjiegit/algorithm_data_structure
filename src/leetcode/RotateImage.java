package leetcode;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 */
public class RotateImage {
	public void rotate(int[][] matrix) {
		int height = matrix.length;
		if (height <= 1)
			return;
		for (int layer = 0; layer < height / 2; layer++) {
			int first = layer;
			int last = height - 1 - layer;
			for (int i = first; i < last; i++) {
				int offset = i - first;
				int top = matrix[first][i];
				// left -> top
				matrix[first][i] = matrix[last - offset][first];
				// bottom -> left
				matrix[last - offset][first] = matrix[last][last - offset];
				// right -> bottom
				matrix[last][last - offset] = matrix[i][last];
				matrix[i][last] = top;
			}
		}
	}
}
