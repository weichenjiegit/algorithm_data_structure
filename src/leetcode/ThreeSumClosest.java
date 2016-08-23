package leetcode;

import java.util.Arrays;

/**
 * Given an array S of n integers,
 * find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
	public int threeSumClosest(int[] num, int target) {
		if (num.length < 3)
			return 0;
		Arrays.sort(num);
		int origin, left, right, res = 0, sum, distance = Integer.MAX_VALUE, curDis, i = 0;
		while (i < num.length - 2) {
			origin = num[i];
			left = i + 1;
			right = num.length - 1;
			while (left < right) {
				sum = origin + num[left] + num[right];
				curDis = Math.abs(origin + num[left] + num[right] - target);
				if (curDis < distance) {
					distance = curDis;
					res = sum;
				}
				if (sum == target) {
					return res;
				} else if (sum >= target) {
					--right;
				} else {
					++left;
				}
			}
			while (++i < num.length && num[i] == origin)
				;
		}
		return res;
	}
}
