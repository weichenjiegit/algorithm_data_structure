package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates. Your function should
 * return true if any value appears at least twice in the array, and it should return false if every
 * element is distinct.
 */
public class ContainsDuplicate {

	public boolean containsDuplicate(int[] nums) {
		Arrays.sort(nums);
		for (int ind = 1; ind < nums.length; ind++) {
			if (nums[ind] == nums[ind - 1]) {
				return true;
			}
		}
		return false;
	}

	public boolean containsDuplicate2(int[] nums) {
		final Set<Integer> distinct = new HashSet<Integer>();
		for (int num : nums) {
			if (distinct.contains(num)) {
				return true;
			}
			distinct.add(num);
		}
		return false;
	}
}
