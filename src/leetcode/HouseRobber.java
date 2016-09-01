package leetcode;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police if
 * two adjacent houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {

	public int rob(int[] nums) {
		if (nums.length == 0)
			return 0;
		if (nums.length == 1)
			return nums[0];

		// Initialize an arrays to store the money
		int[] mark = new int[nums.length];

		// We can infer the formula from problem:mark[i]=max(num[i]+mark[i-2],mark[i-1])
		// so initialize two nums at first.
		mark[0] = nums[0];
		mark[1] = Math.max(nums[0], nums[1]);

		// Using Dynamic Programming to mark the max money in loop.
		for (int i = 2; i < nums.length; i++) {
			mark[i] = Math.max(nums[i] + mark[i - 2], mark[i - 1]);
		}
		return mark[nums.length - 1];
	}

	public int rob2(int[] num) {
		int prevNo = 0;
		int prevYes = 0;
		for (int n : num) {
			int temp = prevNo;
			prevNo = Math.max(prevNo, prevYes);
			prevYes = n + temp;
		}
		return Math.max(prevNo, prevYes);
	}
}
