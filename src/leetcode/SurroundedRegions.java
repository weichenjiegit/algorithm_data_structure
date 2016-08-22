package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions {
	// Solution 1. DFS. May not be able to pass judge large.
	public void solve(char[][] board) {
		if (board.length < 1)
			return;
		// change 'O' to 'A'
		// left -> right
		for (int j = 0; j < board[0].length; j++)
			flip(board, 0, j);
		// up -> down
		for (int i = 0; i < board.length; i++)
			flip(board, i, board[0].length - 1);
		// right -> left
		for (int j = board[0].length - 1; j >= 0; j--)
			flip(board, board.length - 1, j);
		// down -> up
		for (int i = board.length - 1; i >= 0; i--)
			flip(board, i, 0);
		// change 'O' to 'X'
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if (board[i][j] == 'O')
					board[i][j] = 'X';
		// change 'A' to 'O'
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if (board[i][j] == 'A')
					board[i][j] = 'O';
	}

	public void flip(char[][] board, int i, int j) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
			return;
		if (board[i][j] == 'O') {
			board[i][j] = 'A';
			flip(board, i - 1, j);
			flip(board, i + 1, j);
			flip(board, i, j - 1);
			flip(board, i, j + 1);
		}
	}

	// Solution 2. BFS
	public void fill(char[][] board, Queue<Integer> queue, int i, int j, int row,
			int col) {
		if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != 'O')
			return;
		queue.offer(i * row + j);
		board[i][j] = 'D';
	}

	public void bfs(char[][] board, Queue<Integer> queue, int i, int j, int row,
			int col) {
		fill(board, queue, i, j, row, col);

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int nexti = curr / row;
			int nextj = curr % row;
			fill(board, queue, nexti - 1, nextj, row, col);
			fill(board, queue, nexti + 1, nextj, row, col);
			fill(board, queue, nexti, nextj - 1, row, col);
			fill(board, queue, nexti, nextj + 1, row, col);
		}
	}

	public void solve2(char[][] board) {
		if (board == null || board.length == 0)
			return;
		int row = board.length;
		int col = board[0].length;
		Queue<Integer> queue = new LinkedList<>();
		for (int j = 0; j < col; j++) {
			bfs(board, queue, 0, j, row, col);
			bfs(board, queue, row - 1, j, row, col);
		}

		for (int i = 1; i < row - 1; i++) {
			bfs(board, queue, i, 0, row, col);
			bfs(board, queue, i, col - 1, row, col);
		}

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 'O')
					board[i][j] = 'X';
				else if (board[i][j] == 'D')
					board[i][j] = 'O';
			}
	}

	public static void main(String[] args) {
		char[][] board = { { 'O' } };
		SurroundedRegions test = new SurroundedRegions();
		test.solve(board);
	}
}
