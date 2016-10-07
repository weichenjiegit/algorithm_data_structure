package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the
 * given points.
 * 
 * Example 1:
 * 
 * Given points = [[1,1],[-1,1]], return true.
 * 
 * Example 2:
 * 
 * Given points = [[1,1],[-1,-1]], return false.
 * 
 * Follow up: Could you do better than O(n2)?
 * 
 * Hint:
 * 
 * Find the smallest and largest x-value for all points.
 * 
 * If there is a line then it should be at y = (minX + maxX) / 2.
 * 
 * For each point, make sure that it has a reflected point in the opposite side.
 */
public class LineReflection {
	public boolean isReflected(int[][] points) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		Set<String> set = new HashSet<>();
		for (int[] p : points) {
			max = Math.max(max, p[0]);
			min = Math.min(min, p[0]);
			String str = p[0] + "," + p[1];
			set.add(str);
		}
		int sum = max + min;
		for (int[] p : points) {
			String str = (sum - p[0]) + "," + p[1];
			if (!set.contains(str))
				return false;

		}
		return true;
	}

	public boolean isReflected2(int[][] points) {
		HashSet<Integer> pointSet = new HashSet<>();
		int sum;
		int maxX, minX;

		minX = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		for (int[] point : points) {
			maxX = Math.max(maxX, point[0]);
			minX = Math.min(minX, point[0]);
			pointSet.add(Arrays.hashCode(point));
		}

		sum = maxX + minX;
		for (int[] point : points) {
			if (!pointSet.contains(Arrays.hashCode(new int[] { sum - point[0], point[1] }))) {
				return false;
			}
		}
		return true;
	}
}
