package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The
 * algorithm should run in linear time and in O(1) space.
 */
public class MajorityElementII {
	public List<Integer> majorityElement(int[] nums) {
		if (nums == null || nums.length == 0)
			return new ArrayList<>();
		List<Integer> result = new ArrayList<>();
		int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
		for (int i = 0; i < len; i++) {
			if (nums[i] == number1)
				count1++;
			else if (nums[i] == number2)
				count2++;
			else if (count1 == 0) {
				number1 = nums[i];
				count1 = 1;
			} else if (count2 == 0) {
				number2 = nums[i];
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}
		count1 = 0;
		count2 = 0;
		for (int i = 0; i < len; i++) {
			if (nums[i] == number1)
				count1++;
			else if (nums[i] == number2)
				count2++;
		}
		if (count1 > len / 3)
			result.add(number1);
		if (count2 > len / 3)
			result.add(number2);
		return result;
	}

	// Solution for general case
	public List<Integer> majorityElement2(int[] nums) {
		return helper(nums, 3);
	}

	public List<Integer> helper(int[] nums, int k) {
		List<Integer> ret = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		if (nums.length == 0)
			return ret;
		for (int n : nums) {
			if (map.containsKey(n))
				map.put(n, map.get(n) + 1);
			else if (map.keySet().size() < k - 1)
				map.put(n, 1);
			else {
				boolean flag = false;
				for (Integer i : map.keySet()) {
					if (map.get(i) == 0) {
						map.remove(i);
						map.put(n, 1);
						flag = true;
						break;
					}
				}
				if (flag == false) {
					for (Integer i : map.keySet()) {
						map.put(i, map.get(i) - 1);
					}
				}
			}
		}
		for (Integer i : map.keySet()) {
			map.put(i, 0);
		}
		for (int n : nums) {
			if (map.containsKey(n))
				map.put(n, map.get(n) + 1);
		}
		for (Integer i : map.keySet()) {
			if (map.get(i) > nums.length / k)
				ret.add(i);
		}
		return ret;
	}

	public static void main(String[] args) {
		new MajorityElementII().majorityElement(new int[] { 3, 3, 3, 3, 1, 2, 4, 5, 6, 7 });
	}
}
