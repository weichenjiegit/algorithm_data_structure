package leetcode;

/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules. The Sudoku board could
 * be partially filled, where empty cells are filled with the character '.'.
 */
public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9)
			return false;
		boolean[][] row = new boolean[9][9]; // keep existed values in each row
		boolean[][] col = new boolean[9][9]; // keep existed values in each column
		boolean[][] block = new boolean[9][9]; // keep existed values in each 3 * 3 subblock
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					if (row[i][board[i][j] - '1'] || col[j][board[i][j] - '1']
					        || block[(i / 3) * 3 + j / 3][board[i][j] - '1']) { // Once the value
					                                                            // existed in the
					                                                            // row or column or
					                                                            // subblock, return
					                                                            // false
						return false;
					} else { // otherwise, record this value in the three boolean array.
						row[i][board[i][j] - '1'] = true;
						col[j][board[i][j] - '1'] = true;
						block[(i / 3) * 3 + j / 3][board[i][j] - '1'] = true;
					}
				}
			}
		}

		return true;
	}

	public boolean isValidSudoku2(char[][] board) {
		// Normal validation solution checking
		// by row, column and square takes O(n^3) time, where n = 9.
		// This solution takes O(n^2) time, but O(n^2) space.
		int[][] table = new int[27][9];
		// 9 for each comparison: row, column, square
		int[][] square = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.')
					continue;
				if (board[i][j] < '1' || board[i][j] > '9')
					return false;
				int val = board[i][j] - '0';
				if (table[i][val - 1] != 0)
					return false;
				table[i][val - 1] = 1;
				if (table[j + 9][val - 1] != 0)
					return false;
				table[j + 9][val - 1] = 1;
				int squareInt = square[i / 3][j / 3];
				if (table[squareInt + 18][val - 1] != 0)
					return false;
				table[squareInt + 18][val - 1] = 1;
			}
		}
		return true;
	}
}
