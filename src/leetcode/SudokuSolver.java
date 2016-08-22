package leetcode;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution. 
 */
public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		solveSudokuDFS(board);
	}
	public boolean isValid(char[][] board, int x, int y){
		int i, j;
		for(i = 0; i < 9; i++)
			if(i != x && board[i][y] == board[x][y])
				return false;
		for(j = 0; j < 9; j++)
			if(j != y && board[x][j] == board[x][y])
				return false;
		for(i = 3 * (x / 3); i < 3 * (x / 3 + 1); i++)
			for(j = 3 * (y / 3); j < 3 * (y / 3 + 1); j++)
				if(i != x && j != y && board[i][j] == board[x][y])
					return false;
		return true;
	}
	public boolean solveSudokuDFS(char[][] board) {
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(board[i][j] == '.'){
					for(int k = 1; k <= 9; k++){
						board[i][j] = (char)('0' + k);
						if(isValid(board, i, j) && solveSudokuDFS(board))
							return true;
						board[i][j] = '.';
					}
					return false;
				}
			}
		}
		return true;
	}

}
