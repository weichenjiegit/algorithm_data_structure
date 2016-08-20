package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two
 * queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle. Each solution contains
 * a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space respectively.
 * 
 * For example, There exist two distinct solutions to the 4-queens puzzle:
 * 
 * <pre>
 * [
 *  [".Q..",  // Solution 1
 *  "...Q",
 *  "Q...",
 *  "..Q."],
 *  
 *  ["..Q.",  // Solution 2
 *  "Q...",
 *  "...Q",
 *  ".Q.."]
 * ]
 * </pre>
 */
public class NQueens {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();
		int[] place = new int[n];
		// place[i] = j represents matrix[i][j] is safe for a Queen
		placeQueens(res, 0, n, place);
		return res;
	}

	// Check if place[row] is valid by comparing to 0 - (row - 1) values
	public boolean check(int row, int[] place) {
		for (int i = 0; i < row; ++i) {
			int diff = Math.abs(place[i] - place[row]);
			if (diff == 0 || diff == row - i)
				return false;
		}
		return true;
	}

	public void placeQueens(List<List<String>> res, int row, int n, int[] place) {
		if (row == n) {
			List<String> matrix = new ArrayList<>();
			StringBuilder line;
			for (int i = 0; i < row; i++) {
				line = new StringBuilder("");
				for (int j = 0; j < n; j++) {
					if (j == place[i])
						line.append("Q");
					else
						line.append(".");
				}
				matrix.add(line.toString());
			}
			res.add(matrix);
			return;
		}
		for (int col = 0; col < n; col++) {
			place[row] = col;
			if (check(row, place))
				placeQueens(res, row + 1, n, place);
		}
	}
}
