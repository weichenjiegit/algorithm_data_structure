package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj)
 * of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * 
 * If there are multiple solutions, return any subset is fine.
 * 
 * Example 1:
 * 
 * nums: [1,2,3]
 * 
 * Result: [1,2] (of course, [1,3] will also be ok)
 * 
 * Example 2:
 * 
 * nums: [1,2,4,8]
 * 
 * Result: [1,2,4,8]
 */
public class LargestDivisibleSubset {

	public List<Integer> largestDivisibleSubset(int[] nums) {
		if (nums.length == 0)
			return new ArrayList<>();
		Arrays.sort(nums);
		int n = nums.length;
		int[] dp = new int[n];
		// for each element in nums, find the length of largest subset it has.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] % nums[j] == 0)
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
		// pick the index of the largest element in dp.
		int max = 0;
		int index = 0;
		for (int i = 0; i < n; i++) {
			if (dp[i] > max) {
				index = i;
				max = dp[i];
			}
		}
		// from nums[maxIndex] to 0, add every element belongs to the largest subset.
		List<Integer> ans = new ArrayList<>();
		int num = nums[index];
		for (int i = index; i >= 0; i--) {
			if (num % nums[i] == 0 && dp[index] - dp[i] <= 1) {
				ans.add(nums[i]);
				num = nums[i];
				index = i;
			}
		}
		Collections.sort(ans);
		return ans;
	}

	public List<Integer> largestDivisibleSubset2(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return result;
		Arrays.sort(nums);
		int[] count = new int[nums.length], parents = new int[nums.length];
		int maxIdx = 0;
		for (int i = 0, j; i < nums.length; maxIdx = count[i] > count[maxIdx] ? i : maxIdx, i++)
			for (j = 0, count[i] = 1, parents[i] = -1; j < i; j++)
				if (nums[i] % nums[j] == 0 && count[j] + 1 > count[i]) {
					count[i] = count[j] + 1;
					parents[i] = j;
				}
		for (; maxIdx != -1; maxIdx = parents[maxIdx])
			result.add(nums[maxIdx]);
		return result;
	}
}
