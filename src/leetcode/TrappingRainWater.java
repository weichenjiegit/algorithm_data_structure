package leetcode;

/**
 * Given n non-negative integers representing an elevation map
 * where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * 
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrappingRainWater {
	
	// Move the smaller one of left and right to middle
	// to make sure there is a closure to calculate water
	// O(n)
	public int trap(int[] height) {
		int len = height.length;
		if (len < 3)
			return 0;
		int left = 0, right = len - 1, res = 0;
		int wall;
		while (left < right) {
			if (height[left] <= height[right]) {
				wall = left + 1;
				while (height[wall] <= height[left] && wall < right) {
					res += height[left] - height[wall];
					wall++;
				}
				left = wall;
			} else { // height[left] > height[right]
				wall = right - 1;
				while (height[wall] <= height[right] && wall > left) {
					res += height[right] - height[wall];
					wall--;
				}
				right = wall;
			}
		}
		return res;
	}
}
