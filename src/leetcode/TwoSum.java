package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers,
 * find two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers
 * such that they add up to the target, where index1 must be less than index2.
 * Please note that your returned answers
 * (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		for (int left = 0; left < nums.length; left++) {
			for (int right = left + 1; right < nums.length; right++) {
				if (nums[left] + nums[right] == target) {
					return new int[] { left, right };
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution.");
	}

	public int[] twoSumHashtable(int[] nums, int target) {
		Map<Integer, Integer> indexByRemaining = new HashMap<>();
		for (int index = 0; index < nums.length; index++) {
			if (indexByRemaining.containsKey(nums[index])) {
				return new int[]{indexByRemaining.get(nums[index]), index};
			} else {
				indexByRemaining.put(target - nums[index], index);
			}
		}
		throw new IllegalArgumentException("No two sum solution.");
	}
}
