package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 * 
 * <pre>
 *     -1 - A wall or an obstacle.
 *     0 - A gate.
 *     INF - Infinity means an empty room.
 *     We use the value 2 ^ 31 - 1 = 2147483647 to represent INF as you may assume that
 *     the distance to a gate is less than 2147483647.
 * 
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate,
 * it should be filled with INF.
 * 
 * For example, given the 2D grid:
 * 
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * 
 * After running your function, the 2D grid should be:
 * 
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 * </pre>
 */
public class WallsAndGates {
	// BFS Solution.
	public void wallsAndGates(int[][] rooms) {
		if (rooms.length == 0 || rooms[0].length == 0)
			return;
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				if (rooms[i][j] == 0)
					queue.offer(new int[] { i, j });
			}
		}
		while (!queue.isEmpty()) {
			int[] top = queue.poll();
			int row = top[0], col = top[1];
			if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE) {
				rooms[row - 1][col] = rooms[row][col] + 1;
				queue.offer(new int[] { row - 1, col });
			}
			if (row < rooms.length - 1 && rooms[row + 1][col] == Integer.MAX_VALUE) {
				rooms[row + 1][col] = rooms[row][col] + 1;
				queue.offer(new int[] { row + 1, col });
			}
			if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE) {
				rooms[row][col - 1] = rooms[row][col] + 1;
				queue.offer(new int[] { row, col - 1 });
			}
			if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE) {
				rooms[row][col + 1] = rooms[row][col] + 1;
				queue.offer(new int[] { row, col + 1 });
			}
		}
	}

	public void wallsAndGates2(int[][] rooms) {
		for (int i = 0; i < rooms.length; i++)
			for (int j = 0; j < rooms[0].length; j++)
				if (rooms[i][j] == 0)
					helper(rooms, i, j, 0);
	}

	private void helper(int[][] rooms, int i, int j, int d) {
		// checking -1 (wall) is included.
		if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < d)
			return;
		rooms[i][j] = d;
		helper(rooms, i - 1, j, d + 1);
		helper(rooms, i + 1, j, d + 1);
		helper(rooms, i, j - 1, d + 1);
		helper(rooms, i, j + 1, d + 1);
	}

	// Editorial solution.
	private static final int EMPTY = Integer.MAX_VALUE;
	private static final int GATE = 0;
	private static final List<int[]> DIRECTIONS = Arrays.asList(new int[] { 1, 0 }, new int[] { -1, 0 },
	        new int[] { 0, 1 }, new int[] { 0, -1 });

	public void wallsAndGates3(int[][] rooms) {
		int m = rooms.length;
		if (m == 0)
			return;
		int n = rooms[0].length;
		Queue<int[]> q = new LinkedList<>();
		for (int row = 0; row < m; row++) {
			for (int col = 0; col < n; col++) {
				if (rooms[row][col] == GATE) {
					q.add(new int[] { row, col });
				}
			}
		}
		while (!q.isEmpty()) {
			int[] point = q.poll();
			int row = point[0];
			int col = point[1];
			for (int[] direction : DIRECTIONS) {
				int r = row + direction[0];
				int c = col + direction[1];
				if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
					continue;
				}
				rooms[r][c] = rooms[row][col] + 1;
				q.add(new int[] { r, c });
			}
		}
	}
}
