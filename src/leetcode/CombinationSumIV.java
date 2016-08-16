package leetcode;

import java.util.Arrays;

/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible
 * combinations that add up to a positive integer target. Example: nums = [1, 2, 3] target = 4
 * 
 * The possible combination ways are: (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3,
 * 1)
 * 
 * Note that different sequences are counted as different combinations.
 * 
 * Therefore the output is 7.
 */
public class CombinationSumIV {

	// Solution 1
	// Recursive without DP
	public int combinationSum41(int[] nums, int target) {
		if (target == 0) {
			return 1;
		}
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) {
				res += combinationSum41(nums, target - nums[i]);
			}
		}
		return res;
	}

	// Solution 2
	// DP, top down
	public int combinationSum42(int[] nums, int target) {
		int[] dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		return helper(dp, nums, target);
	}

	private int helper(int[] dp, int[] nums, int target) {
		if (dp[target] != -1) {
			return dp[target];
		}
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) {
				res += helper(dp, nums, target - nums[i]);
			}
		}
		dp[target] = res;
		return res;
	}

	// Solution 3
	// DP, bottom up
	public int combinationSum43(int[] nums, int target) {
		int[] comb = new int[target + 1];
		comb[0] = 1;
		for (int i = 1; i < comb.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i - nums[j] >= 0) {
					comb[i] += comb[i - nums[j]];
				}
			}
		}
		return comb[target];
	}
}
