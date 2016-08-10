package leetcode;

/**
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn
 * such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 * 
 * Note: You may not slant the container.
 */
public class ContainerWithMostWater {
	
	// O(n), starts from two ends.
	// No need to compute the area if the new height is
	// smaller than the current ones, since right - left decreases already.
	public int maxArea(int[] height) {
		int maxArea = 0;
		int left = 0;
		int right = height.length - 1;
		while (left < right) {
			maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right])
				while (height[left] > height[++left])
					;
			else
				while (height[right] > height[--right])
					;
		}
		return maxArea;
	}
}
