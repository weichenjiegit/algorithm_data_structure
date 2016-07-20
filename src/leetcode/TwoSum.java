package leetcode;

import java.util.HashMap;
import java.util.Map;

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
