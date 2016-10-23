package leetcode;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned
 * into two subsets such that the sum of elements in both subsets is equal.
 * 
 * Note: Each of the array element will not exceed 100. The array size will not exceed 200.
 */
public class PartitionEqualSubsetSum {
	public boolean canPartition(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum % 2 != 0)
			return false;
		int[] dp = new int[sum / 2];
		return helper(sum / 2, 0, 0, nums, dp);

	}

	public boolean helper(int max, int cur, int index, int[] nums, int[] dp) {
		if (cur > max || index >= nums.length) {
			return false;
		} else if (cur == max) {
			return true;
		}
		if (dp[cur] == 1) {
			return true;
		} else if (dp[cur] == -1) {
			return false;
		}
		boolean res = helper(max, cur + nums[index], index + 1, nums, dp) || helper(max, cur, index + 1, nums, dp);
		dp[cur] = res ? 1 : -1;
		return res;
	}
}
