package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given
 * a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is
 * calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * 
 * For example, given three people living at (0,0), (0,4), and (2,2):
 * 
 * <pre>
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * </pre>
 * 
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So
 * return 6.
 * 
 * Hint:
 * 
 * Try to solve it in one dimension first. How can this solution apply to the two dimension case?
 */
public class BestMeetingPoint {
	public int minTotalDistance(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		List<Integer> I = new ArrayList<>(m);
		List<Integer> J = new ArrayList<>(n);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					I.add(i);
					J.add(j);
				}
			}
		}
		return getMin(I) + getMin(J);
	}

	private int getMin(List<Integer> list) {
		int ret = 0;
		Collections.sort(list);
		int i = 0;
		int j = list.size() - 1;
		while (i < j) {
			ret += list.get(j--) - list.get(i++);
		}
		return ret;
	}

	// Sorting to find the median.
	public int minTotalDistance2(int[][] grid) {
		List<Integer> rows = new ArrayList<>();
		List<Integer> cols = new ArrayList<>();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 1) {
					rows.add(row);
					cols.add(col);
				}
			}
		}
		int row = rows.get(rows.size() / 2);
		Collections.sort(cols);
		int col = cols.get(cols.size() / 2);
		return minDistance1D(rows, row) + minDistance1D(cols, col);
	}

//	private int minDistance1D(List<Integer> points, int origin) {
//		int distance = 0;
//		for (int point : points) {
//			distance += Math.abs(point - origin);
//		}
//		return distance;
//	}

	// Find median without sorting. We can construct the list in order.
	public int minTotalDistance3(int[][] grid) {
		List<Integer> rows = collectRows(grid);
		List<Integer> cols = collectCols(grid);
		int row = rows.get(rows.size() / 2);
		int col = cols.get(cols.size() / 2);
		return minDistance1D(rows, row) + minDistance1D(cols, col);
	}

	private int minDistance1D(List<Integer> points, int origin) {
		int distance = 0;
		for (int point : points) {
			distance += Math.abs(point - origin);
		}
		return distance;
	}

	private List<Integer> collectRows(int[][] grid) {
		List<Integer> rows = new ArrayList<>();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 1) {
					rows.add(row);
				}
			}
		}
		return rows;
	}

	private List<Integer> collectCols(int[][] grid) {
		List<Integer> cols = new ArrayList<>();
		for (int col = 0; col < grid[0].length; col++) {
			for (int row = 0; row < grid.length; row++) {
				if (grid[row][col] == 1) {
					cols.add(col);
				}
			}
		}
		return cols;
	}
}
